package model;


public class Report extends Appointment {

    private String month;
    private String amount;
    private String type;

    /**
     * A constructor used to create an Appointment object
     * @param month
     * @param type
     * @param amount
     */
    public Report(String month, String type, String amount) {
        super();
        this.month = month;
        this.type = type;
        this.amount = amount;
    }

    public String getMonth() {
        return month;
    }

    public String getType() {
        return type;
    }


    public String getAmount() {
        return amount;
    }

}