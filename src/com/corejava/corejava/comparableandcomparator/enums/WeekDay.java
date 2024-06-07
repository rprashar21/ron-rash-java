package com.corejava.corejava.comparableandcomparator.enums;

public enum WeekDay {

    SUNDAY(0),
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURS(4),
    FRI(5),
    SAT(6);

    private int code;

    WeekDay(int code) {
        this.code = code;
    }

    private int getCode() {
        return this.code;
    }
}
