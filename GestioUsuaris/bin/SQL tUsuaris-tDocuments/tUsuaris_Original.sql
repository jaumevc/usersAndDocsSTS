USE [documents]
GO

/****** Object:  Table [dbo].[tUsuaris]    Script Date: 18/08/2023 13:09:23 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[tUsuaris](
	[NIF] [nchar](10) NOT NULL,
	[Cognom1] [varchar](50) NOT NULL,
	[Cognom2] [varchar](50) NULL,
	[Nom] [varchar](50) NOT NULL,
	[login] [varchar](20) NULL,
	[checked] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[NIF] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[login] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[tUsuaris] ADD  CONSTRAINT [DF_tUsuaris_checked]  DEFAULT ((0)) FOR [checked]
GO


