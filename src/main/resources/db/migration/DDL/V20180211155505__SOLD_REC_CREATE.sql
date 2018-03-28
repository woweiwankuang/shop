--
-- table create `sold_rec`
--

CREATE TABLE `sold_rec` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID，自增',
  `createTime` BIGINT(20) NOT NULL COMMENT '创建时间',
  `lastModified` BIGINT(20) NOT NULL COMMENT '最近一次修改时间',
  `customerId` int(11) NOT NULL COMMENT '顾客id',
  `productName` VARCHAR(20) NOT NULL COMMENT '产品名称',
  `address` text NOT NULL COMMENT '邮寄地址',
  `num` int(11) NOT NULL COMMENT '产品数量',
  `cost` decimal(5,1) NOT NULL COMMENT '产品总成本',
  `price` decimal(5,1) NOT NULL COMMENT '产品总售价',
  `postage` int(11) NOT NULL COMMENT '邮费',
  `profit` decimal(5,1) NOT NULL COMMENT '利润',
  `soldTime` BIGINT(20) NOT NULL COMMENT '卖出时间',
  `haveSend` tinyint(1) NOT NULL COMMENT '是否已经寄出',
  `trackingNumber` VARCHAR(15) COMMENT '快递单号',
  `userId` int(11) NOT NULL COMMENT '所属用户id',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='销售记录表';