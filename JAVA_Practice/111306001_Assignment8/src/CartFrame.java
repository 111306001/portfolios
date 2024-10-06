import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class CartFrame extends JFrame{
	
	private static final int FRAME_WIDTH = 400;
	private static final int FRAME_HEIGHT = 300;
	private static final int FIELD_WIDTH = 10;
	private ItemManager itemManager;
	private Order order;
	private JPanel itemPanel, quantityPanel, operatePanel, overallPanel;
	private JLabel itemLabel, quantityLabel;
	private JComboBox itemCombo;
	private JTextField quantityField;
	private JButton addButton, buyButton;
	private JTextArea infoArea;
	
	public CartFrame() {
		this.setTitle("Shopping cart");
		this.setSize(this.FRAME_WIDTH, this.FRAME_HEIGHT);
		createItemComp();
		createButton();
		createInfoArea();
		createPanel();
	}
	public void createItemComp() {
		itemLabel = new JLabel("Item");
		quantityLabel = new JLabel("Quantity");
		itemCombo = new JComboBox();
		quantityField = new JTextField("0", FIELD_WIDTH);
	}
	public void createButton() {
		itemManager = new ItemManager();
		order = new Order();
		itemManager.addItem();	
		for(int i = 0; i < itemManager.getItems().size(); i++) {
			itemCombo.addItem(itemManager.getItems().get(i).getName());
		}
		addButton = new JButton("Add to cart");
		class addListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				if(Integer.parseInt(quantityField.getText()) <= 0) {
					JOptionPane.showMessageDialog(overallPanel, "The number must above than 0", "Error", JOptionPane.ERROR_MESSAGE );
				}
				else {
					for(Item item : (itemManager.getItems())) {
						if(item.getName().equals(itemCombo.getSelectedItem()) == true) {
							order.addOrder(item, Integer.parseInt(quantityField.getText()));
						}
					}
					infoArea.setText("Action completed!");
				}
			}
		}
		addButton.addActionListener(new addListener());
		
		buyButton = new JButton("Check out");
		class buyListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				infoArea.setText(String.format("%10s", "Item") + String.format("%10s", "Price") + String.format("%10s", "Quantity") + "\n"
					+ order.orderList() + "-----------------------------------------" + "\n" + "The total amount:" + order.calTotalAmount());
			}
		}
		buyButton.addActionListener(new buyListener());
	}
	public void createInfoArea(){
		infoArea = new JTextArea(20,30);
	}
	public void createPanel() {
		itemPanel = new JPanel();
		itemPanel.add(itemLabel);
		itemPanel.add(itemCombo);
		quantityPanel = new JPanel();
		quantityPanel.add(quantityLabel);
		quantityPanel.add(quantityField);
		operatePanel = new JPanel(new GridLayout(2, 2));
		operatePanel.add(itemPanel);
		operatePanel.add(quantityPanel);
		operatePanel.add(addButton);
		operatePanel.add(buyButton);
		overallPanel = new JPanel(new BorderLayout());
		overallPanel.add(operatePanel, BorderLayout.NORTH);
		overallPanel.add(infoArea, BorderLayout.CENTER);
		this.add(overallPanel);
	}
}
