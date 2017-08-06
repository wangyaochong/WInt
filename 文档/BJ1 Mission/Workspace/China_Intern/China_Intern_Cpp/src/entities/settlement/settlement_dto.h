#ifndef SETTLEMENT_DTO_H_
#define SETTLEMENT_DTO_H_

#include <string>
#include <iostream>
#include "../icsv_master_dto.h"
using namespace std;

class SettlementDTO: public ICsvMasterDTO {
public:
    SettlementDTO(long settlement_id, struct tm date, string customer_gender, string customer_age, long shop_id, long employee_id);
    long &get_settlement_id();
    struct tm &get_date();
    string &get_customer_gender();
    string &get_customer_age();
    long &get_shop_id();
    long &get_employee_id();
    friend ostream &operator <<(ostream &out, SettlementDTO &obj);
    friend istream &operator >>(istream &in, SettlementDTO &obj);

private:
    long settlement_id;
    struct tm date;
    string customer_gender;
    string customer_age;
    long shop_id;
    long employee_id;
};

#endif /* SETTLEMENT_DTO_H_ */