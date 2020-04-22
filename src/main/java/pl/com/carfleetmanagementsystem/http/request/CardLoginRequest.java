package pl.com.carfleetmanagementsystem.http.request;

import javax.validation.constraints.NotBlank;

public class CardLoginRequest {

    private Long cardId;

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }
}
