/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2014/10/14 21:48:11                          */
/*==============================================================*/


drop function if exists nextval;

drop function if exists setval;

drop function if exists currval;

drop table if exists sequence;

drop table if exists uip_user;

/*==============================================================*/
/* Table: sequence                                              */
/*==============================================================*/
create table sequence
(
   seq_name             varchar(50) not null,
   current_val          bigint,
   increment_val        bigint,
   primary key (seq_name)
);

/*==============================================================*/
/* Table: uip_user                                              */
/*==============================================================*/
create table uip_user
(
   user_id              bigint not null,
   user_name            varchar(100) not null,
   password             varchar(100) not null,
   email                varchar(100) not null,
   status               char(1) not null,
   validate_code        varchar(100) not null,
   register_time        datetime not null,
   primary key (user_id)
);

create function currval (v_seq_name VARCHAR(50)) 
RETURNS BIGINT(20)
begin
    DECLARE value BIGINT;
    SET value = 0;
    SELECT current_val INTO value
    FROM sequence
    WHERE seq_name = v_seq_name;
RETURN value;
end;


create function nextval (v_seq_name VARCHAR(50)) 
RETURNS BIGINT(20)
begin
    UPDATE sequence 
	SET current_val = current_val + increment_val 
	WHERE seq_name = v_seq_name;
	RETURN currval(v_seq_name);
end;


create function setval (v_seq_name VARCHAR(50), v_new_val BIGINT) 
RETURNS BIGINT(20)
begin
    UPDATE sequence
	SET current_val = v_new_val
	WHERE seq_name = v_seq_name;
	RETURN currval(v_seq_name);
end;

INSERT INTO sequence VALUES ('UIP_SEQ', 10000000, 1);
INSERT INTO uip_user VALUES (nextval('UIP_SEQ'), 'AI-UIP', PASSWORD('AI-UIP'), 'ai-uip@asiainfo.com', '1', 'AI-UIP', now());
COMMIT;

