package mainpkg;

import java.io.BufferedReader;
//import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

//import javax.swing.JOptionPane;




public class Strategies {
	private ArrayList<Strat> tStrats = new ArrayList<Strat>();
	private ArrayList<Strat> zStrats = new ArrayList<Strat>();
	private ArrayList<Strat> pStrats = new ArrayList<Strat>();
	private ArrayList<Strat> rStrats = new ArrayList<Strat>();
	
		
	public Strategies() {
		add("OverAll", "N/A", "<html><p style = 'color:#848282'>Who ever cared, about the random</p></html>");
	}

	
	
	public Strategies(ArrayList<Strat> tStrats, ArrayList<Strat> zStrats,
			ArrayList<Strat> pStrats) {
		this.tStrats = tStrats;
		this.zStrats = zStrats;
		this.pStrats = pStrats;
	}



	public Strat get(int r, int ix){
		if (r == 0){
			if (ix < tStrats.size())
				return tStrats.get(ix);
			
		}else if (r == 1){
			if (ix < zStrats.size())
				return zStrats.get(ix);
			
		}else if (r == 2){
			if (ix < pStrats.size())
				return pStrats.get(ix);
			
		}else if (r == 3){
			if (ix < rStrats.size())
				return rStrats.get(ix);
			
		}
		return null;
	}
	
	public Strat getrand(int r) {
		if (r == 0) {
			return tStrats.get((int) new Random().nextInt(tStrats.size()));
		} else if (r == 1) {
			return zStrats.get((int) new Random().nextInt(zStrats.size()));
		} else if (r == 2) {
			return pStrats.get((int) new Random().nextInt(pStrats.size()));
		} else if (r == 3) {
			return rStrats.get((int) new Random().nextInt(rStrats.size()));
		}
		return null;
	}
	
	public ArrayList<Strat> gettStrats() {
		return tStrats;
	}

	public ArrayList<Strat> getzStrats() {
		return zStrats;
	}

	public ArrayList<Strat> getpStrats() {
		return pStrats;
	}

	public ArrayList<Strat> getrStrats() {
		return rStrats;
	}

	public void add(String race ,String type ,String txt){
		race = race.toUpperCase();
		switch (race.toLowerCase()) {
		case "terran":
			tStrats.add(new Strat(race, type, txt));
			break;
		case "zerg":
			zStrats.add(new Strat(race, type, txt));
			break;
		case "protoss":
			pStrats.add(new Strat(race, type, txt));
			break;
		case "overall":
			rStrats.add(new Strat(race, type, txt));
			tStrats.add(new Strat(race, type, txt));
			zStrats.add(new Strat(race, type, txt));
			pStrats.add(new Strat(race, type, txt));
			break;
		default:
			break;
		}
	}
	
	@SuppressWarnings("resource")
	public void LoadStrats(String fName) throws IOException{

		BufferedReader in = null;
		String line;
			
		in = new BufferedReader(new FileReader(fName));
		while ((line = in.readLine()) != null) {
			// System.out.println("l: "+line);
			if (line.contains("<item>")) {
				String race = "", type = "", txt = "";
				while (!line.contains("</item>")) {

					line = in.readLine();
					String temp;
					int fristPos, lastPos;
					if (line.contains("<race>")) {

						fristPos = line.indexOf("<race>");
						lastPos = line.indexOf("</race>");
						temp = line.substring(fristPos + 6, lastPos);
						// System.out.println("r: "+temp);
						race = temp;

					} else if (line.contains("<type>")) {

						fristPos = line.indexOf("<type>");
						lastPos = line.indexOf("</type>");
						temp = line.substring(fristPos + 6, lastPos);
						// System.out.println("t: "+temp);
						type = temp;

					} else if (line.contains("<txt>")) {
						while (!line.contains("</txt>")) {
							line = in.readLine();
							txt = txt + line;
						}
						txt = txt.replaceAll("</txt>", "");
						txt = txt.replaceAll("\t", "");
						// System.out.println("text: "+txt);

					}

				}
				add(race, type, txt);
			}
		}

	}

}
