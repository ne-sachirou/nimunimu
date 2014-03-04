LOAD DATA
INFILE 'our_order_sheet.csv'
BADFILE 'our_order_sheet.bad'
DISCARDFILE 'our_order_sheet.dsc'
APPEND INTO TABLE our_order_sheet
FIELDS TERMINATED BY ','
(
  id expression "our_order_sheet_pk_seq.nextval",
  amount,
  tax
)
