package alphabet_match;

import java.util.Random;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.*;

public class Main 
{
	
    public static void main(String[] args) throws IOException
    {
    	
    	Stack set1 = new Stack(10);
    	Stack set2 = new Stack(10);
    	Stack set3 = new Stack(10);
    	Stack set4 = new Stack(10);
    	Stack set5 = new Stack(10);
    	
    	Queue reserveQueue = new Queue(26);
    	Queue supplementaryQueue = new Queue(26);
    	
    	Queue tempQueue1 = new Queue(26);
    	Queue tempQueue2 = new Queue(26);
    	
 
 
    	for(char letter = 'A' ; letter <= 'Z' ; letter++) 
    	{
    		tempQueue1.enqueue(letter);
    		tempQueue2.enqueue(letter);
    	}
    	
    	
    	Random rnd = new Random();
    	
    	
    	while(!tempQueue1.isEmpty()) 
    	{
    		int shiftingLetters1 = rnd.nextInt(tempQueue1.size()) + 1;
    		int shiftingLetters2 = rnd.nextInt(tempQueue2.size()) + 1;
    		
    		for(int i = 0 ; i < shiftingLetters1; i++) 
    		{
    			char newLetters1 = (Character)tempQueue1.dequeue();
    			tempQueue1.enqueue(newLetters1);
    		}
    		
    		reserveQueue.enqueue(tempQueue1.peek());
    		tempQueue1.dequeue();
    		
    		for(int i = 0 ; i < shiftingLetters2; i++) 
    		{
    			char newLetters2 = (Character)tempQueue2.dequeue();
    			tempQueue2.enqueue(newLetters2);
    		}
    		
    		supplementaryQueue.enqueue(tempQueue2.peek());
    		tempQueue2.dequeue();
    	}
    	
    	
    	
    	int currentSet = 1;
    	
    	while(currentSet <= 5)
    	{
    		int addingSets = rnd.nextInt(10) + 1;
    		
    		for (int i = 0; i < addingSets; i++) 
    		{
                char addLetters = (Character) reserveQueue.dequeue();
                
                if (currentSet == 1)      
        			set1.push(addLetters);
                else if (currentSet == 2) 
                	set2.push(addLetters);
                else if (currentSet == 3) 
                	set3.push(addLetters);
                else if (currentSet == 4) 
                	set4.push(addLetters);
                else if (currentSet == 5) 
                	set5.push(addLetters);
        		
        		 reserveQueue.enqueue(addLetters);
    		}
    		
    		 currentSet++;
    	}
    	
    	
    	int score = 0;
    	int remainingShifts = rnd.nextInt(5) + 1;
    	int currentStep = 0;
    	
    	Scanner sc = new Scanner(System.in);
    	System.out.print("Enter player name: ");
    	String playerName = sc.nextLine();
    	System.out.println();
    	System.out.println("ARE YOU READY? THE GAME STARTS NOW!...");
    	System.out.println();
    	
    	
    	int totalNumberSets = set1.size() + set2.size() + set3.size() + set4.size() + set5.size();
    	int maxStepCount = (int)(totalNumberSets * 1.2);
    	   	
    	display(set1, set2, set3, set4, set5, reserveQueue, supplementaryQueue, score, remainingShifts, currentStep, maxStepCount);
     	
    	
    	
    // denemece	
    	
    	
    	boolean gameOver = false; 
    	
    	while (currentStep < maxStepCount && !gameOver) 
    	{
    	    System.out.print("\n>> ");
    	    String command = sc.nextLine();
    	    
    	    if (command.length() == 1 && (command.charAt(0) == 'F' || command.charAt(0) == 'f')) 
    	    {
    	        gameOver = true; 
    	        System.out.println("------------------------------");
    	        System.out.println("Exiting the game...");
    	        System.out.println("Finalizing your score!..");
    	        System.out.println("------------------------------");
    	    }
    	    else
    	    {
    	    	if (command.length() == 10 && command.charAt(0) == 'S' && command.charAt(5) == 'Q') 
        	    {
        	        remainingShifts = shiftQueue(reserveQueue, remainingShifts);
        	        currentStep++;
        	    }
        	    
        	    else if (command.length() == 9 && command.charAt(0) == 'A' && command.charAt(3) == 'S') 
        	    {
        	        char nChar = command.charAt(7); 
        	        int n = nChar - '0'; 

        	        if (n == 1) 
        	        {
        	        	score = addSet(set1, reserveQueue, score, remainingShifts);
        	        }  	     	
        	        else if (n == 2)
        	        {
        	        	score = addSet(set2, reserveQueue, score, remainingShifts);
        	        }
        	        else if (n == 3) 
        	        {
        	        	score = addSet(set3, reserveQueue, score, remainingShifts);
        	        }
        	        else if (n == 4) 
        	        {
        	        	score = addSet(set4, reserveQueue, score, remainingShifts);
        	        }
        	        else if (n == 5) 
        	        {
        	        	score = addSet(set5, reserveQueue, score, remainingShifts);
        	        }
        	        
        	        currentStep++;
        	    }

        	    else if (command.length() == 10 && command.charAt(0) == 'M' && command.charAt(3) == 'c') 
        	    {
        	        int n = command.charAt(6) - '0';
        	        int m = command.charAt(8) - '0';

        	        Stack sN = null; Stack sM = null;
        	        
        	        if (n == 1) 
        	        	sN = set1; 
        	        else if (n == 2) 
        	        	sN = set2;
        	        else if (n == 3) 
        	        	sN = set3; 
        	        else if (n == 4) 
        	        	sN = set4;
        	        else if (n == 5) 
        	        	sN = set5;

        	        
        	        if (m == 1) 
        	        	sM = set1; 
        	        else if (m == 2) 
        	        	sM = set2; 
        	        else if (m == 3) 
        	        	sM = set3; 
        	        else if (m == 4)
        	        	sM = set4; 
        	        else if (m == 5) 
        	        	sM = set5;
        	        

        	        if (sN != null && sM != null) 
        	        {
        	            score = match(sN, sM, score);
        	        }
        	        
        	        currentStep++;
        	    }
        	    else 
        	    {
        	        System.out.println("Invalid command! Please use: ShiftQueue, AddSet(n), or Match(n,m)");
        	    }

        	    if (currentStep > 0 && currentStep % 3 == 0) 
        	    {
        	        System.out.println("3 Steps Passed: !!Supplementary Action!!");
        	        autoAddFromSupplementary(set1, set2, set3, set4, set5, supplementaryQueue);
        	    }
        	    
        	    if (set1.isEmpty() && set2.isEmpty() && set3.isEmpty() && set4.isEmpty() && set5.isEmpty()) 
        	    {
        	        gameOver = true;
        	        System.out.println("\n========================================");
        	        System.out.println("CONGRATULATIONS! ALL SETS ARE CLEARED!");
        	        System.out.println("========================================");
        	    }
        	    
        	    display(set1, set2, set3, set4, set5, reserveQueue, supplementaryQueue, score, remainingShifts, currentStep, maxStepCount);
    	    }

    	    
    	}
       
    	System.out.println("\n********** GAME OVER **********");
    	System.out.println("\n*********************************");
    	System.out.println("         FINAL RESULTS           ");
    	System.out.println("*********************************");
    	System.out.println("Player Name : " + playerName);
    	System.out.println("Final Score : " + score);
    	System.out.println("Total Steps : " + currentStep + "/" + maxStepCount);
    	System.out.println("*********************************");
    	System.out.println();
    	
    	
    	if (score > 0) 
    		System.out.println("Well done, " + playerName + "!");
    	else 
    		System.out.println("Better luck next time, " + playerName + ".");
    	
    	System.out.println("\n[System] Your score has been successfully saved to HighScoreTable.txt");
    	
    
    	Queue highScoreTable = new Queue(10); 

    	File file = new File("HighScoreTable.txt");
    	if (file.exists()) 
    	{
    	    Scanner fs = new Scanner(file);
    	    
    	    while (fs.hasNext() && !highScoreTable.isFull()) 
    	    {
    	        String n = fs.next();
    	        int s = fs.nextInt();
    	        highScoreTable.enqueue(n + " " + s); 
    	    }
    	    
    	    fs.close();
    	}

    	updateHighScoreTable(highScoreTable, playerName, score);
    	
    	
    	FileWriter fw = new FileWriter("HighScoreTable.txt", false);
    	PrintWriter out = new PrintWriter(fw);

    	
    	System.out.println("\n======= HIGH SCORE TABLE =======");
    	System.out.println();
    	
    	int rank = 1;
    	
    	while (!highScoreTable.isEmpty()) 
    	{
    	    String finalEntry = (String) highScoreTable.dequeue();
    	    
    	    out.println(finalEntry);
    	    
    	    System.out.println(rank + ". " + finalEntry);
    	    rank++;
    	}
    	
    	out.close();
    	fw.close();
    	
    	
    	System.out.println("\n======= HIGH SCORE TABLE =======");
    	
    	
    }
    
    
    // komutlar kısmısı
    
    public static int addSet(Stack targetStack, Queue reserveQueue, int score, int remainingShifts) 
    {
        if (targetStack.isFull()) 
        {
            System.out.println("You can't do that! Set is FULL!..");
        } 
        else 
        {
        	if (reserveQueue.isEmpty()) 
        	{
                System.out.println("Reserve Queue is empty!");
                return score;
            }
        	else 
            {
        		char letter = (Character)reserveQueue.dequeue();
                targetStack.push(letter);
                System.out.println("Operations is done! You added successfully!..");
            }       	
        }
        
        if(remainingShifts == 0) 
        {
        	System.out.println("Since you have no shift right, you lose your points!..");
        	System.out.println("AddSet penalty: -2 points!");
        	
        	score = score - 2;
        }
        	
        return score; 
    }
    
    
    public static int match(Stack s1, Stack s2, int score) 
    {
    	if (s1 == s2) 
    	{
            System.out.println("You cannot match a set with itself!..");
            return score;
    	}
    	
    	if (s1.isEmpty() || s2.isEmpty()) 
    	{
            System.out.println("One or both sets are empty! Cannot match!..");
            return score;
        }
    	
    	char letter1 = (Character)s1.pop();
        char letter2 = (Character)s2.pop();
    	
        if (letter1 == letter2) 
        {
            System.out.println("MATCH! Items are same: " + letter1);
            System.out.println("Since you matched successfully you earned +5 points!..");
            score = score + 5;
        }
        else 
        {
            System.out.println("No match! Items are different: " + letter1 + " and " + letter2);
            s1.push(letter1);
            s2.push(letter2);
        }
    	
        return score; 
    }
    
    
    public static int shiftQueue(Queue reserveQueue, int remainingShifts) 
    {
    	if (remainingShifts <= 0) 
    	{
            System.out.println("No shifts left! You cannot shift anymore!..");
            return remainingShifts;
    	}
    	
    	if (reserveQueue.isEmpty()) 
    	{
            System.out.println("Reserve Queue is empty! Nothing to shift!..");
            return remainingShifts;
        }
    	
    	char letter = (Character)reserveQueue.dequeue();
        reserveQueue.enqueue(letter);
        
        remainingShifts--;
        
        System.out.println("Shift is done! Front item moved to the Rear!..");
        return remainingShifts;
    	
    }
    
    
    public static void autoAddFromSupplementary(Stack s1, Stack s2, Stack s3, Stack s4, Stack s5, Queue sq) 
    {
        if (sq.isEmpty()) 
        {
            System.out.println("Supplementary Queue is empty! No automatic add!..");
            return;
        }

        int minSize = s1.size();
        
        if (s2.size() < minSize) 
        	minSize = s2.size();
        if (s3.size() < minSize) 
        	minSize = s3.size();
        if (s4.size() < minSize) 
        	minSize = s4.size();
        if (s5.size() < minSize) 
        	minSize = s5.size();

        char letter = (Character)sq.dequeue();
        
        if (s1.size() == minSize) 
        {
            s1.push(letter);
            System.out.println("Auto-Add: " + letter + " added to Set1!..");
        } 
        else if (s2.size() == minSize) 
        {
            s2.push(letter);
            System.out.println("Auto-Add: " + letter + " added to Set2!..");
        } 
        else if (s3.size() == minSize) 
        {
            s3.push(letter);
            System.out.println("Auto-Add: " + letter + " added to Set3!..");
        } 
        else if (s4.size() == minSize)
        {
            s4.push(letter);
            System.out.println("Auto-Add: " + letter + " added to Set4!..");
        } 
        else if (s5.size() == minSize) 
        {
            s5.push(letter);
            System.out.println("Auto-Add: " + letter + " added to Set5!..");
        }
    }
    
    
    private static int getScoreFromEntry(String entry)
    {
	    int score = 0;
	    boolean spaceFound = false;
	    
	    for (int i = 0; i < entry.length(); i++) 
	    {
	        char c = entry.charAt(i);
	        
	        if (c == ' ') 
	        {
	            spaceFound = true;
	            continue;
	        }
	        
	        if (spaceFound) 
	        {
	            score = score * 10 + (c - '0');
	        }
	    }
	    
	    return score;
	}
    
    
    public static void updateHighScoreTable(Queue table, String playerName, int playerScore) 
    {
        Queue tempQueue = new Queue(10);
        
        boolean added = false;
        int currentSize = table.size();

        for (int i = 0; i < currentSize; i++) 
        {
            Object entry = table.dequeue();
            int existingScore = getScoreFromEntry(entry.toString());

            if (!added && playerScore >= existingScore) 
            {
                if (!tempQueue.isFull()) 
                {
                    tempQueue.enqueue(playerName + " " + playerScore); 
                    added = true;
                }
            }

            if (!tempQueue.isFull()) 
            {
                tempQueue.enqueue(entry);
            }
        }

        if (!added && !tempQueue.isFull())
        {
            tempQueue.enqueue(playerName + " " + playerScore);
        }

        while (!table.isEmpty()) table.dequeue();
        while (!tempQueue.isEmpty()) table.enqueue(tempQueue.dequeue());
    }
    
    
    
    //yazdırma kısmısı
    
    public static void display(Stack s1, Stack s2, Stack s3, Stack s4, Stack s5, Queue rq, Queue sq, int score, int shifts, int step, int maxStepCount) 
    {
        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
        int pLine = 0;

        pLine = printSetAndPanel(s1, "Set1", pLine, rq, sq, score, shifts, step, maxStepCount);
        pLine = printSetAndPanel(s2, "Set2", pLine, rq, sq, score, shifts, step, maxStepCount);
        pLine = printSetAndPanel(s3, "Set3", pLine, rq, sq, score, shifts, step, maxStepCount);
        pLine = printSetAndPanel(s4, "Set4", pLine, rq, sq, score, shifts, step, maxStepCount);
        pLine = printSetAndPanel(s5, "Set5", pLine, rq, sq, score, shifts, step, maxStepCount);

        while (pLine <= 10) 
        {
            for (int i = 0; i < 45; i++) 
            {
                System.out.print(" "); 
            }
            
            drawPanelSide(pLine, rq, sq, score, shifts, step, maxStepCount);
            System.out.println();
            pLine++;
        }
        
        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
    }
    

    public static int printSetAndPanel(Stack s, String setName, int pLine, Queue rq, Queue sq, int score, int shifts, int step, int maxStepCount) 
    {
        Stack temp = new Stack(10);
        Stack copy = new Stack(10);
        
        while (!s.isEmpty()) 
        {
            temp.push(s.pop());
        }
        
        while (!temp.isEmpty()) 
        {
            char letter = (Character)temp.pop();
            s.push(letter);
            copy.push(letter); 
        }

        int localLine = 0;
        
        while (!copy.isEmpty() || localLine == 0) 
        {
        	char letter = ' ';
        	
            if (!copy.isEmpty()) 
            {
            	letter = (Character)copy.pop();
            }

            int currentPos = 0;
            
            if (localLine == 0) 
            {
                System.out.print(setName); 
                System.out.print(": Top -> ");
                
                if (letter != ' ') 
                { 
                    System.out.print(letter); 
                    currentPos = 14; 
                } 
                else 
                { 
                    currentPos = 13; 
                }
            } 
            else 
            {
                for (int i = 0; i < 13; i++) 
                {
                    System.out.print(" ");
                }
                
                if (letter != ' ') 
                {
                    System.out.print(letter); 
                    currentPos = 14;
                    
                    if (copy.isEmpty()) 
                    { 
                        System.out.print(" <- Bottom"); 
                        currentPos += 10; 
                    }
                } 
                else 
                {
                    currentPos = 13;
                }
            }

            for (int i = currentPos; i < 45; i++)
            {
                System.out.print(" ");
            }
            
            drawPanelSide(pLine, rq, sq, score, shifts, step, maxStepCount);
            
            System.out.println();
            localLine++;
            pLine++;
            
            if (letter == ' ') 
            {
                break;
            }
        }
        
        for (int i = 0; i < 45; i++) 
        {
            System.out.print(" ");
        }
        
        drawPanelSide(pLine, rq, sq, score, shifts, step, maxStepCount);
        System.out.println();
        
        pLine++;

        return pLine;
    }
    
    
    public static void drawPanelSide(int line, Queue rq, Queue sq, int score, int shifts, int step, int maxStepCount) {
        if (line == 0) 
        {
            System.out.print("--------------------------------------------------------------------------");
        } 
        else if (line == 1) 
        {
            System.out.print("Reserve Queue:");
        } 
        else if (line == 2) 
        {
            System.out.print("   Front -> ");
            displayQueue(rq);
            System.out.print("<- Rear");
        } 
        else if (line == 4) 
        {
            System.out.print("Supplementary Queue:");
        } 
        else if (line == 5) 
        {
            System.out.print("   Front -> ");
            displayQueue(sq);
            System.out.print("<- Rear");
        } 
        else if (line == 6) 
        {
            System.out.print("--------------------------------------------------------------------------");
        } 
        else if (line == 8) 
        {
            System.out.print("Score: "); System.out.print(score);
            System.out.print(" | Remaining Shifts: "); System.out.print(shifts);
            System.out.print(" | Step: "); System.out.print(step);
            System.out.print("/" + maxStepCount);
        }
    }

   
    public static void displayQueue(Queue q) 
    {
        int s = q.size();
        
        for (int i = 0; i < s; i++)
        {
            Object item = q.dequeue();
            System.out.print(item);
            System.out.print(" ");
            q.enqueue(item); 
        }
    }
    
   
    
    
}