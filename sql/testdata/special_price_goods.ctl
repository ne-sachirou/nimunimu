LOAD DATA
INFILE 'special_price_goods.csv'
BADFILE 'special_price_goods.bad'
DISCARDFILE 'special_price_goods.dsc'
APPEND INTO TABLE special_price_goods
FIELDS TERMINATED BY ','
(
  goods_id,
  customer_id,
  price
)
