/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multiplechoicetest;

// QuizQuestion
// holds the question number, prompt, and answer choices of a quiz question on a test
public class QuizQuestion 
{
    private String prompt;
    private String [] answerChoices;
    private int correctChoice, studentChoice, questionNumber;
    
    // QuizQuestion – construct a new QuizQuestion from a number, prompt and answer choices
    public QuizQuestion( int qn, String p, String [] ac )
    {
        questionNumber = qn;
        answerChoices = ac.clone();
        correctChoice = 0;
        studentChoice = -1;
        
        for ( int i = 0; i < 100; i++ )
        {
            int a = (int)(Math.random()*ac.length);
            int b = (int)(Math.random()*ac.length);
            
            if ( a == correctChoice )
            {
                correctChoice = b;
            }
            else if ( b == correctChoice )
            {
                correctChoice = a;
            }
            
            String temp = answerChoices[a];
            answerChoices[a] = answerChoices[b];
            answerChoices[b] = temp;
        }
        prompt = p;
    }

    // getQuestionNumber – returns the question number
    public int getQuestionNumber() { return questionNumber; }
    
    // getPrompt  - returns the prompt
    public String getPrompt() { return prompt; }

    // getStudentChoice – gets the student’s choice (0 = A, 1 = B, 2 = C, 3 = D, 4 = E)
    // note: if the student hasn’t answered this question yet, the result is -1
    public int getStudentChoice() { return studentChoice; }
    
    // setStudentChoice – sets the student’s choice (0 = A, 1 = B, 2 = C, 3 = D, 4 = E)
    public void setStudentChoice( int sc )
    {
        studentChoice = sc;
    }
    
    // getCorrectChoice – gets the correct answer choice (0 = A, 1 = B, 2 = C, 3 = D, 4 = E)
    public int getCorrectChoice() { return correctChoice; }
    
    // getChoice( int i ) – gets the text of answer choice i (0 = A, 1 = B, 2 = C, 3 = D, 4 = E)
    public String getChoice( int i ) { return answerChoices[i]; }
}
