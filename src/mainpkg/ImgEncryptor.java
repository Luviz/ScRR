package mainpkg;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class ImgEncryptor {

	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		ArrayList<ImageIcon> img = new ArrayList<ImageIcon>();
		//ImageIcon i;
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
