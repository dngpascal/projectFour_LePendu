package projectFour_JeuDePendu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
//


// class NouvellePartie extends JPanel 
public class NouvellePartie extends JeuDuPendu
{
	// variables d'instance : mise en forme
	private static Color myBlue = new Color(153, 174, 202) ;
	private static ImageIcon img ; 
	private static Graphics g ;  
	Font myFont = new Font("Arial", Font.BOLD, 12) ;
	Font myFont2 = new Font("Arial", Font.PLAIN, 30) ; 
	Color motPenduCouleur = new Color(227, 238, 244) ; 
 
	
	// panel North 
	public JPanel nouvellePartieNorth()
	{
		JPanel nouvellePartieNorth = new JPanel() ; 
		nouvellePartieNorth.setPreferredSize(new Dimension(600, 75)) ;
		nouvellePartieNorth.setBackground(myBlue) ;  
	 
		return nouvellePartieNorth ; 
	}
	//
	
	
	// panel West   
	public JPanel nouvellePartieWest1 (String motTest) 
	{
		JPanel panMot = new JPanel() ; 
		panMot.setBackground(motPenduCouleur) ; 
		JLabel motTestLabel = new JLabel(motTest) ; 
		motTestLabel.setFont(myFont2); ;  
		panMot.add(motTestLabel) ; 
		return panMot ; 
	}
	// 
	public JPanel nouvellePartieWest3 (int ScoreDeLaSession) 
	{    
		JPanel panIndication1 = new JPanel() ; 
		panIndication1.setBackground(Color.white) ;  
		JLabel scoreSession = new JLabel("Score depuis le début de la session : " + ScoreDeLaSession + " points.") ; 
		scoreSession.setFont(myFont) ;  
		panIndication1.add(scoreSession) ; 
		return panIndication1 ; 
	}
	//
	public JPanel nouvellePartieWest4 (int NombreDePartie) 
	{ 
		JPanel panIndication2 = new JPanel() ; 
		panIndication2.setBackground(Color.white) ;
		JLabel nombrePartie = new JLabel("Partie n° " + NombreDePartie + " en cours.") ; 
		nombrePartie.setFont(myFont);  
		panIndication2.add(nombrePartie) ;  
		return panIndication2 ; 
	}
	//	
	public JPanel nouvellePartieWest5 (int NombreErreursParPartie)
	{ 
		JPanel panIndication3 = new JPanel() ; 
		panIndication3.setBackground(Color.white) ;
		JLabel nombreErreurs = new JLabel("Nombre d'erreurs restantes : " + (7 - NombreErreursParPartie) + ".") ; 
		nombreErreurs.setFont(myFont);   
		panIndication3.add(nombreErreurs) ; 
		return panIndication3 ;   
	} 
	//
	 
	
	// panel East avec l'image du pendu 
	public JPanel nouvellePartieEast (int NombreErreursParPartie) 
	{   
		switch (NombreErreursParPartie)
		{ 
			case 0:
				img = new ImageIcon(getClass().getResource("pendu0Err.jpg")) ; 
				break ;  
			case 1:
				img = new ImageIcon(getClass().getResource("pendu1Err.jpg")) ;
				break ;
			case 2:
				img = new ImageIcon(getClass().getResource("pendu2Err.jpg")) ; 
				break ;
			case 3:
				img = new ImageIcon(getClass().getResource("pendu3Err.jpg")) ;
				break ;
			case 4:
				img = new ImageIcon(getClass().getResource("pendu4Err.jpg")) ;
				break ;
			case 5:
				img = new ImageIcon(getClass().getResource("pendu5Err.jpg")) ;	
				break ;
			case 6:
				img = new ImageIcon(getClass().getResource("pendu6Err.jpg")) ;
				break ;
			case 7:
				img = new ImageIcon(getClass().getResource("pendu7Err.jpg")) ;
				break ;
		}
		 
		JLabel labelImgPendu = new JLabel(img) ;    
		
		JPanel nouvellePartieEast = new JPanel(new BorderLayout()) ; 
		nouvellePartieEast.setBackground(Color.white) ;
		nouvellePartieEast.setPreferredSize(new Dimension(420, 250)) ;
		nouvellePartieEast.add(labelImgPendu, BorderLayout.CENTER) ;  
		
		return nouvellePartieEast ; 
	} 
	// 
	
	
	// panel South 
	public JPanel nouvellePartieSouth () 
	{
		JPanel nouvellePartieSouth = new JPanel() ;  
		nouvellePartieSouth.setPreferredSize(new Dimension(600, 75)) ;
		nouvellePartieSouth.setBackground(myBlue) ;
		return nouvellePartieSouth ; 
	}
	//
}
