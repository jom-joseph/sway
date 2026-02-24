import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class DeleteEntry extends JFrame {

    private final int userId;

    public DeleteEntry(int userId) {
        this.userId = userId;

        setTitle("DELETE FOOD ENTRY");
        setSize(420, 220);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        Color primary    = new Color(67, 181, 129);
        Color background = new Color(244, 253, 248);
        Color textColor  = new Color(30, 45, 47);

        // ── Main panel ──
        JPanel main = new JPanel(new BorderLayout());
        main.setBackground(background);
        main.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // ── Title ──
        JLabel title = new JLabel("DELETE FOOD ENTRY", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(primary.darker());
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        main.add(title, BorderLayout.NORTH);

        // ── Form panel ──
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(background);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill   = GridBagConstraints.HORIZONTAL;

        JTextField txtFood = new JTextField(18);

        // Row 0 — Food Name
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST; gbc.weightx = 0;
        JLabel lblFood = new JLabel("Food Name:");
        lblFood.setForeground(textColor);
        lblFood.setFont(new Font("SansSerif", Font.BOLD, 13));
        formPanel.add(lblFood, gbc);

        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST; gbc.weightx = 1;
        formPanel.add(txtFood, gbc);

        main.add(formPanel, BorderLayout.CENTER);

        // ── Bottom panel ──
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(background);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));

        JButton btnDelete = new JButton("Delete");
        btnDelete.setBackground(primary);
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnDelete.setFocusPainted(false);
        btnDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnDelete.setPreferredSize(new Dimension(100, 36));

        JButton btnBack = new JButton("Back");
        btnBack.setBackground(primary.darker());
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnBack.setFocusPainted(false);
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBack.setPreferredSize(new Dimension(100, 36));

        JPanel deleteWrap = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        deleteWrap.setBackground(background);
        deleteWrap.add(btnDelete);

        JPanel backWrap = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        backWrap.setBackground(background);
        backWrap.add(btnBack);

        bottomPanel.add(deleteWrap, BorderLayout.WEST);
        bottomPanel.add(backWrap,   BorderLayout.EAST);

        main.add(bottomPanel, BorderLayout.SOUTH);

        add(main);

        // ── Actions ──
        btnDelete.addActionListener(e -> {
            try (Connection con = DBConnection.getConnection()) {
                String sql = "DELETE FROM food_entries WHERE food_name = ? AND user_id = ?";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, txtFood.getText().trim());
                pst.setInt(2, userId);
                int rows = pst.executeUpdate();
                if (rows > 0)
                    JOptionPane.showMessageDialog(this, "Deleted Successfully!");
                else
                    JOptionPane.showMessageDialog(this, "Food not found in your entries!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        btnBack.addActionListener(e -> dispose());
    }
}