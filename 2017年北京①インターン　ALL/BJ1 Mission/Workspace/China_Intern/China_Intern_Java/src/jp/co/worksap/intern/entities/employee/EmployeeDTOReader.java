package jp.co.worksap.intern.entities.employee;

import java.io.IOException;

import jp.co.worksap.intern.constants.Constants;
import jp.co.worksap.intern.entities.AbstractDTOReader;

public class EmployeeDTOReader extends AbstractDTOReader<EmployeeDTO> {
	private static final int COLUMN_INDEX_EMPLOYEE_ID = 0;
	private static final int COLUMN_INDEX_EMPLOYEE_NAME = 1;
	private static final int COLUMN_INDEX_GENDER = 2;
	private static final int COLUMN_INDEX_AGE = 3;
	private static final int COLUMN_INDEX_SHOP_ID = 4;
	private static final int COLUMN_INDEX_RANK = 5;
	private static final int COLUMN_INDEX_JOB_TYPE_ID = 6;
	

	private String fileAddress = Constants.DEFAULT_CSV_FOLDER+"EMPLOYEE_MST.csv";

	/**
	 * use default file address
	 * 
	 * @throws IOException
	 */
	public EmployeeDTOReader() throws IOException {
		super(EmployeeDTOReader.class.getName());
		super.init();
	}

	/**
	 * use customize file address
	 * 
	 * @param fileAddress
	 * @throws IOException
	 */
	public EmployeeDTOReader(final String fileAddress) throws IOException {
		super(EmployeeDTOReader.class.getName());
		this.fileAddress = fileAddress;
		super.init();
	}

	@Override
	protected String getFileArress() {
		return fileAddress;
	}

	@Override
	protected EmployeeDTO convertArrayToDTO(String[] value) throws IOException {

		Long employeeId = Long.valueOf(value[COLUMN_INDEX_EMPLOYEE_ID]);
		String employeeName = value[COLUMN_INDEX_EMPLOYEE_NAME];
		String gender = value[COLUMN_INDEX_GENDER];
		Integer age = Integer.valueOf(value[COLUMN_INDEX_AGE]);
		Long shopId = Long.valueOf(value[COLUMN_INDEX_SHOP_ID]);
		RankType rank = RankType.valueOfString(value[COLUMN_INDEX_RANK]);
		Long jobTypeId = Long.valueOf(value[COLUMN_INDEX_JOB_TYPE_ID]);
		
		EmployeeDTO dto = new EmployeeDTO(employeeId, employeeName, gender, age, shopId, rank, jobTypeId);
		return dto;
	}
}
