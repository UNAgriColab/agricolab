package agricolab.JsonModel;


public class Update {
    private boolean canceled;
    private String orderId;

    public Update(boolean canceled, String orderId) {
        this.canceled = canceled;
        this.orderId = orderId;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCancel(boolean canceled) {
        this.canceled = canceled;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
