package ru.dextermed.dextermedbackend.service;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dextermed.dextermedbackend.entities.MedicalDocument;
import ru.dextermed.dextermedbackend.entities.User;
import ru.dextermed.dextermedbackend.repository.MedicalDocumentRepository;
import ru.dextermed.dextermedbackend.repository.UserRepository;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MedicalDocumentService {

    private final MedicalDocumentRepository medicalDocumentRepository;
    private final UserRepository userRepository;

    @Autowired
    public MedicalDocumentService(MedicalDocumentRepository medicalDocumentRepository, UserRepository userRepository) {
        this.medicalDocumentRepository = medicalDocumentRepository;
        this.userRepository = userRepository;
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

    public List<MedicalDocument> getMedicalDocumentsByUser(Principal principal) {
        // Получение имени пользователя из аутентификации
        String username = principal.getName();

        // Получение пользователя по имени пользователя
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Реализация метода для фильтрации по идентификатору пользователя
            List<MedicalDocument> userMedicalDocuments = medicalDocumentRepository.findByUserId(user.getId());

            // Верните список документов пользователя
            return userMedicalDocuments;
        } else {
            // Если пользователя не найдено, вернуть пустой список или обработать ошибку
            return List.of(); // или можно бросить исключение
        }
    }
}
