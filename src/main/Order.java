package main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Order extends JFrame {

	static DecimalFormat priceformatter = new DecimalFormat("#0.00");
	DecimalFormat discountnumber = new DecimalFormat("#0");

	private JPanel contentPane;
	public JLabel lblNewLabel_2;

	static double listpricecust = 0;
	static double finalprice = 0;
	static private JLabel titletotalprice;
	static private JLabel totalpricedisplay;
	static PaymentMain paymentframe = null;

	static boolean regularcustomer = false;
	
	//creat frame
	static public void calctotalprice(double totalprice) {
		listpricecust = totalprice;
		if(regularcustomer == true) {
			totalprice = totalprice - (totalprice*Main.getdiscountvalue());
		}
		totalpricedisplay.setText("RM " + priceformatter.format(totalprice));
		finalprice = totalprice;
	}
	
	private boolean containsOrderId(final String orderid) {
		return Main.getcustomer().stream().filter(cust -> cust.getorderid().equals(orderid)).findFirst().isPresent();
	}
	
	static public void setpaymentframenull() {
		paymentframe = null;
	}

	public Order(String orderid) throws IOException {
		ItemSelect itemselector = new ItemSelect(orderid);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				itemselector.dispose();
				CashierMain.getbuttoncreate().setEnabled(true);
			}

			@Override
			public void windowClosing(WindowEvent e) {
				String selectorbutton[] = { "Yes", "No" };
				int PromptResult = JOptionPane.showOptionDialog(null,
						"Are you sure you want to exit?. This order will be discarded.", "Exit Order Window",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, selectorbutton,
						selectorbutton[1]);
				if (PromptResult == JOptionPane.YES_OPTION) {
					Main.getorders().removeIf(Orders -> Orders.getorderid().equals(orderid));
					Main.getitems().removeIf(Items -> Items.getorderid().equals(orderid));
					Main.getcustomer().removeIf(Customer -> Customer.getorderid().equals(orderid));
					if(paymentframe != null) {						
						paymentframe.dispose();
						paymentframe = null;
					}
					listpricecust = 0;
					finalprice = 0;
					regularcustomer = false;
					CashierMain.setorderframenull();
					dispose();
					
				}
			}
		});

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Windows".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		setTitle(Main.getappname());
		setIconImage(Toolkit.getDefaultToolkit().getImage(Order.class.getResource("/main/logo/logo.png")));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1023, 587);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 153, 153));
		contentPane.setBorder(null);
		setContentPane(contentPane);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 204, 153));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(204, 204, 204));

		JTextField custnamefield = new JTextField();
		custnamefield.setColumns(10);

		JTextField phonenofield = new JTextField();
		phonenofield.setColumns(10);

		JTextArea addressfield = new JTextArea();
		addressfield.setFont(new Font("Tahoma", Font.PLAIN, 11));

		JCheckBox regularcustomercheck = new JCheckBox("Yes");
		regularcustomercheck.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (regularcustomercheck.isSelected()) {
					regularcustomer = true;
				} else {
					regularcustomer = false;
				}

				if (regularcustomer == true) {
					titletotalprice.setText("Total Price with discount "
							+ discountnumber.format((Main.getdiscountvalue() * 100)) + "%");
					double totalwithdiscount = listpricecust - (listpricecust * Main.getdiscountvalue());
					totalpricedisplay.setText("RM " + priceformatter.format(totalwithdiscount));
					finalprice = totalwithdiscount;
				} else {
					titletotalprice.setText("Total Price");
					totalpricedisplay.setText("RM " + priceformatter.format(listpricecust));
					finalprice = listpricecust;
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane();

		JRadioButton malevalueradio = new JRadioButton("Male");
		JRadioButton femalevalueradio = new JRadioButton("Female");
		malevalueradio.setActionCommand("Male");
		femalevalueradio.setActionCommand("Female");

		ButtonGroup genderselector = new ButtonGroup();
		genderselector.add(malevalueradio);
		genderselector.add(femalevalueradio);

		JButton btnNewButton = new JButton("Pay");
		btnNewButton.setFont(new Font("SansSerif", Font.PLAIN, 17));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String customername = custnamefield.getText();
				String phoneno = phonenofield.getText();
				String address = addressfield.getText();
				String gender = "";
				boolean regularcustomer = false;
				if (regularcustomercheck.isSelected()) {
					regularcustomer = true;
				}

				// error command
				try {
					gender = genderselector.getSelection().getActionCommand();
				} catch (Exception e1) {
					System.out.println(e1.getMessage());
				}

				// proses 
				boolean process = false;

				// if have any erorr
				boolean customernameerror = false;
				boolean phonenoerror = false;
				boolean addresserror = false;
				boolean gendererror = false;
				boolean quantityerror = false;

				// check name
				if (customername.isEmpty()) {
					customernameerror = true;
				}

				// phone number
				if (phoneno.isEmpty()) {
					phonenoerror = true;
				}

				// addres
				if (address.isEmpty()) {
					addresserror = true;
				}

				// gender
				if (gender.isEmpty()) {
					gendererror = true;
				}

				// added
				int quantitycount = 0;
				for (int i = 0; i < Main.getitems().size(); i++) {
					if (String.valueOf(Main.getitems().get(i).getorderid()).equals(orderid)) {
						quantitycount = quantitycount + Main.getitems().get(i).getquantity();
					}
				}

				// quantity item
				if (quantitycount == 0) {
					quantityerror = true;
				}

				// error massage
				if (customernameerror || phonenoerror || addresserror || gendererror || quantityerror) {
					String error = "Check your required field:";
					if (customernameerror) {
						error += "\nName is empty";
					}
					if (phonenoerror) {
						error += "\nPhone number is empty";
					}
					if (addresserror) {
						error += "\nAddress is empty";
					}
					if (gendererror) {
						error += "\nGender is empty";
					}
					if (quantityerror) {
						error += "\nItems is empty";
					}
					JOptionPane.showMessageDialog(null, error, "Error. ID: " + orderid, JOptionPane.ERROR_MESSAGE);
				} else {
					process = true;
				}

				// if all complete data save
				if (process == true) {
					boolean duplicateorderid = containsOrderId(orderid);
					if (duplicateorderid) {
					} else {						
						Main.getcustomer().add(new CustomerMain(orderid, customername, phoneno, address, gender, regularcustomer));
					}
					if(paymentframe == null) {						
						paymentframe = new PaymentMain(orderid, finalprice);
						paymentframe.setVisible(true);
					}else {
						paymentframe.setVisible(true);
					}
				}
			}
		});

		JButton btnNewButton_1 = new JButton("Show Item Selector");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				itemselector.setVisible(true);
			}
		});

		titletotalprice = new JLabel("Total Price:");
		titletotalprice.setFont(new Font("SansSerif", Font.BOLD, 16));
		titletotalprice.setForeground(Color.BLACK);

		totalpricedisplay = new JLabel("RM 0.00");
		totalpricedisplay.setFont(new Font("SansSerif", Font.BOLD, 19));
		
				JLabel lblNewLabel_1 = new JLabel("Customer Name");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
				JLabel lblNewLabel_3 = new JLabel("Phone Number");
				lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
				JLabel lblNewLabel = new JLabel("Address");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
				JLabel lblNewLabel_6 = new JLabel("Gender");
				lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
				JLabel lblNewLabel_4 = new JLabel("Items");
				lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
				JLabel lblNewLabel_5 = new JLabel("Regular Customer");
				lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));

		scrollPane.setRowHeaderView(addressfield);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 1009, Short.MAX_VALUE)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(139, Short.MAX_VALUE)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 750, GroupLayout.PREFERRED_SIZE)
					.addGap(120))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 408, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(52, Short.MAX_VALUE))
		);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(69)
							.addComponent(lblNewLabel_1))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(69)
							.addComponent(custnamefield, GroupLayout.PREFERRED_SIZE, 614, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(69)
							.addComponent(lblNewLabel_3))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(69)
							.addComponent(phonenofield, GroupLayout.PREFERRED_SIZE, 623, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(205)
							.addComponent(titletotalprice)
							.addGap(30)
							.addComponent(totalpricedisplay))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(256)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(69)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 623, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel)))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGap(69)
									.addComponent(lblNewLabel_6)
									.addGap(183)
									.addComponent(lblNewLabel_4))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGap(79)
									.addComponent(malevalueradio)
									.addGap(12)
									.addComponent(femalevalueradio)
									.addGap(84)
									.addComponent(btnNewButton_1)))
							.addGap(74)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(regularcustomercheck)
								.addComponent(lblNewLabel_5))))
					.addGap(58))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(25)
					.addComponent(lblNewLabel_1)
					.addGap(8)
					.addComponent(custnamefield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblNewLabel_3)
					.addGap(11)
					.addComponent(phonenofield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_6)
						.addComponent(lblNewLabel_4)
						.addComponent(lblNewLabel_5))
					.addGap(7)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(malevalueradio)
								.addComponent(femalevalueradio)
								.addComponent(btnNewButton_1))
							.addGap(38)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(titletotalprice)
								.addComponent(totalpricedisplay))
							.addGap(11)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addComponent(regularcustomercheck)))
		);
		panel_1.setLayout(gl_panel_1);

		lblNewLabel_2 = new JLabel("New order for ID: " + orderid);
		lblNewLabel_2.setIcon(new ImageIcon(Order.class.getResource("/main/logo/contract.png")));
		lblNewLabel_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(303)
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 369, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(337, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(20)
					.addComponent(lblNewLabel_2)
					.addContainerGap(20, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
}
