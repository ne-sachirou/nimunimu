LOAD DATA
INFILE 'billing_detail.csv'
BADFILE 'billing_detail.bad'
DISCARDFILE 'billing_detail.dsc'
APPEND INTO TABLE billing_detail
FIELDS TERMINATED BY ','
(
  billing_id,
  customer_order_id
)
