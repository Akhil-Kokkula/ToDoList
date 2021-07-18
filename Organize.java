import java.util.ArrayList;
import java.util.Collections;

public class Organize {
    private ArrayList<ListInfo> lists; 

    public Organize() {
        lists = new ArrayList<ListInfo>();
    }

    public Organize (ListInfo user) {
        lists = new ArrayList<ListInfo>();
        lists.add(user);
        Collections.sort(lists, new ListInfoComparator());     
    }

    public void add(ArrayList<ListInfo> user) {
        for(int i = 0; i < user.size(); i++) {
            lists.add(user.get(i));
        }
        Collections.sort(lists, new ListInfoComparator());
    }

    public void printInfo() {
        for(int i = 0; i < lists.size(); i++) {
            System.out.println("Year:" + lists.get(i).getYear() + "Month:" + lists.get(i).getMonth() + "Day:" + lists.get(i).getDay());
        }
    }

    public void clearList() {
        ArrayList<ListInfo> empty = new ArrayList<ListInfo>();
        lists = empty;
    }

    public ArrayList<ListInfo> getList () {
        return lists;
    }


    public void addOrganize(ListInfo userInput) {
        lists.add(userInput);
        Collections.sort(lists, new ListInfoComparator());    
    }
}