-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- 主機： 127.0.0.1
-- 產生時間： 2021-01-07 14:28:07
-- 伺服器版本： 10.4.14-MariaDB
-- PHP 版本： 7.4.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 資料庫： `missa_group7`
--

-- --------------------------------------------------------

--
-- 資料表結構 `member`
--

CREATE TABLE `member` (
  `member_id` int(11) NOT NULL,
  `member_name` varchar(30) DEFAULT NULL,
  `member_email` varchar(50) DEFAULT NULL,
  `member_password` varchar(30) DEFAULT NULL,
  `member_modified` datetime(6) DEFAULT NULL,
  `member_created` datetime(6) DEFAULT NULL,
  `member_login_times` int(11) DEFAULT NULL,
  `member_status` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 傾印資料表的資料 `member`
--

INSERT INTO `member` (`member_id`, `member_name`, `member_email`, `member_password`, `member_modified`, `member_created`, `member_login_times`, `member_status`) VALUES
(1, '管理員', 'manager@gmail.com', 'manager123', '2020-12-27 16:17:13.000000', '2020-12-27 16:17:13.000000', 1, 1),
(2, '正常人', 'may@gmail.com', 'mmay1005', '2020-12-30 11:39:31.000000', '2020-12-30 11:39:31.000000', 39, 1),
(3, 'jamie', '1314@gmail.com', '1314wuwu', '2020-12-31 11:39:31.000000', '2020-12-31 11:39:31.000000', 30, 1),
(4, '卷卷', 'qq@gmail.com', 'qqqq1112', '2020-12-31 15:02:16.000000', '2020-12-31 15:02:16.000000', 2, 1),
(6, 'lili', 'lll@gmail.com', 'llll1111', '2021-01-06 07:10:05.000000', '2021-01-06 07:10:05.000000', 10, 1),
(8, 'ting', 'ting@gmail.com', 'ting1202', '2021-01-04 07:10:05.000000', '2021-01-03 07:10:05.000000', 10, 1);

-- --------------------------------------------------------

--
-- 資料表結構 `movie`
--

CREATE TABLE `movie` (
  `movie_id` int(11) NOT NULL,
  `movie_name` varchar(250) DEFAULT NULL,
  `movie_intro` varchar(1000) DEFAULT NULL,
  `movie_length` varchar(30) DEFAULT NULL,
  `movie_image` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 傾印資料表的資料 `movie`
--

INSERT INTO `movie` (`movie_id`, `movie_name`, `movie_intro`, `movie_length`, `movie_image`) VALUES
(5, '致命天際線', '二戰期間，一名帶著機密貨物的女飛行員慕德（克蘿伊摩蕾茲 飾）登上一台B-17轟炸機，面對不請自來的慕德，男性機組人員對她的不滿與鄙視逐漸高漲，眼看雙方衝突越演越烈之際，他們目擊雲層中出現了神祕暗影，究竟這個暗影本體是來襲的日本空軍，還是潛藏在雲端的未知恐怖？', '1 時 23 分', 'sky.jpg'),
(6, '空中謎航', '莎拉（愛莉森威廉絲 飾）和傑克森（亞歷山大德雷蒙 飾）準備搭飛機到熱帶島嶼參加朋友的婚禮，沒想到原訂一個多小時的航程，在飛機起飛沒多久後，機師便心臟病發身亡。他們不知道自己在哪裡，更不知道該如何駕駛飛機。更慘的是，暴風雨即將迎面來襲，這次他們決心放手一搏，靠自己把握最後活下去的機會⋯⋯。', '1 時 32 分', 'plane.jpg'),
(7, '鬼滅之刃劇場版 無限列車篇', '《鬼滅之刃》是家人慘遭鬼殺害的少年－竈門炭治郎，為了讓化為鬼的妹妹禰豆子恢復回人 類，自願加入「鬼殺隊」的故事。以人鬼間的悲痛故事、驚心動魄的劍戰，以及偶然穿插的 滑稽場景，贏得廣大人氣，不僅紅遍日本，更掀起全球觀眾的熱烈討論。 緊接在電視版動畫《竈門炭治郎‧立志篇》之後的故事《無限列車篇》，即將登上大銀幕。 炭治郎等人完成「蝴蝶屋」的訓練，下一個目的地是開往黑暗的「無限列車」。 以多人行蹤不明的這輛列車為舞台，炭治郎帶著禰豆子與善逸、伊之助一行人，與鬼殺隊最 強劍士〝柱〞其中之一「炎柱‧煉獄杏壽郎」會合， 新的任務即將開始！', '120分鐘', 'Demon_Slayer.jpg'),
(8, '名偵探柯南：紅之校外旅行', '以古都京都為舞台，新一將挑戰兇手為天狗的連環殺人案！ 而他與小蘭的戀情又會走向何方呢!? 因校外旅行而來到京都的新一，在清水寺遇見母親有希子的朋友——女演員鞍知景子。和大學同學來替老友掃墓的景子，委託新一解讀同學收到的暗號。當晚，新一帶著小蘭、園子、世良造訪投宿同一家飯店的景子房間，發現那裡聚集了即將上映的電影《紅之修羅天狗》相關人員。演員井隼森也、電影導演馬山峰人、作曲家阿賀田力、編劇西木太郎都是景子的大學同學。 新一接過西木收到的暗號便和景子等人道別，然而緊接著發生了西木被殺害的事件。案發現場如同養源院的血天井一般，天花板染著鮮血。天花板上的血腳印一直延續到窗戶，世良推測能在空中飛翔、擁有神力的怪物即是兇手。景子等人害怕遭到天狗襲擊而顫慄不已。 天狗是這次電影的題材，而新的暗號也與天狗經常拿在手上的八角金盤葉一起被放進了西木的懷中。之後，電影相關人員陸續受到天狗襲擊……。', '100分鐘', 'conan.jpg'),
(9, '古魯家族：新石代', '古魯家族曾經遇到不少危險和災難，像是從滿口尖牙的史前怪獸嘴裡驚險逃生，並且在世界末日中倖免於難，但是他們現在將面對最大的挑戰：另一個家族。 古魯家族需要找到一個全新的地方生活，於是這個史前時代的第一家族就勇闖新世界，尋找一個全新的家園。當他們找到一個受到周全保護，風景優美、氣候宜人，完全符合他們需求的人間樂園的時候，他們都覺得他們所有的問題都解決了…除了一件麻煩事，那就是有另一個家族已經住在那裡：郝野人家族。 郝野人家族顧名思義就是他們的生活過得比古魯家族好太多了，他們有豪華的樹屋、各種先進的發明，以及擁有人工灌溉系統，一望無際的新鮮菜園。換句話說，他們比古魯家族進步太多了。當他們招待古魯家族成為史上第一個到別人家作客的客人之後，古魯這個山頂洞人家族和郝野人這個現代家族很快就產生了越來越激烈的衝突和矛盾。 當這兩個家族看似水火不容的時候，一個全新的威脅即將迫使他們遠離安逸的田園生活，前往危險的外面世界展開一場史詩級的冒險旅程。他們不只是必須接受和擁抱彼此之間的不同、從對方身上得到力量，而且必須共同創造一個美好的未來。', '95分鐘', 'golu.jpg');

-- --------------------------------------------------------

--
-- 資料表結構 `orders`
--

CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL,
  `member_name` varchar(30) DEFAULT NULL,
  `member_email` varchar(30) DEFAULT NULL,
  `payment_method` varchar(30) DEFAULT NULL,
  `picker_name` varchar(30) DEFAULT NULL,
  `picker_phone` varchar(30) DEFAULT NULL,
  `order_create` datetime(6) DEFAULT NULL,
  `order_modify` datetime(6) DEFAULT NULL,
  `state` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 傾印資料表的資料 `orders`
--

INSERT INTO `orders` (`order_id`, `member_name`, `member_email`, `payment_method`, `picker_name`, `picker_phone`, `order_create`, `order_modify`, `state`) VALUES
(66, 'jamie', '1314@gmail.com', 'ATM', 'jolly', '0918237546', '2021-01-07 13:09:17.000000', '2021-01-07 13:09:17.000000', 0),
(67, 'jamie', '1314@gmail.com', '信用卡', 'jamie', '0918237546', '2021-01-07 13:10:22.000000', '2021-01-07 13:10:22.000000', 0),
(68, 'lili', 'lll@gmail.com', '信用卡', 'jamie', '0918237546', '2021-01-07 13:11:46.000000', '2021-01-07 13:11:46.000000', 0),
(69, 'lili', 'lll@gmail.com', '信用卡', '', '', '2021-01-07 13:12:29.000000', '2021-01-07 13:12:29.000000', 0),
(70, '卷卷', 'qq@gmail.com', '信用卡', '醜醜', '0911878787', '2021-01-07 13:16:42.000000', '2021-01-07 13:16:42.000000', 0),
(71, '卷卷', 'qq@gmail.com', 'ATM', '醜醜', '0911878787', '2021-01-07 13:17:17.000000', '2021-01-07 13:17:17.000000', 0),
(72, '正常人', 'may@gmail.com', 'ATM', '不正常人', '0966666666', '2021-01-07 13:21:34.000000', '2021-01-07 13:21:34.000000', 0),
(73, '正常人', 'may@gmail.com', '信用卡', '漿爸', '0966878787', '2021-01-07 13:22:40.000000', '2021-01-07 13:22:40.000000', 0),
(74, 'ting', 'ting@gmail.com', 'ATM', '妤妤', '0912345678', '2021-01-07 13:26:30.000000', '2021-01-07 13:26:30.000000', 0),
(75, 'ting', 'ting@gmail.com', '信用卡', 'jolly', '0911335577', '2021-01-07 13:27:13.000000', '2021-01-07 13:27:13.000000', 0);

-- --------------------------------------------------------

--
-- 資料表結構 `session`
--

CREATE TABLE `session` (
  `session_id` int(11) NOT NULL,
  `datetime` datetime(6) DEFAULT NULL,
  `lobby` varchar(11) DEFAULT NULL,
  `movie_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 傾印資料表的資料 `session`
--

INSERT INTO `session` (`session_id`, `datetime`, `lobby`, `movie_id`) VALUES
(12, '2021-02-01 20:56:00.000000', '3', 5),
(13, '2020-01-10 10:10:00.000000', '1', 5),
(14, '2020-01-10 14:10:00.000000', '1', 5),
(15, '2021-02-01 16:24:00.000000', '2', 6),
(16, '2021-02-01 20:56:00.000000', '3', 6),
(17, '2020-01-10 12:20:00.000000', '2', 7),
(18, '2020-01-10 15:45:00.000000', '3', 7),
(19, '2020-01-11 17:10:00.000000', '3', 7),
(20, '2020-01-11 19:20:00.000000', '1', 8),
(21, '2020-01-13 19:20:00.000000', '2', 8),
(22, '2020-01-13 20:20:00.000000', '3', 8),
(23, '2020-01-12 20:20:00.000000', '3', 9),
(24, '2020-01-11 23:00:00.000000', '2', 9);

-- --------------------------------------------------------

--
-- 資料表結構 `tickets`
--

CREATE TABLE `tickets` (
  `ticket_id` int(11) NOT NULL,
  `ticket_type` varchar(30) DEFAULT NULL,
  `ticket_price` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT 1,
  `ticket_order_id` int(11) DEFAULT NULL,
  `ticket_seat_id` varchar(30) DEFAULT NULL,
  `ticket_session_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 傾印資料表的資料 `tickets`
--

INSERT INTO `tickets` (`ticket_id`, `ticket_type`, `ticket_price`, `quantity`, `ticket_order_id`, `ticket_seat_id`, `ticket_session_id`) VALUES
(1, '學生票', 150, 1, 1, '1-1', 1),
(2, '成人票', 250, 1, 1, '2-1', 1),
(3, '學生票', 150, 1, 2, '3-3', 1),
(4, '學生票', 150, 1, 1, '1-1', 2),
(5, '學生票', 150, 1, 1, '2-1', 2),
(24, '普通票', 250, 1, 10, '3-1', 2),
(25, '學生票', 200, 1, 10, '4-1', 2),
(26, '學生票', 200, 1, 10, '5-1', 2),
(27, '學生票', 200, 1, 33, '3-1', 2),
(28, '學生票', 200, 1, 34, '4-1', 2),
(29, '學生票', 200, 1, 34, '5-1', 2),
(30, '愛心票', 150, 1, 34, '6-1', 2),
(36, '愛心票', 150, 1, 36, '1-7', 2),
(37, '愛心票', 150, 1, 36, '1-8', 2),
(38, '普通票', 250, 1, 36, '1-9', 2),
(39, '愛心票', 150, 1, 0, '8-5', 2),
(40, '學生票', 200, 1, 37, '8-6', 2),
(41, '學生票', 200, 1, 37, '8-7', 2),
(42, '學生票', 200, 1, 38, '9-5', 2),
(43, '愛心票', 150, 1, 38, '9-6', 2),
(44, '愛心票', 150, 1, 38, '9-7', 2),
(45, '學生票', 200, 1, 39, '7-5', 2),
(46, '學生票', 200, 1, 39, '7-6', 2),
(47, '學生票', 200, 1, 39, '7-7', 2),
(48, '愛心票', 150, 1, 40, '6-5', 2),
(74, '愛心票', 150, 1, 48, '6-4', 3),
(75, '學生票', 200, 1, 48, '6-5', 3),
(76, '普通票', 250, 1, 49, '8-4', 3),
(77, '愛心票', 150, 1, 49, '8-5', 3),
(78, '普通票', 250, 1, 49, '8-6', 3),
(79, '愛心票', 150, 1, 0, '4-5', 4),
(80, '普通票', 250, 1, 50, '4-6', 4),
(81, '普通票', 250, 1, 50, '4-7', 4),
(86, '普通票', 250, 1, 52, '1-6', 4),
(87, '學生票', 200, 1, 52, '1-7', 4),
(88, '學生票', 200, 1, 53, '1-5', 3),
(89, '學生票', 200, 1, 53, '1-6', 3),
(90, '學生票', 200, 1, 53, '1-7', 3),
(91, '學生票', 200, 1, 54, '3-5', 3),
(92, '學生票', 200, 1, 54, '4-4', 3),
(93, '學生票', 200, 1, 54, '4-5', 3),
(94, '學生票', 200, 1, 54, '4-6', 3),
(95, '愛心票', 150, 1, 55, '2-4', 3),
(96, '愛心票', 150, 1, 55, '2-5', 3),
(97, '學生票', 200, 1, 55, '2-6', 3),
(98, '學生票', 200, 1, 55, '2-7', 3),
(99, '學生票', 200, 1, 56, '3-5', 4),
(100, '普通票', 250, 1, 56, '3-6', 4),
(101, '學生票', 200, 1, 56, '3-7', 4),
(102, '學生票', 200, 1, 56, '6-5', 4),
(103, '學生票', 200, 1, 57, '3-6', 3),
(104, '學生票', 200, 1, 57, '3-7', 3),
(105, '愛心票', 150, 1, 57, '4-7', 3),
(106, '普通票', 250, 1, 57, '5-7', 3),
(107, '普通票', 250, 1, 57, '6-6', 3),
(108, '愛心票', 150, 1, 58, '9-4', 3),
(109, '普通票', 250, 1, 58, '9-5', 3),
(110, '學生票', 200, 1, 58, '9-6', 3),
(111, '學生票', 200, 1, 58, '9-7', 3),
(112, '愛心票', 150, 1, 59, '10-7', 2),
(113, '學生票', 200, 1, 59, '5-7', 2),
(116, '愛心票', 150, 1, 61, '6-5', 10),
(117, '學生票', 200, 1, 61, '6-6', 10),
(120, '普通票', 250, 1, 63, '5-3', 2),
(121, '學生票', 200, 1, 63, '5-4', 2),
(122, '愛心票', 150, 1, 64, '5-5', 2),
(123, '學生票', 200, 1, 64, '5-6', 2),
(124, '學生票', 200, 1, 65, '7-1', 15),
(125, '普通票', 250, 1, 65, '7-2', 15),
(126, '普通票', 250, 1, 65, '7-3', 15),
(127, '學生票', 200, 1, 66, '5-5', 18),
(128, '學生票', 200, 1, 66, '5-6', 18),
(129, '普通票', 250, 1, 67, '5-4', 22),
(130, '普通票', 250, 1, 67, '5-5', 22),
(131, '學生票', 200, 1, 67, '5-6', 22),
(132, '學生票', 200, 1, 67, '5-7', 22),
(133, '學生票', 200, 1, 67, '5-8', 22),
(134, '愛心票', 150, 1, 68, '3-4', 21),
(135, '學生票', 200, 1, 68, '3-5', 21),
(136, '學生票', 200, 1, 68, '3-6', 21),
(137, '愛心票', 150, 1, 69, '4-6', 13),
(138, '愛心票', 150, 1, 69, '4-7', 13),
(139, '學生票', 200, 1, 69, '4-8', 13),
(140, '學生票', 200, 1, 69, '5-7', 13),
(141, '學生票', 200, 1, 69, '5-8', 13),
(142, '愛心票', 150, 1, 70, '1-1', 23),
(143, '普通票', 250, 1, 70, '1-2', 23),
(144, '學生票', 200, 1, 70, '1-3', 23),
(145, '學生票', 200, 1, 70, '1-4', 23),
(146, '學生票', 200, 1, 71, '4-4', 21),
(147, '學生票', 200, 1, 71, '4-5', 21),
(148, '學生票', 200, 1, 71, '4-6', 21),
(149, '學生票', 200, 1, 72, '10-5', 20),
(150, '學生票', 200, 1, 72, '10-6', 20),
(151, '學生票', 200, 1, 73, '10-7', 20),
(152, '普通票', 250, 1, 73, '10-8', 20),
(153, '學生票', 200, 1, 74, '4-1', 12),
(154, '學生票', 200, 1, 74, '4-2', 12),
(155, '學生票', 200, 1, 74, '4-3', 12),
(156, '學生票', 200, 1, 74, '4-4', 12),
(157, '學生票', 200, 1, 75, '3-10', 19),
(158, '學生票', 200, 1, 75, '3-6', 19),
(159, '普通票', 250, 1, 75, '3-7', 19),
(160, '學生票', 200, 1, 75, '3-8', 19),
(161, '愛心票', 150, 1, 75, '3-9', 19);

--
-- 已傾印資料表的索引
--

--
-- 資料表索引 `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`member_id`);

--
-- 資料表索引 `movie`
--
ALTER TABLE `movie`
  ADD PRIMARY KEY (`movie_id`);

--
-- 資料表索引 `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`order_id`);

--
-- 資料表索引 `session`
--
ALTER TABLE `session`
  ADD PRIMARY KEY (`session_id`);

--
-- 資料表索引 `tickets`
--
ALTER TABLE `tickets`
  ADD PRIMARY KEY (`ticket_id`);

--
-- 在傾印的資料表使用自動遞增(AUTO_INCREMENT)
--

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `member`
--
ALTER TABLE `member`
  MODIFY `member_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `movie`
--
ALTER TABLE `movie`
  MODIFY `movie_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `orders`
--
ALTER TABLE `orders`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=76;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `session`
--
ALTER TABLE `session`
  MODIFY `session_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `tickets`
--
ALTER TABLE `tickets`
  MODIFY `ticket_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=162;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
