package com.hackerrank.weather.controller;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.hackerrank.weather.model.Weather;
import com.hackerrank.weather.service.WeatherService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WeatherApiRestController {


	@Autowired
	WeatherService weatherService;



	@GetMapping("/weather")
	public @ResponseBody List<Weather> weatherGetALL(
			@RequestParam(required = false) String order,
			@RequestParam(required = false) Date date
	) {

		System.out.println(date);




		switch(order) {
			case "asc":
				return this.weatherService.findAll();
			case "desc":
				return this.weatherService.findAll();
			default:
				return this.weatherService.findAll();
		}


	}


	
	@PostMapping("/weather")
	public ResponseEntity<?> weather(@RequestBody Weather weatherRequest) {

		Weather weather = null;
		Map<String, Object> response = new HashMap<>();

		try {
			weather = this.weatherService.save(weatherRequest);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el guardado en la base de datos");
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		// mensaje de exito
		response.put("message", "created");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}



    @GetMapping("/weather/{id}")
    public ResponseEntity<?> weatherById(@PathVariable("id") Integer id){

		Weather weather = null;
		Map<String, Object> response = new HashMap<>();

		try {
			weather = this.weatherService.findeById(id);
		} catch (DataAccessException e) {
			response.put("messages", "there was an error");
			response.put("error", e.getMostSpecificCause());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (weather== null) {
			response.put("messages", "there is no such record");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Weather>(weather, HttpStatus.OK);
    }
	
}
