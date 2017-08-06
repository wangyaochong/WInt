#include "shop_mst_dto_reader.h"
#include "shop_mst_dto.h"
#include "../icsv_master_dto.h"
#include "../../util/utilities.h"
#include "../../constants/constants.h"
#include <string>
#include <vector>
#include <cstdlib>
using namespace std;

ShopDTOReader::ShopDTOReader() {
    this->file_address = kDefaultCsvFolder + "SHOP_MST.csv";
    AbstractDTOReader::init();
}

ShopDTOReader::ShopDTOReader(string &file_address) {
    this->file_address = file_address;
    AbstractDTOReader::init();
}

string ShopDTOReader::get_file_address() {
    return this->file_address;
}

ICsvMasterDTO *ShopDTOReader::convert_array_to_dto(vector<string> &value) {
    long shop_id = atol(value[COLUMN_INDEX_SHOP_ID].c_str());
    string shop_name = value[COLUMN_INDEX_SHOP_NAME];
    string open_time = value[COLUMN_INDEX_OPEN_TIME];
    string close_time = value[COLUMN_INDEX_CLOSE_TIME];
    long number_of_seats = atol(value[COLUMN_INDEX_NUMBER_OF_SEATS].c_str());
    long number_of_employees = atol(value[COLUMN_INDEX_NUMBER_OF_EMPLOYEES].c_str());
    string area = value[COLUMN_INDEX_AREA];

    ICsvMasterDTO *obj = new ShopDTO(shop_id, shop_name, open_time, close_time, number_of_seats, number_of_employees, area);
    return obj;
}

ShopDTOReader::~ShopDTOReader() {

}