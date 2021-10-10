package com.ricardofarias.ctc.main;

import com.ricardofarias.ctc.exceptions.ArquivoNaoEncontradoException;
import com.ricardofarias.ctc.utils.LerArquivo;

public class FrasesMaisFrequentesMain {

    public static void main(String[] args) {

        try {

            LerArquivo.lerArquivoBaseDeDados("arquivoOrigem.txt");

        }catch (ArquivoNaoEncontradoException err) {
            System.out.println(err.getMessage());
        }
    }
}
