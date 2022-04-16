package model;
/**
 * This class is an object constructor used to create Division objects and get/set their values.
 */
public class Division {
    private int divisionId;
    private String division;
    private int countryId;

    /**
     * @param divisionId Int value of Division ID
     * @param division   String value of Division Name
     * @param countryId  Int value of Price
     */
    public Division(int divisionId, String division, int countryId) {
        this.divisionId = divisionId;
        this.division = division;
        this.countryId = countryId;
    }

    /**
     * Gets Division ID
     * @return divisionId Integer
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * Sets Division ID
     * @param divisionId Integer
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    /**
     * Gets Division
     * @return division String
     */
    public String getDivision() {
        return division;
    }

    /**
     * Sets Division
     * @param division String
     */
    public void setDivision(String division) {
        this.division = division;
    }

    /**
     * Gets Country ID
     * @return countryId Integer
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * Sets Country ID
     * @param countryId Integer value
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
}