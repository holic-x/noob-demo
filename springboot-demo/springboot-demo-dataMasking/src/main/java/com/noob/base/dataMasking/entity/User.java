package com.noob.base.dataMasking.entity;

import com.example.datamasking.annotation.DataMasking;
import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @DataMasking(maskType = DataMasking.MaskType.PHONE)
    @Column(name = "phone_number")
    private String phoneNumber;

    @DataMasking(maskType = DataMasking.MaskType.EMAIL)
    private String email;

    @DataMasking(maskType = DataMasking.MaskType.ID_CARD)
    @Column(name = "id_card")
    private String idCard;

    // Getters and Setters
}