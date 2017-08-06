#include "shop_mst_dto.h"
#include "../../util/utilities.h"
#include <string>
using namespace std;

ShopDTO::ShopDTO(long shop_id, string shop_name, string open_time, string close_time, long number_of_seats, long number_of_employees, string area) {
    this->shop_id = shop_id;
    this->shop_name = shop_name;
    this->open_time = open_time;
    this->close_time = close_time;
    this->number_of_seats = number_of_seats;
    this->number_of_employees = number_of_employees;
    this->area = area;
}

long &ShopDTO::get_shop_id() {
    return shop_id;
}

string &ShopDTO::get_shop_name() {
    return shop_name;
}

string &ShopDTO::get_open_time() {
    return open_time;
}

string &ShopDTO::get_close_time() {
    return close_time;
}

long &ShopDTO::get_number_of_seats() {
    return number_of_seats;
}

long &ShopDTO::get_number_of_employees() {
    return number_of_employees;
}

string &ShopDTO::get_area() {
    return area;
}

ostream &operator <<(ostream &out, ShopDTO &obj) {
    out << obj.shop_id << " " << obj.shop_name << " " << obj.open_time << " " << obj.close_time << " " << obj.number_of_seats << " " << obj.number_of_employees << " " << obj.area;
    return out;
}

istream &operator >>(istream &in, ShopDTO &obj) {
    in >> obj.shop_id >> obj.shop_name >> obj.open_time >> obj.close_time >> obj.number_of_seats >> obj.number_of_employees >> obj.area;
    return in;
}