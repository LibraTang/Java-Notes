package display.led;

public class Display {
	private int value = 0;
	private int limit = 0;
	private static int step = 1;
	
	//¹¹Ôìº¯Êı
	public Display(int limit) {
		this.limit = limit;
	}
	
	public void increase() {
		value++;
		if(value == limit) {
			value = 0;
		}
	}
	
	public int getValue() {
		return value;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Display d1 = new Display(10);
		Display d2 = new Display(20);
		System.out.println(d1.step);
		System.out.println(d2.step);
		d1.step = 2;
		System.out.println(d1.step);
		System.out.println(d2.step);
		Display.step = 3;
		System.out.println(d1.step);
		System.out.println(d2.step);
	}

}
