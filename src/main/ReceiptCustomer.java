package main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Toolkit;
import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ReceiptCustomer extends JFrame {
	
	DecimalFormat priceformatter = new DecimalFormat("#0.00");

	private JPanel contentPane;
	private JTable table;

	
	public ReceiptCustomer(String orderid) {
		//@SuppressWarnings("unlikely-arg-type")
		List<CustomerMain> customerdata = Main.getcustomer().stream().filter(custdata -> custdata.getorderid().equals(orderid)).collect(Collectors.toList());
		List<OrderItem> orderdata = Main.getorders().stream().filter(orderdat -> orderdat.getorderid().equals(orderid)).collect(Collectors.toList());
		List<PaymentClass> paymentdata = Main.getpayment().stream().filter(paymentdat -> paymentdat.getorderid().equals(orderid)).collect(Collectors.toList());
		List<Items> itemsdata = Main.getitems().stream().filter(itemdat -> itemdat.getorderid().equals(orderid)).collect(Collectors.toList());
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(ReceiptCustomer.class.getResource("/main/logo/logo.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 956, 795);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 153, 153));
		setContentPane(contentPane);
		setTitle(Main.getappname());
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 942, 68);
		panel.setBackground(new Color(255, 204, 153));
		
		JLabel lblNewLabel_4 = new JLabel("Customer Details");
		lblNewLabel_4.setBounds(183, 79, 140, 24);
		lblNewLabel_4.setBackground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblNewLabel_4.setForeground(Color.BLACK);
		
		JLabel lblNewLabel_4_2 = new JLabel("Order Details");
		lblNewLabel_4_2.setBounds(680, 79, 137, 24);
		lblNewLabel_4_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4_2.setForeground(Color.BLACK);
		lblNewLabel_4_2.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblNewLabel_4_2.setBackground(Color.WHITE);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(90, 314, 757, 225);
		
		JLabel lblNewLabel_4_1 = new JLabel("Total: RM" + priceformatter.format(paymentdata.get(0).gettotalprice()));
		lblNewLabel_4_1.setBounds(10, 565, 289, 26);
		lblNewLabel_4_1.setForeground(Color.BLACK);
		lblNewLabel_4_1.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblNewLabel_4_1.setBackground(Color.WHITE);
		
		JLabel custpaiddisplay = new JLabel("Customer paid: RM" + priceformatter.format(paymentdata.get(0).getcustpay()));
		custpaiddisplay.setBounds(10, 601, 598, 17);
		custpaiddisplay.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel balancedisp = new JLabel("Balance: RM" + priceformatter.format(paymentdata.get(0).getcustpay() - paymentdata.get(0).gettotalprice()));
		balancedisp.setBounds(10, 629, 598, 15);
		balancedisp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		String addressline = "<html>" + customerdata.get(0).getaddress().replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>";
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 109, 548, 206);
		panel_1.setOpaque(false);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(558, 109, 362, 206);
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setOpaque(false);
		
		JLabel lblNewLabel_1_2_2 = new JLabel("Payment Type:");
		lblNewLabel_1_2_2.setForeground(Color.BLACK);
		lblNewLabel_1_2_2.setFont(new Font("SansSerif", Font.PLAIN, 16));
		
		JLabel lblNewLabel_1_2 = new JLabel("Order Date:");
		lblNewLabel_1_2.setForeground(Color.BLACK);
		lblNewLabel_1_2.setFont(new Font("SansSerif", Font.PLAIN, 16));
		
		JLabel orderdatedisplay = new JLabel(orderdata.get(0).getdate() + " " + orderdata.get(0).getordertime());
		orderdatedisplay.setHorizontalAlignment(SwingConstants.RIGHT);
		orderdatedisplay.setForeground(Color.BLACK);
		orderdatedisplay.setFont(new Font("SansSerif", Font.PLAIN, 14));
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Order ID:");
		lblNewLabel_1_2_1.setForeground(Color.BLACK);
		lblNewLabel_1_2_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		
		JLabel orderiddisplay = new JLabel(orderid);
		orderiddisplay.setHorizontalAlignment(SwingConstants.RIGHT);
		orderiddisplay.setForeground(Color.BLACK);
		orderiddisplay.setFont(new Font("SansSerif", Font.PLAIN, 14));
		
		JLabel paymenttypedisplay = new JLabel(paymentdata.get(0).getpaymenttype());
		paymenttypedisplay.setHorizontalAlignment(SwingConstants.RIGHT);
		paymenttypedisplay.setForeground(Color.BLACK);
		paymenttypedisplay.setFont(new Font("SansSerif", Font.PLAIN, 14));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lblNewLabel_1_2)
							.addPreferredGap(ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
							.addComponent(orderdatedisplay, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lblNewLabel_1_2_1, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
							.addComponent(orderiddisplay, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lblNewLabel_1_2_2, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
							.addComponent(paymenttypedisplay, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(orderdatedisplay, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_2_1)
						.addComponent(orderiddisplay, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(paymenttypedisplay, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_2_2))
					.addContainerGap(80, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Address:");
		lblNewLabel_2_1.setForeground(Color.BLACK);
		lblNewLabel_2_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		JLabel addressdisplay = new JLabel(addressline);
		addressdisplay.setVerticalAlignment(SwingConstants.TOP);
		addressdisplay.setForeground(Color.BLACK);
		addressdisplay.setFont(new Font("SansSerif", Font.PLAIN, 14));
		
		JLabel lblNewLabel_2_2 = new JLabel("Gender:");
		lblNewLabel_2_2.setForeground(Color.BLACK);
		lblNewLabel_2_2.setFont(new Font("SansSerif", Font.PLAIN, 16));
		
		JLabel lblGendeDisp = new JLabel(customerdata.get(0).getgender());
		lblGendeDisp.setForeground(Color.BLACK);
		lblGendeDisp.setFont(new Font("SansSerif", Font.PLAIN, 14));
		
				JLabel lblNewLabel_2 = new JLabel("Phone Number:");
				lblNewLabel_2.setForeground(Color.BLACK);
				lblNewLabel_2.setFont(new Font("SansSerif", Font.PLAIN, 16));
		
		JLabel phonenodisplay = new JLabel(customerdata.get(0).getphoneno());
		phonenodisplay.setForeground(Color.BLACK);
		phonenodisplay.setFont(new Font("SansSerif", Font.PLAIN, 14));
		
				JLabel lblNewLabel_1 = new JLabel("Name:");
				lblNewLabel_1.setForeground(Color.BLACK);
				lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		
		JLabel namedisplay = new JLabel(customerdata.get(0).getname());
		namedisplay.setBackground(new Color(240, 240, 240));
		namedisplay.setForeground(Color.BLACK);
		namedisplay.setFont(new Font("SansSerif", Font.PLAIN, 14));
		
		JLabel lblNewLabel_2_2_1 = new JLabel("Regular customer:");
		lblNewLabel_2_2_1.setForeground(Color.BLACK);
		lblNewLabel_2_2_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		
		String regularcuststate = "No";
		if(customerdata.get(0).getregularcustomer()) {
			regularcuststate = "Yes";
		}
		
		JLabel displayregularcust = new JLabel(regularcuststate);
		displayregularcust.setForeground(Color.BLACK);
		displayregularcust.setFont(new Font("SansSerif", Font.PLAIN, 14));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
							.addComponent(lblNewLabel_2_1, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(addressdisplay, GroupLayout.PREFERRED_SIZE, 455, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblNewLabel_2_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblGendeDisp, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblNewLabel_2)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(phonenodisplay, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(namedisplay, GroupLayout.PREFERRED_SIZE, 333, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblNewLabel_2_2_1, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(displayregularcust, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(12)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(namedisplay))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(phonenodisplay))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2_2)
						.addComponent(lblGendeDisp, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(addressdisplay, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2_1))
					.addPreferredGap(ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_2_2_1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(displayregularcust, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
		
		table = new JTable();
		DefaultTableModel listitemmodel = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Item Number", "Item Name", "Quantity", "Price"
				}
			){
			/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int columnIndex) {
			    return false;
			}
			};
		table.setModel(listitemmodel);
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(62);
		scrollPane.setViewportView(table);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Receipt for Order ID " + orderid);
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setIcon(new ImageIcon(ReceiptCustomer.class.getResource("/main/logo/receiptframe.png")));
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(20)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 475, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(403, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addGap(15)
					.addComponent(lblNewLabel)
					.addContainerGap(21, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.add(panel);
		contentPane.add(lblNewLabel_4);
		contentPane.add(lblNewLabel_4_2);
		contentPane.add(panel_1);
		contentPane.add(panel_2);
		contentPane.add(lblNewLabel_4_1);
		contentPane.add(scrollPane);
		contentPane.add(custpaiddisplay);
		contentPane.add(balancedisp);
		
		listitemmodel.setRowCount(0);
		for(int i = 0; i < itemsdata.size(); i++) {	
				listitemmodel.addRow(new Object[]{itemsdata.get(i).getitemnumber(), itemsdata.get(i).getitemname(), itemsdata.get(i).getquantity(), "RM " + priceformatter.format(itemsdata.get(i).gettotalitems())});
		}
	}
}
