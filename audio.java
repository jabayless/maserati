import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class playAudio {
	public static void main(String [] args){ 
		String bip = ".mp3";
		Media hit = new Media(new File(bip).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(hit);
		mediaPlayer.play();
	}
}