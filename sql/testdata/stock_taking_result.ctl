LOAD DATA
INFILE 'stock_taking_result.csv'
BADFILE 'stock_taking_result.bad'
DISCARDFILE 'stock_taking_result.dsc'
APPEND INTO TABLE stock_taking_result
FIELDS TERMINATED BY ','
(
  stock_taking_date,
  place,
  goods_id,
  expected_goods_number,
  actual_goods_number
)
