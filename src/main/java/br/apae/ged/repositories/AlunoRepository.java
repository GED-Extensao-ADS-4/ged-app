package br.apae.ged.repositories;

import br.apae.ged.models.Alunos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository <Alunos, Long> {
    Alunos findByCpf(String cpf);
    Alunos findByRg(String rg);
}
