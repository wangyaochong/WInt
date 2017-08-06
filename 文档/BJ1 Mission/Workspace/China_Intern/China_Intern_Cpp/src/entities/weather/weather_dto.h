#ifndef WEATHER_DTO_H_
#define WEATHER_DTO_H_

#include <string>
#include <iostream>
#include "../icsv_master_dto.h"
using namespace std;

class WeatherDTO: public ICsvMasterDTO {
public:
    WeatherDTO(long shop_id, struct tm date, long rainfall_amount, double temperature, long humidity);
    long &get_shop_id();
    struct tm &get_date();
    long &get_rainfall_amount();
    double &get_temperature();
    long &get_humidity();
    friend ostream &operator <<(ostream &out, WeatherDTO &obj);
    friend istream &operator >>(istream &in, WeatherDTO &obj);

private:
    long shop_id;
    struct tm date;
    long rainfall_amount;
    double temperature;
    long humidity;
};

#endif /* WEATHER_DTO_H_ */