package ru.dextermed.dextermedbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dextermed.dextermedbackend.entities.MedicalDocument;
import ru.dextermed.dextermedbackend.service.MedicalDocumentService;

import java.util.List;
import java.util.Map;

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

    @GetMapping("/{id}")
    public ResponseEntity<MedicalDocument> getMedicalDocumentById(@PathVariable Long id) {
        MedicalDocument medicalDocument = medicalDocumentService.getMedicalDocumentById(id);
        if (medicalDocument != null) {
            return new ResponseEntity<>(medicalDocument, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<MedicalDocument> createMedicalDocument(@RequestBody MedicalDocument medicalDocument) {
        MedicalDocument createdDocument = medicalDocumentService.createMedicalDocument(medicalDocument);
        return new ResponseEntity<>(createdDocument, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicalDocument> updateMedicalDocument(
            @PathVariable Long id,
            @RequestBody MedicalDocument updatedDocument
    ) {
        MedicalDocument updated = medicalDocumentService.updateMedicalDocument(id, updatedDocument);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicalDocument(@PathVariable Long id) {
        boolean deleted = medicalDocumentService.deleteMedicalDocument(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MedicalDocument> patchMedicalDocument(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates
    ) {
        MedicalDocument patchedDocument = medicalDocumentService.patchMedicalDocument(id, updates);
        if (patchedDocument != null) {
            return new ResponseEntity<>(patchedDocument, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
