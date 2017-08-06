#include "delivery_dto_reader.h"
#include "delivery_dto.h"
#include "../icsv_master_dto.h"
#include "../../util/utilities.h"
#include "../../constants/constants.h"
#include <string>
#include <vector>
#include <cstdlib>
using namespace std;

DeliveryDTOReader::DeliveryDTOReader() {
    this->file_address = kDefaultCsvFolder + "DELIVERY.csv";
    AbstractDTOReader::init();
}

DeliveryDTOReader::DeliveryDTOReader(string &file_address) {
    this->file_address = file_address;
    AbstractDTOReader::init();
}

string DeliveryDTOReader::get_file_address() {
    return this->file_address;
}

ICsvMasterDTO *DeliveryDTOReader::convert_array_to_dto(vector<string> &value) {
    long settlement_id = atol(value[COLUMN_INDEX_SETTLEMENT_ID].c_str());
    string customer_name = value[COLUMN_INDEX_CUSTOMER_NAME];
    string customer_address = value[COLUMN_INDEX_CUSTOMER_ADDRESS];
    string customer_tel = value[COLUMN_INDEX_CUSTOMER_TEL];
    struct tm delivery_time = Utilities::parse_date_str(value[COLUMN_INDEX_DELIVERY_TIME]);

    ICsvMasterDTO *obj = new DeliveryDTO(settlement_id, customer_name, customer_address, customer_tel, delivery_time);
    return obj;
}

DeliveryDTOReader::~DeliveryDTOReader() {

}