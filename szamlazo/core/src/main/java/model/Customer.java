package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.internal.NotNull;
import exception.EmptyStringException;

import java.util.UUID;

public class Customer {

    private String id;
    private String name;
    private boolean isCompany;
    private String taxID;
    private String email;
    private String phoneNumber;
    private String shipping_address_postcode;
    private String shipping_address_city;
    private String shipping_address_street;
    private String shipping_address_other;
    private String invoicing_address_postcode;
    private String invoicing_address_city;
    private String invoicing_address_street;
    private String invoicing_address_other;
    private String password;

    public Customer() {
        this.id = UUID.randomUUID().toString();
    }

    public String getName() {
        return name;
    }

    public void setName(@NotNull String name) throws EmptyStringException {
        if (name.isEmpty()){
            throw new EmptyStringException();
        }
        this.name = name;
    }

    public boolean isCompany() {
        return isCompany;
    }

    public void setCompany(boolean company) {
        isCompany = company;
    }

    public String getTaxID() {
        return taxID;
    }

    public void setTaxID(@NotNull String taxID) {
        this.taxID = taxID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(@NotNull String email) throws EmptyStringException {
        if (email.isEmpty()){
            throw  new EmptyStringException();
        }
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getShipping_address_postcode() {
        return shipping_address_postcode;
    }

    public void setShipping_address_postcode(@NotNull String shipping_address_postcode) throws EmptyStringException {
        if (shipping_address_postcode.isEmpty()){
            throw new EmptyStringException();
        }
        this.shipping_address_postcode = shipping_address_postcode;
    }

    public String getShipping_address_city() {
        return shipping_address_city;
    }

    public void setShipping_address_city(@NotNull String shipping_address_city) throws EmptyStringException {
        if (shipping_address_city.isEmpty()){
            throw new EmptyStringException();
        }
        this.shipping_address_city = shipping_address_city;
    }

    public String getShipping_address_street() {
        return shipping_address_street;
    }

    public void setShipping_address_street(@NotNull String shipping_address_street) throws EmptyStringException {
        if (shipping_address_street.isEmpty()){
            throw new EmptyStringException();
        }
        this.shipping_address_street = shipping_address_street;
    }

    public String getShipping_address_other() {
        return shipping_address_other;
    }

    public void setShipping_address_other(String shipping_address_other) {
        this.shipping_address_other = shipping_address_other;
    }

    public String getInvoicing_address_postcode() {
        return invoicing_address_postcode;
    }

    public void setInvoicing_address_postcode(@NotNull String invoicing_address_postcode) throws EmptyStringException {
        if (invoicing_address_postcode.isEmpty()){
            throw new EmptyStringException();
        }
        this.invoicing_address_postcode = invoicing_address_postcode;
    }

    public String getInvoicing_address_city() {
        return invoicing_address_city;
    }

    public void setInvoicing_address_city(@NotNull String invoicing_address_city) throws EmptyStringException {
        if (invoicing_address_city.isEmpty()){
            throw new EmptyStringException();
        }
        this.invoicing_address_city = invoicing_address_city;
    }

    public String getInvoicing_address_street() {
        return invoicing_address_street;
    }

    public void setInvoicing_address_street(@NotNull String invoicing_address_street) throws EmptyStringException {
        if (invoicing_address_street.isEmpty()){
            throw new EmptyStringException();
        }
        this.invoicing_address_street = invoicing_address_street;
    }

    public String getInvoicing_address_other() {
        return invoicing_address_other;
    }

    public void setInvoicing_address_other(String invoicing_address_other) {
        this.invoicing_address_other = invoicing_address_other;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(@NotNull String password) {
        this.password = password;
    }

    @JsonIgnore
    public boolean isMissingArgument(){
        try {
            if (    !this.name.equals("") &&
                    !(isCompany && this.taxID.equals("")) &&
                    !this.email.equals("") &&
                    !this.password.equals("") &&
                    !this.invoicing_address_city.equals("") &&
                    !this.invoicing_address_postcode.equals("") &&
                    !this.invoicing_address_street.equals("") &&
                    !this.shipping_address_city.equals("") &&
                    !this.shipping_address_postcode.equals("") &&
                    !this.shipping_address_street.equals("")){
                return false;
            } else {
                return true;
            }
        } catch (NullPointerException e){
            e.printStackTrace();
            return true;
        }
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Customer){
            if (((Customer) obj).getId().equals(this.id) &&
                    ((Customer) obj).getName().equals(this.name) &&
                    ((Customer) obj).getEmail().equals(this.email) &&
                    ((Customer) obj).getTaxID().equals(this.taxID) &&
                    ((Customer) obj).isCompany == this.isCompany &&
                    ((Customer) obj).getPhoneNumber().equals(this.phoneNumber) &&
                    ((Customer) obj).getInvoicing_address_city().equals(this.invoicing_address_city) &&
                    ((Customer) obj).getInvoicing_address_postcode().equals(this.invoicing_address_postcode)&&
                    ((Customer) obj).getInvoicing_address_street().equals(this.invoicing_address_street)&&
                    ((Customer) obj).getInvoicing_address_other().equals(this.invoicing_address_other)&&
                    ((Customer) obj).getShipping_address_city().equals(this.shipping_address_city)&&
                    ((Customer) obj).getShipping_address_postcode().equals(this.shipping_address_postcode)&&
                    ((Customer) obj).getShipping_address_street().equals(this.shipping_address_street)&&
                    ((Customer) obj).getShipping_address_other().equals(shipping_address_other)){
                return true;
            } else{
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", isCompany=" + isCompany +
                ", taxID='" + taxID + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", shipping_address_postcode='" + shipping_address_postcode + '\'' +
                ", shipping_address_city='" + shipping_address_city + '\'' +
                ", shipping_address_street='" + shipping_address_street + '\'' +
                ", shipping_address_other='" + shipping_address_other + '\'' +
                ", invoicing_address_postcode='" + invoicing_address_postcode + '\'' +
                ", invoicing_address_city='" + invoicing_address_city + '\'' +
                ", invoicing_address_street='" + invoicing_address_street + '\'' +
                ", invoicing_address_other='" + invoicing_address_other + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
