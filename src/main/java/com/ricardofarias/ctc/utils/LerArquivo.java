package com.ricardofarias.ctc.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class LerArquivo {
    private static Map<String, Integer> mapAux = new HashMap<>();
    private static Map<String, Integer> mapFraseAtual = new HashMap<>();
    private static Map<String, Integer> mapFraseFinal = new HashMap<>();

     private static final String PATHBASEDADOS = "basedados-frases.txt";
     //private static final String PATHBASEDADOS = "tt.txt";
     //private static final String PATHBASEDADOS = "arquivoOrigem.txt";

    public static void lerArquivoBaseDeDados() {

        long linhasLidas = 0;
        String[] frasesPorLinha;
        String linha;
        long indexLinhaNoArquivo = 0;
        boolean linhasLidasForMaior1000 = false;

        File file = new File(PATHBASEDADOS);

        try (FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader (fr, 1024)) {

            br.skip(indexLinhaNoArquivo);

            while ((linha = br.readLine()) != null) {

                linhasLidas++;

                if(!linha.isBlank() && !linha.isEmpty()) {
                    frasesPorLinha = retornaArrayFrase(linha);

                    for (String frase : frasesPorLinha) {
                        mapAux.put(frase, mapAux.getOrDefault(frase, 0) + 1);
                    }
                }
                //Após ler 1500 linhas, equivalente
                if(linhasLidas >= 1500) {
                    linhasLidasForMaior1000 = true;
                    SalvaAsPrimeiras50kFrases();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(!linhasLidasForMaior1000){
            SalvaAsPrimeiras50kFrases();
        }

        SalvaArquivo.salvaArquivoFinal(mapFraseFinal);
    }

    private static void SalvaAsPrimeiras50kFrases(){

        mapAux.forEach((key, value) -> mapFraseAtual.merge(key, value, Integer::sum));
        mapAux.clear();

        mapFraseFinal = OrdenaFrase.ordenaPorQuantidadeFrase(mapFraseAtual);
        mapFraseAtual.clear();

        mapFraseAtual = mapFraseFinal;

    }

    private static String[] retornaArrayFrase(String linha) {
        return linha.split("\\|");
    }
}
