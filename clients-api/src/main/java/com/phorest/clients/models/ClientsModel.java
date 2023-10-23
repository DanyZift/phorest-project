package com.phorest.clients.models;

import com.phorest.clients.enums.GenderType;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "client")
public class ClientsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(length = 50)
    private String firstName;
    @Column(length = 50)
    private String lastName;
    @NonNull
    @Column(unique = true, length = 80)
    private String email;

    @Column(length = 35)
    private String phone;

    @Column(name = "gender")
    private GenderType gender;

    private Boolean banned;
}
