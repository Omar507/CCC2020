import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
//        File file = new File("src/resources/level4");
//        File[] files = file.listFiles();
//
//
//        for (File f : files) {
//            Algorithm3 alg = new Algorithm3();
//            alg.go(f);
//        }

        File example = new File("src/resources/level4/level4_example.in");
        Algorithm4 algg = new Algorithm4();
        algg.go(example);
    }
}
