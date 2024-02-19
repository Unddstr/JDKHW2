package client;

import javax.swing.*;

public interface IClientGUI {
    JTextField getTfLogin();

    JTextField getTfMessage();

    void tfMessageNull();

    void connect();

    void notConnected();

    void disconnect();

    void printMessage(String msg);
}
