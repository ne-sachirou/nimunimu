LOAD DATA
INFILE 'our_order.csv'
BADFILE 'our_order.bad'
DISCARDFILE 'our_order.dsc'
APPEND INTO TABLE our_order
FIELDS TERMINATED BY ','
(
  id expression "our_order_pk_seq.nextval",
  supplier_id,
  our_order_sheet_id,
  member_id,
  status
)
