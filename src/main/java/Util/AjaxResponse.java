package Util;

/**
 * @ Author : dell on 2018/2/24.
 * Date :  Created in  11:00.   2018/2/24.
 */
public class AjaxResponse {

    private String state;

    private String message ;

    private Object result;

    private final String SUCCESS_STATE="200";

    private final String ERROR_STATE="999";

    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Object getResult() {
        return result;
    }
    public void setResult(Object result) {
        this.result = result;
    }

    /**
     * 成功提示
     * @param message
     * @param result
     */
    public void  setSuccessMessage(String message, Object result){
        this.setState(SUCCESS_STATE);
        this.setMessage(message);
        this.setResult(result);
    }

    /**
     * 失败提示
     * @param message
     * @param result
     */
    public void  setErrorMessage(String message, Object result){
        this.setState(ERROR_STATE);
        this.setMessage(message);
        this.setResult(result);
    }

}
