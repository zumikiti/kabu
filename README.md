# kabu

## 以下用意
### DB名：kubu
- テーブル： k_monthly

| Field    | Type    | Null | Key | Default | Extra |
|:--------:|:--------|------|-----|---------|-------|
| code     | int(11) | YES  |     | NULL    |       |
| price    | text    | YES  |     | NULL    |       |
| per      | float   | YES  |     | NULL    |       |
| pbr      | float   | YES  |     | NULL    |       |
| roic     | float   | YES  |     | NULL    |       |
| yield    | text    | YES  |     | NULL    |       |
| date     | date    | YES  |     | NULL    |       |
| perpbr_r | int(11) | YES  |     | NULL    |       |
| roic_r   | int(11) | YES  |     | NULL    |       |


- テーブル名：k_data

| Field    | Type        | Null | Key | Default    | Extra |
|:--------:|:------------|------|-----|------------|-------|
| code     | smallint(6) | NO   |     | NULL       |       |
| price    | float       | YES  |     | NULL       |       |
| per      | float       | YES  |     | NULL       |       |
| pbr      | float       | YES  |     | NULL       |       |
| roic     | float       | YES  |     | NULL       |       |
| yield    | varchar(5)  | YES  |     | NULL       |       |
| date     | date        | NO   |     | 2017-06-02 |       |
| perpbr_r | int(11)     | YES  |     | NULL       |       |
| roic_r   | int(11)     | YES  |     | NULL       |       |

## 動き
### 1.追加したい銘柄登録
`k_data`へ登録する。

```sql
INSERT INTO k_db(code,name,type,market,roic,jikosihon,jika,yield_m,yutai_m, yutai, yahoo) VALUES(7840,"フランスベッドホールディングス","その他製品","東証１",2.82,60.3,430,"3,9","3", "5000円分の優待商品", "7840.T")
```
今後、javaで入力簡易化予定。。。

### 2.`makeRank.java`実行
k_dataに登録されている銘柄の前日データを、k_monthlyへ登録  
取得に失敗した場合は、入力が求められる。  

### 3.`makeRank.java`を実行
***魔法の公式***記載内容に従い、月間ランキングを計算して`k_monthly`に登録。

### 4.マンスリーをランキングで表示。
```sql
select d.name, m.*, perpbr_r+roic_r as total from k_monthly as m join k_db as d on m.code = d.code order by total limit 20
```

（以下必要に応じて）
### 5.マンスリーをk_dateにインサート
```mysql
INSERT INTO k_date SELECT * FROM k_monthly
```


### k_dbの中から該当データを別テーブルにぬき出す。
```mysql
CREATE TABLE k_date_2017**** LIKE k_date
INSERT INTO k_date_2017**** SELECT * FROM k_date where date="2017-**-**"
```

```
ALTER TABLE k_date_list CHANGE id id int(4) AUTO_INCREMENT
```
