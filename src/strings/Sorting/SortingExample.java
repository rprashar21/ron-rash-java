package strings.Sorting;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class SortingExample {

    public static void main(String[] args) {

        ResultPrompt resultPrompt = new ResultPrompt();

        Set<String> fixedList = new HashSet();
        fixedList.add("To prevent offending");
        fixedList.add("For the defendant's own protection");
        fixedList.add("To ensure appearance");
        fixedList.add("To attend an interview with a legal adviser");

        resultPrompt.setFixedList(new LinkedHashSet<>(fixedList));




        System.out.println(resultPrompt.getFixedList());
    }




}
class ResultPrompt{

    private Set<String> fixedList;

    public Set<String> getFixedList() {
        return fixedList;
    }

    public void setFixedList(final Set<String> fixedList) {
        this.fixedList = fixedList;
    }
}