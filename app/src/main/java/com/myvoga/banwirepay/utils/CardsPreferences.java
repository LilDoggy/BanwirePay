package com.myvoga.banwirepay.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.card.payment.CreditCard;

public class CardsPreferences {

    private static String PREF = "CARDS";
    private static String PREF_CARDS = "ARRAYCARDS";

    public static void saveNewCard(Context context, CreditCard card){
        SharedPreferences preferences = context.getSharedPreferences(PREF,0);

        Gson gson = new Gson();
        String json = preferences.getString(PREF_CARDS,"");
        Type type = new TypeToken<List< CreditCard >>() {}.getType();
        List<CreditCard> cards = gson.fromJson(json,type);

        if (cards == null)
            cards = new ArrayList<>();

        cards.add(card);

        String toSave = gson.toJson(cards);

        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(PREF_CARDS,toSave);

        editor.commit();

    }

    public static void deleteCard(Context context, CreditCard creditCard){
        SharedPreferences preferences = context.getSharedPreferences(PREF,0);

        Gson gson = new Gson();
        String json = preferences.getString(PREF_CARDS,"");
        Type type = new TypeToken<List< CreditCard >>() {}.getType();
        List<CreditCard> cards = gson.fromJson(json,type);

        if (cards == null){
            cards = new ArrayList<>();
        } else if (cards != null && cards.size() > 0){
            for (CreditCard card : cards){
                if (card.cardNumber.contentEquals(creditCard.cardNumber))
                    cards.remove(card);
            }
        }

        String toSave = gson.toJson(cards);

        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(PREF_CARDS,toSave);

        editor.commit();
    }

    public static boolean checkExistCard(Context context, CreditCard card){

        boolean check = false;

        SharedPreferences preferences = context.getSharedPreferences(PREF,0);

        Gson gson = new Gson();
        String json = preferences.getString(PREF_CARDS,"");
        Type type = new TypeToken<List< CreditCard >>() {}.getType();
        List<CreditCard> cards = gson.fromJson(json,type);

        if (cards == null)
            cards = new ArrayList<>();
        else if (cards.size() > 0){
            for (int i = 0; i < cards.size(); i++){
                if (cards.get(i).cardNumber.contentEquals(card.cardNumber))
                    check = true;
            }
        }

        return check;
    }

    public static List<CreditCard> getCards(Context context){
        SharedPreferences preferences = context.getSharedPreferences(PREF,0);

        Gson gson = new Gson();
        String json = preferences.getString(PREF_CARDS,"");
        Type type = new TypeToken<List< CreditCard >>() {}.getType();

        return gson.fromJson(json,type);
    }
}
