package com.corejava.corejava.datatype.enums;

public enum HttpCodes {

    OK(200),
    NOT_FOUND(404),
    INTERNAL_SERVER_ERROR_New,
    INTERNAL_SERVER_ERROR;

    private final int code;

    HttpCodes(){
        this.code = 500; // this value will be set to  INTERNAL_SERVER_ERROR
    }

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

    public static void main(String[] args) {
        HttpCodes[] array = HttpCodes.values();

        for(HttpCodes httpcodes:array){
            System.out.println(httpcodes.name()+ " = "+httpcodes.getCode());
        }
    }
}