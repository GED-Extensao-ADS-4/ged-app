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
            if (nome == null || nome.isBlank() || nome.isEmpty()){
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
        };
    }

    public static Specification<Alunos> byCpf(String cpf) {
        return (root, query, criteriaBuilder) -> {
            if (cpf == null || cpf.isBlank() || cpf.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("cpf"), "%" + cpf + "%");
        };
    }

    public static Specification<Alunos> byRg(String rg) {
        return (root, query, criteriaBuilder) -> {
            if (rg == null || rg.isBlank() || rg.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("cpf"), "%" + rg + "%");
        };
    }

    public static Specification<Alunos> byResponsavelLegal(String responsavel) {
        return (root, query, criteriaBuilder) -> {
            if (responsavel == null || responsavel.isBlank() || responsavel.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("cpfResponsavel"), "%" + responsavel + "%");
        };
    }
}
