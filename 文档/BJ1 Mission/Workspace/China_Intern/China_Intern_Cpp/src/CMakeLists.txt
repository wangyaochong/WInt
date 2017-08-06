cmake_minimum_required(VERSION 3.7)
project(src)

set(CMAKE_CXX_STANDARD 11)

set(SOURCE_FILES
        constants/constants.h
        constants/messages.h
        constants/price_unit_type.cpp
        constants/price_unit_type.h
        csv_parser/csv_constants.h
        csv_parser/csv_reader.cpp
        csv_parser/csv_reader.h
        csv_parser/csv_writer.cpp
        csv_parser/csv_writer.h
        entities/delivery/delivery_dto.cpp
        entities/delivery/delivery_dto.h
        entities/delivery/delivery_dto_reader.cpp
        entities/delivery/delivery_dto_reader.h
        entities/employee/employee_mst_dto.cpp
        entities/employee/employee_mst_dto.h
        entities/employee/employee_mst_dto_reader.cpp
        entities/employee/employee_mst_dto_reader.h
        entities/item/item_mst_dto.cpp
        entities/item/item_mst_dto.h
        entities/item/item_mst_dto_reader.cpp
        entities/item/item_mst_dto_reader.h
        entities/jobtype/job_type_mst_dto.cpp
        entities/jobtype/job_type_mst_dto.h
        entities/jobtype/job_type_mst_dto_reader.cpp
        entities/jobtype/job_type_mst_dto_reader.h
        entities/pos/pos_dto.cpp
        entities/pos/pos_dto.h
        entities/pos/pos_dto_reader.cpp
        entities/pos/pos_dto_reader.h
        entities/settlement/settlement_dto.cpp
        entities/settlement/settlement_dto.h
        entities/settlement/settlement_dto_reader.cpp
        entities/settlement/settlement_dto_reader.h
        entities/shop/shop_mst_dto.cpp
        entities/shop/shop_mst_dto.h
        entities/shop/shop_mst_dto_reader.cpp
        entities/shop/shop_mst_dto_reader.h
        entities/storage/storage_mst_dto.cpp
        entities/storage/storage_mst_dto.h
        entities/storage/storage_mst_dto_reader.cpp
        entities/storage/storage_mst_dto_reader.h
        entities/weather/weather_dto.cpp
        entities/weather/weather_dto.h
        entities/weather/weather_dto_reader.cpp
        entities/weather/weather_dto_reader.h
        entities/abstract_dto_reader.cpp
        entities/abstract_dto_reader.h
        entities/icsv_master_dto.h
        util/strptime.cpp
        util/strptime.h
        util/utilities.cpp
        util/utilities.h
        writer/i_result_writer.cpp
        writer/i_result_writer.h
        writer/i_result_writer_impl.cpp
        writer/i_result_writer_impl.h
        main.cpp)

add_executable(src ${SOURCE_FILES})