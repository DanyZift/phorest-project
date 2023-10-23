package com.phorest.data.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "appointment")
public class AppointmentsModel {

    @Id
    private UUID id;

    @NonNull
    private UUID clientId;

    @NonNull
    private LocalDateTime startTime;

    @NonNull
    private LocalDateTime endTime;

}
