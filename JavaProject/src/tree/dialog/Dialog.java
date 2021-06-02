package tree.dialog;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Dialog extends JFrame{
	private JDialog dialog;
	
	public Dialog(String label,String msg) { 
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.dialog = new JDialog();
		JOptionPane optionPane = new JOptionPane();
		
		optionPane.setMessage(msg);
		this.dialog = optionPane.createDialog(null, label);
		dialog.setVisible(true);
	}
}
