package program.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import program.entity.WorkTimeRecord;

@RepositoryRestResource
public interface IWorkTimeRecordRepo extends JpaRepository<WorkTimeRecord,String>,JpaSpecificationExecutor<WorkTimeRecord> {

}
