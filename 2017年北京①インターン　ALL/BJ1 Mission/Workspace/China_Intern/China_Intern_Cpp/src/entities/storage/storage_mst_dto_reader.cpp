#include "storage_mst_dto_reader.h"
#include "storage_mst_dto.h"
#include "../icsv_master_dto.h"
#include "../../util/utilities.h"
#include "../../constants/constants.h"
#include <string>
#include <vector>
#include <cstdlib>
using namespace std;

StorageDTOReader::StorageDTOReader() {
    this->file_address = kDefaultCsvFolder + "STORAGE_MST.csv";
    AbstractDTOReader::init();
}

StorageDTOReader::StorageDTOReader(string &file_address) {
    this->file_address = file_address;
    AbstractDTOReader::init();
}

string StorageDTOReader::get_file_address() {
    return this->file_address;
}

ICsvMasterDTO *StorageDTOReader::convert_array_to_dto(vector<string> &value) {
    long item_id = atol(value[COLUMN_INDEX_ITEM_ID].c_str());
    long current_quantity = atol(value[COLUMN_INDEX_CURRENT_QUANTITY].c_str());

    ICsvMasterDTO *obj = new StorageDTO(item_id, current_quantity);
    return obj;
}

StorageDTOReader::~StorageDTOReader() {

}