import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.*;
import java.time.LocalDate;

public class ReportScreen extends JFrame {

    private final int userId;

    public ReportScreen(int userId) {
        this.userId = userId;

        setTitle("DAILY REPORT");
        setSize(500, 420);
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
        JLabel title = new JLabel("DAILY REPORT", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(primary.darker());
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        main.add(title, BorderLayout.NORTH);

        // ── Center: form + table ──
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(background);

        // Form row — label right, field left using GridBagLayout
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(background);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 8, 4, 8);
        gbc.fill   = GridBagConstraints.HORIZONTAL;

        JTextField txtDate = new JTextField(LocalDate.now().toString(), 18);

        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST; gbc.weightx = 0;
        JLabel lblDate = new JLabel("Date (YYYY-MM-DD):");
        lblDate.setForeground(textColor);
        lblDate.setFont(new Font("SansSerif", Font.BOLD, 13));
        formPanel.add(lblDate, gbc);

        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST; gbc.weightx = 1;
        formPanel.add(txtDate, gbc);

        centerPanel.add(formPanel, BorderLayout.NORTH);

        // ── Table ──
        String[] columns = {"Food Name", "Calories"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        JTable table = new JTable(model);
        table.setFont(new Font("SansSerif", Font.PLAIN, 13));
        table.setRowHeight(24);
        table.setBackground(Color.WHITE);
        table.setForeground(textColor);
        table.setGridColor(new Color(210, 235, 220));
        table.setSelectionBackground(new Color(180, 230, 205));

        // Header styling
        JTableHeader header = table.getTableHeader();
        header.setBackground(primary);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("SansSerif", Font.BOLD, 13));

        // Center-align calories column
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(180, 210, 195)));
        scroll.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        centerPanel.add(scroll, BorderLayout.CENTER);

        main.add(centerPanel, BorderLayout.CENTER);

        // ── Bottom panel: View (left) + Back (right) ──
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(background);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(12, 0, 0, 0));

        JButton btnView = new JButton("View");
        btnView.setBackground(primary);
        btnView.setForeground(Color.WHITE);
        btnView.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnView.setFocusPainted(false);
        btnView.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnView.setPreferredSize(new Dimension(100, 36));

        JButton btnBack = new JButton("Back");
        btnBack.setBackground(primary.darker());
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnBack.setFocusPainted(false);
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBack.setPreferredSize(new Dimension(100, 36));

        JPanel viewWrap = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        viewWrap.setBackground(background);
        viewWrap.add(btnView);

        JPanel backWrap = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        backWrap.setBackground(background);
        backWrap.add(btnBack);

        bottomPanel.add(viewWrap, BorderLayout.WEST);
        bottomPanel.add(backWrap, BorderLayout.EAST);

        main.add(bottomPanel, BorderLayout.SOUTH);

        add(main);

        // ── View action ──
        btnView.addActionListener(e -> {
            model.setRowCount(0);
            int totalCalories = 0;
            try (Connection con = DBConnection.getConnection()) {
                String sql = "SELECT food_name, calories FROM food_entries WHERE user_id = ? AND entry_date = ?";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, userId);
                pst.setDate(2, Date.valueOf(txtDate.getText().trim()));
                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    String food = rs.getString("food_name");
                    int    cal  = rs.getInt("calories");
                    totalCalories += cal;
                    model.addRow(new Object[]{food, cal});
                }

                if (model.getRowCount() == 0) {
                    model.addRow(new Object[]{"No entries found", "-"});
                } else {
                    model.addRow(new Object[]{"TOTAL CALORIES", totalCalories});
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        btnBack.addActionListener(e -> dispose());
    }
}