package com.example.utils;

import com.example.common.exception.AmazingException;
import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;

/**
 * 快递相关工具
 */
@UtilityClass
public class ExpressUtil {

    //快递识别符号（快递号格式：快递公司&快递号
    private static final String EXP_SYMBOL = "&";

    //快递名称vs编码map
    private static Map<String, String> expMap;

    static {
        expMap = new HashMap<>();
        expMap.put("顺丰","SF");
        expMap.put("百世","HTKY");
        expMap.put("中通","ZTO");
        expMap.put("申通","STO");
        expMap.put("圆通","YTO");
        expMap.put("韵达","YD");
        expMap.put("邮政","YZPY");
        expMap.put("EMS","EMS");
        expMap.put("天天","HHTT");
        expMap.put("京东","JD");
    }
    /**
     * 获取快递公司编码
     * @param expName 快递公司名称
     */
    public String getExpCode(String expName) {
        for (String expKey : expMap.keySet()) {
            if (expName.contains(expKey)){
                return expMap.get(expKey);
            }
        }
        throw new AmazingException("快递公司无法识别");
    }

    /**
     * 验证快递号
     * @param trackingNumber 快递号
     */
    public String[] validTrackingNumber(String trackingNumber) {
        String[] result = trackingNumber.split(EXP_SYMBOL);
        if (result.length != 2){
            throw new AmazingException("快递号格式有误");
        }
        return result;
    }



}
