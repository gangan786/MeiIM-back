package org.meizhuo.netty.chathandle;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.meizhuo.SpringUtil;
import org.meizhuo.enums.MsgActionEnum;
import org.meizhuo.netty.ChatMsg;
import org.meizhuo.netty.DataContent;
import org.meizhuo.netty.UserChannelRel;
import org.meizhuo.server.UserService;
import org.meizhuo.utils.JsonUtils;

/**
 * @Classname ChatWorkerImpl
 * @Description TODO
 * @Date 2019/4/12 21:52
 * @Created by Gangan
 */
public class ChatWorkerImpl extends BaseChatWorker {

    private static final Integer MSG_TYPE_CHAT = MsgActionEnum.CHAT.type;

    private final ChannelGroup user;

    public ChatWorkerImpl(BaseChatWorker nextBaseChatWorker,
                          Channel currentChannel,
                          ChannelGroup channelGroup) {
        super(MSG_TYPE_CHAT, nextBaseChatWorker, currentChannel);
        this.user = channelGroup;
    }

    @Override
    public void onHandle(DataContent dataContent) {
        //获取聊天内容
        ChatMsg chatMsg = dataContent.getChatMsg();
        //获取接受者身份id
        String receiverId = chatMsg.getReceiverId();

        //保存消息到数据库，并且标记为   未签收
        UserService userService = (UserService) SpringUtil.getBean("userServiceImpl");
        String msgId = userService.saveMsg(chatMsg);
        chatMsg.setMsgId(msgId);

        //构建消息体
        DataContent content = new DataContent();
        content.setChatMsg(chatMsg);

        //发送消息
        //从全局用户Channel关系中获取接受方的Channel
        Channel receiverChannel = UserChannelRel.get(receiverId);
        if (receiverChannel != null) {
            Channel effectiveChannel = user.find(receiverChannel.id());
            if (effectiveChannel != null) {
                //获取到真正有效的Channel
                effectiveChannel.writeAndFlush(
                        new TextWebSocketFrame(JsonUtils.objectToJson(content))
                );
            }else {
                //用户已断开连接
            }
        } else {
            //表示用户离线或者用户不存在
        }
    }
}
