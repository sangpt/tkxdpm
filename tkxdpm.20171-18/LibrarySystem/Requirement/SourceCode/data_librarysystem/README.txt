Lưu trữ database sử dụng mysql dùng phần mềm xampp, dưới đây là các lệnh tạo các bảng

CREATE TABLE `user` (
 `ID` int(11) NOT NULL AUTO_INCREMENT,
 `FirstName` char(10) CHARACTER SET utf8 COLLATE utf8_vietnamese_ci DEFAULT NULL,
 `LastName` char(10) DEFAULT NULL,
 `UserName` char(20) NOT NULL,
 `PassWord` char(20) NOT NULL,
 `CardID` int(10) DEFAULT NULL,
 `Role` char(10) NOT NULL,
 PRIMARY KEY (`ID`),
 UNIQUE KEY `UserName` (`UserName`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1

CREATE TABLE `card` (
 `CID` int(11) NOT NULL AUTO_INCREMENT,
 `ExpiredDate` date DEFAULT NULL,
 `Status` bit(1) DEFAULT NULL,
 `NumActive` int(4) DEFAULT NULL,
 PRIMARY KEY (`CID`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1

CREATE TABLE `book` (
 `BID` int(11) NOT NULL AUTO_INCREMENT,
 `Title` char(20) NOT NULL,
 `Publisher` char(20) DEFAULT NULL,
 `Author` char(20) DEFAULT NULL,
 `Price` int(10) DEFAULT NULL,
 PRIMARY KEY (`BID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1

CREATE TABLE `bookcp` (
 `CPID` int(11) NOT NULL AUTO_INCREMENT,
 `CID` int(10) DEFAULT NULL,
 `BID` int(10) NOT NULL,
 `Status` bit(1) NOT NULL DEFAULT b'1',
 `ExpDate` date DEFAULT NULL,
 PRIMARY KEY (`CPID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1
