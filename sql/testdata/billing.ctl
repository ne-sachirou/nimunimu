LOAD DATA
INFILE 'billing.csv'
BADFILE 'billing.bad'
DISCARDFILE 'billing.dsc'
APPEND INTO TABLE billing
FIELDS TERMINATED BY ','
(
  id expression "billing_pk_seq.nextval",
  customer_id,
  member_id,
  status
)
