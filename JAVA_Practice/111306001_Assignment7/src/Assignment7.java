import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Assignment7 {
	
	static OXGameManager manager = new OXGameManager();
	static JButton[] btns = new JButton[9];
	static JLabel score = new JLabel(String.format("O: %d ; X: %d", 0, 0));
	static JButton reStart = new JButton("ReStart");
	static JButton finish = new JButton("Finish");
	
	public static void main(String[] args) {
		
		JFrame f = new JFrame("Frame");
		f.setLayout(new GridLayout(4, 3));
		
		score.setFont(new Font("Arial", Font.PLAIN, 40));
		reStart.setFont(new Font("Arial", Font.PLAIN, 40));
		finish.setFont(new Font("Arial", Font.PLAIN, 40));
		
		class ReStrartListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < btns.length; i++) {
					btns[i].setText(Integer.toString(i));
					btns[i].setEnabled(true);
					manager.initialize();
				}
			}
		}
		class FinishListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				f.setVisible(false);
				manager.finish();
				manager.initialize();
			}
		}
		for (int i=0; i<9; i++) {
			JButton btn = new JButton();
			int index = i;
			btn.setText(Integer.toString(i));
			btn.setFont(new Font("Arial", Font.PLAIN, 50));
			
			class CheckerboardListener implements ActionListener{
				public void actionPerformed(ActionEvent e) {
					
					btn.setText(manager.play(index));
					btn.setEnabled(false);
					String win = manager.checkWin();
					if(win == "O") {
						score.setText(String.format("O: %d ; X: %d", manager.getScoreO(), manager.getScoreX()));
						for(int j = 0; j < btns.length; j++) {
							btns[j].setEnabled(false);
						}
					}
					else if(win == "X") {
						score.setText(String.format("O: %d ; X: %d", manager.getScoreO(), manager.getScoreX()));
						for(int j = 0; j < btns.length; j++) {
							btns[j].setEnabled(false);
						}
					}
				}
			}
			btns[i] = btn;
			btns[i].addActionListener(new CheckerboardListener());
		}
		
		f.add(score);
		f.add(reStart);
		f.add(finish);
		
		for (int i=0; i<9; i++) { 
			f.add(btns[i]); 
		}
		
		finish.addActionListener(new FinishListener());
		reStart.addActionListener(new ReStrartListener());
		
		f.setSize(450, 300);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}