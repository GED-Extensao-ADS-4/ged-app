package br.apae.ged.repositories;

import br.apae.ged.entitys.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}