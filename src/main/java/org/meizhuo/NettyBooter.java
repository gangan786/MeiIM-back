package org.meizhuo;

import org.meizhuo.netty.WSServer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: meiim
 * @Package: org.meizhuo
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: Gangan
 * @CreateDate: 2019/3/4 21:54
 * @UpdateUser:
 * @UpdateDate: 2019/3/4 21:54
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
@Component
public class NettyBooter implements ApplicationListener<ContextRefreshedEvent> {


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (contextRefreshedEvent.getApplicationContext().getParent() == null) {
            //启动netty
            try {
                WSServer.getInstance().start();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
