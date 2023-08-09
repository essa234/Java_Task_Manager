package com.example.TaskManager.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TaskManagerGui implements ActionListener {

  private static JPanel panel;
  private static JFrame frame;
  private static JButton loginButton;
  private static JButton registerButton;
  private static JTextField textField;
  private static JLabel jLabel;

  public static void main(String[] args) {
    panel = new JPanel();

    frame = new JFrame();
    frame.setSize(500,300);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(panel);

    panel.setLayout(null);

    loginButton = new JButton("Login");
    loginButton.setBounds(10,20, 100, 35);
    panel.add(loginButton);
    registerButton = new JButton("Register");
    registerButton.setBounds(120,20, 100, 35);
    panel.add(registerButton);

    frame.setVisible(true);

  }
  @Override
  public void actionPerformed(ActionEvent e) {

  }
}
