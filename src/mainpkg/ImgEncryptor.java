package mainpkg;

import java.io.*;
import java.util.ArrayList;

import javax.swing.ImageIcon;
/**
 * 
 * @author bardia
 * creates images.dat
 */
public class ImgEncryptor {
	private ArrayList<ImageIcon> img;
	
	public ImgEncryptor (){
		img = new ArrayList<ImageIcon>();
	}
	/**
	 * Checks is all files are there!
	 * @return {@link Boolean};
	 */
	public boolean checkFiles(){
		File testT = null ,testZ = null ,testP = null ,testR = null;

		testT = new File("t.png");
		testZ = new File("z.png");
		testP = new File("p.png");
		testR = new File("r.png");
		
		return testT.exists() && testZ.exists() && testP.exists() && testR.exists();
		
	}
	/** make sure to have t/z/p/r.png!
	 * 
	 * @throws IOException the file is missing 
	 * 		the file is cropped 
	 */
	public void compress() throws IOException{
		img.add(new ImageIcon("t.png"));
		img.add(new ImageIcon("z.png"));
		img.add(new ImageIcon("p.png"));
		img.add(new ImageIcon("r.png"));
		
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("imges.dat"));
		out.writeObject(img);
		out.flush();
		out.close();
	}
	

}
