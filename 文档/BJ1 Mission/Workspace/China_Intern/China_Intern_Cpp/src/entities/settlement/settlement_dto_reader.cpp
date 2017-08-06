#include "settlement_dto_reader.h"
#include "settlement_dto.h"
#include "../icsv_master_dto.h"
#include "../../util/utilities.h"
#include "../../constants/constants.h"
#include <string>
#include <vector>
#include <cstdlib>
using namespace std;

SettlementDTOReader::SettlementDTOReader() {
    this->file_address = kDefaultCsvFolder + "SETTLEMENT.csv";
    AbstractDTOReader::init();
}

SettlementDTOReader::SettlementDTOReader(string &file_address) {
    this->file_address = file_address;
    AbstractDTOReader::init();
}

string SettlementDTOReader::get_file_address() {
    return this->file_address;
}

ICsvMasterDTO *SettlementDTOReader::convert_array_to_dto(vector<string> &value) {
    long settlement_id = atol(value[COLUMN_INDEX_SETTLEMENT_ID].c_str());
    struct tm date = Utilities::parse_date_str(value[COLUMN_INDEX_DATE]);
    string customer_gender = value[COLUMN_INDEX_CUSTOMER_GENDER];
    string customer_age = value[COLUMN_INDEX_CUSTOMER_AGE];
    long shop_id = atol(value[COLUMN_INDEX_SHOP_ID].c_str());
    long employee_id = atol(value[COLUMN_INDEX_EMPLOYEE_ID].c_str());

    ICsvMasterDTO *obj = new SettlementDTO(settlement_id, date, customer_gender, customer_age, shop_id, employee_id);
    return obj;
}

SettlementDTOReader::~SettlementDTOReader() {

}