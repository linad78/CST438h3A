package CST438h3A.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import CST438h3A.domain.*;
import CST438h3A.service.CityService;
import CST438h3A.service.WeatherService;
import CST438h3A.domain.CityInfo;
import CST438h3A.domain.TempAndTime;

@RestController
public class CityRestController {
	
	@Autowired
	private CityService cityService;
	
	@Autowired
	private CST438h3A.service.WeatherService weatherService;
	
	@GetMapping("/api/cities/{city}")
	public ResponseEntity<CityInfo> getWeather(@PathVariable("city") String cityName) {
		CityInfo city = cityService.getCityInfo(cityName);
		if (city == null) {
			return new ResponseEntity<CityInfo>(HttpStatus.NOT_FOUND);
			
		}else {
			
			
		return new ResponseEntity<CityInfo>(HttpStatus.OK);
	}
	
}
}
