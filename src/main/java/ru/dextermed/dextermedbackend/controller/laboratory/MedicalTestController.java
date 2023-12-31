package ru.dextermed.dextermedbackend.controller.laboratory;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dextermed.dextermedbackend.entities.laboratory.MedicalTestResult;
import ru.dextermed.dextermedbackend.entities.laboratory.MedicalTestsDocument;
import ru.dextermed.dextermedbackend.service.laboratory.MedicalTestDocumentService;
import ru.dextermed.dextermedbackend.service.laboratory.MedicalTestResultService;
import ru.dextermed.dextermedbackend.service.laboratory.MedicalTestService;

import java.util.List;
import java.util.Map;

@RestController
@Data
public class MedicalTestController {

    private final MedicalTestService medicalTestService;
    private final MedicalTestDocumentService medicalTestDocumentService;
    private final MedicalTestResultService medicalTestResultService;

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
            medicalTestService.processAndSaveMedicalTests(medicalTestsDocument, 1L);

            return new ResponseEntity<>("Медицинские тесты успешно обработаны", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Произошла ошибка при обработке медицинских тестов", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/process2/{documentId}")
    public ResponseEntity<String> process2MedicalTest(@PathVariable Long documentId) {
        try {
            // Получение MedicalTestsDocument из БД по id
            MedicalTestsDocument medicalTestsDocument = medicalTestDocumentService.getMedicalTestsDocumentById(documentId);

            if (medicalTestsDocument == null) {
                return new ResponseEntity<>("Документ не найден", HttpStatus.NOT_FOUND);
            }

            // Вызов метода для обработки и сохранения медицинских тестов


            return new ResponseEntity<>(medicalTestService.extractMedicalTestResults(medicalTestsDocument.getJsonData()).toString(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Произошла ошибка при обработке медицинских тестов", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user-test-results/{userId}")
    public ResponseEntity<List<MedicalTestResult>> getUserTestResults(@PathVariable Long userId) {
        try {
            List<MedicalTestResult> userTestResults = medicalTestResultService.getUserTestResults(userId);

            if (userTestResults.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(userTestResults, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

