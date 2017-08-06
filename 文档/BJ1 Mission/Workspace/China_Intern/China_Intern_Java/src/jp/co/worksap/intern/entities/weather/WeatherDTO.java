package jp.co.worksap.intern.entities.weather;

import java.util.Date;

import jp.co.worksap.intern.entities.ICsvMasterDTO;

public class WeatherDTO implements ICsvMasterDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2219246629860118704L;
	private Long shopId;
	private Date date;
	private Float rainfallAmount;
	private Float temperature;
	private Float humidity;

	/**
	 * 
	 * @param shopId
	 * @param date
	 * @param rainfallAmount
	 * @param temperature
	 * @param humidity
	 */
	public WeatherDTO(Long shopId, Date date, Float rainfallAmount,
			Float temperature, Float humidity) {
		super();
		this.shopId = shopId;
		this.date = date;
		this.rainfallAmount = rainfallAmount;
		this.temperature = temperature;
		this.humidity = humidity;
	}

	public Long getShopId() {
		return shopId;
	}

	public Date getDate() {
		return date;
	}

	public Float getRainfallAmount() {
		return rainfallAmount;
	}

	public Float getTemperature() {
		return temperature;
	}

	public Float getHumidity() {
		return humidity;
	}

}
