LOAD DATA
INFILE 'quotation_request_sheet_detail.csv'
BADFILE 'quotation_request_sheet_detail.bad'
DISCARDFILE 'quotation_request_sheet_detail.dsc'
APPEND INTO TABLE quotation_request_sheet_detail
FIELDS TERMINATED BY ','
(
  id expression "quotation_rqst_sht_dtl_pk_s.nextval",
  quotation_request_sheet_id,
  goods_id,
  price,
  goods_number
)
