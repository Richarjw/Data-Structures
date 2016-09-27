import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
public class HaysPolygons {
	HaysPolygons(){
		
		JFrame frame = new JFrame("Hays Polygons");

		
		frame.setSize(600,500);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MyPanel panel1 = new MyPanel();
		panel1.setPreferredSize(new Dimension(500,500));
		panel1.setBackground(Color.blue);
		
		
		JPanel panel2 = new JPanel();
		panel2.setPreferredSize(new Dimension(100,500));
		panel2.setBackground(Color.red);
		JButton button = new JButton("Continue");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("hi");
				Polygon newTriangle = panel1.poly;
				panel1.drawTriangle(newTriangle);
				panel1.repaint();
				
			}
		});
		button.setSize(100,100);
		button.setBackground(Color.white);
		panel2.add(button);
		
		JPanel primary = new JPanel();
		primary.setBackground(Color.black);
		primary.add(panel1);
		primary.add(panel2);
		frame.getContentPane().add(primary);
		frame.pack();
		frame.setVisible(true);
		
	}
	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new HaysPolygons();
			}
		});
	}
	private static class MyPanel extends JPanel {
		private Polygon poly;
		int x[] = {250, 0, 500};
		Graphics g;
		int y[] = {0, 500, 500};
		int count = 0;
		public MyPanel() {
			super();
		}
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			this.g= g;

			this.poly = new Polygon(x,y,3);
			g.fillPolygon(this.poly);
		}
		public Polygon getPoly() {
			return poly;
		}
		public void drawTriangle(Polygon poly) {
			Point a = new Point();
			a.x = poly.xpoints[0];
			a.y = poly.ypoints[0];
			Point b = new Point();
			b.x = poly.xpoints[1];
			b.y = poly.ypoints[1];
			Point c = new Point();
			c.x = poly.xpoints[2];
			c.y = poly.ypoints[2];
			
			Point finalA = midPoint(a,b);
			Point finalB = midPoint(b,c);
			Point finalC = midPoint(c,a);
			int xpts[] = {finalA.x, finalB.x, finalC.x};
			int ypts[] = {finalA.y, finalB.y, finalC.y};
			Polygon newPoly = new Polygon(xpts, ypts, 3);
			if (this.count%2 == 0) {
				g.setColor(Color.red);
				
			}else {
				g.setColor(Color.white);
			}
			this.poly = newPoly;
			g.fillPolygon(newPoly);
			count++;

		}
		public Point midPoint(Point p1, Point p2) {
			Point a = new Point();
			a.x = ((p1.x + p2.x)/2);
			a.y = ((p1.y + p2.y)/2);
			return a;

			
			
		}
		
	}

}
