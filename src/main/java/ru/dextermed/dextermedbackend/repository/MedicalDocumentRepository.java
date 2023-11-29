package ru.dextermed.dextermedbackend.repository;

import org.springframework.data.repository.CrudRepository;
import ru.dextermed.dextermedbackend.model.MedicalDocument;

public interface MedicalDocumentRepository extends CrudRepository<MedicalDocument, Long> {

}
