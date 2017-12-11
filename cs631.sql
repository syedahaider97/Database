-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: sql2.njit.edu
-- Generation Time: Dec 08, 2017 at 07:52 PM
-- Server version: 5.5.29-log
-- PHP Version: 5.6.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `cs631`
--

-- --------------------------------------------------------

--
-- Table structure for table `AUTHOR`
--

CREATE TABLE IF NOT EXISTS `AUTHOR` (
  `AUTHORID` int(10) unsigned NOT NULL,
  `ANAME` varchar(50) CHARACTER SET latin1 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `AUTHOR`
--

INSERT INTO `AUTHOR` (`AUTHORID`, `ANAME`) VALUES
(2, 'George Lucas'),
(3, 'Suzanne Collins'),
(4, 'JRR Tolkien'),
(5, 'Stephen Sears'),
(6, 'Haruki Murakami'),
(7, 'Abraham Lincoln'),
(8, 'Arthur Doyle'),
(9, 'RL Stine'),
(10, 'Dorothy Sayers'),
(12345678, 'John Doe');

-- --------------------------------------------------------

--
-- Table structure for table `BOOK`
--

CREATE TABLE IF NOT EXISTS `BOOK` (
  `DOCID` int(10) unsigned NOT NULL,
  `ISBN` varchar(13) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `BOOK`
--

INSERT INTO `BOOK` (`DOCID`, `ISBN`) VALUES
(1, '1449161790181'),
(2, '2187315459453'),
(4, '3854792368847'),
(5, '9842027224536'),
(6, '9080568918614'),
(7, '4012746165288'),
(8, '3944480384258'),
(9, '9112789598173'),
(10, '3048067177868');

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
  `BDTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `RDTIME` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `BORROWS`
--

INSERT INTO `BORROWS` (`BORNUMBER`, `READERID`, `COPYNO`, `DOCID`, `LIBID`, `BDTIME`, `RDTIME`) VALUES
(1, 1, 1, 1, 1, '2017-11-30 20:55:28', NULL),
(3, 1, 7, 2, 3, '2012-08-23 04:00:00', NULL),
(4, 4, 19, 16, 3, '2012-08-23 04:00:00', NULL),
(5, 6, 14, 11, 3, '2012-08-23 04:00:00', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `BRANCH`
--

CREATE TABLE IF NOT EXISTS `BRANCH` (
  `LIBID` int(10) unsigned NOT NULL,
  `LNAME` varchar(50) NOT NULL,
  `LLOCATION` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `BRANCH`
--

INSERT INTO `BRANCH` (`LIBID`, `LNAME`, `LLOCATION`) VALUES
(1, 'Memorial Library', 'Trenton'),
(2, 'Central Library', 'New York City'),
(3, 'Bass Library', 'New Haven');

-- --------------------------------------------------------

--
-- Table structure for table `CHIEF_EDITOR`
--

CREATE TABLE IF NOT EXISTS `CHIEF_EDITOR` (
  `EDITOR_ID` int(10) unsigned NOT NULL,
  `ENAME` varchar(50) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `CHIEF_EDITOR`
--

INSERT INTO `CHIEF_EDITOR` (`EDITOR_ID`, `ENAME`) VALUES
(1, 'Mr.Editor'),
(2, 'John Time'),
(3, 'Sue Time'),
(4, 'Kevin Peoples'),
(5, 'Henry Peoples'),
(6, 'Mike York'),
(7, 'Justin York');

-- --------------------------------------------------------

--
-- Table structure for table `COPY`
--

CREATE TABLE IF NOT EXISTS `COPY` (
  `COPYNO` int(10) unsigned NOT NULL,
  `DOCID` int(10) unsigned NOT NULL,
  `LIBID` int(10) unsigned NOT NULL,
  `POSITION` varchar(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `COPY`
--

INSERT INTO `COPY` (`COPYNO`, `DOCID`, `LIBID`, `POSITION`) VALUES
(1, 1, 1, '001A01'),
(2, 1, 1, '001A01'),
(3, 4, 2, '010B03'),
(4, 4, 3, '100C10'),
(5, 5, 2, '010A06'),
(6, 1, 2, '100A09'),
(7, 2, 3, '100F03'),
(9, 6, 2, '100A02'),
(10, 7, 3, '001A02'),
(11, 8, 2, '001A03'),
(12, 9, 1, '001A04'),
(13, 10, 3, '001A01'),
(14, 11, 3, '001A05'),
(16, 13, 1, '100C10'),
(17, 14, 2, '100A09'),
(18, 15, 1, '100F01'),
(19, 16, 3, '001A01');

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

--
-- Dumping data for table `DOCUMENT`
--

INSERT INTO `DOCUMENT` (`DOCID`, `TITLE`, `PDATE`, `PUBLISHERID`) VALUES
(1, 'Harry Potter', '2017-11-01', 1),
(2, 'Star Wars', '2014-02-11', 2),
(4, 'Lord of the Rings', '1997-08-20', 2),
(5, 'The Civil War', '1965-11-30', 3),
(6, 'History of Japan', '2015-03-11', 3),
(7, 'Lincoln', '1981-06-29', 3),
(8, 'Sherlock Holmes', '2012-09-16', 4),
(9, 'Goosebumps', '2001-01-13', 4),
(10, 'Gaudy NIght', '2013-10-06', 4),
(11, 'Time Magazine', '2016-04-29', 5),
(13, 'New York Times Maagzine', '2017-04-12', 5),
(14, 'Dr. Brock''s Vision', '2004-08-07', 6),
(15, 'A Searing with NWF', '2015-02-18', 6),
(16, 'Bill Nye''s Energy Crisis', '2013-01-18', 6);

-- --------------------------------------------------------

--
-- Table structure for table `INV_EDITOR`
--

CREATE TABLE IF NOT EXISTS `INV_EDITOR` (
  `DOCID` int(10) unsigned NOT NULL DEFAULT '0',
  `ISSUE_NO` int(10) unsigned NOT NULL DEFAULT '0',
  `IENAME` varchar(50) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `INV_EDITOR`
--

INSERT INTO `INV_EDITOR` (`DOCID`, `ISSUE_NO`, `IENAME`) VALUES
(1, 1, 'Editor'),
(11, 1, 'Jimmy Time'),
(13, 1, 'Tom York'),
(11, 2, 'Jimmy Time'),
(13, 2, 'Tom York'),
(11, 3, 'John Time'),
(13, 3, 'Frank York');

-- --------------------------------------------------------

--
-- Table structure for table `JOURNAL_ISSUE`
--

CREATE TABLE IF NOT EXISTS `JOURNAL_ISSUE` (
  `DOCID` int(10) unsigned NOT NULL DEFAULT '0',
  `ISSUE_NO` int(10) unsigned NOT NULL DEFAULT '0',
  `SCOPE` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `JOURNAL_ISSUE`
--

INSERT INTO `JOURNAL_ISSUE` (`DOCID`, `ISSUE_NO`, `SCOPE`) VALUES
(1, 1, 'Editor'),
(11, 1, 'Politics'),
(11, 2, 'Nature'),
(11, 3, 'Smartphones'),
(13, 1, 'Earthquake in Timbaktu'),
(13, 2, 'Election Results'),
(13, 3, 'Energy Crisis');

-- --------------------------------------------------------

--
-- Table structure for table `JOURNAL_VOLUME`
--

CREATE TABLE IF NOT EXISTS `JOURNAL_VOLUME` (
  `DOCID` int(10) unsigned NOT NULL,
  `JVOLUME` int(10) unsigned NOT NULL,
  `EDITOR_ID` int(10) unsigned NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `JOURNAL_VOLUME`
--

INSERT INTO `JOURNAL_VOLUME` (`DOCID`, `JVOLUME`, `EDITOR_ID`) VALUES
(1, 0, 1),
(11, 1, 1),
(13, 1, 6);

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

--
-- Dumping data for table `PROCEEDINGS`
--

INSERT INTO `PROCEEDINGS` (`DOCID`, `CDATE`, `CLOCATION`, `CEDITOR`) VALUES
(14, '2004-08-07', 'Toronto, Canada', 'Dr. Johnson'),
(15, '2015-02-18', 'Washington D.C., Virginia', 'Dr. Gary'),
(16, '2013-01-18', 'Dallas, Texas', 'Dr. Murry');

-- --------------------------------------------------------

--
-- Table structure for table `PUBLISHER`
--

CREATE TABLE IF NOT EXISTS `PUBLISHER` (
  `PUBLISHERID` int(10) unsigned NOT NULL,
  `PUBNAME` varchar(50) CHARACTER SET utf8 NOT NULL,
  `ADDRESS` varchar(50) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `PUBLISHER`
--

INSERT INTO `PUBLISHER` (`PUBLISHERID`, `PUBNAME`, `ADDRESS`) VALUES
(1, 'Books inc', '123 book street'),
(2, 'Fiction Books Inc.', '277 Book Dr.'),
(3, 'Non-Fiction Books Inc.', '641 Reading St.'),
(4, 'Mystery Corp.', '711 Spooky Ave.'),
(5, 'Journals Paradise', '357 Edition St.'),
(6, 'Conference Hut', '985 Knowledge Blvd.');

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

--
-- Dumping data for table `READER`
--

INSERT INTO `READER` (`READERID`, `RTYPE`, `RNAME`, `ADDRESS`) VALUES
(1, 'Student', 'Walter Torres', '242 White Pine Lane'),
(2, 'Student', 'Beverly Butler', '2199 Irving Road'),
(3, 'Student', 'Chris Bennett', '4772 Ashwood Drive'),
(4, 'Staff', 'Amanda Phillips', '1215 Burnside Court'),
(5, 'Staff', 'Jimmy Harris', '3631 Scheuvront Drive'),
(6, 'Staff', 'Michelle Patterson', '1441 Simpson Avenue'),
(7, 'Senior Citizen', 'Karen Long', '3285 Penn Street'),
(8, 'Senior Citizen', 'Justin Henderson', '2301 Spruce Drive'),
(9, 'Senior Citizen', 'Frank Hall', '3061 Kyle Street'),
(10, 'Student', 'Christine Murphy', '2740 Lewis Street');

-- --------------------------------------------------------

--
-- Table structure for table `RESERVES`
--

CREATE TABLE IF NOT EXISTS `RESERVES` (
  `RESUMBER` int(10) unsigned NOT NULL,
  `READERID` int(10) unsigned NOT NULL,
  `DOCID` int(10) unsigned NOT NULL,
  `COPYNO` int(10) unsigned NOT NULL,
  `LIBID` int(10) unsigned NOT NULL,
  `DTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
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
-- Dumping data for table `WRITES`
--

INSERT INTO `WRITES` (`AUTHORID`, `DOCID`) VALUES
(2, 2),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10);

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
 ADD PRIMARY KEY (`BORNUMBER`), ADD KEY `READERID` (`READERID`), ADD KEY `COPYNO_2` (`COPYNO`,`DOCID`,`LIBID`), ADD KEY `BORROWS_ibfk_2` (`DOCID`);

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
 ADD PRIMARY KEY (`COPYNO`,`DOCID`,`LIBID`), ADD KEY `DOCID` (`DOCID`), ADD KEY `LIBID` (`LIBID`);

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
 ADD PRIMARY KEY (`DOCID`,`ISSUE_NO`), ADD UNIQUE KEY `DOCISSUE` (`DOCID`,`ISSUE_NO`);

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
 ADD PRIMARY KEY (`RESUMBER`), ADD KEY `DOCID` (`DOCID`,`COPYNO`,`LIBID`), ADD KEY `RESERVES_ibfk_2` (`COPYNO`), ADD KEY `RESERVES_ibfk_3` (`LIBID`), ADD KEY `READERID` (`READERID`);

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
ADD CONSTRAINT `BORROWS_ibfk_2` FOREIGN KEY (`DOCID`) REFERENCES `COPY` (`DOCID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `BORROWS_ibfk_1` FOREIGN KEY (`COPYNO`) REFERENCES `COPY` (`COPYNO`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `COPY`
--
ALTER TABLE `COPY`
ADD CONSTRAINT `COPY_ibfk_2` FOREIGN KEY (`LIBID`) REFERENCES `BRANCH` (`LIBID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `COPY_ibfk_1` FOREIGN KEY (`DOCID`) REFERENCES `DOCUMENT` (`DOCID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `DOCUMENT`
--
ALTER TABLE `DOCUMENT`
ADD CONSTRAINT `change_publisherid` FOREIGN KEY (`PUBLISHERID`) REFERENCES `PUBLISHER` (`PUBLISHERID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `INV_EDITOR`
--
ALTER TABLE `INV_EDITOR`
ADD CONSTRAINT `INV_EDITOR_ibfk_2` FOREIGN KEY (`DOCID`) REFERENCES `JOURNAL_ISSUE` (`DOCID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `INV_EDITOR_ibfk_1` FOREIGN KEY (`DOCID`, `ISSUE_NO`) REFERENCES `JOURNAL_ISSUE` (`DOCID`, `ISSUE_NO`);

--
-- Constraints for table `JOURNAL_ISSUE`
--
ALTER TABLE `JOURNAL_ISSUE`
ADD CONSTRAINT `JOURNAL_ISSUE_ibfk_1` FOREIGN KEY (`DOCID`) REFERENCES `JOURNAL_VOLUME` (`DOCID`) ON DELETE CASCADE ON UPDATE CASCADE;

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
ADD CONSTRAINT `RESERVES_ibfk_4` FOREIGN KEY (`READERID`) REFERENCES `READER` (`READERID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `RESERVES_ibfk_1` FOREIGN KEY (`DOCID`) REFERENCES `COPY` (`DOCID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `RESERVES_ibfk_2` FOREIGN KEY (`COPYNO`) REFERENCES `COPY` (`COPYNO`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `RESERVES_ibfk_3` FOREIGN KEY (`LIBID`) REFERENCES `COPY` (`LIBID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `WRITES`
--
ALTER TABLE `WRITES`
ADD CONSTRAINT `change_writes_authorid` FOREIGN KEY (`AUTHORID`) REFERENCES `AUTHOR` (`AUTHORID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `change_writes_docid` FOREIGN KEY (`DOCID`) REFERENCES `BOOK` (`DOCID`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
