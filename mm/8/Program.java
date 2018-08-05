import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import com.sun.speech.freetts.audio.JavaClipAudioPlayer;
import java.io.*;

public class Program
{
    public static void main(String[] args)
    {
        FileInputStream fin = null;
        String fileName,strContents = "";

        VoiceManager voiceManager = VoiceManager.getInstance();
        Voice v = voiceManager.getVoice("kevin");

        try
        {
            fin = new FileInputStream("hey-there-delilah.txt");
            System.out.println("Now Speaking...");

            v.allocate();
            v.speak(fin);
            v.deallocate();

            fin.close();

            System.out.println("Over Speaking...");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
