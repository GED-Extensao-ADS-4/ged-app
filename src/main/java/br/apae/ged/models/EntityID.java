package br.apae.ged.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class EntityID {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
