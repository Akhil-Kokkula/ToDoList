import java.util.Comparator;

public class ListInfoComparator implements Comparator<ListInfo> {
    public int compare(ListInfo l1, ListInfo l2) {
        int val0 = ((Integer)l1.getImportant()).compareTo((Integer)(l2.getImportant()));
        if(val0 == 0) {
            int val1 = ((Integer)l1.getYear()).compareTo((Integer)(l2.getYear()));
            if(val1 == 0) {
                int val2 = ((Integer)l1.getMonth()).compareTo((Integer)(l2.getMonth()));
                if(val2 == 0) {
                    return ((Integer)l1.getDay()).compareTo((Integer)(l2.getDay()));
                }
                return val2;
            }
            return val1;    
        }
        return val0; 
        
    }
}