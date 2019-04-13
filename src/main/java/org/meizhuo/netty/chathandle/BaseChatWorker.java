package org.meizhuo.netty.chathandle;

import io.netty.channel.Channel;
import org.meizhuo.netty.DataContent;

/**
 * @Classname BaseChatWorker
 * @Description TODO
 * @Date 2019/4/11 21:26
 * @Created by Gangan
 */
public abstract class BaseChatWorker {

    protected final Integer msgType;

    private BaseChatWorker nextBaseChatWorker;

    protected Channel currentChannel;

    public BaseChatWorker(Integer msgType, BaseChatWorker nextBaseChatWorker,Channel currentChannel) {
        this.msgType = msgType;
        this.nextBaseChatWorker = nextBaseChatWorker;
        this.currentChannel=currentChannel;
    }

    /**
     * 暴露给用户调用的接口
     * @param dataContent
     */
    public void handle(DataContent dataContent) {

        if (dataContent != null) {
            if (dataContent.getAction().equals(msgType)) {
                onHandle(dataContent);
            } else {
                nextBaseChatWorker.handle(dataContent);
            }
        }

    }

    /**
     * 交由用户实现真正的逻辑处理
     * @param dataContent
     */
    public abstract void onHandle(DataContent dataContent);


}
