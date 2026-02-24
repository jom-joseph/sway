import javax.swing.*;
import java.awt.*;

public class HomeScreen extends JFrame {

    private final int userId;
    private final String username;

    public HomeScreen(int userId, String username) {
        this.userId   = userId;
        this.username = username;

        setTitle("SWAY - Find Your Balance");
        setSize(500, 580);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Color primary    = new Color(67, 181, 129);
        Color background = new Color(244, 253, 248);
        Color textColor  = new Color(30, 45, 47);

        JPanel panel = new JPanel();
        panel.setBackground(background);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));

        JLabel title = new JLabel("SWAY");
        title.setFont(new Font("SansSerif", Font.BOLD, 40));
        title.setForeground(primary.darker());
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("Find Your Balance");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 15));
        subtitle.setForeground(textColor);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel tracker = new JLabel("Calorie Tracker");
        tracker.setFont(new Font("SansSerif", Font.BOLD, 18));
        tracker.setForeground(textColor);
        tracker.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Greeting label showing who's logged in
        JLabel greeting = new JLabel("👤 Logged in as: " + username);
        greeting.setFont(new Font("SansSerif", Font.PLAIN, 13));
        greeting.setForeground(new Color(100, 140, 120));
        greeting.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnAdd    = createButton("Food Entry",    primary);
        JButton btnManage = createButton("Manage Entry",  primary);
        JButton btnReport = createButton("Reports",       primary);
        JButton btnLogout = createButton("Logout",        primary.darker());

        panel.add(title);
        panel.add(Box.createVerticalStrut(8));
        panel.add(subtitle);
        panel.add(Box.createVerticalStrut(6));
        panel.add(tracker);
        panel.add(Box.createVerticalStrut(10));
        panel.add(greeting);
        panel.add(Box.createVerticalStrut(30));
        panel.add(btnAdd);
        panel.add(Box.createVerticalStrut(15));
        panel.add(btnManage);
        panel.add(Box.createVerticalStrut(15));
        panel.add(btnReport);
        panel.add(Box.createVerticalStrut(15));
        panel.add(btnLogout);

        add(panel);

        // Pass userId into every child screen
        btnAdd.addActionListener(e    -> new FoodEntry(userId).setVisible(true));
        btnManage.addActionListener(e -> new ManageEntry(userId).setVisible(true));
        btnReport.addActionListener(e -> new ReportScreen(userId).setVisible(true));
        btnLogout.addActionListener(e -> {
            dispose();
            new LoginScreen().setVisible(true);
        });
    }

    private JButton createButton(String text, Color bg) {
        JButton button = new JButton(text);
        button.setMaximumSize(new Dimension(280, 48));
        button.setBackground(bg);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("SansSerif", Font.BOLD, 15));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
    }

    public static void main(String[] args) {
        // For standalone testing only — use LoginScreen in production
        new HomeScreen(1, "testuser").setVisible(true);
    }
}