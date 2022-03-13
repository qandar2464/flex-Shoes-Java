package main;
//payment method,total price,and price that customer pay here 
public class PaymentClass extends OrderItem{
	private String paymenttype;  
	private double totalprice; 
	private double custpay;  
	
	public PaymentClass(String orderid, String paymenttype, double totalprice, double custpay) {
		this.orderid = orderid;
		this.paymenttype = paymenttype;
		this.totalprice = totalprice;
		this.custpay = custpay;
	}
	
	public String getorderid() {
		return orderid;
	}
	
	public String getpaymenttype() {
		return paymenttype;
	}
	
	public double gettotalprice() {
		return totalprice;
	}
	
	public double getcustpay() {
		return custpay;
	}
}
