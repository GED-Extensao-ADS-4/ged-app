package br.apae.ged.services;

import br.apae.ged.dto.AlunoDto;
import br.apae.ged.exceptions.AlunoNaoEncontradoException;
import br.apae.ged.models.Alunos;
import br.apae.ged.repositories.AlunoRepository;
import br.apae.ged.repositories.specifications.AlunoSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlunoRepository alunoRepository;

    public AlunoDto salvar(AlunoDto alunoDto) {
        validarCpfEouRgDuplicado(alunoDto.getCpf(), alunoDto.getRg());

        Alunos aluno = modelMapper.map(alunoDto, Alunos.class);
        aluno = alunoRepository.save(aluno);

        return modelMapper.map(aluno, AlunoDto.class);
    }

    public Page<AlunoDto> listarAlunos(String nome, String cpf, String rg, String responsavel, Pageable pageable) {
        var spec = AlunoSpecification.isAtivo()
                .and(AlunoSpecification.byResponsavelLegal(responsavel))
                .and(AlunoSpecification.byRg(rg))
                .and(AlunoSpecification.byCpf(cpf))
                .and(AlunoSpecification.byNome(nome));

        return alunoRepository.findAll(spec, pageable)
                .map(aluno -> modelMapper.map(aluno, AlunoDto.class));
    }

    public AlunoDto buscarAlunoPorId(Long id) {
        Alunos aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new AlunoNaoEncontradoException(id));

        return modelMapper.map(aluno, AlunoDto.class);
    }

    public AlunoDto atualizarAluno(Long id, AlunoDto alunoDto) {
        Alunos alunoExistente = alunoRepository.findById(id)
                .orElseThrow(() -> new AlunoNaoEncontradoException(id));

        validarCpfEouRgDuplicado(alunoDto.getCpf(), alunoDto.getRg());

        modelMapper.map(alunoDto, alunoExistente);
        alunoExistente = alunoRepository.save(alunoExistente);

        return modelMapper.map(alunoExistente, AlunoDto.class);
    }

    public void deletarAluno(Long id) {
        if (!alunoRepository.existsById(id)) {
            throw new AlunoNaoEncontradoException(id);
        }
        alunoRepository.deleteById(id);
    }

    private void validarCpfEouRgDuplicado(String cpf, String rg) {
        if (cpf != null && alunoRepository.findByCpf(cpf) != null) {
            throw new RuntimeException("CPF já cadastrado");
        }
        if (rg != null && alunoRepository.findByRg(rg) != null) {
            throw new RuntimeException("RG já cadastrado");
        }
    }
}
