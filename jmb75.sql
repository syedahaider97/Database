-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: sql2.njit.edu
-- Generation Time: Nov 21, 2017 at 08:36 PM
-- Server version: 5.5.29-log
-- PHP Version: 5.6.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `jmb75`
--

-- --------------------------------------------------------

--
-- Table structure for table `AUTHOR`
--

CREATE TABLE IF NOT EXISTS `AUTHOR` (
  `AUTHORID` int(10) unsigned NOT NULL DEFAULT '0',
  `ANAME` varchar(50) CHARACTER SET latin1 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `AUTHOR`
--

INSERT INTO `AUTHOR` (`AUTHORID`, `ANAME`) VALUES
(12345678, 'John Doe');

-- --------------------------------------------------------

--
-- Table structure for table `BOOK`
--

CREATE TABLE IF NOT EXISTS `BOOK` (
  `DOCID` int(10) unsigned NOT NULL,
  `ISBN` int(13) unsigned NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `BORROWS`
--

CREATE TABLE IF NOT EXISTS `BORROWS` (
  `BORNUMBER` int(10) unsigned NOT NULL,
  `READERID` int(10) unsigned NOT NULL,
  `COPYNO` int(10) unsigned NOT NULL,
  `DOCID` int(10) unsigned NOT NULL,
  `LIBID` int(10) unsigned NOT NULL,
  `BDTIME` time NOT NULL,
  `RDTIME` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `BRANCH`
--

CREATE TABLE IF NOT EXISTS `BRANCH` (
  `LIBID` int(10) unsigned NOT NULL,
  `LNAME` varchar(50) NOT NULL,
  `LLOCATION` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `CHIEF_EDITOR`
--

CREATE TABLE IF NOT EXISTS `CHIEF_EDITOR` (
  `EDITOR_ID` int(10) unsigned NOT NULL,
  `ENAME` varchar(50) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `COPY`
--

CREATE TABLE IF NOT EXISTS `COPY` (
  `COPYNO` int(10) unsigned NOT NULL,
  `DOCID` int(10) unsigned NOT NULL,
  `LIBID` int(10) unsigned NOT NULL,
  `POSITION` varchar(6) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `DOCUMENT`
--

CREATE TABLE IF NOT EXISTS `DOCUMENT` (
  `DOCID` int(10) unsigned NOT NULL,
  `TITLE` varchar(50) NOT NULL,
  `PDATE` date DEFAULT NULL,
  `PUBLISHERID` int(10) unsigned NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `INV_EDITOR`
--

CREATE TABLE IF NOT EXISTS `INV_EDITOR` (
  `DOCID` int(10) unsigned NOT NULL DEFAULT '0',
  `ISSUE_NO` int(10) unsigned NOT NULL DEFAULT '0',
  `IENAME` varchar(50) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `JOURNAL_ISSUE`
--

CREATE TABLE IF NOT EXISTS `JOURNAL_ISSUE` (
  `DOCID` int(10) unsigned NOT NULL DEFAULT '0',
  `ISSUE_NO` int(10) unsigned NOT NULL DEFAULT '0',
  `SCOPE` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `JOURNAL_VOLUME`
--

CREATE TABLE IF NOT EXISTS `JOURNAL_VOLUME` (
  `DOCID` int(10) unsigned NOT NULL,
  `JVOLUME` int(10) unsigned NOT NULL,
  `EDITOR_ID` int(10) unsigned NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `PROCEEDINGS`
--

CREATE TABLE IF NOT EXISTS `PROCEEDINGS` (
  `DOCID` int(10) unsigned NOT NULL,
  `CDATE` date DEFAULT NULL,
  `CLOCATION` varchar(50) NOT NULL,
  `CEDITOR` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `PUBLISHER`
--

CREATE TABLE IF NOT EXISTS `PUBLISHER` (
  `PUBLISHERID` int(10) unsigned NOT NULL,
  `PUBNAME` varchar(50) CHARACTER SET utf8 NOT NULL,
  `ADDRESS` varchar(50) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `READER`
--

CREATE TABLE IF NOT EXISTS `READER` (
  `READERID` int(10) unsigned NOT NULL,
  `RTYPE` varchar(50) NOT NULL,
  `RNAME` varchar(50) NOT NULL,
  `ADDRESS` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `RESERVES`
--

CREATE TABLE IF NOT EXISTS `RESERVES` (
  `RESNUMBER` int(10) unsigned NOT NULL,
  `READERID` int(10) unsigned NOT NULL,
  `DOCID` int(10) unsigned NOT NULL,
  `COPYNO` int(10) unsigned NOT NULL,
  `LIBID` int(10) unsigned NOT NULL,
  `DTIME` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `WRITES`
--

CREATE TABLE IF NOT EXISTS `WRITES` (
  `AUTHORID` int(10) unsigned NOT NULL,
  `DOCID` int(10) unsigned NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `AUTHOR`
--
ALTER TABLE `AUTHOR`
 ADD PRIMARY KEY (`AUTHORID`);

--
-- Indexes for table `BOOK`
--
ALTER TABLE `BOOK`
 ADD PRIMARY KEY (`DOCID`);

--
-- Indexes for table `BORROWS`
--
ALTER TABLE `BORROWS`
 ADD PRIMARY KEY (`BORNUMBER`), ADD KEY `READERID` (`READERID`), ADD KEY `COPYNO_2` (`COPYNO`,`DOCID`,`LIBID`);

--
-- Indexes for table `BRANCH`
--
ALTER TABLE `BRANCH`
 ADD PRIMARY KEY (`LIBID`);

--
-- Indexes for table `CHIEF_EDITOR`
--
ALTER TABLE `CHIEF_EDITOR`
 ADD PRIMARY KEY (`EDITOR_ID`);

--
-- Indexes for table `COPY`
--
ALTER TABLE `COPY`
 ADD PRIMARY KEY (`COPYNO`,`DOCID`,`LIBID`);

--
-- Indexes for table `DOCUMENT`
--
ALTER TABLE `DOCUMENT`
 ADD PRIMARY KEY (`DOCID`), ADD KEY `PUBLISHERID` (`PUBLISHERID`);

--
-- Indexes for table `INV_EDITOR`
--
ALTER TABLE `INV_EDITOR`
 ADD PRIMARY KEY (`DOCID`,`ISSUE_NO`,`IENAME`), ADD KEY `ISSUE_NO` (`ISSUE_NO`), ADD KEY `DOCID` (`DOCID`,`ISSUE_NO`);

--
-- Indexes for table `JOURNAL_ISSUE`
--
ALTER TABLE `JOURNAL_ISSUE`
 ADD PRIMARY KEY (`DOCID`,`ISSUE_NO`);

--
-- Indexes for table `JOURNAL_VOLUME`
--
ALTER TABLE `JOURNAL_VOLUME`
 ADD PRIMARY KEY (`DOCID`), ADD KEY `EDITOR_ID` (`EDITOR_ID`);

--
-- Indexes for table `PROCEEDINGS`
--
ALTER TABLE `PROCEEDINGS`
 ADD PRIMARY KEY (`DOCID`);

--
-- Indexes for table `PUBLISHER`
--
ALTER TABLE `PUBLISHER`
 ADD PRIMARY KEY (`PUBLISHERID`);

--
-- Indexes for table `READER`
--
ALTER TABLE `READER`
 ADD PRIMARY KEY (`READERID`);

--
-- Indexes for table `RESERVES`
--
ALTER TABLE `RESERVES`
 ADD PRIMARY KEY (`RESNUMBER`), ADD KEY `DOCID` (`DOCID`,`COPYNO`,`LIBID`), ADD KEY `COPYNO` (`COPYNO`,`LIBID`), ADD KEY `RESERVES_ibfk_4` (`READERID`), ADD KEY `RESERVES_ibfk_3` (`LIBID`);

--
-- Indexes for table `WRITES`
--
ALTER TABLE `WRITES`
 ADD PRIMARY KEY (`AUTHORID`,`DOCID`), ADD KEY `change_writes_docid` (`DOCID`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `BOOK`
--
ALTER TABLE `BOOK`
ADD CONSTRAINT `change_book_docid` FOREIGN KEY (`DOCID`) REFERENCES `DOCUMENT` (`DOCID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `BORROWS`
--
ALTER TABLE `BORROWS`
ADD CONSTRAINT `BORROWS_ibfk_1` FOREIGN KEY (`COPYNO`) REFERENCES `COPY` (`COPYNO`);

--
-- Constraints for table `DOCUMENT`
--
ALTER TABLE `DOCUMENT`
ADD CONSTRAINT `change_publisherid` FOREIGN KEY (`PUBLISHERID`) REFERENCES `PUBLISHER` (`PUBLISHERID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `INV_EDITOR`
--
ALTER TABLE `INV_EDITOR`
ADD CONSTRAINT `INV_EDITOR_ibfk_1` FOREIGN KEY (`DOCID`, `ISSUE_NO`) REFERENCES `JOURNAL_ISSUE` (`DOCID`, `ISSUE_NO`),
ADD CONSTRAINT `INV_EDITOR_ibfk_2` FOREIGN KEY (`DOCID`) REFERENCES `JOURNAL_ISSUE` (`DOCID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `INV_EDITOR_ibfk_3` FOREIGN KEY (`ISSUE_NO`) REFERENCES `JOURNAL_ISSUE` (`DOCID`);

--
-- Constraints for table `JOURNAL_ISSUE`
--
ALTER TABLE `JOURNAL_ISSUE`
ADD CONSTRAINT `JOURNAL_ISSUE_ibfk_1` FOREIGN KEY (`DOCID`) REFERENCES `JOURNAL_VOLUME` (`DOCID`);

--
-- Constraints for table `JOURNAL_VOLUME`
--
ALTER TABLE `JOURNAL_VOLUME`
ADD CONSTRAINT `Change_DOCID` FOREIGN KEY (`DOCID`) REFERENCES `DOCUMENT` (`DOCID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `Change_EDITOR_ID` FOREIGN KEY (`EDITOR_ID`) REFERENCES `CHIEF_EDITOR` (`EDITOR_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `PROCEEDINGS`
--
ALTER TABLE `PROCEEDINGS`
ADD CONSTRAINT `change_proceedings_docid` FOREIGN KEY (`DOCID`) REFERENCES `DOCUMENT` (`DOCID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `RESERVES`
--
ALTER TABLE `RESERVES`
ADD CONSTRAINT `RESERVES_ibfk_1` FOREIGN KEY (`COPYNO`) REFERENCES `COPY` (`COPYNO`);

--
-- Constraints for table `WRITES`
--
ALTER TABLE `WRITES`
ADD CONSTRAINT `change_writes_authorid` FOREIGN KEY (`AUTHORID`) REFERENCES `AUTHOR` (`AUTHORID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `change_writes_docid` FOREIGN KEY (`DOCID`) REFERENCES `BOOK` (`DOCID`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
