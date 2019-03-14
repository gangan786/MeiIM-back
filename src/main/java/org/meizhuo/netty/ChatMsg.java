package org.meizhuo.netty;

import java.io.Serializable;

/**
 * @ProjectName: meiim
 * @Package: org.meizhuo.netty
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: Gangan
 * @CreateDate: 2019/3/12 15:59
 * @UpdateUser:
 * @UpdateDate: 2019/3/12 15:59
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class ChatMsg implements Serializable {

    private static final long serialVersionUID = 3536913347608643783L;

    /**
     * 发送者id
     */
    private String senderId;
    /**
     * 接受者id
     */
    private String receiverId;
    /**
     * 聊天内容
     */
    private String msg;
    /**
     * 用于消息的签收
     */
    private String msgId;

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
}
