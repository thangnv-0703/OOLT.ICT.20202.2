package Tree.Screen;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
import javax.swing.JFrame;

import Tree.Node;
import Tree.Binary.BinaryTree;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;

public class TreeScreen extends JFrame {
	private Node root;
	BinaryTree binaryTree;
	public TreeScreen(BinaryTree binaryTree) {
		// TODO Auto-generated constructor stub
		super();
		this.binaryTree=binaryTree;
		JFXPanel fxPanel = new JFXPanel();
		this.add(fxPanel);
		this.setSize(3048, 1500);
		this.setTitle("Tree");

		this.setVisible(true);
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				try {
					FXMLLoader loader = new FXMLLoader(
							getClass().getResource("/Tree/Screen/screen.fxml"));
					ScreenController controller = new ScreenController(binaryTree);
					loader.setController(controller);
					Parent root = loader.load();
					fxPanel.setScene(new Scene(root));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
}