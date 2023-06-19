package ru.tsu.hits.kosterror.laundryemulator.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.tsu.hits.kosterror.laundryemulator.dto.StatusDto;
import ru.tsu.hits.kosterror.laundryemulator.exception.ConflictException;
import ru.tsu.hits.kosterror.laundryemulator.exception.NotFoundException;
import ru.tsu.hits.kosterror.laundryemulator.repository.MachineRepository;
import ru.tsu.hits.kosterror.laundryemulator.service.EmulationService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmulationServiceImpl implements EmulationService {

    private final MachineRepository machineRepository;
    private final Random random;

    @Override
    public void start(Long id) {
        var machine = machineRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Машина с таким id не найдена"));

        if (Boolean.TRUE.equals(machine.getIsWorking())) {
            throw new ConflictException("Стиральная машина уже работает");
        }

        checkDoor();
        machine.setStartTime(LocalDateTime.now());
        machine.setIsWorking(true);
        machineRepository.save(machine);
    }

    @Override
    public StatusDto status(Long id) {
        var machine = machineRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Машина с таким id не найдена"));

        return new StatusDto(Boolean.TRUE.equals(machine.getIsWorking()) ? 1 : 0);
    }

    private void checkDoor() {
        int count = 0;

        for (int i = 0; i < 10; i++) {
            count += random.nextInt(10);
        }

        if (count >= 60) {
            throw new ConflictException("Дверь у стиральной машины открыта!");
        }
    }

    @Scheduled(timeUnit = TimeUnit.SECONDS, fixedRate = 5)
    private void updateStatus() {
        var machines = machineRepository.findAllByIsWorking(true);
        var currentDT = LocalDateTime.now();

        for (var machine : machines) {
            var oldDT = machine.getStartTime();
            var diff = oldDT.until(currentDT, ChronoUnit.MINUTES);
            if (diff >= machine.getWorkTime()) {
                machine.setStartTime(null);
                machine.setIsWorking(false);
                log.info("Машина с id {} закончила работу", machine.getId());
            }
        }

        machineRepository.saveAll(machines);
    }

}
