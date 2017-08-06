#include "weather_dto_reader.h"
#include "weather_dto.h"
#include "../icsv_master_dto.h"
#include "../../util/utilities.h"
#include "../../constants/constants.h"
#include <string>
#include <vector>
#include <cstdlib>
using namespace std;

WeatherDTOReader::WeatherDTOReader() {
    this->file_address = kDefaultCsvFolder + "WEATHER.csv";
    AbstractDTOReader::init();
}

WeatherDTOReader::WeatherDTOReader(string &file_address) {
    this->file_address = file_address;
    AbstractDTOReader::init();
}

string WeatherDTOReader::get_file_address() {
    return this->file_address;
}

ICsvMasterDTO *WeatherDTOReader::convert_array_to_dto(vector<string> &value) {
    long shop_id = atol(value[COLUMN_INDEX_SHOP_ID].c_str());
    struct tm date = Utilities::parse_date_str(value[COLUMN_INDEX_DATE]);
    long rainfall_amount = atol(value[COLUMN_INDEX_RAINFALL_AMOUNT].c_str());
    double temperature = atol(value[COLUMN_INDEX_TEMPERATURE].c_str());
    long humidity = atol(value[COLUMN_INDEX_HUMIDITY].c_str());

    ICsvMasterDTO *obj = new WeatherDTO(shop_id, date, rainfall_amount, temperature, humidity);
    return obj;
}

WeatherDTOReader::~WeatherDTOReader() {

}