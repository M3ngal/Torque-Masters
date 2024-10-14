package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ShowPane extends JDialog implements ActionListener{
    JLabel lb;
    JButton bt;


    public ShowPane(JFrame fr, String parts){
        super(fr,true);
        Container cp = getContentPane();
        cp.setLayout(new FlowLayout());
        lb = new JLabel(parts);
        cp.add(lb);
        bt = new JButton("OK");
        cp.add(bt);
        bt.addActionListener(this);
        setSize(600,300);
        setLocation(700,400);
        setTitle("Cars");
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        dispose();
    }

    static void show(JFrame fr, String parts){
        ShowPane sp = new ShowPane(fr, parts);
    }
}