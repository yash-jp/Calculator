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
    private boolean isAdd;
    private boolean isSub;
    private boolean isMul;
    private boolean isDiv;
    private boolean isPer;

    private boolean isOperation;

    private boolean isLastNumber;
////    THIS WILL BE USED TO PERFORM OPERATION WHEN USER CLICKS ON OPERATION
//    boolean isLastExpression;

//    THIS WILL WORK AS A STACK
    private char stack=0;

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
                this.isOperation=false;
                this.isLastNumber=false;
                this.stack=0;
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
//                ADDITIONALLY TO PREVENT INITIAL OPERATION WITHOUT ENTERING THE NUMBERS AT STARTING OR RIGHT AFTER AC
//                PERFORM ONLY WHEN PREVIOUS IS SOME NUMBERS
                if(isLastNumber){
                    saveCurrentInOld();
                }
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

//                stack='/';
//                STORE CURRENTNUMBER IN OLD AND CLEAR CURRENTNUMBER
//                ADDITIONALLY TO PREVENT INITIAL OPERATION WITHOUT ENTERING THE NUMBERS AT STARTING OR RIGHT AFTER AC
//                PERFORM ONLY WHEN PREVIOUS IS SOME NUMBERS
                if(isLastNumber){
                    saveCurrentInOld();
                }

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

//                stack='*';
//                STORE CURRENTNUMBER IN OLD AND CLEAR CURRENTNUMBER
//                ADDITIONALLY TO PREVENT INITIAL OPERATION WITHOUT ENTERING THE NUMBERS AT STARTING OR RIGHT AFTER AC
//                PERFORM ONLY WHEN PREVIOUS IS SOME NUMBERS
                if(isLastNumber){
                    saveCurrentInOld();
                }


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

//                stack='+';
//                STORE CURRENTNUMBER IN OLD AND CLEAR CURRENTNUMBER
//                ADDITIONALLY TO PREVENT INITIAL OPERATION WITHOUT ENTERING THE NUMBERS AT STARTING OR RIGHT AFTER AC
//                PERFORM ONLY WHEN PREVIOUS IS SOME NUMBERS
                if(isLastNumber){
                    saveCurrentInOld();
                }
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

//                stack='-';
//                STORE CURRENTNUMBER IN OLD AND CLEAR CURRENTNUMBER
//                ADDITIONALLY TO PREVENT INITIAL OPERATION WITHOUT ENTERING THE NUMBERS AT STARTING OR RIGHT AFTER AC
//                PERFORM ONLY WHEN PREVIOUS IS SOME NUMBERS
                if(isLastNumber){
                    saveCurrentInOld();
                }
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
//            check for NAN and 0 divisor
            if(this.currentNumber!=0||this.currentNumber!=0.0){
                this.currentNumber=this.oldNumber/this.currentNumber;
            }else{
                this.currentNumber=0;
            }

        }
        if(isPer){
            this.currentNumber=this.oldNumber%this.currentNumber;
        }
        Log.d(TAG,"result - "+String.valueOf(this.currentNumber));
        this.oldNumber=0;
//        this.currentNumber=0;
        this.isOperation=false;
        this.stack=0;
    }

    private void saveCurrentInOld() {
        if(isOperation){
            if(isAdd){
                stack='+';
            }
            if(isSub){
                stack='-';
            }
            if(isMul){
                stack='*';
            }
            if(isDiv){
                stack='/';
            }
            if(isPer){
                stack='%';
            }
        }
        else{
        if(stack==0){
//            stack is empty
            if(isAdd){
                stack='+';
                this.oldNumber=this.currentNumber;
                this.currentNumber=0;
                this.isOperation=true;
            }
            if(isSub){
                stack='-';
                this.oldNumber=this.currentNumber;
                this.currentNumber=0;
                this.isOperation=true;
            }
            if(isMul){
                stack='*';
                this.oldNumber=this.currentNumber;
                this.currentNumber=0;
                this.isOperation=true;
            }
            if(isDiv){
                stack='/';
                this.oldNumber=this.currentNumber;
                this.currentNumber=0;
                this.isOperation=true;
            }
            if(isPer){
                stack='%';
                this.oldNumber=this.currentNumber;
                this.currentNumber=0;
                this.isOperation=true;
            }

        }else{
//            SOME OPERATIONS ARE PENDING AND YOU GOT ANOTHER OPERATION
            if(stack=='+'){
                this.oldNumber=this.oldNumber+this.currentNumber;
                this.currentNumber=0;
                this.isOperation=true;
            }else if(stack=='-'){
                this.oldNumber=this.oldNumber-this.currentNumber;
                this.currentNumber=0;
                this.isOperation=true;
            }else if(stack=='*'){
                this.oldNumber=this.oldNumber*this.currentNumber;
                this.currentNumber=0;
                this.isOperation=true;
            }else if(stack=='/'){
//                check for NAN and 0 divisor
                if(this.currentNumber!=0||this.currentNumber!=0.0){
                    this.oldNumber=this.oldNumber/this.currentNumber;
                }else{
                    this.oldNumber=0;
                }
                this.currentNumber=0;
                this.isOperation=true;
            }else{
                this.oldNumber=this.oldNumber%this.currentNumber;
                this.currentNumber=0;
                this.isOperation=true;
            }

            if(isAdd){
                stack='+';
            }
            if(isSub){
                stack='-';
            }
            if(isMul){
                stack='*';
            }
            if(isDiv){
                stack='/';
            }
            if(isPer){
                stack='%';
            }
        }}
    }

    private void performAddOperation() {

    }


    private void storeInCurrentNumber(String clickValue) {
        double temp = Double.parseDouble(clickValue);
        temp +=  this.currentNumber * 10;
        this.currentNumber=temp;

//        SO THAT GET CURRENT WORKS PERFECTLY
        this.isOperation=false;
        this.isLastNumber=true;
        Log.d(TAG,String.valueOf(this.currentNumber));
    }

    public String getCurrent(){
        if(isOperation){
            return String.valueOf(this.oldNumber);
        }else{
            return String.valueOf(this.currentNumber);
        }
    }
}
