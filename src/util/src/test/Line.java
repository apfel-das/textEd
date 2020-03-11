package test;

public class Line{

	private String context;
	
	public Line(String info) 
	{
		this.context=info;
	}


	public String getContext() {
		return context;
	}

	public void setContext(String c) {
		this.context = c;
	}
	
	public String toString(){
		return this.context;
		
	}

	
	public void print()
	{
		System.out.println(this.toString());
	}
	

}
