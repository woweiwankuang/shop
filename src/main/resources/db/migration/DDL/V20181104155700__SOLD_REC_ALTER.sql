--
-- alter table for `sold_rec`
--

ALTER TABLE `sold_rec`
ADD INDEX `idx_sold_rec_user_id` (`userId` ASC),
ADD INDEX `idx_sold_rec_customer_id` (`customerId` ASC);