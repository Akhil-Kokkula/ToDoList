import java.util.HashMap;

public class MainTest {
    public static void main(String[] args) {
        ListInfo l1 = new ListInfo(null, null, 2018, 2, 20, 0, false, null);
        ListInfo l2 = new ListInfo(null, null, 2016, 2, 20, 0, false, null);
        ListInfo l3 = new ListInfo(null, null, 2016, 2, 21, 0, false, null);
        Organize o1 = new Organize(l1);
        o1.printInfo();
        o1.addOrganize(l2);
        o1.printInfo();
        o1.addOrganize(l3);
        o1.printInfo();
        String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        HashMap<String, Integer> monthNum = new HashMap<String, Integer>();
        for (int i = 1; i < 13; i++) {
             monthNum.put(months[i-1], i);
        }
        int monthNumSelected = monthNum.get("January");
        System.out.println(monthNumSelected);
        System.out.println(o1.getList().size());
        o1.clearList();
        System.out.println(o1.getList().size());

    }
}