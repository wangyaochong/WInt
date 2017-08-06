#ifndef POS_DTO_READER_H_
#define POS_DTO_READER_H_

#include "../abstract_dto_reader.h"
#include "pos_dto.h"
#include "../icsv_master_dto.h"

class PosDTOReader: public AbstractDTOReader {
public:
    PosDTOReader();
    PosDTOReader(string &file_address);
    ICsvMasterDTO *convert_array_to_dto(vector<string> &value);
    string get_file_address();
    ~PosDTOReader();

private:
    string file_address;
    static const int COLUMN_INDEX_POS_ID = 0;
    static const int COLUMN_INDEX_SETTLEMENT_ID = 1;
    static const int COLUMN_INDEX_ITEM_ID = 2;
    static const int COLUMN_INDEX_QUANTITY = 3;

};

#endif /* POS_DTO_READER_H_ */