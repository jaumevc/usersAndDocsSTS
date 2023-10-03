USE [documents]
GO

/****** Object:  Table [dbo].[tDocuments]    Script Date: 23/08/2023 10:11:46 ******/
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO


