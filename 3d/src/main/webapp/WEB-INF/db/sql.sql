USE [demo]
GO
/****** 对象:  Table [dbo].[Zimi]    脚本日期: 05/19/2013 23:03:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Zimi](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Z1] [nvarchar](50) COLLATE Chinese_PRC_CI_AS NULL,
	[Z2] [nvarchar](50) COLLATE Chinese_PRC_CI_AS NULL,
	[Z3] [nvarchar](50) COLLATE Chinese_PRC_CI_AS NULL,
	[Z4] [nvarchar](50) COLLATE Chinese_PRC_CI_AS NULL,
	[Z5] [nvarchar](50) COLLATE Chinese_PRC_CI_AS NULL,
	[Z6] [nvarchar](50) COLLATE Chinese_PRC_CI_AS NULL,
	[Z7] [nvarchar](50) COLLATE Chinese_PRC_CI_AS NULL,
	[Z8] [nvarchar](50) COLLATE Chinese_PRC_CI_AS NULL,
	[Z9] [nvarchar](50) COLLATE Chinese_PRC_CI_AS NULL,
	[Z10] [nvarchar](50) COLLATE Chinese_PRC_CI_AS NULL,
	[Z11] [nvarchar](50) COLLATE Chinese_PRC_CI_AS NULL,
	[Z12] [nvarchar](50) COLLATE Chinese_PRC_CI_AS NULL,
	[B1] [int] NULL,
	[B2] [int] NULL,
	[B3] [int] NULL,
	[B4] [int] NULL,
	[B5] [int] NULL,
	[B6] [int] NULL,
	[B7] [int] NULL,
	[B8] [int] NULL,
	[B9] [int] NULL,
	[B10] [int] NULL,
	[B11] [int] NULL,
	[B12] [int] NULL,
	[Npis] [nvarchar](50) COLLATE Chinese_PRC_CI_AS NULL,
	[BTotal] [int] NULL,
	[ShiJiHao] [nvarchar](50) COLLATE Chinese_PRC_CI_AS NULL,
	[KaiJiangHao] [nvarchar](50) COLLATE Chinese_PRC_CI_AS NULL,
	[XiaZhuHao] [nvarchar](50) COLLATE Chinese_PRC_CI_AS NULL,
	[InputDate] [datetime] NULL,
	[SerialNumber] [nvarchar](50) COLLATE Chinese_PRC_CI_AS NULL,
	[Zimi] [nvarchar](200) COLLATE Chinese_PRC_CI_AS NULL,
 CONSTRAINT [PK_Zimi] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'第几个字' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Zimi', @level2type=N'COLUMN',@level2name=N'Z1'