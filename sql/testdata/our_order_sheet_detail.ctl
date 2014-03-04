LOAD DATA
INFILE 'our_order_sheet_detail.csv'
BADFILE 'our_order_sheet_detail.bad'
DISCARDFILE 'our_order_sheet_detail.dsc'
APPEND INTO TABLE our_order_sheet_detail
FIELDS TERMINATED BY ','
(
  id expression "our_order_sheet_detail_pk_seq.nextval",
  our_order_sheet_id,
  goods_id,
  price,
  goods_number
)
