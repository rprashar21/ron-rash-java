package com.corejava.regurlarExpression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpression {
    public static void main(String[] args) {

        String regex ="\\d..";
        String inputText ="012";

        Pattern pattern  = Pattern.compile(regex);
        Matcher matcher =pattern.matcher(inputText);

        System.out.println(matcher.matches());
    }
}
