package com.duing.springbootepidemic.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Development {

    private String title;

    private String desc;

    private String publishTime;

    private String url;


}
