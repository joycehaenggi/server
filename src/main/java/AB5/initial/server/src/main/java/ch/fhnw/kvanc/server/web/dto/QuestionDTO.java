package AB5.initial.server.src.main.java.ch.fhnw.kvanc.server.web.dto;

public class QuestionDTO {
    private String questionText;

    public QuestionDTO(String text) {
        this.questionText = text;
    }

    /**
     * @return the questionText
     */
    public String getQuestionText() {
        return questionText;
    }
}