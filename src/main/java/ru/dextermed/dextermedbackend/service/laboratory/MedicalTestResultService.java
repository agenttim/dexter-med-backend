package ru.dextermed.dextermedbackend.service.laboratory;

import lombok.Data;
import org.springframework.stereotype.Service;
import ru.dextermed.dextermedbackend.entities.laboratory.MedicalTestResult;
import ru.dextermed.dextermedbackend.repository.laboratory.MedicalTestResultRepository;

import java.util.List;

@Service
@Data
public class MedicalTestResultService {
    private final MedicalTestResultRepository medicalTestResultRepository;

    public List<MedicalTestResult> getUserTestResults(Long userId) {
        return medicalTestResultRepository.findByMedicalTestsDocumentPatientId(userId);
    }
}
