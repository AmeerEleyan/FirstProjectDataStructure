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
    public static void writeDataInFile(LinkedList<TRecord> list, String region, String branch) {
        //List is Empty
        if (list.isEmpty()) {
            Massage.displayMassage("Error", " There is no data to print it in file ");
        } else {
            File file;
            PrintWriter printData;
            LinkedList<TRecord> topTen = new LinkedList<>();
            try {

                Calculations.topTenGrads(list, topTen);
                Statistic.statAboveGrade(list);

                /* constructor of File class having file as argument */
                file = new File("Report.txt");

                /* file to writing data */
                printData = new PrintWriter(file);
                printData.println("\n\t\t\t******Report on " + branch + " students in " + region + "******\n");
                printData.println("\nThe top ten students are:\n");

                Node<TRecord> current = topTen.getHead();
                int count = 1;
                while (current != null) {
                    printData.println(count + "- " + current);
                    current = current.getNext();
                    count++;
                }
                printData.println("\n");

                float avg = Calculations.calculateAverage(list);
                float mode = Calculations.calculateMode(list);
                float median = Calculations.calculateMedian(list);
                float sd = Calculations.calculateStandardDeviation(list);
                float variance = Calculations.calculateVariance(list);
                int stat = Calculations.numberOfRecordAboveAGrade(list, Statistic.totalGrade);

                printData.println("\nThe average is: " + String.format("%.2f", avg));
                printData.println("\nThe Mode is: " + ((mode == 0) ? "There's no mode" : String.format("%.2f", mode)));
                printData.println("\nThe median is: " + String.format("%.2f", median));
                printData.println("\nThe stander deviation is: " + String.format("%.2f", sd));
                printData.println("\nThe variance is: " + String.format("%.2f", variance));
                printData.println("\nThe number of students who obtained a " + Statistic.totalGrade + " or more: " + stat +
                        "\nAnd their percentage: " + String.format("%.2f", ((float) stat / list.length()) * 100) + "%");

                printData.close(); // close file

            } catch (FileNotFoundException ex) {
                Massage.displayMassage("Error", ex.getMessage());
            }
        }
    }
}
