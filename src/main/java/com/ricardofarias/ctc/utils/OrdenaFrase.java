package com.ricardofarias.ctc.utils;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

public class OrdenaFrase {

    public static Map<String, Integer> ordenaPorQuantidadeFrase(Map<String, Integer> map) {

        List<Entry<String, Integer>> list = new LinkedList<>(map.entrySet());

        Collections.sort(list, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        return list.stream()
                .limit(50000)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (v1, v2) -> v1, LinkedHashMap::new));

    }
}
