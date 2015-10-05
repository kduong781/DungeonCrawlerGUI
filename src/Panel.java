import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

import javax.swing.*;

/**
 * data members and methods for panel class
 * 
 * @author KevinD
 * 
 */
public class Panel extends JPanel implements MouseMotionListener,
		MouseListener, KeyListener, Runnable {
	/**
	 * rectangle array list that shows menu
	 */
	private ArrayList<Rectangle> menu = new ArrayList<Rectangle>();
	/**
	 * booleans
	 */
	private boolean onMenu = false, clicked = false, m = false, s = false,
			picked = false;
	private int state, option = 0, dir = 0, xArray = 0, yArray = 3;
	private int xPos = 0, yPos = 0, fight = 0, run = 0, potion = 0;
	private Thread thread;
	private Level l;
	private Hero h;
	private Enemy enemy;

	/**
	 * constructor for panel class
	 * 
	 * @throws FileNotFoundException
	 */
	public Panel() throws FileNotFoundException {
		setSize(800, 800);
		thread = new Thread(this);
		thread.start();
		setVisible(true);
		setFocusable(true);
		setBackground(Color.black);
		l = new Level();
		h = new Hero("Kevin", "Hello", new Point(0, 480));
	}

	/**
	 * painter for panel class
	 */
	public void paintComponent(Graphics g) {
		// if(onMenu == false) {
		// if(clicked == true) {

		// dir = 0;
		super.paintComponent(g);
		g.setColor(Color.white);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 80));
		g.drawString("Dungeon Crawler", 145, 150);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
		g.drawString("Play New Game", 250, 370);
		g.drawString("Load Game", 250, 570);
		g.drawRect(200, 300, 400, 100);
		g.drawRect(200, 500, 400, 100);
		menu.add(new Rectangle(200, 300, 400, 100));
		menu.add(new Rectangle(200, 500, 400, 100));

		if (option == 1) {
			try {
				if (m == true && picked == true) {
					dir = 0;
				}
				g.setColor(Color.yellow);
				g.fillRect(0, 0, 1000, 1000);
				l.generateLevel(h.getLevel(), g);
				g.setColor(Color.black);
				g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
				g.drawString("Hp: " + h.getHp() + " Level: " + h.getLevel(),
						650, 50);
				g.drawString("Gold: " + h.getGold(), 650, 80);
				h.displayInv(g);
				g.drawString("Enemy: ", 650, 350);

				h.drawHero(g, dir);

				if (m == true) {
					g.drawString("Enemy: " + enemy.getName(), 650, 350);
					g.drawString("HP: " + enemy.getHp(), 650, 380);
					g.setColor(Color.gray);
					g.fillRect(10, 650, 420, 100);
					g.fillRect(440, 650, 420, 100);
					g.fillRect(650, 540, 210, 100);
					g.setColor(Color.white);
					g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
					g.drawString("Fight", 40, 710);
					g.drawString("Run Away", 470, 710);
					g.drawString("Potion", 680, 600);
				}
				updateUI();
				dir = 0;
				// System.out.println(l.level[3][0]);//y,x
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// g.setColor(Color.green);
			// g.fillRect(100,100,100,100);
		} else if (option == 2) {

		}
	}

	// }
	public static void main(String[] args) throws FileNotFoundException {
		Frame x = new Frame();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		Rectangle rect = new Rectangle(e.getX(), e.getY(), 1, 1);
		// System.out.println("x: " + e.getX() + "y: " + e.getY());
		if ((e.getX() >= 208 && e.getX() <= 608)
				&& (e.getY() >= 330 && e.getY() <= 430)) {
			state = 1;
			onMenu = true;

		} else if ((e.getX() >= 208 && e.getX() <= 608)
				&& (e.getY() >= 530 && e.getY() <= 630)) {
			state = 2;
			onMenu = true;
		} else {
			state = -1;
			onMenu = false;
		}
		if ((e.getX() >= 18 && e.getX() <= 436)
				&& (e.getY() >= 681 && e.getY() <= 779)) {
			state = 3; // fight
		} else if ((e.getX() >= 448 && e.getX() <= 867)
				&& (e.getY() >= 681 && e.getY() <= 779)) {
			state = 4; // run
		} else if ((e.getX() >= 658 && e.getX() <= 868)
				&& (e.getY() >= 570 && e.getY() <= 670)) {
			state = 5; // potion
		}
		// System.out.println(state);
		// //this.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * mouselistener for panel class
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if (onMenu == true) {
			if (state == 1) {
				this.removeAll();
				System.out.println("Clicked on the menu one!");
				option = 1;
				clicked = true;
				// this.repaint();
			}
			if (state == 2) {
				this.removeAll();
				System.out.println("Clicked on the menu two!");
				option = 2;
				clicked = true;
				// this.repaint();
			}
		}
		if (m == true) {
			if (state == 3) {
				h.attack(enemy);
				if (enemy.getHp() > 0) {
					enemy.attack(h);
				} else {
					m = false;
				}
			}
			if (state == 4) {
				enemy.attack(h);
				Random r = new Random();
				dir = r.nextInt(4) + 1;
				boolean bounds = false;
				// play(l.level[yArray][xArray]);
				while (bounds == false) {
					switch (dir) {
					case 1:
						yArray--;
						break;
					case 2:
						yArray++;
						break;
					case 3:
						xArray--;
						break;
					case 4:
						xArray++;
						break;
					}
					if ((yArray < 0 || yArray > 3)
							|| (xArray < 0 || xArray > 3)) {
						dir = r.nextInt(4) + 1;
					} else {
						bounds = true;
						try {
							play(l.level[yArray][xArray]);
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				m = false;
			}
			if (state == 5) {
				boolean check = false;
				int index = -1, x = 0;
				for (Item i : h.getInventory()) {
					x++;
					System.out.println(i.getName());
					if (i.getName().equals("Health Potion") == true) {
						index = x;
					}
				}
				if (index > -1) {
					h.removeI(index);
					h.heal(5);
				}
			}

		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * action when moved to new tile
	 * 
	 * @param temp
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException 
	 */
	public void play(char temp) throws FileNotFoundException, ClassNotFoundException {
		switch (temp) {
		case 'm':
			EnemyGenerator e = new EnemyGenerator();
			enemy = e.generateEnemy(1);
			String monster = h.getName() + " has encountered a "
					+ enemy.getName() + "\nIt has " + enemy.getHp() + " hp";
			JOptionPane.showMessageDialog(null, monster);
			m = true;
			s = false;
			// option(enemy, h, p, l);
			break;
		case 'i':
			ItemGenerator i = new ItemGenerator();
			Item item = i.generateItem();
			System.out.println("You found a " + item.getName());
			// choice(item, h);
			h.pickUpItem(item);
			m = false;
			s = false;
			break;
		case 'f':
			if (h.getLevel() != 3) {
				h.resetLocation();
				h.increaseLevel();
				try{
					ObjectOutputStream save = new ObjectOutputStream(new FileOutputStream("hero.dat"));
					save.writeObject(h);
					save.close();
				ObjectInputStream read = new ObjectInputStream(new FileInputStream("hero.dat"));
				}catch(IOException e1){
				}
				xArray = 0;
				yArray = 3;
				dir = 0;
				m = false;
				s = true;
			}
		case 's':
			s = true;
			break;
		// l.generateLevel(h.getLevel(),g);
		}
	}

	/**
	 * actionwhen key is pressed
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();
		// System.out.println("X " + tank.getTX());
		// System.out.println("Y"+ tank.getTY());
		// boolean checkWall = checkWall();
		boolean stop = true;
		System.out.println(yArray + " " + xArray);
		switch (keyCode) {
		case KeyEvent.VK_1:
			if (h.getInventory().size() >= 1 && s == true) {
				h.collectGold(h.getItem2(0).getValue());
				h.removeI(0);
			}
			break;
		case KeyEvent.VK_2:
			if (h.getInventory().size() >= 2 & s == true) {
				h.collectGold(h.getItem2(1).getValue());
				h.removeI(1);

			}
			break;
		case KeyEvent.VK_3:
			if (h.getInventory().size() >= 3 & s == true) {
				h.collectGold(h.getItem2(2).getValue());
				h.removeI(2);
			}
			break;
		case KeyEvent.VK_4:
			if (h.getInventory().size() >= 4 & s == true) {
				h.collectGold(h.getItem2(3).getValue());
				h.removeI(3);
			}
			break;
		case KeyEvent.VK_5:
			if (h.getInventory().size() >= 5 && s == true) {
				h.collectGold(h.getItem2(4).getValue());
				h.removeI(4);
			}
			break;
		case KeyEvent.VK_W:
			if (yArray > 0 && m != true) {
				dir = 1;
				yArray--;
				System.out.println(yArray + " " + xArray);
				try {
					play(l.level[yArray][xArray]);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			// yPos--;
			// System.out.println("Hi");
			// shoot = 0;
			// this.repaint();
			break;
		case KeyEvent.VK_S:
			if (yArray < 3 && m != true) {
				dir = 2;
				yArray++;
				try {
					play(l.level[yArray][xArray]);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// yPos++;
 catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			// this.repaint();
			// shoot = 0;
			break;
		case KeyEvent.VK_A:
			// xPos--;
			if (xArray > 0 && m != true) {
				dir = 3;
				xArray--;
				try {
					play(l.level[yArray][xArray]);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			// this.repaint();
			// shoot = 0;
			break;
		case KeyEvent.VK_D:
			// shoot = 1;
			// xPos++;
			if (xArray < 3 & m != true) {
				dir = 4;
				xArray++;
				try {
					play(l.level[yArray][xArray]);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			// this.repaint();
			// shoot = 0;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * thread run method
	 */
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			this.repaint();
			try {
				thread.sleep(50);
			} catch (Exception e) {
				// System.out.println("error");
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}
