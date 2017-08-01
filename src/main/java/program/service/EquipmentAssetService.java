package program.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import program.entity.EquipmentAsset;
import program.repository.IEquipmentAssetRepo;

@Service
public class EquipmentAssetService extends GenericService<IEquipmentAssetRepo,EquipmentAsset> {
    @Autowired
    public EquipmentAssetService(IEquipmentAssetRepo repo){
        this.setRepo(repo);
    }
}
