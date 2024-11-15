package br.apae.ged.exceptions;

public class AlunoNaoEncontradoException extends RuntimeException {
    public AlunoNaoEncontradoException(Long id) {
        super("Aluno com ID " + id + " não foi encontrado.");
    }
}