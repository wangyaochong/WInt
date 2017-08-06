#include "weather_dto.h"
#include "../../util/utilities.h"
#include <string>
using namespace std;

WeatherDTO::WeatherDTO(long shop_id, struct tm date, long rainfall_amount, double temperature, long humidity) {
    this->shop_id = shop_id;
    this->date = date;
    this->rainfall_amount = rainfall_amount;
    this->temperature = temperature;
    this->humidity = humidity;
}

long &WeatherDTO::get_shop_id() {
    return shop_id;
}

struct tm &WeatherDTO::get_date() {
    return date;
}

long &WeatherDTO::get_rainfall_amount() {
    return rainfall_amount;
}

double &WeatherDTO::get_temperature() {
    return temperature;
}

long &WeatherDTO::get_humidity() {
    return humidity;
}

ostream &operator <<(ostream &out, WeatherDTO &obj) {
    out << obj.shop_id << " " << Utilities::format_time(obj.date) << " " << obj.rainfall_amount << " " << obj.temperature << " " << obj.humidity;
    return out;
}

istream &operator >>(istream &in, WeatherDTO &obj) {
    in >> obj.shop_id;    string date;    in >> date;    obj.date = Utilities::parse_date_str(date);    in >> obj.rainfall_amount >> obj.temperature >> obj.humidity;
    return in;
}