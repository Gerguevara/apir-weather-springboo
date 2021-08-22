package com.hackerrank.weather.service;

import java.util.List;
import java.util.Optional;

import com.hackerrank.weather.model.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hackerrank.weather.repository.WeatherRepository;


@Service
public class WeatherService {

	@Autowired
	WeatherRepository _weatherRepo;


	public List<Weather> findAll() {
		return this._weatherRepo.findAll();
	}

	public Weather save(Weather weather) {
		return this._weatherRepo.save(weather);
	}
	
	public Weather findeById(Integer id) {
		return this._weatherRepo.findById(id).orElse(null);
	}






}
