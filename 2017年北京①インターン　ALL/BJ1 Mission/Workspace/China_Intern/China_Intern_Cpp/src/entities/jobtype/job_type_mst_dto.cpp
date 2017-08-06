#include "job_type_mst_dto.h"
#include "../../util/utilities.h"
#include <string>
using namespace std;

JobTypeDTO::JobTypeDTO(long job_type_id, string job_type_name, long can_do_casher, long can_do_delivery, long can_do_cook) {
    this->job_type_id = job_type_id;
    this->job_type_name = job_type_name;
    this->can_do_casher = can_do_casher;
    this->can_do_delivery = can_do_delivery;
    this->can_do_cook = can_do_cook;
}

long &JobTypeDTO::get_job_type_id() {
    return job_type_id;
}

string &JobTypeDTO::get_job_type_name() {
    return job_type_name;
}

long &JobTypeDTO::get_can_do_casher() {
    return can_do_casher;
}

long &JobTypeDTO::get_can_do_delivery() {
    return can_do_delivery;
}

long &JobTypeDTO::get_can_do_cook() {
    return can_do_cook;
}

ostream &operator <<(ostream &out, JobTypeDTO &obj) {
    out << obj.job_type_id << " " << obj.job_type_name << " " << obj.can_do_casher << " " << obj.can_do_delivery << " " << obj.can_do_cook;
    return out;
}

istream &operator >>(istream &in, JobTypeDTO &obj) {
    in >> obj.job_type_id >> obj.job_type_name >> obj.can_do_casher >> obj.can_do_delivery >> obj.can_do_cook;
    return in;
}