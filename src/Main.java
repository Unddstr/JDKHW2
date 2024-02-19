import client.ClientController;
import server.IServerController;
import server.ServerController;

public class Main {
    public static void main(String[] args) {
        IServerController serverController = new ServerController();
        new ClientController(serverController);
        new ClientController(serverController);
    }
}
