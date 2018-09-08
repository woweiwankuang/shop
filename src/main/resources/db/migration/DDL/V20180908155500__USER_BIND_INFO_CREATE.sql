--
-- Create table `user_bind_info`
--

CREATE TABLE `user_bind_info` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `userId` int(11) NOT NULL COMMENT '用户id',
  `code` VARCHAR(20) NOT NULL COMMENT '绑定码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_id` (`userId`),
  UNIQUE KEY `uk_code` (`code`)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '用户绑定信息表';