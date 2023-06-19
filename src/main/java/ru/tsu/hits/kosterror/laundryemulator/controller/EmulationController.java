package ru.tsu.hits.kosterror.laundryemulator.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.tsu.hits.kosterror.laundryemulator.dto.StatusDto;
import ru.tsu.hits.kosterror.laundryemulator.service.EmulationService;

@RestController
@RequestMapping("/api/emulation")
@RequiredArgsConstructor
@Tag(name = "Запросы эмулирующие машины")
public class EmulationController {

    private final EmulationService emulationService;

    @PostMapping("/{id}/start")
    public void start(@PathVariable Long id) {
        emulationService.start(id);
    }

    @GetMapping("/{id}/status")
    public StatusDto status(@PathVariable Long id) {
        return emulationService.status(id);
    }

}
