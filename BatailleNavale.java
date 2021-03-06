class BatailleNavale {
// bataille navale pour deux joueurs

	final static char VIDE = 'v';
	final static char JOUEURA = 'a' ;
	final static char JOUEURB = 'b' ;
	final static char LES2JOUEURS = '2' ;
	
	char [][] plateau ;
	boolean gagne = false ;
	int choix;
	char tour = 'a' ; // c'est le joueur a qui commence a jouer

	public BatailleNavale() {
	}
	
	public BatailleNavale(int taille) {
		creerPlateau(taille);
		initJoueurs();
	}
	
	public void creerPlateau(int taille) {
		plateau = new char[taille][taille];
		int l, c;
		
		// on remplit toute les cases du tablau avec le caractere VIDE
		for (l = 0 ; l < plateau.length ; l++) {
			for (c=0 ; c < plateau[l].length ; c++) {
				plateau[l][c] = VIDE;
			}
		}
	}
	
	public void initJoueurs() {
	// le joueur A doit placer ses bateaux
		System.out.println("Joueur A : Comment placer vos bateaux ? Tapez :");
		System.out.println(" 1 pour Manuellement \n 2 pour Aleatoirement");
		// on ne peut choisir que 1 ou 2
		do {
			choix = Console.readInt("Votre choix : ");
		} while (choix != 1 && choix != 2 );
		// placement des bateaux selon le choix du joueur
		if (choix == 1) { // manuellement
			placerBateaux('a');
		}
		else { // aleatoirement
			placerBateauxAleatoirement('a');
		}
		// le joueur B doit placer ses bateaux
		System.out.println("Joueur B : Comment placer vos bateaux ? Tapez :");
		System.out.println(" 1 pour Manuellement \n 2 pour Aleatoirement");
		// on ne peut choisir que 1 ou 2
		do {
			choix = Console.readInt("Votre choix : ");
		} while (choix != 1 && choix != 2 );				
		
		// placement des bateaux selon le choix du joueur
		if (choix == 1) { // manuellement
			placerBateaux('b');
		}
		else { // aleatoirement
			placerBateauxAleatoirement('b');
		}
	}
	
	public void placerBateauxAleatoirement(char joueur) {
	// place aleatoirement n bateaux pour le joueue indique, n est la taille du plateau de jeu.

		int c, l ; // indices generes aleatoirement
		int n = 1 ; // nombre de bateau a placer
		
		while (n <= plateau.length) {
// genere un indice aleatoire entre 0 et longueur de plateau - 1
			c = (int)(Math.random() * plateau.length);
			l = (int)(Math.random() * plateau[c].length);
// cas du joueur a
			if (joueur == 'a') {
				switch (plateau[c][l]) {
// si un bateau du joueur b et deja present sur la case
					case 'b' :
						plateau[c][l] = '2';
						n++;
						break;
// si la case est vide
					case 'v' :
						plateau[c][l] = 'a';
						n++;
						break;
				}
			}
// cas du joueur b
			else {
				switch (plateau[c][l]) {
// si un bateau du joueur a et deja present sur la case
					case 'a' :
						plateau[c][l] = '2';
						n++;
						break;
// si la case est vide
					case 'v' :
						plateau[c][l] = 'b';
						n++;
						break;
				}
			}
		}
	}
	
	public void placerBateaux(char joueur) {
	// permet au joueur de placer lui-meme ses bateaux
	
		int c = -1, l= -1 ; // indices saisis par le joueur, on initialise a -1 au cas ou le joueur saisi une valeur incorrecte
		int n = 1 ; // nombre de bateau a placer
		String coor ; // coordonnees
		
		switch (joueur){
			case 'a' : System.out.println("\nJoueur A\n"); break;
			case 'b' : System.out.println("\nJoueur B\n"); break;
		}
		
		while (n <= plateau.length) {
// on force le joueur a entrer des coordonnees exactes.
			do {
				coor = Console.readLine("Coordonnees pour le bateau no " + n + " : ").toUpperCase();
// on force le joueur a entrer deux caracteres pour les coordonnees
				if (coor.length() == 2) {
					l = ((int)coor.charAt(0))-65 ; // conversion de la lettre en indice, en passant par le code ascii
					c = ((int)coor.charAt(1))-49; // conversion du chiffre en indice en passant par le code ascii
				}
				else {
					System.out.println("\t\tCoordonnees inexactes.");
				}
// avertissement si les coordonnees depassent le tableau
				if (l >= plateau.length || c >= plateau.length) {
					System.out.println("\t Ces coordonnees sont en dehors du plateau de jeu.");
				}
			} while (l >= plateau.length || c >= plateau.length || coor.length() != 2);

// le joueur a veut placer son bateau
			if (joueur == 'a') {
				switch (plateau[l][c]) {
// si un bateau du joueur b et deja present sur la case
					case 'b' :
						plateau[l][c] = '2';
						n++;
						break;
// si la case est vide
					case 'v' :
						plateau[l][c] = 'a';
						n++;
						break;
				}
			}
// le joueur b veut placer son bateau
			else {
				switch (plateau[l][c]) {
// si un bateau du joueur a et deja present sur la case
					case 'a' :
						plateau[l][c] = '2';
						n++;
						break;
// si la case est vide
					case 'v' :
						plateau[l][c] = 'b';
						n++;
						break;
				}
			}
		}
	}

	
	public void afficherPlateau() {
	// affiche le plateau de jeu
		int c, l ; // compteur pour colonne et ligne
		// affichage des numeros de colonnes
		for (c = 1 ; c <= plateau.length ; c++) {
			System.out.print("\t" + c);
		}
			System.out.println("\n");
		// affichage des lignes contenant les bateaux
		for (c = 0 ; c < plateau.length ; c++) {
		// affichage de la lettre pour la ligne : A,B,...
			System.out.print((char)(65 + c));
		// affichage du contenu du tableau
			for (l = 0 ; l < plateau[c].length ; l++) {
				System.out.print("\t");
		// on teste le contenu de la case du tableau
				switch (plateau[c][l]) {
					case 'a' : System.out.print('A'); break;
					case 'b' : System.out.print('B'); break;
					case 'v' : System.out.print(' '); break;
		// si ce n'est ni a, ni b, ni v alors c'est a et b donc 2
					default : System.out.print(LES2JOUEURS);
				}
			}
			System.out.println("\n"); // on a va a la ligne a chaque fin de ligne
		}
	}
	
	public void jouer() {
// tant qu'aucun des deux joueurs n'a gagne, on alterne le joueur a et b
		while (!gagne) {
			if (tour == 'a') {
				System.out.println("\t\tJoueur A");
				gagne = tourJoueur('a');
				tour = 'b';
afficherPlateau();
System.out.println();
			}
			else {
				System.out.println("\t\tJoueur B");
				gagne = tourJoueur('b');
				tour = 'a';
afficherPlateau();	
System.out.println();		
			}
		}
	}
	
	public boolean tourJoueur(char joueur) {
	
		String coor ; // coordonnees
		boolean gagne = false ;
		int ennemi = 0;
		int c = -1, l= -1 ; // indices saisis par le joueur, on initialise a -1 au cas ou le joueur saisi une valeur incorrecte
// on force le joueur a entrer des coordonnees exactes.
		do {
			coor = Console.readLine("Lancer une torpille en : ").toUpperCase();
// on force le joueur a entrer deux caracteres pour les coordonnees
			if (coor.length() == 2) {
				l = ((int)coor.charAt(0))-65 ; // conversion de la lettre en indice, en passant par le code ascii
				c = ((int)coor.charAt(1))-49; // conversion du chiffre en indice en passant par le code ascii
			}
			else {
				System.out.println("\t\tCoordonnees inexactes.");
			}
// avertissement si les coordonnees depassent le tableau
			if (l >= plateau.length || c >= plateau.length) {
				System.out.println("\t Ces coordonnees sont en dehors du plateau de jeu.");
			}
		} while (l >= plateau.length || c >= plateau.length || coor.length() != 2);
// Joueur a		
		if (joueur == 'a') {
			switch (plateau[l][c]) {
// les coordonnees correspondent a un bateau ennemi
				case 'b' : 
					plateau[l][c] = 'v';
					System.out.println("\t\t\t\t Coule !\n");
					break;
// les coordonnees correspondent a un bateau ennemi et a un bateau du joueur a
				case '2' : 
					plateau[l][c] = 'a';
					System.out.println("\t\t\t\t Coule !\n");
					break;
// les coordonnees correspondent a un bateau du joueur a ou a une case vide
				default : 
					System.out.println("\t\t\t\t Dans l'eau !\n");
			}
// on compte combien il reste de bateaux b
			for (c = 0 ; c < plateau.length ; c++) {
				for (l=0 ; l < plateau[c].length ; l++) {
					if (plateau[c][l] == 'b' || plateau[c][l] == '2') {
						ennemi++;
					}
				}
			}
		}
// Joueur b
		else {
			switch (plateau[l][c]) {
// les coordonnees correspondent a un bateau ennemi
				case 'a' :
					plateau[l][c] = 'v';
					System.out.println("\t\t\t\t Coule !\n");
					break;
// les coordonnees correspondent a un bateau ennemi et a un bateau du joueur b
				case '2' :
					plateau[l][c] = 'b';
					System.out.println("\t\t\t\t Coule !\n");
					break;
// les coordonnees correspondent a un bateau du joueur b ou a une case vide
				default :
					System.out.println("\t\t\t\t Dans l'eau !\n");
			}
// on compte combien il reste de bateaux a
			for (c = 0 ; c < plateau.length ; c++) {
				for (l=0 ; l < plateau[c].length ; l++) {
					if (plateau[c][l] == 'a' || plateau[c][l] == '2') {
						ennemi++;
					}
				}
			}
		}
// s'il ne reste plus de bateau ennemi, le joueur qui vient de jouer a gagne donc il faut renvoyer true
		if (ennemi == 0) {
			gagne = true;
		}
		
		return gagne;
	}
	
	public static void main(String[] args) {
		int taille ; 
		// on force la saisie d'un nombre compris entre 2 et 9
		do {
			taille = Console.readInt("Quelle taille doit avoir le plateau de jeu ? (entre 2 et 9 : )");
		} while(taille <2 || taille >9);
		
		BatailleNavale jeu = new BatailleNavale(taille);
		jeu.afficherPlateau();
		jeu.jouer();
	}
}