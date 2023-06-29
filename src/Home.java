import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame implements ActionListener {

    JButton add, update, view, remove;

    Home() {
        setLayout(null);

        ImageIcon bgHome = new ImageIcon(ClassLoader.getSystemResource("images/home.png"));
        Image bgHomeScaled = bgHome.getImage().getScaledInstance(1456, 819, Image.SCALE_DEFAULT);
        JLabel bgHomeLabel = new JLabel(new ImageIcon(bgHomeScaled));
        bgHomeLabel.setBounds(0, 0, 1456, 819);
        add(bgHomeLabel);

        JLabel heading = new JLabel("Manage Your Tasks Here");
        heading.setForeground(new Color(21, 46, 85));
        heading.setBackground(Color.BLACK);
        heading.setFont(new Font("Verdana", Font.BOLD, 43));
        heading.setBounds(750, 50, 700, 100);
        bgHomeLabel.add(heading);

        Font buttonFont = new Font("Verdana", Font.BOLD, 25);
        add = new JButton("Add Task");
        add.setBackground(new Color(235, 231, 223));
        add.setForeground(Color.BLACK);
        add.setFont(buttonFont);
        add.setBounds(750, 170, 300, 80);
        add.addActionListener(this);
        bgHomeLabel.add(add);

        view = new JButton("View Tasks");
        view.setBackground(new Color(195, 204, 196));
        view.setForeground(Color.BLACK);
        view.setFont(buttonFont);
        view.setBounds(1070, 170, 300, 80);
        view.addActionListener(this);
        bgHomeLabel.add(view);

        update = new JButton("Update Task");
        update.setBackground(new Color(166, 151, 126));
        update.setForeground(Color.WHITE);
        update.setFont(buttonFont);
        update.setBounds(750, 270, 300, 80);
        update.addActionListener(this);
        bgHomeLabel.add(update);

        remove = new JButton("Delete Task");
        remove.setBackground(new Color(21, 46, 85));
        remove.setForeground(Color.WHITE);
        remove.setFont(buttonFont);
        remove.setBounds(1070, 270, 300, 80);
        remove.addActionListener(this);
        bgHomeLabel.add(remove);


        setTitle("Home");
        setSize(1456, 819);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            new AddTask();
            setVisible(false);
        } else if (e.getSource() == view) {
            new ViewTask();
            setVisible(false);
        } else if (e.getSource() == update) {
            new ViewTask();
            setVisible(false);
        } else if (e.getSource() == remove) {
            new RemoveTask();
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Home();
    }
}