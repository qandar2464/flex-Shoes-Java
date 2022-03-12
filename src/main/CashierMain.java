package main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.Cursor;
import java.awt.Desktop;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CashierMain extends JFrame {

	SimpleDateFormat newdateformat = new SimpleDateFormat("dd-MM-yyyy");
	SimpleDateFormat newtimeformat = new SimpleDateFormat("hh:mm a");

	private JPanel contentPane;
	private JTable orderlist;
	static DefaultTableModel listordermodel;
	private static JButton btnNewButton;
	static Order orderframe = null;

	static DecimalFormat priceformatter = new DecimalFormat("#0.00");
	static private String orderidmain = null;

	public static void setorderframenull() {
		orderframe = null;
	}
	
	public static void showdata() {
		listordermodel.setRowCount(0);
		for (int i = 0; i < Main.getcustomer().size(); i++) {
			// CALCULATING TOTAL ALL ITEM PRICE
			double listpricecust = 0;
			for (int k = 0; k < Main.getitems().size(); k++) {
				if (String.valueOf(Main.getitems().get(k).getorderid())
						.equals(Main.getcustomer().get(i).getorderid())) {
					listpricecust = listpricecust + Main.getitems().get(k).gettotalitems();
				}
			}
			if (Main.getcustomer().get(i).getregularcustomer() == true) {
				listpricecust = listpricecust - (listpricecust * Main.getdiscountvalue());
			}
			listordermodel
					.addRow(new Object[] { Main.getcustomer().get(i).getname(), Main.getcustomer().get(i).getphoneno(),
							Main.getcustomer().get(i).getorderid(), "RM " + priceformatter.format(listpricecust) });

		}
	}
	
	static public Order getorderframe() {
		return orderframe;
	}
	
	static public JButton getbuttoncreate() {
		return btnNewButton;
	}

	private boolean containsOrderId(final String orderid) {
		return Main.getorders().stream().filter(order -> order.getorderid().equals(orderid)).findFirst().isPresent();
	}

	public void displaydata() {
		Iterator itrcustomer = Main.getcustomer().iterator();
		while (itrcustomer.hasNext()) {
			String customerdatalist = (String) itrcustomer.next();
			System.out.println(customerdatalist);
		}
	}

	public CashierMain() throws IOException {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				String selectorbutton[] = { "Yes", "No" };
				int PromptResult = JOptionPane.showOptionDialog(null,
						"Are you sure you want to exit?. All saved order will be lost.", "Exit " + Main.getappname(),
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, selectorbutton,
						selectorbutton[1]);
				if (PromptResult == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		displaydata();
		setTitle("Flex Shoes");
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
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 993, 511);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Order");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Receipt");
		mntmNewMenuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//GET RECEIPT FROM OLDER ORDER
				String orderid = JOptionPane.showInputDialog(null, "Enter existence Order ID",
						"Receipt", JOptionPane.INFORMATION_MESSAGE);
				if (!(orderid == null)) {
					if (!orderid.isEmpty()) {
						boolean duplicateorderid = containsOrderId(orderid);
						if (duplicateorderid) {
							ReceiptCustomer receiptframe = new ReceiptCustomer(orderid);
							receiptframe.setVisible(true);
						} else {
							JOptionPane.showMessageDialog(null,
									"The Order ID you entered not found. Refer Order table", "Order ID not found",
									JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Please enter Order ID", "Empty Order ID field",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		mntmNewMenuItem.setIcon(new ImageIcon(CashierMain.class.getResource("/main/logo/receipt.png")));
		mnNewMenu.add(mntmNewMenuItem);

		JMenu mnNewMenu_1 = new JMenu("Help");
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Read manual");
		mntmNewMenuItem_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (Desktop.isDesktopSupported()) {
					try {
						File manual = new File("manual.pdf");
						Desktop.getDesktop().open(manual);
					} catch (IOException ex) {
						JOptionPane.showMessageDialog(null, "No app found to open this manual", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		mntmNewMenuItem_1.setIcon(new ImageIcon(CashierMain.class.getResource("/main/logo/manual.png")));
		mnNewMenu_1.add(mntmNewMenuItem_1);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("About");
		mntmNewMenuItem_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				JOptionPane.showMessageDialog(null,
						Main.getappname() + "\nDeveloped By: " + Main.getcontributor() + "\nProject SWC2333",
						"About App", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mntmNewMenuItem_2.setIcon(new ImageIcon(CashierMain.class.getResource("/main/logo/about.png")));
		mnNewMenu_1.add(mntmNewMenuItem_2);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 102, 51));
		contentPane.setBorder(null);
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

		JLabel lblNewLabel = new JLabel("Order");
		lblNewLabel.setIcon(new ImageIcon(CashierMain.class.getResource("/main/logo/ordericon.png")));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));

		btnNewButton = new JButton("Order");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setIcon(new ImageIcon(CashierMain.class.getResource("/main/logo/add.png")));
		btnNewButton.setFocusable(false);
		btnNewButton.setBackground(new Color(218, 98, 125));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(btnNewButton.isEnabled()) {
					String orderid = JOptionPane.showInputDialog(null, "To create new order, enter new order ID",
							"Enter new order ID", JOptionPane.INFORMATION_MESSAGE);
					orderidmain = orderid;
					try {
						orderframe = new Order(orderidmain);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					if (!(orderid == null)) {
						if (!orderid.isEmpty()) {
							boolean duplicateorderid = containsOrderId(orderid);
							if (duplicateorderid) {
								JOptionPane.showMessageDialog(null,
										"The Order ID you entered exists. Enter another new Order ID", "Duplicate Order ID",
										JOptionPane.ERROR_MESSAGE);
							} else {
								Date date = new Date();
								
								Main.getorders().add(
										new OrderItem(orderid, newdateformat.format(date), newtimeformat.format(date)));
								orderframe.setVisible(true);
								btnNewButton.setEnabled(false);
							}
						} else {
							JOptionPane.showMessageDialog(null, "Please enter Order ID", "Empty Order ID field",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(42, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(800))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 855, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE))
							.addGap(68))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(19, Short.MAX_VALUE))
		);

		orderlist = new JTable();
		scrollPane.setViewportView(orderlist);
		listordermodel = new DefaultTableModel(new Object[][] {},
				new String[] { "Name", "Phone No", "Order ID", "Total Price" }) {
			/**
				 * 
				 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		};
		orderlist.setModel(listordermodel);
		orderlist.getColumnModel().getColumn(0).setPreferredWidth(195);
		contentPane.setLayout(gl_contentPane);
		setLocationRelativeTo(null);
		setIconImage(new ImageIcon(this.getClass().getResource("/main/logo/logo.png")).getImage());
	}
}
