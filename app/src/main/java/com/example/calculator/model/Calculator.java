package com.example.calculator.model;

import android.util.Log;
import android.view.View;

public class Calculator {

//    STATIC FINAL TAG
    private static final String TAG="Calculator";

    //    DOUBLE VAIABLES
    private double currentNumber;
    private double oldNumber;

    //    STRING VARIABLES
    private String history;
    private String current;

//    EXPRESSION VARIABLES
    boolean isAdd;
    boolean isSub;
    boolean isMul;
    boolean isDiv;
    boolean isPer;
//    THIS WILL BE USED TO PERFORM OPERATION WHEN USER CLICKS ON OPERATION
    boolean isLastExpression;

    public void buttonClicked(String clickValue) {

//        CHECK THE TYPE OF BUTTON
        switch (clickValue) {
            case "=":{
                Log.d(TAG,"equal clicked");

//                EVALUATE EXPRESSION
                evaluateExpression();
                break;
            }

            case "AC":{
                Log.d(TAG,"AC clicked");
                this.currentNumber=0;
                this.oldNumber=0;
                break;
            }

            case "+/-":{
                Log.d(TAG,"+/- clicked");
//                STORE THE VALUE TO OLD NUMBER
//                storeValueToOldNumber();
                break;
            }

            case "%":{
                Log.d(TAG,"percent clicked");
//                MAKE ALL VARIABELS EXCEPT isPer false;
                isAdd=false;
                isSub=false;
                isMul=false;
                isDiv=false;
                isPer=true;
//                STORE CURRENTNUMBER IN OLD AND CLEAR CURRENTNUMBER
                saveCurrentInOld();
                break;
            }

//            CASE DIVISION
            case "\u00f7":{
                Log.d(TAG,"division clicked");
//                MAKE ALL VARIABELS EXCEPT isAdd false;
                isAdd=false;
                isSub=false;
                isMul=false;
                isDiv=true;
                isPer=false;
//                STORE CURRENTNUMBER IN OLD AND CLEAR CURRENTNUMBER
                saveCurrentInOld();

//                STORE CURRENT SYMBOL IN HISTORY
                break;
            }

            case "\u00d7":{
                Log.d(TAG,"mul clicked");
//                MAKE ALL VARIABELS EXCEPT isAdd false;
                isAdd=false;
                isSub=false;
                isMul=true;
                isDiv=false;
                isPer=false;
//                STORE CURRENTNUMBER IN OLD AND CLEAR CURRENTNUMBER
                saveCurrentInOld();

                break;
            }

            case "+":{
                Log.d(TAG,"add clicked");
//                MAKE ALL VARIABELS EXCEPT isAdd false;
                isAdd=true;
                isSub=false;
                isMul=false;
                isDiv=false;
                isPer=false;

//                STORE CURRENTNUMBER IN OLD AND CLEAR CURRENTNUMBER
                saveCurrentInOld();
                break;
            }

            case "-":{
                Log.d(TAG,"sub clicked");
//                MAKE ALL VARIABELS EXCEPT isAdd false;
                isAdd=false;
                isSub=true;
                isMul=false;
                isDiv=false;
                isPer=false;
//                STORE CURRENTNUMBER IN OLD AND CLEAR CURRENTNUMBER
                saveCurrentInOld();
                break;
            }

            case ".":{
                Log.d(TAG,". clicked");
                break;
            }

            default:{
                Log.d(TAG,"number clicked - "+clickValue);
                storeInCurrentNumber(clickValue);
            }
        }
    }

    private void evaluateExpression() {
        if(isAdd){
            this.currentNumber=this.oldNumber+this.currentNumber;
        }
        if(isSub){
            this.currentNumber=this.oldNumber-this.currentNumber;
        }
        if(isMul){
            this.currentNumber=this.oldNumber*this.currentNumber;
        }
        if(isDiv){
            this.currentNumber=this.oldNumber/this.currentNumber;
        }
        if(isPer){
            this.currentNumber=this.oldNumber%this.currentNumber;
        }
        Log.d(TAG,"result - "+String.valueOf(this.currentNumber));
    }

    private void saveCurrentInOld() {
        this.oldNumber=this.currentNumber;
//        CLEAR CURRENT NUMBER
        this.currentNumber=0;
    }

    private void performAddOperation() {

    }


    private void storeInCurrentNumber(String clickValue) {
        double temp = Double.parseDouble(clickValue);
        temp +=  this.currentNumber * 10;
        this.currentNumber=temp;
        Log.d(TAG,String.valueOf(this.currentNumber));
    }

    public String getCurrent(){
        return String.valueOf(this.currentNumber);
    }
}
