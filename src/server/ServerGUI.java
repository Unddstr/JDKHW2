package server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerGUI extends JFrame implements IServerGUI {
    IServerController serverController;
    private static final int POS_X = 500;
    private static final int POS_Y = 550;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private final JTextArea textArea = new JTextArea();

    private final JPanel panelBottom = new JPanel(new GridLayout(1, 2));
    private final JButton btnStart = new JButton("Start");
    private final JButton btnStop = new JButton("Stop");

    public ServerGUI(IServerController serverController) {
        this.serverController = serverController;
        createPanel();

        setVisible(true);
    }

    private void createPanel() {
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                serverController.stopServer();
                textArea.append("Сервер остановлен! \n");
            }
        });

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText(null);
                serverController.loadServer();
                textArea.append("Сервер запущен! \n");
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setAlwaysOnTop(true);

        panelBottom.add(btnStart);
        panelBottom.add(btnStop);
        add(panelBottom, BorderLayout.SOUTH);

        textArea.setEditable(false);
        JScrollPane scrollLog = new JScrollPane(textArea);
        add(scrollLog);
    }

    public void printMessage(String msg) {
        textArea.append(msg);
    }
}
