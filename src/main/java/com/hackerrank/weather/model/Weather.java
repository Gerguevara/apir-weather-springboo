package com.hackerrank.weather.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;


@Entity
@Table(name="weather")
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Getter
    @Setter
    private Date date;

    @Column(columnDefinition="Decimal(10,4)")
    @Getter
    @Setter
    private Float lat;

    @Column(columnDefinition="Decimal(10,4)")
    @Getter
    @Setter
    private Float lon;


    @ElementCollection
    @Size(max = 24)
    @Getter
    @Setter
    private List<Double> temperatures;

    @Getter
    @Setter
    private String city;

    @Getter
    @Setter
    private String state;


    public Weather(Integer id, Date date, Float lat, Float lon, String city, String state, List<Double> temperatures) {
        this.id = id;
        this.date = date;
        this.lat = lat;
        this.lon = lon;
        this.city = city;
        this.state = state;
        this.temperatures= temperatures;
    }

    public Weather(Date date, Float lat, Float lon, String city, String state, List<Double> temperatures) {
        this.date = date;
        this.lat = lat;
        this.lon = lon;
        this.city = city;
        this.state = state;
        this.temperatures= temperatures;
    }
    public Weather() {

    }


}
