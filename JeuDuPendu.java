package projectFour_JeuDePendu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
 

// class JeuDuPendu
public class JeuDuPendu extends JFrame
{
	// mise en forme : poilice, Panel, MenuBar
	Font fontMenuBar = new Font("Arial", Font.PLAIN, 12) ;
	Font myFont = new Font("Arial", Font.BOLD, 12) ; 
	Font myFont2 = new Font("Arial", Font.BOLD, 20) ;
	private static Color myBlue = new Color(153, 174, 202) ;
	private static Color myGray = new Color(42, 59, 74) ;
	private static Color myGray2 = new Color(77, 107, 133) ;
	private static Color myGreen = new Color(78, 231, 214) ;
	public static JPanel panGlobal ;  
	private static JPanel panWest1 ; 
	private static JPanel panWest3 ; 
	private static JPanel panWest4 ; 
	private static JPanel panWest5 ; 
	private static JPanel panEast ; 
	private static NouvellePartie panNouvellePartie ; 
	private static JMenuBar menuBar ;  
	private static JMenu fichier ; 
	private static JMenuItem fichier_nouveau ; 
	private static JMenuItem fichier_regles ;   
	private static JMenuItem fichier_score ; 
	private static JMenu aPropos ; 
	private static JMenuItem aPropos_aPropos ;  
	
	
	
	// compteurs 
	public static int ScoreDeLaSession = 0 ; 
	private static int NombreDePartie = 0 ; 
	private static int ScoreDeLaPartie ;  
	private static int NombreErreursParPartie ; 
	  
	 
	
	// dictionnaire et clavier
	private static Dictionnaire newMotPendu ;
	private static String motPendu ; 
	private static String motDictionnaire ; 
	private static ArrayList charMotPenduArray ; 
	private static String motTest ;  
	  
	
	
	// Action Listener globaux
	NouvellePartieListener nouvellePartieListen = new NouvellePartieListener() ; 
	TableauScoreListener tableauScoreListen = new TableauScoreListener() ; 
	RegleListener regleListen = new RegleListener() ; 
	aProposListener aProposListen = new aProposListener() ;
	leaveGameListener leaveListen = new leaveGameListener() ; 
	 
	
	
	// constructeur
	public JeuDuPendu()
	{
		// mise en place du JFrame 
		this.setTitle("Jeu du Pendu") ; 
		this.setSize(840, 600) ; 
		this.setResizable(false) ; 
		this.setLocationRelativeTo(null) ;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;  
		
		// JFrame suite   
		initMenuBar() ; 
		JPanel panRegles = PaintPanRegles() ; 
		panGlobal = new JPanel() ; 
		panGlobal.add(panRegles) ;  
		this.getContentPane().add(panGlobal) ; 
		this.setVisible(true) ; 
	}
	//
	
	
	
	// paintPanel...
	public JPanel PaintPanRegles()
	{
		// Panel North
		JPanel panNorth = new JPanel() ; 
		panNorth.setPreferredSize(new Dimension(800, 15)) ;
		
		// Panel CENTER
		JPanel panReglesImg = new JPanel() ;
		panReglesImg.setPreferredSize(new Dimension(800, 450)) ;
		ImageIcon imgPenduRegles = new ImageIcon(getClass().getResource("penduRegles.png")) ; 
		JLabel labelPenduRegles = new JLabel(imgPenduRegles) ; 
		panReglesImg.add(labelPenduRegles) ;  
		
		// Panel South
		Font fontButton = new Font("Courier", Font.PLAIN, 12) ;
		JPanel panSouth = new JPanel() ;  
		panSouth.setPreferredSize(new Dimension(800, 50)) ;
		JButton boutonNouvellePartie = new JButton("Nouvelle partie") ; 
		JButton boutonTableauScore = new JButton("Tableau des scores") ;
		boutonNouvellePartie.addActionListener(nouvellePartieListen) ;
		boutonTableauScore.addActionListener(tableauScoreListen) ;
		boutonNouvellePartie.setFont(fontButton) ;
		boutonTableauScore.setFont(fontButton) ;
		panSouth.add(boutonNouvellePartie) ;
		panSouth.add(boutonTableauScore) ;
		
		// panRegles
		JPanel panRegles = new JPanel(new BorderLayout()) ; 
		panRegles.add(panNorth, BorderLayout.NORTH) ; 
		panRegles.add(panReglesImg, BorderLayout.CENTER) ; 
		panRegles.add(panSouth, BorderLayout.SOUTH) ;
		
		//
		return panRegles ;
	}
	// 
	public void PaintPanNouvellePartie()
	{
		// MenuBar
		fichier_nouveau.setEnabled(false) ;
		fichier_regles.setEnabled(false) ;
		fichier_score.setEnabled(false) ;
		aPropos_aPropos.setEnabled(true) ;
		
		// génération d'un nouveau motPendu dans le Dictionnaire 
		newMotPendu = new Dictionnaire() ; 
		motPendu = newMotPendu.getMotPendu() ; 
		charMotPenduArray = newMotPendu.getCharMotPenduArray() ;   
		motTest = newMotPendu.getMotTest();
		motDictionnaire = newMotPendu.getMotDictionnaire() ; 
		
		// compteurs 
		ScoreDeLaPartie = 0 ;
		NombreErreursParPartie = 0 ;
		int nombrePartie = getNombreDePartie() ;  
		int scoreSession = getScoreSession() ;   
		
		// génération des Panel de la Nouvelle Partie
		panNouvellePartie = new NouvellePartie() ;   
		JPanel panNorth = panNouvellePartie.nouvellePartieNorth() ; 
		panWest1 = panNouvellePartie.nouvellePartieWest1(motTest) ;
		JPanel panWest2 = initClavier() ;  
		panWest3 = panNouvellePartie.nouvellePartieWest3(scoreSession) ;
		panWest4 = panNouvellePartie.nouvellePartieWest4(nombrePartie) ; 
		panWest5 = panNouvellePartie.nouvellePartieWest5(NombreErreursParPartie) ; 
		panEast = panNouvellePartie.nouvellePartieEast(NombreErreursParPartie) ;   
		JPanel panSouth = panNouvellePartie.nouvellePartieSouth() ; 
		
		
		// panGlobal -> faire un GridBagLayout
		panGlobal = new JPanel(new GridBagLayout()) ;  
		panGlobal.setBackground(Color.white) ;
		GridBagConstraints gc = new GridBagConstraints() ;  
		gc.fill = GridBagConstraints.BOTH ; 
		gc.anchor = GridBagConstraints.CENTER ; 
		
		gc.insets = new Insets(5, 5, 5, 5) ; 
		gc.gridx = 0 ; 
		gc.gridy = 0 ; 
		gc.weighty = 0.33 ; 
		gc.gridwidth = 8 ; 
		gc.gridheight = 4 ; 
		panGlobal.add(panNorth, gc) ; 
		
		gc.insets = new Insets(5, 5, 5, 5) ;
		gc.gridx = 0 ; 
		gc.gridy = 4 ; 
		gc.gridwidth = 4 ; 
		gc.gridheight = 1 ; 
		panGlobal.add(panWest1, gc) ; 
		
		gc.insets = new Insets(5, 5, 5, 5) ;
		gc.gridx = 0 ; 
		gc.gridy = 5 ; 
		gc.gridwidth = 4 ; 
		gc.gridheight = 4 ; 
		panGlobal.add(panWest2, gc) ;
		
		gc.insets = new Insets(5, 5, 0, 5) ;
		gc.gridx = 0 ; 
		gc.gridy = 9 ; 
		gc.gridwidth = 4 ; 
		gc.gridheight = 1 ; 
		panGlobal.add(panWest3, gc) ;
		
		gc.insets = new Insets(0, 5, 0, 5) ;
		gc.gridx = 0 ; 
		gc.gridy = 10 ; 
		gc.gridwidth = 4 ; 
		gc.gridheight = 1 ; 
		panGlobal.add(panWest4, gc) ;
		
		gc.insets = new Insets(0, 5, 5, 5) ;
		gc.gridx = 0 ; 
		gc.gridy = 11 ; 
		gc.gridwidth = 4 ; 
		gc.gridheight = 1 ; 
		panGlobal.add(panWest5, gc) ;
		
		gc.insets = new Insets(5, 5, 5, 5) ;
		gc.gridx = 4 ; 
		gc.gridy = 4 ; 
		gc.weighty = 0.33 ; 
		gc.gridwidth = 4 ; 
		gc.gridheight = 8 ; 
		panGlobal.add(panEast, gc) ;
		
		gc.insets = new Insets(5, 5, 5, 5) ;
		gc.gridx = 0 ; 
		gc.gridy = 12 ; 
		gc.weighty = 0.33 ; 
		gc.gridwidth = 8 ; 
		gc.gridheight = 4 ; 
		panGlobal.add(panSouth, gc) ; 
		  
		
		getContentPane().add(panGlobal) ;   
		setVisible(true) ; 
	}
	//  
	public JPanel PaintPanScore () 
	{ 
		JPanel panScore = new JPanel(new GridBagLayout()) ; 
		GridBagConstraints gc = new GridBagConstraints() ; 
		gc.fill = GridBagConstraints.BOTH ; 
		gc.anchor = GridBagConstraints.CENTER ; 
		gc.insets = new Insets(10, 10, 10, 10) ; 
		
		
		// Pan North
		JPanel panNorth = new JPanel(new GridBagLayout()) ;
		GridBagConstraints gcc = new GridBagConstraints() ;
		gcc.anchor = GridBagConstraints.CENTER ;
		gcc.gridx = 0 ; 
		gcc.gridy = 0 ; 
		JLabel top = new JLabel("Top 10") ; 
		top.setFont(myFont2) ;
		top.setForeground(Color.white) ; 
		panNorth.setPreferredSize(new Dimension(800, 75)) ;
		panNorth.setBackground(myBlue) ; 
		panNorth.add(top, gcc) ; 
		gc.gridx = 0 ; 
		gc.gridy = 0 ; 
		panScore.add(panNorth, gc) ; 
		//
		
		
		// Pan Center : Tableau des Scores
		JPanel panCenter = paintTabScore() ; 
		gc.gridx = 0 ; 
		gc.gridy = 1 ; 
		panScore.add(panCenter, gc) ; 
		
		 
		// Pan South
		JPanel panSouth = new JPanel() ; 
		panSouth.setPreferredSize(new Dimension(800, 75)) ;
		panSouth.setBackground(myBlue) ;  
		gc.gridx = 0 ; 
		gc.gridy = 2 ;
		panScore.add(panSouth, gc) ; 
		// 
		
		return panScore ; 
		
	}
	//
	public JPanel paintTabScore () 
	{
		//
		TableauScore tabScore = new TableauScore() ; 
		Font font1 = new Font("Arial", Font.BOLD, 14) ;
		Font font2 = new Font("Arial", Font.PLAIN, 12) ;
		
		
		// préparation du Grid Bag Layout
		JPanel panTabScore = new JPanel(new GridBagLayout()) ; 
		panTabScore.setPreferredSize(new Dimension(800, 320)) ; 
		GridBagConstraints gc = new GridBagConstraints() ;
		gc.fill = GridBagConstraints.BOTH ; 
		gc.anchor = GridBagConstraints.CENTER ; 
		gc.insets = new Insets(5, 5, 2, 5) ; 
		//
		
		
		// ligne 0 (position, pseudo, score)
		JPanel zeroZero = new JPanel() ; 
		zeroZero.setBackground(myGray) ;
		JLabel position = new JLabel("Position") ; 
		position.setFont(font1) ;
		position.setAlignmentX(JLabel.CENTER_ALIGNMENT) ;  
		position.setAlignmentY(JLabel.CENTER_ALIGNMENT) ; 
		position.setForeground(myGreen) ;
		zeroZero.add(position) ; 
		gc.gridx = 0 ; 
		gc.gridy = 0 ;
		gc.weightx = 0.2 ; 
		gc.weighty = 0.1 ; 
		panTabScore.add(zeroZero, gc) ; 
		
		JPanel unZero = new JPanel() ; 
		unZero.setBackground(myGray) ;
		JLabel pseudo = new JLabel("Pseudo") ; 
		pseudo.setFont(font1) ;
		pseudo.setAlignmentX(JLabel.CENTER_ALIGNMENT) ;  
		pseudo.setAlignmentY(JLabel.CENTER_ALIGNMENT) ; 
		pseudo.setForeground(myGreen) ;
		unZero.add(pseudo) ; 
		gc.gridx = 1 ; 
		gc.gridy = 0 ; 
		gc.weightx = 0.40 ; 
		panTabScore.add(unZero, gc) ; 
		
		JPanel deuxZero = new JPanel() ; 
		deuxZero.setBackground(myGray) ;
		JLabel score = new JLabel("Score") ; 
		score.setFont(font1) ;
		score.setAlignmentX(JLabel.CENTER_ALIGNMENT) ;  
		score.setAlignmentY(JLabel.CENTER_ALIGNMENT) ; 
		score.setForeground(myGreen) ;
		deuxZero.add(score) ; 
		gc.gridx = 2 ; 
		gc.gridy = 0 ; 
		gc.weightx = 0.40 ; 
		panTabScore.add(deuxZero, gc) ; 
		
		
		// 1e colonne (ligne 2 à 11) : position
		for (int i = 1 ; i < 11 ; i++)
		{
			JPanel p = new JPanel() ; 
			p.setBackground(myGray) ; 
			String str = Integer.toString(i) ; 
			JLabel lab = new JLabel(str) ;
			lab.setFont(font1) ;
			lab.setAlignmentX(JLabel.CENTER_ALIGNMENT) ;  
			lab.setAlignmentY(JLabel.CENTER_ALIGNMENT) ;
			lab.setForeground(myGreen) ;
			p.add(lab) ;
			gc.gridx = 0 ; 
			gc.gridy = i ; 
			gc.weightx = 0.2 ; 
			gc.weighty = 0.09 ; 
			panTabScore.add(p, gc) ;
		} 	
		
		
		// 2e colonne (ligne 2 à 11) : pseudo
		for (int i = 1 ; i < 11 ; i++)
		{
			JPanel p = new JPanel() ; 
			p.setBackground(myGreen) ; 
			String str = tabScore.readPseudoTop10(i) ;  
			JLabel lab = new JLabel(str) ;
			lab.setFont(font2) ;
			lab.setAlignmentX(JLabel.CENTER_ALIGNMENT) ;  
			lab.setAlignmentY(JLabel.CENTER_ALIGNMENT) ;
			lab.setForeground(myGray) ;
			p.add(lab) ;
			gc.gridx = 1 ; 
			gc.gridy = i ; 
			gc.weightx = 0.2 ; 
			gc.weighty = 0.09 ; 
			panTabScore.add(p, gc) ;
		} 
		
		
		// 3e colonne (ligne 2 à 11) : score
		for (int i = 1 ; i < 11 ; i++)
		{
			JPanel p = new JPanel() ; 
			p.setBackground(myGreen) ; 
			String str = tabScore.readScoreTop10(i) ; 
			JLabel lab = new JLabel(str) ;
			lab.setFont(font2) ;
			lab.setAlignmentX(JLabel.CENTER_ALIGNMENT) ;  
			lab.setAlignmentY(JLabel.CENTER_ALIGNMENT) ;
			lab.setForeground(myGray) ;
			p.add(lab) ;
			gc.gridx = 2 ; 
			gc.gridy = i ; 
			gc.weightx = 0.2 ; 
			gc.weighty = 0.09 ; 
			panTabScore.add(p, gc) ;
		}
		
		// 
		return panTabScore ; 
	}
	//
	
	
	
	// Clavier 
	//
	
	
	// Clavier et Action Listener 
	public JPanel initClavier()   
	{
		clavierListener clavierListen = new clavierListener() ;  
		//
		
		
		// A à E
		JButton buttonA = new JButton("A") ; 
		JButton buttonB = new JButton("B") ;
		JButton buttonC = new JButton("C") ;
		JButton buttonD = new JButton("D") ;
		JButton buttonE = new JButton("E") ; 
		buttonA.addActionListener(clavierListen) ;
		buttonB.addActionListener(clavierListen) ;
		buttonC.addActionListener(clavierListen) ;
		buttonD.addActionListener(clavierListen) ;
		buttonE.addActionListener(clavierListen) ; 
		Box b20 = Box.createHorizontalBox() ;
		b20.add(buttonA) ;
		b20.add(buttonB) ;
		b20.add(buttonC) ;
		b20.add(buttonD) ;
		b20.add(buttonE) ; 
		//
		
		
		// F à J 
		JButton buttonF = new JButton("F") ;
		JButton buttonG = new JButton("G") ; 
		JButton buttonH = new JButton("H") ; 
		JButton buttonI = new JButton("I") ;
		JButton buttonJ = new JButton("J") ;
		buttonF.addActionListener(clavierListen) ;
		buttonG.addActionListener(clavierListen) ;
		buttonH.addActionListener(clavierListen) ;
		buttonI.addActionListener(clavierListen) ;
		buttonJ.addActionListener(clavierListen) ;
		Box b30 = Box.createHorizontalBox() ;
		b30.add(buttonF) ;
		b30.add(buttonG) ;
		b30.add(buttonH) ;
		b30.add(buttonI) ;
		b30.add(buttonJ) ;
		// 

		
		// K à O
		JButton buttonK = new JButton("K") ;
		JButton buttonL = new JButton("L") ;
		JButton buttonM = new JButton("M") ;
		JButton buttonN = new JButton("N") ; 
		JButton buttonO = new JButton("O") ; 
		buttonK.addActionListener(clavierListen) ;
		buttonL.addActionListener(clavierListen) ;
		buttonM.addActionListener(clavierListen) ;
		buttonN.addActionListener(clavierListen) ;
		buttonO.addActionListener(clavierListen) ;
		Box b40 = Box.createHorizontalBox() ; 
		b40.add(buttonK) ;
		b40.add(buttonL) ;
		b40.add(buttonM) ;
		b40.add(buttonN) ;
		b40.add(buttonO) ;
		//
		 
		
		// P à T 
		JButton buttonP = new JButton("P") ;
		JButton buttonQ = new JButton("Q") ;
		JButton buttonR = new JButton("R") ;
		JButton buttonS = new JButton("S") ;
		JButton buttonT = new JButton("T") ; 
		buttonP.addActionListener(clavierListen) ;
		buttonQ.addActionListener(clavierListen) ;
		buttonR.addActionListener(clavierListen) ;
		buttonS.addActionListener(clavierListen) ;
		buttonT.addActionListener(clavierListen) ;
		Box b50 = Box.createHorizontalBox() ; 
		b50.add(buttonP) ;
		b50.add(buttonQ) ;
		b50.add(buttonR) ;
		b50.add(buttonS) ;
		b50.add(buttonT) ;
		//
		
		
		// U à W 
		JButton buttonU = new JButton("U") ;
		JButton buttonV = new JButton("V") ; 
		JButton buttonW = new JButton("W") ; 
		buttonU.addActionListener(clavierListen) ;
		buttonV.addActionListener(clavierListen) ;
		buttonW.addActionListener(clavierListen) ;   
		Box b60 = Box.createHorizontalBox() ; 
		b60.add(buttonU) ;
		b60.add(buttonV) ;
		b60.add(buttonW) ;  
		//
		 
		
		// X à Z 
		JButton buttonX = new JButton("X") ;
		JButton buttonY = new JButton("Y") ;
		JButton buttonZ = new JButton("Z") ;   
		buttonX.addActionListener(clavierListen) ;
		buttonY.addActionListener(clavierListen) ;
		buttonZ.addActionListener(clavierListen) ;  
		Box b70 = Box.createHorizontalBox() ;  
		b70.add(buttonX) ;
		b70.add(buttonY) ;
		b70.add(buttonZ) ;
		//
		
		
		// Consolidation
		Box b80 = Box.createVerticalBox() ;  
		b80.add(b20) ; 
		b80.add(b30) ; 
		b80.add(b40) ; 
		b80.add(b50) ;
		b80.add(b60) ;
		b80.add(b70) ; 
		JPanel clavierPanel = new JPanel() ; 
		clavierPanel.add(b80) ; 
		//
		 
		
		//
		return clavierPanel ; 
		//
	} 
	//
	// 
	class clavierListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			((JButton) e.getSource()).setEnabled(false) ; 
			 
			// on récupère la lettre du bouton 
			JButton copieClavier = (JButton) e.getSource(); 
			String texte = copieClavier.getText().toLowerCase() ;  
			boolean isChar = false ; 
			
			
			// on compare le caractère du bouton cliqué à ceux de l'Array  
			for (int i = 0 ; i < motPendu.length() ; i++ )
			{  	 
				if (charMotPenduArray.get(i).toString().equals(texte)) 
				{  
					// on modifie le motTest
					newMotPendu.updateMotTest(i) ; 
					motTest = newMotPendu.getMotTest() ;   
					
					//
					isChar = true ;  
				} 
			} 
			 
			// si on a trouvé un caractère : JLabel(motTest) à modifier
			// si on a fait une erreur : compteur, image
			if (isChar == true)
			{   
				panGlobal.remove(panWest1) ;
				panWest1 = panNouvellePartie.nouvellePartieWest1(motTest) ; 
				GridBagConstraints gc = new GridBagConstraints() ; 
				gc.insets = new Insets(5, 5, 5, 5) ;
				gc.gridx = 0 ; 
				gc.gridy = 4 ; 
				gc.gridwidth = 4 ; 
				gc.gridheight = 1 ; 
				panGlobal.add(panWest1, gc) ;
				panGlobal.repaint() ; 
				getContentPane().add(panGlobal) ; 
				setVisible(true) ; 
				
				if (motTest.equals(motDictionnaire)) 
				{   
					JOptionPane jopGagne = new JOptionPane() ; 
					String message = "Bravo ! Vous avez trouvé le mot caché ! Continuons !" ; 
					jopGagne.showMessageDialog(null, message) ;
					calculScorePartie(NombreErreursParPartie) ; 
					calculScoreSession(ScoreDeLaPartie) ; 
					setNombreDePartie();   
					panGlobal.removeAll() ;  
					PaintPanNouvellePartie() ; 
					panGlobal.repaint() ;  
				}  
			} 
			else if (isChar == false)
			{
				NombreErreursParPartie ++ ; 
				
				panGlobal.remove(panEast) ; 
				panEast = panNouvellePartie.nouvellePartieEast(NombreErreursParPartie) ; 
				GridBagConstraints gc = new GridBagConstraints() ;  
				gc.fill = GridBagConstraints.BOTH ; 
				gc.anchor = GridBagConstraints.CENTER ; 
				gc.insets = new Insets(5, 5, 5, 5) ;
				gc.gridx = 4 ; 
				gc.gridy = 4 ; 
				gc.weighty = 0.33 ; 
				gc.gridwidth = 4 ; 
				gc.gridheight = 8 ; 
				panGlobal.add(panEast, gc) ;
				
				panGlobal.remove(panWest5) ; 
				panWest5 = panNouvellePartie.nouvellePartieWest5(NombreErreursParPartie) ;
				GridBagConstraints gcc = new GridBagConstraints() ;  
				gcc.fill = GridBagConstraints.BOTH ; 
				gcc.anchor = GridBagConstraints.CENTER ; 
				gcc.insets = new Insets(0, 5, 5, 5) ;
				gcc.gridx = 0 ; 
				gcc.gridy = 11 ; 
				gcc.gridwidth = 4 ; 
				gcc.gridheight = 1 ; 
				panGlobal.add(panWest5, gcc) ;
				panGlobal.repaint() ; 
				getContentPane().add(panGlobal) ; 
				setVisible(true) ;  
				
				if (NombreErreursParPartie == 6)
				{  
					JOptionPane jopUneErreurRestante = new JOptionPane() ; 
					String message = "Attention, plus qu'une erreur possible ! " ; 
					jopUneErreurRestante.showMessageDialog(null, message) ; 
				} 
				else if (NombreErreursParPartie == 7)
				{ 
					JOptionPane jopPerdu = new JOptionPane() ; 
					String message = "Game Over." ; 
					jopPerdu.showMessageDialog(null, message) ;  
					
					calculScorePartie(NombreErreursParPartie) ; 
					calculScoreSession(ScoreDeLaPartie) ;
					
					if (isScoreTop10(ScoreDeLaSession) == true)
					{
						panGlobal.removeAll() ; 
						JPanel panScore = PaintPanScore() ; 
						panGlobal.add(panScore) ;
						getContentPane().add(panGlobal) ; 
						setVisible(true) ; 
						panGlobal.repaint() ;  
						
						JOptionPane jopPseudo = new JOptionPane() ; 
						String pseudo = jopPseudo.showInputDialog(null, 
								"Vous intégrez le Top 10 ! Bravo ! Quel est votre Pseudo ?", 
								"Entrée au Top 10", JOptionPane.INFORMATION_MESSAGE) ; 
						
						int newIndex = IndexTop10(ScoreDeLaSession) ; // IndexTop10 = 0 à 9 
						updateTop10(newIndex + 1, ScoreDeLaSession, pseudo) ;   
						
						panGlobal.removeAll() ; 
						panScore = PaintPanScore() ; 
						panGlobal.add(panScore) ;
						getContentPane().add(panGlobal) ; 
						setVisible(true) ; 
						panGlobal.repaint() ; 
					}
					else if (isScoreTop10(ScoreDeLaSession) == false)
					{
						finDePartiePerdue() ;   
					} 
				} 
			} 
		}
	}
	// 
		 
	
	// MenuBar et ActionListener associés : NouvellePartie, Score, Regles, APropos 
	public void initMenuBar()
	{
		// éléments JMenuBar
		menuBar = new JMenuBar() ; 
		fichier = new JMenu("Fichier") ;
		fichier_nouveau = new JMenuItem("Nouvelle partie") ;
		fichier_regles = new JMenuItem("Règles du jeu") ;  
		fichier_score = new JMenuItem("Score") ;  
		JMenuItem fichier_quitter = new JMenuItem("Quitter") ; 
		aPropos = new JMenu("A propos") ;
		aPropos_aPropos = new JMenuItem("A propos...") ;
		
		// Font JMenuBar
		fichier.setFont(fontMenuBar) ; 
		fichier_nouveau.setFont(fontMenuBar) ; 
		fichier_regles.setFont(fontMenuBar) ;
		fichier_score.setFont(fontMenuBar) ;
		fichier_quitter.setFont(fontMenuBar) ;
 		aPropos.setFont(fontMenuBar) ; 
		aPropos_aPropos.setFont(fontMenuBar) ;
		
		// ActionListener
		fichier_nouveau.addActionListener(nouvellePartieListen) ;
		fichier_score.addActionListener(tableauScoreListen) ;
		fichier_regles.addActionListener(regleListen) ; 
		fichier_quitter.addActionListener(leaveListen) ; 
		aPropos_aPropos.addActionListener(aProposListen) ;
		
		// Menu > Fichier & Menu > A Propos
		fichier.add(fichier_nouveau) ; 
		fichier.add(fichier_score) ; 
		fichier_regles.setEnabled(false) ;
		fichier.add(fichier_regles) ;  
		fichier.add(fichier_quitter) ; 
		aPropos.add(aPropos_aPropos) ;  
		
		// JMenuBar mise en place 
		menuBar.add(fichier) ; 
		menuBar.add(aPropos) ; 
		this.setJMenuBar(menuBar) ;
	}
	//
	//
	class NouvellePartieListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{ 
			setNombreDePartie() ;  
			panGlobal.removeAll() ;  
			PaintPanNouvellePartie() ; 
			panGlobal.repaint() ;  
		}
	}
	//
	class TableauScoreListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			fichier_nouveau.setEnabled(true) ;
			fichier_regles.setEnabled(true) ;
			fichier_score.setEnabled(false) ;
			aPropos_aPropos.setEnabled(true) ;
			
			panGlobal.removeAll() ; 
			JPanel panScore = PaintPanScore() ; 
			panGlobal.add(panScore) ;
			getContentPane().add(panGlobal) ; 
			setVisible(true) ; 
			panGlobal.repaint() ;  
		}
	} 
	//
	//
	class RegleListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			NombreDePartie = 0 ; 
			ScoreDeLaSession = 0 ; 
			panGlobal.removeAll() ; 
			panGlobal = new JPanel() ;
			JPanel panRegles = PaintPanRegles() ;
			panGlobal.add(panRegles) ; 
			initMenuBar() ; 
			getContentPane().add(panGlobal) ; 
			setVisible(true) ; 
			panGlobal.repaint() ;  
		}
	}
	// 
	//
	class aProposListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{ 
			JOptionPane jop = new JOptionPane() ; 
			String message = "\n"+
			"Le Pendu est un jeu consistant à trouver un mot en devinant quelles sont les lettres qui le composent.\n" +
			"Le jeu se joue traditionnellement à deux, avec un papier et un crayon, selon un déroulement bien particulier.   \n" + 
			"\n" +  
			"Il est aussi fréquemment pratiqué dans les salles de classe :\n" + 
			"   - du primaire à la seconde\n" +
			"   - à la fin de l'année scolaire (mi-juin);\n" +
			"   - auquel cas un élève au tableau joue contre l'ensemble de ses camarades, en dessinant à la craie.\n" +
			"\n" + 
			"A noter toutefois que les étudiants en Droit dans le supérieur le pratiquent occasionnellement pour :\n" + 
			"   - jouer à se faire deviner des noms d'arrêts importants de la jurisprudence,\n" + 
			"   - réviser d'une manière ludique tout en corsant largement la difficulté du jeu.\n" + 
			"\n" + 
			"Le dessin une fois terminé montre un bonhomme allumette pendu.\n" + 
			"\n" + 
			"Source : Wikipedia." ; 
			ImageIcon imgAPropos = new ImageIcon(getClass().getResource("imgAPropos.png")) ;    
			jop.showMessageDialog(null, message, "A propos...", JOptionPane.INFORMATION_MESSAGE, imgAPropos) ;
		}
	}
	//
	
	
	
	// fin de Partie, compteurs 
	public int getNombreDePartie ()
	{
		return NombreDePartie ;  
	}
	//
	public void setNombreDePartie () 
	{ 
		NombreDePartie ++ ; 
	}
	//
	public int getScoreSession () 
	{
		return ScoreDeLaSession ; 
	}
	//
	// 
	public void calculScorePartie (int NombreErreursParPartie) 
	{
		switch (NombreErreursParPartie)
		{
			case 0:
				ScoreDeLaPartie = 100 ;
				break ; 
				
			case 1:
				ScoreDeLaPartie = 50 ;
				break ;
				
			case 2 : 
				ScoreDeLaPartie = 35 ;
				break ; 
				
			case 3:
				ScoreDeLaPartie = 25 ; 
				break ; 
				
			case 4:
				ScoreDeLaPartie = 15 ; 
				break ; 
				
			case 5:
				ScoreDeLaPartie = 10 ;
				break ; 
				
			case 6:
				ScoreDeLaPartie = 5 ; 
				break ; 
		} 
	}
	//
	public void calculScoreSession (int ScoreDeLaPartie) 
	{
		ScoreDeLaSession += ScoreDeLaPartie ; 
	}
	//  
	
	
	// partie gagnée, top 10
	public JPanel paintPanWinGameNotTop10 () 
	{
		JPanel panWinGame = new JPanel() ; 
		return panWinGame ; 
	}
	//
	public JPanel paintPanWinGameTop10 () 
	{
		JPanel panWinGame = new JPanel() ; 
		return panWinGame ; 
	}
	//
	public boolean isScoreTop10 (int ScoreDeLaSession) 
	{  
		TableauScore tablScore = new TableauScore() ; 
		String minScore = tablScore.readScoreTop10(10) ; 
		int minimumScore = Integer.parseInt(minScore) ; 
		
		if (ScoreDeLaSession >= minimumScore)
		{
			return true ; 
		}
		else
		{
			return false ; 
		}
	}
	//
	public int IndexTop10 (int ScoreDeLaSession)  
	{  
		int indexNewTop10 = 9 ;  
		ArrayList<String> scoresActuels = new ArrayList<String>() ;   
		TableauScore tableScore = new TableauScore() ; 
		
		for (int i = 0 ; i < 11 ; i++)
		{
			if (i == 0)
			{
				//
			}
			else
			{
				String scoreOriginal = tableScore.readScoreTop10(i) ;
				scoresActuels.add(scoreOriginal) ;   
			} 
		}  
		
		for (int i = 0 ; i < 10 ; i++)
		{
			int scoreIndex = Integer.parseInt(scoresActuels.get((9-i))) ;  
			if (ScoreDeLaSession >= scoreIndex)
			{
				indexNewTop10 = 9 - i ; 
			}  
		}
		
		return indexNewTop10 ;   
	}
	//
	public void updateTop10 (int index, int ScoreDeLaSession, String pseudo) 
	{
		TableauScore tab = new TableauScore() ; 
		
		tab.writeScoreTop10(index, ScoreDeLaSession) ;  
		tab.writePseudoTop10(index, pseudo) ; 
	}
	//
	
	
	// Fin de Partie : Perdue 
	public void finDePartiePerdue () 
	{ 
		panGlobal.removeAll() ; 
		panGlobal = new JPanel() ; 
		JPanel panEndGame = paintPanEndGame() ; 
		panGlobal.add(panEndGame) ; 
		getContentPane().add(panGlobal) ; 
		setVisible(true) ;  
		panGlobal.repaint() ;   
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
	public JPanel paintPanEndGame ()
	{
		fichier_regles.setEnabled(true) ;
		JPanel panNorth = new JPanel() ; 
		panNorth.setPreferredSize(new Dimension(800, 15)) ;
		
		// Panel CENTER
		JPanel panEndImg = new JPanel() ;
		panEndImg.setPreferredSize(new Dimension(800, 450)) ;
		ImageIcon imgEndGame = new ImageIcon(getClass().getResource("endGame.png")) ; 
		JLabel labelEndGame = new JLabel(imgEndGame) ; 
		panEndImg.add(labelEndGame) ;  
		
		// Panel South
		Font fontButton = new Font("Courier", Font.PLAIN, 12) ;
		JPanel panSouth = new JPanel() ;  
		panSouth.setPreferredSize(new Dimension(800, 50)) ;
		JButton retourAccueil = new JButton("Retour à l'accueil") ; 
		retourAccueil.addActionListener(regleListen) ;
		JButton quitterJeu = new JButton("Quitter le jeu") ; 
		quitterJeu.addActionListener(leaveListen) ;   
		retourAccueil.setFont(fontButton) ;
		quitterJeu.setFont(fontButton) ;
		panSouth.add(retourAccueil) ;
		panSouth.add(quitterJeu) ;
		
		// panRegles
		JPanel panEndGame = new JPanel(new BorderLayout()) ; 
		panEndGame.add(panNorth, BorderLayout.NORTH) ; 
		panEndGame.add(panEndImg, BorderLayout.CENTER) ; 
		panEndGame.add(panSouth, BorderLayout.SOUTH) ;
		//
		
		return panEndGame ;  
	}
	// 
}
//
