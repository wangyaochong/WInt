#ifndef DELIVERY_DTO_READER_H_
#define DELIVERY_DTO_READER_H_

#include "../abstract_dto_reader.h"
#include "delivery_dto.h"
#include "../icsv_master_dto.h"

class DeliveryDTOReader: public AbstractDTOReader {
public:
    DeliveryDTOReader();
    DeliveryDTOReader(string &file_address);
    ICsvMasterDTO *convert_array_to_dto(vector<string> &value);
    string get_file_address();
    ~DeliveryDTOReader();

private:
    string file_address;
    static const int COLUMN_INDEX_SETTLEMENT_ID = 0;
    static const int COLUMN_INDEX_CUSTOMER_NAME = 1;
    static const int COLUMN_INDEX_CUSTOMER_ADDRESS = 2;
    static const int COLUMN_INDEX_CUSTOMER_TEL = 3;
    static const int COLUMN_INDEX_DELIVERY_TIME = 4;

};

#endif /* DELIVERY_DTO_READER_H_ */