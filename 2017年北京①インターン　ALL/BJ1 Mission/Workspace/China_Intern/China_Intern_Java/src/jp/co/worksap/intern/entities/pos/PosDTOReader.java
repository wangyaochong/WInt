package jp.co.worksap.intern.entities.pos;

import java.io.IOException;

import jp.co.worksap.intern.constants.Constants;
import jp.co.worksap.intern.entities.AbstractDTOReader;

public class PosDTOReader extends AbstractDTOReader<PosDTO> {
	private static final int COLUMN_INDEX_POS_ID = 0;
	private static final int COLUMN_INDEX_SETTLEMENT_ID = 1;
	private static final int COLUMN_INDEX_ITEM_ID = 2;
	private static final int COLUMN_INDEX_QUANTITY = 3;

	private String fileAddress = Constants.DEFAULT_CSV_FOLDER + "POS.csv";

	/**
	 * use default file address
	 * 
	 * @throws IOException
	 */
	public PosDTOReader() throws IOException {
		super(PosDTOReader.class.getName());
		super.init();
	}

	/**
	 * use customize file address
	 * 
	 * @param fileAddress
	 * @throws IOException
	 */
	public PosDTOReader(final String fileAddress) throws IOException {
		super(PosDTOReader.class.getName());
		this.fileAddress = fileAddress;
		super.init();
	}

	@Override
	protected String getFileArress() {
		return fileAddress;
	}

	@Override
	protected PosDTO convertArrayToDTO(String[] value) throws IOException {
		Long posId = Long.valueOf(value[COLUMN_INDEX_POS_ID]);
		Long settlementId = Long.valueOf(value[COLUMN_INDEX_SETTLEMENT_ID]);
		Long itemId = Long.valueOf(value[COLUMN_INDEX_ITEM_ID]);
		int quantity = Integer.valueOf(value[COLUMN_INDEX_QUANTITY]);

		PosDTO dto = new PosDTO(posId, settlementId, itemId, quantity);
		return dto;
	}
}
