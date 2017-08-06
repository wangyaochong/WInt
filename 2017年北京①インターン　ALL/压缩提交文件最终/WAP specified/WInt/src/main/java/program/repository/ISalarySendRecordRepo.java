package program.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import program.entity.SalarySendRecord;

@RepositoryRestResource
public interface ISalarySendRecordRepo extends JpaRepository<SalarySendRecord,String>,JpaSpecificationExecutor<SalarySendRecord> {
}
