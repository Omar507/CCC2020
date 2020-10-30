import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        File file = new File("src/resources");
        File[] files = file.listFiles();


        for (File f : files) {
            Algorithm alg = new Algorithm();
            alg.go(f);
        }
    }
}
