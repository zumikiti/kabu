package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class insertRank {
	ArrayList<Items> list = new ArrayList<Items>();

	public <T> ArrayList<Items> test(String a) {
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
					
					//６結果を表示する
					if (a.equals("r")){
						rs = stmt.executeQuery("select * from k_monthly order by roic desc");
					}else{
						rs = stmt.executeQuery("select * ,("
								+ "(SELECT COUNT( * ) FROM k_monthly AS b WHERE a.per > b.per) + "
								+ "(SELECT COUNT( * ) FROM k_monthly AS c WHERE a.pbr > c.pbr)"
								+ ") AS wariyasu from k_monthly as a order by wariyasu") ;
					}
					
//					while (rs.next()) {
//						System.out.println(rs.getString("code") +
//								", " + rs.getString("per") +
//								", " + rs.getString("roic") + 
//								", "+ rs.getString("date"));
//					}
					
					while (rs.next()) {
						
						Items item = new Items();
						item.setCode(rs.getInt("code"));
						//item.setDate(rs.getString("date"));
						
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
	
	public void updateRank(int c, int r, String a){
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		if (a.equals("p")){
			//sql = "insert into k_rank (code,perpbr) values (?, ?)";
			//sql = "update k_rank set perpbr = ? where code=?";
			sql = "update k_monthly set perpbr_r = ? where code=?";
		}else{
			//sql = "insert into k_rank (code,roic) values (?, ?)";
			//sql = "update k_rank set roic = ? where code=?";
			sql = "update k_monthly set roic_r = ? where code=?";
		}
		int code = c;
		int rank = r;
		ResultSet rs = null;
		
		//System.out.println(sql);
		
		//JDBCドライバを読み込む
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			//1 JDBCドライバが見つからない場合、例外
			e.printStackTrace();
		}
		try {
			
			//２ DBへの接続を行う
			con = DriverManager.getConnection("jdbc:mysql://localhost/kabu",
					"admin", "admin");
			
			//３ DB処理のための準備、Statementオブジェクトの作成
			pstmt = con.prepareStatement(sql);
			
			//４ 値をセット
			pstmt.setInt(1, rank);
			pstmt.setInt(2, code);
			
			System.out.println(pstmt.toString());
			
			//5 SQLを実行
			int result = pstmt.executeUpdate();
			//System.out.println(result);
			
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
			if (pstmt != null) {
				try {
					pstmt.close();
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