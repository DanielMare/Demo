package serverpack.model;

import serverpack.model.Question;
import serverpack.model.Subject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Database implements Serializable {

    private final List<Question> questions;
    private final List<Subject> subjects;

    public Database() {
        
        subjects = new ArrayList<>();
        subjects.add(new Subject("Football"));
        subjects.add(new Subject("Animal"));
        
        questions = new ArrayList<>();
        questions.add(new Question("Hur många ben har en vanlig stol?", "1", "2", "124", "4"));
        questions.add(new Question("Hur lång är en hammarhaj?", "600 m", "300 000 m", "1 cm", "2 m"));
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public Question getRandomQuestion(String userChoice) {
        Question randomQuestion = null;
        for (Subject s : subjects) {
            if (s.getOption().equals("Football") && userChoice.equals("Football")) {
                //get Football questions
                randomQuestion = questions.get(1);
            }else{
                //get the other subject questions
                randomQuestion = null;
            }
        }

        return randomQuestion;
    }

}
