package ru.plants.care.back.services.impl;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;
import ru.plants.care.back.dto.florist.BaseFloristDTO;
import ru.plants.care.back.dto.florist.FloristDTO;
import ru.plants.care.back.dto.florist.FloristTaskDTO;
import ru.plants.care.back.dto.plant.PlantListRecordDTO;
import ru.plants.care.back.dto.task.TaskPeriod;
import ru.plants.care.back.exception.DuplicateKeyException;
import ru.plants.care.back.exception.ItemNotFoundException;
import ru.plants.care.back.mapper.FloristMapper;
import ru.plants.care.back.mapper.PlantMapper;
import ru.plants.care.back.mapper.TaskMapper;
import ru.plants.care.back.repository.FloristRepository;
import ru.plants.care.back.repository.PlantRepository;
import ru.plants.care.back.repository.TaskRunRepository;
import ru.plants.care.back.repository.model.TaskRunEntity;
import ru.plants.care.back.services.FloristService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class FloristServiceImpl implements FloristService {
    private final FloristRepository floristRepository;
    private final PlantRepository plantRepository;
    private final FloristMapper mapper;
    private final PlantMapper plantMapper;
    private final TaskMapper taskMapper;
    private final TaskRunRepository taskRunRepository;

    @Override
    public FloristDTO saveFlorist(BaseFloristDTO florist) {
        var floristEntity = floristRepository.findByEmail(florist.getEmail());
        if (floristEntity != null) {
            throw new DuplicateKeyException("Уже существует запись с таким email:  " + florist.getEmail());
        }

        return mapper.floristEntityToFloristDto(floristRepository.save(mapper.baseFloristDtoToFloristEntity(florist)));
    }

    @Override
    public void deleteFlorist(Long id) {
        floristRepository.deleteById(id);
    }

    @Override
    public List<FloristDTO> getFloristList() {
        return mapper.floristEntityToFloristDto(floristRepository.findAll());
    }

    @Override
    public void addPlant(Long floristId, Long plantId) {
        var floristEntity = floristRepository.findById(floristId);
        if (floristEntity.isEmpty()) {
            throw new ItemNotFoundException("Florist not found: " + floristId);
        }

        var plantEntity = plantRepository.findById(plantId);
        if (plantEntity.isEmpty()) {
            throw new ItemNotFoundException("Plant not found: " + plantId);
        }
        if(floristEntity.get().getPlants().contains(plantEntity.get())){
            throw new DuplicateKeyException("Florist already has this plant: " + plantId);
        }

        floristEntity.get().getPlants().add(plantEntity.get());
        floristRepository.save(floristEntity.get());
    }

    @Override
    public BaseFloristDTO updateFlorist(Long id, BaseFloristDTO floristDTO) {
        var floristEntity = floristRepository.findById(id);
        if (floristEntity.isEmpty()) {
            throw new ItemNotFoundException("Florist not found: " + id);
        }
        floristEntity.get().setPlants(floristEntity.get().getPlants());

        mapper.updateFloristEntity(floristDTO, floristEntity.get());
        return mapper.floristEntityToFloristDto(floristRepository.save(floristEntity.get()));
    }

    @Override
    public List<PlantListRecordDTO> getFloristPlants(Long id) {
        var floristEntity = floristRepository.findById(id);
        if (floristEntity.isEmpty()) {
            throw new ItemNotFoundException("Florist not found: " + id);
        }
        return plantMapper.plantEntityToListPlantDTO(floristEntity.get().getPlants());
    }

    @Override
    public FloristDTO getFlorist(Long id) {
        var floristEntity = floristRepository.findById(id);
        if (floristEntity.isEmpty()) {
            throw new ItemNotFoundException("Florist not found: " + id);
        }
        return mapper.floristEntityToFloristDto(floristEntity.get());
    }
    @Override
    public List<FloristTaskDTO> getFloristTasksAtDate(Long id, LocalDate date) {
        return floristRepository.findById(id)
                .map(floristEntity -> floristEntity.getPlants().stream().flatMap(
                                plantEntity -> plantEntity.getTasks().stream()
                                        .filter(taskEntity -> Boolean.TRUE.equals(taskEntity.getEnabled()) &&
                                                taskEntity.getStartDate() != null && taskEntity.getStartDate().toLocalDate().minusDays(1).isBefore(date) &&
                                                (taskEntity.getEndDate() == null || taskEntity.getEndDate().toLocalDate().plusDays(1).isAfter(date)))
                        )
                        .collect(Collectors.toList()))
                .map(tasks ->
                        tasks.stream()
                                .flatMap(task -> {
                                    if (date.isBefore(LocalDate.now())) {
                                        return taskRunRepository.findAllByTaskAndStartAtBetween(task, date.atStartOfDay(), date.atTime(LocalTime.of(23, 59, 59, 9999)))
                                                .stream()
                                                .map(taskRun -> FloristTaskDTO.builder().
                                                        runDateTime(taskRun.getStartAt())
                                                        .task(taskMapper.taskEntityToTaskListRecordDTO(task))
                                                        .taskRun(taskMapper.toTaskRunDTO(taskRun))
                                                        .build());
                                    } else {
                                        LocalDateTime calcDateTime = task.getNextRun() != null && date.isAfter(task.getNextRun().toLocalDate().minusDays(1)) ?
                                                task.getNextRun() :
                                                task.getStartDate();
                                        if(task.getPeriod() == TaskPeriod.HOURLY){
                                            calcDateTime = LocalDateTime.of(date, LocalTime.of(0,
                                                    calcDateTime.toLocalTime().getMinute(),
                                                    calcDateTime.toLocalTime().getSecond(),
                                                    calcDateTime.toLocalTime().getNano()
                                            ));
                                        }else{
                                            calcDateTime = LocalDateTime.of(date, LocalTime.of(calcDateTime.getHour(),
                                                    calcDateTime.toLocalTime().getMinute(),
                                                    calcDateTime.toLocalTime().getSecond(),
                                                    calcDateTime.toLocalTime().getNano()
                                            ));
                                        }

                                        return Stream.concat(
                                                        date.equals(LocalDate.now()) ?
                                                                taskRunRepository.findAllByTaskAndStartAtBetween(task, date.atStartOfDay(), LocalDateTime.now())
                                                                        .stream()
                                                                        .map(taskRun -> Pair.of(taskRun.getStartAt(), taskRun)) :
                                                                Stream.empty(),
                                                        Stream.iterate(calcDateTime,
                                                                        curDateTime -> curDateTime.toLocalDate().isBefore(date.plusDays(1)),
                                                                        runDateTime -> switch (task.getPeriod()) {
                                                                            case HOURLY -> runDateTime.plusHours(1);
                                                                            case DAILY -> runDateTime.plusDays(1);
                                                                            case WEEKLY -> runDateTime.plusWeeks(1);
                                                                            case MONTHLY -> runDateTime.plusMonths(1);
                                                                            case YEARLY -> runDateTime.plusYears(1);
                                                                        }
                                                                )
                                                                .filter(runDateTime -> runDateTime.isAfter(LocalDateTime.now()))//TODO test
                                                                .map(runDateTime -> Pair.<LocalDateTime, TaskRunEntity>of(runDateTime, null))
                                                )
                                                .filter(runData -> (runData.getLeft().toLocalDate().equals(date) && runData.getLeft().isBefore(task.getEndDate().plusMinutes(1)) && runData.getLeft().isAfter(task.getStartDate().minusMinutes(1))))
                                                .map(runData -> FloristTaskDTO.builder()
                                                        .runDateTime(runData.getLeft())
                                                        .task(taskMapper.taskEntityToTaskListRecordDTO(task))
                                                        .taskRun(taskMapper.toTaskRunDTO(runData.getRight()))
                                                        .build());
                                    }
                                })
                                .collect(Collectors.toList())
                )
                .map(taskRunList -> taskRunList.stream()
                        .sorted(Comparator.comparing(FloristTaskDTO::runDateTime))
                        .collect(Collectors.toList()))
                .orElse(List.of());
    }

    @Override
    public List<FloristTaskDTO> getFloristTasks(Long id) {
        /*return floristRepository.findById(id)
                .map(floristEntity -> floristEntity.getPlants().stream().flatMap(
                                plantEntity -> plantEntity.getTasks().stream())
                        .collect(Collectors.toList()))
                .map(tasks -> tasks.stream()
                                .map(task -> new FloristTaskDTO(task.getStartDate(), taskMapper.taskEntityToTaskListRecordDTO(task)))
                ).stream().collect(Collectors.toList());*/
        return null;
    }

    @Override
    public FloristDTO getFloristByEmail(String email) {
        var florist = floristRepository.findByEmail(email);
        if (florist == null){
            throw new ItemNotFoundException("Florist not found: " + email);
        }
        return mapper.floristEntityToFloristDto(florist);
    }

    @Override
    public void setFirebaseToken(String firebaseToken, String email) {
        var florist = floristRepository.findByEmail(email);
        florist.setFirebaseToken(firebaseToken);
        floristRepository.save(florist);
    }

    public void setUserRole(String uid) throws FirebaseAuthException {
        UserRecord user = FirebaseAuth.getInstance().getUser(uid);
        Map<String, Object> claims = new HashMap<>();
        if (Objects.equals(user.getEmail(), "secret_admin_cat_user@gmail.com")){
            claims.put("role", "admin");
        }else{
            claims.put("role", "user");
        }
        FirebaseAuth.getInstance().setCustomUserClaims(uid, claims);
    }
}
