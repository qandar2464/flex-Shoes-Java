package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PaymentMain extends JFrame {
	
	static DecimalFormat priceformatter = new DecimalFormat("#0.00");

	private JPanel contentPane;
	private JTextField custpayfield;

	//create frame
	public PaymentMain(String orderid, double payment) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				String selectorbutton[] = {"Yes","No"};
		        int PromptResult = JOptionPane.showOptionDialog(null,"Cancel payment? You order data still keep until you close Order window. Make a payment by click Pay button on Order window","Exit Payment Window",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,selectorbutton,selectorbutton[1]);
		        if(PromptResult==JOptionPane.YES_OPTION)
		        {
		            dispose();
		            Order.setpaymentframenull();
		        }
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(PaymentMain.class.getResource("/main/logo/logo.png")));
		setTitle(Main.getappname());
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 844, 479);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 153, 153));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 830, 68);
		panel.setBorder(null);
		panel.setBackground(Color.WHITE);
		
		JLabel lblPayment = new JLabel("Order");
		lblPayment.setIcon(new ImageIcon(PaymentMain.class.getResource("/main/logo/mobile-payment.png")));
		lblPayment.setForeground(new Color(0, 0, 0));
		lblPayment.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 835, Short.MAX_VALUE)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(20)
					.addComponent(lblPayment)
					.addContainerGap(718, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 68, Short.MAX_VALUE)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(15, Short.MAX_VALUE)
					.addComponent(lblPayment)
					.addGap(21))
		);
		panel.setLayout(gl_panel);
		
		ButtonGroup paymenttypeselector = new ButtonGroup();
		contentPane.setLayout(null);
		
		
		JButton btnNewButton = new JButton("Pay");
		btnNewButton.setBounds(599, 370, 164, 39);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				// state process
				boolean process = false;
				
				// state error
				boolean custpayvalueerror = false;
				boolean paymenttypeerror = false;
				boolean insufficientbalance = false;
				
				double custpayvalue = 0;
				String paymenttype = null;
				
				//get payment method
				try {
					paymenttype = paymenttypeselector.getSelection().getActionCommand();
				}catch(Exception e1) {
					paymenttypeerror = true;
					System.out.println("No Value Selected: " + e1.getMessage());
				}
				
				//get pay value
				try {
					custpayvalue = Double.parseDouble(custpayfield.getText());
					if(custpayvalue < payment) {
						insufficientbalance = true;
					}
				}catch(Exception e1) {
					custpayvalueerror = true;
					System.out.println("INVALID PAY VALUE: " + e1.getMessage());
				}
				
				// massage error
				if (custpayvalueerror || paymenttypeerror || insufficientbalance) {
					String error = "Payment unable to proceed:";
					if (custpayvalueerror) {
						error += "\nInvalid pay value";
					}
					if (paymenttypeerror) {
						error += "\nPayment type not selected";
					}
					if (insufficientbalance) {
						error += "\nInsufficient balance";
					}
					JOptionPane.showMessageDialog(null, error, "Error Payment. ID: " + orderid, JOptionPane.ERROR_MESSAGE);
				} else {
					process = true;
				}
				
				//payment prosess
				if(process == true) {
					Main.getpayment().add(new PaymentClass(orderid, paymenttype, payment, custpayvalue));
					ReceiptCustomer receiptframe = new ReceiptCustomer(orderid);
					receiptframe.setVisible(true);
					CashierMain.getorderframe().dispose();
					CashierMain.showdata();
					Order.listpricecust = 0;
					Order.finalprice = 0;
					Order.regularcustomer = false;
					dispose();
				}
			}
		});
		btnNewButton.setBackground(new Color(111, 255, 233));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		contentPane.add(btnNewButton);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setForeground(Color.WHITE);
		panel_2_1.setBounds(416, 79, 347, 83);
		panel_2_1.setBackground(Color.WHITE);
		
		JLabel lblNewLabel_1_1 = new JLabel("Amount pay (RM)");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.ITALIC, 14));
		
		custpayfield = new JTextField();
		custpayfield.setFont(new Font("SansSerif", Font.PLAIN, 16));
		custpayfield.setColumns(10);
		GroupLayout gl_panel_2_1 = new GroupLayout(panel_2_1);
		gl_panel_2_1.setHorizontalGroup(
			gl_panel_2_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1_1, GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
						.addComponent(custpayfield, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_2_1.setVerticalGroup(
			gl_panel_2_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1_1)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(custpayfield, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel_2_1.setLayout(gl_panel_2_1);
		contentPane.add(panel_2_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(Color.WHITE);
		panel_1.setBounds(215, 173, 317, 121);
		panel_1.setBackground(Color.WHITE);
		
		
		JRadioButton creditcardtype = new JRadioButton("Credit Card");
		creditcardtype.setBackground(new Color(200, 213, 185));
		JRadioButton cashtype = new JRadioButton("Cash");
		cashtype.setBackground(new Color(200, 213, 185));
		JRadioButton debittype = new JRadioButton("Debit");
		debittype.setBackground(new Color(200, 213, 185));
		
		cashtype.setActionCommand("Cash");
		debittype.setActionCommand("Debit");
		creditcardtype.setActionCommand("Credit Card");
		paymenttypeselector.add(cashtype);
		paymenttypeselector.add(debittype);
		paymenttypeselector.add(creditcardtype);
		
		JLabel lblNewLabel = new JLabel("Choose Payment Type");
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 13));
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(creditcardtype)
						.addComponent(debittype)
						.addComponent(lblNewLabel)
						.addComponent(cashtype))
					.addContainerGap(198, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(cashtype)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(debittype)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(creditcardtype)
					.addContainerGap(59, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		contentPane.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setForeground(Color.WHITE);
		panel_2.setBounds(68, 79, 317, 83);
		panel_2.setBackground(Color.WHITE);
		
		JLabel lblNewLabel_1 = new JLabel("amount");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 14));
		
		JLabel pricedisplay = new JLabel("RM " + priceformatter.format(payment));
		pricedisplay.setFont(new Font("SansSerif", Font.PLAIN, 18));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
						.addComponent(pricedisplay, GroupLayout.PREFERRED_SIZE, 356, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pricedisplay, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		contentPane.add(panel_2);
		contentPane.add(panel);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(PaymentMain.class.getResource("/main/logo/background.png")));
		lblNewLabel_2.setBounds(0, 65, 830, 377);
		contentPane.add(lblNewLabel_2);
	}
}
