package com.duing.springbootepidemic.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class ChinaTotalGraph {


    private String date;

    private Integer confirm;

    private Integer dead;

    private Integer heal;


}
