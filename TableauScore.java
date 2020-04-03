package projectFour_JeuDePendu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;

public class TableauScore 
{
	// pseudo et score
	String pseudo ;
	String score ;
		
	// Pseudo Original et Copy
	File filePseudoOriginal = new File("pseudoTop10.txt") ;   
	File filePseudoCopy = new File("pseudoTop10Copy.txt") ;
	 
	// Score Original et Copy
	File fileScoreOriginal = new File("scoreTop10.txt") ;   
	File fileScoreCopy = new File("scoreTop10Copy.txt") ;  
	
	
	//
	
	 
				
				
	//
	public String readPseudoTop10 (int index) 
	{
		FileReader readerFilePseudoOriginal ; 
		BufferedReader readerBufferFilePseudoOriginal ; 
		LineNumberReader readerLineFilePseudoOriginal ;
		
		try
		{  
			readerFilePseudoOriginal = new FileReader(filePseudoOriginal) ; 
				readerBufferFilePseudoOriginal = new BufferedReader(readerFilePseudoOriginal) ; 
					readerLineFilePseudoOriginal = new LineNumberReader(readerBufferFilePseudoOriginal) ; 
			 			 
			while ( readerLineFilePseudoOriginal.read() != - 1)
			{
				if ( readerLineFilePseudoOriginal.getLineNumber() == index )
				{ 
					pseudo = readerLineFilePseudoOriginal.readLine() ;   
				}  
			}    
			
			readerLineFilePseudoOriginal.close() ;  
		}
		
		catch ( FileNotFoundException e ) { e.printStackTrace() ; }
		catch ( IOException e ) { e.printStackTrace() ; } 
		
		return pseudo ; 
	}
	//
	public void writePseudoTop10(int index, String pseudo)
	{
		FileReader readerFilePseudoOriginal ; 
		BufferedReader readerBufferFilePseudoOriginal ; 
		LineNumberReader readerLineFilePseudoOriginal ;
		
		FileWriter writerFilePseudoCopy ; 
		BufferedWriter writerBufferPseudoCopy ; 
		PrintWriter writerPrintPseudoCopy ;
		
		try
		{   
			readerFilePseudoOriginal = new FileReader(filePseudoOriginal) ;
			readerBufferFilePseudoOriginal = new BufferedReader(readerFilePseudoOriginal) ;
			readerLineFilePseudoOriginal = new LineNumberReader(readerBufferFilePseudoOriginal) ;
			
			writerFilePseudoCopy = new FileWriter(filePseudoCopy) ;
			writerBufferPseudoCopy = new BufferedWriter(writerFilePseudoCopy) ; 
			writerPrintPseudoCopy = new PrintWriter(writerBufferPseudoCopy) ; 
			
			
			for (int i = 0 ; i < 11 ; i++)
			{
				if (i == index)
				{  
					String str = pseudo + "\n" ;  
					writerPrintPseudoCopy.write(str) ;  
				}
				else
				{  
					String str = readerLineFilePseudoOriginal.readLine() + "\n" ; 
					writerPrintPseudoCopy.write(str) ;  
				} 
			} 
			 
			readerLineFilePseudoOriginal.close() ; 
			writerPrintPseudoCopy.close() ; 
			//  
		}
		
		catch (FileNotFoundException e) { e.printStackTrace() ; }
		catch (IOException e) { e.printStackTrace() ; } 
		
		filePseudoCopy.renameTo(filePseudoOriginal) ; 
		filePseudoCopy.delete() ;  
	} 
	//
	
	
	//
	
	// 
	public String readScoreTop10 (int index)
	{
		FileReader readerFileScoreOriginal ; 
		BufferedReader readerBufferFileScoreOriginal ; 
		LineNumberReader readerLineFileScoreOriginal ;
		
		try
		{
			fileScoreOriginal = new File("scoreTop10.txt") ; 
				readerFileScoreOriginal = new FileReader(fileScoreOriginal) ; 
					readerBufferFileScoreOriginal = new BufferedReader(readerFileScoreOriginal) ; 
						readerLineFileScoreOriginal = new LineNumberReader(readerBufferFileScoreOriginal) ; 
			
			while ( readerLineFileScoreOriginal.read() != - 1)
			{  
				while ( readerLineFileScoreOriginal.getLineNumber() == index )  
				{ 
					score = readerLineFileScoreOriginal.readLine() ;  
				}  
			}  
			
			readerLineFileScoreOriginal.close() ;
						
		}
		
		catch ( FileNotFoundException e ) { e.printStackTrace() ; }
		catch ( IOException e ) { e.printStackTrace() ; } 
		
		return score ; 
	}
	//
	public void writeScoreTop10(int index, int score)
	{
		
		FileReader readerFileScoreOriginal ; 
		BufferedReader readerBufferFileScoreOriginal ; 
		LineNumberReader readerLineFileScoreOriginal ;
		
		FileWriter writerFileScoreCopy ;
		BufferedWriter writerBufferScoreCopy ; 
		PrintWriter writerPrintScoreCopy ; 
		
		try
		{   
			readerFileScoreOriginal = new FileReader(fileScoreOriginal) ;
			readerBufferFileScoreOriginal = new BufferedReader(readerFileScoreOriginal) ;
			readerLineFileScoreOriginal = new LineNumberReader(readerBufferFileScoreOriginal) ;
			
			writerFileScoreCopy = new FileWriter(fileScoreCopy) ;
			writerBufferScoreCopy = new BufferedWriter(writerFileScoreCopy) ; 
			writerPrintScoreCopy = new PrintWriter(writerBufferScoreCopy) ; 
			
			
			for (int i = 0 ; i < 11 ; i++)
			{
				if (i == index)
				{  
					String str = String.valueOf(score) + "\n" ;  
					writerPrintScoreCopy.write(str) ;  
				}
				else
				{  
					String str = readerLineFileScoreOriginal.readLine() + "\n" ; 
					writerPrintScoreCopy.write(str) ;  
				} 
			} 
			 
			readerLineFileScoreOriginal.close() ; 
			writerPrintScoreCopy.close() ; 
			//  
		}
		
		catch (FileNotFoundException e) { e.printStackTrace() ; }
		catch (IOException e) { e.printStackTrace() ; } 
		
		fileScoreCopy.renameTo(fileScoreOriginal) ; 
		fileScoreCopy.delete() ; 
	}
	//
 }





