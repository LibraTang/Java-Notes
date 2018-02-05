package dome;

public class DVD extends Item {
	private String director;
	
	public DVD(String title, String director, int playingTime, String comment) {
		super(title,playingTime,false,comment);
		setTitle("b");
		this.director = director;
	}

	public static void main(String[] args) {
		DVD dvd = new DVD("a","b",1,"...");
		dvd.print();
	}

	public void print() {
		System.out.print("DVD:");
		super.print();
		System.out.println(":"+director);
	}
}
