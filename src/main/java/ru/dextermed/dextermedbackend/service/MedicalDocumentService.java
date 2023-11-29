package ru.dextermed.dextermedbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dextermed.dextermedbackend.model.MedicalDocument;
import ru.dextermed.dextermedbackend.repository.MedicalDocumentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicalDocumentService {

    private final MedicalDocumentRepository medicalDocumentRepository;

    @Autowired
    public MedicalDocumentService(MedicalDocumentRepository medicalDocumentRepository) {
        this.medicalDocumentRepository = medicalDocumentRepository;
    }

    public List<MedicalDocument> getAllMedicalDocuments() {
        Iterable<MedicalDocument> medicalDocumentIterable = medicalDocumentRepository.findAll();
        List<MedicalDocument> medicalDocuments = new ArrayList<>();

        medicalDocumentIterable.forEach(medicalDocuments::add);

        return medicalDocuments;
    }
}
