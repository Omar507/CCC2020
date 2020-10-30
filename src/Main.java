import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        File file = new File("src/resources/level2");
        File[] files = file.listFiles();


        for (File f : files) {
            Algorithm2 alg = new Algorithm2();
            alg.go(f);
        }

//        File example = new File("src/resources/level2/level2_example.in");
//        Algorithm2 algg = new Algorithm2();
//        algg.go(example);
    }
}
