//import java.util.ArrayList;
import javax.swing.*;
// import java.awt.*;
// import java.awt.event.*;

class DialogBatailleNavale {
		JOptionPane jop = new JOptionPane();

	public Integer askNbJoueurs() {
		// doit prendre en parametre min et max, construire un tableau ensuite
		// tout ca pour utiliser le meme Dialog...java dans jeudulabyrinthe,bataillenavale...
		// ou prendre un tableau d'integer
		
		Integer[] tabNbJoueurs = {1,2};
		// affichage de la boite de dialogue et affection du choix a la variable nb
		Integer nb = (Integer)jop.showInputDialog(null,"Combien de joueurs ?","Nombre de joueurs",
		JOptionPane.QUESTION_MESSAGE,
		null,
		tabNbJoueurs,
		tabNbJoueurs[0]);
		
		return nb;
	}
	
	public void message(String message, String titre) {
		jop.showMessageDialog(null, message, titre, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public boolean question(String message, String titre) {
		int option = jop.showConfirmDialog(null, message, titre, JOptionPane.YES_NO_OPTION,
		JOptionPane.QUESTION_MESSAGE);
			
		if(option == JOptionPane.OK_OPTION)
			return true;
		else
			return false;
	}
	
	public void partieTerminee() {
		jop.showMessageDialog(null, "La partie est terminée !", "Partie Terminee", 
		JOptionPane.INFORMATION_MESSAGE);
	}
}