import java.io.FileWriter;
import java.io.IOException;

public class FileWriterDemo {

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("./a.txt");
            fileWriter.append("hello i love you!");
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        fileWriter.close();
    }

}
