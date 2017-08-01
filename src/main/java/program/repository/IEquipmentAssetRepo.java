package program.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import program.entity.EquipmentAsset;
@RepositoryRestResource
public interface IEquipmentAssetRepo extends JpaRepository<EquipmentAsset,Long>,JpaSpecificationExecutor<EquipmentAsset> {

}
