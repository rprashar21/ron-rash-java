package com.corejava.strings;

public class StringFormat {

    private static final String SUBJECT = "Amend & Reshare";
    public static void main(String[] args) {

      String caseApplication = "DOH Application,Application X,Application Y";
      String urn ="12349CRUT";
        build(caseApplication,urn);
    }

    private static String build(final String caseApplication, final String urn) {

        String format = String.format("%s %s %s", SUBJECT, urn, caseApplication.replace(",", "/"));
        System.out.println(format);
        return format;
    }
}
