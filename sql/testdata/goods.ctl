LOAD DATA
INFILE 'goods.csv'
BADFILE 'goods.bad'
DISCARDFILE 'goods.dsc'
APPEND INTO TABLE goods
FIELDS TERMINATED BY ','
(
  id,
  name,
  goods_category_id,
  supplier_id,
  price
)
