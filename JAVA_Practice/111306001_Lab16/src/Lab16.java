import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Lab16 {
	
	public static void main(String[] args) {
		
		Company company = new Company("NCCU");
		company.addFixedAsset(new Machine(8, 420, 1000));
		company.addFixedAsset(new Vehicle(6, 300, 1000));
		
		JFrame frame = new JFrame();
		JLabel yearLabel = new JLabel("Year");
		JLabel year = new JLabel(String.format("%d", company.getYear()));
		JLabel depreciationLabel = new JLabel("This year depreciation");
		JLabel depreciation = new JLabel(String.format("%.2f", company.getDepreciation()));
		JLabel valueLabel = new JLabel("Book value");
		JLabel bookValue = new JLabel(String.format("%.2f", company.getBookValue()));
		JButton button1 = new JButton("Last year");
		JButton button2 = new JButton("Next Year");
		
		frame.setTitle(company.getName());
		frame.setLayout(new GridLayout(4, 2));
		frame.add(yearLabel);
		frame.add(year);
		frame.add(depreciationLabel);
		frame.add(depreciation);
		frame.add(valueLabel);
		frame.add(bookValue);
		frame.add(button1);
		frame.add(button2);
		
		class LastListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				company.setYear(company.getYear() - 1);
				year.setText(String.format("%d", company.getYear()));
				depreciation.setText(String.format("%.2f", company.getDepreciation()));
				bookValue.setText(String.format("%.2f", company.getBookValue()));
			}
		}
		
		class NextListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				company.setYear(company.getYear() + 1);
				year.setText(String.format("%d", company.getYear()));
				depreciation.setText(String.format("%.2f", company.getDepreciation()));
				bookValue.setText(String.format("%.2f", company.getBookValue()));
			}
		}
		
		button1.addActionListener(new LastListener());
		button2.addActionListener(new NextListener());
		frame.setSize(450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

