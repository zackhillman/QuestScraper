
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.Normalizer;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;


public class JavaPDFTest {

    public static void main(String[] args) throws IOException {
    

    	
    	File folder = new File("folder");
    	File[] files = folder.listFiles(new FilenameFilter() {

    	    @Override
    	    public boolean accept(File dir, String name) {
    	        return name.toLowerCase().endsWith(".pdf");
    	    }
    	});
    	
    	for(int j = 0; j<files.length;j++){
	        File file = files[j];
	        PDDocument document = PDDocument.load(file);
	        
	        
	        PDFRenderer renderer = new PDFRenderer(document);
	        

	      
	         
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        //Instantiate PDFTextStripper class
	        PDFTextStripper pdfStripper = new PDFTextStripper();
	
	        
	        //Retrieving text from PDF document
	        String text = pdfStripper.getText(document);
	       
	        int index = text.indexOf("have ")+5;
	        int numProb = Integer.parseInt(text.substring(index,index+2).trim());
	        
	        PDDocument newDoc = new PDDocument();
	    	PDPage page = new PDPage();
	    	  newDoc.addPage(page);
	    	PDPageContentStream contentStream = new PDPageContentStream(newDoc, page); 
	        
	        //Begin the Content stream 
	        contentStream.beginText(); 
	         
	        //Setting the font to the Content stream
	       
	        contentStream.setFont( PDType1Font.TIMES_ROMAN, 12 );
	
	        //Setting the leading
	        contentStream.setLeading(14.5f);
	
	        //Setting the position for the line
	        contentStream.newLineAtOffset(25, 725);
	
	       String[] answers = ExtractInfo.getAnswers(text);
	       for(int i = 0; i<answers.length;i++){
	    	   try{
	    		 
	    			 
	    			   System.out.println(answers[i]);
	    		   contentStream.showText( answers[i].replaceAll("\\P{Print}", "")); 
	           	}catch(Exception e){
	           		System.out.println(e);
	          	}
	           	contentStream.newLine(); 
	       }
	       

	        contentStream.endText();
	        contentStream.close();
	
	        newDoc.save("a"+files[j].getName());
	
	        newDoc.close();
	
	        document.close();  
    	}
    }    
}