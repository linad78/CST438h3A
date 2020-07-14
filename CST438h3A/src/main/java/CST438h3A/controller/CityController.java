package CST438h3A.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import CST438h3A.domain.City;
import CST438h3A.domain.CityInfo;
import CST438h3A.service.CityService;

@Controller
public class CityController {
	
	@Autowired
	private CityService cityService;
	
	@GetMapping("/cities/{city}")
	public String getCityInfo(@PathVariable("city") String cityName, Model model) {

		// TODO Your code goes here
		
			CityInfo cityInfo = cityService.getCityInfo(cityName);
			if ( cityInfo == null) {
				return "error";
				
				
			} else {
				
				model.addAttribute("cityInfo", cityInfo);
				return "city_info";
				   			
			}
	}
	@PostMapping("/cities/reservation")
	public String createReservation(
			@RequestParam("city") String cityName, 
			@RequestParam("level") String level, 
			@RequestParam("email") String email, 
			Model model) {
		
		model.addAttribute("city", cityName);
		model.addAttribute("level", level);
		model.addAttribute("email", email);
		cityService.requestReservation(cityName, level, email);
		return "request_reservation";
	}

}
