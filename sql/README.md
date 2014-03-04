Test data in Excel file
=======================
Test data is written in the `testdata.xlsm`. The file contains all test data list and a macro that write out them to CSV files. So don't edit CSV files directly.

How to import test data
=======================
How to import test datas to Oracle DB?

1. Drop and re-create schemes.
2. Load test data.

Drop and re-create schemes
--------------------------
Drop and re-create schemes.

1. Connect Oracle DB with your SQL*Plus CUI client.
2. Execute DDL in the scheme.sql

Load test data
--------------
Load test data.

1. Start cmd.exe
2. cd to the `sql\testdata` directory.
3. Execute `ruby sqlldr.rb`. It's execute `sqlldr.bat %1` sequentially, and `sqlldr.bat` runs SQL*Loader with each ctl files.
