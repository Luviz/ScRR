package ScRR;

import java.util.Random;
/**
 *  is builed to handel {@link ScRR} randomising needs 
 * @author Bardia Jedi
 * @version 1
 */
public class Randomiser {
	
	private final int sides = 4;	/**the number of sides*/
	private int value;				/**the randomised value*/
	public boolean ignor[]; 		/**if true the value will reroll*/	
	/**
	 * CTOR
	 * builds the dice 
	 * sets sids to be ignor to <code>false</code>
	 */
	public Randomiser(){
		this.ignor = new boolean[this.sides]; 
		for (int i =0; i < this.sides; i ++){
			this.ignor[i] = false; 
		}
		
	}
	/**
	 * will change the {@link ignor[pos]} to {@link Boolean !bool}
	 * @param pos	int 
	 * @param bool	boolean
	 */
	public void chageStat(int pos, boolean bool){
		this.ignor[pos]=!bool;
	}
	/**
	 * return    
	 * @param pos int
	 * @return Boolean {@link Boolean ignor[pos]}
	 */
	public boolean getIgnor(int pos){
		return this.ignor[pos];
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	
	public int roll(){
		int ret;
		Random rand = new Random();
		int i =0;
		do{
			ret = rand.nextInt(this.sides);
			i++;
		}while(this.ignor[ret]==true && i < 10000);
		value = ret;
		return ret;
	}

}
