package org.meizhuo.server;

import org.meizhuo.pojo.Users;

/**
 * @ProjectName: meiim
 * @Package: org.meizhuo.server
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: Gangan
 * @CreateDate: 2019/3/5 10:10
 * @UpdateUser:
 * @UpdateDate: 2019/3/5 10:10
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public interface UserService {

    /**
     * @Description: 判断用户名是否存在
     */
    public boolean queryUsernameIsExist(String username);


    Users queryUserForLogin(String username, String md5Str);

    Users saveUser(Users user);

    Users updateUserInfo(Users user);
}
