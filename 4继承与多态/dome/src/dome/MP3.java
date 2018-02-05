package dome;

public class MP3 extends Item {
	private String artists;
	private int numofTracks;
	
	public MP3(String title, int playingTime, boolean gotIt, String comment,String artists, int numofTracks) {
		super(title, playingTime, gotIt, comment);
		this.artists = artists;
		this.numofTracks = numofTracks;
	}

	
	@Override
	public void print() {
		System.out.print("MP3:[");
		super.print();
		System.out.println("artists:"+artists+";"+"numofTracks:"+numofTracks+"]");
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
