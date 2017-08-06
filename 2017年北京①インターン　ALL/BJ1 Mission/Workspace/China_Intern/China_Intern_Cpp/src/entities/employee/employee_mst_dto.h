#ifndef EMPLOYEE_MST_DTO_H_
#define EMPLOYEE_MST_DTO_H_

#include <string>
#include <iostream>
#include "../icsv_master_dto.h"
using namespace std;

class EmployeeDTO: public ICsvMasterDTO {
public:
    EmployeeDTO(long employee_id, string employee_name, string gender, long age, long shop_id, string rank, long job_type_id);
    long &get_employee_id();
    string &get_employee_name();
    string &get_gender();
    long &get_age();
    long &get_shop_id();
    string &get_rank();
    long &get_job_type_id();
    friend ostream &operator <<(ostream &out, EmployeeDTO &obj);
    friend istream &operator >>(istream &in, EmployeeDTO &obj);

private:
    long employee_id;
    string employee_name;
    string gender;
    long age;
    long shop_id;
    string rank;
    long job_type_id;
};

#endif /* EMPLOYEE_MST_DTO_H_ */