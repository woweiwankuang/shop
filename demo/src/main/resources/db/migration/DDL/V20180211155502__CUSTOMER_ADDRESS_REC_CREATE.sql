--
-- table create `customer_address_rec`
--

CREATE TABLE `customer_address_rec` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID，自增',
  `customerId` int(11) NOT NULL COMMENT '顾客id',
  `address` text NOT NULL COMMENT '邮寄地址',
  PRIMARY KEY (`id`),
  INDEX `idx_customer_address_rec_customer_id` (`customerId` ASC)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='顾客地址表';