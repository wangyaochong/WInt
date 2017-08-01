package program.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import program.controller.entry.EntryQueryPage;
import program.entity.Employee;
import program.entity.EquipmentAsset;
import program.repository.IEquipmentAssetRepo;
import program.service.EquipmentAssetService;
import program.util.ResponseInfo;

import javax.annotation.Resource;

@RestController
@RequestMapping("/EquipmentAsset")
public class CtEquipmentAsset {
    @Resource
    IEquipmentAssetRepo equipmentAssetRepo;
    @Resource
    EquipmentAssetService equipmentAssetService;

    @RequestMapping("/queryPage" )
    public ResponseInfo queryPage(@RequestBody EntryQueryPage<EquipmentAsset> query){
        Page<EquipmentAsset> customers = equipmentAssetService.queryPage(
                query.getCondition(),
                query.getPageNum(),
                query.getPageSize(),
                query.getOrderBy(),
                query.getOrderAsc()
        );
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setData(customers);
        return responseInfo;
    }

}
