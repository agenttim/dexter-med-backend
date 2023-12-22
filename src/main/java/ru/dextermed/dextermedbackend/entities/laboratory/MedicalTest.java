package ru.dextermed.dextermedbackend.entities.laboratory;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "medical_test")
public class MedicalTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ElementCollection
    @CollectionTable(name = "medical_test", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "keywords")
    private Set<String> keywords;

    // Далее следует ваш код
}
