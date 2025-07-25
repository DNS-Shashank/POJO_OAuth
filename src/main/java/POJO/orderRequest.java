package POJO;

import java.util.List;

public class orderRequest {

    private List<OrderDetail> orders;

public List<OrderDetail> getOrders() {
    return orders;
}

public void setOrders(List<OrderDetail> orders) {
    this.orders = orders;
}       
}
