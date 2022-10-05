package com.dappercloud;

public class DataStructure {
    //Sure could use the built in ds here
    //but I do not think that is the point
    private char[] stack;
    private char[] queue;

    public void pushCharacter(char ch){  
    	
    	//just handling this
    	if(stack==null) {
    		stack = new char[1];
    		stack[0] = ch;
    		return; //short circut
    	}
    	
        //Check the length and add 1.
        int len = stack.length;
        
    	//Create a temp stack
    	char[] tmp = new char[len+1];
    	
    	//put all the old values in the stack
    	for(int i = 0; i < len; i++) {
    		tmp[i] = stack[i];
    	}
        
        //put the new value at the top
    	tmp[len] = ch;
    	
    	//set temp to stack
    	stack = tmp;
    	
    }

    public char popCharacter(){
        int len = stack.length;
        char ch = stack[0];
        char[] tmp = new char[len-1];
        for(int i = 1; i < len-1; i++) {
        	tmp[i] = stack[i];
        }
        stack = tmp;
        return ch;
    }
    
    public void enqueueCharacter(char ch){
    	//just handling this
    	if(queue==null) {
    		queue = new char[1];
    		queue[0] = ch;
    		return; //short circut
    	}
    	
        int len = queue.length;
        
    	//Create a temp queue
    	char[] tmp = new char[len+1];
    	
    	//put all the old values in the queue one over
    	//This will let us just pop the top for dequeuing
    	for(int i = 0; i < len; i++) {
    		tmp[i+1] = queue[i];
    	}
        
        //put the new value at the top
    	tmp[0] = ch;
    	
    	//set temp to stack
    	queue = tmp;
    	
    }

    public char dequeueCharacter(){
        int len = queue.length;
        char ch = queue[0];
        char[] tmp = new char[len-1];
        for(int i = 1; i < len-1; i++) {
        	tmp[i] = queue[i];
        }
        queue = tmp;
        return ch;
    }
    
    public static void main(String[] args) {
        
        String input = "racecar";

        // Convert input String to an array of characters:
        char[] s = input.toCharArray();

        // Create a Solution object:
        DataStructure p = new DataStructure();

        // Enqueue/Push all chars to their respective data structures:
        for (char c : s) {
            p.pushCharacter(c);
            p.enqueueCharacter(c);
        }

        // Pop/Dequeue the chars at the head of both data structures and compare them:
        boolean isPalindrome = true;
        for (int i = 0; i < s.length/2; i++) {
            if (p.popCharacter() != p.dequeueCharacter()) {
                isPalindrome = false;                
                break;
            }
        }

        //Finally, print whether string s is palindrome or not.
        System.out.println( "The word, " + input + ", is " 
                           + ( (!isPalindrome) ? "not a palindrome." : "a palindrome." ) );
    }
}