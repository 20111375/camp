package gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: 20111375
 * Date: 05/11/2013
 * Time: 16:16
 */
public class CustomerForm {
    private JPanel MainWindow;
    private JList list1;
    private JTextField FirstNameField;
    private JTextField SurnameField;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton submitButton;
    private JList list2;
    private JButton button1;

    public void make() {
        JFrame frame = new JFrame("CustomerForm");
        frame.setContentPane(new CustomerForm().MainWindow);
        frame.pack();
        frame.setVisible(true);
    }

}

