package edu.sfsu.cs.orange.ocr;

public class UserDTO {
    private String user_phone;
    private String user_email;
    private String user_password;
    private Long user_age;
    private String user_diseases;
    private String user_medicine;

    public UserDTO(){}
    public UserDTO(String user_phone, String user_email, String user_password, Long user_age, String user_diseases, String user_medicine){
        this.user_phone = user_phone;
        this.user_email = user_email;
        this.user_password = user_password;
        this.user_age = user_age;
        this.user_diseases = user_diseases;
        this.user_medicine = user_medicine;
    }

    public String getUser_phone(){ return user_phone;}
    public String getUser_email(){ return user_email;}
    public String getUser_password(){ return user_password;}
    public Long getUser_age(){ return user_age;}
    public String getUser_diseases(){ return user_diseases;}
    public String getUser_medicine(){ return user_medicine;}


    public void setUser_phone(String user_phone){ this.user_phone = user_phone;}
    public void setUser_email(String user_email){ this.user_email= user_email;}
    public void setUser_password(String user_password){ this.user_password = user_password;}
    public void setUser_age(Long user_age){ this.user_age= user_age;}
    public void setUser_diseases(String user_diseases){ this.user_diseases = user_diseases;}
    public void setUser_medicine(String user_medicine){ this.user_medicine= user_medicine;}

}
