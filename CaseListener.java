import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class CaseListener implements MouseListener {

	final static ImageIcon bombe = new ImageIcon("bombe.gif");
	private int l,c;
	private BatailleNavaleGraphique bn;
	private JButton bouton;
	private boolean coule = false;
	
	public CaseListener(BatailleNavaleGraphique bn, int l, int c) {
		this.l = l;
		this.c = c;
		this.bn = bn;
		this.bouton = bn.getBouton(l,c);
	}
	
	public void mouseClicked(MouseEvent e) {
		switch (bn.joueurActif) {
			case 'a' :
				if (bn.getPlateau()[l][c] == 'b' || bn.getPlateau()[l][c] == '2')
					bn.faireCouler('b',l,c);
				else
					bn.dernierCoup.setText("Dans l'eau !");
			break;
			case 'b' :
				if (bn.getPlateau()[l][c] == 'a' || bn.getPlateau()[l][c] == '2')
					bn.faireCouler('a',l,c);
				else
					bn.dernierCoup.setText("Dans l'eau !");
			break;
		}
		
		// on passe au joueur suivant
		bn.joueurSuivant();
	}
	
	public  void mouseEntered(MouseEvent e) {
		// if (!coule)
			// bouton.setIcon(bombe);
	}
	
	public void mouseExited(MouseEvent e) {
		// if (!coule)
			// bouton.setIcon(null);
	}
	
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
}

