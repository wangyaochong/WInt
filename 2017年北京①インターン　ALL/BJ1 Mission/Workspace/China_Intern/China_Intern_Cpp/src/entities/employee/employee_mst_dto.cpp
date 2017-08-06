#include "employee_mst_dto.h"
#include "../../util/utilities.h"
#include <string>
using namespace std;

EmployeeDTO::EmployeeDTO(long employee_id, string employee_name, string gender, long age, long shop_id, string rank, long job_type_id) {
    this->employee_id = employee_id;
    this->employee_name = employee_name;
    this->gender = gender;
    this->age = age;
    this->shop_id = shop_id;
    this->rank = rank;
    this->job_type_id = job_type_id;
}

long &EmployeeDTO::get_employee_id() {
    return employee_id;
}

string &EmployeeDTO::get_employee_name() {
    return employee_name;
}

string &EmployeeDTO::get_gender() {
    return gender;
}

long &EmployeeDTO::get_age() {
    return age;
}

long &EmployeeDTO::get_shop_id() {
    return shop_id;
}

string &EmployeeDTO::get_rank() {
    return rank;
}

long &EmployeeDTO::get_job_type_id() {
    return job_type_id;
}

ostream &operator <<(ostream &out, EmployeeDTO &obj) {
    out << obj.employee_id << " " << obj.employee_name << " " << obj.gender << " " << obj.age << " " << obj.shop_id << " " << obj.rank << " " << obj.job_type_id;
    return out;
}

istream &operator >>(istream &in, EmployeeDTO &obj) {
    in >> obj.employee_id >> obj.employee_name >> obj.gender >> obj.age >> obj.shop_id >> obj.rank >> obj.job_type_id;
    return in;
}