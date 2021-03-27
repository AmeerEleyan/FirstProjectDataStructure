package linkedList;

import javafx.scene.control.TextField;

public abstract class CheckTextFiled {

    /**
     * To check the value of the entered setNumber if it is correct or not
     */
    public static boolean isSeatNumber(TextField txt) {

        /* To check the entered setNumber, that it consists of
           digits or more of the integer numbers and positive value
         */
        if (txt.getText().trim().matches("\\d{8,}"))
            if (Long.parseLong(txt.getText().trim()) > 0) return true;
        return false;
    }

    /**
     * To check the value of the entered branch if it is correct or not
     */
    public static boolean isBranch(TextField txt) {

        //  To check the entered branch, that it consists of only characters
        if (!txt.getText().trim().matches(".*\\d.*")) return true;
        return false;
    }

    /**
     * To check the value of the entered grade if it is correct or not
     */
    public static boolean isGrade(TextField txt) {
        try {
            // To check the entered grade, that it consists of only fractions and positive value
            if (Float.parseFloat(txt.getText().trim()) >= 0.0F)
                return true;
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}
