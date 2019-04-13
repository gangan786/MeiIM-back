package org.meizhuo.netty.chathandle;

import io.netty.channel.Channel;
import org.meizhuo.netty.DataContent;

/**
 * @Classname LastDefaultWorkerImpl
 * @Description 充当责任链的最后一个默认处理器
 * @Date 2019/4/13 14:27
 * @Created by Gangan
 */
public class LastDefaultWorkerImpl extends BaseChatWorker {



    public LastDefaultWorkerImpl(Integer msgType, BaseChatWorker nextBaseChatWorker, Channel currentChannel) {
        super(msgType, nextBaseChatWorker, currentChannel);
    }

    @Override
    public void handle(DataContent dataContent) {
        //什么都不做
        //记录产生异常的消息体
        System.out.println(dataContent);
    }

    @Override
    public void onHandle(DataContent dataContent) {

    }
}
