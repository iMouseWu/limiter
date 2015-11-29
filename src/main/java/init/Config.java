package init;

/**
 * @author wuhao
 */
public class Config {
    private String method;

    private int capacity;

    private int addNum;

    private long addTimeWithMillisecond;

    private int addPeriod;

    private String errorCode;

    private String errorMessage;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getAddNum() {
        return addNum;
    }

    public void setAddNum(int addNum) {
        this.addNum = addNum;
    }

    public long getAddTimeWithMillisecond() {
        return addTimeWithMillisecond;
    }

    public void setAddTimeWithMillisecond(long addTimeWithMillisecond) {
        this.addTimeWithMillisecond = addTimeWithMillisecond;
    }

    public int getAddPeriod() {
        return addPeriod;
    }

    public void setAddPeriod(int addPeriod) {
        this.addPeriod = addPeriod;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
