/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2014/10/14 21:48:11                          */
/*==============================================================*/


DROP FUNCTION IF EXISTS nextval;

DROP FUNCTION IF EXISTS setval;

DROP FUNCTION IF EXISTS currval;

DROP TABLE IF EXISTS sequence;

DROP TABLE IF EXISTS uip_user;

DROP TABLE IF EXISTS uip_param;

DROP TABLE IF EXISTS uip_static_data;

/*==============================================================*/
/* Table: sequence                                              */
/*==============================================================*/
CREATE TABLE sequence
(
  seq_name      VARCHAR(50) NOT NULL,
  current_val   BIGINT,
  increment_val BIGINT,
  PRIMARY KEY (seq_name)
)
  ENGINE =InnoDB
  DEFAULT CHARSET =utf8;

/*==============================================================*/
/* Table: uip_user                                              */
/*==============================================================*/
CREATE TABLE uip_user
(
  user_id       BIGINT       NOT NULL,
  user_name     VARCHAR(100) NOT NULL,
  password      VARCHAR(100) NOT NULL,
  email         VARCHAR(100) NOT NULL,
  status        CHAR(1)      NOT NULL,
  validate_code VARCHAR(100) NOT NULL,
  register_time DATETIME     NOT NULL,
  PRIMARY KEY (user_id)
)
  ENGINE =InnoDB
  DEFAULT CHARSET =utf8;

/*==============================================================*/
/* Table: uip_static_data                                       */
/*==============================================================*/
CREATE TABLE uip_static_data
(
  data_id    BIGINT NOT NULL,
  data_type  VARCHAR(255),
  data_value VARCHAR(255),
  data_name  VARCHAR(255),
  date_desc  VARCHAR(1000),
  sort       INT,
  status     CHAR(1),
  PRIMARY KEY (data_id)
)
  ENGINE =InnoDB
  DEFAULT CHARSET =utf8;

/*==============================================================*/
/* Table: uip_param                                             */
/*==============================================================*/
CREATE TABLE uip_param
(
  seq          BIGINT NOT NULL,
  param_name   VARCHAR(100),
  param_code   VARCHAR(100),
  param_clazz  VARCHAR(100),
  param_length VARCHAR(20),
  is_null      CHAR(1),
  sort         INT,
  param_type   CHAR(1),
  remark       VARCHAR(1000),
  parent_seq   BIGINT,
  PRIMARY KEY (seq)
)
  ENGINE =InnoDB
  DEFAULT CHARSET =utf8;

CREATE FUNCTION currval(v_seq_name VARCHAR(50))
  RETURNS BIGINT(20)
  BEGIN
    DECLARE value BIGINT;
    SET value = 0;
    SELECT
      current_val
    INTO value
    FROM sequence
    WHERE seq_name = v_seq_name;
    RETURN value;
  END;


CREATE FUNCTION nextval(v_seq_name VARCHAR(50))
  RETURNS BIGINT(20)
  BEGIN
    UPDATE sequence
    SET current_val = current_val + increment_val
    WHERE seq_name = v_seq_name;
    RETURN currval(v_seq_name);
  END;


CREATE FUNCTION setval(v_seq_name VARCHAR(50), v_new_val BIGINT)
  RETURNS BIGINT(20)
  BEGIN
    UPDATE sequence
    SET current_val = v_new_val
    WHERE seq_name = v_seq_name;
    RETURN currval(v_seq_name);
  END;

INSERT INTO sequence VALUES ('UIP_SEQ', 10000000, 1);

INSERT INTO uip_static_data VALUES (nextval('UIP_SEQ'), 'PUBLIC_PARAM', '-1', 'ROOT', '根节点', 1, 'U');
INSERT INTO uip_static_data VALUES (nextval('UIP_SEQ'), 'PUBLIC_PARAM', '-2', 'REQUEST', '请求公共参数', 2, 'U');
INSERT INTO uip_static_data VALUES (nextval('UIP_SEQ'), 'PUBLIC_PARAM', '-3', 'RESPONSE', '应答公共参数', 3, 'U');
COMMIT;

