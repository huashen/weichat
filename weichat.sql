#应用程序信息表
CREATE TABLE IF NOT EXISTS t_appversion (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长主键',
  `versionCode` varchar(20) NOT NULL COMMENT '版本代码',
  `downloadUrl` text NOT NULL COMMENT '下载链接',
  `createTime` datetime default NULL COMMENT '创建时间',
  `publishTime` datetime default NULL COMMENT '发版时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

#文件表
DROP TABLE IF EXISTS t_attachment;
CREATE TABLE IF NOT EXISTS t_attachment (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长主键',
  `name` varchar(30) NOT NULL COMMENT '文件名',
  `group_name` varchar(50) NOT NULL COMMENT '对应在FastDFS上的group',
  `path` text NOT NULL COMMENT '文件路径',
  `type` int(1) NOT NULL COMMENT '文件类型',
  `size` int(11) NOT NULL COMMENT '文件大小',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `token` varchar(100) NOT NULL COMMENT '用户token',
  `create_time` datetime default NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

#用户表
DROP TABLE IF EXISTS t_user;
CREATE TABLE IF NOT EXISTS t_user (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长主键',
  `account` varchar(30) NOT NULL COMMENT '账号',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `nick_name` varchar(30) NOT NULL COMMENT '昵称',
  `avatar_id` int(11) NOT NULL COMMENT '头像ID',
  `birthday` datetime default NULL COMMENT '生日',
  `signature` varchar(100) DEFAULT NULL COMMENT '个性签名',
  `gender` int(1) NOT NULL default 0 COMMENT '性别',
  PRIMARY KEY (`id`),
  FOREIGN KEY (`avatar_id`) REFERENCES t_attachment(`id`) ON DELETE CASCADE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

#服务器表
CREATE TABLE IF NOT EXISTS t_Attachment (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长主键',
  `ip` varchar(30) NOT NULL COMMENT 'ip地址',
  `port` int(10) NOT NULL COMMENT '端口',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;






