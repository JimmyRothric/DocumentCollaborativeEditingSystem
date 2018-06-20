use DocumentDB
create table Account
(
	Uid char(20),
	password varchar(20) not null,
	name nvarchar(20) not null,
	email nvarchar(50),
	primary key (Uid)
)

create table Document
(
	Did char(20),
	title nvarchar(100) not null,
	path nvarchar(200) not null,
	create_date datetime not null,
	last_modify_date datetime,
	version int default(1) not null,
	primary key (Did)
)

create table Document_History
(
	Did char(20),
	title nvarchar(100) not null,
	path nvarchar(200) not null,
	create_date datetime not null,
	modify_date datetime not null,
	version int,
	primary key (Did, version)
)

create table Contributor
(
	Uid char(20),
	Did char(20),
	authority char(1),
	primary key (Uid, Did),
	foreign key (Uid) references Account,
	foreign key (Did) references Document
)

create table Contribution
(
	Did char(20),
	Cid char(20),
	Uid char(20),
	path nvarchar(200) not null,
	date datetime not null,
	state char(1) not null,
	primary key(Did,Cid,Uid),
	foreign key (Uid) references Account,
	foreign key (Did) references Document
)

create table Invitation
(
	Did char(20),
	Sender_id char(20),
	Receiver_id char(20),
	primary key (Did,Sender_id,Receiver_id),
	foreign key (Sender_id) references Account(Uid),
	foreign key (Receiver_id) references Account(Uid),
	foreign key (Did) references Document
)


