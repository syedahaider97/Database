
--
-- Table structure for table `COPY`
--

CREATE TABLE IF NOT EXISTS `COPY` (
  `COPYNO` int(10) unsigned NOT NULL,
  `DOCID` int(10) unsigned NOT NULL,
  `LIBID` int(10) unsigned NOT NULL,
  `POSITION` varchar(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
