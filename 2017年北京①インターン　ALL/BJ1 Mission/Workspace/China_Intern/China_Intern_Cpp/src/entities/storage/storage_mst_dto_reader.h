#ifndef STORAGE_MST_DTO_READER_H_
#define STORAGE_MST_DTO_READER_H_

#include "../abstract_dto_reader.h"
#include "storage_mst_dto.h"
#include "../icsv_master_dto.h"

class StorageDTOReader: public AbstractDTOReader {
public:
    StorageDTOReader();
    StorageDTOReader(string &file_address);
    ICsvMasterDTO *convert_array_to_dto(vector<string> &value);
    string get_file_address();
    ~StorageDTOReader();

private:
    string file_address;
    static const int COLUMN_INDEX_ITEM_ID = 0;
    static const int COLUMN_INDEX_CURRENT_QUANTITY = 1;

};

#endif /* STORAGE_MST_DTO_READER_H_ */