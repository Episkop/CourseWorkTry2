package ua.studert.coursework.Entity;

import lombok.Getter;
import lombok.Setter;
import ua.studert.coursework.Model.ProfitModel;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "profit")
public class ProfitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name ="article",nullable = false)
    private String article;
    private Double january;
    private Double february;
    private Double march;
    private Double april;
    private Double may;
    private Double june;
    private Double july;
    private Double august;
    private Double september;
    private Double october;
    private Double november;
    private Double december;
    private Double sum;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "user_id")
    private UserEntity user;


    public ProfitEntity(String article, Double january, Double february, Double march, Double april, Double may,
                        Double june, Double july, Double august, Double september, Double october, Double november,
                        Double december, Double sum) {
        this.article = article;
        this.january = january;
        this.february = february;
        this.march = march;
        this.april = april;
        this.may = may;
        this.june = june;
        this.july = july;
        this.august = august;
        this.september = september;
        this.october = october;
        this.november = november;
        this.december = december;
        this.sum = sum;

    }

    public ProfitEntity() {
    }

    public static ProfitEntity of(String article, Double january, Double february, Double march, Double april, Double may,
                                          Double june, Double july, Double august, Double september, Double october, Double november,
                                          Double december, Double sum){
        return new ProfitEntity(article, january, february, march, april, may, june, july, august,
                september, october, november, december,sum);
    }

    public ProfitModel toModel() {
        return ProfitModel.of(id,article, january, february, march, april, may, june, july, august,
                september, october, november, december,sum);
    }

    public static ProfitEntity fromModel (ProfitModel model){
        return ProfitEntity.of(model.getArticle(),model.getJanuary(),model.getFebruary(),model.getMarch(),model.getApril(),
                model.getMay(), model.getJune(),model.getJuly(), model.getAugust(), model.getSeptember(), model.getOctober(),
                model.getNovember(), model.getDecember(), model.getSum());
    }

}
