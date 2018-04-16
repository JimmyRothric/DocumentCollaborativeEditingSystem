create database DocumentDB
on
(
	name = 'DocumentDBData',
	filename = 'D:\database\DocumentDB_data.mdf',
	size = 10MB,
	maxsize = 100MB,
	filegrowth = 10MB
)
log on
(
	name = 'DocumentDBLog',
	filename = 'D:\database\DocumentDBB_log.ldf',
	size = 5MB,
	maxsize = 50MB,
	filegrowth = 5MB
)