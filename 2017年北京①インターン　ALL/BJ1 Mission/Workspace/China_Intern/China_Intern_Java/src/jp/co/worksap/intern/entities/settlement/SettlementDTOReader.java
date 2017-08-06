package jp.co.worksap.intern.entities.settlement;

import java.io.IOException;
import java.util.Date;

import jp.co.worksap.intern.constants.Constants;
import jp.co.worksap.intern.entities.AbstractDTOReader;
import jp.co.worksap.intern.util.Utilities;

public class SettlementDTOReader extends AbstractDTOReader<SettlementDTO> {
	private static final int COLUMN_INDEX_SETTLEMENT_ID = 0;
	private static final int COLUMN_INDEX_DATE = 1;
	private static final int COLUMN_INDEX_CUSTOMER_GENDER = 2;
	private static final int COLUMN_INDEX_CUSTOMER_AGE = 3;
	private static final int COLUMN_INDEX_SHOP_ID = 4;
	private static final int COLUMN_INDEX_EMPLOYEE_ID = 5;

	private String fileAddress = Constants.DEFAULT_CSV_FOLDER
			+ "SETTLEMENT.csv";

	/**
	 * use default file address
	 * 
	 * @throws IOException
	 */
	public SettlementDTOReader() throws IOException {
		super(SettlementDTOReader.class.getName());
		super.init();
	}

	public SettlementDTOReader(final String fileAddress) throws IOException {
		super(SettlementDTOReader.class.getName());
		this.fileAddress = fileAddress;
		super.init();
	}

	@Override
	protected String getFileArress() {
		return fileAddress;
	}

	@Override
	protected SettlementDTO convertArrayToDTO(String[] value)
			throws IOException {
		Long settlementId = Long.valueOf(value[COLUMN_INDEX_SETTLEMENT_ID]);
		Date date = Utilities.parseDateStr(value[COLUMN_INDEX_DATE]);
		String customerGender = value[COLUMN_INDEX_CUSTOMER_GENDER];
		String customerAge = value[COLUMN_INDEX_CUSTOMER_AGE];
		Long shopId = Long.valueOf(value[COLUMN_INDEX_SHOP_ID]);
		Long employeeId = Long.valueOf(value[COLUMN_INDEX_EMPLOYEE_ID]);

		SettlementDTO dto = new SettlementDTO(settlementId, date,
				customerGender, customerAge, shopId, employeeId);
		return dto;
	}
}
