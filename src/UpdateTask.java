import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

public class UpdateTask extends JFrame implements ActionListener {
    JButton addTask, back;
    JTextField title, description, idLabel;
    JDateChooser deadline;
    JComboBox<String> status;
    JLabel titleLabel, descriptionLabel, deadlineLabel, statusLabel;
    int id;

    UpdateTask(int id) {
        this.id=id;
        setLayout(null);
        Font general = new Font("Verdana", Font.BOLD, 23);
        Font accent = new Font("Verdana", Font.BOLD, 30);
        titleLabel = new JLabel("Task Title");
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(general);
        titleLabel.setBounds(150, 40, 170, 50);
        add(titleLabel);

        title= new JTextField();
        title.setBounds(330,40, 650,50);
        title.setFont(general);
        add(title);


        descriptionLabel = new JLabel("Description");
        descriptionLabel.setForeground(Color.BLACK);
        descriptionLabel.setFont(general);
        descriptionLabel.setBounds(150, 110, 260, 50);
        add(descriptionLabel);

        description= new JTextField();
        description.setBounds(330,110, 650,250);
        description.setFont(general);
        add(description);

        deadlineLabel = new JLabel("Deadline");
        deadlineLabel.setForeground(Color.BLACK);
        deadlineLabel.setFont(general);
        deadlineLabel.setBounds(150, 380, 260, 50);
        add(deadlineLabel);

        deadline= new JDateChooser();
        deadline.setBounds(330,380, 650,50);
        deadline.setFont(general);
        add(deadline);


        statusLabel = new JLabel("Status");
        statusLabel.setForeground(Color.BLACK);
        statusLabel.setFont(general);
        statusLabel.setBounds(150, 450, 260, 50);
        add(statusLabel);

        String[] statusArray = {"Pending", "Under Process", "Postponed", "Done"};
        status = new JComboBox<>(statusArray);
        status.setBounds(330, 450, 650, 50);
        status.setFont(general);
        add(status);


        back = new JButton("Go Back");
        back.setBackground(new Color(235,231,223));
        back.setForeground(Color.BLACK);
        back.setFont(accent);
        back.setBounds(280, 550, 300, 80);
        back.addActionListener(this);
        add(back);

        addTask = new JButton("Update Task");
        addTask.setBackground(new Color(21, 46, 85));
        addTask.setForeground(Color.WHITE);
        addTask.setFont(accent);
        addTask.setBounds(600, 550, 300, 80);
        addTask.addActionListener(this);
        add(addTask);


        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from task where id=" + id);
            if (rs.next()) {
                title.setText(rs.getString("title"));
                description.setText(rs.getString("description"));

                String deadlineValue = rs.getString("deadline"); // What ever column
                java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(deadlineValue);

                deadline.setDate(date);
                status.setSelectedItem(rs.getString("status"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        getContentPane().setBackground(Color.WHITE);
        setTitle("Update Task");
        setSize(1200, 800);
        setLocation(300, 150);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new UpdateTask(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addTask) {
            String titleString = title.getText();
            String descriptionString = description.getText();
            java.sql.Date sqlDate = null;
            if(deadline != null) {
                sqlDate = new java.sql.Date(deadline.getDate().getTime());
            }
            Date deadlineString = sqlDate;
            String statusString= (String)status.getSelectedItem();
            try {
                Conn conn = new Conn();
                String query = "update task set id="+id+",title='" + titleString + "', description='" + descriptionString + "', deadline='" +deadlineString + "',status='" + statusString + "' where id="+id;
                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Task " + id + " has been updated successfully");
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
