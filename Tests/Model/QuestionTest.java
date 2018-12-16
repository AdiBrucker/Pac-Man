package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {

    @Test
    void setquestion() {
        Question question = new Question();
        question.setquestion("Test Question");

        assertEquals(question.getquestion(), "Test Question");
    }
}