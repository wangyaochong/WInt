#include "job_type_mst_dto_reader.h"
#include "job_type_mst_dto.h"
#include "../icsv_master_dto.h"
#include "../../util/utilities.h"
#include "../../constants/constants.h"
#include <string>
#include <vector>
#include <cstdlib>
using namespace std;

JobTypeDTOReader::JobTypeDTOReader() {
    this->file_address = kDefaultCsvFolder + "JOB_TYPE_MST.csv";
    AbstractDTOReader::init();
}

JobTypeDTOReader::JobTypeDTOReader(string &file_address) {
    this->file_address = file_address;
    AbstractDTOReader::init();
}

string JobTypeDTOReader::get_file_address() {
    return this->file_address;
}

ICsvMasterDTO *JobTypeDTOReader::convert_array_to_dto(vector<string> &value) {
    long job_type_id = atol(value[COLUMN_INDEX_JOB_TYPE_ID].c_str());
    string job_type_name = value[COLUMN_INDEX_JOB_TYPE_NAME];
    long can_do_casher = atol(value[COLUMN_INDEX_CAN_DO_CASHER].c_str());
    long can_do_delivery = atol(value[COLUMN_INDEX_CAN_DO_DELIVERY].c_str());
    long can_do_cook = atol(value[COLUMN_INDEX_CAN_DO_COOK].c_str());

    ICsvMasterDTO *obj = new JobTypeDTO(job_type_id, job_type_name, can_do_casher, can_do_delivery, can_do_cook);
    return obj;
}

JobTypeDTOReader::~JobTypeDTOReader() {

}