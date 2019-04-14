package org.meizhuo.netty.chathandle;

import io.netty.channel.Channel;
import org.meizhuo.enums.MsgActionEnum;
import org.meizhuo.netty.DataContent;
import org.meizhuo.netty.UserChannelRel;

/**
 * @Classname ConnectWorkerImpl
 * @Description 当获取到连接请求的时候
 *              将UserID与关联的Channel对应起来保存
 * @Date 2019/4/12 21:40
 * @Created by Gangan
 */
public class ConnectWorkerImpl extends BaseChatWorker {

    private static final Integer MSG_TYPE_CONNECT = MsgActionEnum.CONNECT.type;


    public ConnectWorkerImpl(BaseChatWorker nextBaseChatWorker, Channel currentChannel) {
        super(MSG_TYPE_CONNECT, nextBaseChatWorker,currentChannel);
    }

    @Override
    public void onHandle(DataContent dataContent) {
        //获取发送者身份ID
        String senderId = dataContent.getChatMsg().getSenderId();
        UserChannelRel.put(senderId, currentChannel);
    }
}
