#ifndef ITEM_MST_DTO_H_
#define ITEM_MST_DTO_H_

#include <string>
#include <iostream>
#include "../icsv_master_dto.h"
using namespace std;

class ItemDTO: public ICsvMasterDTO {
public:
    ItemDTO(long item_id, string item_name, long price, long cost);
    long &get_item_id();
    string &get_item_name();
    long &get_price();
    long &get_cost();
    friend ostream &operator <<(ostream &out, ItemDTO &obj);
    friend istream &operator >>(istream &in, ItemDTO &obj);

private:
    long item_id;
    string item_name;
    long price;
    long cost;
};

#endif /* ITEM_MST_DTO_H_ */