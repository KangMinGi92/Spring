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

CREATE TABLE MEMBER(
  USERID VARCHAR2(15) PRIMARY KEY
 ,PASSWORD VARCHAR2(300) NOT NULL
 ,USERNAME  VARCHAR2(20) NOT NULL
 ,GENDER CHAR(1) CHECK (GENDER IN ('M','F'))
 ,AGE NUMBER
 ,EMAIL VARCHAR2(30)
 ,PHONE CHAR(11)  NOT NULL
 ,ADDRESS VARCHAR2(100)
 ,HOBBY VARCHAR2(50)
 ,ENROLLDATE DATE DEFAULT SYSDATE
);

INSERT INTO SPRING.MEMBER VALUES ('abcde','1234','아무개','M',25,'abcde@naver.com','01012345678','서울시 강남구','운동,등산,독서',DEFAULT);
INSERT INTO SPRING.MEMBER VALUES ('qwerty','1234','김말년','F',30,'qwerty@naver.com','01098765432','서울시 관악구','운동,등산',DEFAULT);
INSERT INTO SPRING.MEMBER VALUES ('admin','1234','관리자','F',33,'admin@naver.com','01012345678','서울시 강남구','독서',DEFAULT);
COMMIT;
SELECT * FROM MEMBER;
SELECT * FROM MEMBER WHERE userid='admin' AND PASSWORD='1234';
UPDATE MEMBER SET PASSWORD='$2a$10$QrKUNLOJc7hqI6Kgu.yoUOog198Z5dXnOVoRUfj/IlxALOukAUdcO';

CREATE TABLE MEMO(
    MEMONO NUMBER PRIMARY KEY,
    MEMO VARCHAR2(2000),
    PASSWORD VARCHAR2(4),
    MEMODATE DATE DEFAULT SYSDATE
);

CREATE SEQUENCE SEQ_MEMONO
START WITH 1
INCREMENT BY 1
NOMINVALUE
NOMAXVALUE
NOCYCLE
NOCACHE;

INSERT INTO MEMO VALUES(SEQ_MEMONO.NEXTVAL, '반갑습니다. AOP', 1234, DEFAULT);

SELECT * FROM MEMO;

