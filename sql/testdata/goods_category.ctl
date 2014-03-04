LOAD DATA
INFILE 'goods_category.csv'
BADFILE 'goods_category.bad'
DISCARDFILE 'goods_category.dsc'
APPEND INTO TABLE goods_category
FIELDS TERMINATED BY ','
(
  id expression "goods_category_pk_seq.nextval",
  name
)
