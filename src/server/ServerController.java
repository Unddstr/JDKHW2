package server;

import client.IClientGUI;

import java.io.*;
import java.util.ArrayList;

public class ServerController implements IServerController {
    private final IServerGUI serverGUI;

    private boolean isServerWorking;

    private final ArrayList<IClientGUI> lClient = new ArrayList<>();

    File file = new File("src/server/log.txt");

    public ServerController() {
        serverGUI = new ServerGUI(this);
        isServerWorking = false;
    }

    public void loadServer() {
        isServerWorking = true;
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while ((line = reader.readLine()) != null) {
                serverGUI.printMessage(line + "\n");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void stopServer() {
        isServerWorking = false;
        for (IClientGUI client : lClient) {
            client.disconnect();
        }
        lClient.clear();
    }

    public void newClient(IClientGUI clientGui) {
        lClient.add(clientGui);
    }

    public boolean getServerWorking() {
        return isServerWorking;
    }

    public void printMsg(String msg) {
        serverGUI.printMessage(msg);
        logging(msg);
        for (IClientGUI client : lClient) {
            client.printMessage(msg);
        }
    }

    public void logging(String msg) {
        try (FileWriter fw = new FileWriter(file, true)) {
            fw.write(msg);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public File getFile() {
        return file;
    }
}
