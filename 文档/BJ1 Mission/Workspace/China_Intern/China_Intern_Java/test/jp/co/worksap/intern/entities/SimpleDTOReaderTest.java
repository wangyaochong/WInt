package jp.co.worksap.intern.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.List;

import jp.co.worksap.intern.constants.Constants;
import jp.co.worksap.intern.entities.delivery.DeliveryDTO;
import jp.co.worksap.intern.entities.delivery.DeliveryReader;
import jp.co.worksap.intern.entities.employee.EmployeeDTO;
import jp.co.worksap.intern.entities.employee.EmployeeDTOReader;
import jp.co.worksap.intern.entities.item.ItemDTO;
import jp.co.worksap.intern.entities.item.ItemDTOReader;
import jp.co.worksap.intern.entities.jobtype.JobTypeDTO;
import jp.co.worksap.intern.entities.jobtype.JobTypeReader;
import jp.co.worksap.intern.entities.pos.PosDTO;
import jp.co.worksap.intern.entities.pos.PosDTOReader;
import jp.co.worksap.intern.entities.settlement.SettlementDTO;
import jp.co.worksap.intern.entities.settlement.SettlementDTOReader;
import jp.co.worksap.intern.entities.shop.ShopDTO;
import jp.co.worksap.intern.entities.shop.ShopDTOReader;
import jp.co.worksap.intern.entities.storage.StorageDTO;
import jp.co.worksap.intern.entities.storage.StorageDTOReader;
import jp.co.worksap.intern.entities.weather.WeatherDTO;
import jp.co.worksap.intern.entities.weather.WeatherDTOReader;

import org.junit.Test;

public class SimpleDTOReaderTest {

	@Test
	public void readDeliveryTable() throws IOException {
		DeliveryReader reader = new DeliveryReader();
		List<DeliveryDTO> values = reader.getValues();
		assertNotNull(values);
		for (DeliveryDTO value : values) {
			assertNotNull(value);
			assertNotNull(value.getSettlementId());
			assertNotNull(value.getCustomerName());
			assertNotNull(value.getCustomerAddress());
			assertNotNull(value.getCustomerTel());
			assertNotNull(value.getDeliveryTime());
		}
	}

	@Test
	public void readEmployeeTable() throws IOException {
		EmployeeDTOReader reader = new EmployeeDTOReader();
		List<EmployeeDTO> values = reader.getValues();
		assertNotNull(values);
		for (EmployeeDTO value : values) {
			assertNotNull(value);
			assertNotNull(value.getEmployeeId());
			assertNotNull(value.getEmployeeName());
			assertNotNull(value.getGender());
			assertNotNull(value.getJobTypeId());
			assertNotNull(value.getRank());
			assertNotNull(value.getShopId());
			assertNotNull(value.getAge());
		}
	}

	@Test
	public void readItemTable() throws IOException {
		ItemDTOReader reader = new ItemDTOReader(Constants.DEFAULT_CSV_FOLDER
				+ "ITEM_MST.csv");
		List<ItemDTO> values = reader.getValues();
		assertNotNull(values);
		for (ItemDTO value : values) {
			assertNotNull(value);
			assertNotNull(value.getItemId());
			assertNotNull(value.getItemName());
			assertNotNull(value.getPrice());
			assertNotNull(value.getCost());
		}
	}

	@Test
	public void readJobTypeMstTable() throws IOException {
		JobTypeReader reader = new JobTypeReader();
		List<JobTypeDTO> values = reader.getValues();
		assertNotNull(values);
		for (JobTypeDTO value : values) {
			assertNotNull(value);
			assertNotNull(value.getJobTypeId());
			assertNotNull(value.getJobTypeName());
			assertNotNull(value.getCanDoCasher());
			assertNotNull(value.getCanDoCook());
			assertNotNull(value.getCanDoDelivery());
		}
	}

	@Test
	public void readPosTable() throws IOException {
		PosDTOReader reader = new PosDTOReader();
		List<PosDTO> values = reader.getValues();
		assertNotNull(values);
		for (PosDTO value : values) {
			assertNotNull(value);
			assertNotNull(value.getPosId());
			assertNotNull(value.getSettlementId());
			assertNotNull(value.getItemId());
			assertNotNull(value.getQuantity());
		}
	}

	@Test
	public void readSettlementTable() throws IOException {
		SettlementDTOReader reader = new SettlementDTOReader();
		List<SettlementDTO> values = reader.getValues();
		assertNotNull(values);
		for (SettlementDTO value : values) {
			assertNotNull(value);
			assertNotNull(value.getSettlementId());
			assertNotNull(value.getDate());
			assertNotNull(value.getCustomerAge());
			assertNotNull(value.getCustomerGender());
			assertNotNull(value.getShopId());
			assertNotNull(value.getEmployeeId());
		}
	}

	@Test
	public void readShopTable() throws IOException {
		ShopDTOReader reader = new ShopDTOReader();
		List<ShopDTO> values = reader.getValues();
		assertNotNull(values);
		assertEquals(values.size(), 20);
		for (ShopDTO value : values) {
			assertNotNull(value);
			assertNotNull(value.getShopId());
			assertNotNull(value.getShopName());
			assertNotNull(value.getArea());
			assertNotNull(value.getOpenTime());
			assertNotNull(value.getCloseTime());
			assertNotNull(value.getNumberOfEmployees());
			assertNotNull(value.getNumberOfSeats());
		}
	}

	@Test
	public void readStorageTable() throws IOException {
		StorageDTOReader reader = new StorageDTOReader();
		List<StorageDTO> values = reader.getValues();
		assertNotNull(values);
		for (StorageDTO value : values) {
			assertNotNull(value);
			assertNotNull(value.getItemId());
			assertNotNull(value.getCurrentQuantity());
		}
	}

	@Test
	public void readWeatherTable() throws IOException {
		WeatherDTOReader reader = new WeatherDTOReader();
		List<WeatherDTO> values = reader.getValues();
		assertNotNull(values);
		for (WeatherDTO value : values) {
			assertNotNull(value);
			assertNotNull(value.getShopId());
			assertNotNull(value.getDate());
			assertNotNull(value.getRainfallAmount());
			assertNotNull(value.getTemperature());
			assertNotNull(value.getHumidity());
		}
	}
}
