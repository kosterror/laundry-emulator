package ru.tsu.hits.kosterror.laundryemulator.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.tsu.hits.kosterror.laundryemulator.dto.CreateUpdateMachineDto;
import ru.tsu.hits.kosterror.laundryemulator.dto.MachineDto;
import ru.tsu.hits.kosterror.laundryemulator.service.MachineService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/machines")
@RequiredArgsConstructor
@Tag(name = "CRUD машин")
public class MachineController {

    private final MachineService machineService;

    @Operation(summary = "Создать машину.")
    @PostMapping
    public MachineDto createMachine(@RequestBody @Valid CreateUpdateMachineDto dto) {
        return machineService.createMachine(dto);
    }

    @Operation(summary = "Удалить машину.")
    @DeleteMapping("/{id}")
    public void deleteMachine(@PathVariable Long id) {
        machineService.deleteMachine(id);
    }

    @Operation(summary = "Изменить машину.")
    @PutMapping("/{id}")
    public MachineDto updateMachine(@PathVariable Long id, @RequestBody @Valid CreateUpdateMachineDto dto) {
        return machineService.updateMachine(id, dto);
    }

    @Operation(summary = "Получить машину.")
    @GetMapping("/{id}")
    public MachineDto getMachineById(@PathVariable Long id) {
        return machineService.getMachineById(id);
    }

    @Operation(summary = "Получить все машины.")
    @GetMapping
    public List<MachineDto> getMachines() {
        return machineService.getMachines();
    }

}
