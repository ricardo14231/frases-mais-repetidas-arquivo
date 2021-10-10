package com.ricardofarias.ctc.exceptions;

public class ArquivoNaoEncontradoException extends java.io.FileNotFoundException {

    public ArquivoNaoEncontradoException(String nomeArquivo) {
        super(String.format("Arquivo %s não encontrado! Por favor, verique o nome e diretório do arquivo.", nomeArquivo));
    }
}
