package br.apae.ged.repositories.specifications;

import br.apae.ged.models.Alunos;
import org.springframework.data.jpa.domain.Specification;

public class AlunoSpecification {

    private AlunoSpecification(){
        throw new IllegalStateException("Utility class");
    }

    public static Specification<Alunos> isAtivo(){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isAtivo"), true);
    }

    public static Specification<Alunos> byNome(String nome) {
        return (root, query, criteriaBuilder) -> {
            if (nome == null || nome.isBlank()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");
        };
    }

    public static Specification<Alunos> byCpf(String cpf) {
        return (root, query, criteriaBuilder) -> {
            if (cpf == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("cpf"), cpf);
        };
    }

    public static Specification<Alunos> byCpfResponsavel(String cpf) {
        return (root, query, criteriaBuilder) -> {
            if (cpf == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("cpfResponsavel"), cpf);
        };
    }
}
