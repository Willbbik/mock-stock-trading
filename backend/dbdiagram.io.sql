Table users [note: '사용자'] {
  id bigint [primary key, note: '사용자 ID']
  login_id varchar [note: '로그인 ID']
  password varchar
  name varchar
  email varchar
  created_at timestamp
}

Table user_account [note: '사용자 주식 계좌'] {
  account_id bigint [primary key, note: '계좌 ID']
  user_id bigint [note: '사용자 ID', not null]
  total_asset decimal [note: '총 자산 (현금 + 주식 평가액)', not null]
  deposit_cash decimal [note: '현금 잔고', not null]
  is_active boolean [note: '계좌 활성화 여부']
  created_at timestamp
}

Table user_holding [note: '사용자 주식 보유 현황'] {
  holding_id bigint [primary key, note: '주식 보유 현황 ID']
  account_id bigint [note: '계좌 ID', not null]
  symbol varchar [note: '종목 코드', not null]
  quantity decimal [note: '보유 수량', not null]
  average_purchase_cost decimal [note: '평균 매수 금액']
  reserved_sell_qty decimal [note: '매도 주문 대기 수량']
  created_at timestamp
}

Table stock_order [note: '주문 관리'] {
  order_id bigint [primary key, note: '주문 ID']
  account_id bigint [note: '계좌 ID', not null]
  symbol varchar [note: '종목 코드', not null]
  status varchar [note: '주문 상태', not null]
  type varchar [note: '주문 유형', not null]
  price decimal [note: '주문 가격', not null]
  quantity decimal [note: '주문 수량', not null]
  filled_quantity decimal [note: '체결된 주문 수량', not null]
  created_at timestamp
}

Table trade_history [note: '거래 체결 기록'] {
  trade_id bigint [primary key, note: '거래 기록 ID']
  order_id bigint [note: '주문 ID', not null]
  symbol varchar [note: '종목 코드', not null]
  price decimal [note: '실제 체결된 가격', not null]
  quantity decimal [note: '체결 주문 수량', not null]
  created_at timestamp
}



Ref: "users"."id" - "user_account"."user_id"

Ref: "user_account"."account_id" < "user_holding"."account_id"

Ref: "user_account"."account_id" < "stock_order"."account_id"

Ref: "user_holding"."holding_id" < "trade_history"."order_id"