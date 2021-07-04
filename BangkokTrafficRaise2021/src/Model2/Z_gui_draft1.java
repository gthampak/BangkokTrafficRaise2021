package Model2;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Z_gui_draft1 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Z_gui_draft1 window = new Z_gui_draft1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Z_gui_draft1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		//JScrollPane scrollPane = new JScrollPane(frame); 
		frame.setBounds(100, 100, 2020, 1180);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		ArrayList<JTextArea> tas = new ArrayList<>();
		ArrayList<JComboBox> cbs = new ArrayList<>();
		ArrayList<JScrollPane> sps = new ArrayList<>();
		
		Main main = new Main();
		RoadNetwork RN = main.caseStudy1();
		
		int cbx = 210;
		int cby = 20;
		
		int tax = 210;
		int tay = 90;
		
		for(int i = 0; i < 6; i++) {
			
			for(int j = 0; j < 6; j++) {
				
				JComboBox cb = new JComboBox();
				cb.setBounds(cbx*i, (cby+tay)*j, cbx, cby);
				
				cb.addItem(null);
				
				for(Road r : RN.allRoads()) {
					cb.addItem(r);
				}
				
				JTextArea ta = new JTextArea();
				ta.setBounds(cbx*i, (cby+tay)*j + cby, tax, tay);
				
				JScrollPane sp = new JScrollPane(ta);
				sp.setBounds(cbx*i, (cby+tay)*j + cby, tax, tay);
				
				tas.add(ta);
				cbs.add(cb);
				sps.add(sp);
				
			}
			
		}
		
		for(int i = 0; i < tas.size(); i++) {
			
			JComboBox cb = cbs.get(i);
			JTextArea ta = tas.get(i);
			JScrollPane sp = sps.get(i);
			
			if(cb.getSelectedItem() != null) {
				Road r = (Road) cb.getSelectedItem();
				ta.setText(r.printRoad1());
			}
			
			frame.getContentPane().add(cb);
			frame.getContentPane().add(sp);
		}
		
		JButton btnNewButton = new JButton();
		btnNewButton.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				
				for(int i = 0; i < tas.size(); i++) {
					
					JComboBox cb = cbs.get(i);
					JTextArea ta = tas.get(i);
					JScrollPane sp = sps.get(i);
					
					if(cb.getSelectedItem() != null) {
						Road r = (Road) cb.getSelectedItem();
						ta.setText(r.printRoad1());
					}
					
					frame.getContentPane().add(cb);
					frame.getContentPane().add(sp);
				}
				
			}
		});
		btnNewButton.setBounds(210*6, 0, 20, 20);
		frame.getContentPane().add(btnNewButton);
		
		
	}
}
