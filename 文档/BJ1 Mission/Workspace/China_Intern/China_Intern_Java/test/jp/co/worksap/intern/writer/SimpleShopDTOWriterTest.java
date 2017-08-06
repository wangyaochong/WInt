package jp.co.worksap.intern.writer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jp.co.worksap.intern.constants.Constants;
import jp.co.worksap.intern.entities.shop.ShopDTO;
import jp.co.worksap.intern.entities.shop.ShopDTOReader;
import jp.co.worksap.intern.util.Utilities;
import jp.co.worksap.intern.writer.IResultWriter;
import jp.co.worksap.intern.writer.ResultWriterImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SimpleShopDTOWriterTest {

	private List<ShopDTO> shops = null;
	private IResultWriter writer = null;
	private String fileName = "SHOP_MST.csv";

	@Before
	public void setup() throws IOException {
		ShopDTOReader reader = new ShopDTOReader();
		shops = reader.getValues();
		writer = new ResultWriterImpl(Constants.DEFAULT_CSV_OUTPUT_FOLDER
				+ fileName);
	}

	@After
	public void clean() {
		if (shops != null) {
			shops.clear();
		}
	}

	@Test
	public void noTitleTest() {
		List<String[]> contents = new ArrayList<String[]>(shops.size());
		writeContent(contents);
	}

	@Test
	public void withTitleTest() {
		List<String[]> contents = new ArrayList<String[]>(shops.size());
		contents.add(new String[] { "shop_id", "shop_name", "open_time",
				"close_time", "number_of_seats", "number_of_employees", "area" });
		writeContent(contents);
	}

	private void writeContent(List<String[]> contents) {
		for (ShopDTO shop : shops) {
			String[] args = new String[] { shop.getShopId().toString(),
					shop.getShopName(),
					Utilities.formatTime(shop.getOpenTime()),
					Utilities.formatTime(shop.getCloseTime()),
					String.valueOf(shop.getNumberOfSeats()),
					String.valueOf(shop.getNumberOfEmployees()), shop.getArea() };
			contents.add(args);
		}
		writer.writeResult(contents);
	}
}
