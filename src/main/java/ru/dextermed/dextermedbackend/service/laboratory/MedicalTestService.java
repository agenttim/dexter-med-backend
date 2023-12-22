package ru.dextermed.dextermedbackend.service.laboratory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.stereotype.Service;
import ru.dextermed.dextermedbackend.entities.laboratory.MedicalTest;
import ru.dextermed.dextermedbackend.entities.laboratory.MedicalTestResult;
import ru.dextermed.dextermedbackend.entities.laboratory.MedicalTestsDocument;
import ru.dextermed.dextermedbackend.repository.laboratory.MedicalTestRepository;
import ru.dextermed.dextermedbackend.repository.laboratory.MedicalTestResultRepository;
import ru.dextermed.dextermedbackend.repository.laboratory.MedicalTestsDocumentRepository;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service
@Data
public class MedicalTestService {

    private final MedicalTestsDocumentRepository medicalTestsDocumentRepository;
    private final MedicalTestResultRepository medicalTestResultRepository;
    private final MedicalTestRepository medicalTestRepository;


    public void processAndSaveMedicalTests(MedicalTestsDocument medicalTestsDocument) {
        // Парсинг json_data для поиска ключевых слов
        String jsonData = medicalTestsDocument.getJsonData();
        // Здесь используй свой код для извлечения данных из json_data

        // Пример: Предположим, что у тебя есть метод extractMedicalTestResults(jsonData),
        // который возвращает Map<String, String> с ключами-кодами анализов и их значениями.

        Map<String, String> medicalTestResults = extractMedicalTestResults(jsonData);

        // Сохранение результатов в MedicalTestResult
        for (Map.Entry<String, String> entry : medicalTestResults.entrySet()) {
            String keyword = entry.getKey();
            String result = entry.getValue();

            // Поиск соответствующего анализа по ключевому слову
            MedicalTest medicalTest = medicalTestRepository.findByKeywordsContaining(keyword);

            if (medicalTest != null) {
                // Создание и сохранение записи результата анализа
                MedicalTestResult medicalTestResult = new MedicalTestResult();
                medicalTestResult.setMedicalTest(medicalTest);
                medicalTestResult.setResult(result);
                medicalTestResult.setMedicalTestsDocument(medicalTestsDocument);

                medicalTestResultRepository.save(medicalTestResult);
            }
        }
    }

    public Map<String, String> extractMedicalTestResults(String jsonData) {
        Map<String, String> medicalTestResults = new HashMap<>();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonData);

            Iterator<Map.Entry<String, JsonNode>> fields = jsonNode.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                String analysisCode = entry.getKey();
                String result = entry.getValue().asText();

                medicalTestResults.put(analysisCode, result);
            }

        } catch (IOException e) {
            e.printStackTrace(); // Обработай исключение в соответствии с твоими потребностями
        }

        return medicalTestResults;
    }

    // Другие методы сервиса, если необходимо
}

