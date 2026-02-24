import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class UpdateEntry extends JFrame {

    private final int userId;

    public UpdateEntry(int userId) {
        this.userId = userId;

        setTitle("UPDATE FOOD ENTRY");
        setSize(420, 260);
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
        JLabel title = new JLabel("UPDATE FOOD ENTRY", SwingConstants.CENTER);
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

        JTextField txtFood     = new JTextField(18);
        JTextField txtCalories = new JTextField(18);

        // Row 0 — Food Name
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST; gbc.weightx = 0;
        JLabel lblFood = new JLabel("Food Name:");
        lblFood.setForeground(textColor);
        lblFood.setFont(new Font("SansSerif", Font.BOLD, 13));
        formPanel.add(lblFood, gbc);

        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST; gbc.weightx = 1;
        formPanel.add(txtFood, gbc);

        // Row 1 — New Calories
        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST; gbc.weightx = 0;
        JLabel lblCalories = new JLabel("New Calories:");
        lblCalories.setForeground(textColor);
        lblCalories.setFont(new Font("SansSerif", Font.BOLD, 13));
        formPanel.add(lblCalories, gbc);

        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST; gbc.weightx = 1;
        formPanel.add(txtCalories, gbc);

        main.add(formPanel, BorderLayout.CENTER);

        // ── Bottom panel ──
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(background);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));

        JButton btnUpdate = new JButton("Update");
        btnUpdate.setBackground(primary);
        btnUpdate.setForeground(Color.WHITE);
        btnUpdate.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnUpdate.setFocusPainted(false);
        btnUpdate.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnUpdate.setPreferredSize(new Dimension(100, 36));

        JButton btnBack = new JButton("Back");
        btnBack.setBackground(primary.darker());
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnBack.setFocusPainted(false);
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBack.setPreferredSize(new Dimension(100, 36));

        JPanel updateWrap = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        updateWrap.setBackground(background);
        updateWrap.add(btnUpdate);

        JPanel backWrap = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        backWrap.setBackground(background);
        backWrap.add(btnBack);

        bottomPanel.add(updateWrap, BorderLayout.WEST);
        bottomPanel.add(backWrap,   BorderLayout.EAST);

        main.add(bottomPanel, BorderLayout.SOUTH);

        add(main);

        // ── Actions ──
        btnUpdate.addActionListener(e -> {
            try (Connection con = DBConnection.getConnection()) {
                String sql = "UPDATE food_entries SET calories = ? WHERE food_name = ? AND user_id = ?";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, Integer.parseInt(txtCalories.getText().trim()));
                pst.setString(2, txtFood.getText().trim());
                pst.setInt(3, userId);
                int rows = pst.executeUpdate();
                if (rows > 0)
                    JOptionPane.showMessageDialog(this, "Updated Successfully!");
                else
                    JOptionPane.showMessageDialog(this, "Food not found in your entries!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        btnBack.addActionListener(e -> dispose());
    }
}