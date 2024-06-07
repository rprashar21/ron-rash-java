package com.corejava.corejava.datatype;

class CustomArrayList{
    int[] array;
    int size, lastIndex;

    //Constructor marking initial values of the ArrayList.
    CustomArrayList(){
        lastIndex = 0;
        size = 2;
        array = new int[2];
    }

    //Private method that makes in dynamic growing nature of the ArrayList.
    private void dynamic(){
        size *= 2;
        int[] newArray = new int[size];
        for(int i = 0; i < lastIndex; i++)
            newArray[i] = array[i];
        array = newArray;
    }

    //Method used to add the element in the list.
    public void add(int value){
        //If the size of the ArrayList exceeded with the current then call the dynamic method.
        if(lastIndex == size) dynamic();
        array[lastIndex++] = value;
    }

    //Method that removes the element at particular index.
    public void remove(int index){
        for(int i = index; i < lastIndex; i++){
            array[i] = array[i+1];
        }
        lastIndex--;
    }

    //Method that returns the element at particular index.
    public int get(int index){
        return array[index];
    }

    //Overriden toString method used to print the elements of the ArrayList
    @Override
    public String toString() {
        StringBuffer temp = new StringBuffer();
        temp.append("[");
        for(int i = 0; i < lastIndex; i++) temp.append(array[i]+",");
        temp.deleteCharAt(temp.length()- 1);
        temp.append("}");
        return temp.toString();
    }
}
public class Solution {
    public static void main(String[] args) {

//Testing Methods
        CustomArrayList al = new CustomArrayList();
        for(int i = 0; i < 4; i++){
            al.add((int)(Math.random()*10));
        }
        System.out.println(al);
        al.remove(3);
        System.out.println(al.get(2));
        System.out.println(al);
    }
}
