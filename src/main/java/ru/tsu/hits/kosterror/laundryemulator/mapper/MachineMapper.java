package ru.tsu.hits.kosterror.laundryemulator.mapper;

import org.springframework.stereotype.Component;
import ru.tsu.hits.kosterror.laundryemulator.dto.CreateUpdateMachineDto;
import ru.tsu.hits.kosterror.laundryemulator.dto.MachineDto;
import ru.tsu.hits.kosterror.laundryemulator.entity.Machine;

@Component
public class MachineMapper {

    public MachineDto entityToDto(Machine entity) {
        return MachineDto
                .builder()
                .id(entity.getId())
                .type(entity.getType())
                .isWorking(entity.getIsWorking())
                .startTime(entity.getStartTime())
                .workTime(entity.getWorkTime())
                .build();
    }

    public Machine dtoToEntity(CreateUpdateMachineDto dto) {
        return Machine
                .builder()
                .type(dto.getType())
                .isWorking(false)
                .workTime(dto.getWorkTime())
                .build();
    }

}
