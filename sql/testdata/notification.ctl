LOAD DATA
INFILE 'notification.csv'
BADFILE 'notification.bad'
DISCARDFILE 'notification.dsc'
APPEND INTO TABLE notification
FIELDS TERMINATED BY ','
(
  id expression "notification_pk_seq.nextval",
  member_id,
  message
)
