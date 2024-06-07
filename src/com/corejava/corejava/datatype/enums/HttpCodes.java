package com.corejava.corejava.datatype.enums;

public enum HttpCodes {

    OK(200),
    NOT_FOUND(404),
    INTERNAL_SERVER_ERROR(500);

    private final int code;

    HttpCodes(int code) {
        this.code =code;
    }

    public int getCode() {
        return code;
    }
}

enum MenuOPtions{
    NEW_GAME, LOAD_GAME, SETTINGS, EXIT
}
class AbsSample{

}