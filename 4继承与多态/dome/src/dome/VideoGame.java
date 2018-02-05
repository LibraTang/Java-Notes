package dome;

public class VideoGame extends Item {
	private int numOfPlayers;
	
	
	public VideoGame(String title, int playingTime, boolean gotIt, String comment, int numOfPlayers) {
		super(title, playingTime, gotIt, comment);
		this.numOfPlayers = numOfPlayers;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
