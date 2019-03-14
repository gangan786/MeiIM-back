package org.meizhuo.netty;

import io.netty.channel.Channel;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @ProjectName: meiim
 * @Package: org.meizhuo.netty
 * @ClassName: ${TYPE_NAME}
 * @Description: 用户与channel的关联关系
 * @Author: Gangan
 * @CreateDate: 2019/3/12 16:12
 * @UpdateUser:
 * @UpdateDate: 2019/3/12 16:12
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class UserChannelRel {

    private static ConcurrentHashMap<String, Channel> manage=new ConcurrentHashMap<>();

    public static void put(String userId, Channel channel) {
        manage.put(userId,channel);
    }

    public static Channel get(String userId) {
        return manage.get(userId);
    }

}
