package org.meizhuo.mapper;

import org.meizhuo.pojo.Users;
import org.meizhuo.pojo.vo.FriendRequestVO;
import org.meizhuo.utils.MyMapper;

import java.util.List;

public interface UsersMapperCustomer extends MyMapper<Users> {

    /**
     * 查找好友申请列表
     * @param acceptUserId
     * @return
     */
    List<FriendRequestVO> queryFriendRequestList(String acceptUserId);

}