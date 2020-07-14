package CST438h3A.domain;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class TempAndTime {
	
	public double temp;
	public long time;
	public int timezone;
	
	public TempAndTime(double temp, long time, int timezone){
		this.temp = temp;
		this.time = time;
		this.timezone = timezone;
	}
	public double getTemp() {
		return temp;
	}
	
	public void setTemp (double temp) {
		this.temp=temp;
	}
	public double getTime() {
		return temp;
	}
	
	public void setTime (long time) {
		this.time=time;
	}
	public int getTimezone() {
		return timezone;
	}

	public void setTimezone(int timezone) {
		this.timezone = timezone;
	}
	
	public double getFahrenheit(){
		double temp =  (double) Math.round(((this.temp - 273.15) * 9.0 / 5.0 + 32.0) * 100) / 100;
		return (temp);
	}
		public String getLocalTime(){
			Instant instant = Instant.ofEpochSecond(this.time);
			ZoneOffset zoneOffset = ZoneOffset.ofTotalSeconds(this.timezone);

			OffsetDateTime offsetDate = instant.atOffset(zoneOffset);

			String timeFormat = "hh:mm a";
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(timeFormat);

			return offsetDate.format(dateTimeFormatter);
		}
	}