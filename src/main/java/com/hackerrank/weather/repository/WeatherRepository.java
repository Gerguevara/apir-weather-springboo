package com.hackerrank.weather.repository;
import com.hackerrank.weather.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;


public interface WeatherRepository extends JpaRepository<Weather, Integer> {

    @Query("select w from Weather w where w.date=?1")
    public Weather findByWeatherWithQueryExample(Date date);





}
