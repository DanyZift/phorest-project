package com.phorest.data.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "purchase")
public class PurchasesModel {

    @Id
    private UUID id;

    private UUID appointmentId;

    private String name;

    private Double price;

    private Integer loyaltyPoints;

}
