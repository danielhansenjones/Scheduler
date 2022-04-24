package model;

/**
 * This class is an object constructor used to create Customer objects and get/set their values.
 */
public class Customer {
    private int customerId;
    private String customerName;
    private String address;
    private String postalCode;
    private String phoneNumber;
    private String division;
    private String country;
    private int divisionId;

    /**
     * @param customerId   Int value of Customer ID
     * @param customerName String value of Customer Name
     * @param address      String value of Address
     * @param postalCode   String value of Postal Code
     * @param phoneNumber  String value of Phone Number
     * @param division     String value of Division
     * @param country      String value of Country
     * @param divisionId   Int value of Division ID
     */
    public Customer(int customerId,
                    String customerName,
                    String address,
                    String postalCode,
                    String phoneNumber,
                    String division,
                    String country,
                    int divisionId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.division = division;
        this.country = country;
        this.divisionId = divisionId;
    }

    /**
     * Gets Customer ID
     * @return customerId Integer
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Sets Customer ID
     * @param customerId Integer
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Gets Customer Name
     * @return customerName String
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Sets Customer Name
     * @param customerName String
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Gets Address
     * @return address String
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets Address
     * @param address String
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets Postal Code
     * @return postalCode String
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets Postal Code
     * @param postalCode String
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Gets Phone Number
     * @return phoneNumber String
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets Phone Number
     * @param phoneNumber String
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets Division
     * @return division String
     */
    public String getDivision() {
        return division;
    }

    /**
     * Sets Division Name
     * @param division String
     */
    public void setDivision(String division) {
        this.division = division;
    }

    /**
     * Gets Country Name
     * @return country String
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets Country Name
     * @param country String
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets Division ID
     *
     * @return divisionId Integer
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * Sets Division ID
     *
     * @param divisionId Integer
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }
}