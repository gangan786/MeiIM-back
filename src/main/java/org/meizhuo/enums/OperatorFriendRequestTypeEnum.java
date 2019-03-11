package org.meizhuo.enums;

/**
 * @ProjectName: meiim
 * @Package: org.meizhuo.enums
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: Gangan
 * @CreateDate: 2019/3/11 10:47
 * @UpdateUser:
 * @UpdateDate: 2019/3/11 10:47
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public enum OperatorFriendRequestTypeEnum {

    /**
     * 表示忽略好友请求
     */
    IGNORE(0, "忽略"),
    /**
     * 表示通过好友请求
     */
    PASS(1, "通过");

    public final Integer type;
    public final String msg;

    OperatorFriendRequestTypeEnum(Integer type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    public Integer getType() {
        return type;
    }

    public static String getMsgByType(Integer type) {
        for (OperatorFriendRequestTypeEnum operType : OperatorFriendRequestTypeEnum.values()) {
            if (operType.getType().equals(type)) {
                return operType.msg;
            }
        }
        return null;
    }

}
