package org.meizhuo.netty;


import java.io.Serializable;

/**
 * @ProjectName: meiim
 * @Package: org.meizhuo.netty
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: Gangan
 * @CreateDate: 2019/3/12 15:46
 * @UpdateUser:
 * @UpdateDate: 2019/3/12 15:46
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class DataContent implements Serializable {


    private static final long serialVersionUID = -3700550998837532412L;

    /**
     * 动作类型
     */
    private Integer action;
    /**
     * 用户的聊天内容
     */
    private ChatMsg chatMsg;
    /**
     * 拓展字段
     */
    private String extand;

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public ChatMsg getChatMsg() {
        return chatMsg;
    }

    public void setChatMsg(ChatMsg chatMsg) {
        this.chatMsg = chatMsg;
    }

    public String getExtand() {
        return extand;
    }

    public void setExtand(String extand) {
        this.extand = extand;
    }
}
