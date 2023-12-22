package ru.dextermed.dextermedbackend.repository.laboratory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dextermed.dextermedbackend.entities.laboratory.MedicalTest;

@Repository
public interface MedicalTestRepository extends JpaRepository<MedicalTest, Long> {

    // Дополнительные методы для работы с MedicalTest, если необходимо
    // Например, метод для поиска анализа по ключевым словам
    MedicalTest findByKeywordsContaining(String keyword);
}

