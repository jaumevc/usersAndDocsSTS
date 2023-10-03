alter table dbo.tDocuments
add constraint Fk_tUsuaris foreign key (usuari)
references dbo.tUsuaris (login)
ON DELETE CASCADE
ON UPDATE CASCADE;