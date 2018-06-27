package edu.sfsu.cs.orange.ocr;
public class DrugDTO {
    //    private String drug_name;
    private String drug_num;
    private String drug_name;
    private String user_name;

    public DrugDTO(){ }
    public DrugDTO(String drug_num){
        this.drug_num = drug_num;
    }
    public String getDrug_name(){ return drug_name; }
    public String getDrug_num(){
        return drug_num;
    }
    public void setDrug_name(String drug_name){ this.drug_name = drug_name; }
    public void setDrug_num(String drug_num){
        this.drug_num = drug_num;
    }

}
