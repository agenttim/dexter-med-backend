package ru.dextermed.dextermedbackend.service.laboratory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.dextermed.dextermedbackend.entities.laboratory.MedicalTest;
import ru.dextermed.dextermedbackend.entities.laboratory.MedicalTestResult;
import ru.dextermed.dextermedbackend.entities.laboratory.MedicalTestsDocument;
import ru.dextermed.dextermedbackend.repository.laboratory.MedicalTestRepository;
import ru.dextermed.dextermedbackend.repository.laboratory.MedicalTestResultRepository;
import ru.dextermed.dextermedbackend.repository.laboratory.MedicalTestsDocumentRepository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

class MedicalTestServiceTest {

    @Mock
    private MedicalTestsDocumentRepository medicalTestsDocumentRepository;

    @Mock
    private MedicalTestResultRepository medicalTestResultRepository;

    @Mock
    private MedicalTestRepository medicalTestRepository;

    @InjectMocks
    private MedicalTestService medicalTestService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProcessAndSaveMedicalTests() {
        // Здесь ты можешь написать тест для метода processAndSaveMedicalTests.
        // Используй Mockito для мокирования зависимостей и проверки вызовов методов.
        // Пример:
        MedicalTestsDocument medicalTestsDocument = new MedicalTestsDocument();
        medicalTestsDocument.setJsonData("{\"test1\": \"result1\", \"test2\": \"result2\"}");

        MedicalTest medicalTest1 = new MedicalTest();
        medicalTest1.setKeywords(Collections.singleton("test1"));
        MedicalTest medicalTest2 = new MedicalTest();
        medicalTest2.setKeywords(Collections.singleton("test2"));

        when(medicalTestRepository.findByKeywordsContaining("test1")).thenReturn(medicalTest1);
        when(medicalTestRepository.findByKeywordsContaining("test2")).thenReturn(medicalTest2);

        medicalTestService.processAndSaveMedicalTests(medicalTestsDocument);

        verify(medicalTestResultRepository, times(2)).save(any(MedicalTestResult.class));
    }

    // Добавь другие тесты по необходимости
}


