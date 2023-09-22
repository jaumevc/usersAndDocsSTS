USE [demodb]
GO

/****** Object:  Table [dbo].[tDocuments]    Script Date: 22/09/2023 9:47:21 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[tDocuments](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[fitxer] [varbinary](max) NOT NULL,
	[tipus] [varchar](5) NOT NULL,
	[usuari] [varchar](20) NOT NULL,
	[descripcio] [varchar](250) NULL,
	[AuditFields_InsertDate] [datetime] NOT NULL,
	[AuditFields_UpdateDate] [datetime] NOT NULL,
	[categoria] [int] NULL,
 CONSTRAINT [PK_tDocuments] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO

ALTER TABLE [dbo].[tDocuments]  WITH CHECK ADD  CONSTRAINT [Fk_tUsuaris] FOREIGN KEY([usuari])
REFERENCES [dbo].[tUsuaris] ([login])
ON UPDATE CASCADE
ON DELETE CASCADE
GO

ALTER TABLE [dbo].[tDocuments] CHECK CONSTRAINT [Fk_tUsuaris]
GO


