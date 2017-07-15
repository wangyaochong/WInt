package program.util;

import lombok.Builder;
import lombok.Data;

/**
 * Created by【王耀冲】on 【2016/12/15】 at 【3:02】.
 */
@Data
@Builder
public class ResponseInfo {
    public static final String statusOk="ok";
    public static final String statusBad="bad";
    String statusFlag=statusOk;
    String message="";
    Object data=null;
    public ResponseInfo(){

    }
    public ResponseInfo(String statusFlag, String message, Object data){
        this.statusFlag = statusFlag;
        this.message=message;
        this.data=data;
    }
}
