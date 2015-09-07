/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multiplechoicetest;

import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;
//import java.util.logging.Level;
//import java.util.logging.Logger;

/**
 *
 * @author KSADEGHI
 */
public class MultipleChoiceTest {

    private static final Scanner keyboard = new Scanner( System.in );
    
    // readQuestionsFile
    // reads questions out of the questions file (Questions.txt)
    // and returns an array of QuizQuestions
    //
    // ********************************************
    // DO NOT TOUCH THIS METHOD!!!
    // ********************************************
    public static QuizQuestion[] readQuestionsFile()
    {
        QuizQuestion [] scarlet = null;
        
        try {
            Scanner scan = new Scanner( new File( "Questions.txt" ) );
            
            int numQuestions = scan.nextInt();
            scarlet = new QuizQuestion[numQuestions];
            
            for ( int i = 0; i < numQuestions; i++ )
            {
                int numChoices = scan.nextInt();
                scan.nextLine();
                String [] choices = new String[numChoices];
                String prompt = scan.nextLine();
                for ( int j = 0; j < numChoices; j++ )
                {
                    choices[j] = scan.nextLine();
                }
                
                scarlet[i] = new QuizQuestion( i+1, prompt, choices );
            }
            
            return scarlet;
        } catch (Exception ex) {
            
        }
        
        return scarlet;
    }
    
    // printQuizQuestion
    // prints the prompt and answer choices for a quiz question to the screen
    // also indicates which choice (if any) the student has chosen
    // 
    // Inputs:
    // qq - the quiz question to be printed
    //
    // Side Effects:
    // the QuizQuestion is printed to the screen
    // 
    // Output:
    // void (none)
    public static void printQuizQuestion( QuizQuestion qq )
    {
        // Instructions:
        // each quiz question has:
        // a number
        // a prompt and 
        // 5 choices
        // This function should print the number followed by the prompt on one line
        // and then, one to a line, the letter (A...E) followed by the text of the choice  (indent this line by 2 spaces)
        // If the student has chosen a particular choice, put a * before the choice.
        String[] choiceLetters = { "A)", "B)", "C)", "D)", "E)"};
        System.out.println( qq.getQuestionNumber() + ". " + qq.getPrompt());
        String preAnswer;
        for (int i = 0; i < 5; i++)
        {
            preAnswer = "  ";
            if (qq.getStudentChoice() == i)
                preAnswer = "* ";
            
            System.out.print( preAnswer);
            System.out.print( choiceLetters[i] + " ");
            System.out.println( qq.getChoice(i));
        }
    }
    
    // readChoice
    // prompts the user for their answer to a question (expect A...E)
    // then reads the user's choice and sets the student choice for the question to the appropriate
    // value (0 if the user chooses A or a, 1 for B or b, 2 for C or c, 3 for D or d, 4 for E or e).
    // If the user selects something other than A...E or a...e, do nothing
    //
    // inputs:
    // qq - the QuizQuestion to be answered
    //
    // side effects:
    // a prompt is printed to the screen
    // the student's answer to the quiz question may be changed
    //
    // outputs:
    // none
    public static void readChoice( QuizQuestion qq )
    {
        System.out.println( "What is your answer?");
        String answer = inputAnswer();
        
        switch (answer) {
            case "a": case "A": qq.setStudentChoice(0); break;
            case "b": case "B": qq.setStudentChoice(1); break;
            case "c": case "C": qq.setStudentChoice(2); break;
            case "d": case "D": qq.setStudentChoice(3); break;
            case "e": case "E": qq.setStudentChoice(4); break;
        }
    }
     
    // inputAnswer
    // checks the next keyboard input so that it is a part of the characters a-e 
    // or A-E and keeps accepting input until they get it right
    //
    // inputs:
    // none
    //
    // side effects:
    // none
    //
    // outputs: 
    // answer - the String of one character that contains the answer choice input    
    public static String inputAnswer()
    {
        Scanner sc = new Scanner(System.in);
        Pattern p = Pattern.compile("[a-eA-E]");
        if (sc.hasNext(p))
            return sc.next();
        else {
            return inputAnswer();
        }
    }
    
    // printQuizQuestionResult
    // similar to printQuizQuestion except that correct answer is marked with a +
    // The student's answer (if different from the correct answer is marked with a *)
    // If the student got the answer correct, add a line that says "CORRECT!".  If not, print "WRONG!"
    //
    // inputs:
    // qq - the QuizQuestion
    //
    // Side Effects
    // the question is displayed along with the correct and student answers and a summary (Correct or Wrong)
    // 
    // outputs:
    // none
    public static void printQuizQuestionResult( QuizQuestion qq )
    {
        boolean isCorrect = qq.getStudentChoice() == qq.getCorrectChoice();
        
        String[] choiceLetters = { "A)", "B)", "C)", "D)", "E)"};
        System.out.println( qq.getQuestionNumber() + ". " + qq.getPrompt());
        String preAnswer;
        for (int i = 0; i < 5; i++)
        {
            preAnswer = "  ";
            
            if (qq.getCorrectChoice() == i)
                preAnswer = "+ ";
            else if (qq.getStudentChoice() == i)
                preAnswer = "* ";
            
            System.out.print( preAnswer);
            System.out.print( choiceLetters[i] + " ");
            System.out.println( qq.getChoice(i));
        }
        if (isCorrect) System.out.println( "CORRECT!");
        else System.out.println( "WRONG!");
    }
    
    // allAnswered
    // detects if all questions have been answered
    // 
    // inputs:
    // qqa - an array of QuizQuestions
    //
    // Side Effects:
    // none
    // 
    // ouputs:
    // true if the student has answered all questions in the array, false otherwise
    // ********************************************
    // DO NOT TOUCH THIS METHOD!!!
    // ********************************************
    public static boolean allAnswered( QuizQuestion [] qqa )
    {
        for ( QuizQuestion qq : qqa )
        {
            if ( qq.getStudentChoice() == -1 )
            {
                return false;
            }
        }
        
        return true;
    }
    
    // changeQuestion
    // prompts the user to go to the (N)ext or (P)revious question or remain on the current question
    // 
    // inputs:
    // none
    // 
    // Side Effects:
    // a prompt is printed to the screen
    // a choice is read from the keyboard
    //
    // outputs
    // returns -1 if the user wants to go to the previous question
    // returns 1 if the user wants to go to the next question
    // returns 0 if the user wants to stay on the current question
    public static int changeQuestion() 
    {
        System.out.println( "N) Next question\n" +
                "P) Previous question\n" +
                "R) Remain on this question");
        String choice = inputChangeQuestion();
        switch (choice) {
            case "p": case "P": return -1;
            case "r": case "R": return 0;
            case "n": case "N": return 1;
        }
        
        return 0;
    }
    
    // inputChangeQuestion
    // checks the next keyboard input so that it is a part of the characters n, p,
    // c, N, P, or C and keeps asking until correct input is received
    //
    // inputs:
    // none
    //
    // side effects:
    // none
    //
    // outputs: 
    // answer - the String of one character that contains the input for changeQuestion
    public static String inputChangeQuestion()
    {
        Scanner sc = new Scanner(System.in);
        Pattern p = Pattern.compile("[nprNPR]");
        if (sc.hasNext(p))
            return sc.next();
        else {
            return inputChangeQuestion();
        }
    }
    
    public static void main(String[] args) 
    {
        QuizQuestion [] questions = readQuestionsFile();
        
        System.out.println( "Welcome to the quiz!" );
        boolean done = false;
        int curQuestion = 0;
        
        while( !done )
        {
            System.out.println();
            printQuizQuestion( questions[curQuestion] );
            
            System.out.println();
            readChoice( questions[curQuestion] );
            
            if ( allAnswered( questions ) )
            {
                System.out.println();
                System.out.println( "You've answered all the questions.  Would you like to end the test (Y/N)");
                char endTestResponse = keyboard.next().toUpperCase().charAt(0);
                if ( endTestResponse == 'Y' )
                {
                    done = true;
                }
            }
            
            if ( !done )
            {              
                System.out.println();
                curQuestion = (curQuestion + questions.length + changeQuestion()) % questions.length;
            }
        }
        System.out.println();
        System.out.println( "==================================================" );
        System.out.println( "Summary" );
        
        int numCorrect = 0;
        for (QuizQuestion question : questions) {
            System.out.println();
            printQuizQuestionResult(question);
            if (question.getStudentChoice() == question.getCorrectChoice()) {
                numCorrect++;
            }
        }
        
        System.out.println();
        System.out.println( "==================================================" );
        System.out.println( "You got " + numCorrect + "/" + questions.length + " questions correct." );
        System.out.println( "==================================================" );
    }
}
