drop table goods_category;
drop sequence goods_category_pk_seq;
drop table member;

-- 社員account
create table member (
  id                   varchar2(64) primary key,
  name                 varchar2(50) not null,
  password             varchar2(255) not null,
  is_password_resetted number(1) default 0,
  authority            varchar2(10) not null
    check (authority in ('ADMIN', 'SALES', 'SALES_MANAGER', 'STORE', 'STORE_MANAGER', 'ACCOUNTING'))
);

-- 商品category
create sequence goods_category_pk_seq;
create table goods_category (
  id   number(4) primary key,
  name varchar2(100) unique not null
);
