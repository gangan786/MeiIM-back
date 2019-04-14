package org.meizhuo.netty.chathandle;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.meizhuo.netty.DataContent;
import org.meizhuo.utils.JsonUtils;
import org.meizhuo.utils.PrintUtils;

/**
 * @ProjectName: meiim
 * @Package: org.meizhuo.netty
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: Gangan
 * @CreateDate: 2019/3/4 22:01
 * @UpdateUser:
 * @UpdateDate: 2019/3/4 22:01
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    /**
     * 用于记录和管理所有客户端的Channel
     */
    public static ChannelGroup users =
            new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {

        // 获取客户端传输过来的消息
        String content = msg.text();

        Channel currentChannel = ctx.channel();

        // 1. 获取客户端发来的消息
        DataContent dataContent = JsonUtils.jsonToPojo(content, DataContent.class);
//        Integer action = dataContent.getAction();
        // 2. 判断消息类型，根据不同的类型来处理不同的业务

        //构建处理链，从下往上依次构建
        BaseChatWorker lastDefaultWorker = new LastDefaultWorkerImpl(null, null, null);
        BaseChatWorker keepAliveWorker = new KeepAliveWorkerImpl(lastDefaultWorker, currentChannel);
        BaseChatWorker signedWorker = new SignedWorkerImpl(keepAliveWorker, currentChannel);
        ChatWorkerImpl chatWorker = new ChatWorkerImpl(signedWorker, currentChannel, users);
        BaseChatWorker connectWorker = new ConnectWorkerImpl(chatWorker, currentChannel);

        //从头节点开始处理消息
        connectWorker.handle(dataContent);


    }

    /**
     * 客户端连接后，把相应的Channel收集管理
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        users.add(ctx.channel());
    }

    /**
     * 这里有个逻辑：移掉handle就视为客户端失去连接，自然的就移除掉对应的Channel
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        PrintUtils.logToConsole("handle被移除");
    }

    /**
     * 发生异常后关闭异常Channel并移除
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        PrintUtils.logToConsole("发生异常: " + cause.getMessage());
        ctx.channel().close();
        users.remove(ctx.channel());
    }
}
