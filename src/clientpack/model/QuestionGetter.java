package clientpack.model;

import clientpack.controller.ClientXMLController;
import javafx.scene.control.Button;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.scene.control.TextArea;

public class QuestionGetter implements Runnable{

    private TextArea questionText;

    private Button buttonOne;
    private Button buttonTwo;
    private Button buttonThree;
    private Button buttonFour;

    private List<Button> buttons;
    
    private String userChoice;
    
    public QuestionGetter(String userChoice){
        this.userChoice = userChoice;
    }
   
    public QuestionGetter(TextArea questionText,Button b1,Button b2, Button b3,Button b4) {
        this.questionText = questionText;
        this.buttonOne = b1;
        this.buttonTwo = b2;
        this.buttonThree = b3;
        this.buttonFour = b4;
        buttons = new ArrayList<>();
        buttons.add(buttonOne);
        buttons.add(buttonTwo);
        buttons.add(buttonThree);
        buttons.add(buttonFour);
    }
    public String getUserChoice(){
        return userChoice;
    }

    @Override
    public void run() {

        try(Socket socket = new Socket("localhost",8888)) {

            ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream serverReader = new ObjectInputStream(socket.getInputStream());
            
            System.out.println("Connected to: "+socket.getInetAddress().getHostName()+""
                    + (String)serverReader.readObject());
           
            writer.writeObject(getUserChoice());
            writer.flush();
            Question question;
            
            while ((question = (Question) serverReader.readObject())!=null) {
                Collections.shuffle(buttons);
                questionText.setText(question.getQuestionText());
                buttons.get(0).setText(question.getAnswerOne());
                buttons.get(1).setText(question.getAnswerTwo());
                buttons.get(2).setText(question.getAnswerThree());
                buttons.get(3).setText(question.getAnswerCorrect());
                ClientXMLController.setCurrentQuestion(question);
            }


        }catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
