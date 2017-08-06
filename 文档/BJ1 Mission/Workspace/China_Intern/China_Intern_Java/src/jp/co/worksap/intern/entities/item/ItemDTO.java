package jp.co.worksap.intern.entities.item;

import jp.co.worksap.intern.entities.ICsvMasterDTO;

public class ItemDTO implements ICsvMasterDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7993842130786140713L;
	private Long itemId;
	private String itemName;
	private Long price;
	private Long cost;

	public ItemDTO(Long itemId, String itemName, Long price, Long cost) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.price = price;
		this.cost = cost;
	}

	public Long getItemId() {
		return itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public Long getPrice() {
		return price;
	}

	public Long getCost() {
		return cost;
	}

}
