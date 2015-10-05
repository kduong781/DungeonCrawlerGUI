import java.awt.*;
/**
 * Methods and data members for the Hero Class
 */
import java.io.*;
import java.util.*;

public class Hero extends Character implements Serializable {

	/*
	 * List of items the hero currenty has
	 */
	private ArrayList<Item> item = new ArrayList<Item>();
	private int x2 = 0, y2 = 0;
	/*
	 * location of the hero
	 */
	private Point location;
	/*
	 * Heros starting hp
	 */
	private static int h = 30;

	/**
	 * constructor for the hero
	 * 
	 * @param n
	 *            name
	 * @param q
	 *            quip
	 * @param start
	 */
	Hero(String n, String q, Point start) {
		super(n, q, h, 1, 25);
		location = start;
		x2 = location.x;
		y2 = location.y;

	}

	public void incrementX() {
		x2++;
	}

	public void decrementX() {
		x2--;
	}

	public void incrementY() {
		y2++;
	}

	public void decrementY() {
		y2--;
	}

	/**
	 * returns maximum hp hero can have at each level( used for health potions)
	 * 
	 * @return hp scaled according to level
	 */
	public int maxHp() {
		return 30 * getLevel();
	}

	/**
	 * Checks if potion is in itemList
	 * 
	 * @return true if health potion is in inventory/ false if not
	 */
	public ArrayList<Item> getInventory() {
		return item;
	}

	/**
	 * paint heroes on board updates position
	 * 
	 * @param g
	 * @param dir
	 */
	public void drawHero(Graphics g, int dir) {
		// TODO Auto-generated method stub
		g.setColor(Color.black);
		// System.out.println(dir);
		if (dir == 1) {
			goUp(g);
			System.out.println(dir);
		} else if (dir == 2) {
			goDown(g);
			// System.out.println("Hello");
		} else if (dir == 3) {
			goLeft(g);
		} else if (dir == 4) {
			goRight(g);
		} else {
			g.fillRect(x2, y2, 160, 160);
		}
	}

	/**
	 * goes up
	 * 
	 * @param g
	 */
	public void goUp(Graphics g) {
		y2 = y2 - 160;
		g.fillRect(x2, y2, 160, 160);

	}

	/**
	 * moves tank down
	 * 
	 * @param g
	 * @param rect
	 * @param dir
	 */
	public void goDown(Graphics g) {
		y2 = y2 + 160;
		g.fillRect(x2, y2, 160, 160);
	}

	/**
	 * makes tank go left
	 * 
	 * @param g
	 * @param rect
	 * @param dir
	 */
	public void goLeft(Graphics g) {
		x2 = x2 - 160;
		g.fillRect(x2, y2, 160, 160);
		// }
	}

	/**
	 * go right
	 * 
	 * @param g
	 */
	public void goRight(Graphics g) {
		x2 = x2 + 160;
		g.fillRect(x2, y2, 160, 160);
		// }
	}

	/**
	 * displays the items that the hero currently has
	 */
	public void displayInv(Graphics g) {
		int x = 1;
		int y = 140;
		g.drawString("Inventory: ", 650, 110);
		for (Item i : item) { // goes through item arrayList
			g.drawString(x + ". " + i.getName(), 650, y);
			x++;
			y = y + 30;
		}
	}

	/**
	 * removes item
	 * 
	 * @param x
	 */
	public void removeI(int x) {
		if (x <= item.size() - 1) {
			item.remove(x);
		}
	}

	/**
	 * Displays items at the element specified by the user
	 * 
	 * @param answer
	 *            // element
	 * @return // returns the item at the element
	 */
	public Item displayItem(int answer) {
		return item.get(answer);
	}

	/**
	 * gets item when hero kills a minion or when he walks into a room with an
	 * item
	 * 
	 * @param i
	 *            item recieved
	 * @return returns the item recieved into the arrayList
	 */
	public Item getItem2(int i) {
		return item.get(i);
	}

	public ArrayList<Item> getItem(Item i) {
		if (item.size() < 5) { // limits maximum item to 5
			item.add(i);
		} else {
			System.out.println("Your inventory is full!");
		}
		return item;

	}

	/**
	 * Used to check if user wants to pick up item
	 * 
	 * @param i
	 * @return
	 */
	public void pickUpItem(Item i) {
		if (item.size() < 5) {
			item.add(i);
		}
	}

	/**
	 * removes item specified by user
	 * 
	 * @param i
	 *            (specified by Type: Item)
	 */
	public void removeItem(Item i) {
		item.remove(i);
	}

	/**
	 * removes item specfied by user
	 * 
	 * @param index
	 *            element where item is in the arrayList
	 */

	/**
	 * returns location of the hero
	 * 
	 * @param p
	 *            Point p
	 * @return returns location
	 */
	public Point getLocation(Point p) {
		return location;
	}

	/**
	 * set location of hero on the map
	 * 
	 * @param p
	 */
	public void resetLocation() {
		x2 = 0;
		y2 = 480;
	}

	/**
	 * moves the hero north
	 * 
	 * @param l
	 *            Level
	 * @return returns temporary char
	 */
	/**
	 * Attacks the character specified
	 * 
	 * @param c
	 *            character being attacked
	 */
	@Override
	public void attack(Character c) {
		// TODO Auto-generated method stub
		Random r = new Random();
		int random = r.nextInt(3) + 1;
		Item item = new Item("", 0);
		System.out.println(getName() + " hits " + c.getName() + " for "
				+ random + " damage");
		c.takeDamage(random);
		if (c.getHp() <= 0) {
			System.out.println(getName() + " has killed an " + c.getName());
			System.out.println(getName() + " has recieved " + c.getGold()
					+ " gold");
			System.out.println(c.getName() + " had been defeated and says "
					+ c.getQuip());

			collectGold(c.getGold());

		}

	}

}
