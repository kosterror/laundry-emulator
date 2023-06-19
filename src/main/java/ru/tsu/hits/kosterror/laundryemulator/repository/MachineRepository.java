package ru.tsu.hits.kosterror.laundryemulator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tsu.hits.kosterror.laundryemulator.entity.Machine;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Long> {
}
