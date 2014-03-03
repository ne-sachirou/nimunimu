LOAD DATA
INFILE 'customer.csv'
BADFILE 'customer.bad'
DISCARDFILE 'customer.dsc'
APPEND INTO TABLE customer
FIELDS TERMINATED BY ','
(
  id expression "customer_pk_seq.nextval",
  name,
  zipcode,
  address,
  tel,
  fax,
  person,
  billing_cutoff_date
)
