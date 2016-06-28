-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 28, 2016 at 01:56 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `tb`
--

DELIMITER $$
--
-- Functions
--
CREATE DEFINER=`root`@`localhost` FUNCTION `VERIFY_LOG_IN`(name VARCHAR(100),psword varchar(10)) RETURNS varchar(100) CHARSET latin1
BEGIN
	DECLARE name_count int;
    DECLARE psword1 varchar(10);
    DECLARE return_st varchar(100);
	SELECT COUNT(*) INTO name_count
    FROM user
    WHERE USER_NAME=name;
	IF name_count>0 THEN
    	SELECT PASSWORD INTO psword1
        FROM user
        WHERE USER_NAME=name;
    	IF psword1=psword THEN
        	SET return_st='DONE';
        ELSE
        	SET return_st='INCORRECT PASSWORD';
		END IF;
    ELSE
    	SET return_st='INVALID USERNAME';
	END IF;
	RETURN return_st;
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `VERIFY_SIGN_IN`(`name` VARCHAR(100), `email` VARCHAR(100), `pword` VARCHAR(10)) RETURNS varchar(200) CHARSET latin1
BEGIN
	DECLARE name_count int;
    DECLARE email_count int;
    DECLARE return_st varchar(200);
	SELECT COUNT(*) INTO name_count
    FROM user
    WHERE USER_NAME=name;
    SELECT COUNT(*) INTO email_count
    FROM user
    WHERE EMAIL_ID=email;
	IF name_count=0 THEN
    	IF email_count=0 THEN
        	SET return_st='DONE';
            INSERT INTO `user`(`USER_NAME`, `EMAIL_ID`, `PASSWORD`) VALUES (name,email,pword);
        ELSE
        	SET return_st='EMAIL ID ALREADY USED IN ANOTHER ACCOUNT';
		END IF;
    ELSE
    	SET return_st='USERNAME NOT AVAILABLE';
	END IF;
	RETURN return_st;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `answer`
--

CREATE TABLE IF NOT EXISTS `answer` (
  `QUESTION_NO` int(255) NOT NULL,
  `ANSWER_NO` int(255) NOT NULL AUTO_INCREMENT,
  `ANSWER_TEXT` text NOT NULL,
  `USER_NAME` varchar(100) NOT NULL,
  `DATE_TIME` datetime NOT NULL,
  PRIMARY KEY (`ANSWER_NO`),
  KEY `QUESTION_NO` (`QUESTION_NO`),
  KEY `USER_NAME` (`USER_NAME`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=24 ;

--
-- Dumping data for table `answer`
--

INSERT INTO `answer` (`QUESTION_NO`, `ANSWER_NO`, `ANSWER_TEXT`, `USER_NAME`, `DATE_TIME`) VALUES
(1, 1, 'It''s awesome !', 'Shamik', '2015-12-09 17:39:08'),
(1, 4, 'Thanks for the response @Shamik', 'bbbbb', '2015-12-15 17:39:08'),
(1, 7, 'nice place', 'bbbbb', '2015-12-05 17:39:08'),
(1, 8, 'testing', 'bbbbb', '2015-12-19 19:59:58'),
(7, 9, 'sorry sorry :(', 'faria', '2015-12-17 12:34:14'),
(11, 23, 'never even heard of it :/ ', 'faria', '2015-12-20 11:36:31');

-- --------------------------------------------------------

--
-- Table structure for table `description`
--

CREATE TABLE IF NOT EXISTS `description` (
  `TOURIST_SPOT_NAME` varchar(100) NOT NULL,
  `TEXT` text NOT NULL,
  PRIMARY KEY (`TOURIST_SPOT_NAME`),
  KEY `TOURIST_SPOT_NAME` (`TOURIST_SPOT_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `description`
--

INSERT INTO `description` (`TOURIST_SPOT_NAME`, `TEXT`) VALUES
('Ahsan Monjil', 'Ahsan Manzil (Ahsan Monjil) was the official residential palace and seat of the Dhaka Nawab Family.[1] This magnificent building is situated at Kumartoli along the banks of the Buriganga River in Dhaka, Bangladesh. The construction of this palace was started in 1859 and was completed in 1872.[1] It was constructed in the Indo-Saracenic Revival architecture. It has been designated as a national museum.'),
('Batali Hill', 'Batali Hill is the highest hill in the city of Chittagong, Bangladesh. It is located near the Tiger Pass crossing, about 1 kilometres from the center of the city, and falls under the Pahartoli Thana.\r\n\r\nThe hill is about 280 feet high.However, the hill faces threats of erosion and landslides due to illegal hill-cutting activities.In June, 2007, a massive landslide in the area surrounding Batali Hill killed at least 128 people.\r\n\r\nFoy''s Lake, the largest man-made lake in Chittagong, is situated next to the hill. There is also an Eternal Flame ("Shikha Onirban") commemorating the Bangladesh Liberation War martyrs'),
('Bhatiari', 'The Biggest Ship wrecking yard of Bangladesh is situated here.\r\nA scene of the movie Avengers: Age of Ultron was shot at the ship breaking yards of Chittagong'),
('Durga Sagar', 'Durga Sagar (known locally as Madhabpasha Dighi) is the biggest lake in Southern Bangladesh. It has a total area of about 2500 hectares. The lake is about 11 km far away from the Barisal city. Rani Durgabati mother of Raja Joy Narayan Excavated this lake at 1780. There is a hummock in the center of the lake which looks like a small island.'),
('Fantasy Kingdom', 'Located @Ashulia . A true combination of fun, entertainment and excitement in one complex.'),
('Foys Lake', 'Foy''s Lake is a man-made lake in Chittagong, Bangladesh. It was created in 1924 by constructing a dam across the stream that came down from the hills in the northern part of Chittagong. The purpose of creating an artificial lake was to provide water to the residence of railway colony. It was named after Mr Foy who was a Railway engineer and believed to materialized the project. Pahartali was basically a railway town with workshop, yard and shed. A good number of railway employees lives there. Presently, a carriage workshop, diesel workshop, loco shed, laboratory, stores, electric workshop, school (established in 1924) are located.\r\n\r\nThe area belongs to Railway. However, an amusement park, managed by the Concord group, is located here.'),
('Jaflong', 'Jaflong is a hill station and popular tourist destination in the Division of Sylhet, Bangladesh. It is located in Gowainghat Upazila of Sylhet District and situated at the border between Bangladesh and the Indian state of Meghalaya, overshadowed by subtropical mountains and rainforests. Jaflong is famous for its stone collections and is home of the Khasi tribe.'),
('Kaptai Lake', 'Kaptai Lake is a man made lake in south-eastern Bangladesh. It is located in the Kaptai Upazila under Rangamati District of Chittagong Division. The lake was created as a result of building the Kaptai Dam on the Karnaphuli River, as part of the Karnaphuli Hydro-electric project. The Kaptai Lake''s average depth is 100 feet (30 m) and maximum depth is 490 feet (150 m).'),
('Khulna University', 'Khulna University is a public university in Bangladesh. It is situated at Gollamari, Khulna, Bangladesh, by the river Moyur, beside the Khulna-Satkhira highway. The academic programs of Khulna University started on 31 August 1991 with 80 students in four disciplines. Khulna University has 26 disciplines under five schools and one institute. It is the only university in Bangladesh where student politics is not allowed. Students passing the Higher Secondary Certificate examination are very much interested in their admission in Khulna University for this politics-free environment. Khulna is an administrative division as well as the third largest metropolitan city in Bangladesh. The Mongla Sea Port, the Shipyard, the Bangladesh Naval Base are in Khulna. Khulna is an industrial city and famous for the Sundarbans.'),
('Lakhutia Jomidar Bari', 'This Jomidar bari is located at the village Lakhutia , also pronounced as Lakutia . The Jomidari was established by the Rupchondro Ray . But the further expansion was made by Rajchondro Ray who was a grand son of him, and he is the builder of this famous Lakhutia Jomidar Bari.'),
('Lalbagh Fort', 'Lalbagh Fort (also Fort Aurangabad) is an incomplete 17th century Mughal fort complex that stands proudly before the Buriganga River in the southwestern part of Dhaka, Bangladesh. The construction was started in 1678 AD by Mughal Subahdar Muhammad Azam Shah who was son of Emperor Aurangzeb and later emperor himself. His successor, Shaista Khan, did not continue the work, though he stayed in Dhaka up to 1688.'),
('Nandan Park', 'Nandan park is one of the most attractive amusement park at Savar suburb near Dhaka city in Bangladesh. Nandan Park is now country’s largest and only family entertainment center, which is attracting largest crowd every day.'),
('National Museum', 'The Bangladesh National Museum , originally established on 20 March 1913, albeit under another name (the Dhaka Museum), and formally inaugurated on 7 August 1913, was accorded the status of the national museum of Bangladesh on 17 November 1983. It is located at Shahbag, Dhaka. The museum is well organized and displays have been housed chronologically in several departments like department of ethnography and decorative art, department of history and classical art, department of natural history, and department of contemporary and world civilization. The museum also has a rich conservation laboratory.'),
('National Zoo', 'Dhaka Zoo, is a Zoo located in the Mirpur section of Dhaka, the capital city of Bangladesh. The Zoo contains many native and non-native animals and wild life, and hosts about three million visitors each year.\r\n\r\nEstablished in 1974, the 186-acre (75 ha) Dhaka Zoo is the largest zoo in Bangladesh, and is operated by the Ministry of Fisheries and Livestock.The zoo attracts around 10,000 visitors every day with the number increasing during the weekends and holidays.\r\n\r\nThe yearly budget of Dhaka Zoo is Tk 37.5 million, out of which Tk 25 million is spent on feeding the animals.'),
('Padma River', 'Padma is a major river in Bangladesh. It is the main distributary of the Ganges, flowing generally southeast for 120 kilometres (75 mi) to its confluence with the Meghna River near the Bay of Bengal. The city of Rajshahi is situated on the banks of the river.'),
('Potenga', 'Potenga is a popular tourist spot. The beach is very close to the Bangladesh Naval Academy of the Bangladesh Navy and Shah Amanat International Airport. Its width is narrow and swimming in the seas is not recommended. Part of the seashore is built-up with concrete walls, and large blocks of stones have been laid to prevent erosion. During the 1990s, a host of restaurants and kiosks sprouted out around the beach area. Lighting of the area has enhanced the security aspect of visiting at night.\n\nNowadays, alcohol peddling is very common at the beach. Vendors from the city sell their ice creams, cold drinks and food to the hundreds of tourists who come to Potenga Beach. According to the local people, Potenga is the best place for delicious, mouth-watering street food at very low costs. One of the popular dishes of the food stands is the fried, spicy mud crab served with a small plate full of falafel, garnished with cucumber and onion. The beach has a wonderful cool atmosphere even at the evening, and people come to enjoy the soothing breeze. The beach is lined with massive shady palm trees and fishing boats. It also has an array of speed boats for visitors. The beach, however, is quite sandy, with a few rocky patches.\n\nMost visitors come to Potenga Beach as it is known for having some of the most stunning sunsets and sunrises in Bangladesh.'),
('Ratargul Swamp Forest', 'Ratargul Swamp Forest is a freshwater swamp forest located in Gowainghat, Sylhet, Bangladesh. It is the only swamp forest located in Bangladesh and one of the few freshwater swamp forest in the world. The forest is naturally conserved under the Department of Forestry, Govt. of Bangladesh.\r\n\r\nThe evergreen forest is situated by the river Goain and linked with the cannel Chengir Khal. Most of the trees grow here are Pongamia pinnata (Koroch tree). The forest goes under 20–30 feet water in the rainy season. Rest of the year the water level is about 10 feet deep.'),
('Rupsha River', 'The Rupsha River is a river in southwestern Bangladesh and a distributary of the Ganges. It forms from the union of the Bhairab and Atrai rivers, and flows into the Pasur River. Its entire length is affected by tides.\r\nRupsha river, Khulna\r\n\r\nNear Chalna, it changes its name to Pasur River and flows into the Bay of Bengal.\r\n\r\nRupsha is one of the most famous rivers of Bangladesh. It flows by the side of Khulna, and connects to the Bay of Bengal through Poshur river at Mongla channel.\r\n\r\nA significant number of fisheries, dockyards, shipyards and factories are situated on the bank of this river. A significant number of families depend on catching fish in the river. There is a bridge over the river named Khan Jahan Ali Bridge. This bridge connects Khulna and Bagerhat Districts.'),
('Tajhat Palace', 'Tajhat Palace, Tajhat Rajbari, is a historic palace of Bangladesh, located in Tajhat, Rangpur. This palace now has been turned into a museum. Tajhat Palace is situated three km. south-east of the city of Rangpur, on the outskirts of town.'),
('Varendra Research Museum', 'Varendra Museum is a museum, research center and popular visitor attraction located at the heart of Rajshahi town and maintained by Rajshahi University in Bangladesh. It is considered the oldest museum in Bangladesh. Varendra museum was the first museum to be established in East Bengal in 1910. The museum started out as the collection for Varendra Anushandan Samiti or Varendra Investigation Society got its current name in 1919. The Rajahs of Rajshahi and Natore, notably prince Sharat Kumar Ray, donated their personal collections to Varendra Museum. Varendra refers to an ancient janapada roughly corresponding to modern northern Bangladesh.'),
('Vinnya Jagat Park', 'Vinnya jagat brings lots of fun to northwest of Bangladesh.It is one of the most spectacular (kind of theme) parks in the country and has become a great tourist attraction in Rangpur. It is about 100 acres acres.Vinnya jagat beautify by many rides. This place take you a daydream world.');

-- --------------------------------------------------------

--
-- Table structure for table `district`
--

CREATE TABLE IF NOT EXISTS `district` (
  `DISTRICT_NAME` varchar(100) NOT NULL,
  PRIMARY KEY (`DISTRICT_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `district`
--

INSERT INTO `district` (`DISTRICT_NAME`) VALUES
('Barisal'),
('Chittagong'),
('Dhaka'),
('Khulna'),
('Rajshahi'),
('Rangpur'),
('Sylhet');

-- --------------------------------------------------------

--
-- Table structure for table `hotel`
--

CREATE TABLE IF NOT EXISTS `hotel` (
  `HOTEL_NAME` varchar(200) NOT NULL,
  `DISTRICT_NAME` varchar(100) NOT NULL,
  PRIMARY KEY (`HOTEL_NAME`),
  KEY `DISTRICT_NAME` (`DISTRICT_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hotel`
--

INSERT INTO `hotel` (`HOTEL_NAME`, `DISTRICT_NAME`) VALUES
('Hotel Arena', 'Barisal'),
('Hotel Athena International', 'Barisal'),
('Hotel Agrabad', 'Chittagong'),
('Hotel Lord''s Inn (Pvt.) Ltd', 'Chittagong'),
('Hotel Raj', 'Chittagong'),
('Hotel Radisson Blu', 'Dhaka'),
('Hotel Sonargaon', 'Dhaka'),
('Westin', 'Dhaka'),
('City Inn Ltd.', 'Khulna'),
('Hotel Royal International', 'Khulna'),
('Hotel Dallas International', 'Rajshahi'),
('Hotel Nice International', 'Rajshahi'),
('Boishakhi', 'Rangpur'),
('Hotel North View', 'Rangpur'),
('Hotel Eastern', 'Sylhet'),
('Hotel Shahban', 'Sylhet');

-- --------------------------------------------------------

--
-- Table structure for table `image`
--

CREATE TABLE IF NOT EXISTS `image` (
  `PHOTO_ID` int(255) NOT NULL AUTO_INCREMENT,
  `TOURIST_SPOT_NAME` varchar(100) NOT NULL,
  `PHOTO_URL` varchar(150) NOT NULL,
  PRIMARY KEY (`PHOTO_ID`),
  KEY `TOURIST_SPOT_NAME` (`TOURIST_SPOT_NAME`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=28 ;

--
-- Dumping data for table `image`
--

INSERT INTO `image` (`PHOTO_ID`, `TOURIST_SPOT_NAME`, `PHOTO_URL`) VALUES
(1, 'Potenga', 'http://59.152.96.51/ci/images/Chittagong/potenga/potenga1.jpg'),
(2, 'Potenga', 'http://59.152.96.51/ci/images/Chittagong/potenga/potenga2.jpg'),
(3, 'Potenga', 'http://59.152.96.51/ci/images/Chittagong/potenga/potenga3.jpg'),
(4, 'Batali Hill', 'http://59.152.96.51/ci/images/chittagong/batalihill/batali1.jpg'),
(5, 'Batali Hill', 'http://59.152.96.51/ci/images/chittagong/batalihill/batali2.jpg'),
(6, 'Bhatiari', 'http://59.152.96.51/ci/images/chittagong/bhatiari/bhatiari1.jpg'),
(7, 'Bhatiari', 'http://59.152.96.51/ci/images/chittagong/bhatiari/bhatiari2.jpg'),
(8, 'Foys Lake', 'http://59.152.96.51/ci/images/chittagong/foyslake/foyslake1.jpg'),
(9, 'Foys Lake', 'http://59.152.96.51/ci/images/chittagong/foyslake/foyslake2.jpg'),
(10, 'Kaptai Lake', 'http://59.152.96.51/ci/images/chittagong/kaptailake/kaptai1.jpg'),
(11, 'Kaptai Lake', 'http://59.152.96.51/ci/images/chittagong/kaptailake/kaptai2.jpg'),
(12, 'Fantasy Kingdom', 'http://59.152.96.51/ci/images/dhaka/fantasykingdom/fantasykingdom.jpg'),
(13, 'Nandan Park', 'http://59.152.96.51/ci/images/dhaka/nandanpark/np.jpg'),
(14, 'Lalbagh Fort', 'http://59.152.96.51/ci/images/dhaka/lalbagh/lb.jpg'),
(15, 'National Museum', 'http://59.152.96.51/ci/images/dhaka/nationalmuseum/nm.jpg'),
(16, 'National Zoo', 'http://59.152.96.51/ci/images/dhaka/nationalzoo/nz.jpg'),
(17, 'Ahsan Monjil', 'http://59.152.96.51/ci/images/dhaka/ahsanmanzil/am.jpg'),
(18, 'Tajhat Palace', 'http://59.152.96.51/ci/images/rangpur/tajhat/tzb.png'),
(19, 'Vinnya Jagat Park', 'http://59.152.96.51/ci/images/rangpur/vinnyajagat/vj.jpg'),
(20, 'Lakhutia Jomidar Bari', 'http://59.152.96.51/ci/images/barisal/lakhutiajomidarbari/ljb.jpg'),
(21, 'Durga Sagar', 'http://59.152.96.51/ci/images/barisal/durgasagar/ds.jpg'),
(22, 'Rupsha River', 'http://59.152.96.51/ci/images/khulna/rupsha/rupsha.jpg'),
(23, 'Khulna University', 'http://59.152.96.51/ci/images/khulna/ku/ku.jpg'),
(24, 'Padma River', 'http://59.152.96.51/ci/images/rajshahi/padma/padma.jpg'),
(25, 'Varendra Research Museum', 'http://59.152.96.51/ci/images/rajshahi/varendra/varendra.jpg'),
(26, 'Ratargul Swamp Forest', 'http://59.152.96.51/ci/images/sylhet/ratargul/ratargul.jpg'),
(27, 'Jaflong', 'http://59.152.96.51/ci/images/sylhet/jaflong/jaflong.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `question`
--

CREATE TABLE IF NOT EXISTS `question` (
  `QUESTION_NO` int(255) NOT NULL AUTO_INCREMENT,
  `QUESTION_TEXT` text NOT NULL,
  `USER_NAME` varchar(100) NOT NULL,
  `DATE_TIME` datetime NOT NULL,
  PRIMARY KEY (`QUESTION_NO`),
  KEY `USER_NAME` (`USER_NAME`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=17 ;

--
-- Dumping data for table `question`
--

INSERT INTO `question` (`QUESTION_NO`, `QUESTION_TEXT`, `USER_NAME`, `DATE_TIME`) VALUES
(1, 'How''s Potenga?', 'bbbbb', '2015-12-10 17:39:08'),
(4, 'What what?', 'bbbbb', '2015-12-07 17:39:08'),
(5, 'What would you rate Jaflong out of 10?', 'bbbbb', '2015-12-16 17:39:08'),
(6, 'testing testing', 'bbbbb', '2015-12-19 19:30:48'),
(7, 'onek gula post diye felsi hehe sorry :P', 'faria', '2015-12-16 10:18:42'),
(8, 'beshi joss!', 'Faidarius', '2015-12-18 08:04:58'),
(11, 'Can anyone give any information about sajek?', 'faisal', '2015-12-20 11:17:35'),
(12, 'information about sajek?', 'bivor1', '2015-12-20 11:51:26'),
(13, 'information about sajek?', 'bivor1', '2015-12-20 11:51:27'),
(14, 'when can i go to khulna from dhaka?', 'Faidarius ', '2015-12-20 16:15:20'),
(15, 'kicchu hoynai app. soja 0 paba', 'modhu', '2015-12-20 16:33:54'),
(16, 'faislami korar jayga? ki likhso eisob forum e????!!! direct fail korba ', 'modhu', '2015-12-20 16:35:19');

-- --------------------------------------------------------

--
-- Table structure for table `review`
--

CREATE TABLE IF NOT EXISTS `review` (
  `REVIEW_NO` int(255) NOT NULL AUTO_INCREMENT,
  `TOURIST_SPOT_NAME` varchar(100) NOT NULL,
  `REVIEW` text NOT NULL,
  `USER_NAME` varchar(100) NOT NULL,
  `DATE_TIME` datetime NOT NULL,
  PRIMARY KEY (`REVIEW_NO`),
  KEY `TOURIST_SPOT_NAME` (`TOURIST_SPOT_NAME`),
  KEY `TOURIST_SPOT_NAME_2` (`TOURIST_SPOT_NAME`),
  KEY `USER_NAME` (`USER_NAME`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=35 ;

--
-- Dumping data for table `review`
--

INSERT INTO `review` (`REVIEW_NO`, `TOURIST_SPOT_NAME`, `REVIEW`, `USER_NAME`, `DATE_TIME`) VALUES
(2, 'Potenga', 'josss jayga', 'bbbbb', '2015-12-10 17:39:08'),
(5, 'Batali Hill', 'onk ucha', 'bbbbb', '2015-12-11 17:39:08'),
(6, 'Bhatiari', 'shei shei', 'bbbbb', '2015-12-12 17:39:08'),
(7, 'Foys Lake', 'shei mama shei', 'bbbbb', '2015-12-09 17:39:08'),
(9, 'Foys Lake', 'has a theme park', 'bbbbb', '2015-12-13 17:39:08'),
(11, 'Batali Hill', 'ucha pahar', 'bbbbb', '2015-12-08 17:39:08'),
(15, 'Ratargul Swamp Forest', 'onk shundor jaga', 'bbbbb', '2015-12-14 17:39:08'),
(16, 'Ratargul Swamp Forest', 'best swamp forest ', 'bbbbb', '2015-12-15 17:39:08'),
(19, 'Potenga', 'long sea beach', 'bbbbb', '2015-12-16 17:39:08'),
(20, 'Potenga', 'onk onk onk onk onk shundor', 'bbbbb', '2015-12-06 17:39:08'),
(22, 'Kaptai Lake', 'beautiful', 'bbbbb', '2015-12-05 17:39:08'),
(23, 'Potenga', 'r koto', 'bbbbb', '2015-12-17 17:39:08'),
(24, 'Potenga', 'matha noshto', 'Shamik', '2015-12-18 17:39:08'),
(26, 'Potenga', 'nice', 'bbbbb', '2015-12-19 17:39:08'),
(27, 'Foys Lake', 'purai kop obostha, mamma', 'Faidarius', '2015-12-15 10:19:34'),
(28, 'Ratargul Swamp Forest', 'panir jongol wow!!', 'Faidarius', '2015-12-16 20:45:18'),
(30, 'Potenga', 'Nice place to hangout with dear ones', 'bipul', '2015-12-20 00:21:58'),
(32, 'Foys Lake', 'nice placr', 'bivor1', '2015-12-20 11:50:00'),
(33, 'Padma River', 'That splashing beauty of afternoon ', 'sakin', '2015-12-20 12:13:28'),
(34, 'Durga Sagar', 'hoy kisu review dile?', 'modhu', '2015-12-20 16:35:53');

-- --------------------------------------------------------

--
-- Table structure for table `status`
--

CREATE TABLE IF NOT EXISTS `status` (
  `USER_NAME` varchar(100) NOT NULL,
  `STATUS` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`USER_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `status`
--

INSERT INTO `status` (`USER_NAME`, `STATUS`) VALUES
('bbbbb', 0),
('Bipul', 0),
('bivor', 0),
('bivor1', 0),
('Faidarius', 0),
('faisal', 0),
('faria', 0),
('modhu', 0),
('pavel', 0),
('sakin', 0),
('Shamik', 0),
('Shamik Roy', 0),
('tahsin', 0);

-- --------------------------------------------------------

--
-- Table structure for table `tourist_spot`
--

CREATE TABLE IF NOT EXISTS `tourist_spot` (
  `TOURIST_SPOT_NAME` varchar(200) NOT NULL,
  `DISTRICT_NAME` varchar(100) NOT NULL,
  PRIMARY KEY (`TOURIST_SPOT_NAME`),
  KEY `DISTRICT_NAME` (`DISTRICT_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tourist_spot`
--

INSERT INTO `tourist_spot` (`TOURIST_SPOT_NAME`, `DISTRICT_NAME`) VALUES
('Durga Sagar', 'Barisal'),
('Lakhutia Jomidar Bari', 'Barisal'),
('Batali Hill', 'Chittagong'),
('Bhatiari', 'Chittagong'),
('Foys Lake', 'Chittagong'),
('Kaptai Lake', 'Chittagong'),
('Potenga', 'Chittagong'),
('Ahsan Monjil', 'Dhaka'),
('Fantasy Kingdom', 'Dhaka'),
('Lalbagh Fort', 'Dhaka'),
('Nandan Park', 'Dhaka'),
('National Museum', 'Dhaka'),
('National Zoo', 'Dhaka'),
('Khulna University', 'Khulna'),
('Rupsha River', 'Khulna'),
('Padma River', 'Rajshahi'),
('Varendra Research Museum', 'Rajshahi'),
('Tajhat Palace', 'Rangpur'),
('Vinnya Jagat Park', 'Rangpur'),
('Jaflong', 'Sylhet'),
('Ratargul Swamp Forest', 'Sylhet');

-- --------------------------------------------------------

--
-- Table structure for table `transport`
--

CREATE TABLE IF NOT EXISTS `transport` (
  `SOURCE` varchar(100) NOT NULL,
  `DESTINATION` varchar(100) NOT NULL,
  `TRANSPORT_NAME` varchar(100) NOT NULL,
  `TRANSPORT_TYPE` varchar(100) NOT NULL,
  PRIMARY KEY (`SOURCE`,`DESTINATION`,`TRANSPORT_NAME`),
  KEY `SOURCE` (`SOURCE`),
  KEY `DESTINATION` (`DESTINATION`),
  KEY `SOURCE_2` (`SOURCE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transport`
--

INSERT INTO `transport` (`SOURCE`, `DESTINATION`, `TRANSPORT_NAME`, `TRANSPORT_TYPE`) VALUES
('Chittagong', 'Dhaka', 'Shohag', 'Bus'),
('Dhaka', 'Barisal', 'Greenline', 'Launch'),
('Dhaka', 'Chittagong', 'Bangladesh Biman', 'Flight'),
('Dhaka', 'Chittagong', 'Godhuli', 'Train'),
('Dhaka', 'Chittagong', 'Hanif', 'Bus'),
('Dhaka', 'Chittagong', 'Provati', 'Train'),
('Dhaka', 'Chittagong', 'Shohag', 'Bus'),
('Dhaka', 'Khulna', 'Hanif', 'Bus'),
('Dhaka', 'Khulna', 'Shohag', 'Bus'),
('Dhaka', 'Rajshahi', 'Hanif', 'Bus'),
('Dhaka', 'Rajshahi', 'Padma', 'Train'),
('Dhaka', 'Rajshahi', 'Shyamoli', 'Bus'),
('Dhaka', 'Rangpur', 'Bablu', 'Bus'),
('Dhaka', 'Rangpur', 'Drutojan', 'Train'),
('Dhaka', 'Rangpur', 'Hanif', 'Bus'),
('Dhaka', 'Rangpur', 'Tista', 'Train'),
('Dhaka', 'Sylhet', 'Greenline', 'Bus'),
('Dhaka', 'Sylhet', 'Parabat', 'Train'),
('Dhaka', 'Sylhet', 'Saudia', 'Bus');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `USER_NAME` varchar(100) CHARACTER SET latin1 NOT NULL,
  `EMAIL_ID` varchar(100) CHARACTER SET latin1 NOT NULL,
  `PASSWORD` varchar(10) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`USER_NAME`),
  UNIQUE KEY `USER_NAME` (`USER_NAME`),
  UNIQUE KEY `EMAIL_ID` (`EMAIL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`USER_NAME`, `EMAIL_ID`, `PASSWORD`) VALUES
('bbbbb', 'b', 'b'),
('Bipul', 'bipul@gmail.com', 'bipul'),
('bivor', 'bivor@gmail.com', '1234'),
('bivor1', 'bivor1@gmail.com', 'bivor'),
('Faidarius', 'f6@ymail.com', '123456'),
('faisal', 'faisal@gmail.com', 'faisal'),
('faria', 'fariatabassum3@gmail.com', '1234'),
('modhu', 'modhu@gmail.com', 'abcd'),
('pavel', 'pavel@gmail.com', 'pavel'),
('sakin', 'walt.sakin@gmail.com', '1234'),
('Shamik', 'shamikroy007@gmail.com', '1234'),
('Shamik Roy', 'shamikroy@gmail.com', '1234'),
('tahsin', 'tahsin@gmail.com', '12345');

--
-- Triggers `user`
--
DROP TRIGGER IF EXISTS `updating_status_table`;
DELIMITER //
CREATE TRIGGER `updating_status_table` AFTER INSERT ON `user`
 FOR EACH ROW begin
  insert into status (USER_NAME,STATUS) values (new.USER_NAME, 0);
end
//
DELIMITER ;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `answer`
--
ALTER TABLE `answer`
  ADD CONSTRAINT `answer_ibfk_1` FOREIGN KEY (`QUESTION_NO`) REFERENCES `question` (`QUESTION_NO`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `answer_ibfk_2` FOREIGN KEY (`USER_NAME`) REFERENCES `user` (`USER_NAME`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `description`
--
ALTER TABLE `description`
  ADD CONSTRAINT `description_ibfk_1` FOREIGN KEY (`TOURIST_SPOT_NAME`) REFERENCES `tourist_spot` (`TOURIST_SPOT_NAME`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `hotel`
--
ALTER TABLE `hotel`
  ADD CONSTRAINT `hotel_ibfk_1` FOREIGN KEY (`DISTRICT_NAME`) REFERENCES `district` (`DISTRICT_NAME`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `image`
--
ALTER TABLE `image`
  ADD CONSTRAINT `image_ibfk_1` FOREIGN KEY (`TOURIST_SPOT_NAME`) REFERENCES `tourist_spot` (`TOURIST_SPOT_NAME`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `question`
--
ALTER TABLE `question`
  ADD CONSTRAINT `question_ibfk_1` FOREIGN KEY (`USER_NAME`) REFERENCES `user` (`USER_NAME`);

--
-- Constraints for table `review`
--
ALTER TABLE `review`
  ADD CONSTRAINT `review_ibfk_1` FOREIGN KEY (`TOURIST_SPOT_NAME`) REFERENCES `tourist_spot` (`TOURIST_SPOT_NAME`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `review_ibfk_2` FOREIGN KEY (`USER_NAME`) REFERENCES `user` (`USER_NAME`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `status`
--
ALTER TABLE `status`
  ADD CONSTRAINT `statusTOuser` FOREIGN KEY (`USER_NAME`) REFERENCES `user` (`USER_NAME`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tourist_spot`
--
ALTER TABLE `tourist_spot`
  ADD CONSTRAINT `tourist_spot_ibfk_1` FOREIGN KEY (`DISTRICT_NAME`) REFERENCES `district` (`DISTRICT_NAME`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Constraints for table `transport`
--
ALTER TABLE `transport`
  ADD CONSTRAINT `transport_ibfk_3` FOREIGN KEY (`SOURCE`) REFERENCES `district` (`DISTRICT_NAME`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `transport_ibfk_4` FOREIGN KEY (`DESTINATION`) REFERENCES `district` (`DISTRICT_NAME`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
