package jp.co.worksap.intern.entities.weather;

import java.io.IOException;
import java.util.Date;

import jp.co.worksap.intern.constants.Constants;
import jp.co.worksap.intern.entities.AbstractDTOReader;
import jp.co.worksap.intern.util.Utilities;

public class WeatherDTOReader extends AbstractDTOReader<WeatherDTO> {
	private static final int COLUMN_INDEX_SHOP_ID = 0;
	private static final int COLUMN_INDEX_DATE = 1;
	private static final int COLUMN_INDEX_RAINFALL_AMOUNT = 2;
	private static final int COLUMN_INDEX_TEMPERATURE = 3;
	private static final int COLUMN_INDEX_HUMIDIFY = 4;

	private String fileAddress = Constants.DEFAULT_CSV_FOLDER + "WEATHER.csv";

	/**
	 * use default file address
	 * 
	 * @throws IOException
	 */
	public WeatherDTOReader() throws IOException {
		super(WeatherDTOReader.class.getName());
		super.init();
	}

	/**
	 * use customize file address
	 * 
	 * @param fileAddress
	 * @throws IOException
	 */
	public WeatherDTOReader(final String fileAddress) throws IOException {
		super(WeatherDTOReader.class.getName());
		this.fileAddress = fileAddress;
		super.init();
	}

	@Override
	protected String getFileArress() {
		return fileAddress;
	}

	@Override
	protected WeatherDTO convertArrayToDTO(String[] value) throws IOException {
		Long shopId = Long.valueOf(value[COLUMN_INDEX_SHOP_ID]);
		Date date = Utilities.parseDateStr(value[COLUMN_INDEX_DATE]);
		Float rainfallAmount = Float
				.valueOf(value[COLUMN_INDEX_RAINFALL_AMOUNT]);
		Float temperature = Float.valueOf(value[COLUMN_INDEX_TEMPERATURE]);
		Float humidity = Float.valueOf(value[COLUMN_INDEX_HUMIDIFY]);

		WeatherDTO dto = new WeatherDTO(shopId, date, rainfallAmount,
				temperature, humidity);
		return dto;
	}
}
