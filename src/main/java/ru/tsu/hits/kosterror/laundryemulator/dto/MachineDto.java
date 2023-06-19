package ru.tsu.hits.kosterror.laundryemulator.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tsu.hits.kosterror.laundryemulator.entity.MachineType;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MachineDto {
    private Long id;
    private MachineType type;
    private boolean isWorking;
    private LocalDateTime startTime;
    private int workTime;
}
