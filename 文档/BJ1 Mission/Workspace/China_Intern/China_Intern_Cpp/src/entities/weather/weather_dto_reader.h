#ifndef WEATHER_DTO_READER_H_
#define WEATHER_DTO_READER_H_

#include "../abstract_dto_reader.h"
#include "weather_dto.h"
#include "../icsv_master_dto.h"

class WeatherDTOReader: public AbstractDTOReader {
public:
    WeatherDTOReader();
    WeatherDTOReader(string &file_address);
    ICsvMasterDTO *convert_array_to_dto(vector<string> &value);
    string get_file_address();
    ~WeatherDTOReader();

private:
    string file_address;
    static const int COLUMN_INDEX_SHOP_ID = 0;
    static const int COLUMN_INDEX_DATE = 1;
    static const int COLUMN_INDEX_RAINFALL_AMOUNT = 2;
    static const int COLUMN_INDEX_TEMPERATURE = 3;
    static const int COLUMN_INDEX_HUMIDITY = 4;

};

#endif /* WEATHER_DTO_READER_H_ */