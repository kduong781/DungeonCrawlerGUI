import java.awt.*;
import java.util.*;
import java.io.*;

/**
 * Methods and data members of Level Class
 * 
 * @author KevinD
 * 
 */
public class Level {
	// private char[][] level1 = new char[4][4];
	/**
	 * map of dungeon put into a two dimensional array
	 */
	char[][] level;

	Level() {
		level = new char[4][4];
	}

	// public void setBoard(char c,Point p){
	// level[(int)p.getX()][(int)p.getY()] = c;
	// }
	/**
	 * generates the level the hero is in
	 * 
	 * @param levelNum
	 *            the level the hero is in
	 * @throws FileNotFoundException
	 */
	public void generateLevel(int levelNum, Graphics g)
			throws FileNotFoundException {
		Scanner s = new Scanner(new File("Level" + levelNum + ".txt"));
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				level[y][x] = s.next().charAt(0);
				if (level[x][y] == 's') {
					g.setColor(Color.red);
					g.fill3DRect(y * 160, x * 160, 160, 160, true);
				} else if (level[x][y] == 'm') {
					g.setColor(Color.green);
					g.fill3DRect(y * 160, x * 160, 160, 160, true);
				} else if (level[x][y] == 'i') {
					g.setColor(Color.blue);
					g.fill3DRect(y * 160, x * 160, 160, 160, true);
				} else if (level[x][y] == 'f') {
					g.setColor(Color.pink);
					g.fill3DRect(y * 160, x * 160, 160, 160, true);
				}
			}
			if (s.hasNextLine()) {
				s.nextLine();
			}
		}
	}

	/**
	 * Updates where the user is on the map
	 * 
	 * @param p
	 * @return
	 */
	public char getRoom(Point p) {
		char temp = level[(int) p.getX()][(int) p.getY()];
		level[(int) p.getX()][(int) p.getY()] = '*';
		// System.out.println(p.getX());
		return temp;
	}

	/**
	 * displays the two dimensional array
	 * 
	 * @param p
	 */
	public void displayMap(Point p) {
		System.out.println("Dungeon Map: ");
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				System.out.print(level[y][x] + " ");
			}
			System.out.println();
		}
	}

	/**
	 * finds the location of character 's'
	 * 
	 * @return the Point of location s;
	 */
	public Point findStartLocation() {
		Point p = new Point(3, 0);
		return p;
	}
}
