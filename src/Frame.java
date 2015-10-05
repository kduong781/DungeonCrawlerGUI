import java.io.FileNotFoundException;

import javax.swing.JFrame;

/**
 * data members and methods for frame classs
 * 
 * @author KevinD
 * 
 */
public class Frame extends JFrame {
	/**
	 * constructor for frame
	 * 
	 * @throws FileNotFoundException
	 */
	public Frame() throws FileNotFoundException {
		setSize(900, 800);
		setVisible(true);
		Panel x = new Panel();
		addMouseMotionListener(x);
		addMouseListener(x);
		addKeyListener(x);
		setContentPane(x);
		// pack();
	}
}
