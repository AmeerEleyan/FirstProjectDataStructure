/**
 * @authore: Amir Eleyan
 * ID: 1191076
 * Time:  10/3/2021  7:10 PM
 */
package linkedList;

public class TRecord implements Comparable<TRecord> {

    /**
     * Attributes
     */

    private long seatNum;// set number of the student

    private float grade; // grade of the student

    private static int SCount = 0, // total number of students scientific
            LCount = 0; // total number of students literary

    private String branch; // branch name (scientific or literary)


    // No arg constructor
    public TRecord() {
    }

    // Constructor with specific values
    public TRecord(String line) {
        // 33090802,scientific,80.6
        int firstQ = line.indexOf(',');
        int secondQ = line.lastIndexOf(',');//20
        seatNum = Long.parseLong(line.substring(0, firstQ).trim());
        branch = line.substring(firstQ + 1, secondQ).trim();
        grade = Float.parseFloat(line.substring(secondQ + 1).trim());
        if (this.branch.equalsIgnoreCase("literary")) LCount++;
        else SCount++;
    }

    // return the setNumber of this record
    public long getSeatNum() {
        return this.seatNum;
    }

    // return the grade of this record
    public float getGrade() {
        return this.grade;
    }

    // return the branch of this record
    public String getBranch() {
        return this.branch;
    }

    // return total number of record form scientific branch
    public static int getSCount() {
        return SCount;
    }

    // return total number of record form literary branch
    public static int getLCount() {
        return LCount;
    }

    // set a new setNumber for this record
    public void setSeatNum(long seatNum) {
        this.seatNum = seatNum;
    }

    // set a new grade for this record
    public void setGrade(float grade) {
        this.grade = grade;
    }

    // set a new branch for this record
    public void setBranch(String branch) {
        this.branch = branch;
    }

    // return true if this record from literary branch
    public boolean isLiterary() {
        return branch.trim().equalsIgnoreCase("literary");
    }

    // return true if this record from scientific branch
    public boolean isScientific() {
        return branch.trim().equalsIgnoreCase("scientific");
    }


    // Compare two object with them depends on the set number or grade
    @Override
    public int compareTo(TRecord o) {
        return ((this.grade > o.grade) ? -1 : (this.grade == o.grade) ? 0 : 1);
    }

    // return data of this record as string
    @Override
    public String toString() {
        return "Student: " + this.seatNum + " ," + this.grade + " ," + this.branch + '\n';
    }
    

    // if two obj has a same set number
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TRecord) { // if the object  from TRecord class and has tha same setNumber
            return (this.seatNum == ((TRecord) obj).seatNum);
        }
        return false;
    }

}
