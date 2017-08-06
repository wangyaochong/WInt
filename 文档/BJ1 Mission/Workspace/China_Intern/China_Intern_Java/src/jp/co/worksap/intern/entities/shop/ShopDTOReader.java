package jp.co.worksap.intern.entities.shop;

import java.io.IOException;
import java.util.Date;

import jp.co.worksap.intern.constants.Constants;
import jp.co.worksap.intern.entities.AbstractDTOReader;
import jp.co.worksap.intern.util.Utilities;

public class ShopDTOReader extends AbstractDTOReader<ShopDTO> {
	private static final int COLUMN_INDEX_SHOP_ID = 0;
	private static final int COLUMN_INDEX_SHOP_NAME = 1;
	private static final int COLUMN_INDEX_OPEN_TIME = 2;
	private static final int COLUMN_INDEX_CLOSE_TIME = 3;
	private static final int COLUMN_INDEX_NUMBER_OF_SEATS = 4;
	private static final int COLUMN_INDEX_NUMBER_OF_EMPLOYEES = 5;
	private static final int COLUMN_INDEX_AREA = 6;

	private String fileAddress = Constants.DEFAULT_CSV_FOLDER + "SHOP_MST.csv";

	/**
	 * use default file address
	 * 
	 * @throws IOException
	 */
	public ShopDTOReader() throws IOException {
		super(ShopDTOReader.class.getName());
		super.init();
	}

	/**
	 * use customize file address
	 * 
	 * @param fileAddress
	 * @throws IOException
	 */
	public ShopDTOReader(final String fileAddress) throws IOException {
		super(ShopDTOReader.class.getName());
		this.fileAddress = fileAddress;
		super.init();
	}

	@Override
	protected String getFileArress() {
		return fileAddress;
	}

	@Override
	protected ShopDTO convertArrayToDTO(String[] value) throws IOException {
		Long shopId = Long.valueOf(value[COLUMN_INDEX_SHOP_ID]);
		String shopName = value[COLUMN_INDEX_SHOP_NAME];
		Date openTime = Utilities.parseTimeStr(value[COLUMN_INDEX_OPEN_TIME]);
		Date closeTime = Utilities.parseTimeStr(value[COLUMN_INDEX_CLOSE_TIME]);
		int numberOfSeats = Integer
				.valueOf(value[COLUMN_INDEX_NUMBER_OF_SEATS]);
		int numberOfEmployees = Integer
				.valueOf(value[COLUMN_INDEX_NUMBER_OF_EMPLOYEES]);
		String area = value[COLUMN_INDEX_AREA];

		ShopDTO dto = new ShopDTO(shopId, shopName, openTime, closeTime,
				numberOfSeats, numberOfEmployees, area);

		return dto;
	}
}
