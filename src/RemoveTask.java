import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class RemoveTask extends JFrame implements ActionListener {
    JLabel selectTaskLabel, taskTitleLabel, deadlineLabel, statusLabel, taskTitle, deadline, status;
    Choice selectTask;
    JButton back, remove;

    RemoveTask() {
        setLayout(null);

        Font accent = new Font("Verdana", Font.BOLD, 25);

        selectTaskLabel = new JLabel("Select Task by ID ");
        selectTaskLabel.setForeground(Color.BLACK);
        selectTaskLabel.setFont(accent);
        selectTaskLabel.setBounds(40, 40, 280, 50);
        add(selectTaskLabel);

        selectTask = new Choice();
        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from task");
            while (rs.next()) {
                selectTask.add(String.valueOf(rs.getInt("id")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        selectTask.setBounds(360, 40, 220, 50);
        selectTask.setFont(accent);
        add(selectTask);

        taskTitleLabel = new JLabel("Task Title : ");
        taskTitleLabel.setForeground(Color.BLACK);
        taskTitleLabel.setFont(accent);
        taskTitleLabel.setBounds(40, 110, 300, 50);
        add(taskTitleLabel);

        taskTitle = new JLabel();
        taskTitle.setForeground(Color.BLACK);
        taskTitle.setFont(accent);
        taskTitle.setBounds(360, 110, 300, 50);
        add(taskTitle);

        deadlineLabel = new JLabel("Deadline : ");
        deadlineLabel.setForeground(Color.BLACK);
        deadlineLabel.setFont(accent);
        deadlineLabel.setBounds(40, 170, 300, 50);
        add(deadlineLabel);

        deadline = new JLabel();
        deadline.setForeground(Color.BLACK);
        deadline.setFont(accent);
        deadline.setBounds(360, 170, 300, 50);
        add(deadline);


        statusLabel = new JLabel("Status: ");
        statusLabel.setForeground(Color.BLACK);
        statusLabel.setFont(accent);
        statusLabel.setBounds(40, 230, 300, 50);
        add(statusLabel);

        status = new JLabel();
        status.setForeground(Color.BLACK);
        status.setFont(accent);
        status.setBounds(360, 230, 260, 50);
        add(status);

        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from task where id=" + selectTask.getSelectedItem());
            if (rs.next()) {
                taskTitle.setText(rs.getString("title"));
                deadline.setText(rs.getString("deadline"));
                status.setText(rs.getString("status"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        selectTask.addItemListener(e -> {
            try {
                Conn conn = new Conn();
                ResultSet rs = conn.s.executeQuery("select * from task where id=" + selectTask.getSelectedItem());
                while (rs.next()) {
                    taskTitle.setText(rs.getString("title"));
                    deadline.setText(rs.getString("deadline"));
                    status.setText(rs.getString("status"));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        ImageIcon bgDelete = new ImageIcon(ClassLoader.getSystemResource("images/bgDelete.png"));
        Image bgDeleteScaled = bgDelete.getImage().getScaledInstance(550, 750, Image.SCALE_DEFAULT);
        JLabel bgDeleteLabel = new JLabel(new ImageIcon(bgDeleteScaled));
        bgDeleteLabel.setBounds(600, 20, 550, 750);
        add(bgDeleteLabel);

        back = new JButton("Back");
        back.setBackground(new Color(21, 46, 85));
        back.setForeground(Color.WHITE);
        back.setFont(accent);
        back.setBounds(40, 500, 250, 80);
        back.addActionListener(this);
        add(back);

        remove = new JButton("Delete");
        remove.setBackground(Color.RED);
        remove.setForeground(Color.WHITE);
        remove.setFont(accent);
        remove.setBounds(300, 500, 250, 80);
        remove.addActionListener(this);
        add(remove);

        getContentPane().setBackground(Color.WHITE);
        setTitle("Delete Task");
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == remove) {
            try {
                Conn conn = new Conn();
                conn.s.executeUpdate("delete from task where id=" + selectTask.getSelectedItem());
                JOptionPane.showMessageDialog(null, "Task with Id: " + selectTask.getSelectedItem() + " has been removed");
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

    public static void main(String[] args) {
        new RemoveTask();
    }
}
