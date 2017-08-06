package jp.co.worksap.intern.entities.settlement;

import java.util.Date;

import jp.co.worksap.intern.entities.ICsvMasterDTO;

public class SettlementDTO implements ICsvMasterDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1478492629899162123L;
	private Long settlementId;
	private Date date;
	private String customerGender;
	private String customerAge;
	private Long shopId;
	private Long employeeId;

	/**
	 * 
	 * @param settlementId
	 * @param date
	 * @param time
	 * @param customerGender
	 * @param customerAge
	 * @param shopId
	 * @param employeeId
	 */
	public SettlementDTO(Long settlementId, Date date, String customerGender,
			String customerAge, Long shopId, Long employeeId) {
		super();
		this.settlementId = settlementId;
		this.date = date;
		this.customerGender = customerGender;
		this.customerAge = customerAge;
		this.shopId = shopId;
		this.employeeId = employeeId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getSettlementId() {
		return settlementId;
	}

	public Date getDate() {
		return date;
	}

	public String getCustomerGender() {
		return customerGender;
	}

	public String getCustomerAge() {
		return customerAge;
	}

	public Long getShopId() {
		return shopId;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

}
