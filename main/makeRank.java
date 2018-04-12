///////////////////////
//1.SQLのk_monthlyに当月分をひとまず置く
//2.k_monthluをperpbrとroicをソーロしたのを取得して、ランキング番号つけて、k_rankに返す。
//
//ひとまず、makeRank.javaでinserRank.javaのArrrayList<Items>でそーとしたものを取得。
//そんで、標準forでランキングを取得して、inserRank.java内のupdateRankメソッドに1つずつ渡して登録させる。
//roicとperpbrの仕分けは引数にp/rを渡すことでinnsertRankで判別させる。
///////////////////////

package main;

import java.util.ArrayList;
import sql.*;

public class makeRank {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		if(args[0].length() > 0){
//			D = args[0];
//		}
		
		
		// 1 sqlに接続。remind_dayを取得。 <<不要>>
//		getDate gdt = new getDate();
//		ArrayList<Items> dlist = gdt.test();
//		
//		LocalDate d = dlist.get(0).getDate();
//		
//		System.out.println(d);
		
//		for(Items b : dlist){
//			d = b.getDate();
//			//System.out.println(d);
//		}
		
		//PER+PBRかROICかを判別する値を格納
		String[] pr = {"p","r"};
		
		//配列を順に実行
		System.out.println("配列順に実行");
		for(String j: pr){
			
			//メソッドを実行して、Itemsにk_monthlyの値を格納
			System.out.println("メソッドを実行して、Itemsにk_monthlyの値を格納");
			insertRank ir = new insertRank();
			ArrayList<Items> list = ir.test(j);
			int r = 0; //ランキング用の値
			
			//Itemsに格納した内容を順に呼び出す。
			System.out.println("Itemsに格納した内容を順に呼び出す。");
			for(int i=0; i < list.size(); i++){
				int code = list.get(i).getCode();
				//r = r+1; 
				r++; //r値を実行順に足していく。
				System.out.println("コード：" + code + ", ランキング：" + r);
				
				//codeと判別値jとr値をランキングとしてメソッドに返す。
				ir.updateRank(code, r, j);
			}
		}
		
		
	}

}
