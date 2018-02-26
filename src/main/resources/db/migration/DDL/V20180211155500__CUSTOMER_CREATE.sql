--
-- table create `customer`
--

CREATE TABLE `customer` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID，自增',
  `createTime` BIGINT(20) NOT NULL COMMENT '创建时间',
  `lastModified` BIGINT(20) NOT NULL COMMENT '最近一次修改时间',
  `realName` VARCHAR(40) NOT NULL COMMENT '用户名称',
  `phoneNum` VARCHAR(11) NOT NULL COMMENT '手机号码',
  PRIMARY KEY (`id`),
  INDEX `idx_customer_phone_num` (`phoneNum` ASC)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='顾客表';