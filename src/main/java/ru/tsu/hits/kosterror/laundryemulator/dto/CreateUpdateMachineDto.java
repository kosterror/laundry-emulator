package ru.tsu.hits.kosterror.laundryemulator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tsu.hits.kosterror.laundryemulator.entity.MachineType;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUpdateMachineDto {
    @NotNull(message = "Тип машины не может быть null")
    private MachineType type;

    @NotNull(message = "Время работы машины не может быть null")
    @Min(value = 0, message = "Работа машины не может быть меньше 0")
    private Integer workTime;
}
