package jp.co.worksap.intern.entities.jobtype;

import java.io.IOException;

import jp.co.worksap.intern.constants.Constants;
import jp.co.worksap.intern.entities.AbstractDTOReader;

public class JobTypeReader extends AbstractDTOReader<JobTypeDTO> {
	private static final int COLUMN_INDEX_JOB_TYPE_ID = 0;
	private static final int COLUMN_INDEX_JOB_TYPE_NAME = 1;
	private static final int COLUMN_INDEX_CAN_DO_CASHER = 2;
	private static final int COLUMN_INDEX_CAN_DO_DELIVERY = 3;
	private static final int COLUMN_INDEX_CAN_DO_COOK = 4;

	private String fileAddress = Constants.DEFAULT_CSV_FOLDER
			+ "JOB_TYPE_MST.csv";

	/**
	 * use default file address
	 * 
	 * @throws IOException
	 */
	public JobTypeReader() throws IOException {
		super(JobTypeReader.class.getName());
		super.init();
	}

	/**
	 * use customize file address
	 * 
	 * @param fileAddress
	 * @throws IOException
	 */
	public JobTypeReader(final String fileAddress) throws IOException {
		super(JobTypeReader.class.getName());
		this.fileAddress = fileAddress;
		super.init();
	}

	@Override
	protected String getFileArress() {
		return fileAddress;
	}

	@Override
	protected JobTypeDTO convertArrayToDTO(String[] value) throws IOException {
		Long jobTypeId = Long.valueOf(value[COLUMN_INDEX_JOB_TYPE_ID]);
		String jobTypeName = value[COLUMN_INDEX_JOB_TYPE_NAME];
		Boolean canDoCasher = ("1".equals(value[COLUMN_INDEX_CAN_DO_CASHER]));
		Boolean canDoDelivery = ("1"
				.equals(value[COLUMN_INDEX_CAN_DO_DELIVERY]));
		Boolean canDoCook = ("1".equals(value[COLUMN_INDEX_CAN_DO_COOK]));

		JobTypeDTO dto = new JobTypeDTO(jobTypeId, jobTypeName, canDoCasher,
				canDoDelivery, canDoCook);
		return dto;
	}
}
