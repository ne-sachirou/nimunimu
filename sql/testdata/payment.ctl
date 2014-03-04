LOAD DATA
INFILE 'payment.csv'
BADFILE 'payment.bad'
DISCARDFILE 'payment.dsc'
APPEND INTO TABLE payment
FIELDS TERMINATED BY ','
(
  id expression "payment_pk_seq.nextval",
  supplier_id,
  member_id,
  status
)
