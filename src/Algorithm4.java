import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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

        //minute minIndex cant be used
        if(priceUsage.get(minIndex) == maxPower){
            ArrayList<Integer> fakePriceList = new ArrayList<>();
            for (Integer value : priceList) {
                fakePriceList.add(value);
            }
            fakePriceList.set(minIndex, Integer.MAX_VALUE);
            getMinForTask(task, fakePriceList, priceUsage);
        } else {
            int powerAvailableForMinute = maxPower - priceUsage.get(minIndex);
            if(task.getPower() > powerAvailableForMinute){
                remElectricity = remElectricity - powerAvailableForMinute * priceList.get(minIndex);
                priceUsage.set(minIndex, powerAvailableForMinute);
                Tuple tuple = new Tuple(minIndex, powerAvailableForMinute);
                task.getConsumption().add(tuple);
                task.setPower(task.getPower() - powerAvailableForMinute);
                //this task still has power left
                getMinForTask(task, priceList, priceUsage);
            } else if(task.getPower() <= powerAvailableForMinute) {
                remElectricity = remElectricity - task.getPower() * priceList.get(minIndex);
                priceUsage.set(minIndex, task.getPower());
                Tuple tuple = new Tuple(minIndex, task.getPower());
                task.getConsumption().add(tuple);
                task.setPower(0);
            }

            if(remElectricity < 0){
                System.out.println("Something went wrong!!! Remaining Electricity is negative!!!");
            } else {
                maxElectricity = remElectricity;
            }
        }
        return minIndex;
    }

    private void writeFile(File file, ArrayList<Task4> tasks, int numberOfTasks) {
        tasks.sort(Comparator.comparing(Task4::getId));
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
                outputString += String.valueOf(task.toString());
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
