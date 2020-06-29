package com.example.qodiegence;

public class servicesFourWheeler {
    String name,tfee,lfee;
    boolean training,license;

    public servicesFourWheeler(String name, String tfee, String lfee, boolean training, boolean license) {
        this.name = name;
        this.tfee = tfee;
        this.lfee = lfee;
        this.training = training;
        this.license = license;
    }

    public String getName() {
        return name;
    }

    public boolean isTraining() {
        return training;
    }

    public boolean isLicense() {
        return license;
    }

    public String getTfee() {
        return tfee;
    }

    public String getLfee() {
        return lfee;
    }
}
