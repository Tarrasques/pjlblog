package com.pengjl.utils;

/**
 * 各种验证
 */
public class CheckUtil {

    /**
     * 输入的必须是大于零的整数
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        if (str==null){
          return false;
        }
        else {
            if((str.matches("[0-9]+"))&&(Long.parseLong(str)>0)) {
                return true;
            }
        }
        return false;
    }

}
