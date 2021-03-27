package linkedList;

public abstract class Calculations {
    // WestBank Records
    LinkedList<TRecord> westBankLiterarySeatNumber = new LinkedList<>();
    LinkedList<TRecord> westBankLiteraryGrads = new LinkedList<>();
    LinkedList<TRecord> westBankScientificSeatNumber = new LinkedList<>();
    LinkedList<TRecord> westBankScientificGrads = new LinkedList<>();

    // Gaza Records
    LinkedList<TRecord> gazaLiterarySeatNumber = new LinkedList<>();
    LinkedList<TRecord> gazaLiteraryGrads = new LinkedList<>();
    LinkedList<TRecord> gazaScientificSeatNumber = new LinkedList<>();
    LinkedList<TRecord> gazaScientificGrads = new LinkedList<>();

    // Utility list
    LinkedList<TRecord> utility = new LinkedList<>();

    // Top Ten Grads
    static LinkedList<TRecord> topTen = new LinkedList<>();


    /**
     * Calculate Average for data in any specific linkedList
     */
    public static float calculateAverage(LinkedList<TRecord> TRecordLinkedList) {
        if (TRecordLinkedList.getHead() != null) {
            float sum = 0.0F;
            int counter = 0;
            Node<TRecord> currentRecord = TRecordLinkedList.getHead();
            String branch = currentRecord.getData().getBranch();
            while (currentRecord != null) {
                sum += currentRecord.getData().getGrade();
                counter++;
                currentRecord = currentRecord.getNext();
            }
            //System.out.println("The average of " + branch + " students = " + String.format("%.2f", sum / counter));
            return sum / counter;
        }
        return 0.0F;
    }

    /**
     * Calculate Mode for data in any specific linkedList
     */
    public static float calculateMode(LinkedList<TRecord> TRecordLinkedList) {
        if (TRecordLinkedList.getHead() != null) {

            Node<TRecord> current = TRecordLinkedList.getHead();

            String branch = current.getData().getBranch();

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
            if (counter != 0) System.out.println("The Mode on the " + branch + " students is " + mode);
            else System.out.println("There is no Mode in " + branch + " record!!");
            return mode;
        }
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
                return ((previous.getData().getGrade() + current.getData().getGrade()) / (2));
            }
        }
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
            return ((float) Math.sqrt((sum / (TRecordLinkedList.length()-1))));
        }
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
            return 0.0F;
        }
    }

    public static void topTenGrads(LinkedList<TRecord> listOfData, LinkedList<TRecord> topTen) {
        topTen.clear();
        if (listOfData.getHead() != null) {
            Node<TRecord> previous, current = listOfData.getHead();
            int count = 0;
            while (current != null) {
                previous = current;
                current = current.getNext();
                topTen.addLast(previous.getData());
                count++;
                if (count >= 10 && (current.getData().getGrade() != previous.getData().getGrade())) break;
            }
        }
    }

    public static int numberOfRecordAboveAGrade(LinkedList<TRecord> list, float grade) {
        if (list.getHead() != null) {
            Node<TRecord> current = list.getHead();
            int count = 0;
            while (current != null) {
                if (current.getData().getGrade() < grade) break;
                count++;
                current = current.getNext();
            }
            return count;
        }
        return 0;
    }

}
