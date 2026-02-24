import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginScreen extends JFrame {

    public LoginScreen() {

        setTitle("SWAY - Login");
        setSize(420, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        Color primary     = new Color(67, 181, 129);
        Color primaryDark = primary.darker();
        Color background  = new Color(244, 253, 248);
        Color textColor   = new Color(30, 45, 47);
        Color subtle      = new Color(180, 210, 195);
        Color errorRed    = new Color(220, 80, 80);

        JPanel root = new JPanel();
        root.setBackground(background);
        root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
        root.setBorder(new EmptyBorder(50, 60, 40, 60));

        // ── App name ──
        JLabel appName = new JLabel("SWAY");
        appName.setFont(new Font("SansSerif", Font.BOLD, 38));
        appName.setForeground(primaryDark);
        appName.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ── Tagline ──
        JLabel tagline = new JLabel("Find Your Balance");
        tagline.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tagline.setForeground(new Color(100, 140, 120));
        tagline.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ── Sign in label ──
        JLabel loginTitle = new JLabel("Sign in to continue");
        loginTitle.setFont(new Font("SansSerif", Font.BOLD, 15));
        loginTitle.setForeground(textColor);
        loginTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ── Form panel ──
        JPanel formPanel = new JPanel();
        formPanel.setBackground(background);
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Username label
        JLabel lblUser = new JLabel("Username");
        lblUser.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblUser.setForeground(textColor);
        lblUser.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Username field
        JTextField txtUser = new JTextField();
        txtUser.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtUser.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txtUser.setPreferredSize(new Dimension(300, 40));
        txtUser.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(subtle, 1, true),
                new EmptyBorder(6, 12, 6, 12)));
        txtUser.setBackground(Color.WHITE);
        txtUser.setForeground(textColor);
        txtUser.setAlignmentX(Component.LEFT_ALIGNMENT);
        txtUser.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                txtUser.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(primary, 2, true), new EmptyBorder(5, 11, 5, 11)));
            }
            public void focusLost(FocusEvent e) {
                txtUser.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(subtle, 1, true), new EmptyBorder(6, 12, 6, 12)));
            }
        });

        // Password label
        JLabel lblPass = new JLabel("Password");
        lblPass.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblPass.setForeground(textColor);
        lblPass.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Password field
        JPasswordField txtPass = new JPasswordField();
        txtPass.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtPass.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txtPass.setPreferredSize(new Dimension(300, 40));
        txtPass.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(subtle, 1, true),
                new EmptyBorder(6, 12, 6, 12)));
        txtPass.setBackground(Color.WHITE);
        txtPass.setForeground(textColor);
        txtPass.setAlignmentX(Component.LEFT_ALIGNMENT);
        txtPass.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                txtPass.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(primary, 2, true), new EmptyBorder(5, 11, 5, 11)));
            }
            public void focusLost(FocusEvent e) {
                txtPass.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(subtle, 1, true), new EmptyBorder(6, 12, 6, 12)));
            }
        });

        // Error label
        JLabel lblError = new JLabel(" ");
        lblError.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblError.setForeground(errorRed);
        lblError.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Login button
        JButton btnLogin = new JButton("Login");
        btnLogin.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        btnLogin.setBackground(primary);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("SansSerif", Font.BOLD, 15));
        btnLogin.setFocusPainted(false);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogin.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnLogin.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnLogin.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnLogin.setBackground(primaryDark); }
            public void mouseExited(MouseEvent e)  { btnLogin.setBackground(primary); }
        });

        // ── Build form panel ──
        formPanel.add(lblUser);
        formPanel.add(Box.createVerticalStrut(6));
        formPanel.add(txtUser);
        formPanel.add(Box.createVerticalStrut(14));
        formPanel.add(lblPass);
        formPanel.add(Box.createVerticalStrut(6));
        formPanel.add(txtPass);
        formPanel.add(Box.createVerticalStrut(6));
        formPanel.add(lblError);
        formPanel.add(Box.createVerticalStrut(16));
        formPanel.add(btnLogin);

        // ── Footer ──
        JLabel footer = new JLabel("© 2025 SWAY Calorie Tracker");
        footer.setFont(new Font("SansSerif", Font.PLAIN, 11));
        footer.setForeground(new Color(160, 190, 175));
        footer.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ── Assemble root (no logo) ──
        root.add(appName);
        root.add(Box.createVerticalStrut(4));
        root.add(tagline);
        root.add(Box.createVerticalStrut(28));
        root.add(loginTitle);
        root.add(Box.createVerticalStrut(20));
        root.add(formPanel);
        root.add(Box.createVerticalGlue());
        root.add(Box.createVerticalStrut(20));
        root.add(footer);

        add(root);

        // ── Login logic ──
        ActionListener loginAction = e -> {
            String username = txtUser.getText().trim();
            String password = new String(txtPass.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                lblError.setText("Please enter both username and password.");
                return;
            }

            try (Connection con = DBConnection.getConnection()) {
                String sql = "SELECT id, username FROM users WHERE username = ? AND password = ?";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, username);
                pst.setString(2, password);
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    int userId          = rs.getInt("id");
                    String loggedInUser = rs.getString("username");

                    lblError.setForeground(primary);
                    lblError.setText("Welcome back, " + loggedInUser + "!");

                    Timer timer = new Timer(700, ev -> {
                        dispose();
                        new HomeScreen(userId, loggedInUser).setVisible(true);
                    });
                    timer.setRepeats(false);
                    timer.start();
                } else {
                    lblError.setForeground(errorRed);
                    lblError.setText("Invalid username or password.");
                    txtPass.setText("");
                }
            } catch (Exception ex) {
                lblError.setForeground(errorRed);
                lblError.setText("Connection error: " + ex.getMessage());
            }
        };

        btnLogin.addActionListener(loginAction);
        txtPass.addActionListener(loginAction);
        txtUser.addActionListener(ev -> txtPass.requestFocus());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginScreen().setVisible(true));
    }
}