import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LayoutDemoApp extends JFrame {

    public LayoutDemoApp() {
        setTitle("Layout Demo App");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel menuPanel = new JPanel(new GridLayout(6, 1, 10, 10));

        JButton flowBtn = new JButton("FlowLayout");
        JButton borderBtn = new JButton("BorderLayout");
        JButton gridBtn = new JButton("GridLayout");
        JButton boxBtn = new JButton("BoxLayout");
        JButton cardBtn = new JButton("CardLayout");
        JButton gridBagBtn = new JButton("GridBagLayout");

        flowBtn.addActionListener(this::showFlowLayoutDemo);
        borderBtn.addActionListener(this::showBorderLayoutDemo);
        gridBtn.addActionListener(this::showGridLayoutDemo);
        boxBtn.addActionListener(this::showBoxLayoutDemo);
        cardBtn.addActionListener(this::showCardLayoutDemo);
        gridBagBtn.addActionListener(this::showGridBagLayoutDemo);

        menuPanel.add(flowBtn);
        menuPanel.add(borderBtn);
        menuPanel.add(gridBtn);
        menuPanel.add(boxBtn);
        menuPanel.add(cardBtn);
        menuPanel.add(gridBagBtn);

        getContentPane().add(menuPanel);
    }

    private JDialog createDialog(String title) {
        JDialog dialog = new JDialog(this, title, false);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);
        return dialog;
    }

    private void showFlowLayoutDemo(ActionEvent e) {
        JDialog dialog = createDialog("FlowLayout");
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(new JLabel("FlowLayout: elementy w rzedzie, zawijanie po braku miejsca"));
        for (int i = 1; i <= 8; i++) {
            panel.add(new JButton("Przycisk " + i));
        }
        dialog.add(panel);
        dialog.setVisible(true);
    }

    private void showBorderLayoutDemo(ActionEvent e) {
        JDialog dialog = createDialog("BorderLayout");
        dialog.setLayout(new BorderLayout());
        dialog.add(new JLabel("NORTH", SwingConstants.CENTER), BorderLayout.NORTH);
        dialog.add(new JLabel("SOUTH", SwingConstants.CENTER), BorderLayout.SOUTH);
        dialog.add(new JLabel("EAST", SwingConstants.CENTER), BorderLayout.EAST);
        dialog.add(new JLabel("WEST", SwingConstants.CENTER), BorderLayout.WEST);
        JLabel center = new JLabel("CENTER - rozciaga sie", SwingConstants.CENTER);
        center.setOpaque(true);
        center.setBackground(Color.LIGHT_GRAY);
        dialog.add(center, BorderLayout.CENTER);
        dialog.setVisible(true);
    }

    private void showGridLayoutDemo(ActionEvent e) {
        JDialog dialog = createDialog("GridLayout");
        JPanel panel = new JPanel(new GridLayout(3, 3, 5, 5));
        for (int i = 1; i <= 9; i++) {
            panel.add(new JButton("Kom. " + i));
        }
        dialog.add(panel);
        dialog.setVisible(true);
    }

    private void showBoxLayoutDemo(ActionEvent e) {
        JDialog dialog = createDialog("BoxLayout");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Element 1"));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(new JLabel("Element 2"));
        panel.add(Box.createVerticalGlue());
        panel.add(new JLabel("Element 3 (na dole)"));
        dialog.add(panel);
        dialog.setVisible(true);
    }

    private void showCardLayoutDemo(ActionEvent e) {
        JDialog dialog = createDialog("CardLayout");
        CardLayout cardLayout = new CardLayout();
        JPanel cardsPanel = new JPanel(cardLayout);

        JPanel card1 = new JPanel();
        card1.add(new JLabel("Karta 1 - Krok pierwszy"));
        JPanel card2 = new JPanel();
        card2.add(new JLabel("Karta 2 - Krok drugi"));
        JPanel card3 = new JPanel();
        card3.add(new JLabel("Karta 3 - Krok trzeci"));

        cardsPanel.add(card1, "1");
        cardsPanel.add(card2, "2");
        cardsPanel.add(card3, "3");

        JPanel controlPanel = new JPanel(new FlowLayout());
        JButton prevBtn = new JButton("Wstecz");
        JButton nextBtn = new JButton("Dalej");
        prevBtn.addActionListener(ev -> cardLayout.previous(cardsPanel));
        nextBtn.addActionListener(ev -> cardLayout.next(cardsPanel));
        controlPanel.add(prevBtn);
        controlPanel.add(nextBtn);

        dialog.setLayout(new BorderLayout());
        dialog.add(cardsPanel, BorderLayout.CENTER);
        dialog.add(controlPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    private void showGridBagLayoutDemo(ActionEvent e) {
        JDialog dialog = createDialog("GridBagLayout");
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Login:"), gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panel.add(new JTextField(15), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Haslo:"), gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panel.add(new JPasswordField(15), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(new JButton("Zaloguj"), gbc);

        gbc.gridx = 2;
        panel.add(new JButton("Anuluj"), gbc);

        dialog.add(panel);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LayoutDemoApp().setVisible(true));
    }
}