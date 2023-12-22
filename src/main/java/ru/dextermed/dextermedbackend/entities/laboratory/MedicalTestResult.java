package ru.dextermed.dextermedbackend.entities.laboratory;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "medical_test_result")
public class MedicalTestResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "medical_test_id")
    private MedicalTest medicalTest;

    @Column(name = "result")
    private String result;

    @ManyToOne
    @JoinColumn(name = "medical_tests_document_id")
    private MedicalTestsDocument medicalTestsDocument;

    // Другие поля по необходимости

    // Геттеры и сеттеры
}

