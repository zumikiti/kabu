package makeMonttly;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import sql.*;

public class makeMonthly {
	
	public static void main(String[] args) {
		//k_dbからk_monthlyにデータをコピー
		copyDB();
		System.out.println("マンスリーの初期化完了。");
		
		//yahooのurlから各銘柄の前日価格、per,pbr,利回りをスクレイピングで取得。
		System.out.println("getDB実行");
		
		getDB gdb = new getDB();
		ArrayList<Items> list = gdb.test();
		
		System.out.println("k_dbからURLを取得。");
		
		for(Items i : list){
			System.out.println("スクレイピング開始。");
			String l = i.getLink();
			int c = i.getCode();
			
			screiping sc = new screiping();
			sc.screiping(c,l);
		}
		
		System.out.println("/////////スクレイピング完了///////////////");
		
		//空白がある場合は、手動で入力。
		
		//k_monthlyに登録。
		
		//roicを見直す
		
		//makeRank.javaを呼び出し実行。
		
		//k_dateに登録するか確認。
	}

	private static void copyDB() {
		// TODO Auto-generated method stub
		Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        // JDBCドライバの読み込み
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
        // JDBCドライバが見つからない場合、例外
            e.printStackTrace();
        }
        try {
            // DBへの接続を行う
            con = DriverManager.getConnection("jdbc:mysql://localhost/kabu",
					"admin", "admin");// "password"の部分は，ご自身でrootユーザーに設定したものを記載してください。
            // DB処理のための準備、Statementオブジェクトの作成
            stmt = con.createStatement();
            // Select文の実行と結果を格納／代入
            rs = stmt.executeQuery("select * from k_db limit 50");
            // 結果を表示する
            while (rs.next()) {
                System.out.println(rs.getString("name"));
                System.out.println(rs.getInt("code"));
            }

            // データの削除　参照：https://www.dbonline.jp/mysql/insert/index12.html
            stmt.executeUpdate("delete from k_monthly");
            
            //k_dbの内容をコピー
            stmt.executeUpdate("insert into k_monthly(code, roic) select code, roic from k_db");
            
            //k_monthlyに今日の日付をセット。 動的にやらないとだめ？
            //stmt.executeUpdate("update k_monthly set date=" + LocalDate.now() + "where date = null");
            
            

        } catch (SQLException e) {
            // DBとの処理で何らかのエラーがあった場合の例外
            e.printStackTrace();

        } finally { // 以降は何があっても接続を切断する
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
	}
}
