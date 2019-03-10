package org.meizhuo.server;

import org.meizhuo.pojo.Users;
import org.meizhuo.pojo.vo.FriendRequestVO;

import java.util.List;

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
     * 根据用户名查询是否存在此用户
     *
     * @param username
     * @return
     */
    boolean queryUsernameIsExist(String username);

    /**
     * 登录验证
     *
     * @param username
     * @param md5Str
     * @return
     */
    Users queryUserForLogin(String username, String md5Str);

    /**
     * 注册用户
     *
     * @param user
     * @param path
     * @return
     */
    Users saveUser(Users user, String path);

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    Users updateUserInfo(Users user);

    /**
     * 验证搜索的用户是否具有合法性
     *
     * @param myUserId
     * @param friendUsername
     * @return 搜索结果状态码
     */
    Integer preconditionSearchFriends(String myUserId, String friendUsername);

    /**
     * 根据用户名搜索用户信息
     *
     * @param username
     * @return
     */
    Users queryUserInfoByUsername(String username);

    /**
     * 添加好友请求记录，保存到数据库
     *
     * @param myUserId
     * @param friendUsername
     */
    void sendFriendRequest(String myUserId, String friendUsername);

    /**
     * 查询好友添加申请列表
     * @param acceptUserId
     * @return
     */
    List<FriendRequestVO> queryFriendRequestList(String acceptUserId);

}
