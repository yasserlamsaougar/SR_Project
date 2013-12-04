package client;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;

public class LobbyScreen extends JPanel {

	private JList listOfPlayers;
	private DefaultListModel<String> listModel;
	private JToggleButton readyButton;
	private JButton quitButton;
	public LobbyScreen(int gridSize, int cellSize, GameManager g) {
		super(new BorderLayout());
		listModel = new DefaultListModel<>();
		listOfPlayers = new JList<>(listModel);
		listOfPlayers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(listOfPlayers);
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		readyButton = new JToggleButton("Ready");
		quitButton = new JButton("Quit");
		buttonPane.add(readyButton);
		buttonPane.add(quitButton);
		
		readyButton.addItemListener(new LobbyInputCatcher().new ReadyCatcher(g, readyButton));
		quitButton.addMouseListener(new LobbyInputCatcher().new CancelCatcher(g));
		
		setPreferredSize(new Dimension((gridSize + 3) * cellSize, cellSize
				* (gridSize + 3)));
	}
	
	public void addToModel(String userName) {
		listModel.addElement(userName);
	}
	
	public void removeFromModel(String userName) {
		listModel.removeElement(userName);
	}

}
