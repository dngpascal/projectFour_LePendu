package projectFour_JeuDePendu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
//
 
 
// class SplashScreen (écran d'accueil)
public class LePendu extends JWindow
{
	// variables d'instance
	private static LePendu splash ;  
	private static Color myBlue = new Color(153, 174, 202) ; 
	//
	
	
	// constructeur 
	public LePendu()
	{
		// mise en place du JWindow
		this.setSize(600, 485) ;
		this.setLocationRelativeTo(null) ; 
		// 
		
		
		// mise en place du panel NORTH avec un titre 
		Font fontTitre = new Font("Arial", Font.BOLD, 18) ;
		
		JLabel titre = new JLabel("Le Jeu du Pendu.") ; 
		titre.setFont(fontTitre) ;
		titre.setForeground(Color.white) ;
		
		JPanel panNorth = new JPanel() ; 
		panNorth.add(titre) ; 
		panNorth.setBackground(myBlue) ;
		//
		
		
		// mise en place du panel CENTER avec l'image centrale  
		ImageIcon img = new ImageIcon(getClass().getResource("penduImg1.jpg")) ;
		JLabel labelImg = new JLabel(img) ; 
		
		labelImg.setBorder(BorderFactory.createLineBorder(Color.gray)) ;
		
		JPanel panCenter = new JPanel() ;
		panCenter.add(labelImg) ; 
		labelImg.setVerticalAlignment(JLabel.CENTER) ; 
		labelImg.setHorizontalAlignment(JLabel.CENTER) ; 
		panCenter.setBackground(myBlue) ;
		// 
		
		
		// mise en place du panel SOUTH avec les boutons "Let's Go !" et "Quitter" 
		Font fontButton = new Font("Courier", Font.PLAIN, 12) ;
		
		JButton startGame = new JButton("Let's Go !") ; 
		startGame.addActionListener(new startGameListener()) ;
		startGame.setFont(fontButton) ;
		
		
		JButton leaveGame = new JButton("Une autre fois...") ;
		leaveGame.addActionListener(new leaveGameListener()) ;
		leaveGame.setFont(fontButton) ;
		
		JPanel panSouth = new JPanel() ;
		panSouth.add(startGame) ; 
		panSouth.add(leaveGame) ;
		panSouth.setBackground(myBlue) ;
		//
		
		
		// finalisation du JWindow
		getContentPane().add(panNorth, BorderLayout.NORTH) ;
		getContentPane().add(panCenter, BorderLayout.CENTER) ;
		getContentPane().add(panSouth, BorderLayout.SOUTH) ;
		//
	}
	//
	
	
	// Action Listener
	class startGameListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			JeuDuPendu ecranPrincipal = new JeuDuPendu() ; 
			ecranPrincipal.setVisible(true) ;
			splash.setVisible(false) ;
		}
	} 
	//
	class leaveGameListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0) ; 
		}
	} 
	//
	
	
	// méthode main 
	public static void main(String[] args) 
	{
		splash = new LePendu() ;  
		splash.setVisible(true) ; 
	}
	// 
}
//
 