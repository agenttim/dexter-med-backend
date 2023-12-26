package ru.dextermed.dextermedbackend.repository.laboratory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dextermed.dextermedbackend.entities.laboratory.MedicalTestResult;

import java.util.List;

@Repository
public interface MedicalTestResultRepository extends JpaRepository<MedicalTestResult, Long> {

    List<MedicalTestResult> findByMedicalTestsDocumentPatientId(Long patientId);

}

