package org.example.currencyconverter.util;

public class LastestDateApiResponse {

    private String calendarDate;

    private boolean swedishBankday;

    private int weekYear;

    private int weekNumber;

    private int quarterNumber;

    private boolean ultimo;

    public LastestDateApiResponse(String calendarDate, boolean swedishBankday, int weekYear, int weekNumber, int quarterNumber, boolean ultimo) {
        this.calendarDate = calendarDate;
        this.swedishBankday = swedishBankday;
        this.weekYear = weekYear;
        this.weekNumber = weekNumber;
        this.quarterNumber = quarterNumber;
        this.ultimo = ultimo;
    }

    public LastestDateApiResponse() {
    }

    public String getCalendarDate() {
        return calendarDate;
    }

    public void setCalendarDate(String calendarDate) {
        this.calendarDate = calendarDate;
    }

    public boolean isSwedishBankday() {
        return swedishBankday;
    }

    public void setSwedishBankday(boolean swedishBankday) {
        this.swedishBankday = swedishBankday;
    }

    public int getWeekYear() {
        return weekYear;
    }

    public void setWeekYear(int weekYear) {
        this.weekYear = weekYear;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }

    public int getQuarterNumber() {
        return quarterNumber;
    }

    public void setQuarterNumber(int quarterNumber) {
        this.quarterNumber = quarterNumber;
    }

    public boolean isUltimo() {
        return ultimo;
    }

    public void setUltimo(boolean ultimo) {
        this.ultimo = ultimo;
    }


}
