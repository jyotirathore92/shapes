CREATE TABLE SHAPE_DETAILS (ID NUMBER PRIMARY KEY,NAME VARCHAR2(20) NOT NULL,TYPE VARCHAR2(20) NOT NULL,LENGTH NUMBER NOT NULL,X_COORDINATE NUMBER NOT NULL,Y_COORDINATE NUMBER NOT NULL,CREATION_DATE DATE NOT NULL,UNIQUE (NAME));

CREATE SEQUENCE SHAPE_SEQ START WITH 1 INCREMENT BY 1;