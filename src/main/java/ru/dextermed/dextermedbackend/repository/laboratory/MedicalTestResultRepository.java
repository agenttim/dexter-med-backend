package ru.dextermed.dextermedbackend.repository.laboratory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dextermed.dextermedbackend.entities.laboratory.MedicalTestResult;

@Repository
public interface MedicalTestResultRepository extends JpaRepository<MedicalTestResult, Long> {
    // Дополнительные методы для работы с MedicalTestResult, если необходимо
}

