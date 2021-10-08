package com.ricardofarias.ctc.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class LerArquivo {
    private static Map<String, Integer> mapAux = new HashMap<>();
    private static Map<String, Integer> mapFraseAtual = new HashMap<>();
    private static Map<String, Integer> mapFraseFinal = new HashMap<>();

    private static final String PATHBASEDADOS = "basedados-frases.txt";

    public static void lerArquivoBaseDeDados() {

        long indexLinhaAtual = 0;
        long linhasLidas = 0;
        List<String> frasesPorLinha = null;
        String linha;
        long indexLinhaNoArquivo = 0;
        boolean linhasLidasForMaior1000 = false;

        File file = new File(PATHBASEDADOS);

        try (FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader (fr, 1024)) {

            br.skip(indexLinhaNoArquivo);

            while ((linha = br.readLine()) != null) {
                //TODO soma 2 pois é os caractere /n de fim de linha.
                indexLinhaAtual += linha.length() + 2;
                linhasLidas++;

                if(!linha.isBlank() && !linha.isEmpty()) {
                    frasesPorLinha = retornaArrayFrase(linha);

                    for (int i=0; i < frasesPorLinha.size(); i++){
                        mapAux.put(frasesPorLinha.get(i), mapAux.getOrDefault(frasesPorLinha.get(i), 0) + 1);
                    }
                }

                if(linhasLidas >= 10) {
                    linhasLidasForMaior1000 = true;
                    SalvaAsPrimeiras80kFrases();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //TODO se não entra no if de linhas
        if(!linhasLidasForMaior1000)
        SalvaAsPrimeiras80kFrases();
        imprime(mapFraseFinal);
        SalvaArquivo.salvaArquivoFinal(mapFraseFinal);

    }

    private static void SalvaAsPrimeiras80kFrases(){
        mapAux.forEach((key, value) -> mapFraseAtual.merge(key, value, (v1, v2) -> v1+v2));
        mapAux.clear();

        mapFraseFinal = OrdenaFrase.ordenaPorQuantidadeFrase(mapFraseAtual);
    }

    //TODO temporário
    private static void imprime(Map<String, Integer> mapFrase) {
        for (Map.Entry<String,Integer> frase : mapFrase.entrySet()) {
            System.out.println(frase.getKey() +" " + frase.getValue());
        }
    }

    private static List<String> retornaArrayFrase(String linha) {
        return Arrays.stream(linha.split("\\|")).collect(Collectors.toList());
    }
}
