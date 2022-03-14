package main;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JProgressBar;

public class Welcome extends JFrame {
	
	private static final long serialVersionUID = 1L;
	JLabel lblNewLabel_3 = new JLabel("Loading");
	public Welcome() {
		setUndecorated(true);
		setResizable(false);
		setTitle(Main.getappname());
		setBounds(100, 100, 765, 387);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setIconImage(new ImageIcon(this.getClass().getResource("/main/logo/logo.png")).getImage());

		JPanel panel_1 = new JPanel();
		panel_1.setForeground(Color.BLACK);
		panel_1.setBackground(new Color(255, 204, 153));
		panel_1.setBounds(0, 0, 766, 387);
		getContentPane().add(panel_1);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(this.getClass().getResource("/main/logo/logo.png")));

		JLabel lblNewLabel_2 = new JLabel(Main.getappname());
		lblNewLabel_2.setBackground(Color.WHITE);
		lblNewLabel_2.setForeground(Color.DARK_GRAY);
		lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 58));

		JLabel lblNewLabel = new JLabel("By: " + Main.getcontributor());
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Calibri Light", Font.PLAIN, 17));
		
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setForeground(Color.BLACK);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(18)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 509, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(158)
					.addComponent(lblNewLabel_1)
					.addGap(10)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 518, GroupLayout.PREFERRED_SIZE)))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(21)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addGap(63)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(13)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)))
					.addGap(103))
		);
		panel_1.setLayout(gl_panel_1);
	}
}
