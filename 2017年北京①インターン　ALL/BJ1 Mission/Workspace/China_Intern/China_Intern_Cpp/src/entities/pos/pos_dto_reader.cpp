#include "pos_dto_reader.h"
#include "pos_dto.h"
#include "../icsv_master_dto.h"
#include "../../util/utilities.h"
#include "../../constants/constants.h"
#include <string>
#include <vector>
#include <cstdlib>
using namespace std;

PosDTOReader::PosDTOReader() {
    this->file_address = kDefaultCsvFolder + "POS.csv";
    AbstractDTOReader::init();
}

PosDTOReader::PosDTOReader(string &file_address) {
    this->file_address = file_address;
    AbstractDTOReader::init();
}

string PosDTOReader::get_file_address() {
    return this->file_address;
}

ICsvMasterDTO *PosDTOReader::convert_array_to_dto(vector<string> &value) {
    long pos_id = atol(value[COLUMN_INDEX_POS_ID].c_str());
    long settlement_id = atol(value[COLUMN_INDEX_SETTLEMENT_ID].c_str());
    long item_id = atol(value[COLUMN_INDEX_ITEM_ID].c_str());
    long quantity = atol(value[COLUMN_INDEX_QUANTITY].c_str());

    ICsvMasterDTO *obj = new PosDTO(pos_id, settlement_id, item_id, quantity);
    return obj;
}

PosDTOReader::~PosDTOReader() {

}