package org.meizhuo.netty.chathandle;

import io.netty.channel.Channel;
import org.meizhuo.enums.MsgActionEnum;
import org.meizhuo.netty.DataContent;

/**
 * @Classname KeepAliveWorkerImpl
 * @Description TODO
 * @Date 2019/4/11 22:12
 * @Created by Gangan
 */
public class KeepAliveWorkerImpl extends BaseChatWorker {

    private static final Integer MSG_TYPE_KEEPLIVE = MsgActionEnum.KEEPALIVE.type;

    private Channel currentChannel;

    public KeepAliveWorkerImpl(BaseChatWorker nextBaseChatWorker,Channel currentChannel) {
        super(MSG_TYPE_KEEPLIVE, nextBaseChatWorker,currentChannel);
    }

    @Override
    public void onHandle(DataContent dataContent) {
        System.out.println("收到来自channel为[" + currentChannel + "]的心跳包...");
    }
}
