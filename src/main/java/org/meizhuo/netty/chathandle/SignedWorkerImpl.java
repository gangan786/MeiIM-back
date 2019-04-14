package org.meizhuo.netty.chathandle;

import io.netty.channel.Channel;
import org.apache.commons.lang3.StringUtils;
import org.meizhuo.SpringUtil;
import org.meizhuo.enums.MsgActionEnum;
import org.meizhuo.netty.DataContent;
import org.meizhuo.server.UserService;

import java.util.ArrayList;

/**
 * @Classname SignedWorkerImpl
 * @Description 签收消息类型，针对具体的消息进行签收，
 * 修改数据库中对应消息的签收状态[已签收]
 * @Date 2019/4/13 14:14
 * @Created by Gangan
 */
public class SignedWorkerImpl extends BaseChatWorker {

    private static final Integer MSG_TYPE_SIGNED = MsgActionEnum.SIGNED.type;


    public SignedWorkerImpl (BaseChatWorker nextBaseChatWorker, Channel currentChannel) {
        super(MSG_TYPE_SIGNED, nextBaseChatWorker, currentChannel);
    }

    @Override
    public void onHandle(DataContent dataContent) {
        UserService userService = (UserService) SpringUtil.getBean("userServiceImpl");

        String msgIdsStr = dataContent.getExtand();
        String[] msgIds = msgIdsStr.split(",");

        ArrayList<String> msgIdList = new ArrayList<>();
        for (String id : msgIds) {
            if (StringUtils.isNotBlank(id)) {
                msgIdList.add(id);
            }
        }

        //批量签收
        if (!msgIdList.isEmpty()) {
            userService.updateMsgSigned(msgIdList);
        }
    }
}
