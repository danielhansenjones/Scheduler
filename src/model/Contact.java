package model;

/**
 * This class is an object constructor used to create Contact objects and get/set their values.
 */
public class Contact {

    private int contactId;
    private String contactName;
    private String contactEmail;

    /**
     * @param contactId    Int value of Contact ID
     * @param contactName  String value of Contact Name
     * @param contactEmail String value of Contact Email
     */
    public Contact(int contactId, String contactName, String contactEmail) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
    }

    /**
     * Gets Contact ID
     *
     * @return contactId Integer value
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * Sets Contact ID
     *
     * @param contactId Integer value
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     * Gets Contact Name
     *
     * @return contactName String value
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Sets Contact Name
     *
     * @param contactName String value
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * Gets Contact Email
     *
     * @return contactEmail String value
     */
    public String getContactEmail() {
        return contactEmail;
    }

    /**
     * Sets Contact Email
     *
     * @param contactEmail String value
     */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    @Override
    public String toString() {
        return (contactName);
    }
}