package org.meizhuo.enums;

/**
 * @ProjectName: meiim
 * @Package: org.meizhuo.enums
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: Gangan
 * @CreateDate: 2019/3/10 19:51
 * @UpdateUser:
 * @UpdateDate: 2019/3/10 19:51
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public enum  SearchFriendsStateEnum {

    /**
     * 查找好友的状态码与对应信息
     */
    SUCCESS(0, "OK"),
    USER_NOT_EXIST(1, "无此用户..."),
    NOT_YOURSELF(2, "不能添加你自己..."),
    ALREADY_FRIENDS(3, "该用户已经是你的好友...");


    public final Integer status;
    public final String msg;

    SearchFriendsStateEnum(Integer status, String msg){
        this.status = status;
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public static String getMsgByKey(Integer status) {
        for (SearchFriendsStateEnum type : SearchFriendsStateEnum.values()) {
            if (type.getStatus().equals(status)) {
                return type.msg;
            }
        }
        return null;
    }

}
