LOAD DATA
INFILE 'quotation_request_sheet.csv'
BADFILE 'quotation_request_sheet.bad'
DISCARDFILE 'quotation_request_sheet.dsc'
APPEND INTO TABLE quotation_request_sheet
FIELDS TERMINATED BY ','
(
  id expression "quotation_request_sheet_pk_seq.nextval",
  amount,
  tax
)
