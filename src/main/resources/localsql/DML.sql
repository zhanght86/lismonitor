INSERT INTO lismonitor.lisjob (ID, JOB, MODULEID) VALUES (1, '红利', 'BQ');
INSERT INTO lismonitor.lisjob (ID, JOB, MODULEID) VALUES (1, '重复收付费', 'CW');
INSERT INTO lismonitor.lisjob (ID, JOB, MODULEID) VALUES (1, '理赔赔穿', 'LP');
INSERT INTO lismonitor.lisjob (ID, JOB, MODULEID) VALUES (1, '提数', 'QD');
INSERT INTO lismonitor.lisjob (ID, JOB, MODULEID) VALUES (1, '数据正确性校验', 'QY');
INSERT INTO lismonitor.lisjob (ID, JOB, MODULEID) VALUES (1, '000112短信批次监控', 'SMS');
INSERT INTO lismonitor.lisjob (ID, JOB, MODULEID) VALUES (1, '应催未催校验', 'XQ');
INSERT INTO lismonitor.lisjob (ID, JOB, MODULEID) VALUES (2, '万能结息', 'BQ');
INSERT INTO lismonitor.lisjob (ID, JOB, MODULEID) VALUES (2, '坏账', 'CW');
INSERT INTO lismonitor.lisjob (ID, JOB, MODULEID) VALUES (2, '银保手续费', 'QD');
INSERT INTO lismonitor.lisjob (ID, JOB, MODULEID) VALUES (2, '000301短信批次监控', 'SMS');
INSERT INTO lismonitor.lisjob (ID, JOB, MODULEID) VALUES (2, '应核销未核销校验', 'XQ');
INSERT INTO lismonitor.lisjob (ID, JOB, MODULEID) VALUES (3, '生存金', 'BQ');
INSERT INTO lismonitor.lisjob (ID, JOB, MODULEID) VALUES (3, '凭证应提未提1', 'CW');
INSERT INTO lismonitor.lisjob (ID, JOB, MODULEID) VALUES (3, '监控失败的批处理', 'SMS');
INSERT INTO lismonitor.lisjob (ID, JOB, MODULEID) VALUES (3, '三合一通知书应生成未生成校验', 'XQ');
INSERT INTO lismonitor.lisjob (ID, JOB, MODULEID) VALUES (4, '投连计价', 'BQ');
INSERT INTO lismonitor.lisjob (ID, JOB, MODULEID) VALUES (4, '凭证应提未提2', 'CW');
INSERT INTO lismonitor.lisjob (ID, JOB, MODULEID) VALUES (4, '监控一直正在处理中的批处理', 'SMS');
INSERT INTO lismonitor.lisjob (ID, JOB, MODULEID) VALUES (4, '应停效未停效校验', 'XQ');
INSERT INTO lismonitor.lisjob (ID, JOB, MODULEID) VALUES (5, '凭证应提未提3', 'CW');
INSERT INTO lismonitor.lisjob (ID, JOB, MODULEID) VALUES (5, '停效满两年终止', 'XQ');
INSERT INTO lismonitor.lisjob (ID, JOB, MODULEID) VALUES (6, '凭证应提未提4', 'CW');
INSERT INTO lismonitor.lisjob (ID, JOB, MODULEID) VALUES (6, '满期终止', 'XQ');
INSERT INTO lismonitor.lisjob (ID, JOB, MODULEID) VALUES (7, '凭证应提未提5', 'CW');
INSERT INTO lismonitor.lisjob (ID, JOB, MODULEID) VALUES (8, '凭证应提未提6', 'CW');
INSERT INTO lismonitor.lismodule (ID, MODULE) VALUES ('BQ', '保全');
INSERT INTO lismonitor.lismodule (ID, MODULE) VALUES ('CW', '财务');
INSERT INTO lismonitor.lismodule (ID, MODULE) VALUES ('LP', '理赔');
INSERT INTO lismonitor.lismodule (ID, MODULE) VALUES ('QD', '渠道');
INSERT INTO lismonitor.lismodule (ID, MODULE) VALUES ('QY', '新契约');
INSERT INTO lismonitor.lismodule (ID, MODULE) VALUES ('SMS', '短信');
INSERT INTO lismonitor.lismodule (ID, MODULE) VALUES ('XQ', '续期');
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '保险期间不得为空', 'QY', 1, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '缴费期间不得为空', 'QY', 1, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '投被保人名字含特殊字符', 'QY', 1, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '投被保人名字含特殊字符', 'QY', 1, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '投被保人名字含特殊字符', 'QY', 1, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '投被保人名字含特殊字符', 'QY', 1, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '受益人名字含特殊字符', 'QY', 1, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '当状态值为有效及退保时，生效日期为空', 'QY', 1, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '当状态值为有效及退保时，生效日期为空', 'QY', 1, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '投保人名字与保单表名字不一致', 'QY', 1, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '被保人名字与保单表名字不一致', 'QY', 1, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '投被保人同一人，但客户号不同', 'QY', 1, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '投保人无地址信息', 'QY', 1, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '被保人无地址信息', 'QY', 1, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '有银行编码没有账户', 'QY', 1, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '被保人职业类别与职业类别表中描述不同的保单', 'QY', 1, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '个险现金分红应计算未计算数据校验', 'BQ', 1, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '个险保额分红应计算未计算数据校验', 'BQ', 1, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '个险现金分红应分配红利未分配数据校验', 'BQ', 1, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '红利账户总表与明细汇总不一致校验', 'BQ', 1, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '红利分配金额为负校验', 'BQ', 1, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '万能账户应结息未结息', 'BQ', 2, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '万能账户总表与明细汇总不一致校验', 'BQ', 2, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '万能账户结息为负的校验', 'BQ', 2, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '万能年报应生成未生成', 'BQ', 2, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '生存金应分未分', 'BQ', 3, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '账户表与明细表数据不一致 ', 'BQ', 3, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '年金金额为负数', 'BQ', 3, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '投连应计未计', 'BQ', 4, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '账户总金额为负', 'LP', 1, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '轨迹汇总金额为负', 'LP', 1, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '账户分类表总金额为负', 'LP', 1, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '三个表账户金额不一致', 'LP', 1, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '当天催收批处理运行过后，不该存在未催收的数据', 'XQ', 1, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '存在应收，且存在暂收超过1天未核销', 'XQ', 2, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '当天客户手动生成了通知书后，还存在可以生成的数据', 'XQ', 3, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '主险为长险超过宽限期还未停效的保单', 'XQ', 4, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '停效超过两年没终止的保单', 'XQ', 5, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '超过责任终止日期且未终止的保单', 'XQ', 6, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '校验提数日期', 'QD', 1, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '校验个银的新单承保提数条数', 'QD', 1, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '校验手续费计算结果为0', 'QD', 2, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '新单重复收费', 'CW', 1, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '非新单重复收费', 'CW', 1, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '重复付费', 'CW', 1, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '坏账', 'CW', 2, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '收费', 'CW', 3, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '付费', 'CW', 3, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '非账户型险种理赔', 'CW', 3, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '账户型险种理赔退费', 'CW', 3, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '账户型险种理赔', 'CW', 4, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '保全应付或应收业务', 'CW', 4, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '利息等', 'CW', 4, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '其他应付', 'CW', 4, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '红利领取', 'CW', 5, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '年金领取', 'CW', 5, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '暂交退费', 'CW', 5, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '个单余额', 'CW', 5, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '团单余额', 'CW', 6, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '应收保费', 'CW', 6, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '银保手续费计提', 'CW', 6, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '手续费实收', 'CW', 6, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '红利计提', 'CW', 7, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '红利进入账户', 'CW', 7, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '终了红利', 'CW', 7, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '保全保费', 'CW', 7, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '计提实付手续费', 'CW', 8, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '理赔回滚或加费', 'CW', 8, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '定期结算保费', 'CW', 8, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '续期收取保全
新增附加险的保费', 'CW', 8, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '000112短信前一小时内连续出现11次以上未成功的记录', 'SMS', 1, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '000301短信前一小时内连续出现11次以上未成功的记录', 'SMS', 2, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '查询当天失败的批次', 'SMS', 3, 0);
INSERT INTO lismonitor.lisrule (RULESQL, RULE, MODULEID, JOBID, STATE) VALUES ('SELECT 1 FROM DUAL', '查询当天一直运行超过6小时的批次', 'SMS', 4, 0);
INSERT INTO lismonitor.task_schedule_job (create_time, update_time, job_name, job_group, job_status, cron_expression, description, bean_class, is_concurrent, spring_id, method_name) VALUES (null, '2017-08-20 14:17:34', 'QYTest', 'QYTestGroup', '1', '0/600 * * * * ?', 'testdesc', 'com.sinosoft.job.lis.qy.QYTest', '1', null, 'doTest');
INSERT INTO lismonitor.task_schedule_job (create_time, update_time, job_name, job_group, job_status, cron_expression, description, bean_class, is_concurrent, spring_id, method_name) VALUES (null, '2017-08-20 14:17:34', 'ContDataVerifyJob', 'QYTestGroup', '0', '0/600 * * * * ?', 'qytestdesc', 'com.sinosoft.job.lis.qy.ContDataVerifyJob', '1', null, 'execute');