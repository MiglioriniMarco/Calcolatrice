package pages;

import database.DBConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class registration {
    JPanel registrazione;
    private JLabel user;
    private JLabel pass;
    private JTextField username;
    private JTextField pwd;
    private JButton REGISTRATIButton;

    public registration() {
        REGISTRATIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean registrato = registra(username.getText(), pwd.getText());

                if (registrato) {
                    JOptionPane.showMessageDialog(null, "Registrazione riuscita!");
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(registrazione);
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Registrazione NON riuscita!");
                }
            }
        });
    }

    private boolean registra(String username, String password) {
        Connection connection = DBConnection.getConnection();

        try {
            String query = "INSERT INTO utenti (user_name, password) VALUES (?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
