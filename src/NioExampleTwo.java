import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

public class NioExampleTwo {

    public static void main(String[] args) throws IOException {

        //Input files
        String[] inputFiles = {"src/file1.txt", "src/file2.txt"};

        //Files' content written to this file
        String outputFileLocation = "src/OutputExample2.txt";

        FileOutputStream fileOutputStream = new FileOutputStream(outputFileLocation);
        WritableByteChannel targetChannel = fileOutputStream.getChannel();

        for (int i = 0; i < inputFiles.length; i++) {

            //Get channel for input files
            FileInputStream fileInputStream = new FileInputStream(inputFiles[i]);
            FileChannel inputChannel = fileInputStream.getChannel();

            //Transfer data from input channel to output channel
            inputChannel.transferTo(0, inputChannel.size(), targetChannel);

            //close the input channel
            inputChannel.close();
            fileInputStream.close();
        }

        //finally close the target channel
        targetChannel.close();
        fileOutputStream.close();
    }
}
