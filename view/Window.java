/**
 *
 * @author Khor Kia Kin
 */

package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;

// View of MVC.
// Singleton class - To ensure only one instance of JFrame is created.
// The main frame.
public class Window {
	private static Window instance = null;
	private JFrame mainFrame;
	
	private Window() {
		mainFrame = new JFrame("Tank War");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(768, 720);
		mainFrame.setMinimumSize(new Dimension(768, 720));
		mainFrame.setLocationRelativeTo(null); // Center the frame
		mainFrame.setVisible(true);
		mainFrame.setResizable(true);
		mainFrame.setLayout(new FlowLayout());
		mainFrame.addComponentListener(new ResizeListener());
	}
	
	public static Window getInstance() {
		if (instance == null)
			instance = new Window();
		return instance;
	}
	
	private void refreshFrame() {
		mainFrame.validate();
		mainFrame.repaint();
	}
	
	public void addPanel(JPanel panel) {
		mainFrame.add(panel);
		refreshFrame();
	}
	
	public void removePanel(JPanel panel) {
		mainFrame.remove(panel);
		refreshFrame();
	}

	private class ResizeListener implements ComponentListener {
		@Override
		public void componentHidden(ComponentEvent e) {}
		@Override
		public void componentMoved(ComponentEvent e) {}
		@Override
		public void componentShown(ComponentEvent e) {}
		@Override
		public void componentResized(ComponentEvent e) {
            Dimension currentDimesion = mainFrame.getSize();
            Dimension minDimension = mainFrame.getMinimumSize();
            if(currentDimesion.width < minDimension.width)
                currentDimesion.width = minDimension.width;
            if(currentDimesion.height < minDimension.height)
                currentDimesion.height = minDimension.height;
            mainFrame.setSize(currentDimesion);
        }
	}
}