#ifndef STORAGE_MST_DTO_H_
#define STORAGE_MST_DTO_H_

#include <string>
#include <iostream>
#include "../icsv_master_dto.h"
using namespace std;

class StorageDTO: public ICsvMasterDTO {
public:
    StorageDTO(long item_id, long current_quantity);
    long &get_item_id();
    long &get_current_quantity();
    friend ostream &operator <<(ostream &out, StorageDTO &obj);
    friend istream &operator >>(istream &in, StorageDTO &obj);

private:
    long item_id;
    long current_quantity;
};

#endif /* STORAGE_MST_DTO_H_ */