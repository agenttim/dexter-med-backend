package ru.dextermed.dextermedbackend.entities;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Table("medical_document")
public class MedicalDocument {

    @Id
    private Long id;

    private Long userId;

    private String documentName;
    private String documentDescription;
    private String documentContent;
    private Date documentDate;
    private String file;

    @PersistenceCreator
    public MedicalDocument(Long id, Long userId, String documentName, String documentDescription, String documentContent, Date documentDate, String file) {
        this.id = id;
        this.userId = userId;
        this.documentName = documentName;
        this.documentDescription = documentDescription;
        this.documentContent = documentContent;
        this.documentDate = documentDate;
        this.file = file;
    }

    public Long getId() {
        return id;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentDescription() {
        return documentDescription;
    }

    public void setDocumentDescription(String documentDescription) {
        this.documentDescription = documentDescription;
    }

    public String getDocumentContent() {
        return documentContent;
    }

    public void setDocumentContent(String documentContent) {
        this.documentContent = documentContent;
    }

    public Date getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(Date documentDate) {
        this.documentDate = documentDate;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
