package jp.co.worksap.intern.writer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jp.co.worksap.intern.constants.Constants;
import jp.co.worksap.intern.entities.employee.EmployeeDTO;
import jp.co.worksap.intern.entities.employee.EmployeeDTOReader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SimpleEmployeeDTOWriterTest {

	private List<EmployeeDTO> employees = null;
	private IResultWriter writer = null;
	private String fileName = "EMPLOYEE_MST.csv";
	
	@Before
	public void setup() throws IOException {
		EmployeeDTOReader reader = new EmployeeDTOReader();
		employees = reader.getValues();
		writer = new ResultWriterImpl(
				Constants.DEFAULT_CSV_OUTPUT_FOLDER + 
				fileName);
	}

	@After
	public void clean() {
		if (employees != null) {
			employees.clear();
		}
	}

	@Test
	public void noTitleTest() {
		List<String[]> contents = new ArrayList<String[]>(employees.size());
		writeContent(contents);
	}

	@Test
	public void withTitleTest() {
		List<String[]> contents = new ArrayList<String[]>(employees.size());
		contents.add(new String[] { 
				"employee_id", "employee_name", "gender", "age",
				"shop_id", "rank","job_type_id" });
		writeContent(contents);
	}

	private void writeContent(List<String[]> contents) {
		for (EmployeeDTO employee : employees) {
			String[] args = new String[] { 
					employee.getEmployeeId().toString(),
					employee.getEmployeeName(),
					employee.getGender(),
					employee.getAge().toString(),
					employee.getShopId().toString(),
					employee.getRank().toString(),
					employee.getJobTypeId().toString()
					};
			contents.add(args);
		}
		writer.writeResult(contents);
	}
}
