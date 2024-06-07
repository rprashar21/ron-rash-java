package com.corejava.corejava.datatype.enums;

public class App {

    public static void main(String[] args) {

        // call Some webservice and the response is fine 
        int code  = HttpCodes.OK.getCode();
        System.out.println(code);

    }
}
