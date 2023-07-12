	create table dev (
		devno number primary key,
		devname varchar2(50) not null,
		devage number,
		devemail varchar2(100) not null,
		devgender char(1) check(devgender in ('M','F')),
		devlang varchar2(100) not null
	);

	create sequence seq_dev_no
	start with 1
	increment by 1
	nominvalue
	nomaxvalue
	nocycle
	nocache;
	
SELECT * FROM dev;