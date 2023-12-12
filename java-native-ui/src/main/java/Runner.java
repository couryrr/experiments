import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class Runner {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());

            var frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new GridBagLayout());

            var constraints = new GridBagConstraints();

            var headerPanel = new JPanel();
            headerPanel.setPreferredSize(new Dimension(250, 100));
            var headerTextLabel = new JLabel("Application Login", SwingConstants.CENTER);
            headerTextLabel.setFont(new Font("Roboto", Font.BOLD, 25));
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.gridwidth = 2;
            constraints.fill = GridBagConstraints.HORIZONTAL;
            headerPanel.add(headerTextLabel);
            frame.add(headerPanel, constraints);

            var formPanel = new JPanel();
            formPanel.setLayout(new GridLayout(0, 2));

            var leftTextFieldPanel = new JPanel();
            leftTextFieldPanel.setLayout(new FlowLayout());
            var leftTextLabel = new JLabel("User Name");
            var leftTextField = new JTextField();
            leftTextField.setPreferredSize(new Dimension(150,20));
            leftTextFieldPanel.add(leftTextLabel);
            leftTextFieldPanel.add(leftTextField);
            formPanel.add(leftTextFieldPanel);

            var rightTextFieldPanel = new JPanel();
            rightTextFieldPanel.setLayout(new FlowLayout());
            var rightTextLabel = new JLabel("Password");
            var rightTextField = new JPasswordField();
            rightTextField.setPreferredSize(new Dimension(150,20));
            rightTextFieldPanel.add(rightTextLabel);
            rightTextFieldPanel.add(rightTextField);
            formPanel.add(rightTextFieldPanel);

            constraints.gridx = 0;
            constraints.gridy = 1;
            frame.add(formPanel, constraints);

            constraints.gridx = 0;
            constraints.gridy = 2;
            constraints.gridwidth = 2;
            constraints.fill = GridBagConstraints.HORIZONTAL;

            var formButton = new JButton("Test");
            formButton.addActionListener(new ButtonListener());
            frame.add(formButton, constraints);

            frame.setSize(500, 500);
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
}
