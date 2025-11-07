CREATE TABLE `users` (
                         `id` bigint PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT '사용자 ID',
                         `login_id` varchar(255) NOT NULL COMMENT '로그인 ID',
                         `password` varchar(255) NOT NULL COMMENT '비밀번호',
                         `name` varchar(255) NOT NULL COMMENT '이름',
                         `email` varchar(255) NOT NULL COMMENT '이메일',
                         `created_at` timestamp
) COMMENT = '사용자';

CREATE TABLE `user_account` (
                                `account_id` bigint PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT '계좌 ID',
                                `user_id` bigint NOT NULL COMMENT '사용자 ID',
                                `total_asset` decimal NOT NULL COMMENT '총 자산 (현금 + 주식 평가액)',
                                `deposit_cash` decimal NOT NULL COMMENT '현금',
                                `is_active` boolean NOT NULL COMMENT '계좌 활성화 여부',
                                `created_at` timestamp
) COMMENT = '사용자 계좌';

CREATE TABLE `user_holding` (
                                `holding_id` bigint PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT '주식 보유 현황 ID',
                                `account_id` bigint NOT NULL COMMENT '계좌 ID',
                                `symbol` varchar(255) NOT NULL COMMENT '종목 코드',
                                `quantity` decimal NOT NULL COMMENT '보유 수량',
                                `average_purchase_cost` decimal NOT NULL COMMENT '평균 매수 금액',
                                `reserved_sell_qty` decimal NOT NULL COMMENT '매도 주문 대기 수량',
                                `created_at` timestamp
) COMMENT = '사용자 주식 보유 현황';

CREATE TABLE `stock_order` (
                               `order_id` bigint PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT '주문 ID',
                               `account_id` bigint NOT NULL COMMENT '계좌 ID',
                               `symbol` varchar(255) NOT NULL COMMENT '종목 코드',
                               `status` varchar(255) NOT NULL COMMENT '주문 상태',
                               `type` varchar(255) NOT NULL COMMENT '주문 유형',
                               `price` decimal NOT NULL COMMENT '주문 가격',
                               `quantity` decimal NOT NULL COMMENT '주문 수량',
                               `filled_quantity` decimal NOT NULL COMMENT '체결된 주문 수량',
                               `created_at` timestamp
) COMMENT = '주문 관리';

CREATE TABLE `trade_history` (
                                 `trade_id` bigint PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT '거래 기록 ID',
                                 `order_id` bigint NOT NULL COMMENT '주문 ID',
                                 `symbol` varchar(255) NOT NULL COMMENT '종목 코드',
                                 `price` decimal NOT NULL COMMENT '실제 체결된 가격',
                                 `quantity` decimal NOT NULL COMMENT '체결 주문 수량',
                                 `created_at` timestamp
) COMMENT = '거래 체결 기록';

ALTER TABLE `user_account` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `user_holding` ADD FOREIGN KEY (`account_id`) REFERENCES `user_account` (`account_id`);

ALTER TABLE `stock_order` ADD FOREIGN KEY (`account_id`) REFERENCES `user_account` (`account_id`);

ALTER TABLE `trade_history` ADD FOREIGN KEY (`order_id`) REFERENCES `user_holding` (`holding_id`);