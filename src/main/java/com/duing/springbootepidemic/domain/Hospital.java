package com.duing.springbootepidemic.domain;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Hospital {

    private String name;

    private Integer count;
}
