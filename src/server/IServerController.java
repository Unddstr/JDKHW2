package server;

import client.IClientGUI;

import java.io.File;

public interface IServerController {
    void stopServer();

    void loadServer();

    void newClient(IClientGUI clientGUI);

    File getFile();

    boolean getServerWorking();

    void printMsg(String s);
}
