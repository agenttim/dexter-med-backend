package ru.dextermed.dextermedbackend.controller.laboratory;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dextermed.dextermedbackend.entities.laboratory.MedicalTestsDocument;
import ru.dextermed.dextermedbackend.repository.laboratory.MedicalTestsDocumentRepository;
import ru.dextermed.dextermedbackend.service.laboratory.MedicalTestDocumentService;
import ru.dextermed.dextermedbackend.service.laboratory.MedicalTestService;

import java.util.Map;

@RestController
@Data
public class MedicalTestController {

    private final MedicalTestService medicalTestService;
    private final MedicalTestDocumentService medicalTestDocumentService;


    @PostMapping("/test-extraction")
    public ResponseEntity<Map<String, String>> testExtraction(@RequestBody String jsonData) {
        try {
            Map<String, String> medicalTestResults = medicalTestService.extractMedicalTestResults(jsonData);
            return new ResponseEntity<>(medicalTestResults, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace(); // Обработай исключение в соответствии с твоими потребностями
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/process/{documentId}")
    public ResponseEntity<String> processMedicalTest(@PathVariable Long documentId) {
        try {
            // Получение MedicalTestsDocument из БД по id
            MedicalTestsDocument medicalTestsDocument = medicalTestDocumentService.getMedicalTestsDocumentById(documentId);

            if (medicalTestsDocument == null) {
                return new ResponseEntity<>("Документ не найден", HttpStatus.NOT_FOUND);
            }

            // Вызов метода для обработки и сохранения медицинских тестов
            medicalTestService.extractMedicalTestResults(medicalTestsDocument.getJsonData());

            return new ResponseEntity<>("Медицинские тесты успешно обработаны", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Произошла ошибка при обработке медицинских тестов", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

/*    @GetMapping("/process/{documentId}")
    public String processMedicalTest(@PathVariable Long documentId) {
        MedicalTestsDocument medicalTestsDocument = medicalTestDocumentService.getMedicalTestsDocumentById(documentId);
        return medicalTestsDocument.getDocumentName();
    }*/
}

