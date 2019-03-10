package org.meizhuo.controller;

import org.apache.commons.lang3.StringUtils;
import org.meizhuo.enums.SearchFriendsStateEnum;
import org.meizhuo.pojo.Users;
import org.meizhuo.pojo.vo.UsersVO;
import org.meizhuo.server.UserService;
import org.meizhuo.utils.FastDFSClient;
import org.meizhuo.utils.FileUtils;
import org.meizhuo.utils.IMoocJSONResult;
import org.meizhuo.utils.MD5Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @ProjectName: meiim
 * @Package: org.meizhuo.Controller
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: Gangan
 * @CreateDate: 2019/3/4 21:10
 * @UpdateUser:
 * @UpdateDate: 2019/3/4 21:10
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
@RestController
@RequestMapping("u")
public class UserController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserService userService;

    @Autowired
    private FastDFSClient fastDFSClient;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    /**
     * 注册或者登陆
     * 用户名不存在则注册，存在即校验密码合法性
     * @param username
     * @param password
     * @param cid
     * @return
     * @throws Exception
     */
    @PostMapping("/registOrLogin")
    public IMoocJSONResult registOrLogin(String username, String password, String cid) throws Exception {

        Users user = new Users();
        user.setCid(cid);
        user.setUsername(username);
        user.setPassword(password);
        // 0. 判断用户名和密码不能为空
        if (StringUtils.isBlank(user.getUsername())
                || StringUtils.isBlank(user.getPassword())) {
            return IMoocJSONResult.errorMsg("用户名或密码不能为空...");
        }

        // 1. 判断用户名是否存在，如果存在就登录，如果不存在则注册
        boolean usernameIsExist = userService.queryUsernameIsExist(user.getUsername());
        Users userResult = null;
        if (usernameIsExist) {
            // 1.1 登录
            userResult = userService.queryUserForLogin(user.getUsername(),
                    MD5Utils.getMD5Str(user.getPassword()));
            if (userResult == null) {
                return IMoocJSONResult.errorMsg("用户名或密码不正确...");
            }
        } else {
            // 1.2 注册
            user.setNickname(user.getUsername());
            user.setFaceImage("");
            user.setFaceImageBig("");
            user.setPassword(MD5Utils.getMD5Str(user.getPassword()));
            userResult = userService.saveUser(user,request.getServletContext().getRealPath(""));
        }

        UsersVO userVO = new UsersVO();
        BeanUtils.copyProperties(userResult, userVO);

        return IMoocJSONResult.ok(userVO);
    }


    /**
     * 上传头像
     * @param faceData
     * @param userId
     * @return
     * @throws Exception
     */
    @PostMapping("uploadFaceBase64")
    public IMoocJSONResult uploadFaceBase64(String userId,String faceData) throws Exception {

        String realPath = request.getServletContext().getRealPath("");

        // 获取前端传过来的base64字符串, 然后转换为文件对象再上传
        String userFacePath = realPath + userId + "userface64.png";
        FileUtils.base64ToFile(userFacePath, faceData);

        // 上传文件到fastdfs
        MultipartFile faceFile = FileUtils.fileToMultipart(userFacePath);
        String url = fastDFSClient.uploadBase64(faceFile);
        System.out.println(url);

        //上传完成后删除临时文件
        FileUtils.deleteFileByPath(userFacePath);

//		"dhawuidhwaiuh3u89u98432.png"
//		"dhawuidhwaiuh3u89u98432_80x80.png"

        // 获取缩略图的url
        String thump = "_80x80.";
        String arr[] = url.split("\\.");
        String thumpImgUrl = arr[0] + thump + arr[1];

        // 更新用户头像
        Users user = new Users();
        user.setId(userId);
        user.setFaceImage(thumpImgUrl);
        user.setFaceImageBig(url);

        Users result = userService.updateUserInfo(user);

        return IMoocJSONResult.ok(result);
    }

    /**
     * 设置 nickname
     * @param userId
     * @param nickname
     * @return
     * @throws Exception
     */
    @PostMapping("/setNickname")
    public IMoocJSONResult setNickname(String userId,String nickname) throws Exception {

        Users user = new Users();
        user.setId(userId);
        user.setNickname(nickname);

        Users result = userService.updateUserInfo(user);

        return IMoocJSONResult.ok(result);
    }

    /**
     * 搜索好友
     * @param myUserId
     * @param friendUsername
     * @return
     * @throws Exception
     */
    @PostMapping("/search")
    public IMoocJSONResult searchUser(String myUserId, String friendUsername)
            throws Exception {

        // 0. 判断 myUserId friendUsername 不能为空
        if (StringUtils.isBlank(myUserId)
                || StringUtils.isBlank(friendUsername)) {
            return IMoocJSONResult.errorMsg("");
        }

        // 前置条件 - 1. 搜索的用户如果不存在，返回[无此用户]
        // 前置条件 - 2. 搜索账号是你自己，返回[不能添加自己]
        // 前置条件 - 3. 搜索的朋友已经是你的好友，返回[该用户已经是你的好友]
        Integer status = userService.preconditionSearchFriends(myUserId, friendUsername);
        if (SearchFriendsStateEnum.SUCCESS.status.equals(status)) {
            Users user = userService.queryUserInfoByUsername(friendUsername);
            UsersVO userVO = new UsersVO();
            BeanUtils.copyProperties(user, userVO);
            return IMoocJSONResult.ok(userVO);
        } else {
            String errorMsg = SearchFriendsStateEnum.getMsgByKey(status);
            return IMoocJSONResult.errorMsg(errorMsg);
        }
    }

    @PostMapping("/addFriendRequest")
    public IMoocJSONResult addFriendRequest(String myUserId, String friendUsername)
            throws Exception {

        // 0. 判断 myUserId friendUsername 不能为空
        if (StringUtils.isBlank(myUserId)
                || StringUtils.isBlank(friendUsername)) {
            return IMoocJSONResult.errorMsg("");
        }

        // 前置条件 - 1. 添加的用户如果不存在，返回[无此用户]
        // 前置条件 - 2. 添加账号是你自己，返回[不能添加自己]
        // 前置条件 - 3. 添加的朋友已经是你的好友，返回[该用户已经是你的好友]
        Integer status = userService.preconditionSearchFriends(myUserId, friendUsername);
        if (SearchFriendsStateEnum.SUCCESS.status.equals(status)) {
            userService.sendFriendRequest(myUserId, friendUsername);
        } else {
            String errorMsg = SearchFriendsStateEnum.getMsgByKey(status);
            return IMoocJSONResult.errorMsg(errorMsg);
        }

        return IMoocJSONResult.ok();
    }

    @PostMapping("/queryFriendRequests")
    public IMoocJSONResult queryFriendRequests(String userId) {

        // 0. 判断不能为空
        if (StringUtils.isBlank(userId)) {
            return IMoocJSONResult.errorMsg("");
        }

        // 1. 查询用户接受到的朋友申请
        return IMoocJSONResult.ok(userService.queryFriendRequestList(userId));
    }

}
