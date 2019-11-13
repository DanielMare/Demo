package serverpack.requesthandler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import serverpack.model.Database;

public class ClientHandler implements Runnable {

    private Socket socket;
    private Database database;
    private int counter;
    private boolean isFirstClient = true;

    public ClientHandler(Socket socket, int counter) {
        this.socket = socket;
        this.database = new Database();
        this.counter = counter;

    }

    @Override
    public void run() {
        String userChoice;
        try {
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            System.out.println("Got: " + counter);

            do {
                if (this.counter == 2 && isFirstClient) {
                    userChoice = "Waiting for the openent..";
                    output.writeObject(userChoice);
                    output.flush();
                    
                    userChoice = (String) input.readObject();

                    output.writeObject(database.getRandomQuestion(userChoice));
                    output.flush();
                    isFirstClient = false;
                } else {
                    userChoice = "Waiting for the openent..";
                    System.out.println(userChoice);
                    
                    output.writeObject(userChoice);
                    output.flush();
                }
                output.writeObject(database.getRandomQuestion(userChoice) + "" + isFirstClient);
                output.flush();

            } while (!input.readObject().equals("bye"));

        } catch (Exception e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}
