package ru.dextermed.dextermedbackend.entities.laboratory;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "medical_tests_document") // Имя таблицы в базе данных
public class MedicalTestsDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; // Идентификатор документа с группой анализов

    @Column(name = "patient_id")
    private Long patientId; // Идентификатор пациента

    @Column(name = "document_name")
    private String documentName; // Название документа (например, "Группа медицинских анализов")

    @Column(name = "creation_date")
    private Date creationDate; // Дата создания документа

    @Lob
    @Column(name = "json_data", columnDefinition = "TEXT") // Изменено имя поля на json_data
    private String jsonData; // Данные в формате JSON, сформированные с помощью ИИ


    // Далее следует ваш код
}
