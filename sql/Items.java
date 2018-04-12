package sql;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Items {
	private int Code;
	private String Name, Type, Market;
	private float jikosihon, jika;
	private int yield_m, yutai_m;
	private String yutai, link;
	private float price, per, pbr, roic, yield;
	private LocalDate date;
	private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private ResultSet rs;
	
	public int getCode(){
		return Code;
	}
	
	public void setCode(int code){
//		System.out.println("setItemName");
		this.Code = code;
	}
	
	public String getMarket(){
		return Market;
	}
	
	public void setMarket(String market){
//		System.out.println("setItemName");
		this.Market = market;
	}
	
	public float getJikosihon(){
		return jikosihon;
	}
	
	public void setJikosihon(float jikosihon){
//		System.out.println("setItemName");
		this.jikosihon = jikosihon;
	}
	
	public float getJika(){
		return jika;
	}
	
	public void setJika(float jika){
//		System.out.println("setItemName");
		this.jika = jika;
	}
	
	public int getYield_m(){
		return yield_m;
	}
	
	public void setYield_m(char yield_m){
//		System.out.println("setItemName");
		this.yield_m = yield_m;
	}
	
	public int getYutai_m(){
		return yutai_m;
	}
	
	public void setYutai_m(char yutai_m){
//		System.out.println("setItemName");
		this.yutai_m = yutai_m;
	}
	
	public String getYutai(){
		return yutai;
	}
	
	public void setYutai(String Yutai){
//		System.out.println("setItemName");
		this.yutai = Yutai;
	}
	
	public float getPrice(){
		return price;
	}
	
	public void setPrice(float Price){
//		System.out.println("setItemName");
		this.price = Price;
	}
	
	public float getPer(){
		return per;
	}
	
	public void setPer(float Per){
//		System.out.println("setItemName");
		this.per = Per;
	}
	
	public float getPbr(){
		return pbr;
	}
	
	public void setPbr(float pbr){
//		System.out.println("setItemName");
		this.pbr = pbr;
	}
	
	public float getRoic(){
		return roic;
	}
	
	public void setRoic(float Roic){
//		System.out.println("setItemName");
		this.roic = Roic;
	}
	
	public float getYield(){
		return yield;
	}
	
	public void setYield(float Yield){
//		System.out.println("setItemName");
		this.yield = Yield;
	}
	
	//コントラクタ
	Items(){
	}
	
	Items(int code, String Name, String type, String market, 
			float jikosihon, float jika, String Link, String date, 
			int yield_m, int yutai_m, float price, float per, 
			float pbr, float roic, float yield){
		System.out.println("初期化時に引数ありコンストラクタが呼ばれました");
		this.Code = code;
		this.Name = Name;
		this.Type = type;
		this.Market = market;
		this.link = Link;
		this.date = LocalDate.parse(date);
	}
	
/*	public ResultSet getRs(){
		System.out.println("Rsをゲットトできた！");
		return rs;
	}
	
	public void setRs(ResultSet rs){
		this.rs = rs;
		System.out.println("Rsをセットできた！");
	}*/
	
	
	
	public String getName(){
		return Name;
	}
	
	public void setName(String Name){
//		System.out.println("setItemName");
		this.Name = Name;
	}
	
	public String getType(){
		return Type;
	}
	
	public void setType(String type){
//		System.out.println("setCategoryName");
		this.Type = type;
	}
	
	public String getLink(){
		return link;
	}
	
	public void setLink(String link){
//		System.out.println("setAmazonLink");
		String ylink = "https://stocks.finance.yahoo.co.jp/stocks/detail/?code=" + link;
		this.link = ylink;
	}
	
	public LocalDate getDate(){
		return date;
	}
	
	public void setDate(String date){
		this.date = LocalDate.parse(date, dtf);
//		System.out.println("setRemindDay");
	}

}
