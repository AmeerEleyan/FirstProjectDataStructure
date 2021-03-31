/**
 * @author: Amir Eleyan
 * ID: 1191076
 * At: 26/3/2021 6:00 PM
 */
package linkedList;

import GUI.Massage;

public abstract class Calculations {

    // WestBank Records
    public static LinkedList<TRecord> westBankLiteraryList = new LinkedList<>();
    public static LinkedList<TRecord> westBankScientificList = new LinkedList<>();

    // Gaza records
    public static LinkedList<TRecord> gazaLiterary = new LinkedList<>();
    public static LinkedList<TRecord> gazaScientific = new LinkedList<>();


    // Top Ten Grads
    public static LinkedList<TRecord> topTen = new LinkedList<>();


    /**
     * Calculate Average for data in any specific linkedList
     */
    public static float calculateAverage(LinkedList<TRecord> TRecordLinkedList) {
        if (TRecordLinkedList.getHead() != null) {
            float sum = 0.0F;
            int counter = 0;
            Node<TRecord> currentRecord = TRecordLinkedList.getHead();
            while (currentRecord != null) {
                sum += currentRecord.getData().getGrade();
                counter++;
                currentRecord = currentRecord.getNext();
            }

            return sum / counter;
        }
        Massage.displayMassage("Error", " There are no data to calculate its Average ");
        return 0.0F;
    }

    /**
     * Calculate Mode for data in any specific linkedList
     */
    public static float calculateMode(LinkedList<TRecord> TRecordLinkedList) {
        if (TRecordLinkedList.getHead() != null) {

            Node<TRecord> current = TRecordLinkedList.getHead();

            float mode = current.getData().getGrade();

            current = current.getNext();

            int counter = 0;

            // To get the first mode
            while (current != null && current.getData().getGrade() == mode) {
                counter++;
                current = current.getNext();
            }

            // To check all values in linkedList if have another mode
            while (current != null) {
                int tempCount = 0;

                // Get the second Mode to compare it with the first Mode
                while (current.getNext() != null && current.getData().getGrade() == current.getNext().getData().getGrade()) {
                    tempCount++;
                    current = current.getNext();
                }

                // Compare first Mode with second Mode
                if (tempCount > counter) {
                    counter = tempCount;
                    mode = current.getData().getGrade();
                }
                current = current.getNext();
            }
            if (counter != 0) return mode;
            else {
                Massage.displayMassage("", " There is no Mode in this data ");
                return 0;
            }
        }
        Massage.displayMassage("Error", " There are no data to calculate its Mode ");
        return -1.0F;
    }

    /**
     * Calculate Median for data in any specific linkedList
     */
    public static float calculateMedian(LinkedList<TRecord> TRecordLinkedList) {
        if (TRecordLinkedList.getHead() != null) {

            int length = TRecordLinkedList.length();
            int indexOfMedian = length / 2;

            Node<TRecord> current = TRecordLinkedList.getHead();
            Node<TRecord> previous = null;

            for (int i = 0; (i < indexOfMedian && current.getNext() != null); ++i) {
                previous = current;
                current = current.getNext();
            }

            System.out.println(current.getData() + "\n");
            if (length % 2 != 0) {
                return current.getData().getGrade();
            } else {
                return ((previous.getData().getGrade() + current.getData().getGrade()) / 2);
            }
        }
        Massage.displayMassage("Error", " There are no data to calculate its Median ");
        return 0.0F;
    }

    /**
     * Calculate Standard Deviation for data in any specific linkedList
     */
    public static float calculateStandardDeviation(LinkedList<TRecord> TRecordLinkedList) {
        if (TRecordLinkedList.getHead() != null) {

            float average = calculateAverage(TRecordLinkedList);
            float sum = 0.0F;

            Node<TRecord> currentForSum = TRecordLinkedList.getHead();
            while (currentForSum != null) {

                sum += (float) Math.pow((currentForSum.getData().getGrade() - average), 2.0D);
                currentForSum = currentForSum.getNext();
            }
            return ((float) Math.sqrt((sum / (TRecordLinkedList.length() - 1))));
        }
        Massage.displayMassage("Error", " There are no data to calculate its Stander Deviation ");
        return 0.0F;
    }

    /**
     * Calculate Variance for data in any specific linkedList
     */
    public static float calculateVariance(LinkedList<TRecord> TRecordLinkedList) {
        if (TRecordLinkedList.getHead() != null) {
            float var = calculateStandardDeviation(TRecordLinkedList);
            return (float) Math.pow(var, 2);
        } else {
            Massage.displayMassage("Error", " There are no data to calculate its Variance ");
            return 0.0F;
        }
    }

    /**
     * Return top ten grades in the records
     */
    public static void topTenGrads(LinkedList<TRecord> listOfData, LinkedList<TRecord> topTen) {
        topTen.clear();
        if (listOfData.getHead() != null) {
            Node<TRecord> previous, current = listOfData.getHead();
            int count = 0;
            while (current != null) {
                previous = current;
                current = current.getNext();
                topTen.insertAtLast(previous.getData());
                count++;
                if (count >= 10 && (current.getData().getGrade() != previous.getData().getGrade())) break;
            }
        } else
            Massage.displayMassage("Error", " There are no records to display Top Ten Grads ");
    }

    /**
     * Returns the number of students who obtained a certain grade or more
     */
    public static int numberOfRecordAboveAGrade(LinkedList<TRecord> list, float grade) {
        if (list.getHead() != null) {

            Node<TRecord> current = list.getHead();
            int count = 0;

            while (current != null) {
                if (current.getData().getGrade() < grade) break; // because we read list descending
                count++;
                current = current.getNext();
            }
            return count;
        }
        Massage.displayMassage("Error", " There is no data to statistic ");
        return 0;
    }

}
