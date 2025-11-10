CREATE TABLE `users`
(
    `id`         varchar(30) PRIMARY KEY NOT NULL COMMENT '사용자 ID',
    `login_id`   varchar(255)            NOT NULL COMMENT '로그인 ID',
    `password`   varchar(255)            NOT NULL COMMENT '비밀번호',
    `name`       varchar(255)            NOT NULL COMMENT '이름',
    `email`      varchar(255)            NOT NULL COMMENT '이메일',
    `created_at` timestamp
) COMMENT = '사용자';

CREATE TABLE `user_account`
(
    `account_id`     varchar(30) PRIMARY KEY NOT NULL COMMENT '계좌 ID',
    `user_id`        varchar(30)             NOT NULL COMMENT '사용자 ID',
    `account_number` varchar(255)            NOT NULL COMMENT '사용자 계좌 번호',
    `total_asset`    decimal                 NOT NULL COMMENT '총 자산 (현금 + 주식 평가액)',
    `deposit_cash`   decimal                 NOT NULL COMMENT '현금',
    `is_active`      boolean                 NOT NULL COMMENT '계좌 활성화 여부',
    `created_at`     timestamp
) COMMENT = '사용자 계좌';

CREATE TABLE `user_holding`
(
    `holding_id`        varchar(30) PRIMARY KEY NOT NULL COMMENT '주식 보유 현황 ID',
    `account_id`        varchar(30)             NOT NULL COMMENT '계좌 ID',
    `symbol`            varchar(255)            NOT NULL COMMENT '종목 코드',
    `quantity`          INT                     NOT NULL COMMENT '보유 수량',
    `reserved_sell_qty` decimal                 NOT NULL COMMENT '매도 주문 대기 수량',
    `created_at`        timestamp
) COMMENT = '사용자 주식 보유 현황';

CREATE TABLE `trade_order`
(
    `order_id`   varchar(30) PRIMARY KEY NOT NULL COMMENT '거래 주문 ID',
    `account_id` varchar(30)             NOT NULL COMMENT '계좌 ID',
    `stock_code` varchar(255)            NOT NULL COMMENT '종목 코드',
    `status`     varchar(255)            NOT NULL COMMENT '주문 상태',
    `type`       varchar(255)            NOT NULL COMMENT '주문 유형',
    `unit_price` decimal                 NOT NULL COMMENT '한 주당 주문 가격',
    `quantity`   INT                     NOT NULL COMMENT '주문 수량',
    `created_at` timestamp
) COMMENT = '거래 주문';

CREATE TABLE trade_order_history
(
    history_id          varchar(30) PRIMARY KEY NOT NULL COMMENT '거래 주문 이력 ID',
    order_id            varchar(30)             NOT NULL COMMENT '주문 ID',
    status              VARCHAR(255)            NOT NULL COMMENT '주문 상태',
    previous_quantity   INT NULL COMMENT '이전 주문 수량',
    new_quantity        INT NULL COMMENT '변경된 주문 수량',
    previous_unit_price decimal NULL COMMENT '이전 주문 금액',
    new_unit_price      decimal NULL COMMENT '변경된 주문 금액',
    history_created_at  timestamp               NOT NULL COMMENT '상태 변경 일시'
) COMMENT '거래 주문 이력';


CREATE TABLE `trade`
(
    `trade_id`        varchar(30) PRIMARY KEY NOT NULL COMMENT '체결 거래 기록 ID',
    `order_id`        varchar(30)             NOT NULL COMMENT '주문 ID',
    `status`          varchar(255)            NOT NULL COMMENT '거래 상태',
    `stock_code`      varchar(255)            NOT NULL COMMENT '종목 코드',
    `execution_price` decimal                 NOT NULL COMMENT '한 주당 체결가',
    `quantity`        INT                     NOT NULL COMMENT '체결 수량',
    `executed_amount` decimal                 NOT NULL COMMENT '총 체결 금액',
    `created_at`      timestamp
) COMMENT = '체결 거래 기록';

CREATE TABLE `stock`
(
    `stock_code`  varchar(30) PRIMARY KEY NOT NULL COMMENT '종목 코드 (예: 005930)',
    `stock_name`  VARCHAR(100)            NOT NULL COMMENT '종목명 (예: 삼성전자)',
    `market_type` VARCHAR(10)             NOT NULL COMMENT '시장 구분 (코스피, 코스닥 등)',
    `currency`    VARCHAR(10)             NOT NULL COMMENT '거래 화폐 단위 (KRW, USD 등)',
    `is_use`      boolean                 NOT NULL COMMENT '사용 여부'
) COMMENT = '주식 종목 정보';

ALTER TABLE `user_account`
    ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `user_holding`
    ADD FOREIGN KEY (`account_id`) REFERENCES `user_account` (`account_id`);

ALTER TABLE `trade_order`
    ADD FOREIGN KEY (`account_id`) REFERENCES `user_account` (`account_id`);

ALTER TABLE `trade`
    ADD FOREIGN KEY (`order_id`) REFERENCES `user_holding` (`holding_id`);