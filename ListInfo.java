import java.util.Comparator;

public class ListInfo{
    
    private String task;
    private String description;
    private int year;
    private int month;
    private int day;
    private int important;
    private boolean isSet;
    private String displayImportant;

    public ListInfo (String myTask, String myDecscription, int myYear, int myMonth, int myDay, int myImportant, boolean myIsSet, String myDisImportant) {
        this.task = myTask;
        this.description = myDecscription;
        this.year = myYear;
        this.month = myMonth;
        this.day = myDay;
        this.important = myImportant; 
        this.isSet = myIsSet;
        this.displayImportant = myDisImportant;
    }

    public ListInfo()
    {
        this.task = "";
        this.description = "";
        this.year = 0;
        this.month = 0;
        this.day = 0;
        this.important = 0; 
        this.isSet = false;
    }

    public void setIsSet(boolean userIsSet) {
        this.isSet = userIsSet;
    }

    public String getDisImportant() {
        return this.displayImportant;
    }

    public String getTask() {
        return this.task;
    }

    public String getDescription() {
        return this.description;
    }

    public int getYear() {
        return this.year;
    }
    
    public int getMonth() {
        return this.month;
    }

    public boolean getIsSet() {
        return this.isSet;
    }

    public int getDay() {
        return this.day;
    }

    public int getImportant () {
        return this.important;
    }

    public void setYear(int userYear) {
        this.year = userYear;
    }

    public void setMonth(int userMonth) {
        this.month = userMonth;
    }
    
    public void setDay(int userDay) {
        this.day = userDay;
    }

    public void setImportant(int userImportant) {
        this.important = userImportant;
    }

    public void setDisImportant(String userDisImportant) {
        this.displayImportant = userDisImportant;
    }

    public void setAll(String userTask, String userDecscription, int userYear, int userMonth, int userDay, int userImportant) {
        this.task = userTask;
        this.description = userDecscription;
        this.year = userYear;
        this.month = userMonth;
        this.day = userDay;
        this.important = userImportant;
    }
    public static Comparator<ListInfo> yearComparator = new Comparator<ListInfo>() {
        public int compare(ListInfo l1, ListInfo l2) {
            int year1 = l1.getYear();
            int year2 = l2.getYear();

            return year1-year2;
        } 
    };

    
 
}