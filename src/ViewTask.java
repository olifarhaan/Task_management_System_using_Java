import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class ViewTask extends JFrame implements ActionListener {
    JButton search, print, update, back;
    JTable taskTable;
    Choice selectFieldById;
    JScrollPane taskTableScroller;

    ViewTask() {
        setLayout(null);
        Color customBlue = new Color(21, 46, 85);
        Font accent = new Font("Verdana", Font.BOLD, 25);
        JLabel searchLabel = new JLabel("Search Task by ID ");
        searchLabel.setForeground(customBlue);
        searchLabel.setFont(accent);
        searchLabel.setBounds(40, 40, 280, 50);
        add(searchLabel);

        selectFieldById = new Choice();
        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from task");
            while (rs.next()) {
                selectFieldById.add(String.valueOf(rs.getInt("id")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        selectFieldById.setBounds(330, 40, 290, 50);
        selectFieldById.setForeground(customBlue);
        selectFieldById.setFont(accent);
        add(selectFieldById);

        search = new JButton("Search");
        search.setBackground(new Color(235, 231, 223));
        search.setForeground(Color.BLACK);
        search.setFont(accent);
        search.setBounds(40, 100, 250, 50);
        search.addActionListener(this);
        add(search);

        print = new JButton("Print");
        print.setBackground(new Color(195, 204, 196));
        print.setForeground(Color.BLACK);
        print.setFont(accent);
        print.setBounds(300, 100, 250, 50);
        print.addActionListener(this);
        add(print);

        update = new JButton("Update");
        update.setBackground(new Color(166, 151, 126));
        update.setForeground(Color.WHITE);
        update.setFont(accent);
        update.setBounds(560, 100, 250, 50);
        update.addActionListener(this);
        add(update);

        back = new JButton("Back");
        back.setBackground(customBlue);
        back.setForeground(Color.WHITE);
        back.setFont(accent);
        back.setBounds(820, 100, 250, 50);
        back.addActionListener(this);
        add(back);

        taskTable = new JTable();
        try {
            Conn conn = new Conn();
            ResultSet taskSet = conn.s.executeQuery("select * from task order by deadline asc");
            taskTable.setModel(DbUtils.resultSetToTableModel(taskSet));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        JTableHeader header = taskTable.getTableHeader();
        header.setFont(new Font("Verdana", Font.PLAIN, 25));
        header.setBackground(customBlue);
        header.setForeground(Color.WHITE);
        JTableUtilities.setCellsAlignment(taskTable, SwingConstants.CENTER);
        taskTable.setFont(new Font("Verdana", Font.PLAIN, 25));
        taskTable.setRowHeight(40);
        taskTableScroller = new JScrollPane(taskTable);
        taskTableScroller.setBounds(40, 170, 1120, 550);
        taskTable.setDefaultEditor(Object.class, null);

        add(taskTableScroller);

        getContentPane().setBackground(Color.WHITE);
        setTitle("View Task");
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new ViewTask();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == search) {
            int id = Integer.parseInt(selectFieldById.getSelectedItem());
            try {
                Conn conn = new Conn();
                ResultSet taskSet = conn.s.executeQuery("select * from task where id=" + id);
                taskTable.setModel(DbUtils.resultSetToTableModel(taskSet));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (ae.getSource() == print) {
            try {
                taskTable.print();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (ae.getSource() == update) {
            new UpdateTask(Integer.parseInt(selectFieldById.getSelectedItem()));
            setVisible(false);

        } else if (ae.getSource() == back) {
            new Home();
            setVisible(false);
        }
    }
}
