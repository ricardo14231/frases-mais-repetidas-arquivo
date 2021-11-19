package com.ricardofarias.ctc.utils;

import com.ricardofarias.ctc.exceptions.ArquivoNaoEncontradoException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/*
        O algoritmo itera nas 80 mil frases iniciais e depois ordena as primeiras 50 mil palavras.
        Depois de ordenar, cópia para o map que está iterando sobre o arquivo.
        É armazenado a quantidade de caracteres que foram lidos no arquivo
        para voltar no mesmo ponto durantes as iterações.
 */

public class LerArquivo {
    private static Map<String, Integer> mapFrasesAtual = new HashMap<>();
    private static Map<String, Integer> mapFrasesFinal = new HashMap<>();

    public static void lerArquivoBaseDeDados(final String PATHBASEDADOS) throws ArquivoNaoEncontradoException {

        String[] frasesPorLinha;
        String linha;
        long indexLinhaNoArquivo = 0;

        File file = new File(PATHBASEDADOS);

        if(!file.exists())
            throw new ArquivoNaoEncontradoException(PATHBASEDADOS);

        try (FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader (fr, 1024)) {

            br.skip(indexLinhaNoArquivo);

            while ((linha = br.readLine()) != null) {

                if(!linha.isBlank() && !linha.isEmpty()) {
                    frasesPorLinha = retornaArrayFrase(linha);

                    for (String frase : frasesPorLinha) {
                        mapFrasesAtual.put(frase, mapFrasesAtual.getOrDefault(frase, 0) + 1);
                    }
                }
                //Após ler 80000 frases chama o método para ordernar as 50 mil frases mais repetidas.
                if(mapFrasesAtual.size() >= 80000) {
                    SalvaAsPrimeiras50kFrases();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Ordena a ultma parte do Map ou caso o arquivo tenha menos que 80k de frases.
        SalvaAsPrimeiras50kFrases();


        SalvaArquivo.salvaArquivoFinal(mapFrasesFinal);
    }

    private static void SalvaAsPrimeiras50kFrases(){

        mapFrasesFinal = OrdenaFrase.ordenaPorQuantidadeFrase(mapFrasesAtual);
        mapFrasesAtual.clear();

        //Faz uma cópis das 50k para iterar com o restante das frases do arquivo.
        mapFrasesAtual.putAll(mapFrasesFinal);

    }

    private static String[] retornaArrayFrase(String linha) {
        return linha.split("\\|");
    }
}
