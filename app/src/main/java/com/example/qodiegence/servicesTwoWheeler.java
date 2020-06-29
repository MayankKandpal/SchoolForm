package com.example.qodiegence;

public class servicesTwoWheeler{
    String name,tfee,lfee;
    Boolean training,license;

    public servicesTwoWheeler(String name, String tfee, String lfee, Boolean training, Boolean license) {
        this.name = name;
        this.tfee = tfee;
        this.lfee = lfee;
        this.training = training;
        this.license = license;
    }

    public String getName() {
        return name;
    }

    public Boolean getTraining() {
        return training;
    }

    public Boolean getLicense() {
        return license;
    }

    public String getTfee() {
        return tfee;
    }

    public String getLfee() {
        return lfee;
    }
}
