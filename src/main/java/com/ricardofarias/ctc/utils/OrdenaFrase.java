package com.ricardofarias.ctc.utils;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class OrdenaFrase {

    public static Map<String, Integer> ordenaPorQuantidadeFrase(Map<String, Integer> map) {
        ComparatorFrase comparator = new ComparatorFrase(map);
        Map<String, Integer> resultado = new TreeMap<>(comparator);
        resultado.putAll(map);
        return resultado;
    }

}

class ComparatorFrase implements Comparator<Object> {
    Map<String, Integer> map;
    public ComparatorFrase(Map<String, Integer> map) {
        this.map = map;
    }
    public int compare(Object o1, Object o2) {
        if (map.get(o1) == map.get(o2))
            return 1;
        else
            return (map.get(o2)).compareTo(map.get(o1));
    }
}
