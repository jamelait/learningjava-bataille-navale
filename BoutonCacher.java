import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class BoutonCacher extends JToggleButton implements ActionListener {

	String rep = BatailleNavaleGraphique.REP;
	String ext = BatailleNavaleGraphique.EXT;
	
	private JPanel panel;
	private ImageIcon iconNotSelected = new ImageIcon(rep+"non"+ext);
	private ImageIcon iconSelected = new ImageIcon(rep+"oui"+ext);
	
	public BoutonCacher(JPanel panel) {
		this.panel = panel;
		setIcon(iconNotSelected);
		setSelectedIcon(iconSelected);
		setRolloverEnabled(false);
		setMnemonic(KeyEvent.VK_W);
		addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (isSelected())
			panel.setVisible(false);
		else 
			panel.setVisible(true);
	}
}