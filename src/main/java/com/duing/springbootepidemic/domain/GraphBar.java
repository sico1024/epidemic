package com.duing.springbootepidemic.domain;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data@AllArgsConstructor
public class GraphBar implements Comparable<GraphBar>{

    private String name;

    private Integer confirm;

    @Override
    public int compareTo(GraphBar o) {
        return o.getConfirm()-this.getConfirm();
    }
}
