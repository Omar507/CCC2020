import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

@SuppressWarnings("Duplicates")
public class Algorithm2 {

    private int numberOfMinutes;
    private int numberOfTasks;

    public void go(File file) throws IOException {
        // Read input file
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            numberOfMinutes = Integer.parseInt(br.readLine());

            ArrayList<Integer> priceList = new ArrayList<>();
            String line;

            for(int i = 0; i < numberOfMinutes; i++){
                line = br.readLine();
                priceList.add(Integer.parseInt(line));
            }

            numberOfTasks = Integer.parseInt(br.readLine());

            ArrayList<Task> taskList = new ArrayList<>();

            for(int i = 0; i < numberOfTasks; i++){
                line = br.readLine();
                String[] tokens = line.split(" ");
                taskList.add(new Task(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1])));
            }


//            param n = Anzahl der Stromwerte
//            param d = Dauer der Aufgabe
//
//            int min = infinity aka. Integer.max();
//            int minIndex = infinty
//
//
//            for i = 0 to n-d {
//                int sum = 0;
//                for j = 0 to d {
//                    sum += Stromwerte[j]
//                }
//
//                if( sum < min ) {
//                    min = sum;
//                    minIndex = i;
//                }
//
//            }

            for(int i = 0; i < taskList.size(); i++){
                getMinForTask(taskList.get(i), priceList);
            }

            for(int i = 0; i < taskList.size(); i++){
                System.out.print(taskList.get(i).getId() + " ");
                System.out.println(taskList.get(i).getStartId());
            }

            System.out.println("File: " + file.getName());
            int minIndex = priceList.indexOf(Collections.min(priceList));
//            System.out.println("Min is: " + minIndex);
        }
    }

    private int getMinForTask(Task task, ArrayList<Integer> priceList) {
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < priceList.size() - task.getCompletionTime() + 1; i++){
            int sum = 0;
            for (int j = i; j < task.getCompletionTime() + i; j++){
                sum += priceList.get(j);
            }
            if(sum < min){
                min = sum;
                task.setStartId(i);
            }
        }
        return task.getStartId();
    }
}
