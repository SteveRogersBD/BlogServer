package com.example.Blog.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

    private String status;
    private String message;
    private int statusCode;
    private T data;

    public static <T>ApiResponse<T> success(String message, T data)
    {
        return new ApiResponse<>("success",message,200,data);
    }

    public static <T>ApiResponse<T> error(String message)
    {
        return new ApiResponse<>("error",message,400,null);
    }

    private static <T>ApiResponse<T> success(String message,int statusCode)
    {
        return new ApiResponse<>("success",message,statusCode,null);
    }


}
