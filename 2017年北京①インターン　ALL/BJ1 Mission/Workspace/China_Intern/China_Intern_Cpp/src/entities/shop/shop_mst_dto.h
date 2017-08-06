#ifndef SHOP_MST_DTO_H_
#define SHOP_MST_DTO_H_

#include <string>
#include <iostream>
#include "../icsv_master_dto.h"
using namespace std;

class ShopDTO: public ICsvMasterDTO {
public:
    ShopDTO(long shop_id, string shop_name, string open_time, string close_time, long number_of_seats, long number_of_employees, string area);
    long &get_shop_id();
    string &get_shop_name();
    string &get_open_time();
    string &get_close_time();
    long &get_number_of_seats();
    long &get_number_of_employees();
    string &get_area();
    friend ostream &operator <<(ostream &out, ShopDTO &obj);
    friend istream &operator >>(istream &in, ShopDTO &obj);

private:
    long shop_id;
    string shop_name;
    string open_time;
    string close_time;
    long number_of_seats;
    long number_of_employees;
    string area;
};

#endif /* SHOP_MST_DTO_H_ */