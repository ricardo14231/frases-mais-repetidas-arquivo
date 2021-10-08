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

    private static final String PATHBASEDADOS = "arquivoOrigem.txt";

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
                //TODO soma 2 pois é os caractere /n de fim de linha.
                linhasLidas++;

                if(!linha.isBlank() && !linha.isEmpty()) {
                    frasesPorLinha = retornaArrayFrase(linha);

                    for (String frase : frasesPorLinha) {
                        mapAux.put(frase, mapAux.getOrDefault(frase, 0) + 1);
                    }
                }
                //TODO colocar 2000
                if(linhasLidas >= 5000) {
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

        SalvaArquivo.salvaArquivoFinal(OrdenaFrase.ordenaPorQuantidadeFrase(mapFraseFinal));
    }

    private static void SalvaAsPrimeiras50kFrases(){

        mapAux.forEach((key, value) -> mapFraseAtual.merge(key, value, Integer::sum));
        mapAux.clear();
        mapFraseFinal = mapFraseAtual;
    }

    private static String[] retornaArrayFrase(String linha) {
        return linha.split("\\|");
    }
}
