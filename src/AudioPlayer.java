import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.File;

public class AudioPlayer extends JFrame{
    static MediaPlayer mediaPlayer;


    public static void playAudio(String path){
        Media audioClip = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(audioClip);

       /* mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                Platform.exit();
            }
        });*/

       try{
           mediaPlayer.play();
       }
       catch(Exception e){
           System.out.println("The audio file could not be played");
       }
    }
}
