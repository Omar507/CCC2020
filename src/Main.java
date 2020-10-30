import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        File file = new File("src/resources/level3");
        File[] files = file.listFiles();


        for (File f : files) {
            Algorithm3 alg = new Algorithm3();
            alg.go(f);
        }

//        File example = new File("src/resources/level3/level3_example.in");
//        Algorithm3 algg = new Algorithm3();
//        algg.go(example);
    }
}
