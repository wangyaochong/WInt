#ifndef SETTLEMENT_DTO_READER_H_
#define SETTLEMENT_DTO_READER_H_

#include "../abstract_dto_reader.h"
#include "settlement_dto.h"
#include "../icsv_master_dto.h"

class SettlementDTOReader: public AbstractDTOReader {
public:
    SettlementDTOReader();
    SettlementDTOReader(string &file_address);
    ICsvMasterDTO *convert_array_to_dto(vector<string> &value);
    string get_file_address();
    ~SettlementDTOReader();

private:
    string file_address;
    static const int COLUMN_INDEX_SETTLEMENT_ID = 0;
    static const int COLUMN_INDEX_DATE = 1;
    static const int COLUMN_INDEX_CUSTOMER_GENDER = 2;
    static const int COLUMN_INDEX_CUSTOMER_AGE = 3;
    static const int COLUMN_INDEX_SHOP_ID = 4;
    static const int COLUMN_INDEX_EMPLOYEE_ID = 5;

};

#endif /* SETTLEMENT_DTO_READER_H_ */