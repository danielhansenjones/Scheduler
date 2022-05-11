package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Appointment {
    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String contactName;
    private int customerId;
    private int userId;
    private int contactId;
    /*  timestamps are retrieved from SQL database */

    /**
     * @param appointmentId Int value of Appointment ID
     * @param title         String value of Title
     * @param description   String value of Description
     * @param location      String value of Location
     * @param type          String value of Type
     * @param startDate     LocalDate value of Start Date
     * @param startTime     LocalDateTime value of Start Time
     * @param endDate       LocalDate value of End Date
     * @param endTime       LocalDateTime value of End Time
     * @param customerId    Int value of Customer ID
     * @param userId        Int value of User ID
     * @param contactId     Int value of Contact ID
     * @param contactName   String value of Contact Name
     */
    public Appointment(int appointmentId,
                       String title,
                       String description,
                       String location,
                       String type,
                       LocalDate startDate,
                       LocalDateTime startTime,
                       LocalDate endDate,
                       LocalDateTime endTime,
                       int customerId,
                       int userId,
                       int contactId,
                       String contactName) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
        this.contactName = contactName;
    }

    public Appointment() {

    }

    /**
     * Gets Appointment ID
     *
     * @return appointmentId Integer
     */
    public int getAppointmentId() {
        return appointmentId;
    }

    /**
     * Sets Appointment ID
     *
     * @param appointmentId Integer
     */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**
     * Gets Appointment Title
     *
     * @return title String
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets Appointment Title
     *
     * @param title String
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets Appointment Description
     *
     * @return description String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets Appointment Description
     *
     * @param description String
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets Appointment Location
     *
     * @return location String
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets Appointment Location
     *
     * @param location String
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets Appointment Type
     *
     * @return type String
     */
    public String getType() {
        return type;
    }

    /**
     * Sets Appointment Type
     *
     * @param type String
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets Start Date
     *
     * @return startDate LocalDate
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Sets Appointment Start Date
     *
     * @param startDate LocalDate
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets Start Time
     *
     * @return startTime LocalDateTime
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Sets Appointment Start Time
     *
     * @param startTime LocalDateTime
     */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets End Date
     *
     * @return endDate LocalDate
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Sets Appointment End Date
     *
     * @param endDate LocalDate
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets End Time
     *
     * @return endTime LocalDateTime
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Sets Appointment End Time
     *
     * @param endTime LocalDateTime
     */
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    /**
     * Gets Customer ID
     *
     * @return customerId Integer
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Sets Customer ID
     *
     * @param customerId Integer
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Gets User ID
     *
     * @return userId Integer
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets User ID
     *
     * @param userId Integer
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets Contact ID
     *
     * @return contactId Integer
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * Sets Contact ID
     *
     * @param contactId Integer
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     * Gets Contact Name
     *
     * @return contactName String
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Sets Contact Name
     *
     * @param contactName String
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
}





