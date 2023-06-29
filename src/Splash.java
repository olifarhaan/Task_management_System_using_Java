import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.*;

public class Splash extends JFrame implements EventListener {
    Splash() {
        setLayout(null);
        Font descriptionFont = new Font("Verdana", Font.BOLD, 30);
        JLabel heading = new JLabel("Task Management System");
        heading.setForeground(new Color(21, 46, 85));
        heading.setFont(new Font("Verdana", Font.BOLD, 55));
        heading.setBounds(200, 20, 1000, 100);
        add(heading);

        ImageIcon bgSplash = new ImageIcon(ClassLoader.getSystemResource("images/bgSplash.png"));
        Image bgSplashScaled = bgSplash.getImage().getScaledInstance(1200, 700, Image.SCALE_DEFAULT);
        JLabel bgSplashLabel = new JLabel(new ImageIcon(bgSplashScaled));
        bgSplashLabel.setBounds(0, 120, 1200, 650);
        add(bgSplashLabel);

        JButton proceed = new JButton("Manage Tasks");
        proceed.setBackground(new Color(21, 46, 85));
        proceed.setForeground(Color.WHITE);
        proceed.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        proceed.setFont(descriptionFont);
        proceed.setBounds(400, 100, 400, 80);
        proceed.addActionListener(e -> {
            new Login();
            setVisible(false);
        });
        bgSplashLabel.add(proceed);


        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Verdana", Font.BOLD, 20)));
        UIManager.put("OptionPane.minimumSize", new Dimension(500, 300));

        getContentPane().setBackground(Color.WHITE);
        setTitle("Task Management System");
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Splash();
    }
}
