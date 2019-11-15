package com.hxzy.common.validator;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义entity参数验证类
 */
@Getter
@Setter
public class ValidatorResult {
    //校验结果是否有错
    private boolean hasErrors = false;

    //存放错误信息的map
    private Map<String,String> erroMsgMap = new HashMap<String,String>();

    /**
     * 通过格式化字符串信息后去错误结果的msg方法
     * @return
     */
    public String getErrMsg(){
        return StringUtils.join(erroMsgMap.values().toArray(),",");
    }
}
