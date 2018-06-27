package edu.sfsu.cs.orange.ocr;

/**
 * Created by ravi on 16/11/17.
 */

public class Contact {
    String title;
    String image;
    String description;
    String Dnumber;
    String caution;
    String pro;
    String usage;

    public Contact() {
    }

    public String getName() {
        return title;
    }
    public String getImage() {
        return image;
    }
    public String getPhone() {
        return description;
    }
    public String getDnumber() { return Dnumber; }
    public String getCaution() { return caution; }
    public String getPro() { return pro; }
    public String getUsage() { return usage; }
}
