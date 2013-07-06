package mainpkg;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

/**
 * @author Bardia Jedi
 * @version 1.01
 */

public class ScRR extends JFrame {

	private final String VERSION = "v 1.01";

	private static final long serialVersionUID = 1L;

	private class BListen implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getActionCommand() == "RANDOMISE RACE!") {
				timer.start();
				
			}
			if (e.getActionCommand() == "timer"){
				
				if (tTimer < 4){
					//System.out.println(tTimer);
					setJlImage(tTimer);
					tTimer++;
				}else {
					//System.out.println("\t"+tTimer);
					randomise();
					timer.stop();
					tTimer =0;
				}
			}
		}
	}

	private class MListen implements ActionListener {	//Menu action ActionListener

		@Override
		public void actionPerformed(ActionEvent e) { 	
			if (e.getActionCommand().equals("Exit")) {
				System.exit(0);
			}
			if (e.getActionCommand().equals("Settings")) {
				lunchSettings();
			}
			if (e.getActionCommand().equals("Load Strat")) {
				String temp = fStrat;
				fStrat = (String)JOptionPane.showInputDialog(null, 
						"File Name","File Name",JOptionPane.DEFAULT_OPTION, null, null, fStrat);
				File test = null;
				try {
				test = new File(fStrat);
				}catch(Exception e1){}	//The Exception in ignored do to JOptions cancel return being null
				if (fStrat == null){
					fStrat = temp;
				}else if(!test.exists()){
					JOptionPane.showMessageDialog(null, "<html>" +
							"<p>The File dose NOT exists</p>" +
							"<p>Reseting file to the previous file</p>" +
							"<p align='center'>"+temp+"</p>" +							
							"</html>");
					fStrat = temp;
				}
			}
			if (e.getActionCommand().equals("About")) {
				JLabel aboutTL = new JLabel(ABOUT);
				aboutTL.setHorizontalAlignment((int) CENTER_ALIGNMENT);
				JOptionPane.showMessageDialog(null, aboutTL, "About",
						JOptionPane.PLAIN_MESSAGE);
			}
		}
	}

	private class KListen implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.isControlDown()) {
				if (e.getKeyCode() == KeyEvent.VK_S) {
					lunchSettings();
				} else if (e.getKeyCode() == KeyEvent.VK_R) {
					rand.chageStat(3, rand.getIgnor(3));
				} else if (e.getKeyCode() == KeyEvent.VK_E) {
					rand.chageStat(2, rand.getIgnor(2));
				} else if (e.getKeyCode() == KeyEvent.VK_W) {
					rand.chageStat(1, rand.getIgnor(1));
				} else if (e.getKeyCode() == KeyEvent.VK_Q) {
					rand.chageStat(0, rand.getIgnor(0));
				} else if (e.getKeyCode() == KeyEvent.VK_X){
					strats = !strats;
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {

			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				System.exit(0);
			}
			if (e.getKeyCode() == KeyEvent.VK_ENTER
					|| e.getKeyCode() == KeyEvent.VK_SPACE) {
				randomise();
			}
			
		}

		@Override
		public void keyTyped(KeyEvent e) {

		}

	}

	private Timer timer;
	private int tTimer =0;	//timer counter! 
	
	private Container mSrccen;
	private JLabel jL_m;
	private JButton jB_rand;

	private Randomiser rand;
	StratGUI s;

	private ImageIcon img_race;
	private ArrayList<ImageIcon> races;
	private String fStrat = "strat.sml"; 
	private Settings setting; 
	private boolean strats = false;

	private final String ABOUT = "<HTML>" 	//about text in HTML!
			+ "<P ALIGN=LEFT STYLE=\"margin-bottom: 0in\">Bardia Jedi<BR>"
			+ "ScRR - Starcraft race randomiser<BR>"
			+ VERSION
			+ "<BR>"
			+ "&#169;2013 Bardia \"Luviz\" Jedi<BR>"
			+ "java version 1.7.0_17<BR><BR>"
			+ "<A HREF='mailto:bardiajedi@gmail.com'>bardiajedi@gmail.com</A>"
			+ "</P>" + "</HTML>";
	
	private void lunchSettings(){
		if (setting != null){
			setting.dispose();
		}
		setting = new Settings(this, rand);
		setting.setVisible(true);
	}
	
	public boolean isStrats() {
		return strats;
	}



	public void setStrats(boolean strats) {
		this.strats = strats;
	}



	public ScRR() {
		config();
		init();
		render();
		menu();
	}

	private void init() {

		img_race = races.get(2); // 2 == Protoss
		jL_m = new JLabel(img_race);
		jB_rand = new JButton("RANDOMISE RACE!");

		jL_m.setSize(300, 300);
		jB_rand.setSize(300, 80);

		jL_m.setLocation(0, 0);
		jB_rand.setLocation(0, 300);

		rand = new Randomiser();
		rand.chageStat(3, false);

		mSrccen = this.getContentPane();
		mSrccen.setLayout(new GridLayout(1, 1));
		
	}

	private void config() {

		setSize(306, 430);
		// pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("SC2 Random");
		setRaces();
		setIconImage(races.get(2).getImage());
	}

	private void render() {
		JPanel panel = new JPanel();
		panel.setLayout(null);

		BListen listen = new BListen();
		
				
		panel.add(jL_m);
		panel.add(jB_rand);
		
		jB_rand.addActionListener(listen);
		KeyListener l = new KListen();
		addKeyListener(l);
		jB_rand.addKeyListener(l);
		
		timer = new Timer(75, listen);
		timer.setActionCommand("timer");
		
		mSrccen.add(panel);
		setFocusable(true);
		
	}

	/**
	 * this build the Menu bar at the top
	 * and connects to {@link MListen}
	 * 
	 */
	private void menu() { 	// Menu!
		MListen menuListner = new MListen();

		JMenuBar menubar = new JMenuBar(); // add Menu in bar
		JMenu menu = new JMenu("File"); // set item
		menubar.add(menu); // add item

		JMenuItem menuitem = new JMenuItem("Settings"); // add item
		menu.add(menuitem); // set item
		menuitem.addActionListener(menuListner); // add Listener

		menuitem = new JMenuItem("Load Strat");
		menu.add(menuitem);
		menuitem.addActionListener(menuListner);
		
		menu.addSeparator(); // Separator line

		menuitem = new JMenuItem("Exit");
		menu.add(menuitem);
		menuitem.addActionListener(menuListner);

		menu = new JMenu("Info");
		menubar.add(menu);
		menuitem = new JMenuItem("About");
		menu.add(menuitem);
		menuitem.addActionListener(menuListner);
		this.setJMenuBar(menubar);

	}

	
	/**
	 * imports all images for the races from imges.dat
	 * @throws IOException 	if the files missing runs a {@link JOptionPane}
	 * 						to inform and quits the program 						
	 */
	@SuppressWarnings("unchecked")
	public void setRaces() {
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream("imges.dat"));
			races = (ArrayList<ImageIcon>) in.readObject();
			//e.printStackTrace();

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e + "\n<html><p style = 'color: red; text-align:center;'><i><u>" +
					"imges.dat is missing</u></i><p></html>","imges.dat is missing".toUpperCase(),0);
			races = null;
			System.exit(0);
		}
	}
	
	/**
	 * run the randomise 0 -4 and 
	 * set the iconImag to images value
	 */
	private void randomise() {
		img_race = races.get(rand.roll());
		if (strats == true){
			if (s != null){
				s.dispose();
			}
			s = new StratGUI(rand.getValue(), this, fStrat);
			s.setVisible(true);
		}
		
		jB_rand.setFocusable(false);
		jL_m.setIcon(img_race);
		setJlImage(rand.roll());
	}
	
	
	/**@param iIndex	image to it's value*/
	private void setJlImage(int iIndex){
		jL_m.setIcon(races.get(iIndex));
	}

}
