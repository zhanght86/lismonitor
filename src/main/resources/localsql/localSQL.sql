TRUNCATE task_schedule_job;
INSERT INTO `task_schedule_job` VALUES
  ('1', null, '2017-08-20 14:17:34', 'QYTest', 'QYTestGroup', '1', '0/5 * * * * ?', 'test', 'com.sinosoft.job.lis.qy.QYTest', '1', null, 'doTest');


DROP TABLE JOBCONFIG;
CREATE TABLE JOBCONFIG
(
  id          BIGINT AUTO_INCREMENT    PRIMARY KEY,
  system      VARCHAR(255)             NOT NULL ,
  module      VARCHAR(255)             NOT NULL ,
  job_id      VARCHAR(255)             NOT NULL ,
  job_name    VARCHAR(255)             NULL,
  sql_id      BIGINT                   NOT NULL ,
  sql_statement         VARCHAR(2000)            NOT NULL ,
  description VARCHAR(2000)            NULL
);

SELECT * FROM JOBCONFIG;
TRUNCATE `jobconfig`;
INSERT INTO `jobconfig` VALUES (1,'LIS','QY','1','数据正确性校验',1,'SELECT count(1)>10 FROM lccont a','保单数需大于10'),(2,'LIS','QY','1','数据正确性校验',2,'SELECT count(1)>0 FROM user a','用户数需大于0');

# 模块表
DROP TABLE IF EXISTS LISMODULE;
CREATE TABLE LISMODULE
(
  ID          VARCHAR(255)    PRIMARY KEY,
  MODULE      VARCHAR(255)    NOT NULL COMMENT '模块名称'
);
SELECT * FROM LISMODULE;
# 任务表
DROP TABLE IF EXISTS LISJOB;
CREATE TABLE LISJOB
(
  ID       BIGINT,
  JOB      VARCHAR(255)    NOT NULL COMMENT '任务名称',
  MODULEID VARCHAR(255),
  CONSTRAINT `PK_ID_JOB` PRIMARY KEY (`ID`,`MODULEID`),
  CONSTRAINT `FK_ID_JOB` FOREIGN KEY (`MODULEID`) REFERENCES `LISMODULE` (`ID`)
);
SELECT * FROM LISJOB;
# 规则表
DROP TABLE IF EXISTS LISRULE;
CREATE TABLE LISRULE
(
  ID       BIGINT  AUTO_INCREMENT,
  RULESQL      VARCHAR(255) NOT NULL COMMENT 'SQL描述',
  RULE     VARCHAR(255) NOT NULL COMMENT '规则描述',
  JOBID    BIGINT,
  CONSTRAINT `PK_ID_RULE` PRIMARY KEY (`ID`,`JOBID`),
  CONSTRAINT `FK_ID_RULE` FOREIGN KEY (`JOBID`) REFERENCES `LISJOB` (`ID`)
);
SELECT * FROM LISRULE;
