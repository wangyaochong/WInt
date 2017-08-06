#include "pos_dto.h"
#include "../../util/utilities.h"
#include <string>
using namespace std;

PosDTO::PosDTO(long pos_id, long settlement_id, long item_id, long quantity) {
    this->pos_id = pos_id;
    this->settlement_id = settlement_id;
    this->item_id = item_id;
    this->quantity = quantity;
}

long &PosDTO::get_pos_id() {
    return pos_id;
}

long &PosDTO::get_settlement_id() {
    return settlement_id;
}

long &PosDTO::get_item_id() {
    return item_id;
}

long &PosDTO::get_quantity() {
    return quantity;
}

ostream &operator <<(ostream &out, PosDTO &obj) {
    out << obj.pos_id << " " << obj.settlement_id << " " << obj.item_id << " " << obj.quantity;
    return out;
}

istream &operator >>(istream &in, PosDTO &obj) {
    in >> obj.pos_id >> obj.settlement_id >> obj.item_id >> obj.quantity;
    return in;
}