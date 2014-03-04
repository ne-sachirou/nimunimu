LOAD DATA
INFILE 'goods.csv'
BADFILE 'goods.bad'
DISCARDFILE 'goods.dsc'
APPEND INTO TABLE goods
FIELDS TERMINATED BY ','
(
  id expression "goods_pk_seq.nextval",
  name,
  goods_category_id,
  supplier_id,
  price
)
