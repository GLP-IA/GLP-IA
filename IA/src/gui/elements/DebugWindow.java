package gui.elements;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class DebugWindow extends JFrame{

	private static final long serialVersionUID = 1L;
	private JTextArea debugArea= new JTextArea(25,70);
	
	public DebugWindow() {
		init();
	}
	
	public void init() {
		debugArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(debugArea);
		this.getContentPane().add(scrollPane);
		
		this.setTitle("Debug");
		this.getContentPane().setLayout(new FlowLayout());
		this.pack();
		this.setVisible(true);
	}
	
	public void setText(String r) {
		debugArea.setText(r);
	}
}
