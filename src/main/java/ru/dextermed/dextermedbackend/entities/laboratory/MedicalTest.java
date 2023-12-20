package ru.dextermed.dextermedbackend.entities.laboratory;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "medical_tests") // Имя таблицы в базе данных
public class MedicalTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; // Идентификатор анализа

    @Column(name = "patient_id")
    private Long patientId; // Идентификатор пациента

    @Column(name = "name")
    private String name;

    @Column(name = "date")
    private Date date;

    @Column(name = "results")
    private String results;


    @Column(name = "keywords",  columnDefinition = "TEXT[]")
    private String keywords;

    // Далее следует ваш код
}
