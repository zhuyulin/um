package um.exception;

/**
 * Created by Yuleen on 2016/12/25.
 */
public class ServiceException extends Exception {

    private String            errorCode;

    public ServiceException(String message){
        super(message);
    }

    public ServiceException(String errorCode, String message){
        super(message);
        this.errorCode = errorCode;
    }

    public ServiceException(String msg, Throwable cause){
        super(msg, cause);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

}
