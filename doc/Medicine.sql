CREATE TABLE medicine (
	Id integer PRIMARY KEY AUTOINCREMENT,
	NameMedicine string,
	LotNumber integer,
	Note string,
	Amount integer,
	ArrivalDate date,
	DateOfManufacture date,
	ShelfLife date
);

CREATE TABLE measur (
	IdMedicine integer PRIMARY KEY AUTOINCREMENT,
	kilo boolean,
	pieces boolean,
	gram boolean,
	liters boolean,
	mliters boolean
);

CREATE TABLE lot (
	LNumber integer,
	IdLot integer PRIMARY KEY AUTOINCREMENT,
	NameCountry string,
	NameCity string,
	Adress string,
	DepartureDate date,
	PriceOne integer
);

CREATE TABLE shop (
	NameMedicineAtShop string,
	IdShop integer PRIMARY KEY AUTOINCREMENT,
	NameShop string,
	AdressShop string,
	PriceAtShop integer
);

CREATE TABLE country (
	IdCountry integer PRIMARY KEY AUTOINCREMENT,
	Name string,
	CodeCountry integer,
	CodePhone integer,
	NameCity string
);

CREATE TABLE city (
	IdCity integer PRIMARY KEY AUTOINCREMENT,
	Name string,
	PostCode integer
);

CREATE TABLE users (
	IdUser integer PRIMARY KEY AUTOINCREMENT,
	SurnameUser string,
	NameUser string,
	PatronymicUser string,
	TypeUser string
);

CREATE TABLE Role (
	IdRole integer PRIMARY KEY AUTOINCREMENT,
	Name string
);

