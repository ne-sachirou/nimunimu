LOAD DATA
INFILE 'supplier.csv'
BADFILE 'supplier.bad'
DISCARDFILE 'supplier.dsc'
APPEND INTO TABLE supplier
FIELDS TERMINATED BY ','
(
  id expression "supplier_pk_seq.nextval",
  name,
  zipcode,
  address,
  tel,
  fax,
  person,
  billing_cutoff_date
)
