package com.duing.springbootepidemic.domain;


import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AreaHospital {

    private String city;

    private List<Hospital> town;


}
