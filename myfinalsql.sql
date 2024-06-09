-- إذا كانت موجودة وإنشاء جديدة
DROP DATABASE IF EXISTS g2_vehicle_insurance_company;
CREATE DATABASE g2_vehicle_insurance_company;
USE g2_vehicle_insurance_company;

-- إسقاط الجداول إذا كانت موجودة
DROP TABLE IF EXISTS sites;
DROP TABLE IF EXISTS branches;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS addresses;
DROP TABLE IF EXISTS customer_addresses;
DROP TABLE IF EXISTS phones;
DROP TABLE IF EXISTS vehicles;
DROP TABLE IF EXISTS insurance_policies;
DROP TABLE IF EXISTS claims;
DROP TABLE IF EXISTS drivers;
DROP TABLE IF EXISTS installments;
DROP TABLE IF EXISTS accidents;
DROP TABLE IF EXISTS accidents_drive;
DROP TABLE IF EXISTS accidents_vehicle;

-- جدول المواقع
CREATE TABLE sites (
    SiteID INT PRIMARY KEY ,
    StreetName VARCHAR(100) NOT NULL,
    CityName VARCHAR(100) NOT NULL
);
-- إدخال بيانات إلى جدول المواقع

INSERT INTO sites (SiteID ,StreetName, CityName) VALUES 
(1,'123 Main St', 'Cairo'),
(2,'456 Elm St', 'Alexandria'),
(3,'123 Main St', 'Cairo'),
(4,'456 Elm St', 'Alexandria');


-- جدول فروع الشركة
CREATE TABLE branches (
    BranchID INT PRIMARY KEY ,
    BranchName VARCHAR(100) NOT NULL,
    SiteID INT,
    FOREIGN KEY (SiteID) REFERENCES sites(SiteID)
);
-- إدخال بيانات إلى جدول الفروع

INSERT INTO branches (BranchID ,BranchName, SiteID) VALUES 
(1,'Downtown Branch', 1),
(2,'Uptown Branch', 2);


-- جدول الزبائن
CREATE TABLE customers (
    CustomerID varchar(30) PRIMARY KEY ,
    Gender varchar(30) ,
    CName VARCHAR(100) NOT NULL,
    DateOfBirth DATE NOT NULL,
    Email varchar(50) NOT NULL,
    BranchID INT,
    FOREIGN KEY (BranchID) REFERENCES branches(BranchID)
);

INSERT INTO customers (CustomerID, Gender, CName, DateOfBirth, Email, BranchID) VALUES 
('CUST001', 'Male', 'John Doe', '1990-01-01', 'john@example.com', 1),
('CUST002', 'Female', 'Jane Smith', '1992-05-15', 'jane@example.com', 2);

-- جدول العناوين
CREATE TABLE addresses (
    AddressID INT PRIMARY KEY ,
    Street VARCHAR(100) NOT NULL,
    City VARCHAR(100) NOT NULL,
    BuildingName VARCHAR(100) NOT NULL,
    BuildingNumber VARCHAR(5) NOT NULL
);

INSERT INTO addresses (AddressID, Street, City, BuildingName, BuildingNumber) VALUES 
(1, '123 Street', 'Cairo', 'Building A', 1),
(2, '456 Avenue', 'Alexandria', 'Building B', 2);


-- جدول علاقة متعدد لمتعدد بين العناوين والزبائن
CREATE TABLE customer_addresses (
    AddressID INT NOT NULL,
    CustomerID varchar(30) NOT NULL,
    PRIMARY KEY (AddressID, CustomerID),
    FOREIGN KEY (AddressID) REFERENCES addresses(AddressID),
    FOREIGN KEY (CustomerID) REFERENCES customers(CustomerID)
);

INSERT INTO customer_addresses (AddressID, CustomerID) VALUES 
(1, 'CUST001'),
(2, 'CUST002');


-- جدول أرقام الهواتف
CREATE TABLE phones (
    PhoneID INT PRIMARY KEY ,
    PhoneNumber VARCHAR(20) NOT NULL,
    CustomerID varchar(30),
    FOREIGN KEY (CustomerID) REFERENCES customers(CustomerID)
);

INSERT INTO phones (PhoneID,PhoneNumber, CustomerID) VALUES 
(1,'123456789', 'CUST001'),
(2,'987654321', 'CUST002');

-- جدول وثائق التأمين
CREATE TABLE insurance_policies (
    PolicyID INT PRIMARY KEY ,
    PolicyNumber VARCHAR(50) NOT NULL,
    PolicyType VARCHAR(50) NOT NULL,
    StartDate DATE NOT NULL,
    EndDate DATE NOT NULL,
    PremiumAmount DOUBLE NOT NULL,
	CustomerID varchar(30),
    FOREIGN KEY (CustomerID) REFERENCES customers(CustomerID)
);

INSERT INTO insurance_policies (PolicyID ,PolicyNumber, PolicyType, StartDate, EndDate, PremiumAmount, CustomerID) VALUES 
(1,'POL001', 'Car Insurance', '2023-01-01', '2024-01-01', 500.00, 'CUST001'),
(2,'POL002', 'Home Insurance', '2023-05-01', '2024-05-01', 1000.00, 'CUST002');

-- جدول الأقساط
CREATE TABLE installments (
    InstallmentID INT PRIMARY KEY,
    Amount DOUBLE NOT NULL,
    DueDate DATE NOT NULL,
    PaidDate DATE,
    PolicyID INT,
    FOREIGN KEY (PolicyID) REFERENCES insurance_policies(PolicyID)
);

INSERT INTO installments (InstallmentID, Amount, DueDate, PaidDate, PolicyID) VALUES 
(1, 250.00, '2023-02-01', NULL, 1),
(2, 500.00, '2023-06-01', '2023-05-28', 2);


-- جدول المركبات
CREATE TABLE vehicles (
	VIN  VARCHAR(50)  PRIMARY KEY ,
    VehicleName VARCHAR(100) NOT NULL,
    VehicleType VARCHAR(50) NOT NULL,
    ModelYear INT NOT NULL,
    PolicyID INT ,
    FOREIGN KEY (PolicyID ) REFERENCES insurance_policies(PolicyID )
);

INSERT INTO vehicles (VIN, VehicleName, VehicleType, ModelYear, PolicyID) VALUES 
('VIN001', 'Toyota Camry', 'Car', 2022, 1),
('VIN002', 'Honda Civic', 'Car', 2023, 1);

-- جدول السائقين
CREATE TABLE drivers (
    DriverID INT PRIMARY KEY ,
    LicenseNumber VARCHAR(50) NOT NULL,
    DName VARCHAR(100) NOT NULL,
    Age INT NOT NULL,
    VIN VARCHAR(50),
    FOREIGN KEY ( VIN) REFERENCES  vehicles(VIN)
);

INSERT INTO drivers (DriverID, LicenseNumber, DName, Age, VIN) VALUES 
(1, 'DL001', 'John Doe', 30, 'VIN001'),
(2, 'DL002', 'Jane Smith', 28, 'VIN001');


-- جدول الحوادث
CREATE TABLE accidents (
    AccidentID INT PRIMARY KEY ,
    AccidentDate DATE NOT NULL,
    Location VARCHAR(1000) NOT NULL,
    ADescription VARCHAR(1000)
  
);

INSERT INTO accidents (AccidentID, AccidentDate, Location, ADescription) VALUES 
(1, '2023-03-01', 'Intersection', 'Minor collision'),
(2, '2023-07-15', 'Highway', 'Multiple vehicle collision');

-- علاقة متعدد لمتعدد بين الئق والحادث 
create TABLE accidents_drive (
    AccidentID INT  ,
    DriverID INT,
        PRIMARY KEY ( AccidentID ,  DriverID),
        FOREIGN KEY (DriverID) REFERENCES drivers(DriverID) ,
		FOREIGN KEY (AccidentID ) REFERENCES accidents(AccidentID )
        ) ;


-- علاقة متعدد لمتعدد بين الحادث والسيارة 
create TABLE accidents_vehicle (
    AccidentID INT  ,
    VIN VARCHAR(50),
	PRIMARY KEY ( AccidentID ,  VIN),
    FOREIGN KEY (VIN) REFERENCES vehicles(VIN),
	FOREIGN KEY (AccidentID ) REFERENCES accidents(AccidentID )
) ;
-- جدول المطالبات
CREATE TABLE claims (
    ClaimID INT PRIMARY KEY ,
    CDescription VARCHAR(255) NOT NULL,
    ClaimDate DATE NOT NULL,
    ClaimAmount DOUBLE NOT NULL,
    CStatus VARCHAR(50) NOT NULL,
    AccidentID  INT,
    FOREIGN KEY (AccidentID ) REFERENCES accidents(AccidentID )
);

INSERT INTO claims (ClaimID, CDescription, ClaimDate, ClaimAmount, CStatus, AccidentID) VALUES 
(1, 'Vehicle damage claim', '2023-03-05', 1000.00, 'Pending', 1),
(2, 'Injury claim', '2023-07-20', 5000.00, 'Approved', 2);



