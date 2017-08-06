package jp.co.worksap.intern.entities.storage;

import java.io.IOException;

import jp.co.worksap.intern.constants.Constants;
import jp.co.worksap.intern.entities.AbstractDTOReader;

public class StorageDTOReader extends AbstractDTOReader<StorageDTO> {
	private static final int COLUMN_INDEX_ITEM_ID = 0;
	private static final int COLUMN_INDEX_CURRENT_QUANTITY = 1;

	private String fileAddress = Constants.DEFAULT_CSV_FOLDER
			+ "STORAGE_MST.csv";

	/**
	 * use default file address
	 * 
	 * @throws IOException
	 */
	public StorageDTOReader() throws IOException {
		super(StorageDTOReader.class.getName());
		super.init();
	}

	/**
	 * use customize file address
	 * 
	 * @param fileAddress
	 * @throws IOException
	 */
	public StorageDTOReader(final String fileAddress) throws IOException {
		super(StorageDTOReader.class.getName());
		this.fileAddress = fileAddress;
		super.init();
	}

	@Override
	protected String getFileArress() {
		return fileAddress;
	}

	@Override
	protected StorageDTO convertArrayToDTO(String[] value) throws IOException {
		Long itemId = Long.valueOf(value[COLUMN_INDEX_ITEM_ID]);
		int currentQuantity = Integer
				.valueOf(value[COLUMN_INDEX_CURRENT_QUANTITY]);

		StorageDTO dto = new StorageDTO(itemId, currentQuantity);
		return dto;
	}
}
