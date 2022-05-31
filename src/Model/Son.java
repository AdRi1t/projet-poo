
package Model;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Son {
    private Clip clip1;
    private Clip clip2;

    public Son() throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        Scanner scanner = new Scanner(System.in);

        File file2 = new File("Musique/modern-rnb-all-your-base-15484.wav");
        File file1 = new File("Musique/let-the-games-begin-21858.wav");

        AudioInputStream audioStream1 = AudioSystem.getAudioInputStream(file1);
        AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(file2);

        this.clip1 = AudioSystem.getClip();
        this.clip1.open(audioStream1);

        this.clip2 = AudioSystem.getClip();
        this.clip2.open(audioStream2);
    }

    //clip1
    public void setRepeats1(boolean repeats) {
        clip1.loop(repeats ? Clip.LOOP_CONTINUOUSLY : 1);
    }

    public void reset1() {
        clip1.stop();
        clip1.setFramePosition(0);
    }
    public void play1() {
        clip1.start();
    }

    public void stop1() {
        clip1.stop();
    }

    public boolean isPlaying1() {

        return clip1.isActive();
    }


    //clip2
    public void setRepeats2(boolean repeats) {
        clip2.loop(repeats ? Clip.LOOP_CONTINUOUSLY : 1);
    }

    public void reset2() {
        clip2.stop();
        clip2.setFramePosition(0);
    }
    public void play2() {
        clip2.start();
    }

    public void stop2() {
        clip2.stop();
    }

    public boolean isPlaying2() {
        return clip2.isActive();
    }


}