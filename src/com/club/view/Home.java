package com.club.view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JMenuBar;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.Font;

import javax.swing.LayoutStyle.ComponentPlacement;

public class Home {

	public JFrame frame;
	protected boolean isLogIn=false;
	protected boolean isLogOut=true;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home window = new Home();
					window.frame.setVisible(true);
					window.frame.setResizable(false);
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
	public Home() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Welcome in Club-Student!");
		frame.getContentPane().setBackground(new Color(245, 245, 220));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblWelcomeInGclubs = new JLabel("Welcome In Club-Students!");
		lblWelcomeInGclubs.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblGclubsIs = new JLabel("Para-university activities have considerable scope for forging a personality for the future engineer. ");
		
		JLabel lblExact = new JLabel("It is in this vision that the para-academic constitutes an integral and inseparable part of the training within the ENSA-El Jadida.");
		
		JLabel lblCreatedBy = new JLabel("Created By : Charhabil Sanaa & Djakaridia Traore");
		lblCreatedBy.setFont(new Font("Tahoma", Font.ITALIC, 11));
		
		JLabel lblThisPlatformWas = new JLabel("This platform was created to establish better management of a student club.");
		
		JLabel lblEnsaElJadida = new JLabel("ENSA El Jadida - 2019");
		lblEnsaElJadida.setFont(new Font("Tahoma", Font.ITALIC, 11));
		
		JButton btnLogIn = new JButton("Log In");
		btnLogIn.setForeground(new Color(255, 255, 255));
		btnLogIn.setBackground(new Color(0, 0, 128));
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//new AuthSearchApp();
				//setVisible(true);
				//dispose();
				try {
					AuthSearchApp window2 = new AuthSearchApp();
					window2.frame.setVisible(true);
					window2.frame.setLocationRelativeTo(null);
				} catch (Exception exp) {
					exp.printStackTrace();
				}

			}
		});
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setForeground(new Color(255, 255, 255));
		btnLogOut.setBackground(new Color(0, 0, 128));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(282)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnLogIn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnLogOut, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(260)
							.addComponent(lblWelcomeInGclubs, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(76, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(29)
					.addComponent(lblCreatedBy)
					.addPreferredGap(ComponentPlacement.RELATED, 333, Short.MAX_VALUE)
					.addComponent(lblEnsaElJadida)
					.addGap(30))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addComponent(lblExact)
					.addContainerGap(110, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(91)
					.addComponent(lblThisPlatformWas)
					.addContainerGap(275, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(66)
					.addComponent(lblGclubsIs)
					.addContainerGap(189, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(34)
					.addComponent(lblWelcomeInGclubs, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblGclubsIs, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblExact)
					.addGap(31)
					.addComponent(lblThisPlatformWas)
					.addGap(42)
					.addComponent(btnLogIn)
					.addGap(18)
					.addComponent(btnLogOut)
					.addPreferredGap(ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEnsaElJadida)
						.addComponent(lblCreatedBy))
					.addGap(23))
		);
		frame.getContentPane().setLayout(groupLayout);
		frame.setBounds(100, 100, 750, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JButton btnHome = new JButton("Home");
		btnHome.setForeground(new Color(255, 255, 255));
		btnHome.setBackground(new Color(0, 0, 128));
		btnHome.setPreferredSize(new Dimension(200, 25));
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Home();
			}
		});
		menuBar.add(btnHome);
		
		JButton btnMembers = new JButton("Members");
		btnMembers.setBackground(new Color(0, 0, 128));
		btnMembers.setForeground(new Color(255, 255, 255));
		btnMembers.setPreferredSize(new Dimension(200, 25));
		btnMembers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				try {
					MembreSearchApp window4 = new MembreSearchApp();
					window4.setVisible(true);
					window4.setLocationRelativeTo(null);
				} catch (Exception exp) {
					exp.printStackTrace();
				}
			}
		});
		
		JButton btnClub = new JButton("Club");
		btnClub.setBackground(new Color(0, 0, 128));
		btnClub.setForeground(new Color(255, 255, 255));
		btnClub.setPreferredSize(new Dimension(200, 25));
		btnClub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				try {
					ClubSearchApp window5 = new ClubSearchApp();
					window5.frame.setVisible(true);
					window5.frame.setLocationRelativeTo(null);
				} catch (Exception exp) {
					exp.printStackTrace();
				}
			}
		});
		menuBar.add(btnClub);
		menuBar.add(btnMembers);
		
		JButton btnTasks = new JButton("Tasks");
		btnTasks.setBackground(new Color(0, 0, 128));
		btnTasks.setForeground(new Color(255, 255, 255));
		btnTasks.setPreferredSize(new Dimension(200, 25));
		btnTasks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ActiviteSearchApp().setVisible(true);
				frame.setVisible(false);
			}
		});
		menuBar.add(btnTasks);
		
		JButton btnEvents = new JButton("Events");
		btnEvents.setBackground(new Color(0, 0, 128));
		btnEvents.setForeground(new Color(255, 255, 255));
		btnEvents.setPreferredSize(new Dimension(200, 25));
		btnEvents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EventSearchApp().setVisible(true);
				frame.setVisible(false);
			}
		});
		menuBar.add(btnEvents);
		
		JButton btnRessources = new JButton("Ressources");
		btnRessources.setBackground(new Color(0, 0, 128));
		btnRessources.setForeground(new Color(255, 255, 255));
		btnRessources.setPreferredSize(new Dimension(200, 25));
		btnRessources.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ComptaSearchApp().setVisible(true);
				frame.setVisible(false);
			}
		});
		menuBar.add(btnRessources);
	}
}
