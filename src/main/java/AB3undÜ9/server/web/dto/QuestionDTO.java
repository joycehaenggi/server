package AB3undÜ9.server.web.dto;

/**
 * QuestionDTO
 */
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