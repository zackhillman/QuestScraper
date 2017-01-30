

public class ExtractInfo {


	
	
	public static String[] getAnswers(String input){
	
		boolean MC = false;
        int index = input.indexOf("have ")+5;
        int numProb = Integer.parseInt(input.substring(index,index+2).trim());
        String[] answers = new String[numProb];
        int nextProbI = 0;
        
        for (int i = 0; i<numProb; i++){
        	String answer = "";
        	answer+=("["+(i+1)+"] ");
        	int begI = input.indexOf("points",nextProbI);
        	
        	
        	int endI = input.indexOf("Explanation:", begI);
        	nextProbI = endI+12;
        	
        	String newText = input.substring(begI, endI);
        	boolean found = false;
        	int sInde = 0;
        	int ix = -1;
        	
        	if(newText.indexOf("correct\n",sInde)==-1){
        		if(newText.indexOf("Correct answer:")==-1)
        			answer += "Unknown";
        		else{
        			MC = false;
	        		 ix = newText.indexOf("Correct answer:")+15;
	         	
	         		answer += newText.substring(ix).trim();
	         	
	         		answer = answer.split("\n")[0];
        		}
      
         
         		
        	}else{
        		MC = true;
        		int pIX = 0;
        		while(ix==-1){
        			if(newText.indexOf("correct",sInde)==-1){
        				ix = pIX;
        			}else{
        				 pIX =  newText.indexOf("correct",sInde);
            			if(newText.indexOf("correct",sInde)+8==newText.length()){
            				 ix = newText.indexOf("correct",sInde);
            			}else if((Character.isDigit(newText.charAt(newText.indexOf("correct",sInde)+8)))){
            					ix = newText.indexOf("correct",sInde);
            			}else{
            				sInde = newText.indexOf("correct",sInde)+7;

            			}
        			}
        			
        			

            		
            			
            		}
            		while(!found){
            			if(newText.charAt(ix)=='.'&&Character.isDigit(newText.charAt(ix-1))){
            					 answer += newText.substring(ix-2, ix+1).trim();
        
            					found = true;
            			}
            			ix--;
            		}
        		
        	}
        	if(answer.indexOf(" 10")!=-1&&!MC)
        		answer= "["+(i+1)+"] Unknown (scientific notation)";
        	answers[i] = answer;
        		
        }
		return answers;
        
	}
	
	
	
	
}
