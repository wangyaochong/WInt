#ifndef SHOP_MST_DTO_READER_H_
#define SHOP_MST_DTO_READER_H_

#include "../abstract_dto_reader.h"
#include "shop_mst_dto.h"
#include "../icsv_master_dto.h"

class ShopDTOReader: public AbstractDTOReader {
public:
    ShopDTOReader();
    ShopDTOReader(string &file_address);
    ICsvMasterDTO *convert_array_to_dto(vector<string> &value);
    string get_file_address();
    ~ShopDTOReader();

private:
    string file_address;
    static const int COLUMN_INDEX_SHOP_ID = 0;
    static const int COLUMN_INDEX_SHOP_NAME = 1;
    static const int COLUMN_INDEX_OPEN_TIME = 2;
    static const int COLUMN_INDEX_CLOSE_TIME = 3;
    static const int COLUMN_INDEX_NUMBER_OF_SEATS = 4;
    static const int COLUMN_INDEX_NUMBER_OF_EMPLOYEES = 5;
    static const int COLUMN_INDEX_AREA = 6;

};

#endif /* SHOP_MST_DTO_READER_H_ */