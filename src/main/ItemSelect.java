package main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.swing.table.DefaultTableModel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextField;
import java.awt.SystemColor;

public class ItemSelect extends JFrame {
	
	DecimalFormat priceformatter = new DecimalFormat("#0.00");

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField deletenumberfield;
	DefaultTableModel listitemmodel;
	private String orderid;
	JLabel totalpricedisplay;
	

	

	public ItemSelect(final String orderid) throws IOException {
		this.orderid = orderid;
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(ItemSelect.class.getResource("/main/logo/logo.png")));
		setTitle(Main.getappname());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 783, 539);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 153, 153));
		contentPane.setBorder(null);
		setContentPane(contentPane);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 772, 73);
		panel.setBackground(new Color(255, 204, 153));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(70, 194, 626, 228);

		// read file from item
		BufferedReader itemlistinput = null;
		List<String> itemlist = new ArrayList<String>();
		List<String> itemlistname = new ArrayList<String>();
		List<Double> priceperitem = new ArrayList<Double>();
		
		try {
			itemlistinput = new BufferedReader(new FileReader("items.txt"));
			String shoesitem = null;
			itemlist.add("Select Item");
			while ((shoesitem = itemlistinput.readLine()) != null) {
				String[] listitemcomma = shoesitem.split(",");
				itemlist.add(listitemcomma[0] + " RM"+ listitemcomma[1]);
				itemlistname.add(listitemcomma[0]);
				priceperitem.add(Double.parseDouble(listitemcomma[1]));
			}
		}
		catch (FileNotFoundException e) {
			System.err.println("Error, file didn't exist.");
		} finally {
			itemlistinput.close();
		}

		String[] itemlistArray = itemlist.toArray(new String[] {});
		String[] itemlistnameArray = itemlistname.toArray(new String[] {});
		Double[] priceperitemArray = priceperitem.toArray(new Double[] {});
		
		JComboBox itemcombobox = new JComboBox(itemlistArray);
		itemcombobox.setForeground(new Color(0, 0, 0));
		itemcombobox.setBounds(48, 102, 196, 22);

		JSpinner quantity = new JSpinner();
		quantity.setBounds(60, 163, 32, 20);
		quantity.setModel(new SpinnerNumberModel(1, 1, null, 1));
		

		JButton btnNewButton = new JButton("+");
		btnNewButton.setBounds(266, 102, 51, 23);
		btnNewButton.addMouseListener(new MouseAdapter() {
			int lastitemnumber = 1;
			@Override
			public void mouseClicked(MouseEvent e) {
				//add item to customer order
				int selecteditem = 0;
				int quantityno;
				double totalitemsprice = 0;
				
				selecteditem = itemcombobox.getSelectedIndex();
				
				try {
					if(selecteditem != 0) {
						if(table.getRowCount() > 0) {				
							lastitemnumber = (int) table.getModel().getValueAt(table.getRowCount() - 1, 0) + 1;
						}else {
							lastitemnumber = 1;
						}
						
						//calculate price with quantity
						quantityno = (Integer) quantity.getValue();
						totalitemsprice = priceperitemArray[selecteditem - 1] * quantityno;
						Main.getitems().add(new Items(orderid, lastitemnumber, String.valueOf(itemlistnameArray[selecteditem - 1]), (Integer)quantity.getValue(), totalitemsprice));
						quantity.setValue(1);
						showdata();
					}else {
						JOptionPane.showMessageDialog(null, "Please select item", "No item selected", JOptionPane.ERROR_MESSAGE);
					}
				}catch(Exception error) {
					System.out.println("Error: " + error);
				}
				calctotalprice();
			}
		});
		
		JLabel lblNewLabel = new JLabel("Items");
		lblNewLabel.setBounds(48, 84, 55, 17);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNewLabel_1 = new JLabel("Quantity");
		lblNewLabel_1.setBounds(50, 135, 53, 17);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnNewButton_1 = new JButton("Save");
		btnNewButton_1.setBounds(629, 457, 103, 34);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		
		deletenumberfield = new JTextField();
		deletenumberfield.setBounds(420, 105, 103, 20);
		deletenumberfield.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Item Number");
		lblNewLabel_3.setBounds(420, 85, 84, 17);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.setBounds(536, 102, 65, 23);
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//if user want delete item here
				int deletenumber;
				try {
					deletenumber = Integer.parseInt(deletenumberfield.getText());
					Predicate<Items> condition2 = p->p.getitemnumber()==deletenumber && p.orderid == orderid;
					Main.getitems().removeIf(condition2);
					calctotalprice();
					showdata();
				}catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Enter a valid item number", "Invalid Item Number", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		totalpricedisplay = new JLabel("Total Price: RM 0");
		totalpricedisplay.setBounds(599, 433, 133, 19);
		totalpricedisplay.setFont(new Font("Tahoma", Font.BOLD, 15));

		table = new JTable();
		listitemmodel = new DefaultTableModel(
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

		JLabel lblNewLabel_2 = new JLabel("Total order ID: "+orderid );

		lblNewLabel_2.setIcon(new ImageIcon(ItemSelect.class.getResource("/main/logo/contract.png")));
		lblNewLabel_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(18)
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 358, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(33, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, gl_panel
				.createSequentialGroup().addContainerGap(21, Short.MAX_VALUE).addComponent(lblNewLabel_2).addGap(20)));
		panel.setLayout(gl_panel);
		contentPane.add(panel);
		contentPane.add(lblNewLabel);
		contentPane.add(quantity);
		contentPane.add(itemcombobox);
		contentPane.add(btnNewButton);
		contentPane.add(lblNewLabel_1);
		contentPane.add(lblNewLabel_3);
		contentPane.add(deletenumberfield);
		contentPane.add(btnNewButton_2);
		contentPane.add(totalpricedisplay);
		contentPane.add(btnNewButton_1);
		contentPane.add(scrollPane);
		
		showdata();
	}
	
	private void calctotalprice() {
		double listpricecust = 0;
		for(int i = 0; i < Main.getitems().size(); i++) {
			if(String.valueOf(Main.getitems().get(i).getorderid()).equals(orderid)) {				
				listpricecust = listpricecust + Main.getitems().get(i).gettotalitems();
			}
		}
		totalpricedisplay.setText("Total Price: RM " + priceformatter.format(listpricecust));
		Order.calctotalprice(listpricecust);
	}
	
	private void showdata() {
		//add data
		listitemmodel.setRowCount(0);
		for(int i = 0; i < Main.getitems().size(); i++) {
			if(String.valueOf(Main.getitems().get(i).getorderid()).equals(orderid)) {				
				listitemmodel.addRow(new Object[]{Main.getitems().get(i).getitemnumber(), Main.getitems().get(i).getitemname(), Main.getitems().get(i).getquantity(), "RM " + priceformatter.format(Main.getitems().get(i).gettotalitems())});
			}
		}
	}
}
