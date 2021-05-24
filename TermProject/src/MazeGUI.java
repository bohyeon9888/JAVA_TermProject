import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;


public class MazeGUI
{
	public static void main(String[] args) throws IOException, InterruptedException
	{
		
		
		try
		{
						
			int size = 15;
			
			Maze maze = new Maze(size); // Constructs the maze object
			
			JFrame frame = new JFrame("Maze");
			MazePanel panel = new MazePanel(maze); // Constructs the panel to hold the
			// maze
			JScrollPane scrollPane = new JScrollPane(panel);
			
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(800, 800);
			frame.add(scrollPane, BorderLayout.CENTER);
			frame.setVisible(true);
		}
		catch(NumberFormatException exception)
		{
			System.out.println("The input number for the maze size must be an integer") ;
		}
	}
}

// This is the JPanel replacement for mazes that stores as a data
// element the maze and calls the mazes's drawing function
class MazePanel extends JPanel implements KeyListener, Runnable
{
	private Maze maze; // the maze object
	
	int x, y;
	boolean KeyUp = false;
	boolean KeyDown  = false;
	boolean KeyLeft = false;
	boolean KeyRight = false;
	Thread th;
	
	Toolkit tk = Toolkit.getDefaultToolkit();
	Image me_img = tk.getImage("C:\\Users\\dongh\\git\\JAVA_TermProject\\TermProject\\src\\chu1.png");

	public MazePanel(Maze theMaze)
	{
		maze = theMaze;
		init();
		start();
	}
	
	public void init() {
		x = 30;
		y = 30;
	}
	
	public void start() {
		addKeyListener(this);
		th = new Thread(this);
		th.start();
	}


	public void run() {
		try {
			while(true) {
				KeyProcess();
				repaint();
				Thread.sleep(20);
			}
		}catch(Exception e) {}
	}

	// The paintComponent method is called every time
	// that the panel needs to be displayed or refreshed.
	// Anything you want drawn on the panel should be drawn
	// in this method.
	public void paintComponent(Graphics page) 
	{
		super.paintComponent(page);
		
		setBackground(Color.white); // set preferredSize for JScrollPane
		
		this.setPreferredSize(maze.windowSize()); // draw the maze and the solution 
		// path in red points
		
		maze.draw(page);
		page.drawImage(me_img, x, y, this);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_UP :
		KeyUp = true;
		break;
		case KeyEvent.VK_DOWN:
			KeyDown = true;
			break;
		case KeyEvent.VK_LEFT:
			KeyLeft = true;
			break;
		case KeyEvent.VK_RIGHT:
			KeyRight = true;
			break;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_UP:
			KeyUp = false;
			break;
		case KeyEvent.VK_DOWN:
			KeyDown = false;
			break;
		case KeyEvent.VK_LEFT:
			KeyLeft = false;
			break;
		case KeyEvent.VK_RIGHT:
			KeyRight = false;
			break;
		}
	}
	
	public void keyTyped(KeyEvent e) {}
	public void KeyProcess() {
		if(KeyUp == true) y -= 5;
		if(KeyDown == true) y+= 5;
		if(KeyLeft == true) x-=5;
		if(KeyRight == true) x += 5;
	}

}
