create table lisjob
(
	ID bigint not null,
	JOB varchar(255) not null comment '任务名称',
	MODULEID varchar(255) not null,
	primary key (ID, MODULEID)
)
;

create index FK_ID_JOB
	on lisjob (MODULEID)
;

create table lismodule
(
	ID varchar(255) not null
		primary key,
	MODULE varchar(255) not null comment '模块名称'
)
;

alter table lisjob
	add constraint FK_ID_JOB
		foreign key (MODULEID) references lismonitor.lismodule (ID)
;

create table lisrule
(
	ID bigint auto_increment
		primary key,
	RULESQL varchar(255) not null comment 'SQL描述',
	RULE varchar(255) not null comment '规则描述',
	MODULEID varchar(255) null,
	JOBID bigint null,
	STATE bigint null,
	constraint FK_ID_RULE
		foreign key (MODULEID, JOBID) references lismonitor.lisjob (MODULEID, ID)
)
;

create index FK_ID_RULE
	on lisrule (MODULEID, JOBID)
;

create table task_schedule_job
(
	job_id bigint auto_increment
		primary key,
	create_time timestamp null,
	update_time timestamp default CURRENT_TIMESTAMP null,
	job_name varchar(255) null,
	job_group varchar(255) null,
	job_status varchar(255) null,
	cron_expression varchar(255) not null,
	description varchar(255) null,
	bean_class varchar(255) null,
	is_concurrent varchar(255) null comment '1',
	spring_id varchar(255) null,
	method_name varchar(255) not null,
	constraint name_group
		unique (job_name, job_group)
)
;

