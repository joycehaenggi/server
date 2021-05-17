package AB3undÜ9.server.repository;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class QuestionRepository {
    private Logger logger = LoggerFactory.getLogger(QuestionRepository.class);

    /*
     * Warning: Should be checked for concurrent access if method getQuestionContent
     * is used! No problem in the default use case, because this thread reads and
     * pushes the new question to the clients.
     */
    private String questionContent = null;

    /**
     * @return the questionContent
     */
    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestion(String question) throws IOException {
        questionContent = question;
        if (questionContent.length() > 0) {
            logger.debug("New question set: '{}''", this.questionContent);
        }
    }

}
