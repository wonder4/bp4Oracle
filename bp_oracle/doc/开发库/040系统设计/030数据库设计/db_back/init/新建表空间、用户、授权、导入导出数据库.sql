-- 建表空间与用户
create tablespace bp_oracle2 logging datafile '/u02/oradata/devdb/BP_ORACLE2.DBF' size 500m autoextend on next 50m maxsize 1024m extent management local; 
create user bp_oracle2 identified by bp_oracle2 default tablespace bp_oracle2; 

-- 授权
grant connect,resource to bp_oracle2 ; --基础权限
grant create view to bp_oracle2 ;  --导入视图权限
grant read,write on directory dump_dir to bp_oracle2 ;  --数据通道授权
grant exp_full_database to bp_oracle2 ;  --导出授权
grant imp_full_database to bp_oracle2 ;  --导入授权

-- 导出数据库
expdp bp_oracle2/bp_oracle2 schemas=bp_oracle2 DUMPFILE=bp_oracle_20130815.dmp DIRECTORY=DUMP_DIR JOB_NAME=full

-- 导入数据库
impdp newuser/newuserpassword directory=dump_dir dumpfile=bp_oracle_20130815.dmp remap_schema=bp_oracle2:newuserschema remap_tablespace=bp_oracle2:newusertablespace