package zizeaku.zongza.controller;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeedForm {
    private String name;
    private String scientificName;
    private String introNum;
    private String generic;
    private String family;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String place;
    private Double length;
    private Double width;
    private String note;
}
