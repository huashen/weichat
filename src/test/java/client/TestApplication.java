package client;

import com.lhs.weichat.bean.ChatMessage;
import com.lhs.weichat.utils.UUIDUtil;

import java.util.Date;

/**
 * TestApplication
 *
 * @author longhuashen
 * @since 17/9/4
 */
public class TestApplication {

    public static void main(String[] args) {
        Session session = new Session("127.0.0.1", 8888);
        session.connect();

        String content = "你好啊";
        int toId = 1;

//        ChatMessage chatMessage = new ChatMessage();
//        chatMessage.setContent("雯雯");
//
//        chatMessage.setFromId(2);
//        chatMessage.setDate(new Date());
//        chatMessage.setType(ChatMessage.TYPE_SEND);
//        chatMessage.setContentType(ChatMessage.CONTENT_TYPE_NORMAL);
//        chatMessage.setMsgType(ChatMessage.MSG_TYPE_UU);
//        chatMessage.setToId(1);
//
        String uuid = UUIDUtil.uuid();
//        chatMessage.setUuid(uuid);

        session.sendMessage(uuid, ChatMessage.CONTENT_TYPE_NORMAL,
                content, toId, ChatMessage.MSG_TYPE_UU, "", "");
    }
}
