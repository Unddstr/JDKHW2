package client;

import server.IServerController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ClientGUI extends JFrame implements IClientGUI {
    private final IClientController clientController;
    private final IServerController serverController;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private final JTextArea textArea = new JTextArea();

    private final JPanel panelTop = new JPanel(new GridLayout(2, 3));
    private final JTextField tfIPAddress = new JTextField("127.0.0.1");
    private final JTextField tfPort = new JTextField("8189");
    private final JTextField tfLogin = new JTextField("User");
    private final JPasswordField tfPassword = new JPasswordField("123456");
    private final JButton btnLogin = new JButton("Login");

    private final JPanel panelBottom = new JPanel(new BorderLayout());
    private final JTextField tfMessage = new JTextField();
    private final JButton btnSend = new JButton("Send");

    public ClientGUI(IClientController clientController, IServerController serverController) {
        this.clientController = clientController;
        this.serverController = serverController;
        createPanel();

        setVisible(true);
    }

    private void createPanel() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDTH, HEIGHT);
        setTitle("Chat Client");

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientController.connect();
            }
        });

        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientController.sendMessage();
            }
        });

        panelTop.add(tfIPAddress);
        panelTop.add(tfPort);
        panelTop.add(tfLogin);
        panelTop.add(tfPassword);
        panelTop.add(btnLogin);
        add(panelTop, BorderLayout.NORTH);

        panelBottom.add(tfMessage, BorderLayout.CENTER);
        panelBottom.add(btnSend, BorderLayout.EAST);
        add(panelBottom, BorderLayout.SOUTH);

        textArea.setEditable(false);
        JScrollPane scrollLog = new JScrollPane(textArea);
        add(scrollLog);
    }

    public void printMessage(String msg) {
        textArea.append(msg);
    }

    public void connect() {
        this.serverController.newClient(this);
        textArea.append("Вы успешно подключились! \n");
        panelTop.setVisible(false);
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(serverController.getFile()))) {
            while ((line = reader.readLine()) != null) {
                printMessage(line + "\n");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void notConnected() {
        textArea.append("Подключение не удалось! \n");
    }

    public void disconnect() {
        textArea.append("Вы были отключены от сервера! \n");
        panelTop.setVisible(true);
    }

    public JTextField getTfLogin() {
        return tfLogin;
    }

    public JTextField getTfMessage() {
        return tfMessage;
    }

    public void tfMessageNull() {
        tfMessage.setText(null);
    }
}
