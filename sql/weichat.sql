#应用程序信息表
DROP TABLE IF EXISTS `t_app_version`;
CREATE TABLE IF NOT EXISTS `t_app_version` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长主键',
  `version_code` varchar(20) NOT NULL COMMENT '版本代码',
  `download_url` text NOT NULL COMMENT '下载链接',
  `create_time` datetime default NULL COMMENT '创建时间',
  `publish_time` datetime default NULL COMMENT '发版时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

#文件表
DROP TABLE IF EXISTS 't_attachment';
CREATE TABLE IF NOT EXISTS `t_attachment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长主键',
  `name` varchar(30) NOT NULL COMMENT '文件名',
  `group_name` varchar(50) NOT NULL COMMENT '对应在FastDFS上的group',
  `path` text NOT NULL COMMENT '文件路径',
  `type` int(1) NOT NULL COMMENT '文件类型',
  `size` int(11) NOT NULL COMMENT '文件大小',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `token` varchar(100) NOT NULL COMMENT '用户token',
  `reate_time` datetime default NULL COMMENT '创建时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

#用户表
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE IF NOT EXISTS `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长主键',
  `account` varchar(30) NOT NULL COMMENT '账号',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `nick_name` varchar(30) NOT NULL COMMENT '昵称',
  `avatar_id` int(11) NOT NULL COMMENT '头像ID',
  `birthday` datetime default NULL COMMENT '生日',
  `signature` varchar(100) DEFAULT NULL COMMENT '个性签名',
  `gender` int(1) NOT NULL default 0 COMMENT '性别',
  PRIMARY KEY (id),
  FOREIGN KEY (avatar_id) REFERENCES t_attachment(id) ON DELETE CASCADE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

#群类别表
DROP TABLE IF EXISTS `t_group_class`;
CREATE TABLE IF NOT EXISTS `t_group_class` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长主键',
  `name` varchar(30) NOT NULL COMMENT '分类名称 ',
  `type` int(10) NOT NULL COMMENT '类型',
  `parent_id` int(10) NOT NULL DEFAULT '0' COMMENT '父类的id',
  PRIMARY KEY (id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

#聊天群表
DROP TABLE IF EXISTS `t_chat_group`;
CREATE TABLE IF NOT EXISTS `t_chat_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长主键',
  `account` varchar(30) NOT NULL COMMENT '群名称',
  `slogan` varchar(30) NOT NULL COMMENT '群口号',
  `create_time` datetime default NULL COMMENT '创建时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;


#聊天群表
DROP TABLE IF EXISTS `t_user_auth_token`;
CREATE TABLE IF NOT EXISTS `t_user_auth_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长主键',
  `client_id` varchar(50) NOT NULL COMMENT '客户端id',
  `client_type` varchar(250) COMMENT '客户端类型',
  `create_date` datetime default NULL COMMENT '创建时间',
  `enable` tinyint COMMENT '是否可用',
  `token` varchar(100) COMMENT 'token',
  `user_id` int(11) COMMENT '用户id',
  PRIMARY KEY (id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

#待办事项表
DROP TABLE IF EXISTS `t_todo`;
CREATE TABLE `t_todo` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `agree` tinyint(1) DEFAULT NULL COMMENT '是否同意',
  `complete` tinyint(1) DEFAULT NULL COMMENT '是否已完成',
  `create_date` datetime DEFAULT NULL COMMENT '发起时间',
  `handle_date` datetime DEFAULT NULL COMMENT '处理时间',
  `handle_msg` varchar(100) DEFAULT NULL COMMENT '处理意见',
  `request_msg` varchar(100) DEFAULT NULL COMMENT '请求消息',
  `type` int(11) DEFAULT NULL COMMENT '待办类型',
  `from_id` int(11) DEFAULT NULL COMMENT '发起人id',
  `group_id` int(11) DEFAULT NULL COMMENT '群id,如果是入群请求则该字段不能为空',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `t_group_class`;
CREATE TABLE `t_group_class` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(200) DEFAULT NULL COMMENT '类别名称',
  `type` int(11) DEFAULT NULL COMMENT '类型',
  `big_class_id` int(11) DEFAULT NULL COMMENT '大类id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `t_chat_group`;
CREATE TABLE `t_chat_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account` varchar(10) DEFAULT NULL COMMENT '群号，由数组组成，唯一id',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `name` varchar(20) DEFAULT NULL COMMENT '群名称',
  `slogan` varchar(300) DEFAULT NULL COMMENT '群口号',
  `big_class_id` int(11) DEFAULT NULL COMMENT '群组所属大类',
  `create_by_Id` int(11) DEFAULT NULL COMMENT '群主',
  `manager1_id` int(11) DEFAULT NULL COMMENT '管理员1',
  `manager2_id` int(11) DEFAULT NULL COMMENT '管理员2',
  `small_class_id` int(11) DEFAULT NULL COMMENT '群组所属小类',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_chat_server`;
CREATE TABLE `t_chat_server` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ip` varchar(50) DEFAULT NULL COMMENT 'ip',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `online` tinyint(1) DEFAULT NULL COMMENT '是否在线',
  `port` int(11) DEFAULT NULL COMMENT '端口',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `t_user_auth_token`;
CREATE TABLE `t_user_auth_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `client_id` varchar(50) NOT NULL COMMENT '客户端id',
  `client_type` varchar(250) DEFAULT NULL COMMENT '客户端类型',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `enable` tinyint(1) DEFAULT NULL COMMENT '是否有效',
  `token` varchar(100) DEFAULT NULL COMMENT 'token',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_friends`;
CREATE TABLE `t_friends` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `remark_name` varchar(15) DEFAULT NULL COMMENT '备注名称',
  `shield` tinyint(1) DEFAULT NULL COMMENT '是否屏蔽消息',
  `visible` int(11) DEFAULT NULL COMMENT '好友对自己的可见状态,在线对其隐身，隐身可见',
  `friend_id` int(11) DEFAULT NULL COMMENT '朋友id',
  `friends_group_id` int(11) DEFAULT NULL COMMENT '朋友组id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_user_online_server`;
CREATE TABLE `t_user_online_server` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `online_status` int(11) DEFAULT NULL COMMENT '在线状态',
  `chat_server_id` int(11) DEFAULT NULL COMMENT '聊天服务器id',
  `user_auth_token_id` int(11) DEFAULT NULL COMMENT '用户token',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_friends_group`;
CREATE TABLE `t_friends_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) DEFAULT NULL COMMENT '名称',
  `position` int(11) NOT NULL COMMENT '排列顺序',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#讨论组
DROP TABLE IF EXISTS `t_discussion_group`;
CREATE TABLE `t_discussion_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `name` varchar(20) DEFAULT NULL COMMENT '名称',
  `create_by_id` int(11) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_discussion_group_member`;
CREATE TABLE `t_discussion_group_member` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `remark_name` varchar(15) DEFAULT NULL COMMENT '备注名称',
  `shield` tinyint(1) DEFAULT NULL COMMENT '是否屏蔽消息',
  `discussion_group_id` int(11) DEFAULT NULL COMMENT '讨论组id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `t_chat_group_member`;
CREATE TABLE `t_chat_group_member` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `remark_name` varchar(15) DEFAULT NULL COMMENT '备注名称',
  `shield` tinyint(1) DEFAULT NULL COMMENT '是否屏蔽消息',
  `chat_group_id` int(11) DEFAULT NULL COMMENT '群组id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `t_chat_message`;
CREATE TABLE `t_chat_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `chat_group_id` int(11) DEFAULT NULL COMMENT '群id',
  `content` varchar(500) DEFAULT NULL COMMENT '消息内容',
  `content_type` int(11) DEFAULT NULL COMMENT '消息内容类型，0为普通消息，1为带附件消息',
  `create_date` datetime DEFAULT NULL COMMENT '创建日期',
  `discussion_group_id` int(11) DEFAULT NULL COMMENT '讨论组id',
  `from_id` int(11) DEFAULT NULL COMMENT '发送消息用户',
  `msg_type` int(11) DEFAULT NULL COMMENT '消息类型，0--人人消息 1--群消息 2--讨论组消息',
  `status` int(11) DEFAULT NULL COMMENT 'status',
  `to_id` int(11) DEFAULT NULL COMMENT '接受消息用户',
  `transfer` tinyint(1) NOT NULL COMMENT '是否需要转发，如果传入的是clinet则需要转发，如果是服务端则说明本就是转发消息不需要转发',
  `type` int(11) DEFAULT NULL COMMENT '消息类型，0--发送消息，1--接收消息',
  `uuid` varchar(150) DEFAULT NULL COMMENT 'uuid',
  `attachment_id` int(11) DEFAULT NULL COMMENT '附件id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




