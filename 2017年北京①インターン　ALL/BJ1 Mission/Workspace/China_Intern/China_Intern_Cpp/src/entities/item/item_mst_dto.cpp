#include "item_mst_dto.h"
#include "../../util/utilities.h"
#include <string>
using namespace std;

ItemDTO::ItemDTO(long item_id, string item_name, long price, long cost) {
    this->item_id = item_id;
    this->item_name = item_name;
    this->price = price;
    this->cost = cost;
}

long &ItemDTO::get_item_id() {
    return item_id;
}

string &ItemDTO::get_item_name() {
    return item_name;
}

long &ItemDTO::get_price() {
    return price;
}

long &ItemDTO::get_cost() {
    return cost;
}

ostream &operator <<(ostream &out, ItemDTO &obj) {
    out << obj.item_id << " " << obj.item_name << " " << obj.price << " " << obj.cost;
    return out;
}

istream &operator >>(istream &in, ItemDTO &obj) {
    in >> obj.item_id >> obj.item_name >> obj.price >> obj.cost;
    return in;
}