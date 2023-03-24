package com.example.jpa.common;

import lombok.Getter;

@Getter
public enum Status {

    SUCCESSFULLY(1, "Successfully!"),
    Error(2, "Failed roi"),
    LOL(3, "lol");

    private int code;
    private String message;

    Status(Status status) {
        this.code = status.code;
        this.message = status.message;
    }

    Status(int i, String s) {
        this.code = i;
        this.message = s;
    }

    public static Status findStatusByCode(int code) {
        for(Status status : Status.values()) {
            if(status.getCode() == code ) {
                return status;
            }
        }
        return null;
    }
}
