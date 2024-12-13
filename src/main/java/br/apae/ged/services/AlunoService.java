package br.apae.ged.services;

import br.apae.ged.dto.aluno.AlunoRequestDTO;
import br.apae.ged.exceptions.NotFoundException;
import br.apae.ged.models.Alunos;
import br.apae.ged.models.Endereco;
import br.apae.ged.repositories.AlunoRepository;
import br.apae.ged.repositories.EnderecoRepository;
import br.apae.ged.repositories.specifications.AlunoSpecification;
import br.apae.ged.strategy.NewAlunoValidationStrategy;
import br.apae.ged.utils.AuthenticationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final EnderecoRepository enderecoRepository;
    private final List<NewAlunoValidationStrategy> alunoValidationStrategies;

    public Alunos create(AlunoRequestDTO request){

        alunoValidationStrategies.forEach(validation -> validation.validate(request));

        Alunos aluno = AlunoRequestDTO.alunoFromEntity(request);
        aluno.setCreatedBy(AuthenticationUtil.retriveAuthenticatedUser());
        aluno.setIsAtivo(true);
        alunoRepository.save(aluno);

        Endereco endereco = AlunoRequestDTO.enderecoFromEntity(request);
        endereco.setAluno(aluno);
        enderecoRepository.save(endereco);

        return aluno;
    }

    public Page<Alunos> findAll(String cpf, String cpfResponsavel, String nome, Pageable pageable){
        var spec = Specification
                .where(AlunoSpecification.isAtivo())
                .and(AlunoSpecification.byCpf(cpf))
                .and(AlunoSpecification.byCpfResponsavel(cpfResponsavel))
                .and(AlunoSpecification.byNome(nome));

        return alunoRepository.findAll(spec, pageable);
    }

    public Alunos byID(Long id){
        return alunoRepository.findById(id).orElseThrow(() -> new NotFoundException("Aluno não encontrado"));
    }

    public void changeStatusAluno(Long id){
        Alunos byId = alunoRepository.findById(id).orElseThrow(() -> new NotFoundException("Aluno não encontrado"));
        byId.setUpdatedAt(LocalDateTime.now());

        if (!byId.getIsAtivo()){
            byId.setIsAtivo(true);
            alunoRepository.save(byId);
            return;
        }

        byId.setIsAtivo(false);
        alunoRepository.save(byId);
    }


    public Alunos update(Long id, AlunoRequestDTO updated){

        Alunos byID = alunoRepository.findById(id).orElseThrow(() -> new NotFoundException("Aluno não encontrado"));

        byID.setNome(updated.nome());
        byID.setDataNascimento(updated.dataNascimento());
        byID.setSexo(updated.sexo());
        byID.setCpf(updated.cpf());
        byID.setTelefone(updated.telefone());
        byID.setCpfResponsavel(updated.cpfResponsavel());
        byID.setDeficiencia(updated.deficiencia());
        byID.setObservacoes(updated.observacoes());
        byID.setCreatedBy(byID.getCreatedBy());
        byID.setCreatedAt(byID.getCreatedAt());
        byID.setUpdatedBy(AuthenticationUtil.retriveAuthenticatedUser());
        byID.setUpdatedAt(LocalDateTime.now());

        Endereco endereco = enderecoRepository.findByAluno(byID);

        endereco.setEstado(updated.estado());
        endereco.setCidade(updated.cidade());
        endereco.setBairro(updated.bairro());
        endereco.setRua(updated.rua());
        endereco.setNumero(updated.numero());
        endereco.setCep(updated.cep());
        endereco.setAluno(byID);

        enderecoRepository.save(endereco);

        return alunoRepository.save(byID);
    }
}
