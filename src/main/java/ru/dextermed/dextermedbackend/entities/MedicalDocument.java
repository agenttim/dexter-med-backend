package ru.dextermed.dextermedbackend.entities;


import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "medical_document")
public class MedicalDocument {

    @javax.persistence.Id
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "document_name")
    private String documentName;

    @Column(name = "document_description")
    private String documentDescription;

    @Column(name = "document_content")
    private String documentContent;

    @Column(name = "document_date")
    private Date documentDate;

    @Column(name = "file")
    private String file;
}
