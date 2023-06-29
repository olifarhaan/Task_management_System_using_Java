import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class Login extends JFrame {
    Login() {
        setLayout(null);
        Font descriptionFont = new Font("Verdana", Font.BOLD, 30);
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setForeground(Color.BLACK);
        usernameLabel.setFont(descriptionFont);
        usernameLabel.setBounds(100, 120, 200, 70);
        add(usernameLabel);

        JTextField username = new JTextField();
        username.setBounds(310, 120, 290, 70);
        username.setFont(descriptionFont);
        add(username);

        JLabel passLabel = new JLabel("Password");
        passLabel.setForeground(Color.BLACK);
        passLabel.setFont(descriptionFont);
        passLabel.setBounds(100, 210, 200, 70);
        add(passLabel);

        JPasswordField password = new JPasswordField();
        password.setBounds(310, 210, 290, 70);
        password.setFont(descriptionFont);
        add(password);

        ImageIcon bgLogin = new ImageIcon(ClassLoader.getSystemResource("images/bgLogin.png"));
        Image bgLoginScaled = bgLogin.getImage().getScaledInstance(600, 600, Image.SCALE_DEFAULT);
        JLabel bgLoginLabel = new JLabel(new ImageIcon(bgLoginScaled));
        bgLoginLabel.setBounds(600, 0, 600, 800);
        add(bgLoginLabel);

        JButton proceed = new JButton("Login");
        proceed.setBackground(new Color(21, 46, 85));
        proceed.setForeground(Color.WHITE);
        proceed.setFont(descriptionFont);
        proceed.setBounds(100, 330, 500, 80);
        proceed.addActionListener(e -> {
            try {
                Conn conn = new Conn();
                String usernameString = username.getText();
                String passwordString = new String(password.getPassword());
                String query = "select * from login where username='" + usernameString + "' and password='" + passwordString + "'";
                ResultSet rs = conn.s.executeQuery(query);
                if (rs.next()) {
                    new Home();
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Login Details");
                }

            } catch (Exception ea) {
                ea.printStackTrace();
            }
        });

        add(proceed);

        getContentPane().setBackground(Color.WHITE);
        setTitle("Task Management System");
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Login();
    }
}
