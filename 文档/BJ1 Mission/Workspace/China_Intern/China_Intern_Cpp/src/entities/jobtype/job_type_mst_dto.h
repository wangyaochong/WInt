#ifndef JOB_TYPE_MST_DTO_H_
#define JOB_TYPE_MST_DTO_H_

#include <string>
#include <iostream>
#include "../icsv_master_dto.h"
using namespace std;

class JobTypeDTO: public ICsvMasterDTO {
public:
    JobTypeDTO(long job_type_id, string job_type_name, long can_do_casher, long can_do_delivery, long can_do_cook);
    long &get_job_type_id();
    string &get_job_type_name();
    long &get_can_do_casher();
    long &get_can_do_delivery();
    long &get_can_do_cook();
    friend ostream &operator <<(ostream &out, JobTypeDTO &obj);
    friend istream &operator >>(istream &in, JobTypeDTO &obj);

private:
    long job_type_id;
    string job_type_name;
    long can_do_casher;
    long can_do_delivery;
    long can_do_cook;
};

#endif /* JOB_TYPE_MST_DTO_H_ */