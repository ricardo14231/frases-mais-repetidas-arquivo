package com.ricardofarias.ctc.main;

import com.ricardofarias.ctc.utils.LerArquivo;
import com.ricardofarias.ctc.utils.OrdenaFrase;
import com.ricardofarias.ctc.utils.SalvaArquivo;

import java.util.HashMap;
import java.util.Map;

public class FrasesMaisFrequentesMain {

    private static Map<String, Integer> mapAux = new HashMap<>();

    private static Map<String, Integer> mapFraseAtual = new HashMap<>();
    private static Map<String, Integer> mapFraseFinal = new HashMap<>();

    public static void main(String[] args) {
            long index = 0;

        long valorAnt = 0;
        long valorAtual = -1;
int j = 0;
        while (j != 7){
            j++;
            index = LerArquivo.lerArquivoBaseDeDados(mapAux, index);
            mapAux.forEach((key, value) -> mapFraseAtual.merge(key, value, (v1, v2) -> v1+v2));
            mapAux.clear();

            mapFraseFinal = OrdenaFrase.ordenaPorQuantidadeFrase(mapFraseAtual);
            //System.out.println(valorAnt+ "   " + valorAtual + "   " + index);

            //valorAtual = index;
            if(valorAnt == valorAtual) {
                //System.out.println("Foi" + " index: " + index);
             //   break;
            }
            valorAnt = valorAtual;
        }
        //SalvaArquivo.salvaArquivoFinal(mapFraseFinal);
        imprime(mapFraseFinal);



        /*
        //IMPRIME CADA FRASE
        imprime(mapFraseAtual);
        System.out.println("___________________________________");
*/
        //mapParte2ArquivoSegemento.values().stream().forEach(System.out::println);

        //frasesMaisFrequentes = OrdenaFrase.ordenaPorQuantidadeFrase(frasesMaisFrequentes);


        //SalvaArquivo.salvaArquivoFrases(frasesMaisFrequentes, "fraseResultado.txt");
        //IMPRIME A QUANTIDADE DE CADA FRASE



        //TODO chamar só uma vez. Gera o arquivo com as frases.
        //GeraArquivoTeste.geraArquivo();
    }
/*
    //TODO finalizar
    private static boolean verificaIndexArray1ComArray2(Map<String, Integer> map1, Map<String, Integer> map2) {

        int ultimoIndex = map2.size() - 1;
        //System.out.println(ultimoIndex);
        //TODO acessar o ultimo elemento para não precisar passar pela ordenação.
        if(map1.values().stream().min((o1, o2) -> o2).get() > map2.values().stream().max((o1, o2) -> o1).get()){
            //return true;

        }
        System.out.println(map1.values().stream().min((o1, o2) -> o2).get());
        System.out.println(map2.values().stream().max((o1, o2) -> o1).get());
        System.out.println(map2.get(0));
        return false;
    }

    private static void substituiMaiorValorArrayComArray2(Map<String, Integer> map1, Map<String, Integer> map2) {
        for (Map.Entry<String,Integer> frase : map1.entrySet()) {
            //frase.getValue() < map2.
        }
    }
*/

    //TODO temporário
    private static void imprime(Map<String, Integer> mapFrase) {
        for (Map.Entry<String,Integer> frase : mapFrase.entrySet()) {
            System.out.println(frase.getKey() +" " + frase.getValue());
        }
    }
}
