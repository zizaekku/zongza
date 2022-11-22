package zizeaku.zongza.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class Seed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(nullable = false, length=30, name="name")
    private String name;

    @Column(nullable = false, length=200, name="scientific_name")
    private String scientificName;

    @Column(nullable = false, length=30, name="intro_num")
    private String introNum;
    
    @ManyToOne
    @JoinColumn(name="generic_id")
    private Generic generic;
    
    @ManyToOne
    @JoinColumn(name="family_id")
    private Family family;
    
    @Column(nullable = false, name="place")
    private String place;

    @Column(nullable = false, name="length")
    private Double length;

    @Column(nullable = false, name="width")
    private Double width;

    @Column(nullable = false, name="note")
    private String note;

}