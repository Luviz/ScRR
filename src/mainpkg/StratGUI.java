package mainpkg;

import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;

@SuppressWarnings("serial")
public class StratGUI extends JFrame{
	private Strategies s = new Strategies();
	private Strat strat;
	private int race;
	private JButton buttom;
	private ScRR scrr;
	//private JLabel mL;
	
	private class BListener implements ActionListener{

		
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			
		}
		
	}
	private class KListener implements KeyListener{

		@Override
		public void keyPressed(KeyEvent arg0) {
			
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE){
				dispose();
			}
			if (arg0.getKeyCode() == KeyEvent.VK_SPACE){
				dispose();
			}
			if (arg0.getKeyCode() == KeyEvent.VK_ENTER){
				dispose();
			}
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			
		}
		
	}
	
	public StratGUI (int r , ScRR scrr, String fName){
		race = r;
		this.scrr = scrr;
		try {
			s.LoadStrats(fName);
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			JOptionPane.showMessageDialog(null,"\n<html><p style = 'color: red; text-align:center;'><i><u>" +
					 fName+" is missing</u></i><p></html>","FILE'S MISSING",0);
			scrr.setStrats(false);
			
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"\n<html><p style = 'color: red; text-align:center;'><i><u>" +
					"The File maybe cropped</u></i><p></html>","FILE'S MISSING",0);
			scrr.setStrats(false);
		}
		config();
		init();
		
	}
	private void config() {
		setTitle("Strategies");
		setIconImage(scrr.getIconImage());
		addKeyListener(new KListener());
	}
	
	private void init() {
		strat = s.getrand(race);
		buttom = new JButton();
		buttom.setText("<html>" +
				"<p>RACE:   "+strat.getRace()+"</p>" +
				"<p>TYPE:   "+strat.gettype() +"</p>" +
				"<p>Quote:</p>"+
				strat.getTxt()+
				"</html>");
		buttom.addActionListener(new BListener());
		add(buttom);
		setFocusable(true);
		pack();
		//setLocationRelativeTo(null);
		setLocation(scrr.getLocation().x+scrr.getWidth()+ 10, scrr.getLocation().y + (scrr.getWidth()/2));	//at the side
		//setLocation(scrr.getLocation().x , scrr.getLocation().y+300);	//on top of rand buttem 
		//System.out.println(getLocation());
	}

	

	
	
	
}
