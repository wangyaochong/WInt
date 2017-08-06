package jp.co.worksap.intern.entities.storage;

import jp.co.worksap.intern.entities.ICsvMasterDTO;

public class StorageDTO implements ICsvMasterDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2269246629860118704L;
	private Long itemId;
	private int currentQuantity;

	public StorageDTO(Long itemId, int currentQuantity) {
		super();
		this.itemId = itemId;
		this.currentQuantity = currentQuantity;
	}

	public Long getItemId() {
		return itemId;
	}

	public int getCurrentQuantity() {
		return currentQuantity;
	}

}
