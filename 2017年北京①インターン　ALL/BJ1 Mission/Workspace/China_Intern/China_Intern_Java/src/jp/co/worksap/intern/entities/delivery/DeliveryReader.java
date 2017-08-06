package jp.co.worksap.intern.entities.delivery;

import java.io.IOException;
import java.util.Date;

import jp.co.worksap.intern.constants.Constants;
import jp.co.worksap.intern.entities.AbstractDTOReader;
import jp.co.worksap.intern.util.Utilities;

public class DeliveryReader extends AbstractDTOReader<DeliveryDTO> {
	private static final int COLUMN_INDEX_SETTLEMENT_ID = 0;
	private static final int COLUMN_INDEX_CUSTOMER_NAME = 1;
	private static final int COLUMN_INDEX_CUSTOMER_ADDRESS = 2;
	private static final int COLUMN_INDEX_CUSTOMER_TEL = 3;
	private static final int COLUMN_INDEX_DELIVERY_TIME = 4;

	private String fileAddress = Constants.DEFAULT_CSV_FOLDER + "delivery.csv";

	/**
	 * use default file address
	 * 
	 * @throws IOException
	 */
	public DeliveryReader() throws IOException {
		super(DeliveryReader.class.getName());
		super.init();

	}

	/**
	 * use customize file address
	 * 
	 * @param fileAddress
	 * @throws IOException
	 */
	public DeliveryReader(final String fileAddress) throws IOException {
		super(DeliveryReader.class.getName());
		this.fileAddress = fileAddress;
		super.init();
	}

	@Override
	protected String getFileArress() {
		return fileAddress;
	}

	@Override
	protected DeliveryDTO convertArrayToDTO(String[] value) throws IOException {
		Long settlementId = Long.valueOf(value[COLUMN_INDEX_SETTLEMENT_ID]);
		String customerName = value[COLUMN_INDEX_CUSTOMER_NAME];
		String customerAddress = value[COLUMN_INDEX_CUSTOMER_ADDRESS];
		String customerTel = value[COLUMN_INDEX_CUSTOMER_TEL];
		Date deliveryTime = Utilities
				.parseDateStr(value[COLUMN_INDEX_DELIVERY_TIME]);

		DeliveryDTO dto = new DeliveryDTO(settlementId, customerName,
				customerAddress, customerTel, deliveryTime);
		return dto;
	}
}
