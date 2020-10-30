import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Algorithm {

    private int numberOfMinutes;

    public void go(File file) throws IOException {
        // Read input file
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            numberOfMinutes = Integer.parseInt(br.readLine());

            ArrayList<Integer> list = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                list.add(Integer.parseInt(line));
            }
            System.out.println("File: " + file.getName());
            int minIndex = list.indexOf(Collections.min(list));
            System.out.println("Min is: " + minIndex);
        }
    }
}
