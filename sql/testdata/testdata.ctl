LOAD DATA
INFILE 'member.csv'
BADFILE 'member.bad'
DISCARDFILE 'member.dsc'
APPEND INTO TABLE member
FIELDS TERMINATED BY ','
(
  id,
  name,
  password,
  is_password_resetted,
  authority
)
