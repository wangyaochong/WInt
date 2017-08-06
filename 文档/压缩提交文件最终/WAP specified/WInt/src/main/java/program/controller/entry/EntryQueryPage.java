package program.controller.entry;

import lombok.Data;

@Data
public class EntryQueryPage<T> {
    T condition;
    Integer pageNum;
    Integer pageSize;
    String orderBy;
    Boolean orderAsc;
}
