package ru.tsu.hits.kosterror.laundryemulator.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.tsu.hits.kosterror.laundryemulator.dto.CreateUpdateMachineDto;
import ru.tsu.hits.kosterror.laundryemulator.dto.MachineDto;
import ru.tsu.hits.kosterror.laundryemulator.exception.NotFoundException;
import ru.tsu.hits.kosterror.laundryemulator.mapper.MachineMapper;
import ru.tsu.hits.kosterror.laundryemulator.repository.MachineRepository;
import ru.tsu.hits.kosterror.laundryemulator.service.MachineService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MachineServiceImpl implements MachineService {

    private static final String MACHINE_NOT_FOUND = "Машина с таким id не найдена";
    private final MachineRepository machineRepository;
    private final MachineMapper mapper;

    @Override
    public MachineDto createMachine(CreateUpdateMachineDto dto) {
        var machine = mapper.dtoToEntity(dto);
        machine = machineRepository.save(machine);
        return mapper.entityToDto(machine);
    }

    @Override
    public void deleteMachine(Long id) {
        var machine = machineRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(MACHINE_NOT_FOUND));

        machineRepository.delete(machine);
    }

    @Override
    public MachineDto updateMachine(Long id, CreateUpdateMachineDto dto) {
        var machine = machineRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(MACHINE_NOT_FOUND));

        machine.setType(dto.getType());
        machine.setWorkTime(dto.getWorkTime());
        machine = machineRepository.save(machine);
        return mapper.entityToDto(machine);
    }

    @Override
    public MachineDto getMachineById(Long id) {
        var machine = machineRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(MACHINE_NOT_FOUND));

        return mapper.entityToDto(machine);
    }

    @Override
    public List<MachineDto> getMachines() {
        return machineRepository
                .findAll()
                .stream()
                .map(mapper::entityToDto)
                .toList();
    }
}
