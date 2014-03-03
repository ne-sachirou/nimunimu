drop table payment_detail;
drop sequence payment_pk_seq;
drop table payment;
drop sequence our_order_pk_seq;
drop table our_order;
drop sequence our_order_sheet_detail_pk_seq;
drop table our_order_sheet_detail;
drop sequence our_order_sheet_pk_seq;
drop table our_order_sheet;
drop table billing_detail;
drop sequence billing_pk_seq;
drop table billing;
drop sequence customer_order_pk_seq;
drop table customer_order;
drop sequence customer_order_sheet_detail_pk_seq;
drop table customer_order_sheet_detail;
drop table customer_order_sheet;
drop sequence customer_order_sheet_pk_seq;
drop sequence quotation_request_sheet_detail_pk_seq;
drop table quotation_request_sheet_detail;
drop sequence quotation_request_sheet_pk_seq;
drop table quotation_request_sheet;
drop table stock_taking_result;
drop table store;
drop table special_price_goods;
drop sequence goods_pk_seq;
drop table goods;
drop sequence goods_category_pk_seq;
drop table goods_category;
drop sequence supplier_pk_seq;
drop table supplier;
drop sequence customer_pk_seq;
drop table customer;
drop table notification;
drop sequence notification_pk_seq;
drop table member;

-- 社員account
create table member(
  id                   varchar2(64)  primary key,
  name                 varchar2(50)  not null,
  password             varchar2(255) not null,
  is_password_resetted number(1)     default 0,
  authority            varchar2(20)  not null,
  check (authority in ('ADMIN', 'SALES', 'SALES_MANAGER', 'STORE', 'STORE_MANAGER', 'ACCOUNTING'))
);

-- お報せ
create sequence notification_pk_seq;
create table notification(
  id         number(10)    primary key,
  member_id  varchar2(64)  references member(id),
  message    varchar2(400) not null,
  create_at  date,
  deleted_at date
);

-- 顧客
create sequence customer_pk_seq;
create table customer(
  id                  number(10)    primary key,
  name                varchar2(100) not null,
  zipcode             varchar2(7)   not null,
  address             varchar2(100) not null,
  tel                 varchar2(15)  not null,
  fax                 varchar2(15)  not null,
  person              varchar2(50)  not null,
  billing_cutoff_date number(2),
  credit_limit        number(8)
);

-- 仕入先
create sequence supplier_pk_seq;
create table supplier(
  id                  number(10)    primary key,
  name                varchar2(100) not null,
  zipcode             varchar2(7)   not null,
  address             varchar2(100) not null,
  tel                 varchar2(15)  not null,
  fax                 varchar2(15)  not null,
  person              varchar2(50)  not null,
  billing_cutoff_date number(2)
);

-- 商品category
create sequence goods_category_pk_seq;
create table goods_category(
  id   number(4)     primary key,
  name varchar2(100) unique not null
);

-- 商品
create sequence goods_pk_seq;
create table goods(
  id                varchar2(50)  primary key,
  name              varchar2(100) not null,
  goods_category_id number(4)     references goods_category(id),
  supplier_id       number(10)    references supplier(id),
  price             number(7)     not null
);

-- 特別価格の商品-顧客
create table special_price_goods(
  goods_id    varchar2(50) references goods(id),
  customer_id number(10)   references customer(id),
  price       number(7)    not null,
  primary key (goods_id, customer_id)
);

-- 在庫
create table store(
   place        varchar2(5),
   goods_id     varchar2(50) references goods(id),
   goods_number number(5)    not null,
   primary key (place, goods_id)
);

-- 棚卸結果
create table stock_taking_result(
  stock_taking_date     date,
  place                 varchar2(5),
  goods_id              varchar2(50),
  expected_goods_number number(5),
  actual_goods_number   number(5),
  primary key (stock_taking_date, place, goods_id),
  foreign key (place, goods_id) references store(place, goods_id)
);

-- 見積依頼書
create sequence quotation_request_sheet_pk_seq;
create table quotation_request_sheet(
  id         number(20) primary key,
  amount     number(8)  not null,
  tax        number(7)  not null,
  created_at timestamp  default systimestamp,
  updated_at timestamp  default systimestamp,
  deleted_at timestamp
);

-- 見積依頼書明細
create sequence quotation_request_sheet_d_pk_s;
create table quotation_request_sheet_detail(
  id                         number(3),
  quotation_request_sheet_id number(20)   references quotation_request_sheet(id),
  goods_id                   varchar2(50) references goods(id),
  price                      number(7)    not null,
  goods_number               number(3)    not null,
  primary key (id, quotation_request_sheet_id)
);

-- 注文書
create sequence customer_order_sheet_pk_seq;
create table customer_order_sheet(
  id         number(20) primary key,
  amount     number(8)  not null,
  tax        number(7)  not null,
  created_at timestamp default systimestamp,
  updated_at timestamp default systimestamp,
  deleted_at timestamp
);

-- 注文書明細
create sequence customer_order_sheet_dtl_pk_s;
create table customer_order_sheet_detail(
  id                      number(3),
  customer_order_sheet_id number(20)   references customer_order_sheet(id),
  goods_id                varchar2(50) references goods(id),
  price                   number(7)    not null,
  goods_number            number(3)    not null,
  primary key (id, customer_order_sheet_id)
);

-- 受注flow
create sequence customer_order_pk_seq;
create table customer_order(
  id                         number(20)   primary key,
  customer_id                number(10)   references customer(id),
  quotation_request_sheet_id number(20)   references quotation_request_sheet(id),
  customer_order_sheet_id    number(20)   references customer_order_sheet(id),
  member_id                  varchar2(64) references member(id),
  status                     varchar2(15) not null,
  check (status in ('YET_ESTIMATE', 'ESTIMATED', 'YET_ACCEPT', 'YET_DELIVER', 'DELIVERED'))
);

-- 請求
create sequence billing_pk_seq;
create table billing(
  id          number(20)   primary key,
  customer_id number(10)   references customer(id),
  member_id   varchar2(64) references member(id),
  status      varchar2(10) not null,
  check (status in ('YET_CLAIM', 'YET_PAY', 'PAYED'))
);

-- 請求明細
create table billing_detail(
  billing_id        number(20) references billing(id),
  customer_order_id number(20) references customer_order(id),
  primary key (billing_id, customer_order_id)
);

-- 発注書
create sequence our_order_sheet_pk_seq;
create table our_order_sheet(
  id         number(20) primary key,
  amount     number(8)  not null,
  tax        number(7)  not null,
  created_at timestamp default systimestamp,
  updated_at timestamp default systimestamp,
  deleted_at timestamp
);

-- 発注書明細
create sequence our_order_sheet_detail_pk_seq;
create table our_order_sheet_detail(
  id                 number(3),
  our_order_sheet_id number(20)   references our_order_sheet(id),
  goods_id           varchar2(50) references goods(id),
  price              number(7)    not null,
  goods_number       number(3)    not null,
  primary key (id, our_order_sheet_id)
);

-- 発注
create sequence our_order_pk_seq;
create table our_order(
  id                 number(20)   primary key,
  supplier_id        number(10)   references supplier(id),
  our_order_sheet_id number(20)   references our_order_sheet(id),
  member_id          varchar2(64) references member(id),
  status             varchar2(15) not null,
  check (status in ('YET_RECEIPT', 'RECEIPTED'))
);

-- 被請求
create sequence payment_pk_seq;
create table payment(
  id          number(20)   primary key,
  supplier_id number(10)   references supplier(id),
  member_id   varchar2(64) references member(id),
  status      varchar2(7)  not null,
  check (status in ('YET_PAY', 'PAYED'))
);

-- 被請求明細
create table payment_detail(
  payment_id   number(20) references payment(id),
  our_order_id number(20) references our_order(id),
  primary key (payment_id, our_order_id)
);
