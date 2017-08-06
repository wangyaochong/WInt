#ifndef JOB_TYPE_MST_DTO_READER_H_
#define JOB_TYPE_MST_DTO_READER_H_

#include "../abstract_dto_reader.h"
#include "job_type_mst_dto.h"
#include "../icsv_master_dto.h"

class JobTypeDTOReader: public AbstractDTOReader {
public:
    JobTypeDTOReader();
    JobTypeDTOReader(string &file_address);
    ICsvMasterDTO *convert_array_to_dto(vector<string> &value);
    string get_file_address();
    ~JobTypeDTOReader();

private:
    string file_address;
    static const int COLUMN_INDEX_JOB_TYPE_ID = 0;
    static const int COLUMN_INDEX_JOB_TYPE_NAME = 1;
    static const int COLUMN_INDEX_CAN_DO_CASHER = 2;
    static const int COLUMN_INDEX_CAN_DO_DELIVERY = 3;
    static const int COLUMN_INDEX_CAN_DO_COOK = 4;

};

#endif /* JOB_TYPE_MST_DTO_READER_H_ */