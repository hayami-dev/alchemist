-- =====================================================================
-- 1. テーブルの作成（依存関係の少ないマスタから順に作成）
-- =====================================================================

-- 1. アカウントマスタ (accounts)
CREATE TABLE IF NOT EXISTS accounts (
    id VARCHAR(36) NOT NULL COMMENT 'アカウントID',
    login_token VARCHAR(64) NOT NULL COMMENT 'ログイントークン',
    password_hash VARCHAR(60) NULL COMMENT 'ハッシュ化パスワード',
    bgm_volume INT NOT NULL DEFAULT 50 COMMENT 'ゲーム内BGMボリューム',
    se_volume INT NOT NULL DEFAULT 50 COMMENT 'ゲーム内SEボリューム',
    PRIMARY KEY (id),
    UNIQUE KEY uk_login_token (login_token)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='ユーザーのアカウント認証情報を格納する基本テーブル';

-- 2. レアリティマスタ (rarities)
CREATE TABLE IF NOT EXISTS rarities (
    id INT NOT NULL AUTO_INCREMENT COMMENT 'レアリティID',
    rabel VARCHAR(15) NOT NULL COMMENT 'ラベル',
    drop_rate DOUBLE NOT NULL COMMENT 'ドロップ率',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='アイテムのレアリティを管理';

-- 3. 計算方法マスタ (calculations)
CREATE TABLE IF NOT EXISTS calculations (
    id INT NOT NULL AUTO_INCREMENT COMMENT '計算方法ID',
    type VARCHAR(15) NOT NULL COMMENT '計算方法ラベル',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='レシピの計算方法を管理するテーブル。';

-- 4. 日記マスタ (diaries)
CREATE TABLE IF NOT EXISTS diaries (
    id BIGINT NOT NULL AUTO_INCREMENT COMMENT '日記ID',
    text TEXT NOT NULL COMMENT '画面に表示されるストーリーテキスト',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='カタログ進捗に応じて解放されるストーリーテリング用のテキストマスター';

-- 5. プレイヤーデータ (players)
CREATE TABLE IF NOT EXISTS players (
    id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'プレイヤーID',
    name VARCHAR(20) NOT NULL COMMENT 'ゲーム内で表示される名前',
    money INT NOT NULL DEFAULT 1000 COMMENT 'ショップ購入時に減算、売却時に加算。マイナス不可制約',
    progressRate DOUBLE NOT NULL DEFAULT 0.0 COMMENT 'カタログの解放率',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='アカウントに紐づくゲーム内のプレイヤー情報、資産を管理';

-- 6. アイテムマスタ (items)
CREATE TABLE IF NOT EXISTS items (
    id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'アイテムID',
    name VARCHAR(30) NOT NULL COMMENT '画面表示用（素材名や道具名）',
    itemType VARCHAR(15) NOT NULL COMMENT 'MATERIAL, TOOLなどの種類',
    rarities_id INT NULL COMMENT 'COMMONなどのランク',
    star_rank INT NULL COMMENT '★の数',
    description VARCHAR(255) NULL COMMENT '図鑑や詳細パネルで表示されるテキスト',
    buy_price INT NOT NULL COMMENT 'ショップで購入する際の基本価格',
    sell_price INT NOT NULL COMMENT 'ショップで売却する際の基本価格（初期値）',
    can_sell BOOLEAN NOT NULL DEFAULT TRUE COMMENT '重要アイテムやイベント品を売らせない防衛ロック',
    value INT NULL COMMENT '調合値',
    efficiency INT NULL COMMENT '調合補正値',
    tool_effect_type VARCHAR(20) NULL COMMENT 'TOOLアイテムの効果種別（EFFICIENCY, UNLOCK_MULTIPLY, UNLOCK_MODULOなど）。itemTypeがTOOLの場合のみ使用',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='ゲーム内に存在するすべてのアイテムのマスターデータ。図鑑のベースとなる情報';

-- 7. インベントリ (inventories)
CREATE TABLE IF NOT EXISTS inventories (
    id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'インベントリID',
    player_id BIGINT NOT NULL COMMENT 'players.id への外部キー。誰のカバンか',
    item_id BIGINT NOT NULL COMMENT 'items.id への外部キー。何が入っているか',
    quantity INT NOT NULL DEFAULT 1 COMMENT 'カバンの中の所持数量。0以下になったらサービスでレコード削除',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='プレイヤーの所持品スロットを管理する中間テーブル。数量が0になったら自動削除対象';

-- 8. レシピマスタ (recipes)
CREATE TABLE IF NOT EXISTS recipes (
    id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'レシピID',
    item_id BIGINT NOT NULL COMMENT 'itemテーブルとつなぐ外部キー。',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='アイテムを調合するためのレシピを束ねる中間テーブル。';

-- 9. カタログ (catalogs)
CREATE TABLE IF NOT EXISTS catalogs (
    id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'カタログID',
    player_id BIGINT NOT NULL COMMENT 'players.id への外部キー',
    item_id BIGINT NOT NULL COMMENT 'items.id への外部キー。一度でも手に入れたアイテム',
    recipe_id BIGINT NULL COMMENT '調合レシピの外部キー',
    memo VARCHAR(50) NULL COMMENT 'アイテムに対して残せるメモ',
    unlocked_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '初めて図鑑に載った日時',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='プレイヤーがアイテムをインベントリに入れた瞬間に自動INSERTされる。アイテムログテーブル。';

-- 10. レシピ詳細 (recipe_details)
CREATE TABLE IF NOT EXISTS recipe_details (
    id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'レシピ詳細ID',
    recipes_id BIGINT NOT NULL COMMENT 'recipes.id（中間テーブル）への外部キー。',
    step_order INT NOT NULL DEFAULT 1 COMMENT '調合アルゴリズムの計算順序。1～3まで',
    item_id BIGINT NOT NULL COMMENT '素材となるアイテムIDの外部キー。',
    calculations_type VARCHAR(20) NULL COMMENT 'calculations.typeに対応した外部キー。Enum型に対応（ADD, MULTIPLY, MODULO）',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='錬金調合時の素材組み合わせロジックと、完成するアイテムの関係を定義';

-- 11. 日記解放ログ (unlocked_diaries)
CREATE TABLE IF NOT EXISTS unlocked_diaries (
    id BIGINT NOT NULL AUTO_INCREMENT COMMENT '解放ID',
    player_id BIGINT NOT NULL COMMENT 'players.id への外部キー',
    diary_id BIGINT NOT NULL COMMENT 'diaries.id への外部キー',
    unlocked_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '図鑑進捗率の閾値を超えた瞬間にINSERTされる',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='プレイヤーが実際に読むことのできる日記（ページ）の解放状態を記録する中間テーブル';


-- =====================================================================
-- 2. 外部キー制約（FOREIGN KEY）の追加
-- =====================================================================

-- items から rarities への制約
ALTER TABLE items
    ADD CONSTRAINT fk_items_rarities_id FOREIGN KEY (rarities_id) REFERENCES rarities (id);

-- inventories からの制約
ALTER TABLE inventories
    ADD CONSTRAINT fk_inventories_player_id FOREIGN KEY (player_id) REFERENCES players (id) ON DELETE CASCADE,
    ADD CONSTRAINT fk_inventories_item_id FOREIGN KEY (item_id) REFERENCES items (id);

-- recipes から items への制約
ALTER TABLE recipes
    ADD CONSTRAINT fk_recipes_item_id FOREIGN KEY (item_id) REFERENCES items (id);

-- catalogs からの制約
ALTER TABLE catalogs
    ADD CONSTRAINT fk_catalogs_player_id FOREIGN KEY (player_id) REFERENCES players (id) ON DELETE CASCADE,
    ADD CONSTRAINT fk_catalogs_item_id FOREIGN KEY (item_id) REFERENCES items (id),
    ADD CONSTRAINT fk_catalogs_recipe_id FOREIGN KEY (recipe_id) REFERENCES recipes (id);

-- recipe_details からの制約
ALTER TABLE recipe_details
    ADD CONSTRAINT fk_recipe_details_recipes_id FOREIGN KEY (recipes_id) REFERENCES recipes (id) ON DELETE CASCADE,
    ADD CONSTRAINT fk_recipe_details_item_id FOREIGN KEY (item_id) REFERENCES items (id);

-- unlocked_diaries からの制約
ALTER TABLE unlocked_diaries
    ADD CONSTRAINT fk_unlocked_diaries_player_id FOREIGN KEY (player_id) REFERENCES players (id) ON DELETE CASCADE,
    ADD CONSTRAINT fk_unlocked_diaries_diary_id FOREIGN KEY (diary_id) REFERENCES diaries (id);