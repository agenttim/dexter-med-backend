package ru.dextermed.dextermedbackend.service.laboratory;

import lombok.Data;
import org.springframework.stereotype.Service;
import ru.dextermed.dextermedbackend.entities.laboratory.MedicalTestsDocument;
import ru.dextermed.dextermedbackend.repository.laboratory.MedicalTestsDocumentRepository;

@Data
@Service
public class MedicalTestDocumentService {

    private final MedicalTestsDocumentRepository medicalTestsDocumentRepository;


    public MedicalTestsDocument getMedicalTestsDocumentById(Long documentId) {

        return medicalTestsDocumentRepository.findByIdCustom(documentId);
    }
}
