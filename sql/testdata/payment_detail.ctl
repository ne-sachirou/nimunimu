LOAD DATA
INFILE 'payment_detail.csv'
BADFILE 'payment_detail.bad'
DISCARDFILE 'payment_detail.dsc'
APPEND INTO TABLE payment_detail
FIELDS TERMINATED BY ','
(
  payment_id,
  our_order_id
)
