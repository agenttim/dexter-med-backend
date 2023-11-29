package ru.dextermed.dextermedbackend.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Table("medical_document")
public class MedicalDocument {

    @Id
    private Long id;

    private String documentName;
    private String documentDescription;
    private String documentContent;
    private Date documentDate;
    private String file;

    @PersistenceCreator
    public MedicalDocument(Long id, String documentName, String documentDescription, String documentContent, Date documentDate, String file) {
        this.id = id;
        this.documentName = documentName;
        this.documentDescription = documentDescription;
        this.documentContent = documentContent;
        this.documentDate = documentDate;
        this.file = file;
    }

    public String getDocumentName() {
        return documentName;
    }

    public String getDocumentDescription() {
        return documentDescription;
    }

    public String getDocumentContent() {
        return documentContent;
    }

    public Date getDocumentDate() {
        return documentDate;
    }


    public String getFile() {
        return file;
    }
}
