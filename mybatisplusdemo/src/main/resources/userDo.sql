CREATE TABLE `user_do` (
  `id` tinyint(2) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_name` varchar(255) NOT NULL DEFAULT '' COMMENT '用户姓名',
  `sex` varchar(10) NOT NULL DEFAULT '男' COMMENT '性别',
  `age` tinyint(4) NOT NULL DEFAULT '0' COMMENT '年龄',
  `phone_number` varchar(100) NOT NULL DEFAULT '13888888888' COMMENT '电话',
  `address` varchar(200) DEFAULT '' COMMENT '用户地址',
  `pwd` varchar(50) NOT NULL DEFAULT '' COMMENT '用户密码',
  `email` varchar(50) DEFAULT '' COMMENT '用户个人邮箱',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `pwd_question` varchar(100) NOT NULL DEFAULT '' COMMENT '密码提示问题',
  `pwd_answer` varchar(100) NOT NULL DEFAULT '' COMMENT '密码问题提示答案',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;