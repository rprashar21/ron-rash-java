package com.corejava.corejava.comparableandcomparator.sampless;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SortingComparator {

    public static void main(String[] args) {
        //
        DefendantCase defendantCase1 = new DefendantCase(0,1,"def1");
        DefendantCase defendantCase2 = new DefendantCase(0,3,"def3");
        DefendantCase defendantCase3 = new DefendantCase(0,2,"def2");

        List<DefendantCase> defendantCaseList1 = Arrays.asList(defendantCase1,defendantCase3,defendantCase2);

        DefendantCase defendantCase4 = new DefendantCase(10,1,"def123");
        DefendantCase defendantCase5 = new DefendantCase(10,2,"def456");
        DefendantCase defendantCase6 = new DefendantCase(1,3,"def786");

        List<DefendantCase> defendantCaseList2 = Arrays.asList(defendantCase4,defendantCase5,defendantCase6);

        Case case1 = new Case("B123",defendantCaseList1);
        Case case2 = new Case("A123",defendantCaseList2);
        List<Case> caseList = Arrays.asList(case1, case2);
        NowDocument nowDocument = new NowDocument(1,caseList);

        display(nowDocument);
        System.out.println();

        caseList.sort(Comparator.comparing(Case::getRefernce));

    //    System.out.println(caseList);

        sorted(caseList);

        System.out.println("After sorting");

        display(nowDocument);

       // display()

      //  System.out.println(caseList);
    }

    private static void display(final NowDocument nowDocument) {

        List<Case> caseList = nowDocument.getCaseList();

       caseList.forEach(aCase -> {

           List<DefendantCase> defendantCaseList = aCase.getDefendantCaseList();

           defendantCaseList.forEach(defendantCase -> System.out.print(" count " +defendantCase.getCount()+" order "+ defendantCase.getOrderIndex()));
           System.out.println();

       });
    }

    private static void sorted(final List<Case> caseList) {
        caseList.forEach(newcase->{
            List<DefendantCase> defendantCaseList = newcase.getDefendantCaseList();

            defendantCaseList.sort(Comparator.comparing(DefendantCase::getCount).thenComparing(DefendantCase::getOrderIndex));

        });
    }
}

class NowDocument{
    private Integer id ;
    private List<Case> caseList;

    public NowDocument(final Integer id, final List<Case> caseList) {
        this.id = id;
        this.caseList = caseList;
    }

    public Integer getId() {
        return id;
    }

    public List<Case> getCaseList() {
        return caseList;
    }

    @Override
    public String toString() {
        return "NowDocument{" +
                "id=" + id +
                ", caseList=" + caseList +
                '}';
    }
}
class Case{
    private String refernce;
    private List<DefendantCase> defendantCaseList;

    public Case(final String refernce, final List<DefendantCase> defendantCaseList) {
        this.refernce = refernce;
        this.defendantCaseList = defendantCaseList;
    }

    public String getRefernce() {
        return refernce;
    }

    public List<DefendantCase> getDefendantCaseList() {
        return defendantCaseList;
    }

    @Override
    public String toString() {
        return "Case{" +
                "refernce='" + refernce + '\'' +
                ", defendantCaseList=" + defendantCaseList +
                '}';
    }
}

class DefendantCase{
    private Integer count;
    private Integer orderIndex;
    private String name;

    public DefendantCase(final Integer count, final Integer orderIndex, final String name) {
        this.count = count;
        this.orderIndex = orderIndex;
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return "DefendantCase{" +
                "count=" + count +
                ", orderIndex=" + orderIndex +
                ", name='" + name + '\'' +
                '}';
    }
}