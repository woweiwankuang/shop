--
-- table create `stock`
--

CREATE TABLE `stock` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID，自增',
  `createTime` BIGINT(20) NOT NULL COMMENT '创建时间',
  `lastModified` BIGINT(20) NOT NULL COMMENT '最近一次修改时间',
  `name` VARCHAR(40) NOT NULL COMMENT '名称',
  `num` int(11) NOT NULL COMMENT '数量',
  `price` decimal(5,1) NULL COMMENT '总价',
  `specification` VARCHAR(100) COMMENT '规格',
  `supplier` VARCHAR(30) COMMENT '供应商',
  `userId` int(11) NOT NULL COMMENT '所属用户id',
  PRIMARY KEY (`id`),
  INDEX `idx_stock_user_id` (`userId` ASC)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='库存表';