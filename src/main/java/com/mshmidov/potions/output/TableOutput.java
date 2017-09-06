package com.mshmidov.potions.output;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;
import static org.apache.commons.lang3.StringUtils.rightPad;

public class TableOutput {

    private int minimalSpacing;

    public TableOutput(int minimalSpacing) {
        this.minimalSpacing = minimalSpacing;
    }

    public void print(List<List<String>> items) {

        final Map<Integer, Integer> columnWidth = new HashMap<>();

        items.forEach(row -> {
            for (int i = 0; i < row.size(); i++) {
                columnWidth.compute(i, (k,v) -> Math.max(v == null ? 0 :v, row.get(k).length() + minimalSpacing));
            }
        });

        items.forEach(row -> {
             final String line = IntStream.range(0, row.size())
                .mapToObj(i -> rightPad(row.get(i), columnWidth.getOrDefault(i, 1)))
                .collect(joining("","","%n"));

             System.out.printf(line);
        });

    }

}
