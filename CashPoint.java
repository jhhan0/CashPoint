/**
 * CSCI1130 Assignment 2 Cash Point
 * Aim: 1. To build a practical cash point application using Java.
 *      2. To practice structured programming and use of static methods.
 * 
 * Task: Create a Java application for cash account management
 * 
 * I declare that the assignment here submitted is original
 * except for source material explicitly acknowledged,
 * and that the same or closely related material has not been
 * previously submitted for another course.
 * I also acknowledge that I am aware of University policy and 
 * regulations on honesty in academic work, and of the disciplinary 
 * guidelines and procedures applicable to breaches of such 
 * policy and regulations, as contained in the website.
 * 
 * University Guideline on Academic Honesty:
 *   http://www.cuhk.edu.hk/policy/academichonesty
 * Faculty of Engineering Guidelines to Academic Honesty:
 *   https://www.erg.cuhk.edu.hk/erg/AcademicHonesty
 * 
 * Student Name: HAN, Jihun
 * Student ID  : 1155128719
 * Date        : 20/10/2020
 */
package cashpoint;

import javax.swing.JOptionPane;

public class CashPoint {

    public static double balance = 109700.0;
    
    public static String showMenu(){
        return JOptionPane.showInputDialog("Cash Point: Input your task\n" 
                + "1. Check Balance\n" 
                + "2. Cash Deposit\n" 
                + "3. Cash Withdrawal\n" 
                + "4. Exit\n"
                , "<type [1 - 4] here>");
    }
    
    public static String showDepositMenu(){
        return JOptionPane.showInputDialog("Cash Deposit (HKD):\n"
                + "Input # of $100 banknotes [20 max]");
    }
    
    public static String showWithdrawalMenu(){
        return JOptionPane.showInputDialog("Cash Withdrawal: Choose your currency:\n"
                + "1. Hong Kong Dollar (HKD)\n" 
                + "2. Korean Won (KRW)" 
                , "<type [1-2] here>");
    }
    
    public static void showEndMessage (){
        JOptionPane.showMessageDialog(null, "Hope to serve you again");
    }
    
    public static void checkBalance (){
        String balance_str = String.format("Balance (HKD): %.2f", balance);
        JOptionPane.showMessageDialog(null, balance_str);
    }
    
    public static void deposit(){
        String choiceDeposit;
        choiceDeposit = showDepositMenu();
        if (choiceDeposit == null){
            
        }
        else {
            int depositIntNumAmount = Integer.parseInt(choiceDeposit);
        
            if (depositIntNumAmount > 0 && depositIntNumAmount <= 20){
                int depositAmount = 100 * depositIntNumAmount;
                String depositAmountStr = String.format("HKD %d deposited", depositAmount);
                JOptionPane.showMessageDialog(null, depositAmountStr);
                balance = balance + (double) depositAmount;
            } 
            else {
                JOptionPane.showMessageDialog(null, "Invaild input");
                deposit();
            }
        }
    }
    
    public static void withdrawal(){
        String choiceWithdrawal;
        choiceWithdrawal = showWithdrawalMenu();
        if (choiceWithdrawal == null){
            
        }
        else {
            int chooseOption = Integer.parseInt(choiceWithdrawal);
        
            if (chooseOption == 1){
                withdrawalHKD();
            } 
            else if (chooseOption == 2){
                withdrawalKRW();
            }
            else {
                JOptionPane.showMessageDialog(null, "Invaild input");
                withdrawal();
            }
        }
    }
    
    public static String withdrawalMenuHKD(){
        return JOptionPane.showInputDialog("Cash Withdrawal (HKD):\n" 
                + "100min, 10000max");
    }
    
    public static String withdrawalMenuKRW(){
        return JOptionPane.showInputDialog("Cash Withdrawal (KRW):\n" 
                + "1000min, 200000max");
    }
    
    public static void withdrawalHKD(){
        String choiceWithdrawalHKD;
        choiceWithdrawalHKD = withdrawalMenuHKD();
        if (choiceWithdrawalHKD == null){
            withdrawal();
        }
        else {
            int withdrawalHKDAmount = Integer.parseInt(choiceWithdrawalHKD);
        
            if (withdrawalHKDAmount >= 100 && withdrawalHKDAmount <= 10000){
                double mathEqWithdrawalHKDAmount = withdrawalHKDAmount;
                mathEqWithdrawalHKDAmount = mathEqWithdrawalHKDAmount / 100;
                int MathEqActualAmount =  (int) Math.floor(mathEqWithdrawalHKDAmount);
                int actualAmountHKD = MathEqActualAmount * 100;
            
                String actualAmountOptionStr = String.format("Banknotes provided for HKD are 500 & 100\n" 
                    + "Withdraw HKD %d or not?", actualAmountHKD);  
                int dialogButton = JOptionPane.showConfirmDialog(null, actualAmountOptionStr, 
                    "Message", JOptionPane.YES_NO_OPTION);
            
                if (balance < (double) actualAmountHKD){
                    JOptionPane.showMessageDialog(null, "Not enough balance, Input again");
                    withdrawalHKD();
                } 
                else{
                    if (dialogButton == 0) {
                        int numOf500HKD = 0;
                        int numOf100HKD = 0;
                        int mathEqActualAmount = actualAmountHKD;
                
                        String actualAmountStr = String.format("HKD%d withdrawn\n", actualAmountHKD);

                        numOf500HKD = mathEqActualAmount / 500;
                        mathEqActualAmount = mathEqActualAmount % 500;
                        numOf100HKD = mathEqActualAmount / 100;
                
                        String numOfHKDOutput = "";
                            if (numOf500HKD != 0){ 
                                numOfHKDOutput += "HKD 500 x " + numOf500HKD + "\n";
                            }
                            if (numOf100HKD != 0){
                                numOfHKDOutput += "HKD 100 x " + numOf100HKD;
                            }
                        JOptionPane.showMessageDialog(null, actualAmountStr + numOfHKDOutput);
                        balance = (balance - (double) actualAmountHKD);
                    }
                    else if (dialogButton == 1){
                        withdrawalHKD();
                    }
                } 
            }
            else {
                JOptionPane.showMessageDialog(null, "Invaild input");
                withdrawalHKD();
            }
        }
    }

    
    public static void withdrawalKRW(){
        String choiceWithdrawalKRW;
        choiceWithdrawalKRW = withdrawalMenuKRW();
        
        if (choiceWithdrawalKRW == null){
            withdrawal();
        }
        else {
            int withdrawalKRWAmount = Integer.parseInt(choiceWithdrawalKRW);
        
            if (withdrawalKRWAmount >= 1000 && withdrawalKRWAmount <= 200000){
                double mathEqWithdrawalKRWAmount = withdrawalKRWAmount;
                mathEqWithdrawalKRWAmount = mathEqWithdrawalKRWAmount / 1000;
                int mathEq1ActualAmount =  (int) Math.floor(mathEqWithdrawalKRWAmount);
                int actualAmountKRW = mathEq1ActualAmount * 1000;
                double actualAmountEqualsHKD = actualAmountKRW * (1.0 / 150);
                
                String actualAmountOptionStr = String.format("Banknotes provided for KRW are 10000 & 1000\n" 
                    + "Withdraw KRW %d (HKD %.2f) or not?", actualAmountKRW, actualAmountEqualsHKD);  
                int dialogButton = JOptionPane.showConfirmDialog(null, actualAmountOptionStr, 
                    "Message", JOptionPane.YES_NO_OPTION);
                
                if (balance < (actualAmountKRW * (1.0 / 150))){
                    JOptionPane.showMessageDialog(null, "Not enough balance, Input again");
                    withdrawalKRW();
                } 
                else{
                    if (dialogButton == 0) {
                        int numOf1000KRW = 0;
                        int numOf10000KRW = 0;
                        int mathEq2ActualAmount = actualAmountKRW;
                
                        String actualAmountStr = String.format("KRW%d withdrawn\n", actualAmountKRW);

                        numOf10000KRW = mathEq2ActualAmount / 10000;
                        mathEq2ActualAmount = mathEq2ActualAmount % 10000;
                        numOf1000KRW = mathEq2ActualAmount / 1000;
                
                        String numOfKRWOutput = "";
                            if (numOf10000KRW != 0){ 
                                numOfKRWOutput += "KRW 10000 x " + numOf10000KRW + "\n";
                            }
                            if (numOf1000KRW != 0){
                                numOfKRWOutput += "KRW 1000 x " + numOf1000KRW;
                            }
                        JOptionPane.showMessageDialog(null, actualAmountStr + numOfKRWOutput);
                        balance = (balance - actualAmountEqualsHKD);
                    }
                    else if (dialogButton == 1){
                        withdrawalKRW();
                    }
                }
            }
            else {
               JOptionPane.showMessageDialog(null, "Invalid input");
               withdrawalKRW();
            }
        }
    }
    
    public static boolean done = false;

    public static void main(String[] args) {
        while (!done){
        String choice;
        choice = showMenu();
        if (choice == null){
            done = true;
            System.out.println("User closed or cancelled dialog box");
        } 
        else if ("1".equals(choice)){
            checkBalance();
        }
        else if ("2".equals(choice)){
            deposit();
        }
        else if ("3".equals(choice)){
            withdrawal();
        }
        else if ("4".equals(choice)){
            showEndMessage();
            break;
        }
        else {
            JOptionPane.showMessageDialog(null, "Invalid input");
        }
        }
    }  
}
