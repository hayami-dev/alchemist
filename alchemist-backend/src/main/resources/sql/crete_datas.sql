-- =========================================================
-- 錬金ゲームシステム マスターデータ INSERT文
-- 生成元: _RfD_マスターデータ定義書.xlsx
-- 対象DB: MySQL
-- =========================================================

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ---------------------------------------------------------
-- 01. accounts (アカウントマスタ) ※サンプルデータのみ
-- ---------------------------------------------------------
INSERT INTO `accounts` (`id`, `login_token`, `password_hash`, `bgm_volume`, `se_volume`) VALUES
('acc-test-uuid-001', 'test_token_123456789', '$2a$10$X87gYv9mRre9E6Kghp7i8ux/0u1a0sL7G3bM3k9v/r9gL4eC3yYV.', 65, 50);

-- ---------------------------------------------------------
-- 02. players (プレイヤーデータ) ※サンプルデータのみ
-- ---------------------------------------------------------
INSERT INTO `players` (`id`, `name`, `money`, `progressRate`) VALUES
(1, 'アルケミスト', 1500, 25);

-- ---------------------------------------------------------
-- 03. rarities (レアリティマスタ) ※本番データ
-- ---------------------------------------------------------
INSERT INTO `rarities` (`id`, `rabel`, `drop_rate`) VALUES
(1, 'COMMON', 70),
(2, 'RARE', 25),
(3, 'EPIC', 5),
(4, 'FANTASIA', 0),
(5, 'STIGMA', 0);

-- ---------------------------------------------------------
-- 04. calculations (計算方法マスタ) ※本番データ
-- ---------------------------------------------------------
INSERT INTO `calculations` (`id`, `type`) VALUES
(1, 'ADD'),
(2, 'MULTIPLY'),
(3, 'MODULO');

-- ---------------------------------------------------------
-- 05. items (アイテムマスタ) ※本番データ 全73件
-- TODO: アイテム名見直し
-- ---------------------------------------------------------
INSERT INTO `items` (`id`, `name`, `itemType`, `rarities_id`, `star_rank`, `description`, `buy_price`, `sell_price`, `can_sell`, `value`, `efficiency`) VALUES
(1, 'シープイヤー', 'MATERIAL', 1, 1, '煎じて飲むとスッと寝付ける。これを飲んでから寝よう。', 50, 12, 1, 8, NULL),
(2, 'シープイヤーの蒸留水', 'MATERIAL', 1, 1, NULL, 75, 18, 1, 16, NULL),
(3, 'まどろみの香り', 'MATERIAL', 1, 2, NULL, 100, 25, 1, 26, NULL),
(4, 'まどろみの花弁', 'MATERIAL', 1, 2, NULL, 125, 31, 1, 38, NULL),
(5, 'うたたねの夢', 'MATERIAL', 1, 3, NULL, 200, 60, 1, 46, NULL),
(6, 'うたたねの残滓', 'MATERIAL', 1, 3, NULL, 225, 67, 1, 58, NULL),
(7, '睡蓮のゆりかごの夢', 'MATERIAL', 1, 4, NULL, 500, 150, 1, 68, NULL),
(8, '71～80', 'MATERIAL', 1, 4, NULL, 550, 165, 1, 76, NULL),
(9, 'さんざめく生命の夢', 'MATERIAL', 1, 5, NULL, 600, 180, 1, 88, NULL),
(10, '世界樹の鼓動の夢', 'MATERIAL', 1, 5, NULL, 650, 162, 1, 94, NULL),
(11, '安らかな灯火', 'MATERIAL', 2, 1, NULL, 60, 15, 1, 9, NULL),
(12, '誘いの灯火', 'MATERIAL', 2, 1, NULL, 85, 21, 1, 18, NULL),
(13, '導きのオーブ', 'MATERIAL', 2, 2, NULL, 150, 37, 1, 27, NULL),
(14, '願いのオーブ', 'MATERIAL', 2, 2, NULL, 185, 46, 1, 39, NULL),
(15, '蜃気楼の夢', 'MATERIAL', 2, 3, NULL, 600, 180, 1, 48, NULL),
(16, '蜃気楼の篝火', 'MATERIAL', 2, 3, NULL, 650, 195, 1, 57, NULL),
(17, '魔法のランプの夢', 'MATERIAL', 2, 4, NULL, 700, 210, 1, 69, NULL),
(18, 'ランプの灯影', 'MATERIAL', 2, 4, NULL, 750, 225, 1, 78, NULL),
(19, '悠久の琥珀の夢', 'MATERIAL', 2, 5, NULL, 800, 240, 1, 87, NULL),
(20, '忘れられた王国の夢', 'MATERIAL', 2, 5, NULL, 850, 212, 1, 99, NULL),
(21, 'ムーンドロップ', 'MATERIAL', 3, 1, '青くて冷たい、不思議な弾力を持つ液体。調合のベースによく使われる。', 70, 17, 1, 10, NULL),
(22, 'ムーンドロップの燐光', 'MATERIAL', 3, 1, NULL, 100, 25, 1, 20, NULL),
(23, '月明りの雫', 'MATERIAL', 3, 2, NULL, 200, 50, 1, 30, NULL),
(24, '月明りの結晶', 'MATERIAL', 3, 2, NULL, 250, 62, 1, 40, NULL),
(25, '月兎の夢', 'MATERIAL', 3, 3, NULL, 800, 240, 1, 50, NULL),
(26, '月兎の綿毛', 'MATERIAL', 3, 3, NULL, 850, 255, 1, 60, NULL),
(27, '不思議の国の夢', 'MATERIAL', 3, 4, NULL, 1000, 300, 1, 65, NULL),
(28, '71～80', 'MATERIAL', 3, 4, NULL, 1200, 360, 1, 80, NULL),
(29, '81～90', 'MATERIAL', 3, 5, NULL, 1500, 450, 1, 90, NULL),
(30, '91～100', 'MATERIAL', 3, 5, NULL, 2000, 500, 1, 100, NULL),
(31, '宇宙卵の欠片', 'LOOK_ITEM', 4, 5, NULL, 9999, 4999, 1, 0, NULL),
(32, '賢者の石', 'LOOK_ITEM', 4, 5, '赤く怪しく輝く、錬金術の到達点。世界の理を書き換える力を持つ。', 9999, 4999, 1, 1, NULL),
(33, 'ネムリギの樹皮', 'MATERIAL', 4, 5, NULL, 8000, 4000, 1, 11, NULL),
(34, 'ドラウムクヴェーデの詩片', 'MATERIAL', 4, 5, NULL, 8000, 4000, 1, 13, NULL),
(35, 'オーディンの隻眼', 'MATERIAL', 4, 5, NULL, 8000, 4000, 1, 17, NULL),
(36, 'ザントマンの砂', 'MATERIAL', 4, 5, NULL, 8000, 4000, 1, 19, NULL),
(37, 'オネイロスの群れ', 'MATERIAL', 4, 5, NULL, 8000, 4000, 1, 23, NULL),
(38, 'モルペウスの羽根', 'MATERIAL', 4, 5, NULL, 8000, 4000, 1, 29, NULL),
(39, 'レテの水滴', 'MATERIAL', 4, 5, NULL, 8000, 4000, 1, 31, NULL),
(40, 'ニュクスの帳', 'MATERIAL', 4, 5, NULL, 8000, 4000, 1, 37, NULL),
(41, 'エンデュミオンの永眠', 'MATERIAL', 4, 5, NULL, 8000, 4000, 1, 41, NULL),
(42, 'パシテアの寵愛', 'MATERIAL', 4, 5, NULL, 8000, 4000, 1, 43, NULL),
(43, 'ヒュプノスの蜜', 'MATERIAL', 4, 5, NULL, 8000, 4000, 1, 47, NULL),
(44, '胡蝶の鱗粉', 'MATERIAL', 4, 5, NULL, 8000, 4000, 1, 53, NULL),
(45, '邯鄲の枕', 'MATERIAL', 4, 5, NULL, 8000, 4000, 1, 59, NULL),
(46, '南柯の蟻穴', 'MATERIAL', 4, 5, NULL, 8000, 4000, 1, 61, NULL),
(47, '黄粱の一炊', 'MATERIAL', 4, 5, NULL, 8000, 4000, 1, 67, NULL),
(48, '獏の香', 'MATERIAL', 4, 5, NULL, 8000, 4000, 1, 71, NULL),
(49, '夢殿の燭火', 'MATERIAL', 4, 5, NULL, 8000, 4000, 1, 73, NULL),
(50, 'オーレ＝ルゴイエのまなざし', 'MATERIAL', 4, 5, NULL, 8000, 4000, 1, 79, NULL),
(51, 'リップ・ヴァン・ウィンクルの一滴', 'MATERIAL', 4, 5, NULL, 8000, 4000, 1, 83, NULL),
(52, 'セイレーンの子守唄', 'MATERIAL', 4, 5, NULL, 8000, 4000, 1, 89, NULL),
(53, 'ブーシュヤンスターの涙', 'MATERIAL', 4, 5, NULL, 8000, 4000, 1, 97, NULL),
(54, '孤独の断片', 'LOOK_ITEM', 5, 1, NULL, 0, 0, 0, 7, NULL),
(55, '憤怒の悪夢', 'LOOK_ITEM', 5, 1, NULL, 0, 0, 0, 14, NULL),
(56, '悲哀の断片', 'LOOK_ITEM', 5, 2, NULL, 0, 0, 0, 21, NULL),
(57, '強欲の悪夢', 'LOOK_ITEM', 5, 2, NULL, 0, 0, 0, 28, NULL),
(58, '水銀', 'LOOK_ITEM', 5, 2, NULL, 0, 0, 0, 35, NULL),
(59, '怠惰の悪夢', 'LOOK_ITEM', 5, 3, NULL, 0, 0, 0, 42, NULL),
(60, '破壊の断片', 'LOOK_ITEM', 5, 3, NULL, 0, 0, 0, 49, NULL),
(61, '暴食の悪夢', 'LOOK_ITEM', 5, 3, NULL, 0, 0, 0, 56, NULL),
(62, '忘却の煤', 'LOOK_ITEM', 5, 4, NULL, 0, 0, 0, 63, NULL),
(63, '傲慢の悪夢', 'LOOK_ITEM', 5, 4, NULL, 0, 0, 0, 70, NULL),
(64, '夢遊病の断片', 'LOOK_ITEM', 5, 4, NULL, 0, 0, 0, 77, NULL),
(65, '嫉妬の悪夢', 'LOOK_ITEM', 5, 5, NULL, 0, 0, 0, 84, NULL),
(66, '最果ての断片', 'LOOK_ITEM', 5, 5, NULL, 0, 0, 0, 91, NULL),
(67, '色欲の悪夢', 'LOOK_ITEM', 5, 5, NULL, 0, 0, 0, 98, NULL),
(68, '黒こげの何か', 'LOOK_ITEM', 1, 1, NULL, 1, 1, 0, NULL, NULL); -- can_sellはTRUE

INSERT INTO `items` (`id`, `name`, `itemType`, `rarities_id`, `star_rank`, `description`, `buy_price`, `sell_price`, `can_sell`, `value`, `efficiency`,`tool_effect_type`) VALUES
(69, '古ぼけた錬金鍋', 'TOOL', NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 'UNLOCK_ADD'),
(70, '清涼のふいご', 'TOOL', NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 'UNLOCK_MULTIPLY'),
(71, '蒸留器', 'TOOL', NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 'UNLOCK_MODULO'),
(72, 'ハサミ', 'TOOL', NULL, NULL, '1段階前のアイテムに変化させる', 800, 200, 1, NULL, -10, NULL),
(73, '星屑の聖水', 'TOOL', NULL, NULL, '7の倍数のアイテムを-1して救済する', 1000, 250, 1, NULL, -1, NULL);

-- ---------------------------------------------------------
-- 06. diaries (日記マスタ) ※サンプルデータのみ(10件)
-- ---------------------------------------------------------
INSERT INTO `diaries` (`id`, `text`) VALUES
(1, 'サンプルテキスト①'),
(2, 'サンプルテキスト②'),
(3, 'サンプルテキスト③'),
(4, 'サンプルテキスト④'),
(5, 'サンプルテキスト⑤'),
(6, 'サンプルテキスト⑥'),
(7, 'サンプルテキスト⑦'),
(8, 'サンプルテキスト⑧'),
(9, 'サンプルテキスト⑨'),
(10, 'サンプルテキスト⑩');

-- ---------------------------------------------------------
-- 07. inventories (インベントリ) ※サンプルデータのみ
-- ---------------------------------------------------------
INSERT INTO `inventories` (`id`, `player_id`, `item_id`, `quantity`) VALUES
(1, 1, 1, 10),
(2, 1, 2, 5),
(3, 1, 3, 3),
(4, 1, 5, 1);

INSERT INTO inventories (player_id, item_id, quantity) VALUES
(1, 69, 1),
(1, 70, 1),
(1, 71, 1);

-- ---------------------------------------------------------
-- 08. recipes (レシピ) ※サンプルデータのみ
-- ---------------------------------------------------------
INSERT INTO `recipes` (`id`, `item_id`) VALUES
(1, 5);

-- ---------------------------------------------------------
-- 09. recipe_details (レシピ詳細) ※サンプルデータのみ
-- ---------------------------------------------------------
INSERT INTO `recipe_details` (`id`, `recipes_id`, `step_order`, `item_id`, `calculations_type`) VALUES
(1, 1, 1, 1, 'ADD'),
(2, 1, 2, 2, 'MULTIPLY'),
(3, 1, 3, 3, 'MODULO');

-- ---------------------------------------------------------
-- 10. catalogs (カタログ) ※サンプルデータのみ
-- ---------------------------------------------------------
INSERT INTO `catalogs` (`id`, `player_id`, `item_id`, `recipe_id`, `memo`, `unlocked_at`) VALUES
(1, 1, 1, NULL, '近くの森の川辺で採取。いくらでも拾える。', '2026-07-14 15:00:00'),
(2, 1, 2, NULL, 'ちょっと魔力のある泉から汲んできた。冷たい！', '2026-07-14 15:05:00'),
(3, 1, 5, 1, '初めて調合に成功したお薬！ちょっと苦い。', '2026-07-14 15:10:00');

-- ---------------------------------------------------------
-- 11. unlocked_diaries (日記解放ログ) ※サンプルデータのみ
-- ---------------------------------------------------------
INSERT INTO `unlocked_diaries` (`id`, `player_id`, `diary_id`, `unlocked_at`) VALUES
(1, 1, 1, '2026-07-14 15:10:00');

SET FOREIGN_KEY_CHECKS = 1;

-- 手動でInventoriesとcatalogsを一致させる。
INSERT INTO catalogs (player_id, item_id, unlocked_at)
SELECT player_id, item_id, NOW()
FROM inventories
WHERE NOT EXISTS (
  SELECT 1 FROM catalogs
  WHERE catalogs.player_id = inventories.player_id
    AND catalogs.item_id = inventories.item_id
);
