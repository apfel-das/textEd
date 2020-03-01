package test;

public class Line{

	private int lNum;
	private String context;
	
	public Line(int n, String info) {
		this.lNum=n;
		this.context=info;
	}

	public int getLNum() {
		return lNum;
	}

	public void setLNum(int n) {
		this.lNum = n;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String c) {
		this.context = c;
	}
	
	public String toString(){
		return this.lNum+") "+ this.context;
	}
	
	public void print(){
		System.out.println(this.toString());
	}

}
