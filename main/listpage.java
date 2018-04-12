package main;

import java.time.LocalDate;
import java.util.ArrayList;

import sql.*;

public class listpage {
	
	ArrayList<Items> per = new ArrayList<Items>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		if(args[0].length() > 0){
//			D = args[0];
//		}
		
		
		// 1 sqlに接続。remind_dayを取得。
		getDate gdt = new getDate();
		ArrayList<Items> dlist = gdt.test();
		
		LocalDate d = dlist.get(0).getDate();
		
		System.out.println(d);
		
//		for(Items b : dlist){
//			d = b.getDate();
//			//System.out.println(d);
//		}
		
		getDB gdb = new getDB();
		ArrayList<Items> list = gdb.test();
		
		for(Items a : list){
			LocalDate ld = a.getDate();
			
//			int b = ld.compareTo(today);
			//System.out.println(ld);
			
			if(ld.equals(d)){
				int code = a.getCode();
				String name = a.getName();
				float price = a.getPrice();
				float roic = a.getRoic();
				String link = a.getLink();
				System.out.println(code + ", " + name + ", " + price + ", " + roic + ", " + link);
				
			}
		}
	}

}
