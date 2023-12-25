package ru.dextermed.dextermedbackend.repository.laboratory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.dextermed.dextermedbackend.entities.laboratory.MedicalTestsDocument;

@Repository
public interface MedicalTestsDocumentRepository extends JpaRepository<MedicalTestsDocument, Long> {

    @Query("SELECT m FROM MedicalTestsDocument m WHERE m.id = :id")
    MedicalTestsDocument findByIdCustom(Long id);
    // Дополнительные методы для работы с MedicalTestsDocument, если необходимо
}

