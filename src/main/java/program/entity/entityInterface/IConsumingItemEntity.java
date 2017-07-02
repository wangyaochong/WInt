package program.entity.entityInterface;

/**
 * Created by【王耀冲】on 【2017/6/30】 at 【17:57】.
 */
public interface IConsumingItemEntity {
    //记录消耗品
    Long getInDate();//入库或者被创建的日期
    void setInDate(Long date);
    Long getOutDate();//出库或者被消耗的日期
    void setOutDate(Long date);
}
