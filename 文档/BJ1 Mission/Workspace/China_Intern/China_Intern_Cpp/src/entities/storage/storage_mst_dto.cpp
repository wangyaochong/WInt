#include "storage_mst_dto.h"
#include "../../util/utilities.h"
#include <string>
using namespace std;

StorageDTO::StorageDTO(long item_id, long current_quantity) {
    this->item_id = item_id;
    this->current_quantity = current_quantity;
}

long &StorageDTO::get_item_id() {
    return item_id;
}

long &StorageDTO::get_current_quantity() {
    return current_quantity;
}

ostream &operator <<(ostream &out, StorageDTO &obj) {
    out << obj.item_id << " " << obj.current_quantity;
    return out;
}

istream &operator >>(istream &in, StorageDTO &obj) {
    in >> obj.item_id >> obj.current_quantity;
    return in;
}