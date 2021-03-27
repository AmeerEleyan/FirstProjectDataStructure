package linkedList;

public class TRecord implements Comparable<TRecord> {

    private long seatNum;

    private float grade;

    private static int SCount = 0, LCount = 0;

    private String branch;

    // Sort by the seat number or mark
    private static int whichSort ;

    public TRecord() {
    }

    public TRecord(String line) {
        // 33090802,scientific,80.6
        int firstQ = line.indexOf(',');//10
        int secondQ = line.lastIndexOf(',');//20
        seatNum = Long.parseLong(line.substring(0, firstQ).trim());
        branch = line.substring(firstQ + 1, secondQ).trim();
        grade = Float.parseFloat(line.substring(secondQ + 1).trim());
        if (this.branch.equalsIgnoreCase("literary")) LCount++;
        else SCount++;
    }

    public long getSeatNum() {
        return seatNum;
    }

    public float getGrade() {
        return grade;
    }

    public String getBranch() {
        return branch;
    }

    public static int getSCount() {
        return SCount;
    }

    public static int getLCount() {
        return LCount;
    }

    public void setSeatNum(long seatNum) {
        this.seatNum = seatNum;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public boolean isLiterary() {
        return branch.trim().equalsIgnoreCase("literary");
    }

    public boolean isScientific() {
        return branch.trim().equalsIgnoreCase("scientific");
    }


    @Override
    public int compareTo(TRecord o) {
        if (whichSort == 1)
            return ((this.seatNum < o.seatNum) ? -1 : (this.seatNum == o.seatNum) ? 0 : 1);
        else
            return ((this.grade > o.grade) ? -1 : (this.grade == o.grade) ? 0 : 1);
    }

    @Override
    public String toString() {
        return "Student: " + this.seatNum + " ," + this.grade + " ," + this.branch + '\n';
    }

    public static void setWhichSort(int whichSort) {
        TRecord.whichSort = whichSort;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TRecord) {
            return (this.seatNum == ((TRecord) obj).seatNum);
        }
        return false;
    }

}
