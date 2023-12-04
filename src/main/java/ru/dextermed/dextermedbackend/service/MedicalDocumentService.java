package ru.dextermed.dextermedbackend.service;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dextermed.dextermedbackend.model.MedicalDocument;
import ru.dextermed.dextermedbackend.repository.MedicalDocumentRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    public MedicalDocument getMedicalDocumentById(Long id) {
        return medicalDocumentRepository.findById(id).orElse(null);
    }

    public MedicalDocument createMedicalDocument(MedicalDocument medicalDocument) {
        return medicalDocumentRepository.save(medicalDocument);
    }

    public MedicalDocument updateMedicalDocument(Long id, MedicalDocument updatedDocument) {
        return medicalDocumentRepository.findById(id)
                .map(existingDocument -> {
                    existingDocument.setDocumentName(updatedDocument.getDocumentName());
                    existingDocument.setDocumentDescription(updatedDocument.getDocumentDescription());
                    existingDocument.setDocumentContent(updatedDocument.getDocumentContent());
                    existingDocument.setDocumentDate(updatedDocument.getDocumentDate());
                    existingDocument.setFile(updatedDocument.getFile());

                    return medicalDocumentRepository.save(existingDocument);
                })
                .orElse(null);
    }

    public boolean deleteMedicalDocument(Long id) {
        if (medicalDocumentRepository.existsById(id)) {
            medicalDocumentRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public MedicalDocument patchMedicalDocument(Long id, Map<String, Object> updates) {
        MedicalDocument existingDocument = getMedicalDocumentById(id);

        if (existingDocument != null) {
            // Применяем обновления к существующему документу
            // Например, можно использовать библиотеку ModelMapper для копирования полей
            // или обновлять поля вручную

            // Пример с использованием Jackson ObjectMapper:
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                objectMapper.updateValue(existingDocument, updates);
            } catch (JsonMappingException e) {
                throw new RuntimeException(e);
            }

            // Сохраняем обновленный документ в базе данных
            return medicalDocumentRepository.save(existingDocument);
        } else {
            return null;
        }
    }
}
