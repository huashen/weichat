package com.lhs.weichat.core.handler;

import com.lhs.weichat.bean.Attachment;
import com.lhs.weichat.core.SessionManager;
import com.lhs.weichat.core.bean.Msg;
import com.lhs.weichat.core.bean.MsgHelper;
import com.lhs.weichat.service.AttachmentService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * FileUploadHandler
 *
 * @author longhuashen
 * @since 17/10/5
 */
@Service
public class FileUploadHandler extends SimpleChannelInboundHandler<Attachment> {

    @Autowired
    private SessionManager sessionManager;

    @Autowired
    private AttachmentService attachmentService;

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Attachment msg) throws Exception {
        if (sessionManager.clientAuth(msg.getUserId() + "", msg.getToken()) != null) {
            attachmentService.saveAttachment(msg);
        } else {
            Msg.Message rtMessage = MsgHelper.newResultMessage(Msg.MessageType.AUTH_ERROR,
                    "用户认证失败，重新认证！");
            LOGGER.info("用户认证失败，重新认证！");
            ctx.channel().writeAndFlush(rtMessage);
        }
        ReferenceCountUtil.release(msg);
    }
}
