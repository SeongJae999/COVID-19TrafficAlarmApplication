package com.example.coronagpsalarm.models;

public class PatientInfo {
    private int other;
    private int deaths;
    private int patient;
    private int recovered;
    private int Isolation;

    public PatientInfo() {}

    public int getOther() {
        return other;
    }

    public void setOther(int other) {
        this.other = other;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getPatient() {
        return patient;
    }

    public void setPatient(int patient) {
        this.patient = patient;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public int getIsolation() {
        return Isolation;
    }

    public void setIsolation(int isolation) {
        Isolation = isolation;
    }
}
