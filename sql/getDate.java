package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class getDate {
	
	ArrayList<Items> list = new ArrayList<Items>();

	public <T> ArrayList<Items> test() {
		// TODO Auto-generated method stub
		
//		list.removeAll(list);
//		System.out.println(list);
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		//JDBCドライバを読み込む
				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e) {
					// １JDBCドライバが見つからない場合、例外
					e.printStackTrace();
				}
				try {
					
					// ２DBへの接続を行う
					con = DriverManager.getConnection("jdbc:mysql://localhost/kabu",
							"admin", "admin");
					
					// ３DB処理のための準備、Statementオブジェクトの作成
					stmt = con.createStatement();
					
					// ４，５Select文の実行と結果を格納／代入
					rs = stmt.executeQuery("select date from k_date "
							+ "group by date order by date desc");
					
					//６結果を表示する
					
					while (rs.next()) {
						
						Items item = new Items();
						item.setDate(rs.getString("date"));
						
						list.add(item);
					}
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
//		System.out.println(list);
		return list;
	}
}
