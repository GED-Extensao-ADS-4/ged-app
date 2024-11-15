package br.apae.ged.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryAluno extends JpaRepository <Aluno, Long> {
    Aluno findByCpf(String cpf);

    Aluno findByNome(String nome);

    Aluno findByStatus(boolean is_ativo);
}
