#include "delivery_dto.h"
#include "../../util/utilities.h"
#include <string>
using namespace std;

DeliveryDTO::DeliveryDTO(long settlement_id, string customer_name, string customer_address, string customer_tel, struct tm delivery_time) {
    this->settlement_id = settlement_id;
    this->customer_name = customer_name;
    this->customer_address = customer_address;
    this->customer_tel = customer_tel;
    this->delivery_time = delivery_time;
}

long &DeliveryDTO::get_settlement_id() {
    return settlement_id;
}

string &DeliveryDTO::get_customer_name() {
    return customer_name;
}

string &DeliveryDTO::get_customer_address() {
    return customer_address;
}

string &DeliveryDTO::get_customer_tel() {
    return customer_tel;
}

struct tm &DeliveryDTO::get_delivery_time() {
    return delivery_time;
}

ostream &operator <<(ostream &out, DeliveryDTO &obj) {
    out << obj.settlement_id << " " << obj.customer_name << " " << obj.customer_address << " " << obj.customer_tel << " " << Utilities::format_time(obj.delivery_time);
    return out;
}

istream &operator >>(istream &in, DeliveryDTO &obj) {
    in >> obj.settlement_id >> obj.customer_name >> obj.customer_address >> obj.customer_tel;    string delivery_time;    in >> delivery_time;    obj.delivery_time = Utilities::parse_date_str(delivery_time);
    return in;
}