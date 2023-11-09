package pages;

import database.DBConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class calcolatrice {
    JPanel panel;
    private JLabel display;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JButton plus;
    private JButton minus;
    private JButton X;
    private JButton uguale;
    private JButton diviso;
    private JButton punto;
    private JButton button0;
    private JButton delete;
    private JButton apriParentesi;
    private JButton chiudiParentesi;
    private JRadioButton RPNRadioButton;
    private JRadioButton infissaRadioButton;
    private JButton cronologia;

    private StringBuilder currentEspressione = new StringBuilder();

    public calcolatrice() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (RPNRadioButton.isSelected())
                    currentEspressione.append("1 ");
                else
                    currentEspressione.append("1");
                display.setText(currentEspressione.toString());
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (RPNRadioButton.isSelected())
                    currentEspressione.append("2 ");
                else
                    currentEspressione.append("2");
                display.setText(currentEspressione.toString());
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (RPNRadioButton.isSelected())
                    currentEspressione.append("3 ");
                else
                    currentEspressione.append("3");
                display.setText(currentEspressione.toString());
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (RPNRadioButton.isSelected())
                    currentEspressione.append("4 ");
                else
                    currentEspressione.append("4");
                display.setText(currentEspressione.toString());
            }
        });

        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (RPNRadioButton.isSelected())
                    currentEspressione.append("5 ");
                else
                    currentEspressione.append("5");
                display.setText(currentEspressione.toString());
            }
        });

        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (RPNRadioButton.isSelected())
                    currentEspressione.append("6 ");
                else
                    currentEspressione.append("6");
                display.setText(currentEspressione.toString());
            }
        });

        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (RPNRadioButton.isSelected())
                    currentEspressione.append("7 ");
                else
                    currentEspressione.append("7");
                display.setText(currentEspressione.toString());
            }
        });

        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (RPNRadioButton.isSelected())
                    currentEspressione.append("8 ");
                else
                    currentEspressione.append("8");
                display.setText(currentEspressione.toString());
            }
        });

        button9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (RPNRadioButton.isSelected())
                    currentEspressione.append("9 ");
                else
                    currentEspressione.append("9");
                display.setText(currentEspressione.toString());
            }
        });

        button0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (RPNRadioButton.isSelected())
                    currentEspressione.append("0 ");
                else
                    currentEspressione.append("0");
                display.setText(currentEspressione.toString());
            }
        });

        punto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentEspressione.append(".");
                display.setText(currentEspressione.toString());
            }
        });

        plus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentEspressione.append(" + ");
                display.setText(currentEspressione.toString());
            }
        });

        minus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentEspressione.append(" - ");
                display.setText(currentEspressione.toString());
            }
        });

        X.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentEspressione.append(" * ");
                display.setText(currentEspressione.toString());
            }
        });

        diviso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentEspressione.append(" / ");
                display.setText(currentEspressione.toString());
            }
        });

        apriParentesi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentEspressione.append("(");
                display.setText(currentEspressione.toString());
            }
        });

        chiudiParentesi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentEspressione.append(")");
                display.setText(currentEspressione.toString());
            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentEspressione.length() > 0) {
                    currentEspressione.delete(0, currentEspressione.length());
                    display.setText("Calcola...");
                }
            }
        });

        uguale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String espressioneCorrente = currentEspressione.toString();
                if (infissaRadioButton.isSelected()) {
                    String espressioneRPN = traduciRPN(espressioneCorrente);
                    display.setText(espressioneRPN);
                    try {
                        float result = calcolaRPN(espressioneRPN);
                        display.setText(String.valueOf(result));
                        inserisci(espressioneRPN, DBConnection.getUserID());
                    } catch (Exception ex) {
                        display.setText("Errore nell'espressione");
                    }
                }
                else {
                    try {
                        float result = calcolaRPN(espressioneCorrente);
                        display.setText(String.valueOf(result));
                        inserisci(espressioneCorrente, DBConnection.getUserID());
                    } catch (Exception ex) {
                        display.setText("Errore nell'espressione");
                    }
                }


            }
        });
        infissaRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (RPNRadioButton.isSelected())
                    RPNRadioButton.setSelected(false);
            }
        });
        RPNRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (infissaRadioButton.isSelected())
                    infissaRadioButton.setSelected(false);
            }
        });
        cronologia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection connection = DBConnection.getConnection();

                List<String> operazioniList = new ArrayList<>();

                try {
                    String query = "SELECT operazione FROM operazioni WHERE Id_utente = ?";

                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, DBConnection.getUserID());

                    ResultSet resultSet = preparedStatement.executeQuery();

                    while (resultSet.next()) {
                        operazioniList.add(resultSet.getString("operazione"));
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                // Converti la lista di operazioni in una stringa
                String operazioni = String.join("\n", operazioniList);

                JOptionPane.showMessageDialog(null, "Cronologia operazioni di " + DBConnection.getUser() + ":\n" + operazioni);
            }
        });
    }

    public boolean inserisci(String rpn, int id_utente) {
        Connection connection = DBConnection.getConnection();

        try {
            String query = "INSERT INTO operazioni (Id_utente, operazione) VALUES (?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id_utente);
            preparedStatement.setString(2, rpn);

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private float calcolaRPN(String rpn) {
        Stack<Float> stack = new Stack<>();
        String[] s = rpn.split(" ");

        for (String c : s) {
            if (isDigit(c)) {
                stack.push(Float.parseFloat(c));
            } else if (operatore(c)) {
                float operando2 = stack.pop();
                float operando1 = stack.pop();
                float risultato = operazione(operando1, operando2, c);
                stack.push(risultato);
            }
        }

        if (stack.size() == 1) {
            return stack.pop();
        } else {
            throw new IllegalArgumentException("Espressione RPN non valida");
        }
    }


    private boolean isDigit(String str) {
        try {
            Float.parseFloat(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean operatore(String str) {
        return str.matches("[-+*/]");
    }

    private float operazione(float operando1, float operando2, String operatore) {
        switch (operatore) {
            case "+":
                return operando1 + operando2;
            case "-":
                return operando1 - operando2;
            case "*":
                return operando1 * operando2;
            case "/":
                if (operando2 == 0) {
                    throw new ArithmeticException("Divisione per zero");
                }
                return operando1 / operando2;
            default:
                throw new IllegalArgumentException("Operatore non valido: " + operatore);
        }
    }

    private String traduciRPN(String espressioneInfissa) {
        // Aggiungi spazi tra operatori e numeri (compresi i numeri con doppia cifra)
        espressioneInfissa = espressioneInfissa.replaceAll("([\\+\\-\\*\\/\\(\\)])", " $1 ");

        // Aggiungi spazi tra cifre contigue in modo che i numeri con doppia cifra siano trattati correttamente
        espressioneInfissa = espressioneInfissa.replaceAll("([0-9]+)", "$1 ");

        StringBuilder rpn = new StringBuilder();
        Stack<Character> stackOperatori = new Stack<>();
        String[] s = espressioneInfissa.split(" ");

        for (String c : s) {
            if (isDigit(c)) {
                rpn.append(c).append(" ");
            } else if ("(".equals(c)) {
                stackOperatori.push('(');
            } else if (")".equals(c)) {
                while (!stackOperatori.isEmpty() && stackOperatori.peek() != '(') {
                    rpn.append(stackOperatori.pop()).append(" ");
                }
                if (!stackOperatori.isEmpty() && stackOperatori.peek() == '(') {
                    stackOperatori.pop();
                } else {
                    throw new IllegalArgumentException("Espressione infissa non valida");
                }
            } else if (operatore(c)) {
                char operatore = c.charAt(0);
                while (!stackOperatori.isEmpty() && stackOperatori.peek() != '(' && precedenza(operatore) <= precedenza(stackOperatori.peek())) {
                    rpn.append(stackOperatori.pop()).append(" ");
                }
                stackOperatori.push(operatore);
            }
        }

        while (!stackOperatori.isEmpty()) {
            char operatore = stackOperatori.pop();
            if (operatore == '(' || operatore == ')') {
                throw new IllegalArgumentException("Espressione infissa non valida");
            }
            rpn.append(operatore).append(" ");
        }

        return rpn.toString().trim();
    }




    private int precedenza(char operatore) {
        switch (operatore) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '(':
            case ')':
                return 0;
            default:
                throw new IllegalArgumentException("Operatore non valido: " + operatore);
        }
    }




}