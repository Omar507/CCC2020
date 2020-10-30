import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

@SuppressWarnings("Duplicates")
public class Algorithm4 {

    private int maxPower;
    private int maxElectricity;
    private int numberOfMinutes;
    private int numberOfTasks;

    public void go(File file) throws IOException {
        // Read input fileF
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            maxPower = Integer.parseInt(br.readLine());
            maxElectricity = Integer.parseInt(br.readLine());

            numberOfMinutes = Integer.parseInt(br.readLine());

            ArrayList<Integer> priceList = new ArrayList<>();
            ArrayList<Integer> priceUsage = new ArrayList<>();
            String line;

            for (int i = 0; i < numberOfMinutes; i++) {
                line = br.readLine();
                priceList.add(Integer.parseInt(line));
                priceUsage.add(0);
            }

            numberOfTasks = Integer.parseInt(br.readLine());

            ArrayList<Task4> taskList = new ArrayList<>();

            for (int i = 0; i < numberOfTasks; i++) {
                line = br.readLine();
                String[] tokens = line.split(" ");
                taskList.add(new Task4(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3])));
            }

            Collections.sort(taskList);

            for (int i = 0; i < taskList.size(); i++) {
                getMinForTask(taskList.get(i), priceList, priceUsage);
            }
            writeFile(file, taskList, numberOfTasks);
        }
    }

    private int getMinForTask(Task4 task, ArrayList<Integer> priceList, ArrayList<Integer> priceUsage) {
        int min = Integer.MAX_VALUE;
        int remElectricity = maxElectricity;
        ArrayList<Integer> priceInInterval = new ArrayList();
        for (int i = task.getStartInterval(); i < task.getEndInterval() + 1; i++) {
            priceInInterval.add(priceList.get(i));
        }

        int minIndex = priceInInterval.indexOf(Collections.min(priceInInterval)) + task.getStartInterval();

        if(task.getPower() >= maxPower){
            remElectricity = remElectricity - maxPower * priceList.get(minIndex);
            priceUsage.set(minIndex, task.getPower());
            task.setPower(task.getPower() - maxPower);
        } else if(task.getPower() < maxPower) {
            remElectricity = remElectricity - maxPower * priceList.get(minIndex);
            priceUsage.set(minIndex, task.getPower());
            task.setPower(0);
        }


        for(int j = 0; j < task.getPower(); j++){

        }

        System.out.println(minIndex);
        System.out.println(priceList.get(minIndex));

//        task.setMinute(minIndex);
        return minIndex;
    }

    private void writeFile(File file, ArrayList<Task4> tasks, int numberOfTasks) {
        /**
         * Output: arrival times of the cars, separated by comma, in the order of the input (ex. "98,37,71")
         * @param cars the list of cars in order of appearance in the input file
         */
        try {
            File outputFile = new File("src/resources/level4/" + file.getName().substring(0, file.getName().length() - 3) + ".out");

            // True: the file has been newly created; False: the file already existed
            if (outputFile.createNewFile()) {
                System.out.println("Output file created at " + outputFile.getAbsolutePath());
            } else {
                System.out.println("Writing output to " + outputFile.getAbsolutePath());
            }

            String outputString = "";

            outputString += numberOfTasks + "\n";

            for (Task4 task : tasks) {
//                outputString += String.valueOf(task.getId()) + " " + String.valueOf(task.getMinute()) + " " + String.valueOf(task.getPower()) +"\n";
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
