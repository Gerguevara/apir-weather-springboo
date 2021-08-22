package com.hackerrank.weather.service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import com.hackerrank.weather.model.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hackerrank.weather.repository.WeatherRepository;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;


@Service
public class WeatherService {

	@Autowired
	WeatherRepository _weatherRepo;

	public Weather save(Weather weather) {
		return this._weatherRepo.save(weather);
	}
	
	public Weather findeById(Integer id) {
		return this._weatherRepo.findById(id).orElse(null);
	}


	public List<Weather> findAll(String order, List<String>  city, Date date  ) {


		List<Weather> weather  = this._weatherRepo.findAll((Specification<Weather>)(root, cq, cb) -> {
			Predicate p = cb.conjunction();

			if(!(city == null)){
				System.out.println("entra en el arreglo");

	//			for (String name : city) {
	//				p = cb.and(p, cb.equal(root.get("city"), name));
	//			}


			}

			/*if (!StringUtils.isEmpty(city)) {
			System.out.println("ciudad:" + name);
				p = cb.and(p, cb.equal(root.get("city"), city ));
			}*/

			if (Objects.nonNull(date)) {
				p = cb.and(p, cb.equal(root.get("date"), date ));
			}


			if (!StringUtils.isEmpty(order)) {
				if (!StringUtils.isEmpty(order)) {
					switch(order) {
						case "date":
							cq.orderBy(cb.asc(root.get("date")));
							break;
						case "-date":
							cq.orderBy(cb.desc(root.get("date")));
							break;
						default:
							cq.orderBy(cb.asc(root.get("id")));
					}
				}
			}



			return p;
		});

		return weather;

	}


}
