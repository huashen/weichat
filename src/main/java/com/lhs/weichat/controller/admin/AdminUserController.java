package com.lhs.weichat.controller.admin;

import com.alibaba.fastjson.JSON;
import com.lhs.weichat.bean.*;
import com.lhs.weichat.core.Session;
import com.lhs.weichat.core.SessionManager;
import com.lhs.weichat.core.bean.Msg;
import com.lhs.weichat.core.bean.MsgHelper;
import com.lhs.weichat.service.*;
import com.lhs.weichat.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * UserAction
 *
 * @author longhuashen
 * @since 15/9/24
 */
@Controller
@RequestMapping("/user")
public class AdminUserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IAttachmntService attachemntService;

    @Autowired
    private IUserAuthTokenService userAuthTokenService;

    @Autowired
    private ITodoService todoService;

    @Autowired
    private IFriendsService friendService;

    @Autowired
    private IFriendsGroupService friendsGroupService;

    @Autowired
    private IUserOnlineServerService userOnlineServerService;

    @Autowired
    private SessionManager sessionManager;

    @RequestMapping(value = "/register.json", method = RequestMethod.POST)
    public Result register(HttpServletRequest request,
                           @RequestParam(value = "name", defaultValue = "") String name,
                           @RequestParam(value = "account", defaultValue = "") String account,
                           @RequestParam(value = "password", defaultValue = "") String password,
                           @RequestParam(value = "birthday") long brithday,
                           @RequestParam(value = "gender", defaultValue = "1") int gender,
                           @RequestParam(value = "signature", defaultValue = "") String signature,
                           @RequestParam(value = "avatarId", defaultValue = "") int avatarId) {

        String message = "";
        boolean success = true;
        if(name == null || name.isEmpty()) {
            message = message + "昵称不能为空";
            success = false;
        }

        if(account == null || account.isEmpty()) {
            message = message + "账户不能为空";
            success = false;
        }else {
            User user = userService.getUserByAccount(account);
            if(user != null && user.getId() > 0) {
                message = message + "账户[" + account + "]已存在";
                success = false;
            }
        }

        if(password == null || password.isEmpty()) {
            message = message + "密码不能为空!";
            success = false;
        } else if(password.length() < 6){
            message = message + "密码至少6位！";
            success = false;
        }

        User u = null;
        Attachment a = null;
        if(success) {
            if(avatarId > 0) {
                a = attachemntService.getAttachmentById(avatarId);
            }
            User newUser = new User();
            newUser.setName(name);
            newUser.setAccount(account);
            newUser.setPassword(password);
            newUser.setBirthday(new Date(brithday));
            newUser.setSignature(signature);
            newUser.setGender(gender);
            newUser.setAvatar(a);
            u = userService.addUser(newUser);
        }

        Result result = new Result(success);
        result.setMessage(message);
        result.setObj(u);
        return result;
    }

    @RequestMapping(value = "/login.json", method = RequestMethod.POST)
    public Result login(@RequestParam(value = "account", defaultValue = "") String account,
                        @RequestParam(value = "password", defaultValue = "") String password,
                        @RequestParam(value = "clientId", defaultValue = "") String clientId,
                        @RequestParam(value = "clientType", defaultValue = "android") String clientType) {
        String message = "";
        boolean success = true;

        if(account == null || account.isEmpty()) {
            message = message + "账户不能为空！";
        }

        if(clientId == null || clientId.isEmpty()) {
            message = message + "客户端id不能为空!";
        }

        if(password == null || clientId.isEmpty()) {
            message = message + "用户不能为空！";
        }

        if(!message.isEmpty()) {
            Result r0 = new Result(success, message);
            String str = JSON.toJSONString(r0);
            return r0;
        }

        User user = userService.getUserByAccount(account);
        if(user != null && user.getPassword().equals(DigestUtils.shaHex(password).toLowerCase())) {
            String token = UUIDUtil.uuid();
            message = token;
            UserAuthToken userAuthToken = new UserAuthToken();
            userAuthToken.setUser(user);
            userAuthToken.setClientId(clientId);
            userAuthToken.setClientType(clientType);
            userAuthToken.setToken(token);
            userAuthTokenService.save(user, clientId, clientType, token);
        }else {
            message = "用户名或密码错误!";
        }
        Result r = new Result(success, message, user);
        return r;
    }

    @ResponseBody
    @RequestMapping("/quickLogin.json")
    public Result quickLogin(HttpServletRequest request, @RequestParam(value = "account", defaultValue = "") String account,
                             @RequestParam(value = "clientId", defaultValue = "") String clientId,
                             @RequestParam(value = "token", defaultValue = "") String token) {
        boolean success = false;
        String message = "";
        User user = userService.getUserByAccount(account);
        if(user != null) {
            UserAuthToken ut = userAuthTokenService.getUserAuthTokenByUserIdAndToken(user.getId(), token);
            if(ut != null) {
                if(ut.getToken().equals(token)) {
                    success = false;
                }
            }
        }
        return new Result(success, message, user);
    }

    @ResponseBody
    @RequestMapping("/checkAccount.json")
    public Result checkAccount(HttpServletRequest request,
                               @RequestParam(value = "account", defaultValue = "") String account) {
        boolean success = false;
        String message = "";
        User user = userService.getUserByAccount(account);
        if(user != null && user.getId() > 0) {
            message = "用户存在！";
            success = true;
        } else {
            message = "用户不存在！";
        }
        return new Result(success, message);
    }

    @ResponseBody
    @RequestMapping("/search.json")
    public List<User> search(@RequestParam(value = "condition", defaultValue = "") String condition) {
        List<User> list = userService.search(condition);
        if(list != null) {
            for (User u : list) {
                u.setPassword("");
            }
        }
        return list;
    }

    @ResponseBody
    @RequestMapping("/getUser.json")
    public User getUser(@RequestParam(value = "userId", defaultValue = "") int userId) {
        User user = userService.getUserById(userId);
        return user;
    }

    @ResponseBody
    @RequestMapping("/addFriends.json")
    public Result addFriends(@RequestParam(value = "userId", defaultValue = "") int userId,
                             @RequestParam(value = "token", defaultValue = "") String token,
                             @RequestParam(value = "friendsId", defaultValue = "") int friendsId,
                             @RequestParam(value = "requestMsg", defaultValue = "") String requestMsg) {
        boolean success = false;
        String message = "";
        User user = userService.getUserById(userId);
        User friends = userService.getUserById(friendsId);
        UserAuthToken ut = userAuthTokenService.getUserAuthTokenByUserIdAndToken(userId, token);
        if(ut != null && ut.getId() > 0 && user != null && user.getId() > 0 && friends != null && friends.getId() > 0) {
            Todo todo = new Todo();
            todo.setUser(friends);
            todo.setComplete(false);
            todo.setCreateDate(new Date());
            todo.setFrom(user);
            todo.setRequestMsg(requestMsg);
            todo.setType(Todo.TODO_TYPE_ADD_FRIENDS);

            todo = todoService.saveTodo(todo);
            List<UserAuthToken> uts = userAuthTokenService.getUserAuthTokenByUserId(friendsId);
            Session session = null;
            if(uts != null) {
                for (UserAuthToken u : uts) {
                    session = SessionManager.get(friendsId + u.getToken());
                    if (session != null) {
                        Msg.Message msg = MsgHelper.newTodoMessage(todo.getId(), "用户[" + user.getName() + "]请求添加您为好友！",Todo.TODO_TYPE_ADD_FRIENDS, requestMsg);
                        session.send(msg);
                        //成功发送即为完成
                        todo.setComplete(true);
                        todoService.updateTodo(todo);
                    }
                }
            }
            success = true;
        } else {
            message = "非法请求";
        }
        return new Result(success, message);
    }

    @ResponseBody
    @RequestMapping(value = "/dealWithTodo.json")
    public Result dealWithTodo(HttpServletRequest request,
                               @RequestParam(value = "userId", defaultValue = "") int userId,
                               @RequestParam(value = "token", defaultValue = "") String token,
                               @RequestParam(value = "todoId", defaultValue = "") int todoId,
                               @RequestParam(value = "argeeOrNot", defaultValue = "") boolean argeeOrNot) {
            boolean success = false;
            String message = "";

            if (userAuth(userId, token)) {
                if (argeeOrNot) {
                    Todo todo = todoService.getTodoById(todoId);
                    if (todo != null || todo.getHandleDate() != null) {
                        switch (todo.getType()) {
                            //加好友
                            case Todo.TODO_TYPE_ADD_FRIENDS:
                                //添加好友
                                friendService.makeFriends(todo.getFrom(), todo.getUser());
                                //推送消息
                                this.sendFriends(todo.getFrom());
                                this.sendFriends(todo.getUser());
                                //更新代办状态
                                todoService.finishTodo(todoId, "成功添加好友", argeeOrNot);
                                message = "成功添加好友";
                                break;
                            //加群
                            case Todo.TODO_TYPE_JOIN_GROUP:

                        }
                    }
                }
            }
        return null;
    }

    private void sendFriends(User user) {
        //获取用户的好友列表
        List<Friends> friendses = friendService.getFriendsByUserId(user.getId());
        for (Friends u : friendses) {
            u.setOnlineStatus(friendService.getFriendsOnlineStatus(user.getId(), u.getFriend().getId()));
        }

        Msg.Message msg = MsgHelper.newFriendsListMessage(friendses);
        //获取好友分组列表
        List<FriendsGroup> fg = friendsGroupService.getFriendsGroupByUserId(user.getId());

        Msg.Message mfg = MsgHelper.newFriendsGroupListMessage(fg);

        Set<UserAuthToken> tokenSet = userOnlineServerService.getOnlineToken(user.getId());
        if (tokenSet != null) {
            for (UserAuthToken token : tokenSet) {
                String sessionKey = user.getId() + token.getToken();
                sessionManager.sendMessage(msg, sessionKey);
                sessionManager.sendMessage(mfg, sessionKey);
            }
        }
    }

    /**
     * 用户权限认证
     *
     * @param userId
     * @param token
     * @return
     */
    private boolean userAuth(int userId, String token) {
        User user = userService.getUserById(userId);
        UserAuthToken userAuthToken = userAuthTokenService.getUserAuthTokenByUserIdAndToken(userId, token);
        if (userAuthToken != null && userAuthToken.getId() > 0 && user != null && user.getId() > 0) {
            return true;
        } else {
            return false;
        }
    }

}
