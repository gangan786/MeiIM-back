package org.meizhuo.pojo.vo;

/**
 * @ProjectName: meiim
 * @Package: org.meizhuo.pojo.vo
 * @ClassName: ${TYPE_NAME}
 * @Description: 好友请求发送方的信息
 * @Author: Gangan
 * @CreateDate: 2019/3/10 22:02
 * @UpdateUser:
 * @UpdateDate: 2019/3/10 22:02
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class FriendRequestVO {

    private String sendUserId;

    private String sendUsername;

    private String sendFaceImage;

    private String sendNickname;

    public String getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(String sendUserId) {
        this.sendUserId = sendUserId;
    }

    public String getSendUsername() {
        return sendUsername;
    }

    public void setSendUsername(String sendUsername) {
        this.sendUsername = sendUsername;
    }

    public String getSendFaceImage() {
        return sendFaceImage;
    }

    public void setSendFaceImage(String sendFaceImage) {
        this.sendFaceImage = sendFaceImage;
    }

    public String getSendNickname() {
        return sendNickname;
    }

    public void setSendNickname(String sendNickname) {
        this.sendNickname = sendNickname;
    }
}
