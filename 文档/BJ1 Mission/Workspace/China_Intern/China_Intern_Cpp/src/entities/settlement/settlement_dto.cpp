#include "settlement_dto.h"
#include "../../util/utilities.h"
#include <string>
using namespace std;

SettlementDTO::SettlementDTO(long settlement_id, struct tm date, string customer_gender, string customer_age, long shop_id, long employee_id) {
    this->settlement_id = settlement_id;
    this->date = date;
    this->customer_gender = customer_gender;
    this->customer_age = customer_age;
    this->shop_id = shop_id;
    this->employee_id = employee_id;
}

long &SettlementDTO::get_settlement_id() {
    return settlement_id;
}

struct tm &SettlementDTO::get_date() {
    return date;
}

string &SettlementDTO::get_customer_gender() {
    return customer_gender;
}

string &SettlementDTO::get_customer_age() {
    return customer_age;
}

long &SettlementDTO::get_shop_id() {
    return shop_id;
}

long &SettlementDTO::get_employee_id() {
    return employee_id;
}

ostream &operator <<(ostream &out, SettlementDTO &obj) {
    out << obj.settlement_id << " " << Utilities::format_time(obj.date) << " " << obj.customer_gender << " " << obj.customer_age << " " << obj.shop_id << " " << obj.employee_id;
    return out;
}

istream &operator >>(istream &in, SettlementDTO &obj) {
    in >> obj.settlement_id;    string date;    in >> date;    obj.date = Utilities::parse_date_str(date);    in >> obj.customer_gender >> obj.customer_age >> obj.shop_id >> obj.employee_id;
    return in;
}