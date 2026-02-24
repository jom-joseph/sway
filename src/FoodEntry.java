import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.time.LocalDate;

public class FoodEntry extends JFrame {

    private final int userId;

    public FoodEntry(int userId) {
        this.userId = userId;

        setTitle("FOOD ENTRY");
        setSize(420, 300);
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
        JLabel title = new JLabel("FOOD ENTRY", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(primary.darker());
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        main.add(title, BorderLayout.NORTH);

        // ── Form panel — GridBagLayout for label-right / field-left alignment ──
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(background);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill   = GridBagConstraints.HORIZONTAL;

        JTextField txtFood     = new JTextField(18);
        JTextField txtCalories = new JTextField(18);
        JTextField txtDate     = new JTextField(LocalDate.now().toString(), 18);

        // Row 0 — Food Name
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST; gbc.weightx = 0;
        JLabel lblFood = new JLabel("Food Name:");
        lblFood.setForeground(textColor);
        lblFood.setFont(new Font("SansSerif", Font.BOLD, 13));
        formPanel.add(lblFood, gbc);

        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST; gbc.weightx = 1;
        formPanel.add(txtFood, gbc);

        // Row 1 — Calories
        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST; gbc.weightx = 0;
        JLabel lblCalories = new JLabel("Calories:");
        lblCalories.setForeground(textColor);
        lblCalories.setFont(new Font("SansSerif", Font.BOLD, 13));
        formPanel.add(lblCalories, gbc);

        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST; gbc.weightx = 1;
        formPanel.add(txtCalories, gbc);

        // Row 2 — Date
        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.EAST; gbc.weightx = 0;
        JLabel lblDate = new JLabel("Date (YYYY-MM-DD):");
        lblDate.setForeground(textColor);
        lblDate.setFont(new Font("SansSerif", Font.BOLD, 13));
        formPanel.add(lblDate, gbc);

        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST; gbc.weightx = 1;
        formPanel.add(txtDate, gbc);

        main.add(formPanel, BorderLayout.CENTER);

        // ── Bottom panel: Add (below fields) + Back (bottom right) ──
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(background);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));

        JButton btnAdd = new JButton("Add");
        btnAdd.setBackground(primary);
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnAdd.setFocusPainted(false);
        btnAdd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAdd.setPreferredSize(new Dimension(100, 36));

        JButton btnBack = new JButton("Back");
        btnBack.setBackground(primary.darker());
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnBack.setFocusPainted(false);
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBack.setPreferredSize(new Dimension(100, 36));

        // Add button — left side of bottom (aligned under fields)
        JPanel addWrap = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        addWrap.setBackground(background);
        addWrap.add(btnAdd);

        // Back button — right side of bottom
        JPanel backWrap = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        backWrap.setBackground(background);
        backWrap.add(btnBack);

        bottomPanel.add(addWrap,  BorderLayout.WEST);
        bottomPanel.add(backWrap, BorderLayout.EAST);

        main.add(bottomPanel, BorderLayout.SOUTH);

        add(main);

        // ── Actions ──
        btnAdd.addActionListener(e -> {
            try (Connection con = DBConnection.getConnection()) {
                String sql = "INSERT INTO food_entries (user_id, food_name, calories, entry_date) VALUES (?, ?, ?, ?)";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, userId);
                pst.setString(2, txtFood.getText().trim());
                pst.setInt(3, Integer.parseInt(txtCalories.getText().trim()));
                pst.setDate(4, Date.valueOf(txtDate.getText().trim()));
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Food Added!");
                txtFood.setText("");
                txtCalories.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        btnBack.addActionListener(e -> dispose());
    }
}