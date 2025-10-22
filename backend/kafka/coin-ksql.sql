-- 실시간 인기 TOP3 코인 조회용 스트림 생성

-- 1
CREATE STREAM coin_trade_hist_stream (
    ID VARCHAR KEY,
    market VARCHAR,
    buy_price DOUBLE,
    buy_quantity INT,
    create_dt VARCHAR,
    buy_date VARCHAR,
    type VARCHAR
)
WITH (
    KAFKA_TOPIC='mysqlserver.test1.TB_COIN_TRADE_HIST',
    VALUE_FORMAT='JSON',
    PARTITIONS=1
);

-- 2
CREATE TABLE coin_trades AS
SELECT
    market,
    COUNT(*) AS order_count
FROM coin_trade_hist_stream
GROUP BY market
EMIT CHANGES;