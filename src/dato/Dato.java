
package src.dato;

public class Dato {

    private int date;
    private int year;
    private int month;
    private int day;
    private boolean leap;
    private int dInYear;
    private boolean valid;

    // Constructor
    public Dato() {
    }

    public Dato(int date) { //  * Takes date input
        this.date = date;
    }

    public Dato(int year, int month, int day) {
        this.year = year * 10000;
        this.month = month * 100;
        this.day = day;
        
        date = year*10000 + month * 100 + day;
    }

    public int getDato() {
        return date;
    }

    public int getYear() {   // * should return YYYY
        return date / 10000;
    }

    public int getMonth() {    // * should return M
        return date / 100 % 100;
    }

    public int getDay() {   // * should return D
        return date % 100;
    }

    public int getQuarter() {   // * SAOL method for returning Q
        return ((getMonth() - 1) / 3) + 1;
    }

    public int altGetQuarter() {   // * alternate way of returning Q
        int quarter = 0;
    
        if(month >= 1 && month <= 3) {
            quarter = 1;
        } else if (month >= 4 && month <= 6) {
            quarter = 2;
        } else if (month >= 7 && month <= 9) {
            quarter = 3;
        } else if (month >= 10 && month <= 12){
            quarter = 4;
        }
        return quarter;
    }

    public void setDate(int newDate) {
        date = newDate;
        System.out.println("New date: " + date);
    }

    public void setYear(int newYear) {
        String d = String.valueOf(getDay());
        String m = String.valueOf(getMonth());
        String y = String.valueOf(newYear);

        date = Integer.parseInt(y + m + d);

        System.out.println("New year: " + y);
    }

    public void setMonth(int newMonth) {
        String d = String.valueOf(getDay());
        String m = String.valueOf(newMonth);
        String y = String.valueOf(getYear());

        date = Integer.parseInt(y + m + d);
        
        System.out.println("New month: " + m);
    }

    public void setDay(int newDay) {
        String d = String.valueOf(newDay);
        String m = String.valueOf(getMonth());
        String y = String.valueOf(getYear());

        date = Integer.parseInt(y + m + d);
        
        System.out.println("New day: " + d);
    }

    // Selektion
    public boolean leapYear() {    //  * insert year
        if((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {   // * Must be divisible by 4 and 400 and not by 100
            leap = true;
        } else {
            leap = false;
        }
        return leap;
    }

    public boolean validDate() {
        day = getDay();
        month = getMonth();
        year = getYear();
        if (day == 0 || month == 0 || month > 12) {
            return false;
        }
        if(date >= 17000301 && date <= 99991231) { //  * After georgian calendar but before end of year 9999
            if(month == 1 
            || month == 3 
            || month == 5 
            || month == 7 
            || month == 8 
            || month == 10 
            || month == 12) {
                if (day > 31) { //  * If month is long and day is greater than 31, its an invalid date
                    valid = false;
                } else {
                    valid = true;
                }
            } else if (month == 2) {
                if ((leap && day > 29 || !leap && day > 28)) {
                    valid = false;
                } else {
                    valid = true;
                }
            } else {
                if (day > 30) {
                    valid = false;
                } else {
                    valid = true;
                }
            }
        } else {
            valid = false;
        }
        return valid;
    }

    // Erklæring, iteration og selektion
    public int dayInYear() {
        int dInMCum= 0;
        
        for(int m = 1; m < month; m++) {
            if(m == 2) {
                if (leap) {
                    dInMCum += 29;
                } else {
                    dInMCum += 28;
                }
            }
            else if(m % 2 == 0) {
                if (m < 8) {
                    dInMCum += 30;
                } else {
                    dInMCum += 31;
                }
            }
            else {
                if (m < 8) {
                    dInMCum += 31;
                } else {
                    dInMCum += 30;
                }
            }
        }
        dInYear = dInMCum + day;
        return dInYear;
    }

    public int remainingDaysInYear() {
        if (leap) {
            return 366 - dInYear; 
        } else {
            return 365 - dInYear;
        }
    }

    public void dateAddOne() {
        date++;
        while (!validDate()) {
            date++;
        }
    }

    public void dateSubOne() {
        date--;
        while (!validDate()) {
            date--;
        }
    }

    public void dateAddX(int x) {
        for (int i = 1; i <= x; i++) {
            dateAddOne();
        }
        System.out.println("New date = " + getDato());
    }

    public int dateSubDate(Dato enDato) {
        int daysBetween = 0;

        Dato tempDate = new Dato(date);

        if (tempDate.date < enDato.date) {
            while(tempDate.date != enDato.date) {
                tempDate.dateAddOne();
                daysBetween++;
            }
        }
        else
        if (tempDate.date > enDato.date) {
            while(tempDate.date != enDato.date) {
                tempDate.dateSubOne();
                daysBetween--;
            }
        }
        if (daysBetween < 0) {
            return daysBetween * -1;
        } else {
            return daysBetween;
        }
    }

    public String dayOfWeek(int d) {
        if (month == 1 || month == 2) {
            month += 12;
            year--;
        }

        int k = year % 7; // * The year of the century
        int j = (int)(year / 100.0); // * the century
        int q = day;
        int m = month;
        int h = ((q + (int)((26 * (m + 1)) / 10.0) + k + (int)(k / 4.0) 
                + (int)(j / 4.0) + (5 * j)) % 7)-1;

        //Display the name of the day of the week
        switch (h) {
            case 0:
            return "Saturday";

            case 1:
            return "Sunday";

            case 2:
            return "Monday";

            case 3:
            return "Tuesday";

            case 4:
            return "Wednesday";

            case 5:
            return "Thursday";

            case 6:
            return "Friday";

            default:
            return "null";
        }
    }

    public void print(Dato subdate) {  // * Input which date to subtract
        System.out.println();

        System.out.println("Input: " + getDato());
        System.out.println("Valid date: " + validDate());

        if(validDate()) {
            System.out.println("Date: " + getDay() + "/" + getMonth() + "/" + getYear());
            System.out.println("Day: " + getDay());
            System.out.println("Month: " + getMonth());
            System.out.println("Year: " + getYear());
            System.out.println("Quarter: " + getQuarter());
            System.out.println("Leap year: " + leapYear());
            System.out.println("Day in year: " + dayInYear());
            System.out.println("Remaining days in year: " + remainingDaysInYear());
            System.out.println("Days between " + getDay() + "/" + getMonth() + "/" + getYear() + " and " + subdate.getDay() + "/" + subdate.getMonth() + "/" + subdate.getYear() + " = " + dateSubDate(subdate));
            System.out.println("Day of the week: " + dayOfWeek(date));
        } else {
            System.out.println("Date is invalid - Can not process");
        }

        System.out.println();
        System.out.println("=======");
    }
}