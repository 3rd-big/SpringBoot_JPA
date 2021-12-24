package net.bulletinboard.domain;

public class Result {
    private boolean vaild;

    private String errorMessage;

    public Result(boolean vaild, String errorMessage) {
        this.vaild = vaild;
        this.errorMessage = errorMessage;
    }

    public boolean isValid() {
        return vaild;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public static Result ok() {
        return new Result(true, null);
    }

    public static Result fail(String errorMessage) {
        return new Result(false, errorMessage);
    }
}
