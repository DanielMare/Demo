package clientpack.controller;

import clientpack.model.Question;
import clientpack.model.QuestionGetter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;

public class ClientXMLController implements Initializable {

    @FXML
    private RadioButton btnRadio1;
    @FXML
    private RadioButton btnRadio2;
    @FXML
    private Button btnLoad;
    @FXML
    private TextArea txtAreaQuestion;
    @FXML
    private Button btnAnswer1;
    @FXML
    private Button btnAnswer2;
    @FXML
    private Button btnAnswer3;
    @FXML
    private Button btnAnswer4;
    @FXML
    ToggleGroup btnRad;
    List<Button> buttons = new ArrayList<>();
    private static Question currentQuestion;
    
       @FXML
    private void btnLoader(ActionEvent event) {
        RadioButton selectedRadioButton = (RadioButton) btnRad.getSelectedToggle();
        String toogleGroupValue = selectedRadioButton.getText();
        new QuestionGetter(toogleGroupValue);
        
        Thread t = new Thread(new QuestionGetter(txtAreaQuestion, 
                btnAnswer1, btnAnswer2, btnAnswer3, btnAnswer4));
        t.start();
        buttons.add(btnAnswer1);
        buttons.add(btnAnswer2);
        buttons.add(btnAnswer3);
        buttons.add(btnAnswer4);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }  
    
        public static void setCurrentQuestion(Question currentQuestion) {
        ClientXMLController.currentQuestion = currentQuestion;
    }

    public void onButtonClicked(ActionEvent e) {

        for (Button button : buttons) {
            if (button.getText().equals(currentQuestion.getAnswerCorrect())) {
                button.setStyle("-fx-background-color: #85ff88; -fx-border-color: #85ff88");
                if(e.getSource().equals(button)) {
                    System.out.println("rätt");
                }

            } else {
                button.setStyle("-fx-background-color: rgb(255,118,124); -fx-border-color: rgb(255,118,124)");
            }

        }
    }

}
