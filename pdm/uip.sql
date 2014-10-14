/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     10/14/2014 17:31:06                          */
/*==============================================================*/


drop function if exists nextval;

drop function if exists setval;

drop function if exists currval;

drop table if exists sequence;

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

