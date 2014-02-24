-- 社員account
create table account (
  id                   varchar2(64) primary key,
  name                 varchar2(50),
  password             varchar2(255),
  is_password_resetted number(1),
  authority            varchar2(10)
    check (authority in ('ADMIN', 'SALES', 'SALES_MANAGER', 'STORE', 'STORE_MANAGER', 'ACCOUNTING'))
);
