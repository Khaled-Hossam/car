import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MyClass {

    // it is better to use LocalDate, it is a newer api for dates and includes more features like time addition/subtraction   
    private Date m_time;
    private String m_name;
    // here we need to define types to avoid compile time errors (for example if user added non long value to the array)
    // also if we didn't need to save big numbers we better use int instead of long to save memory
    private List<Long> m_numbers;
    private List<String> m_strings;

    // here there is a problem if type is list, user may pass unmodified list (for example by using List.of) 
    // and then when we try removeString() it will fail, so one solution is to use ArrayList instead in the constructor and in setter
    // to be able to modify the list later
    public MyClass(Date time, String name, List<Long> numbers, ArrayList<String> strings) {
        m_time = time;
        m_name = name;
        m_numbers = numbers;
        m_strings = strings;
    }

    public boolean equals(Object obj) {
        if (obj instanceof MyClass) {
            return m_name.equals( ((MyClass) obj).m_name );
        }
        return false;
    }
    public String toString() {
        String out = m_name;
        for (long item: m_numbers) {
            out += " " + item;
        }
        return out;
    }

    // this method by current implemention will not work if list is unmodified, so one way to solve the problem as said in the constructor we may change
    // the type to ArrayList<String> to make sure we can modifiy it 
    public void removeString(String str) {
        // if we need to remove only the first occuerence we can just use remove() method in the list instead of looping
        for (int i = 0; i < m_strings.size(); i++) {
            if (m_strings.get(i).equals(str)) {
                m_strings.remove(i);
            }
        }
    }

    // - Confidential -
    // i don't know if i understand the context of confidential here correctly, but if i understand right we need to make this methods private 
    public boolean containsNumber(long number) {
        for (long num: m_numbers) {
            if (num == number) {
                return true;
            }
        }
        return false;
    }

    public boolean isHistoric() {
        return m_time.before(new Date());
    }
}