package org.meizhuo.utils;

/**
 * @ProjectName: meiim
 * @Package: org.meizhuo.utils
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: Gangan
 * @CreateDate: 2019/3/14 14:37
 * @UpdateUser:
 * @UpdateDate: 2019/3/14 14:37
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class PrintUtils {

    private static final String ON = "on";

    private static final String OFF = "off";

    private static String CURRENT_STATE = ON;


    public static void logToConsole(String msg) {
        if (CURRENT_STATE.equals(ON)) {
            System.out.println(msg);
        } else if (CURRENT_STATE.equals(OFF)) {

        } else {

        }
    }

}
