#ifndef POS_DTO_H_
#define POS_DTO_H_

#include <string>
#include <iostream>
#include "../icsv_master_dto.h"
using namespace std;

class PosDTO: public ICsvMasterDTO {
public:
    PosDTO(long pos_id, long settlement_id, long item_id, long quantity);
    long &get_pos_id();
    long &get_settlement_id();
    long &get_item_id();
    long &get_quantity();
    friend ostream &operator <<(ostream &out, PosDTO &obj);
    friend istream &operator >>(istream &in, PosDTO &obj);

private:
    long pos_id;
    long settlement_id;
    long item_id;
    long quantity;
};

#endif /* POS_DTO_H_ */