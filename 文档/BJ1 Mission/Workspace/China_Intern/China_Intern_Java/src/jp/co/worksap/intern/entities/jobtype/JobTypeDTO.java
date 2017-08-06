package jp.co.worksap.intern.entities.jobtype;

import jp.co.worksap.intern.entities.ICsvMasterDTO;

public class JobTypeDTO implements ICsvMasterDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5569246629860122704L;
	private Long jobTypeId;
	private String jobTypeName;
	private Boolean canDoCasher;
	private Boolean canDoDelivery;
	private Boolean canDoCook;

	public JobTypeDTO(Long jobTypeId, String jobTypeName, Boolean canDoCasher,
			Boolean canDoDelivery, Boolean canDoCook) {
		super();
		this.jobTypeId = jobTypeId;
		this.jobTypeName = jobTypeName;
		this.canDoCasher = canDoCasher;
		this.canDoDelivery = canDoDelivery;
		this.canDoCook = canDoCook;
	}

	public Long getJobTypeId() {
		return jobTypeId;
	}

	public String getJobTypeName() {
		return jobTypeName;
	}

	public Boolean getCanDoCasher() {
		return canDoCasher;
	}

	public Boolean getCanDoDelivery() {
		return canDoDelivery;
	}

	public Boolean getCanDoCook() {
		return canDoCook;
	}

}
