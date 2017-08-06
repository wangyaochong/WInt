#ifndef EMPLOYEE_MST_DTO_READER_H_
#define EMPLOYEE_MST_DTO_READER_H_

#include "../abstract_dto_reader.h"
#include "employee_mst_dto.h"
#include "../icsv_master_dto.h"

class EmployeeDTOReader: public AbstractDTOReader {
public:
    EmployeeDTOReader();
    EmployeeDTOReader(string &file_address);
    ICsvMasterDTO *convert_array_to_dto(vector<string> &value);
    string get_file_address();
    ~EmployeeDTOReader();

private:
    string file_address;
    static const int COLUMN_INDEX_EMPLOYEE_ID = 0;
    static const int COLUMN_INDEX_EMPLOYEE_NAME = 1;
    static const int COLUMN_INDEX_GENDER = 2;
    static const int COLUMN_INDEX_AGE = 3;
    static const int COLUMN_INDEX_SHOP_ID = 4;
    static const int COLUMN_INDEX_RANK = 5;
    static const int COLUMN_INDEX_JOB_TYPE_ID = 6;

};

#endif /* EMPLOYEE_MST_DTO_READER_H_ */