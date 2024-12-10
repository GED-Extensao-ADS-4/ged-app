package br.apae.ged.repositories.specifications;

import br.apae.ged.models.Document;
import org.springframework.data.jpa.domain.Specification;

public class DocumentSpecification {

    private DocumentSpecification(){
        throw new IllegalStateException("Utility Class");
    }

    public static Specification<Document> isLast(){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isLast"), true);
    }

    public static Specification<Document> dateDesc(){
        return (root, query, criteriaBuilder) -> {
            if (query == null){
                return criteriaBuilder.conjunction();
            }
            query.orderBy(criteriaBuilder.desc(root.get("dataDownload")));
            return query.getRestriction();
        };
    }

    public static Specification<Document> byTitulo(String titulo){
        return (root, query, criteriaBuilder) -> {
            if (titulo == null || titulo.isBlank() || titulo.isEmpty()){
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("titulo")), "%" + titulo.toLowerCase() + "%");
        };
    }

    public static Specification<Document> byAlunoId(Long id) {
        return (root, query, criteriaBuilder) -> {
            if (id == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("aluno").get("id"), id);
        };
    }

    public static Specification<Document> byAlunoNome(String nome) {
        return (root, query, criteriaBuilder) -> {
            if (nome == null || nome.isBlank() || nome.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("aluno").get("nome")),"%" + nome.toLowerCase() + "%");
        };
    }
}