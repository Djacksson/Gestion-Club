package com.club.view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClubSearchApp {

	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClubSearchApp window = new ClubSearchApp();
					window.frame.setVisible(true);
					window.frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClubSearchApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("About The Club");
		frame.getContentPane().setBackground(new Color(245, 245, 220));
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblAboutTheClub = new JLabel("About The Club:");
		lblAboutTheClub.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		
		JLabel lblNameOfClub = new JLabel("Name of Club :");
		lblNameOfClub.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNameOfClub.setForeground(new Color(0, 0, 128));
		
		JLabel lblDateStart = new JLabel("Date Start :");
		lblDateStart.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDateStart.setForeground(new Color(0, 0, 128));
		
		JLabel lblObjectif = new JLabel("Objectif :");
		lblObjectif.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblObjectif.setForeground(new Color(0, 0, 128));
		
		JLabel lblPresident = new JLabel("President :");
		lblPresident.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPresident.setForeground(new Color(0, 0, 128));
		
		JButton btnHome = new JButton("Home");
		btnHome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnHome.setBackground(new Color(0, 0, 128));
		btnHome.setForeground(new Color(255, 255, 255));
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
				try {
					Home window = new Home();
					window.frame.setVisible(true);
					window.frame.setLocationRelativeTo(null);
				} catch (Exception exp) {
					exp.printStackTrace();
				}
			}
		});
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUpdate.setBackground(new Color(0, 0, 128));
		btnUpdate.setForeground(new Color(255, 255, 255));
		
		JLabel lblCyberit = new JLabel("CyberIT");
		lblCyberit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel label = new JLabel("2019-05-31");
		label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblProposeActivitiesAnd = new JLabel("Propose activities and projects to members");
		lblProposeActivitiesAnd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblAzzedinne = new JLabel("Mr Azzedinne");
		lblAzzedinne.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblVicepresident = new JLabel("Vice-president :");
		lblVicepresident.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblVicepresident.setForeground(new Color(0, 0, 128));
		
		JLabel lblMrAmin = new JLabel("Mr Amin");
		lblMrAmin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel label_1 = new JLabel("Treasurer :");
		label_1.setForeground(new Color(0, 0, 128));
		label_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblMlleAicha = new JLabel("Mlle Aicha");
		lblMlleAicha.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel label_2 = new JLabel("Secretary :");
		label_2.setForeground(new Color(0, 0, 128));
		label_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblMlleNajat = new JLabel("Mlle Najat");
		lblMlleNajat.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnHome, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 503, Short.MAX_VALUE)
					.addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(14)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblObjectif)
						.addComponent(lblNameOfClub)
						.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDateStart))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMlleNajat)
								.addComponent(label)
								.addComponent(lblCyberit))
							.addPreferredGap(ComponentPlacement.RELATED, 194, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblVicepresident)
								.addComponent(lblPresident)
								.addComponent(label_1))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMlleAicha)
								.addComponent(lblMrAmin)
								.addComponent(lblAzzedinne)))
						.addComponent(lblProposeActivitiesAnd))
					.addGap(63))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(282)
					.addComponent(lblAboutTheClub)
					.addContainerGap(296, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAboutTheClub, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNameOfClub)
							.addComponent(lblCyberit))
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblPresident)
							.addComponent(lblAzzedinne)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(label)
								.addComponent(lblDateStart))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_2)
								.addComponent(lblMlleNajat)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblVicepresident)
								.addComponent(lblMrAmin))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_1)
								.addComponent(lblMlleAicha))))
					.addGap(88)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblObjectif)
						.addComponent(lblProposeActivitiesAnd))
					.addGap(181)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnHome)
						.addComponent(btnUpdate))
					.addContainerGap())
		);
		frame.getContentPane().setLayout(groupLayout);
		frame.setBounds(100, 100, 700, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
