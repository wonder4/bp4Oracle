/*==============================================================*/
/* Table: MAIL_ATTACHMENT                                       */
/*==============================================================*/
create table MAIL_ATTACHMENT 
(
   ID                   NUMBER(16)           not null,
   LINK_ID              NUMBER(16),
   LINK_TAB             VARCHAR2(50),
   FILE_NAME            VARCHAR2(255),
   FILE_TYPE            VARCHAR2(255),
   FILE_SIZE            VARCHAR2(255),
   SAVE_PATH            VARCHAR2(255),
   SAVE_NAME            VARCHAR2(255),
   FILE_DESC            VARCHAR2(255),
   IS_DEL               NUMBER(2),
   constraint PK_MAIL_ATTACHMENT primary key (ID)
);

comment on table MAIL_ATTACHMENT is
'邮件附件表';

comment on column MAIL_ATTACHMENT.ID is
'ID';

comment on column MAIL_ATTACHMENT.LINK_ID is
'关联ID';

comment on column MAIL_ATTACHMENT.LINK_TAB is
'关联表';

comment on column MAIL_ATTACHMENT.FILE_NAME is
'源文件名';

comment on column MAIL_ATTACHMENT.FILE_TYPE is
'文件类型';

comment on column MAIL_ATTACHMENT.FILE_SIZE is
'文件大小';

comment on column MAIL_ATTACHMENT.SAVE_PATH is
'存储路径';

comment on column MAIL_ATTACHMENT.SAVE_NAME is
'存储名称';

comment on column MAIL_ATTACHMENT.FILE_DESC is
'文件说明';

comment on column MAIL_ATTACHMENT.IS_DEL is
'是否删除';


/*==============================================================*/
/* Table: MAIL_MAIN                                             */
/*==============================================================*/
create table MAIL_MAIN 
(
   ID                   NUMBER(16)           not null,
   PAR_ID               NUMBER(16),
   DEPT_ID              NUMBER(16),
   TITLE                NVARCHAR2(30),
   CONTENT              CLOB,
   SEND_USER_ID         NUMBER(16),
   SEND_DATE            DATE,
   ADD_DATE             DATE,
   IS_FLOAT             NUMBER(1),
   constraint PK_MAIL_MAIN primary key (ID)
);

comment on table MAIL_MAIN is
'邮件主表';

comment on column MAIL_MAIN.ID is
'站内信ID';

comment on column MAIL_MAIN.PAR_ID is
'父站内信ID：首次发送为0。回复则为回复的邮件ID';

comment on column MAIL_MAIN.DEPT_ID is
'部门ID【保留字段】';

comment on column MAIL_MAIN.TITLE is
'信息标题：不能超过30个汉字';

comment on column MAIL_MAIN.CONTENT is
'信息内容：不超过2000个汉字';

comment on column MAIL_MAIN.SEND_USER_ID is
'发送人ID';

comment on column MAIL_MAIN.SEND_DATE is
'发送时间';

comment on column MAIL_MAIN.ADD_DATE is
'添加时间';

comment on column MAIL_MAIN.IS_FLOAT is
'是否浮动';


/*==============================================================*/
/* Table: MAIL_PEOP                                             */
/*==============================================================*/
create table MAIL_PEOP 
(
   ID                   NUMBER(16)           not null,
   MAIL_ID              NUMBER(16),
   IS_RECE              NUMBER(2),
   RECE_ID              NUMBER(16),
   RECE_OBJ             NUMBER(16),
   MAIL_STATE           NUMBER(2),
   RE_STATE             NUMBER(2),
   IS_DEL               NUMBER(2),
   DEL_DATE             DATE,
   constraint PK_MAIL_PEOP primary key (ID)
);

comment on table MAIL_PEOP is
'邮件人员表';

comment on column MAIL_PEOP.ID is
'ID';

comment on column MAIL_PEOP.MAIL_ID is
'站内信ID';

comment on column MAIL_PEOP.IS_RECE is
'发送或接收
0：发送
1：接收
2：抄送';

comment on column MAIL_PEOP.RECE_ID is
'接收人员或部门ID';

comment on column MAIL_PEOP.RECE_OBJ is
'接收对象【保留字段】
0：人员ID
1：部门ID';

comment on column MAIL_PEOP.MAIL_STATE is
'信息状态：-1-暂存，0-已接收未查看，1-已发送，2-已查看，3-已回复，4-已删除
这个里面的删除状态是放在垃圾箱里的。
如果在垃圾箱里再点删除，将IS_DEL=1

如果改为删除，则需要把删除前的状态记录到RE_STATE中去。';

comment on column MAIL_PEOP.RE_STATE is
'恢复状态';

comment on column MAIL_PEOP.IS_DEL is
'是否删除： 0：未删除 1：已删除';

comment on column MAIL_PEOP.DEL_DATE is
'删除时间';


-- sys_module 表添加数据
insert into sys_module (MOD_ID, PAR_ID, MOD_NAME, MOD_DESC, MOD_URL, PPDM_CODE, ORDER_VALUE, IS_PUBLIC, IS_LOCK, IS_DEL, SYS_SIGN)
values (8100000000, 1000000000, '站内信', null, null, '15', 8100000000, 1, 0, 0, 0);

insert into sys_module (MOD_ID, PAR_ID, MOD_NAME, MOD_DESC, MOD_URL, PPDM_CODE, ORDER_VALUE, IS_PUBLIC, IS_LOCK, IS_DEL, SYS_SIGN)
values (8100001000, 8100000000, '收件箱', null, 'MailMain.do', '15', 8100001000, 1, 0, 0, 0);

insert into sys_module (MOD_ID, PAR_ID, MOD_NAME, MOD_DESC, MOD_URL, PPDM_CODE, ORDER_VALUE, IS_PUBLIC, IS_LOCK, IS_DEL, SYS_SIGN)
values (8100002000, 8100000000, '发件箱', null, 'MailMain.do?method=list', '15', 8100002000, 1, 0, 0, 0);
