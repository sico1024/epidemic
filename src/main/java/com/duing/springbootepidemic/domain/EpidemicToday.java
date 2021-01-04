package com.duing.springbootepidemic.domain;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EpidemicToday {

    private String lastUpdateTime;

    //现有确诊
    private Integer nowConfirm;
    private String compareNowConfirm;

    //无症状感染者
    private Integer noInfect;
    private String compareNoInfect;

    //境外输入
    private Integer importedCase;
    private String compareImportedCase;

    //累计确证
    private Integer confirm;
    private String compareConfirm;

    //累计治愈
    private Integer heal;
    private String compareHeal;

    //累计死亡
    private Integer dead;
    private String compareDead;

}
