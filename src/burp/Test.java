package burp;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import ui.MainPanel;

public class Test {

	public static void main(String[] args) {
		
//		   SwingUtilities.invokeLater(new Runnable() {
//			      public void run() {
//			    	  JPanel mainPanel = new MainPanel();
//			        mainPanel.setVisible(true);
//			      }
//			    });
		
		UIManager.put("TabbedPane.tabAreaInsets"
			    , new javax.swing.plaf.InsetsUIResource(3,20,2,20));
		new MainPanel();

	}

}
