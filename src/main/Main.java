package main;

import java.util.ArrayList;

public class Main {
	static ArrayList<CustomerMain> customers = new ArrayList<CustomerMain>();
	static ArrayList<Items> listitems = new ArrayList<Items>();
	static ArrayList<OrderItem> listorders = new ArrayList<OrderItem>();
	static ArrayList<PaymentClass> listpayment = new ArrayList<PaymentClass>();
	
	//configuration
	static private double discount = 0.20;
	static private String appname = "Flex Shoes";
	static private String contributor = "Hafiz, Nuqman, Luqman, Faiz";
	
	static public String getappname() {
		return appname;
	}
	
	static public String getcontributor() {
		return contributor;
	}
	
	static public double getdiscountvalue() {
		return discount;
	}

	public static ArrayList<CustomerMain> getcustomer() {
		return customers;
	}

	public static ArrayList<Items> getitems() {
		return listitems;
	}
	
	public static ArrayList<OrderItem> getorders() {
		return listorders;
	}
	
	public static ArrayList<PaymentClass> getpayment(){
		return listpayment;
	}

	public static void main(String[] args) {
		Welcome welcomeframe;
		CashierMain cashier;

		try {
			welcomeframe = new Welcome();
			cashier = new CashierMain();

			welcomeframe.setVisible(true);
			Thread.sleep(2000);
			welcomeframe.progressBar.setVisible(true);
			try {
				for (int i = 0; i <= 100; i += 4) {
					Thread.sleep(20);
					welcomeframe.progressBar.setValue(i);
					welcomeframe.lblNewLabel_3.setText("Welcome! Starting up " + i + "%");
				}
				Thread.sleep(1000);
				welcomeframe.setVisible(false);
				cashier.setVisible(true);
			} catch (Exception e) {
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}