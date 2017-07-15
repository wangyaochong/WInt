package program.repository.interfaces;

import org.springframework.stereotype.Repository;

@Repository
public interface ISelfTestEntityRepo<T,ID> {
    T selfFindByName(String name);
}
