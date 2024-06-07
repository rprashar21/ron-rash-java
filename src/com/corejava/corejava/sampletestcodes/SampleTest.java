package com.corejava.corejava.sampletestcodes;

public class SampleTest {
    public static void main(String[] args) {
//        final boolean hello = isUserPresent("hello");
//        System.out.println(hello);
   String status="Active";
        System.out.println(isCaseStatusEligible(status));

    }

    private static boolean isCaseStatusEligible(final String status) {
        return "INACTIVE".equals(status) || "ACTIVE".equals(status);
    }
}
