package com.ricardofarias.ctc.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GeraArquivoTeste {
    private static Random gerador = new Random();

    private static String[] frases = {"Você quer passar o resto da sua vida vendendo água com açúcar ou quer uma chance de mudar o mundo?|",
            "Estou fazendo um sistema operacional gratuito (apenas um hobby, não será grande e profissional como GNU) para 386/486 AT.|",
            "A melhor maneira de prever o futuro é inventá-lo.|", "Acho que vírus de computador deve contar como vida. Creio que dizem " +
            "algo sobre a natureza humana que a única forma de vida que criamos até agora é puramente destrutiva. Nós criamos " +
            "vida à nossa própria imagem.|","Algumas coisas o homem nunca deverá saber. Para todas as outras, há o Google.|",
            "Programadores são ferramentas para converter cafeína em código.|", "Hardware é a parte de um computador que você chuta; Software é a parte que você xinga.|",
            "Uma imagem vale mais do que mil palavras, mas ocupa 3 mil vezes mais espaço em disco.|"};


    public static void geraArquivo() {
        File file = new File("arquivoOrigem.txt");

        try (FileWriter fw = new FileWriter(file, false);
             BufferedWriter bw = new BufferedWriter(fw)) {
            for(int i=0; i < 10000000; i++) {

                for (int j=0; j < frases.length; j++) {
                    bw.write(frases[gerador.nextInt(6)] + System.currentTimeMillis());
                }
                bw.write(System.lineSeparator());
                bw.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
