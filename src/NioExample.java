import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioExample {

    public static void main(String[] args) throws IOException {

        // initializing two files in Array;
        String[] inputFiles = {"src/file1.txt", "src/file2.txt"};

        // Specify out file with path location
        //Files contents will be written in these files
        String outputFileLocation = "src/nioOutput.txt";

        // Get Channel for destination or outputFile
        FileOutputStream fileOutputStream = new FileOutputStream(outputFileLocation);
        FileChannel targetChannel = fileOutputStream.getChannel();

        for (int i = 0; i < inputFiles.length; i++) {

            //Get channel for input files
            FileInputStream fileInputStream = new FileInputStream(inputFiles[i]);
            FileChannel inputChannel = fileInputStream.getChannel();

            long size = inputChannel.size();
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) size);
            System.out.print((char) byteBuffer.get());
            while (inputChannel.read(byteBuffer) > 0) {
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()) {
                    //System.out.print((char) byteBuffer.get());
                    targetChannel.write(byteBuffer);
                }
            }
            fileInputStream.close();
        }
    }
}