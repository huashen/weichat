package com.lhs.service;

import com.lhs.weichat.core.NettyServerBootstrap;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ApplicationStarter
 *
 * @author longhuashen
 * @since 17/5/21
 */
public class ApplicationStarter {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        NettyServerBootstrap nettyServerBootstrap = (NettyServerBootstrap) applicationContext.getBean("nettyServerBootstrap");
    }
}
