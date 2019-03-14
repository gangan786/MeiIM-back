package org.meizhuo.mapper;

import org.meizhuo.pojo.Users;
import org.meizhuo.pojo.vo.FriendRequestVO;
import org.meizhuo.pojo.vo.MyFriendsVO;
import org.meizhuo.utils.MyMapper;

import java.util.List;


public interface UsersMapperCustomer extends MyMapper<Users> {

    /**
     * 查找好友申请列表
     *
     * @param acceptUserId
     * @return
     */
    List<FriendRequestVO> queryFriendRequestList(String acceptUserId);

    /**
     * 查找用户好友列表
     * @param userId
     * @return
     */
    List<MyFriendsVO> queryMyFriends(String userId);

    /**
     * 标记信息已签收
     * @param msgIdList
     */
    void batchUpdateMsgSigned(List<String> msgIdList);
}