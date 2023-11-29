package ru.dextermed.dextermedbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dextermed.dextermedbackend.model.MedicalDocument;
import ru.dextermed.dextermedbackend.service.MedicalDocumentService;

import java.util.List;

@RestController
@RequestMapping("/api/medical-documents")
public class MedicalDocumentController {

    private final MedicalDocumentService medicalDocumentService;

    @Autowired
    public MedicalDocumentController(MedicalDocumentService medicalDocumentService) {
        this.medicalDocumentService = medicalDocumentService;
    }

    @GetMapping
    public ResponseEntity<List<MedicalDocument>> getAllMedicalDocuments() {
        List<MedicalDocument> medicalDocuments = medicalDocumentService.getAllMedicalDocuments();
        return new ResponseEntity<>(medicalDocuments, HttpStatus.OK);
    }
}
