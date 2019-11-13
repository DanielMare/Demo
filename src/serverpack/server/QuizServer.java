package serverpack.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import serverpack.requesthandler.ClientHandler;

public class QuizServer {

    ClientHandler clientHandler;
    int counter = 0;

    public QuizServer() {
        try (ServerSocket serverSocket = new ServerSocket(8888)) {
            System.out.println("Waiting for connection... ");
            while (true) {
                Socket socket = serverSocket.accept();
                counter++;
                System.out.println("Client: "+counter+" Connected");
                Thread t = new Thread(new ClientHandler(socket, counter));
                t.start();
                
                if(counter>2){
                    System.out.println("Can not accept more clients");
                    break;
                }
            }

            } catch(IOException e) {
                System.out.println(e.getMessage());
            }

        }

    }
