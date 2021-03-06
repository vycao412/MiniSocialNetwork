USE [master]
GO
/****** Object:  Database [J3.L.P0010]    Script Date: 12/28/2020 3:40:03 PM ******/
CREATE DATABASE [J3.L.P0010]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'J3.L.P0010', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\J3.L.P0010.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'J3.L.P0010_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\J3.L.P0010_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [J3.L.P0010] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [J3.L.P0010].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [J3.L.P0010] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [J3.L.P0010] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [J3.L.P0010] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [J3.L.P0010] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [J3.L.P0010] SET ARITHABORT OFF 
GO
ALTER DATABASE [J3.L.P0010] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [J3.L.P0010] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [J3.L.P0010] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [J3.L.P0010] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [J3.L.P0010] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [J3.L.P0010] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [J3.L.P0010] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [J3.L.P0010] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [J3.L.P0010] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [J3.L.P0010] SET  DISABLE_BROKER 
GO
ALTER DATABASE [J3.L.P0010] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [J3.L.P0010] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [J3.L.P0010] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [J3.L.P0010] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [J3.L.P0010] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [J3.L.P0010] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [J3.L.P0010] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [J3.L.P0010] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [J3.L.P0010] SET  MULTI_USER 
GO
ALTER DATABASE [J3.L.P0010] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [J3.L.P0010] SET DB_CHAINING OFF 
GO
ALTER DATABASE [J3.L.P0010] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [J3.L.P0010] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [J3.L.P0010] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [J3.L.P0010] SET QUERY_STORE = OFF
GO
USE [J3.L.P0010]
GO
/****** Object:  Table [dbo].[tblArticles]    Script Date: 12/28/2020 3:40:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblArticles](
	[postID] [int] IDENTITY(1,1) NOT NULL,
	[title] [nvarchar](100) NULL,
	[description] [nvarchar](max) NULL,
	[image] [varchar](50) NULL,
	[date] [date] NULL,
	[userID] [int] NULL,
	[status] [int] NULL,
	[numLike] [int] NULL,
	[numDislike] [int] NULL,
 CONSTRAINT [PK_tblArticles] PRIMARY KEY CLUSTERED 
(
	[postID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblComments]    Script Date: 12/28/2020 3:40:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblComments](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[postID] [int] NULL,
	[userID] [int] NULL,
	[contents] [nvarchar](max) NULL,
	[date] [date] NULL,
	[status] [int] NULL,
 CONSTRAINT [PK_tblComments] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblEmotions]    Script Date: 12/28/2020 3:40:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblEmotions](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[postID] [int] NULL,
	[userID] [int] NULL,
	[isLike] [bit] NULL,
	[isDislike] [bit] NULL,
	[date] [date] NULL,
 CONSTRAINT [PK_tblEmotions] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblNotifies]    Script Date: 12/28/2020 3:40:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblNotifies](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[postID] [int] NULL,
	[userID] [int] NULL,
	[date] [date] NULL,
	[type] [varchar](20) NULL,
	[status] [int] NULL,
 CONSTRAINT [PK_tblNotifies] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblRoles]    Script Date: 12/28/2020 3:40:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblRoles](
	[roleID] [int] NOT NULL,
	[roleName] [varchar](50) NULL,
 CONSTRAINT [PK_tblRoles] PRIMARY KEY CLUSTERED 
(
	[roleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblStatuses]    Script Date: 12/28/2020 3:40:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblStatuses](
	[statusID] [int] NOT NULL,
	[name] [varchar](20) NULL,
 CONSTRAINT [PK_tblStatuses] PRIMARY KEY CLUSTERED 
(
	[statusID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblUsers]    Script Date: 12/28/2020 3:40:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblUsers](
	[userID] [int] IDENTITY(1,1) NOT NULL,
	[mail] [varchar](50) NULL,
	[name] [nvarchar](100) NULL,
	[password] [varchar](64) NULL,
	[role] [int] NULL,
	[status] [int] NULL,
	[verifyCode] [varchar](10) NULL,
 CONSTRAINT [PK_tblUsers_1] PRIMARY KEY CLUSTERED 
(
	[userID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[tblArticles] ON 

INSERT [dbo].[tblArticles] ([postID], [title], [description], [image], [date], [userID], [status], [numLike], [numDislike]) VALUES (5, N'Meow Meow', N'Mlem', N'5e534be36f958fd279dac2382f215223.jpg', CAST(N'2020-12-07' AS Date), 2, 4, 1, 0)
INSERT [dbo].[tblArticles] ([postID], [title], [description], [image], [date], [userID], [status], [numLike], [numDislike]) VALUES (6, N'B2', N'Mlem Mlem', N'6a93bd7e55de7b5938fabbedfc7483df.jpg', CAST(N'2020-12-07' AS Date), 2, 3, 0, 0)
INSERT [dbo].[tblArticles] ([postID], [title], [description], [image], [date], [userID], [status], [numLike], [numDislike]) VALUES (7, N'B3', N'Meow Meow Meow', N'7 Ways To Read Your Cat''s Mood.jfif', CAST(N'2020-12-07' AS Date), 4, 4, 0, 0)
INSERT [dbo].[tblArticles] ([postID], [title], [description], [image], [date], [userID], [status], [numLike], [numDislike]) VALUES (9, N'meo meo', N'qq a chu meo meo', N'', CAST(N'2020-12-10' AS Date), 9, 4, 0, 0)
INSERT [dbo].[tblArticles] ([postID], [title], [description], [image], [date], [userID], [status], [numLike], [numDislike]) VALUES (10, N'keke', N'kekekiki', N'', CAST(N'2020-12-10' AS Date), 9, 4, 0, 2)
INSERT [dbo].[tblArticles] ([postID], [title], [description], [image], [date], [userID], [status], [numLike], [numDislike]) VALUES (11, N'keke', N'kiki', N'', CAST(N'2020-12-10' AS Date), 8, 4, 1, 0)
INSERT [dbo].[tblArticles] ([postID], [title], [description], [image], [date], [userID], [status], [numLike], [numDislike]) VALUES (12, N'keke', N'kiki', N'', CAST(N'2020-12-10' AS Date), 8, 4, 0, 0)
INSERT [dbo].[tblArticles] ([postID], [title], [description], [image], [date], [userID], [status], [numLike], [numDislike]) VALUES (13, N'pÃ³t moi', N'moi moi', N'', CAST(N'2020-12-10' AS Date), 8, 4, 0, 1)
INSERT [dbo].[tblArticles] ([postID], [title], [description], [image], [date], [userID], [status], [numLike], [numDislike]) VALUES (14, N'1', N'1', N'', CAST(N'2020-12-14' AS Date), 10, 3, 0, 0)
INSERT [dbo].[tblArticles] ([postID], [title], [description], [image], [date], [userID], [status], [numLike], [numDislike]) VALUES (15, N'11', N'11', N'1200px-Octicons-mark-github.svg.png', CAST(N'2020-12-14' AS Date), 10, 4, 1, 0)
INSERT [dbo].[tblArticles] ([postID], [title], [description], [image], [date], [userID], [status], [numLike], [numDislike]) VALUES (16, N'qw', N'qw', N'9d12cf4a4e67a67dc09bf5a1082ff9fd.jpg', CAST(N'2020-12-14' AS Date), 2, 3, 0, 0)
INSERT [dbo].[tblArticles] ([postID], [title], [description], [image], [date], [userID], [status], [numLike], [numDislike]) VALUES (17, N'B3', N'123', N'1200px-Octicons-mark-github.svg.png', CAST(N'2020-12-14' AS Date), 5, 4, 0, 0)
INSERT [dbo].[tblArticles] ([postID], [title], [description], [image], [date], [userID], [status], [numLike], [numDislike]) VALUES (18, N'B5', N'test img resize', N'1200px-Octicons-mark-github.svg.png', CAST(N'2020-12-14' AS Date), 2, 3, 0, 0)
INSERT [dbo].[tblArticles] ([postID], [title], [description], [image], [date], [userID], [status], [numLike], [numDislike]) VALUES (19, N'12', N'12
12
12
1
2
1
2', N'1567587838-363-thumbnail.jpg', CAST(N'2020-12-15' AS Date), 2, 4, 0, 0)
SET IDENTITY_INSERT [dbo].[tblArticles] OFF
GO
SET IDENTITY_INSERT [dbo].[tblComments] ON 

INSERT [dbo].[tblComments] ([ID], [postID], [userID], [contents], [date], [status]) VALUES (21, 10, 8, N'hellooo', CAST(N'2020-12-10' AS Date), 5)
INSERT [dbo].[tblComments] ([ID], [postID], [userID], [contents], [date], [status]) VALUES (22, 10, 8, N'hello', CAST(N'2020-12-10' AS Date), 5)
INSERT [dbo].[tblComments] ([ID], [postID], [userID], [contents], [date], [status]) VALUES (23, 10, 8, N'heheh
', CAST(N'2020-12-10' AS Date), 5)
INSERT [dbo].[tblComments] ([ID], [postID], [userID], [contents], [date], [status]) VALUES (24, 10, 8, N'hehehehehllalala', CAST(N'2020-12-10' AS Date), 5)
INSERT [dbo].[tblComments] ([ID], [postID], [userID], [contents], [date], [status]) VALUES (25, 11, 9, N'fdsfjbs', CAST(N'2020-12-10' AS Date), 5)
INSERT [dbo].[tblComments] ([ID], [postID], [userID], [contents], [date], [status]) VALUES (26, 13, 9, N'hellooo', CAST(N'2020-12-10' AS Date), 5)
INSERT [dbo].[tblComments] ([ID], [postID], [userID], [contents], [date], [status]) VALUES (27, 13, 9, N'hellooo', CAST(N'2020-12-10' AS Date), 1)
INSERT [dbo].[tblComments] ([ID], [postID], [userID], [contents], [date], [status]) VALUES (32, 15, 10, N'1', CAST(N'2020-12-14' AS Date), 5)
INSERT [dbo].[tblComments] ([ID], [postID], [userID], [contents], [date], [status]) VALUES (33, 15, 10, N'12
12
12
12
12
12', CAST(N'2020-12-14' AS Date), 5)
INSERT [dbo].[tblComments] ([ID], [postID], [userID], [contents], [date], [status]) VALUES (34, 5, 10, N'sd', CAST(N'2020-12-14' AS Date), 5)
INSERT [dbo].[tblComments] ([ID], [postID], [userID], [contents], [date], [status]) VALUES (35, 5, 10, N'sd', CAST(N'2020-12-14' AS Date), 5)
INSERT [dbo].[tblComments] ([ID], [postID], [userID], [contents], [date], [status]) VALUES (36, 5, 10, N'd', CAST(N'2020-12-14' AS Date), 5)
INSERT [dbo].[tblComments] ([ID], [postID], [userID], [contents], [date], [status]) VALUES (40, 6, 2, N'a
a', CAST(N'2020-12-14' AS Date), 4)
INSERT [dbo].[tblComments] ([ID], [postID], [userID], [contents], [date], [status]) VALUES (41, 6, 5, N'a', CAST(N'2020-12-14' AS Date), 4)
INSERT [dbo].[tblComments] ([ID], [postID], [userID], [contents], [date], [status]) VALUES (42, 6, 5, N'aaa', CAST(N'2020-12-14' AS Date), 4)
INSERT [dbo].[tblComments] ([ID], [postID], [userID], [contents], [date], [status]) VALUES (43, 6, 5, N'aaaa', CAST(N'2020-12-14' AS Date), 4)
SET IDENTITY_INSERT [dbo].[tblComments] OFF
GO
SET IDENTITY_INSERT [dbo].[tblEmotions] ON 

INSERT [dbo].[tblEmotions] ([ID], [postID], [userID], [isLike], [isDislike], [date]) VALUES (21, 6, 5, 1, 0, CAST(N'2020-12-10' AS Date))
INSERT [dbo].[tblEmotions] ([ID], [postID], [userID], [isLike], [isDislike], [date]) VALUES (29, 15, 10, 1, 0, CAST(N'2020-12-14' AS Date))
INSERT [dbo].[tblEmotions] ([ID], [postID], [userID], [isLike], [isDislike], [date]) VALUES (30, 5, 10, 1, 0, CAST(N'2020-12-14' AS Date))
SET IDENTITY_INSERT [dbo].[tblEmotions] OFF
GO
SET IDENTITY_INSERT [dbo].[tblNotifies] ON 

INSERT [dbo].[tblNotifies] ([ID], [postID], [userID], [date], [type], [status]) VALUES (65, 15, 10, CAST(N'2020-12-14' AS Date), N'Commented', 4)
INSERT [dbo].[tblNotifies] ([ID], [postID], [userID], [date], [type], [status]) VALUES (66, 15, 10, CAST(N'2020-12-14' AS Date), N'Commented', 4)
INSERT [dbo].[tblNotifies] ([ID], [postID], [userID], [date], [type], [status]) VALUES (67, 15, 10, CAST(N'2020-12-14' AS Date), N'Liked', 4)
INSERT [dbo].[tblNotifies] ([ID], [postID], [userID], [date], [type], [status]) VALUES (68, 15, 10, CAST(N'2020-12-14' AS Date), N'Disliked', 4)
INSERT [dbo].[tblNotifies] ([ID], [postID], [userID], [date], [type], [status]) VALUES (69, 15, 10, CAST(N'2020-12-14' AS Date), N'deleted dislike', 4)
INSERT [dbo].[tblNotifies] ([ID], [postID], [userID], [date], [type], [status]) VALUES (70, 15, 10, CAST(N'2020-12-14' AS Date), N'Liked', 4)
INSERT [dbo].[tblNotifies] ([ID], [postID], [userID], [date], [type], [status]) VALUES (71, 5, 10, CAST(N'2020-12-14' AS Date), N'Liked', 4)
INSERT [dbo].[tblNotifies] ([ID], [postID], [userID], [date], [type], [status]) VALUES (72, 5, 10, CAST(N'2020-12-14' AS Date), N'Commented', 4)
INSERT [dbo].[tblNotifies] ([ID], [postID], [userID], [date], [type], [status]) VALUES (73, 5, 10, CAST(N'2020-12-14' AS Date), N'Commented', 4)
INSERT [dbo].[tblNotifies] ([ID], [postID], [userID], [date], [type], [status]) VALUES (74, 5, 10, CAST(N'2020-12-14' AS Date), N'Commented', 4)
INSERT [dbo].[tblNotifies] ([ID], [postID], [userID], [date], [type], [status]) VALUES (75, 5, 10, CAST(N'2020-12-14' AS Date), N'Commented', 4)
INSERT [dbo].[tblNotifies] ([ID], [postID], [userID], [date], [type], [status]) VALUES (79, 6, 5, CAST(N'2020-12-14' AS Date), N'Commented', 4)
INSERT [dbo].[tblNotifies] ([ID], [postID], [userID], [date], [type], [status]) VALUES (80, 6, 5, CAST(N'2020-12-14' AS Date), N'Commented', 4)
INSERT [dbo].[tblNotifies] ([ID], [postID], [userID], [date], [type], [status]) VALUES (81, 6, 5, CAST(N'2020-12-14' AS Date), N'Commented', 4)
SET IDENTITY_INSERT [dbo].[tblNotifies] OFF
GO
INSERT [dbo].[tblRoles] ([roleID], [roleName]) VALUES (1, N'Member')
INSERT [dbo].[tblRoles] ([roleID], [roleName]) VALUES (2, N'Admin')
GO
INSERT [dbo].[tblStatuses] ([statusID], [name]) VALUES (1, N'New')
INSERT [dbo].[tblStatuses] ([statusID], [name]) VALUES (2, N'Disable')
INSERT [dbo].[tblStatuses] ([statusID], [name]) VALUES (3, N'Active')
INSERT [dbo].[tblStatuses] ([statusID], [name]) VALUES (4, N'Delete')
INSERT [dbo].[tblStatuses] ([statusID], [name]) VALUES (5, N'Read')
GO
SET IDENTITY_INSERT [dbo].[tblUsers] ON 

INSERT [dbo].[tblUsers] ([userID], [mail], [name], [password], [role], [status], [verifyCode]) VALUES (2, N'caovy0412@gmail.com', N'Vy Cao', N'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', 1, 1, NULL)
INSERT [dbo].[tblUsers] ([userID], [mail], [name], [password], [role], [status], [verifyCode]) VALUES (4, N'ngocnhatvycao2000@gmail.com', N'Vy', N'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', 1, 1, N'MJvMKuU86d')
INSERT [dbo].[tblUsers] ([userID], [mail], [name], [password], [role], [status], [verifyCode]) VALUES (5, N'as@gmail.com', N'11111111', N'ee79976c9380d5e337fc1c095ece8c8f22f91f306ceeb161fa51fecede2c4ba1', 1, 1, NULL)
INSERT [dbo].[tblUsers] ([userID], [mail], [name], [password], [role], [status], [verifyCode]) VALUES (7, N'admin@gmail.com', N'admin', N'ee79976c9380d5e337fc1c095ece8c8f22f91f306ceeb161fa51fecede2c4ba1', 2, 1, NULL)
INSERT [dbo].[tblUsers] ([userID], [mail], [name], [password], [role], [status], [verifyCode]) VALUES (8, N'vuthugiang2610@gmail.com', N'vu thu giang', N'932f3c1b56257ce8539ac269d7aab42550dacf8818d075f0bdf1990562aae3ef', 1, 1, NULL)
INSERT [dbo].[tblUsers] ([userID], [mail], [name], [password], [role], [status], [verifyCode]) VALUES (9, N'caytamto@gmail.com', N'vu thu giang giang', N'34da4b584c31d757c57589f5b3e5462af1a75d3db59f01e25f4e584d3dcb2fb4', 1, 1, N'86zvmUpoqG')
INSERT [dbo].[tblUsers] ([userID], [mail], [name], [password], [role], [status], [verifyCode]) VALUES (10, N'vttvan@gmail.com', N'vttvan@gmail.com', N'7783003a6c86a1ec54ed3a2557d98c5c7552604fdfe8d887d772058c2e772939', 1, 1, N'LIHEVOdh0z')
SET IDENTITY_INSERT [dbo].[tblUsers] OFF
GO
ALTER TABLE [dbo].[tblComments]  WITH CHECK ADD  CONSTRAINT [FK_tblComments_tblArticles1] FOREIGN KEY([postID])
REFERENCES [dbo].[tblArticles] ([postID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[tblComments] CHECK CONSTRAINT [FK_tblComments_tblArticles1]
GO
ALTER TABLE [dbo].[tblComments]  WITH CHECK ADD  CONSTRAINT [FK_tblComments_tblUsers] FOREIGN KEY([userID])
REFERENCES [dbo].[tblUsers] ([userID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[tblComments] CHECK CONSTRAINT [FK_tblComments_tblUsers]
GO
ALTER TABLE [dbo].[tblEmotions]  WITH CHECK ADD  CONSTRAINT [FK_tblEmotions_tblArticles] FOREIGN KEY([postID])
REFERENCES [dbo].[tblArticles] ([postID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[tblEmotions] CHECK CONSTRAINT [FK_tblEmotions_tblArticles]
GO
ALTER TABLE [dbo].[tblNotifies]  WITH CHECK ADD  CONSTRAINT [FK_tblNotifies_tblArticles1] FOREIGN KEY([postID])
REFERENCES [dbo].[tblArticles] ([postID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[tblNotifies] CHECK CONSTRAINT [FK_tblNotifies_tblArticles1]
GO
ALTER TABLE [dbo].[tblNotifies]  WITH CHECK ADD  CONSTRAINT [FK_tblNotifies_tblUsers1] FOREIGN KEY([userID])
REFERENCES [dbo].[tblUsers] ([userID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[tblNotifies] CHECK CONSTRAINT [FK_tblNotifies_tblUsers1]
GO
ALTER TABLE [dbo].[tblUsers]  WITH CHECK ADD  CONSTRAINT [FK_tblUsers_tblRoles1] FOREIGN KEY([role])
REFERENCES [dbo].[tblRoles] ([roleID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[tblUsers] CHECK CONSTRAINT [FK_tblUsers_tblRoles1]
GO
ALTER TABLE [dbo].[tblUsers]  WITH CHECK ADD  CONSTRAINT [FK_tblUsers_tblStatuses1] FOREIGN KEY([status])
REFERENCES [dbo].[tblStatuses] ([statusID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[tblUsers] CHECK CONSTRAINT [FK_tblUsers_tblStatuses1]
GO
USE [master]
GO
ALTER DATABASE [J3.L.P0010] SET  READ_WRITE 
GO
