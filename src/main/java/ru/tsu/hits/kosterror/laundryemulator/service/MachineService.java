package ru.tsu.hits.kosterror.laundryemulator.service;

import ru.tsu.hits.kosterror.laundryemulator.dto.CreateUpdateMachineDto;
import ru.tsu.hits.kosterror.laundryemulator.dto.MachineDto;

import java.util.List;

public interface MachineService {

    MachineDto createMachine(CreateUpdateMachineDto dto);

    void deleteMachine(Long id);

    MachineDto updateMachine(Long id, CreateUpdateMachineDto dto);

    MachineDto getMachineById(Long id);

    List<MachineDto> getMachines();

}
