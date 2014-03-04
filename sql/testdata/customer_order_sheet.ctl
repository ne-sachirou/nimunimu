LOAD DATA
INFILE 'customer_order_sheet.csv'
BADFILE 'customer_order_sheet.bad'
DISCARDFILE 'customer_order_sheet.dsc'
APPEND INTO TABLE customer_order_sheet
FIELDS TERMINATED BY ','
(
  id expression "customer_order_sheet_pk_seq.nextval",
  amount,
  tax
)
