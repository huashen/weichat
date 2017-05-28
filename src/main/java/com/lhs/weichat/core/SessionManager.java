package com.lhs.weichat.core;

import com.lhs.weichat.bean.ChatMessage;
import com.lhs.weichat.bean.ChatServer;
import com.lhs.weichat.bean.User;
import com.lhs.weichat.bean.UserAuthToken;
import com.lhs.weichat.core.bean.Msg;
import com.lhs.weichat.core.bean.MsgHelper;
import com.lhs.weichat.service.ChatServerService;
import com.lhs.weichat.service.UserAuthTokenService;
import com.lhs.weichat.service.UserOnlineServerService;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.SocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * SessionManager
 * 保持服务间通信通道
 *
 * @author longhuashen
 * @since 15/10/3
 */
@Service
public class SessionManager {

    private static final Logger logger = LoggerFactory.getLogger(SessionManager.class);

    @Autowired
    private ChatServerService chatServerService;

    @Autowired
    private UserAuthTokenService userAuthTokenService;

    @Autowired
    private UserOnlineServerService userOnlineServerService;

    private static Map<String, Session> sessionMap = new ConcurrentHashMap<>();

    private static Map<Session, String> sessionKeyMap = new ConcurrentHashMap<>();

    private static Map<Channel, String> channelMap = new ConcurrentHashMap<>();

    private static Map<String, Channel> channelKeyMap = new ConcurrentHashMap<>();


    private String ip;

    private int port;

    public static void add(String clientId, Session session) {
        sessionMap.put(clientId, session);
        sessionKeyMap.put(session, clientId);
        channelMap.put(session.getChannel(), clientId);
        channelKeyMap.put(clientId, session.getChannel());
    }

    public static Session get(String clientId) {
        return sessionMap.get(clientId);
    }

    public static void remove(Session session) {
        String key = sessionKeyMap.get(session);
        if (key == null) {
            return;
        }
        Channel channel = channelKeyMap.get(key);
        sessionKeyMap.remove(session);
        channelMap.remove(channel);
        channelKeyMap.remove(key);
    }

    public void logout(Channel channel) {
        Session session = SessionManager.get(channel);
        if (session != null && session.getType().equals(Session.SessionType.SC)) {
            userOnlineServerService.removeUserOnlineServer(session.getUserId(), session.getToken(), this.getIp(), this.getPort());
        }

    }

    private static Session get(Channel channel) {
        String key = channelMap.get(channel);
        return sessionMap.get(key);
    }


    public Session clientLoginAuth(ChannelHandlerContext channelHandlerContext, String token, int userId) {
        Session session = null;
        User user = userAuthTokenService.getUserByToken(token);
        if (user.getId() == userId) {
            session = new Session(channelHandlerContext.channel(), userId, token);
            SessionManager.add(userId + token, session);
            userOnlineServerService.saveUserOnlineServer(userId, token, this.getIp(), this.getPort());
        }
        return session;
    }

    /**
     * 客户端认证检查
     *
     * @param userId
     * @param token
     * @return
     */
    public Session clientAuth(String userId, String token) {
        Session session = SessionManager.get(userId + token);
        return session;
    }

    /**
     * 处理收到的server login消息
     *
     * @param channelHandlerContext
     * @return
     */
    public boolean serverLogin(ChannelHandlerContext channelHandlerContext) {
        InetSocketAddress inetSocketAddress = (InetSocketAddress) channelHandlerContext.channel().localAddress();
        String ip = inetSocketAddress.getAddress().getHostAddress();
        int port = inetSocketAddress.getPort();
        ChatServer server = chatServerService.getChatServerByIpAndPort(ip, port);
        if (server != null && server.isOnline()) {
            Session session = new Session((SocketChannel) channelHandlerContext.channel());
            SessionManager.add(ip + ":" + port, session);
            logger.info("服务器(" + ip + "," + port + ")登录连接成功。");

            Msg.Message rtMessage = MsgHelper.newResultMessage(Msg.MessageType.LOGIN_SUCCESS, "认证成功！");
            session.send(rtMessage);
            logger.info("认证成功！");
            return true;
        } else {
            String msg;
            if (server == null) {
                msg = "服务器(" + ip + "," + port + ") 登录连接失败，未找到该服务器。";
            } else if (server != null && !server.isOnline()) {
                msg = "服务器(" + ip + "," + port + ") 登录连接失败，服务器不在线。";
            } else {
                msg = "服务器(" + ip + "," + port + ") 登录连接失败，原因未知。";
            }
            logger.info(msg);
            Msg.Message rtMessage = MsgHelper.newResultMessage(Msg.MessageType.LOGIN_ERROR, "服务器认证失败，重新认证");
            logger.info("服务器认证失败，重新认证");
            channelHandlerContext.channel().writeAndFlush(rtMessage);
            return false;
        }
    }

    /**
     * 检查服务端的认证
     *
     * @param channelHandlerContext
     * @return
     */
    public boolean serverAuth(ChannelHandlerContext channelHandlerContext) {
        InetSocketAddress inetSocketAddress = (InetSocketAddress) channelHandlerContext.channel().localAddress();
        String ip = inetSocketAddress.getAddress().getHostAddress();
        int port = inetSocketAddress.getPort();
        String clientId = ip + ":" + port;
        Session session = SessionManager.get(clientId);
        if (session == null) {
            return serverLogin(channelHandlerContext);
        }
        return true;
    }

    /**
     * 服务端响应pong消息
     *
     * @param channelHandlerContext
     */
    public void serverPing(ChannelHandlerContext channelHandlerContext) {
        InetSocketAddress inetSocketAddress = (InetSocketAddress) channelHandlerContext.channel().localAddress();
        String ip = inetSocketAddress.getAddress().getHostAddress();
        int port = inetSocketAddress.getPort();
        String clientId = ip + ":" + port;
        Session session = SessionManager.get(clientId);
        if (session != null) {
            Msg.Message pong = MsgHelper.newPingMessage(Msg.MessageType.SERVER_PONG, this.getIp() + ":" + this.getPort());
            session.send(pong);
        } else {
            logger.info("响应服务器ping是获取channel失败。");
        }
    }

    /**
     * 客户端响应pong消息
     *
     * @param session
     */
    public void clientPing(Session session) {
        if (session != null) {
            Msg.Message pong = MsgHelper.newPingMessage(Msg.MessageType.CLIENT_PONG, this.getIp() + ":" + this.getPort());
            session.send(pong);
        } else {
            logger.info("响应客户端ping时获取channel失败");
        }
    }

    /**
     * 发送聊天消息
     *
     * @param msg
     */
    public void sendMessage(ChatMessage msg) {
        Msg.Message message = MsgHelper.newChatMessage(msg);
        Set<UserAuthToken> userAuthTokenSet = userOnlineServerService.getOnlineToken(msg.getToId());
        if (userAuthTokenSet != null) {
            for (UserAuthToken token : userAuthTokenSet) {
                Session session = SessionManager.get(msg.getToId() + token.getToken());
                if (session != null) {
                    session.send(msg);
                    if (msg.getAttachment() != null) {
                        Msg.Message d = MsgHelper.newFileDownloadMessage(msg.getAttachment());
                        session.send(d);
                    }
                }
            }
        }

        if (msg.isTransfer()) {
            Set<ChatServer> set = userOnlineServerService.getOnlineServer(msg.getFromId());
            if (set != null) {
                for (ChatServer server : set) {
                    String clientId = server.getIp() + ":" + server.getPort();
                    Session serverSession = SessionManager.get(clientId);
                    if (serverSession != null) {
                        serverSession.send(message);
                    }
                }
            }
        }
    }

    /**
     * 发送json格式的消息
     * @param message
     * @param sessionKey
     */
    public void sendMessage(Msg.JsonMessage message, String sessionKey) {
        Session session = SessionManager.get(sessionKey);
        if (session != null) {
            Msg.Message msg = MsgHelper.newJsonMessage(message.getJsonMessageType(), message.getJsonStr());
            session.send(msg);
        }
    }

    /**
     * 发送普通消息
     * @param message
     * @param sessionKey
     */
    public void sendMessage(Msg.Message message, String sessionKey) {
        Session session = SessionManager.get(sessionKey);
        if (session != null) {
            session.send(message);
        }
    }

    /**
     * 按人推送消息
     * @param userId
     * @param message
     * @return
     */
    public boolean sendMessage(int userId, Msg.Message message) {
        boolean flag = false;
        List<UserAuthToken> uts = userAuthTokenService.getUserAuthTokenByUserId(userId);
        if (uts != null) {
            for (UserAuthToken u : uts) {
                Session session = SessionManager.get(userId + u.getToken());
                if (session != null) {
                    session.send(message);
                }
                flag = true;
            }
        }
        return flag;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
