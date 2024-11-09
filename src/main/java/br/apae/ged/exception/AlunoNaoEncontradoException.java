package br.apae.ged.exception;

public class AlunoNaoEncontradoException extends RuntimeException {
    public AlunoNaoEncontradoException(Long id) {
        super("Aluno com ID " + id + " não foi encontrado.");
    }
}