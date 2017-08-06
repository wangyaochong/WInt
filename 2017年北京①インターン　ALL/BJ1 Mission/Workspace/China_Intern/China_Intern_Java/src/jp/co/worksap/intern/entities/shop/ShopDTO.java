package jp.co.worksap.intern.entities.shop;

import java.util.Date;

import jp.co.worksap.intern.entities.ICsvMasterDTO;

public class ShopDTO implements ICsvMasterDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6342807555669243688L;
	private Long shopId;
	private String shopName;
	private Date openTime;
	private Date closeTime;
	private int numberOfSeats;
	private int numberOfEmployees;
	private String area;

	public ShopDTO(Long shopId, String shopName, Date openTime, Date closeTime,
			int numberOfSeats, int numberOfEmployees, String area) {
		super();
		this.shopId = shopId;
		this.shopName = shopName;
		this.openTime = openTime;
		this.closeTime = closeTime;
		this.numberOfSeats = numberOfSeats;
		this.numberOfEmployees = numberOfEmployees;
		this.area = area;
	}

	public Long getShopId() {
		return shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public Date getOpenTime() {
		return openTime;
	}

	public Date getCloseTime() {
		return closeTime;
	}

	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	public int getNumberOfEmployees() {
		return numberOfEmployees;
	}

	public String getArea() {
		return area;
	}

}
