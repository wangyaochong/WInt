#include "item_mst_dto_reader.h"
#include "item_mst_dto.h"
#include "../icsv_master_dto.h"
#include "../../util/utilities.h"
#include "../../constants/constants.h"
#include <string>
#include <vector>
#include <cstdlib>
using namespace std;

ItemDTOReader::ItemDTOReader() {
    this->file_address = kDefaultCsvFolder + "ITEM_MST.csv";
    AbstractDTOReader::init();
}

ItemDTOReader::ItemDTOReader(string &file_address) {
    this->file_address = file_address;
    AbstractDTOReader::init();
}

string ItemDTOReader::get_file_address() {
    return this->file_address;
}

ICsvMasterDTO *ItemDTOReader::convert_array_to_dto(vector<string> &value) {
    long item_id = atol(value[COLUMN_INDEX_ITEM_ID].c_str());
    string item_name = value[COLUMN_INDEX_ITEM_NAME];
    long price = atol(value[COLUMN_INDEX_PRICE].c_str());
    long cost = atol(value[COLUMN_INDEX_COST].c_str());

    ICsvMasterDTO *obj = new ItemDTO(item_id, item_name, price, cost);
    return obj;
}

ItemDTOReader::~ItemDTOReader() {

}