--
-- Create table `authority`
--

CREATE TABLE `authority` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `authority` VARCHAR(20) NOT NULL COMMENT '权限',
  `userId` INT(11) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_id_authority` (`userId`, `authority`),
  CONSTRAINT `fk_authority_ref_user_id` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
  ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '权限表';