package br.apae.ged.repositories.specifications;

import br.apae.ged.entitys.Document;
import org.springframework.data.jpa.domain.Specification;

public class DocumentSpecification {

    private DocumentSpecification(){
        throw new IllegalStateException("Utility Class");
    }

    public static Specification<Document> hasNome(String nome) {
        return (root, query, criteriaBuilder) -> {
            if (nome == null || nome.isEmpty() || nome.isBlank()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
        };
    }
}
