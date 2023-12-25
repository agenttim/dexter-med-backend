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
import java.util.*;

@Service
@Data
public class MedicalTestService {

    private final MedicalTestsDocumentRepository medicalTestsDocumentRepository;
    private final MedicalTestResultRepository medicalTestResultRepository;
    private final MedicalTestRepository medicalTestRepository;


    public void processAndSaveMedicalTests(MedicalTestsDocument medicalTestsDocument, Long medicalTestId) {

        MedicalTest medicalTest = medicalTestRepository.findById(medicalTestId)
                .orElseThrow(() -> new IllegalArgumentException("Анализ не найден!"));

        String jsonData = medicalTestsDocument.getJsonData();

        Map<String, String> results = extractMedicalTestResults(jsonData);

        // Получаем ключевые слова в виде строки
        String keywords = medicalTest.getKeywords();

        for (Map.Entry<String, String> entry : results.entrySet()) {

            String keyword = entry.getKey();
            String result = entry.getValue();

            System.out.println("Сравниваем ключевые слова: " + keywords);
            System.out.println("с ключевым словом: " + keyword);

            boolean match = false;

            for (String part : keyword.split("_")) {

                System.out.println("Проверяем часть: " + part);

                // Сравниваем каждое ключевое слово
                for (String key : keywords.split(", ")) {

                    System.out.println("Сравниваем: " + key + " и " + part);

                    if (key.equalsIgnoreCase(part) || part.contains(key)) {
                        match = true;
                        System.out.println("Найдено совпадение!");
                        break;
                    }

                }

                if (match) {
                    break;
                }

            }

            if (!match) {
                System.out.println("Совпадений не найдено");
            }

            if (match) {
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

            Iterator<Map.Entry<String, JsonNode>> tests = jsonNode.fields();
            while (tests.hasNext()) {
                Map.Entry<String, JsonNode> test = tests.next();
                String testName = test.getKey();
                JsonNode indicatorsNode = test.getValue();

                Iterator<JsonNode> indicators = indicatorsNode.elements();
                while (indicators.hasNext()) {
                    JsonNode indicator = indicators.next();
                    String indicatorName = indicator.get("indicator").asText();
                    String value = indicator.get("value").asText();

                    String key = testName + "_" + indicatorName;
                    medicalTestResults.put(key, value);
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // Обработай исключение в соответствии с твоими потребностями
        }

        return medicalTestResults;
    }


    // Другие методы сервиса, если необходимо
}

