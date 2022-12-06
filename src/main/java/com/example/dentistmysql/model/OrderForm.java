package com.example.dentistmysql.model;

import org.aspectj.weaver.ast.Or;

import java.time.LocalDate;

public class OrderForm {

    private LocalDate dateFrom;
    private LocalDate dateTo;
    private String patientName;
    private String patientSurname;
    private String orderType;
    private String info;

    private String status;

    public OrderForm() {super();}

    public OrderForm(LocalDate dateFrom, LocalDate dateTo, String patientName, String patientSurname, String orderType, String info, String status) {
        super();
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.patientName = patientName;
        this.patientSurname = patientSurname;
        this.orderType = orderType;
        this.info = info;
        this.status = status;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientSurname() {
        return patientSurname;
    }

    public void setPatientSurname(String patientSurname) {
        this.patientSurname = patientSurname;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) { this.status = status; }
}
