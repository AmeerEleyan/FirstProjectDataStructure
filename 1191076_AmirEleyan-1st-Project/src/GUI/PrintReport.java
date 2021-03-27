package GUI;

import linkedList.Calculations;
import linkedList.LinkedList;
import linkedList.Node;
import linkedList.TRecord;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class PrintReport {

    /**
     * Writing data from List to file
     */
    public static void writeDataInFile(LinkedList<TRecord> list, LinkedList<TRecord> topTen, String region, String branch) {
        //List is Empty
        if (list.isEmpty() && topTen.isEmpty()) {
            Massage.displayMassage("Error", "There is no data to print it in file");
        } else {
            File file;
            PrintWriter printData;
            try {
                Statistic.statAboveGrade(list);

                /* constructor of File class having file as argument */
                file = new File("Report.txt");

                /* file to writing data */
                printData = new PrintWriter(file);
                printData.println("\n\t\t******Report on " + branch + " students in " + region + "******");
                printData.println("The top ten grads are:");
                Node<TRecord> current = topTen.getHead();
                while (current != null) {
                    printData.println(current);
                    current = current.getNext();
                }
                printData.println("\n");

                float avg = Calculations.calculateAverage(list);
                float mode = Calculations.calculateMode(list);
                float median = Calculations.calculateMedian(list);
                float sd = Calculations.calculateStandardDeviation(list);
                float variance = Calculations.calculateVariance(list);
                int stat = Calculations.numberOfRecordAboveAGrade(list, Statistic.grade);

                printData.println("The average is: " + String.format("%.2f", avg));
                printData.println("The Mode is: " + ((mode == 0) ? "There's no mode" : String.format("%.2f", mode)));
                printData.println("The median is: " + String.format("%.2f", median));
                printData.println("The stander deviation is: " + String.format("%.2f", sd));
                printData.println("The variance is: " + String.format("%.2f", variance));
                printData.println("The number of students who obtained a " + Statistic.grade + " or more: " + stat +
                        "\nAnd their percentage: " + String.format("%.2f", (stat / list.length()) * 100) + "%");

                printData.close(); // close file

            } catch (FileNotFoundException ex) {
                Massage.displayMassage("Error", ex.getMessage());
            }
        }
    }
}
