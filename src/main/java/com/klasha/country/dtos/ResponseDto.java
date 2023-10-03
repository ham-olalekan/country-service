package com.klasha.country.dtos;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.klasha.country.enums.ResponseStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseDto<T> {
    private Status status;
    private T data;
    private String message;

    @JsonIgnore
    private Object[] messageArgs;

    public enum Status {
        SUCCESS, ERROR
    }

    public static <T> ResponseDto<T> wrapSuccessResult(T data, String message) {
        ResponseDto<T> response = new ResponseDto<>();
        response.setData(data);
        response.setMessage(message);
        response.setStatus(Status.SUCCESS);
        return response;
    }

    public static <T> ResponseDto<T> wrapErrorResult(String message) {
        ResponseDto<T> response = new ResponseDto<>();
        response.setStatus(Status.ERROR);
        response.setMessage(message);
        return response;
    }
}