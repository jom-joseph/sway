import javax.swing.*;
import java.awt.*;

public class ManageEntry extends JFrame {

    private final int userId;

    public ManageEntry(int userId) {
        this.userId = userId;

        setTitle("MANAGE ENTRY");
        setSize(350, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        Color primary    = new Color(67, 181, 129);
        Color background = new Color(244, 253, 248);

        JPanel panel = new JPanel();
        panel.setBackground(background);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        JLabel title = new JLabel("MANAGE ENTRY");
        title.setFont(new Font("SansSerif", Font.BOLD, 28));
        title.setForeground(primary.darker());
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(title);
        panel.add(Box.createVerticalStrut(30));

        JButton btnUpdate = new JButton("Update Entry");
        JButton btnDelete = new JButton("Delete Entry");
        JButton btnBack   = new JButton("Back");

        JButton[] buttons = {btnUpdate, btnDelete, btnBack};
        for (JButton b : buttons) {
            b.setAlignmentX(Component.CENTER_ALIGNMENT);
            b.setBackground(primary);
            b.setForeground(Color.WHITE);
            b.setFocusPainted(false);
        }
        btnBack.setBackground(primary.darker());

        panel.add(btnUpdate);
        panel.add(Box.createVerticalStrut(15));
        panel.add(btnDelete);
        panel.add(Box.createVerticalStrut(15));
        panel.add(btnBack);

        add(panel);

        // Pass userId so updates/deletes only affect this user's entries
        btnUpdate.addActionListener(e -> new UpdateEntry(userId).setVisible(true));
        btnDelete.addActionListener(e -> new DeleteEntry(userId).setVisible(true));
        btnBack.addActionListener(e   -> dispose());
    }
}