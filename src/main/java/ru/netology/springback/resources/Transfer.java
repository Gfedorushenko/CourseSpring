package ru.netology.springback.resources;

public class Transfer {
    private String cardFromNumber;
    private String cardFromValidTill;
    private String cardFromCVV;
    private String cardToNumber;
    private Amount amount;
    private Long id;
    private boolean status;

    public Transfer(String cardFromNumber, String cardFromValidTill, String cardFromCVV, String cardToNumber, Amount amount) {
        this.cardFromNumber = cardFromNumber;
        this.cardFromValidTill = cardFromValidTill;
        this.cardFromCVV = cardFromCVV;
        this.cardToNumber = cardToNumber;
        this.amount = amount;
        this.status=false;
    }
    public void setId(Long id){this.id=id;}
    public void setStatus(boolean status){this.status=status;}

    public Card getCard(){return new Card(cardFromNumber,cardFromCVV,cardFromValidTill);}
    public Amount getAmount() {return amount;}
    public String getCardFromNumber(){
        return cardFromNumber;
    }
    public String getCardToNumber(){
        return cardToNumber;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "cardFromNumber='" + cardFromNumber + '\'' +
                ", cardFromValidTill='" + cardFromValidTill + '\'' +
                ", cardFromCVV='" + cardFromCVV + '\'' +
                ", cardToNumber='" + cardToNumber + '\'' +
                ", amount=" + amount +
                '}';
    }
}
