import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class CalcFrame extends JFrame{
	
	private JButton btnEqual;
	private JTextField textFieldA, textFieldB;
	private JRadioButton rbtnAdd, rbtnSub, rbtnMul, rbtnDiv;
	private JTextArea outputArea;
	
	//constructor
	public CalcFrame() {
		outputArea = new JTextArea(8, 12);
		outputArea.setEditable(false);
		createTextField();
		createButton();
		createRbtn();
		createLayout();
		setSize(600, 200);
	}
	
	private void createTextField() {
		
		textFieldA = new JTextField(10);
		textFieldB = new JTextField(10);
		
	}
	private void createRbtn() {
		
		rbtnAdd = new JRadioButton("+");
		rbtnSub = new JRadioButton("-");
		rbtnMul = new JRadioButton("*");
		rbtnDiv = new JRadioButton("/");
		
		ButtonGroup group = new ButtonGroup();
		group.add(rbtnAdd);
		group.add(rbtnSub);
		group.add(rbtnMul);
		group.add(rbtnDiv);
		
	}
	private void createButton(){
		btnEqual = new JButton("=");
		class calListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				
				double a = Double.parseDouble(textFieldA.getText());
				double b = Double.parseDouble(textFieldB.getText());
				
				if(rbtnAdd.isSelected()) {
					outputArea.append(getResult(a, rbtnAdd.getText(), b));
				}
				else if(rbtnSub.isSelected()) {
					outputArea.append(getResult(a, rbtnSub.getText(), b));
				}
				else if(rbtnMul.isSelected()) {
					outputArea.append(getResult(a, rbtnMul.getText(), b));
				}
				else if(rbtnDiv.isSelected()) {
					outputArea.append(getResult(a, rbtnDiv.getText(), b));
				}
			}
		}
		btnEqual.addActionListener(new calListener());

	}
	private String getResult(double a, String op, double b) {

		double cal  = 0;
		if(op.equals("+")) {
			cal = a + b;
		}
		else if(op.equals("-")) {
			cal = a - b;
		}
		else if(op.equals("*")) {
			cal = a * b;
		}
		else if(op.equals("/")) {
			cal = a / b;
		}
		String result =  String.format("%.2f",a) + op + String.format("%.2f",b) + btnEqual.getText() + String.format("%.2f",cal) + "\n";
		return result;
	}
	private void createLayout(){
		
		JPanel flow_panel = new JPanel();
		JPanel rbtn_panel = new JPanel(new GridLayout(4,1));
		rbtn_panel.add(this.rbtnAdd);
		rbtn_panel.add(this.rbtnSub);
		rbtn_panel.add(this.rbtnMul);
		rbtn_panel.add(this.rbtnDiv);
		flow_panel.add(this.textFieldA);
		flow_panel.add(rbtn_panel);
		flow_panel.add(this.textFieldB);
		flow_panel.add(this.btnEqual);
		flow_panel.add(new JScrollPane(outputArea));
		add(flow_panel);
		
	}
}