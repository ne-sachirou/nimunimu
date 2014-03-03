LOAD DATA
INFILE 'customer_order.csv'
BADFILE 'customer_order.bad'
DISCARDFILE 'customer_order.dsc'
APPEND INTO TABLE customer_order
FIELDS TERMINATED BY ','
(
  id expression "customer_order_pk_seq.nextval",
  customer_id,
  quotation_request_sheet_id,
  customer_order_sheet_id,
  member_id,
  status
)
