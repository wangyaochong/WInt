#ifndef ITEM_MST_DTO_READER_H_
#define ITEM_MST_DTO_READER_H_

#include "../abstract_dto_reader.h"
#include "item_mst_dto.h"
#include "../icsv_master_dto.h"

class ItemDTOReader: public AbstractDTOReader {
public:
    ItemDTOReader();
    ItemDTOReader(string &file_address);
    ICsvMasterDTO *convert_array_to_dto(vector<string> &value);
    string get_file_address();
    ~ItemDTOReader();

private:
    string file_address;
    static const int COLUMN_INDEX_ITEM_ID = 0;
    static const int COLUMN_INDEX_ITEM_NAME = 1;
    static const int COLUMN_INDEX_PRICE = 2;
    static const int COLUMN_INDEX_COST = 3;

};

#endif /* ITEM_MST_DTO_READER_H_ */