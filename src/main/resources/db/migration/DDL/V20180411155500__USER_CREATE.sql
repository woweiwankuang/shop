--
-- Create table `user`
--

CREATE TABLE `user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` VARCHAR(50) NOT NULL COMMENT '用户名',
  `password` VARCHAR(80) NOT NULL COMMENT '密码',
  `enabled` TINYINT(1)  NOT NULL DEFAULT '1' COMMENT '帐号是否启用',
  `accountNonExpired` TINYINT(1)  NOT NULL DEFAULT '1' COMMENT '帐号是否未过期',
  `accountNonLocked` TINYINT(1)  NOT NULL DEFAULT '1' COMMENT '帐号是否未锁定',
  `credentialsNonExpired` TINYINT(1) NOT NULL DEFAULT '1' COMMENT '密码是否未过期',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '用户表';