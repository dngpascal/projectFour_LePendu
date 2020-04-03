package projectFour_JeuDePendu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;

public class Dictionnaire   
{
	// variables d'instance
	private static String motPendu ;
	private static String motDictionnaire ;
	private static ArrayList charMotPenduArray ; 
	private static String motTest ;    
	
	
	
	// constructeur Dictionnaire 
	public Dictionnaire() 
	{ 
		 
		File file ;
			FileReader readerFile ;  
				BufferedReader readerBuffer ; 
					LineNumberReader readerLine ; 
		
		try  
		{ 
			file = new File("dictionnaire.txt") ; 
				readerFile = new FileReader(file) ;  
					readerBuffer = new BufferedReader(readerFile) ;  
						readerLine = new LineNumberReader(readerBuffer) ;  	
			
			int nombre = (int) (Math.random() * 336529) ;  
			String str = "" ;  
			
			while (readerLine.read() != - 1)
			{
				if (readerLine.getLineNumber() == nombre)
				{ 
					motDictionnaire = readerLine.readLine() ; 
				}
			}
			readerLine.close() ; 
		}
		
		catch (FileNotFoundException e) { e.printStackTrace() ; }
		catch (IOException e) { e.printStackTrace() ; }
		 
		  
		// on génère le mot à trouver  
		motPendu = motDictionnaire.toLowerCase() ;  
		
		
		// on génère un Array avec tous les caractères du mot à trouver
		int nombreCharMotPendu = motPendu.length() ;
		charMotPenduArray = new ArrayList<String>(nombreCharMotPendu) ; 
		for (int i = 0 ; i < nombreCharMotPendu ; i++)
		{
			switch (motPendu.charAt(i)) 
			{ 
				case 'à' : case 'â' : case 'á' : case 'ä' : case 'ã' : case 'ā' : 
					charMotPenduArray.add("a") ;
					break ; 
				case 'é' : case 'è' : case 'ê' : case 'ë' : case 'ę' : case 'ē' : case 'ė' :  
					charMotPenduArray.add("e") ;
					break ;  
				case 'î' : case 'ï' : case 'ì' : case 'í' : case 'ī' : 
					charMotPenduArray.add("i") ; 
					break ; 
				case 'ô' : case 'ö' : case 'ò' : case 'ó' : case 'õ' : case 'ō' : 
					charMotPenduArray.add("o") ; 
					break ; 
				case 'û' : case 'ù' : case 'ü' : case 'ú' : case 'ū' : 
					charMotPenduArray.add("u") ; 
					break ;
				default : 
					charMotPenduArray.add(motPendu.charAt(i)) ; 
					break ;  
			}   
		}  
		
		// on génère le mot test qui est affiché dynamiquement au fil des essais
		motTest = "*" ;
		for (int i = 0 ; i < nombreCharMotPendu - 1 ; i++)
		{
			if (charMotPenduArray.get(i) == "-")
			{
				motTest += "-" ; 
			}
			else
			{
				motTest += "*" ; 
			} 
		}
		//
	} 
	
	// accesseurs
	public String getMotPendu()
	{ 
		return motPendu ; 
	}
	// 
	public ArrayList getCharMotPenduArray()
	{
		return charMotPenduArray ; 
	}
	//
	public String getMotTest()
	{
		return motTest ; 
	}
	// 
	public String getMotDictionnaire () 
	{
		return motDictionnaire ; 
	}
	//
	
	
	// mutateurs 
	public void updateMotTest(int index)
	{
		//
		String copieMotTest = motTest ; 
		motTest = "" ; 
		
		
		//
		for (int i = 0 ; i < copieMotTest.length(); i++)
		{
			if (i == index)
			{
				motTest += motDictionnaire.charAt(index) ;  
			}
			else
			{
				motTest += copieMotTest.charAt(i) ;
			} 
		} 
		//  
	}
	//
}

// Revoir "Gérer les flux d'entrée et de sortie"