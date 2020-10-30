import java.io.*;
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

            for (int i = 0; i < numberOfMinutes; i++) {
                line = br.readLine();
                priceList.add(Integer.parseInt(line));
            }

            numberOfTasks = Integer.parseInt(br.readLine());

            ArrayList<Task> taskList = new ArrayList<>();

            for (int i = 0; i < numberOfTasks; i++) {
                line = br.readLine();
                String[] tokens = line.split(" ");
                taskList.add(new Task(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1])));
            }


            for (int i = 0; i < taskList.size(); i++) {
                getMinForTask(taskList.get(i), priceList);
            }

            for (int i = 0; i < taskList.size(); i++) {
                System.out.print(taskList.get(i).getId() + " ");
                System.out.println(taskList.get(i).getStartId());
            }
            writeFile(file, taskList, numberOfTasks);

        }
    }

    private int getMinForTask(Task task, ArrayList<Integer> priceList) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < priceList.size() - task.getCompletionTime() + 1; i++) {
            int sum = 0;
            for (int j = i; j < task.getCompletionTime() + i; j++) {
                sum += priceList.get(j);
            }
            if (sum < min) {
                min = sum;
                task.setStartId(i);
            }
        }
        return task.getStartId();
    }

    private void writeFile(File file, ArrayList<Task> tasks, int numberOfTasks) {
        /**
         * Output: arrival times of the cars, separated by comma, in the order of the input (ex. "98,37,71")
         * @param cars the list of cars in order of appearance in the input file
         */
        try {
            File outputFile = new File("src/resources/level2/" + file.getName().substring(0, file.getName().length() - 3) + ".out");

            // True: the file has been newly created; False: the file already existed
            if (outputFile.createNewFile()) {
                System.out.println("Output file created at " + outputFile.getAbsolutePath());
            } else {
                System.out.println("Writing output to " + outputFile.getAbsolutePath());
            }

            String outputString = "";

            outputString += numberOfTasks + "\n";

            for (Task task : tasks) {
                outputString += String.valueOf(task.getId()) + " " + String.valueOf(task.getStartId()) + "\n";
            }


            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));
            bw.write(outputString);
            bw.close();
            System.out.println(outputString);

        } catch (IOException e) {
            System.out.println("ERROR: Something went wrong creating / writing to the output file");
            e.printStackTrace();
        }
    }
}
