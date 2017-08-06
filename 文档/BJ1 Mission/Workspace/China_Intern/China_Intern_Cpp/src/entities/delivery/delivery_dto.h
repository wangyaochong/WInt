#ifndef DELIVERY_DTO_H_
#define DELIVERY_DTO_H_

#include <string>
#include <iostream>
#include "../icsv_master_dto.h"
using namespace std;

class DeliveryDTO: public ICsvMasterDTO {
public:
    DeliveryDTO(long settlement_id, string customer_name, string customer_address, string customer_tel, struct tm delivery_time);
    long &get_settlement_id();
    string &get_customer_name();
    string &get_customer_address();
    string &get_customer_tel();
    struct tm &get_delivery_time();
    friend ostream &operator <<(ostream &out, DeliveryDTO &obj);
    friend istream &operator >>(istream &in, DeliveryDTO &obj);

private:
    long settlement_id;
    string customer_name;
    string customer_address;
    string customer_tel;
    struct tm delivery_time;
};

#endif /* DELIVERY_DTO_H_ */