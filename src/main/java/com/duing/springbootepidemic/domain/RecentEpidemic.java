package com.duing.springbootepidemic.domain;


import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RecentEpidemic {

    private String city;

    private String province;

    private Integer confirmAdd;

    private Integer nowConfirm;

    private String grade;
}
