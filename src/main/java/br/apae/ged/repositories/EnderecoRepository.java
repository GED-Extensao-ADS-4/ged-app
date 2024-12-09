package br.apae.ged.repositories;

import br.apae.ged.models.Alunos;
import br.apae.ged.models.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    Endereco findByAluno(Alunos aluno);
}
