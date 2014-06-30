package ScRR;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;



public class Settings extends JFrame implements KeyListener {

	private static final long serialVersionUID = 1L;
	private JCheckBox[] checkbox = new JCheckBox[5];
	private JButton button;
	private Container srccen;
	private Randomiser rand;
	private ScRR scrr;

	private class ButtonAct implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String action = e.getActionCommand();
			if (action.equals("Apply")) {
				for (int i = 0; i < 4; i++) {
					rand.chageStat(i, checkbox[i].isSelected());
				}
				scrr.setStrats(checkbox[4].isSelected());
			}
			setVisible(false);
			dispose();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.isControlDown()) {
			if (e.getKeyCode() == KeyEvent.VK_R) {
				checkbox[3].setSelected(!checkbox[3].isSelected());
			} else if (e.getKeyCode() == KeyEvent.VK_E) {
				checkbox[2].setSelected(!checkbox[2].isSelected());
			} else if (e.getKeyCode() == KeyEvent.VK_W) {
				checkbox[1].setSelected(!checkbox[1].isSelected());
			} else if (e.getKeyCode() == KeyEvent.VK_Q) {
				checkbox[0].setSelected(!checkbox[0].isSelected());
				//rand.chageStat(0, rand.getIgnor(0));
			} else if (e.getKeyCode() == KeyEvent.VK_X){
				checkbox[4].setSelected(!checkbox[4].isSelected());
			}

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			dispose();
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER|| e.getKeyCode() == KeyEvent.VK_SPACE) {
			for (int i = 0; i < 4; i++) {
				rand.chageStat(i, checkbox[i].isSelected());
			}
			scrr.setStrats(checkbox[4].isSelected());
			setVisible(false);
			dispose();
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	public Settings() {
		config();
		init();
		render();
	}

	public Settings(ScRR scrr, Randomiser rand) {
		this.scrr = scrr;
		this.rand = rand;
		config();
		init();
		render();
		setLocation(scrr.getLocation().x - 150, scrr.getLocation().y);
	}

	public void config() {
		setSize(135, 240);

		//setLocationRelativeTo(null);
		
		setResizable(false);
		setTitle("Settings");
		
		addKeyListener(this);
		setFocusable(true);
		setIconImage(scrr.getIconImage());
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	public void init() {
		button = new JButton("Apply");
		button.setSize(100, 50);

		button.setLocation(15, 150);

		srccen = this.getContentPane();
		srccen.setLayout(new GridLayout(1, 1));

	}

	public void makeCheckBox(JPanel panel) {
		JPanel checkPanel = new JPanel();
		checkPanel.setLayout(new GridLayout(6, 0, 1, 1));

		JCheckBox check = new JCheckBox("Protoss");
		Dimension dim = check.getPreferredSize();

		checkPanel.setSize(dim.width + 100, dim.height + 100);

		String str[] = { "Terran", "Zerg", "Protoss", "Random", "Strategis" };

		for (int i = 0; i < 4; i++) {
			checkbox[i] = new JCheckBox(str[i]);
			checkbox[i].setName(str[i]);
			checkbox[i].setSelected(!rand.getIgnor(i));
			checkPanel.add(checkbox[i]);
		}
		
		//add separtor!
		JSeparator s = new JSeparator();
		checkPanel.add(s);
		
		checkbox[4] = new JCheckBox(str[4]);
		checkbox[4].setName(str[4]); 
		checkbox[4].setSelected(scrr.isStrats());
		checkPanel.add(checkbox[4]);
		
		panel.add(checkPanel);

	}

	public void render() {
		JPanel panel = new JPanel();
		panel.setLayout(null);

		ButtonAct BA = new ButtonAct();
		
		makeCheckBox(panel);

		// panel.add(checkbox);
		panel.add(button);
		button.addActionListener(BA);
		//button.addKeyListener(this);
		//button.setFocusable(true);

		srccen.add(panel);

	}

}
