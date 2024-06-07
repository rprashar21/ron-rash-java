package com.corejava.java8.streams.streamsApi;

import static java.util.Arrays.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class StreamsSorting {

    public static void main(String[] args) {

        StreamsSorting streamsSorting = new StreamsSorting();
        streamsSorting.sorts();

        // sort the departmen names in the Department objec
        // here we are able to sort a list within becoz it is a list what if it is a set
        List<Department> departmentList = asList(new Department("1", asList("b","c","a")),new Department("2",asList("z","x","y")));

        departmentList.forEach(department -> Collections.sort(department.getNames()));

        departmentList.forEach(department -> System.out.println(department));

       Set<String> stringSet = new HashSet<>(Arrays.asList("b","a"));
        Set<String> stringSet2 = new HashSet<>(Arrays.asList("z","x"));
        List<Department2> departmentList2 = asList(new Department2("1",stringSet ),new Department2("2",stringSet2));
        // we will not be able to sort if it is a set
        departmentList2.forEach(department2 -> {
            Set<String> set = new TreeSet<>();
            if(department2.getNames()!=null || !department2.getNames().isEmpty())
            {
                // this will be sorted now
                set = new TreeSet<>(department2.getNames());
            }
            department2.setNames(set);
        });

        departmentList2.forEach(department -> System.out.println(department));
    }

    public void sorts()
    {
      String [] ar ={"b","a","c"};
        String [] a ={"z","y","x"};
        Set<String> stringList = new HashSet<>(List.of(a));
        Set<String> stringList2 = new HashSet<>(List.of(ar));
        ResultPropmt resultPropmt1 = new ResultPropmt(Type.FIXL,stringList );
        ResultPropmt resultPropmt2 = new ResultPropmt(Type.FIXL,stringList2 );


        List<ResultPropmt> resultPropmts = asList(resultPropmt1,resultPropmt2);

        ResultDefinition resultDefinition = new ResultDefinition("123",resultPropmts);

      resultDefinition.getResultPropmts().stream()
                .flatMap(resultPropmt -> resultPropmt.getFixedList().stream())
                .sorted();



        // if this would have been a list we can sort this
        System.out.println(resultDefinition);

    }
}

enum Type{
    FIXL;
        }
class ResultDefinition{

  private   String id;
  private   List<ResultPropmt> resultPropmts;

    public ResultDefinition(final String id, final List<ResultPropmt> resultPropmts) {
        this.id = id;
        this.resultPropmts = resultPropmts;
    }

    public String getId() {
        return id;
    }

    public List<ResultPropmt> getResultPropmts() {
        return resultPropmts;
    }
}

class ResultPropmt {
    Type type;
    Set<String> fixedList;


    public ResultPropmt(final Type type, final Set<String> fixedList) {
        this.type = type;
        this.fixedList = fixedList;
    }

    public Type getType() {
        return type;
    }

    public Set<String> getFixedList() {
        return fixedList;
    }
}

class Department{
    private String id;
    private List<String> names;

    public Department(final String id, final List<String> names) {
        this.id = id;
        this.names = names;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(final List<String> names) {
        this.names = names;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id='" + id + '\'' +
                ", names=" + names +
                '}';
    }
}
class Department2{
    private String id;
    private Set<String> names;

    public Department2(final String id, final Set<String> names) {
        this.id = id;
        this.names = names;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public Set<String> getNames() {
        return names;
    }

    public void setNames(final Set<String> names) {
        this.names = names;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id='" + id + '\'' +
                ", names=" + names +
                '}';
    }
}