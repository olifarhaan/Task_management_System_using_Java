import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Random;

public class AddTask extends JFrame implements ActionListener {
    Random random = new Random();
    int id = random.nextInt(999999);
    JButton addTask, back;
    JTextField title, description;
    JDateChooser deadline;
    JComboBox<String> status;
    JLabel titleLabel, descriptionLabel, deadlineLabel, statusLabel;

    AddTask() {
        setLayout(null);
        Color customBlue = new Color(21, 46, 85);
        Font general = new Font("Verdana", Font.BOLD, 23);
        Font accent = new Font("Verdana", Font.BOLD, 30);
        titleLabel = new JLabel("Task Title");
        titleLabel.setForeground(customBlue);
        titleLabel.setFont(general);
        titleLabel.setBounds(150, 40, 170, 50);
        add(titleLabel);

        title = new JTextField();
        title.setBounds(330, 40, 650, 50);
        title.setFont(general);
        add(title);


        descriptionLabel = new JLabel("Description");
        descriptionLabel.setForeground(customBlue);
        descriptionLabel.setFont(general);
        descriptionLabel.setBounds(150, 110, 260, 50);
        add(descriptionLabel);

        description = new JTextField();
        description.setBounds(330, 110, 650, 250);
        description.setFont(general);
        add(description);

        deadlineLabel = new JLabel("Deadline");
        deadlineLabel.setForeground(customBlue);
        deadlineLabel.setFont(general);
        deadlineLabel.setBounds(150, 380, 260, 50);
        add(deadlineLabel);

        deadline = new JDateChooser();
        deadline.setBounds(330, 380, 650, 50);
        deadline.setFont(general);
        deadline.setMinSelectableDate(new java.util.Date()); //disables the past date
        add(deadline);


        statusLabel = new JLabel("Status");
        statusLabel.setForeground(customBlue);
        statusLabel.setFont(general);
        statusLabel.setBounds(150, 450, 260, 50);
        add(statusLabel);

        String[] statusArray = {"Pending", "Under Process", "Postponed", "Done"};
        status = new JComboBox<>(statusArray);
        status.setBounds(330, 450, 650, 50);
        status.setFont(general);
        add(status);


        back = new JButton("Go Back");
        back.setBackground(new Color(166, 151, 126));
        back.setForeground(Color.BLACK);
        back.setFont(accent);
        back.setBounds(280, 550, 300, 80);
        back.addActionListener(this);
        add(back);

        addTask = new JButton("Add Task");
        addTask.setBackground(customBlue);
        addTask.setForeground(Color.WHITE);
        addTask.setFont(accent);
        addTask.setBounds(600, 550, 300, 80);
        addTask.addActionListener(this);
        add(addTask);

        getContentPane().setBackground(Color.WHITE);
        setTitle("Add Task");
        setSize(1200, 800);
        setLocation(300, 150);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new AddTask();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addTask) {
            String titleString = title.getText();
            String descriptionString = description.getText();
            java.sql.Date sqlDate = null;
            if (deadline != null) {
                sqlDate = new java.sql.Date(deadline.getDate().getTime());
            }
            Date deadlineString = sqlDate;
            String statusString = (String) status.getSelectedItem();
            try {
                Conn conn = new Conn();
                String query = "insert into task values(" + id + " ,'" + titleString + "','" + descriptionString + "','" + deadlineString + "','" + statusString + "')";
                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Task " + id + " been added successfully");
                new Home();
                setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else {
            new Home();
            setVisible(false);
        }
    }
}
