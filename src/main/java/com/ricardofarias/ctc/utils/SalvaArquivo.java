package com.ricardofarias.ctc.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class SalvaArquivo {

    public static void salvaArquivoFinal(Map<String, Integer> mapFrase) {
        final String PATHARQUIVOFINAL = "arquivo-final.txt";
        int numeroDeLinhas = 0;
        File file = new File(PATHARQUIVOFINAL);

        try (FileWriter fw = new FileWriter(file, false);
             BufferedWriter bw = new BufferedWriter(fw)) {

            Set<Map.Entry<String, Integer>> set = mapFrase.entrySet();
            Iterator iterator = set.iterator();

            while (iterator.hasNext() &&  numeroDeLinhas <= 50000) {
                Map.Entry frase = (Map.Entry) iterator.next();
                bw.write(frase.getKey() + "|" + frase.getValue());
                bw.write(System.lineSeparator());
            }

            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
