package ru.tsu.hits.kosterror.laundryemulator.service;

import ru.tsu.hits.kosterror.laundryemulator.dto.StatusDto;

public interface EmulationService {

    void start(Long id);

    StatusDto status(Long id);

}
