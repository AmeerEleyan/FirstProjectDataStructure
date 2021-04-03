/**
 * @author: Amir Eleyan
 * ID: 1191076
 * At: 30/3/2021  5:50 PM
 */
package GUI;

import linkedList.Calculations;
import linkedList.LinkedList;
import linkedList.Node;
import linkedList.TRecord;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public abstract class PrintReport {

    /**
     * Writing data from List to file
     */
    public static void writeDataInFile(LinkedList<TRecord> list, String region, String branch) {
        //List is Empty
        if (list.isEmpty()) {
            Massage.displayMassage("Error", " There are no records to print her report on file ");
        } else {
            File file;
            PrintWriter printData;
            LinkedList<TRecord> topTen = new LinkedList<>();
            try {
                if (Statistic.total == 0) {
                    Statistic.statAboveGrade(list);
                    Massage.displayMassage("Error", " Please enter the grade to get the\n" +
                            " number of students who obtained this\n grade and there percentage ");
                } else {
                    Calculations.topTenGrads(list, topTen);
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

                    printData.println("\nThe average is: " + String.format("%.2f", avg));
                    printData.println("\nThe Mode is: " + ((mode == 0) ? "There's no mode" : String.format("%.2f", mode)));
                    printData.println("\nThe median is: " + String.format("%.2f", median));
                    printData.println("\nThe stander deviation is: " + String.format("%.2f", sd));
                    printData.println("\nThe variance is: " + String.format("%.2f", variance));
                    printData.println("\n" + Statistic.txtStat.getText());

                    printData.close(); // close file
                    Massage.displayMassage("Success", " The report has been successfully printed ");
                }

            } catch (FileNotFoundException ex) {
                Massage.displayMassage("Error", ex.getMessage());
            }
        }
    }
}
