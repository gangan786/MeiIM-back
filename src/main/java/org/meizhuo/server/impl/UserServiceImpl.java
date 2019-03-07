package org.meizhuo.server.impl;

import org.meizhuo.mapper.UsersMapper;
import org.meizhuo.pojo.Users;
import org.meizhuo.server.UserService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private Sid sid;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUsernameIsExist(String username) {
        Users user = new Users();
        user.setUsername(username);

        Users result = usersMapper.selectOne(user);

        return result != null;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users queryUserForLogin(String username, String pwd) {
        Example userExample = new Example(Users.class);
        Example.Criteria criteria = userExample.createCriteria();

        criteria.andEqualTo("username", username);
        criteria.andEqualTo("password", pwd);

        Users result = usersMapper.selectOneByExample(userExample);

        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Users saveUser(Users user) {
        //生成唯一id
        String userId = sid.nextShort();

//        // 为每个用户生成一个唯一的二维码
//        String qrCodePath = "C://user" + userId + "qrcode.png";
//        // muxin_qrcode:[username]
//        qrCodeUtils.createQRCode(qrCodePath, "muxin_qrcode:" + user.getUsername());
//        MultipartFile qrCodeFile = FileUtils.fileToMultipart(qrCodePath);
//
//        String qrCodeUrl = "";
//        try {
//            qrCodeUrl = fastDFSClient.uploadQRCode(qrCodeFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        user.setQrcode(qrCodeUrl);

        user.setId(userId);
        usersMapper.insert(user);

        return user;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Users updateUserInfo(Users user) {
        usersMapper.updateByPrimaryKeySelective(user);
        return queryUserById(user.getId());
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Users queryUserById(String userId) {
        return usersMapper.selectByPrimaryKey(userId);
    }

}
