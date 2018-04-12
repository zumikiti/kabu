package makeMonttly;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class screiping {
	
	public void screiping(int code, String link) {
		// TODO Auto-generated method stub
		
		String url = link;
		
		try {
			Document doc = Jsoup.connect(url).get();
			Elements ele = doc.select("dd.mar0 strong");
			
			System.out.println(url);
			//System.out.println("前日株価");
			float price = chengeFloat(ele.get(0).text());
			//System.out.println("利回り");
			float rimawari = chengeFloat(ele.get(9).text());
			//System.out.println("PER");
			float per = chengeFloat(ele.get(11).text());
			//System.out.println("PBR");
			float pbr = chengeFloat(ele.get(12).text());
			
			/*if( code == 6995 ){
				throw new IOException();
			}*/
			System.out.println("コード：" + code + ", "
					+ "前日株価：" + price + ", "
					+ "配当利回り：" + rimawari + ", "
					+ "PER：" + per + ", "
					+ "PBR：" + pbr);
			
			//k_monthlyに登録する。
			updateMonthly(code,price,per,pbr, rimawari);
			
			//System.out.println("i live");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error");
		}
		

	}

	private float chengeFloat(String text) {
		// TODO Auto-generated method stub
		
		float f = 0f;
		
		System.out.println("「 "+ text+ " 」をfloatに痴漢したい！");
		
		try{
			//正規表現で数字以外を排除　https://www.javadrive.jp/regex/replace/index2.html
			//https://qiita.com/themoon/items/7c8811b5cf37d700adc4
			text = text.replaceAll("(.(連|単).)|,", "");
			System.out.println(text);
			//型変換　http://java-reference.com/java_string_tonumber.html　参照。
			f = Float.parseFloat(text);
		} catch (Exception e) {
			System.out.println("値エラー");
			f = scanner(text);
		}
		return f;
	}


	private float scanner(String text) {
		// TODO Auto-generated method stub
		
		float i;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("スクレイピングできなかったので手入力！");
		
		while(true){
			if(sc.hasNext()){
				i = sc.nextFloat();
				break;
			} else {
				System.out.println("数値を入れろ！");
				sc.next();
			}
		}
		return i;
	}

	private void updateMonthly(int Code, float Price, float Per, float Pbr, float Yield) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "update k_monthly set price=?, per=?,  pbr=?, yield=? where code=?";
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
			//price=?, per=?,  pbr=?, yield=? where code=?
			pstmt.setFloat(1, Price);
			pstmt.setFloat(2, Per);
			pstmt.setFloat(3, Pbr);
			pstmt.setFloat(4, Yield);
			pstmt.setInt(5, Code);
			
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
