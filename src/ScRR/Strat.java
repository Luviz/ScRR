package ScRR;

public class Strat {
	private String race;	//terran protuss zerg overall
	private String type;		//micro chees etc!
	private String txt;
	//private String img; 	// download form web!
	
	public Strat(){
		this.race = null;
		this.type = null;
		this.txt = null;
	}
	public Strat(String race, String type, String txt) {
		this.race = race;
		this.type = type;
		this.txt = txt;
	}
	
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	
	public String gettype() {
		return type;
	}
	public void settype(String type) {
		this.type = type;
	}
	
	public String getTxt() {
		return txt;
	}
	public void setTxt(String txt) {
		this.txt = txt;
	}
	
	
	
	public String toHtml() {
		//return "Strat [race=" + race + ", type=" + type + ", txt=" + txt + "]";
		return "<html>"+
				"<p style='color:blue;margin-left:20px;'>"+race+"</p>"+
				"<p align='center'>"+type +"</p>"+
				txt+"<br>-----------------</html>";
	}
	
	@Override
	public String toString(){
		return race +'\n'+type+'\n'+txt+"\n--------------------\n";
	}
	
	
		
}
