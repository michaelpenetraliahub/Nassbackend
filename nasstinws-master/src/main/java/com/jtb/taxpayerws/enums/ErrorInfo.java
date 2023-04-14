package com.jtb.taxpayerws.enums;

public enum ErrorInfo {
    RECORD_DOES_NOT_EXIST("Record does not exist"),
    INTERNAL_SERVER_ERROR("Error occurred while processing your request please try again later"),
    UNAUTHORIZED_RESOURCE("you are not authorized to access this resource"),
    EXPIRED_TOKEN("expired token"),
    COULD_NOT_VERIFY_CREDENTIALS("We could not verify your credentials. Please check and try again."),
    NO_TOKEN_PROVIDED("no token provided"),
    INVALID_CREDENTIALS("invalid authorization credentials"),
    UNABLE_TO_SEND_MAIL("an error occurred, failed to send mail"),
    INVALID_PASSWORD("invalid password. try again"),
    PASSWORDS_DO_NOT_MATCH("passwords do not match. please check and try again"),
    INVALID_DATE("invalid date format"),
    INVALID_DATE_INTERVAL("invalid date interval");

    private String errorMessage;

    ErrorInfo(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
