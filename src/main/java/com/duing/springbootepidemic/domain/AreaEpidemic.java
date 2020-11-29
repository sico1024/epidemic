package com.duing.springbootepidemic.domain;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;

@Data
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("area_epidemic")
public class AreaEpidemic implements Serializable {

    private Long id;
    private String name;
    private Integer nowConfirm;
    private Integer confirm;
    private Integer heal;
    private Integer dead;;
}
