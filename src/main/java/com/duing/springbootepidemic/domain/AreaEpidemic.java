package com.duing.springbootepidemic.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AreaEpidemic {
    private String name;
    private int nowConfirm;
    private int confirm;
    private int heal;
    private int dead;;
}
