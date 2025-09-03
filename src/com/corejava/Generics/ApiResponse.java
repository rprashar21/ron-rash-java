package com.corejava.Generics;

public class ApiResponse<T> {

  private T data;
  private String status;

    public ApiResponse(final T data, final String status) {
        this.status = status;
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }


    public static void main(String[] args) {
        ApiResponse<String> apiResponse = new ApiResponse<String>("Hello","200");

        // or
        ApiResponse<User> apiResponse1 = new ApiResponse<>(new User("rohit"),"201");

    }
}

class User{
    private String name;

    public User(final String name) {
        this.name = name;
    }
}
