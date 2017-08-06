package jp.co.worksap.intern.entities.employee;

import jp.co.worksap.intern.entities.ICsvMasterDTO;

public class EmployeeDTO implements ICsvMasterDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8538243149394207296L;
	private Long employeeId;
	private String employeeName;
	private String gender;
	private Integer age;
	private Long shopId;
	private RankType rank;
	private Long jobTypeId;

	public EmployeeDTO(Long employeeId, String employeeName, String gender,
			Integer age, Long shopId, RankType rank, Long jobTypeId) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.gender = gender;
		this.age = age;
		this.shopId = shopId;
		this.rank = rank;
		this.jobTypeId = jobTypeId;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public String getGender() {
		return gender;
	}

	public Integer getAge() {
		return age;
	}

	public Long getShopId() {
		return shopId;
	}

	public RankType getRank() {
		return rank;
	}

	public Long getJobTypeId() {
		return jobTypeId;
	}

}
