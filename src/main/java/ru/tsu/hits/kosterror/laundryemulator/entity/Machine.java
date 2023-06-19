package ru.tsu.hits.kosterror.laundryemulator.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private MachineType type;

    @Column(nullable = false)
    private Boolean isWorking;

    private LocalDateTime startTime;

    @Column(nullable = false)
    private int workTime;

}
