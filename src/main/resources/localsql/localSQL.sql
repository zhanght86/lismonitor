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
