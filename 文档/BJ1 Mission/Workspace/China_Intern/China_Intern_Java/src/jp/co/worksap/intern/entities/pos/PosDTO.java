package jp.co.worksap.intern.entities.pos;

import jp.co.worksap.intern.entities.ICsvMasterDTO;

public class PosDTO implements ICsvMasterDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2911231132225687846L;
	private Long posId;
	private Long settlementId;
	private Long itemId;
	private int quantity;

	/**
	 * 
	 * @param posId
	 * @param settlementId
	 * @param productId
	 * @param quantity
	 */
	public PosDTO(Long posId, Long settlementId, Long itemId, int quantity) {
		super();
		this.posId = posId;
		this.settlementId = settlementId;
		this.itemId = itemId;
		this.quantity = quantity;
	}

	public Long getPosId() {
		return posId;
	}

	public Long getSettlementId() {
		return settlementId;
	}

	public Long getItemId() {
		return itemId;
	}

	public int getQuantity() {
		return quantity;
	}

}
