use demodb;

create table tUsuaris(
       NIF varchar(10) NOT NULL PRIMARY KEY,
       Cognom1 varchar(50) NOT NULL,
       Cognom2 varchar(50) NULL,
       Nom varchar(50) NOT NULL,
       login varchar(20) NULL,
       checked bit NOT NULL
);

-- drop table tUsuaris;

-- USE demodb;  
-- GO  
ALTER TABLE dbo.tUsuaris  
ADD CONSTRAINT ak_login UNIQUE ([login]);   
-- GO
