create database StoreManagement


create table Accounts
(
    userID int identity(1,1) primary key,
	userName varchar(50),
	[passWord] varchar(50),
	name varchar(50),
	roleID varchar(2) CHECK (roleID in ('KH','QL','RT'))
)


create table Products
(
   productID int identity(1,1) primary key,
   productName varchar(50),
   price decimal(7,2),
   quantity int
)

create table Orders
(
   orderID int identity(1,1) primary key,
   dateOrder date,
   userID int,
   total decimal(10,2),
   foreign key(userID) references Users(userID)
)

create table OrderDetails
(
   orderDetailID int identity(1,1) primary key,
   orderID int,
   productID int,
   price decimal(7,2),
   quantity int,
   foreign key(orderID) references Orders(orderID),
   foreign key(productID) references Products(productID) 
)


insert into Accounts(userName, passWord, name, roleID) values('trancanh',1,'Tran Quang Canh','KH')
insert into Accounts(userName, passWord, name, roleID) values('hoanganh', 1, 'Hoang Anh', 'QL')
insert into Accounts(userName, passWord, name, roleID) values('thanhtruc', 1, 'Thanh Truc', 'QL')
insert into Accounts(userName, passWord, name, roleID) values('minhquan', 1, 'Minh Quan', 'KH')
insert into Accounts(userName, passWord, name, roleID) values('linhnguyen', 1, 'Linh Nguyen', 'KH')
insert into Accounts(userName, passWord, name, roleID) values('tungngoc', 1, 'Tung Ngoc', 'KH')
insert into Accounts(userName, passWord, name, roleID) values('hanhpham', 1, 'Hanh Pham', 'KH')
insert into Accounts(userName, passWord, name, roleID) values('ducnguyen', 1, 'Duc Nguyen', 'KH')
insert into Accounts(userName, passWord, name, roleID) values('linhan', 1, 'Lin An', 'KH')
insert into Accounts(userName, passWord, name, roleID) values('tuantran', 1, 'Tuan Tran', 'KH')
insert into Accounts(userName, passWord, name, roleID) values('giangphung', 1, 'Giang Phung', 'QL')


insert into Products(productName, price, quantity) values('BMW',55000,10)
insert into Products(productName, price, quantity) values('Ford', 55000, 8)
insert into Products(productName, price, quantity) values('Toyota', 35000, 12)
insert into Products(productName, price, quantity) values('Honda', 28000, 15)
insert into Products(productName, price, quantity) values('Mercedes', 60000, 7)
insert into Products(productName, price, quantity) values('Honda', 48000, 11)
insert into Products(productName, price, quantity) values('Chevrolet', 38000, 13)


go


