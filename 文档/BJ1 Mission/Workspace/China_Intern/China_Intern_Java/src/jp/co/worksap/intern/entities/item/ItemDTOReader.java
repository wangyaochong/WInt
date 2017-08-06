package jp.co.worksap.intern.entities.item;

import java.io.IOException;

import jp.co.worksap.intern.constants.Constants;
import jp.co.worksap.intern.entities.AbstractDTOReader;

public class ItemDTOReader extends AbstractDTOReader<ItemDTO> {
	private static final int COLUMN_INDEX_ITEM_ID = 0;
	private static final int COLUMN_INDEX_ITEM_NAME = 1;
	private static final int COLUMN_INDEX_PRICE = 2;
	private static final int COLUMN_INDEX_COST = 3;

	private String fileAddress = Constants.DEFAULT_CSV_FOLDER + "ITEM_MST.csv";

	/**
	 * use default file address
	 * 
	 * @throws IOException
	 */
	public ItemDTOReader() throws IOException {
		super(ItemDTOReader.class.getName());
		super.init();
	}

	/**
	 * use customize file address
	 * 
	 * @param fileAddress
	 * @throws IOException
	 */
	public ItemDTOReader(final String fileAddress) throws IOException {
		super(ItemDTOReader.class.getName());
		this.fileAddress = fileAddress;
		super.init();
	}

	@Override
	protected String getFileArress() {
		return fileAddress;
	}

	@Override
	protected ItemDTO convertArrayToDTO(String[] value) throws IOException {
		Long itemId = Long.valueOf(value[COLUMN_INDEX_ITEM_ID]);
		String itemName = value[COLUMN_INDEX_ITEM_NAME];
		Long price = Long.valueOf(value[COLUMN_INDEX_PRICE]);
		Long cost = Long.valueOf(value[COLUMN_INDEX_COST]);

		ItemDTO dto = new ItemDTO(itemId, itemName, price, cost);
		return dto;
	}
}
