package com.duing.springbootepidemic.domain;


import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Foreign {


    private String name;

    private Integer confirmAdd;

    private Integer confirm;

    private Integer heal;

    private Integer dead;
}
