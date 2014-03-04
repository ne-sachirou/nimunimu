LOAD DATA
INFILE 'store.csv'
BADFILE 'store.bad'
DISCARDFILE 'store.dsc'
APPEND INTO TABLE store
FIELDS TERMINATED BY ','
(
  place,
  goods_id,
  goods_number
)
