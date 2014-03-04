LOAD DATA
INFILE 'customer_order_sheet_detail.csv'
BADFILE 'customer_order_sheet_detail.bad'
DISCARDFILE 'customer_order_sheet_detail.dsc'
APPEND INTO TABLE customer_order_sheet_detail
FIELDS TERMINATED BY ','
(
  id expression "customer_order_sheet_dtl_pk_s.nextval",
  customer_order_sheet_id,
  goods_id,
  price,
  goods_number
)
