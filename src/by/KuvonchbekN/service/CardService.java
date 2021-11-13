package by.KuvonchbekN.service;

import by.KuvonchbekN.model.Card;
import by.KuvonchbekN.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CardService implements BaseService<Card, Card, List<Card>>{
    @Override
    public Card add(Card card) {
        BaseService.cardList.add(card);
        return card;
    }

    @Override
    public Card get(UUID id) {

        return null;
    }

    @Override
    public Card check(String str) {
        return null;
    }

    public boolean check(UUID id) {

        for (Card card: BaseService.cardList) {
            if(card.getOwnerId().equals(id) && card.isActive())
                return false;
        }

        return true;
    }

    @Override
    public List<Card> list(Card card) {
        return null;
    }

    @Override
    public Boolean delete(UUID id) {
        int ind = 0;
        for (Card card: BaseService.cardList) {
            if(card.getId().equals(id) && card.isActive()){
                card.setActive(false);
                BaseService.cardList.set(ind, card);
            }
            ind++;
        }
        return true;
    }

    public List<Card> getUserCards(User user){
        List<Card> myCards = new ArrayList<>();
        for (Card card:BaseService.cardList) {
            if(card.getOwnerId().equals(user.getId()) && card.isActive())
                myCards.add(card);
        }
        return myCards;
    }

    public void teacherCardUpdate(UUID id, double amount){
        int ind = 0;
        for (Card card1: BaseService.cardList) {
            if(card1.getOwnerId().equals(id) && card1.isActive() && card1.isMain()){
                card1.setBalance(card1.getBalance() + amount);
                BaseService.cardList.set(ind, card1);
                break;
            }
            ind++;
        }
    }

//    public void updateCard(Card card){
//        int ind = 0;
//        for (Card card1: BaseService.cardList) {
//            if(card1.getId().equals(card.getId()) && card1.isActive()){
//                BaseService.cardList.set(ind, card);
//                break;
//            }
//            ind++;
//        }
//    }

    //for testing
    public void allList(){
        for(Card card: cardList){
            System.out.println("card name: "+card.getName());
            System.out.println("card number: "+card.getCardNumber());
            System.out.println("card ID: "+card.getId());
            System.out.println("card ownerID: "+card.getOwnerId());
            System.out.println("card balance: "+card.getBalance());
        }
    }
}
