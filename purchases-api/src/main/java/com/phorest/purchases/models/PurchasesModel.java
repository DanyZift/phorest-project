package com.phorest.purchases.models;

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
@Table(name = "purchases")
public class PurchasesModel {

    @Id
    private UUID id;

    private UUID appointmentId;

    private String name;

    private Double price;

    private Integer loyaltyPoints;

}

