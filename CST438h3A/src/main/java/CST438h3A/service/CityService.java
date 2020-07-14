package CST438h3A.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CST438h3A.domain.City;
import CST438h3A.domain.CityInfo;
import CST438h3A.domain.CityRepository;
import CST438h3A.domain.Country;
import CST438h3A.domain.CountryRepository;
import CST438h3A.domain.TempAndTime;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private WeatherService weatherService;
	@Autowired
	private CityService cityService;
    
	@Autowired
    private RabbitTemplate rabbitTemplate;
	
    @Autowired
    private FanoutExchange fanout;

	
	public CityInfo getCityInfo(String cityName) {
		List<City> cities = cityRepository.findByName(cityName);
		if (cities.size() > 0) {
			// if multiple cities, return first city
			City city = cities.get(0);
			Country country = countryRepository.findByCode(city.getCountryCode());
			TempAndTime tt = weatherService.getTempAndTime(cityName);
			double tempf = (tt.temp - 273.15) * 9.0/5.0 + 32.0;
			tempf = ((int)(tempf*100.0))/100.0;
			CityInfo cityInfo = new CityInfo(city, country.getName(), tempf, adjustTime(tt.timezone, tt.time*1000));
			return cityInfo;
		} else {
			// cityName not found
			return null;
		}
		
	}
	private static  String adjustTime(int timezone, long time) {
		SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
		TimeZone tz = TimeZone.getTimeZone("UTC");
		tz.setRawOffset(timezone*1000);
		sdf.setTimeZone(tz);
		Date date = new Date(time);
		return sdf.format(date);
	}


public void requestReservation( 
        String cityName, 
        String level, 
        String email) {
String msg  = "{\"cityName\": \""+ cityName + 
    "\" \"level\": \""+level+
    "\" \"email\": \""+email+"\"}" ;
System.out.println("Sending message:"+msg);
rabbitTemplate.convertSendAndReceive(
     fanout.getName(), 
     "",   // routing key none.
     msg);
}

}