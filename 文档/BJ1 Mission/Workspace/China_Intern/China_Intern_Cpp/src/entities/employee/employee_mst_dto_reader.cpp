#include "employee_mst_dto_reader.h"
#include "employee_mst_dto.h"
#include "../icsv_master_dto.h"
#include "../../util/utilities.h"
#include "../../constants/constants.h"
#include <string>
#include <vector>
#include <cstdlib>
using namespace std;

EmployeeDTOReader::EmployeeDTOReader() {
    this->file_address = kDefaultCsvFolder + "EMPLOYEE_MST.csv";
    AbstractDTOReader::init();
}

EmployeeDTOReader::EmployeeDTOReader(string &file_address) {
    this->file_address = file_address;
    AbstractDTOReader::init();
}

string EmployeeDTOReader::get_file_address() {
    return this->file_address;
}

ICsvMasterDTO *EmployeeDTOReader::convert_array_to_dto(vector<string> &value) {
    long employee_id = atol(value[COLUMN_INDEX_EMPLOYEE_ID].c_str());
    string employee_name = value[COLUMN_INDEX_EMPLOYEE_NAME];
    string gender = value[COLUMN_INDEX_GENDER];
    long age = atol(value[COLUMN_INDEX_AGE].c_str());
    long shop_id = atol(value[COLUMN_INDEX_SHOP_ID].c_str());
    string rank = value[COLUMN_INDEX_RANK];
    long job_type_id = atol(value[COLUMN_INDEX_JOB_TYPE_ID].c_str());

    ICsvMasterDTO *obj = new EmployeeDTO(employee_id, employee_name, gender, age, shop_id, rank, job_type_id);
    return obj;
}

EmployeeDTOReader::~EmployeeDTOReader() {

}