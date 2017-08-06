package jp.co.worksap.intern.entities.delivery;

import java.util.Date;

import jp.co.worksap.intern.entities.ICsvMasterDTO;

public class DeliveryDTO implements ICsvMasterDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2269246629860122704L;
	private Long settlementId;
	private String customerName;
	private String customerAddress;
	private String customerTel;
	private Date deliveryTime;

	public DeliveryDTO(Long settlementId, String customerName,
			String customerAddress, String customerTel, Date deliveryTime) {
		super();
		this.settlementId = settlementId;
		this.customerName = customerName;
		this.customerAddress = customerAddress;
		this.customerTel = customerTel;
		this.deliveryTime = deliveryTime;
	}

	public Long getSettlementId() {
		return settlementId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public String getCustomerTel() {
		return customerTel;
	}

	public Date getDeliveryTime() {
		return deliveryTime;
	}

}
