package ru.dextermed.dextermedbackend.repository;

import org.springframework.data.repository.CrudRepository;
import ru.dextermed.dextermedbackend.entities.MedicalDocument;

import java.util.List;

public interface MedicalDocumentRepository extends CrudRepository<MedicalDocument, Long> {

    List<MedicalDocument> findByUserId(Long id);
}
