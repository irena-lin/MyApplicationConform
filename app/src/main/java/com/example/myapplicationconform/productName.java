package com.example.myapplicationconform;

public class productName {
    private Integer Pid;
    private String Pname;
    private String icon;

    public String getIcon() {
        return icon;
    };

    public String getPname() {
        return Pname;
    }

    public Integer getPid() {
        return Pid;
    }

    public void set(int Pid,String Pname){
        this.Pid = Pid;
        this.Pname = Pname;
    }
}
