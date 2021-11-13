package com.carmiapps.tictacto.game;

public class InputNumber {
    public static int choice;

    GameView gameView;

    public InputNumber(GameView gameView) {
        this.gameView = gameView;
    }

    public static int getNuber(GameView gameView, StringScanner sc) {
        String input = "a";
        boolean notAnInteger = true;
        while(notAnInteger){
            input = sc.next();
            try{
                choice = Integer.parseInt(input);
                if(arrayContains(choice)){
                    if (choice >= 10) {
                        choice = choice / 10;
                    }
                    notAnInteger = false;

                }else{

                    gameView.addInstraction("Your choics is iligal");
                };

            }catch(Exception e){
                gameView.addInstraction("Not an integer");
            }
        }
        return choice;
    }

    public static boolean arrayContains(int toFind) {

        int[] myArray = {1, 2, 3, 4,10, 20, 30, 40};
        int length = myArray.length;

        boolean found = false;

        for (int i = 0; i < length; i++) {
            if (myArray[i] == toFind) {
                found = true;
                return found;
            }
        }
        return found;
    }
}