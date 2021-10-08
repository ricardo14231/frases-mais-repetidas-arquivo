package com.ricardofarias.ctc.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LerArquivo {
    static final String PATHBASEDADOS = "tt.txt";

    public static long lerArquivoBaseDeDados(Map<String, Integer> mapFrases, long indexLinhaNoArquivo) {

        long indexLinhaAtual = 0;
        long linhasLidas = 0;
        List<String> frasesPorLinha = null;
        String linha;

        File file = new File(PATHBASEDADOS);

        try (FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader (fr, 1024)) {

            br.skip(indexLinhaNoArquivo);
            //System.out.println(br.readLine());
           // System.out.println(br.read());
            while ((linha = br.readLine()) != null) {
                //TODO soma 2 pois é os caractere /n de fim de linha.
                indexLinhaAtual += linha.length() + 2;
                linhasLidas++;

                if(!linha.isBlank() && !linha.isEmpty()) {
                    frasesPorLinha = retornaArrayFrase(linha);

                    for (int i=0; i < frasesPorLinha.size(); i++){
                        mapFrases.put(frasesPorLinha.get(i), mapFrases.getOrDefault(frasesPorLinha.get(i), 0) + 1);
                    }
                    //frasesPorLinha.clear();
                }
                System.out.println("main");

                if(linhasLidas >= 1) {
                    //linhasLidas = 0;
                    //break;
                    test();
                }
                //System.out.println(linha);
            }
           // System.out.println(frasesPorLinha.size());
           // if(frasesPorLinha.size() == 0) return -1;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return indexLinhaAtual;
    }
    private static void test(){
        int i = 0;
        while (i < 1000000) i++;
        System.out.println("TESTE");
    }
    private static List<String> retornaArrayFrase(String linha) {
        return Arrays.stream(linha.split("\\|")).collect(Collectors.toList());
    }
}
