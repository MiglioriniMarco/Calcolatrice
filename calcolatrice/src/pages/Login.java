package pages;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import database.DBConnection;

public class Login {
    private JPanel login;
    private JTextField username;
    private JTextField password;
    private JLabel user;
    private JLabel pwd;
    private JButton LOGINButton;
    private JButton REGISTRATIButton;

    public Login() {
        LOGINButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean registrato = verificaCredenziali(username.getText(), password.getText());

                if (registrato) {
                    JFrame calcolatrice = new JFrame("Calcolatrice");
                    calcolatrice.setContentPane(new calcolatrice().panel);
                    calcolatrice.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    calcolatrice.pack();
                    calcolatrice.setSize(400, 400);
                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    int x = (screenSize.width - calcolatrice.getWidth()) / 2;
                    int y = (screenSize.height - calcolatrice.getHeight()) / 2;
                    calcolatrice.setLocation(x, y);
                    calcolatrice.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Credenziali non valide. Riprova o registrati");
                }
            }
        });

        REGISTRATIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame registrazioneFrame = new JFrame("Registrazione");
                registrazioneFrame.setContentPane(new registration().registrazione);
                registrazioneFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Chiudi solo la finestra di registrazione
                registrazioneFrame.pack();
                registrazioneFrame.setSize(350, 350);
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                int x = (screenSize.width - registrazioneFrame.getWidth()) / 2;
                int y = (screenSize.height - registrazioneFrame.getHeight()) / 2;
                registrazioneFrame.setLocation(x, y);
                registrazioneFrame.setVisible(true);
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(login);
                frame.dispose();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(new Login().login);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(350, 350);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);
        frame.setVisible(true);
    }

    private boolean verificaCredenziali(String username, String pwd) {
        Connection connection = DBConnection.getConnection();

        try {
            String query = "SELECT ID, user_name, password FROM utenti WHERE user_name = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String Password = resultSet.getString("password");

                if (pwd.equals(Password)) {
                    DBConnection.setUserID(resultSet.getInt("ID"));
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
