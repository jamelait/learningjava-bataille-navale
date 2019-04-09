import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class BatailleNavaleGraphique extends BatailleNavale {

	// constantes
	final static int TAILLE = 7; // la taille du plateau
	final static int TAILLEIMAGE = 50;
	final static String EXT = ".gif";
	final static String REP = "";
	final static Color couleurEau = new Color(28,164,243);
	
	// attribut de l'interface graphique
	private JFrame fenetre = new JFrame("Bataille Navale");
	private JPanel panelPrincipal = new JPanel(new BorderLayout());
	private JPanel panelPlateau = new JPanel(new GridLayout(TAILLE,TAILLE));
	private JEditorPane panelInfo = new JEditorPane();
	public JLabel dernierCoup = new JLabel("Cliquez sur une case pour tenter de couler le bateau adverse !");
	private DialogBatailleNavale dialog = new DialogBatailleNavale();
	private BoutonCacher boutonCacher = new BoutonCacher(panelPlateau); // masquer le panelPlateau
	private int[][] positionBouton; // index des boutons dans le panelPlateau

	// 
	int nbBateauxA = TAILLE;
	int nbBateauxB = TAILLE;
	//public int nbJoueurs; // ne prend pas en charge le nombre de joueurs pour l'instant
	public char joueurActif = 'a'; // 
	
	public BatailleNavaleGraphique() {
		super.creerPlateau(TAILLE);
		super.placerBateauxAleatoirement('a');
		super.placerBateauxAleatoirement('b');
		
		// construction du panelPlateau et masquage de celui-ci
		construirePanelPlateau();
		actualiserPlateau();
		boutonCacher.doClick();
		
		// configuration du panelPlateau
		JPanel pp = new JPanel();
		pp.setLayout(null);
		pp.setBackground(couleurEau);
		panelPlateau.setBounds(0,0,TAILLE*TAILLEIMAGE,TAILLE*TAILLEIMAGE);
		pp.add(panelPlateau);
		
		// configuration du panel info et actualisation avec le nombre de bateaux des joueurs
		panelInfo.setSize(142,400);
		panelInfo.setEditable(false);
		actualiserInfo(TAILLE,TAILLE);
		
		// rassemblement dans le panelPrincipal
		panelPrincipal.add(pp, BorderLayout.CENTER);
		panelPrincipal.add(panelInfo,BorderLayout.EAST);
		panelPrincipal.add(dernierCoup,BorderLayout.NORTH);
		panelPrincipal.add(boutonCacher,BorderLayout.SOUTH);
		
		// configuration de la fenetre
		fenetre.setContentPane(panelPrincipal);
		fenetre.setMinimumSize(new Dimension(500,458));
		fenetre.setLocationRelativeTo(null);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setVisible(true);
	}
	
	public void reset() {
		joueurActif = 'a';
		nbBateauxA = TAILLE;
		nbBateauxB = TAILLE;
		super.creerPlateau(TAILLE);
		super.placerBateauxAleatoirement('a');
		super.placerBateauxAleatoirement('b');
		actualiserPlateau();
		actualiserInfo(nbBateauxA,nbBateauxB);
	}
	
	public void construirePanelPlateau() {
		JButton bouton;
		Dimension dim = new Dimension(15,15);
		int i = 0;
		
		positionBouton = new int[TAILLE][TAILLE];
		for (int l = 0 ; l < TAILLE ; l++) {
			for (int c = 0 ; c < TAILLE ; c++) {
				positionBouton[l][c] = i;
				i++;
				bouton = new JButton();
				bouton.setPreferredSize(dim);
				bouton.setBorderPainted(false);
				bouton.setIcon(new ImageIcon(REP + plateau[l][c] + EXT));
				panelPlateau.add(bouton);
				bouton.addMouseListener(new CaseListener(this,l,c));
			}
		}
	}
	
	
	public void actualiserPlateau() {
	// actualise le plateau pour le joueur actif, afin qu'il ne voit que ses bateaux
		JButton bouton;
		for (int l = 0 ; l < TAILLE ; l++) {
			for (int c = 0 ; c < TAILLE ; c++) {
				bouton = (JButton) panelPlateau.getComponent(positionBouton[l][c]);
				if (plateau[l][c] == joueurActif || plateau[l][c] == '2')
					bouton.setIcon(new ImageIcon(REP + joueurActif + EXT));
				else
					bouton.setIcon(new ImageIcon(REP + 'v' + EXT));
			}
		}
	}

	public void actualiserInfo(int nbBateauActif,int nbBateauAutre) {
		panelInfo.setText("JOUEUR " + joueurActif);
		panelInfo.setText(panelInfo.getText() + "\n Il vous reste " + nbBateauAutre + " bateaux.");
		panelInfo.setText(panelInfo.getText() + "\n Encore " + nbBateauActif + " bateaux a exploser !");
	}
	
	public char[][] getPlateau() {
		return plateau;
	}
	
	public void setCasePlateau(int l, int c, char j) {
		plateau[l][c] = j;
	}
	
	public void joueurSuivant() {
		boutonCacher.doClick(); // on masque le panelPlateau
		
		if (joueurActif == 'a') {
			joueurActif = 'b';
			actualiserInfo(nbBateauxB, nbBateauxA);
		}
		else {
			joueurActif = 'a';
			actualiserInfo(nbBateauxA, nbBateauxB);
		}
		if (nbBateauAutre == 0)
			terminerPartie();
		
		System.out.println("----------------------------------------------------");
		actualiserPlateau();
		afficherPlateau();
	}
	
	public void faireCouler(char j,int l,int c) {
		JButton bouton = (JButton) panelPlateau.getComponent(positionBouton[l][c]);
		char joueurRestant;

		if (j == 'a') {
			joueurRestant = 'b';
			nbBateauxB--;
		}
		else {
			joueurRestant = 'a';
			nbBateauxA--;
		}

		if (plateau[l][c] == '2') {
			plateau[l][c] = joueurRestant;
		}
		else {
			plateau[l][c] = 'v';
		}
		
		dernierCoup.setText("Coule !");
		bouton.setIcon(new ImageIcon(REP + plateau[l][c] + EXT));
	}

	public JButton getBouton(int l, int c) {
		return (JButton) panelPlateau.getComponent(positionBouton[l][c]);
	}

	public void terminerPartie() {
		dialog.partieTerminee();
		if (dialog.question("Voulez-vous rejouer ?","rejouer"))
			reset();
		else
			System.exit(0);
	}
	
	public static void main(String[] args) {
		BatailleNavaleGraphique jeu = new BatailleNavaleGraphique();
	}
	
}