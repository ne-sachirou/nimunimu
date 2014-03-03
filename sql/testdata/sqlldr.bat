@echo off

set bin_dir=C:\app\ne_Sachirou\product\11.1.0\client_1\BIN
set user_id=ora103
set password=ora103
set server_name=TSTDSV03
set instance_name=orcl2
set user=%user_id%/%password%@%server_name%/%instance_name%

set name=%1
set ctl_dir=C:\Users\ne_Sachirou\BTSync\HALt\JV33\nimunimu\sql\testdata
%bin_dir%\sqlldr %user% control=%ctl_dir%\%name%.ctl log=%ctl_dir%\%name%.log
