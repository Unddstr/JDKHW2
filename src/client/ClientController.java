package client;

import server.IServerController;

public class ClientController implements IClientController {
    IClientGUI clientGUI;
    IServerController serverController;

    private Boolean isConnected;

    public ClientController(IServerController serverController) {
        this.clientGUI = new ClientGUI(this, serverController);
        this.serverController = serverController;
        isConnected = false;
    }

    public void sendMessage() {
        if (serverController.getServerWorking() && isConnected) {
            serverController.printMsg(clientGUI.getTfLogin().getText() + ": " + clientGUI.getTfMessage().getText() + "\n");
        }
        clientGUI.tfMessageNull();
    }

    public void connect() {
        if (serverController.getServerWorking()) {
            clientGUI.connect();
            isConnected = true;
        } else {
            clientGUI.notConnected();
        }
    }
}
