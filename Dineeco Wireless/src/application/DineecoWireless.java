/*
 * NAME: Neeco Fabian
 * DATE: 10 March 2019
 * COURSE CODE: ICS4UQ-01
 * PROGRAM: Dineeco Wireless
 */


package application;

import java.util.Optional;

// Import packages
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;


public class DineecoWireless extends Application {

	// Declare global variables
	private int numApps;
	private String phoneColor, storageCapacity, data, local, can, us;
	private double storagePriceTotal, appPriceTotal, dataPrice, localPrice, canPrice, usPrice, planPriceTotal, subtotal, discount, hst, grandTotal;
	private TextField[] txtSectionPrices, txtTotals;
	private Label lblPhone;
	private ImageView[] iphoneColors;
	private Button[] btnStorage, btnColumn;
	private CheckBox[] chkApps;
	private ComboBox<String> cboData, cboLocal, cboCan, cboUs;
	private RadioButton[] rbtnColors;

	
	public void start(Stage primaryStage) {
		try {

			// Initialize variables (defaults)
			numApps = 0;
			phoneColor = "Red";
			storageCapacity = "";
			storagePriceTotal = 0;
			appPriceTotal = 0;
			dataPrice = 0;
			localPrice = 0;
			canPrice = 0;
			usPrice = 0;
			planPriceTotal = 0;
			subtotal = 0;
			discount = 0;
			hst = 0;
			grandTotal = 0;
			data = "";
			local = "";
			can = "";
			us = "";
			Alert alert = new Alert(AlertType.NONE);
			
		
			// Declare and initialize GridPane, set padding around grid and space between each cell of grid
			GridPane grid = new GridPane();
			grid.setPadding(new Insets(10, 10, 10, 10));
			grid.setHgap(10);
			grid.setVgap(10);

			// Declare and intialize scene, set properties
			Scene scene = new Scene(grid, 985, 600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			// Declare and initialize title image
			ImageView imageTitle = new ImageView(new Image("file:dineeco.png", 500, 0, true, true));

			// Set properties of title cell
			GridPane.setColumnSpan(imageTitle, 3);
			GridPane.setHalignment(imageTitle, HPos.CENTER);
			grid.add(imageTitle, 0, 0);

			// Set the size of columns
			ColumnConstraints colLeft = new ColumnConstraints(315); 
			ColumnConstraints colMiddle = new ColumnConstraints(315); 
			ColumnConstraints colRight = new ColumnConstraints(315); 
			grid.getColumnConstraints().addAll(colLeft, colMiddle, colRight);


			//----------------------------------

			// Section Prices

			// Declare and initialize TextField array
			txtSectionPrices = new TextField[3];

			// Declare and initialize HBox object, set properties
			HBox hboxSectionPrices = new HBox(160);
			hboxSectionPrices.setMaxWidth(scene.getWidth());
			hboxSectionPrices.setPadding(new Insets(0, 75, 0, 75));
			for (int i = 0; i < 3; i++)
			{
				txtSectionPrices[i] = new TextField();
				txtSectionPrices[i].setEditable(false);
				txtSectionPrices[i].setPrefSize(175, 35);
				txtSectionPrices[i].setAlignment(Pos.CENTER);
				txtSectionPrices[i].setStyle("-fx-font-family: Arial; -fx-font-weight: bold");
				txtSectionPrices[i].setText("$0.00");				
			}

			hboxSectionPrices.getChildren().addAll(txtSectionPrices);

			// Set GridPane properties
			GridPane.setColumnSpan(hboxSectionPrices, 3);
			GridPane.setHalignment(hboxSectionPrices, HPos.CENTER);
			grid.add(hboxSectionPrices, 0, 3);
			
			//----------------------------------
			// Column of buttons

			// Initialize and array of buttons
			btnColumn = new Button[4];
			btnColumn[0] = new Button("CALCULATE");
			btnColumn[1] = new Button("CHECKOUT");
			btnColumn[2] = new Button("CLEAR");
			btnColumn[3] = new Button("EXIT");


			// Set button properties
			for(int i =0; i < btnColumn.length; i++)
			{
				btnColumn[i].setPrefSize(165, 35);
				
			}
			// Disable Checkout by default
			btnColumn[1].setDisable(true);

			// Check is user presses calculate, call calculate method
			btnColumn[0].setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					calculate();
				}
			});

			// Check is user presses checkout
			btnColumn[1].setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					
					// Set Alert object properties
					alert.setTitle("Order Summary");
					alert.setHeaderText(null);
					alert.setAlertType(AlertType.CONFIRMATION);
					
					// Show confirmation message
					alert.setContentText("PHONE:\n"
							+ "iPhone X, " + phoneColor + ", " + storageCapacity 
							+ "\n\nMONTHLY PLAN:\n"
							+ data + local + can + us
							+ "\nIs this order correct?");
					alert.getButtonTypes().clear();
					alert.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);

					// Show the Alert, wait for a response
					Optional<ButtonType> result = alert.showAndWait();

					// Check if user wants to exit, show goodbye message
					if (result.get() == ButtonType.YES)
					{
						// Set Alert Properties
						alert.setContentText("Thank you for choosing Dineeco!");
						alert.setTitle("Dineeco Wireless");
						alert.setHeaderText(null);
						alert.setAlertType(AlertType.INFORMATION);
						alert.getButtonTypes().clear();
						alert.getButtonTypes().add(ButtonType.OK);
						
						// Wait for user to click ok, close program
						alert.showAndWait();
						System.exit(0);
					}	
					
				}
			});

			// Check is user presses clear
			btnColumn[2].setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					clear();
			
				}
			});

			// Check is user presses exit
			btnColumn[3].setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {

					// Set Alert object properties
					alert.setTitle("Dineeco Wireless");
					alert.setHeaderText(null);
					alert.setAlertType(AlertType.CONFIRMATION);
					alert.setContentText("Are you sure you want to exit?");
					alert.getButtonTypes().clear();
					alert.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);

					// Show the Alert, wait for a response
					Optional<ButtonType> result = alert.showAndWait();

					// Check if user wants to exit, show goodbye message
					if (result.get() == ButtonType.YES)
					{
						// Set Alert Properties
						alert.setContentText("Thank you for choosing Dineeco!");
						alert.setTitle("Dineeco Wireless");
						alert.setHeaderText(null);
						alert.setAlertType(AlertType.INFORMATION);
						alert.getButtonTypes().clear();
						alert.getButtonTypes().add(ButtonType.OK);
						
						// Wait for user to click ok, close program
						alert.showAndWait();
						System.exit(0);
					}					
				}
			});


			// Declare, initialize and set properties of Vbox
			VBox vboxBtnColumn = new VBox(10);
			vboxBtnColumn.setAlignment(Pos.TOP_CENTER);
			vboxBtnColumn.getChildren().addAll(btnColumn);

			// Add to grid
			grid.add(vboxBtnColumn, 2, 4);


			//----------------------------------
			// Phone order
	
			// Initialize and array of iphone ImageView objects
			iphoneColors = new ImageView[] { 
					new ImageView(new Image("file:iphone_red.jpg", 140, 0, true, true)),
					new ImageView(new Image("file:iphone_blue.jpg", 140, 0, true, true)),
					new ImageView(new Image("file:iphone_white.jpg", 140, 0, true, true)),
					new ImageView(new Image("file:iphone_black.jpg", 140, 0, true, true))
			};
			
			// Initialize Label object for the phone picture
			lblPhone = new Label();
			
			// Set initial image to the red iphone, is changeable
			lblPhone.setGraphic(iphoneColors[0]);
			
			// Initialize an array of RadioButtons
			rbtnColors = new RadioButton[4];
			rbtnColors[0] = new RadioButton("Red");
			rbtnColors[1] = new RadioButton("Blue");
			rbtnColors[2] = new RadioButton("White");
			rbtnColors[3] = new RadioButton("Black");
			
			// Set red as deafault colour
			rbtnColors[0].setSelected(true);
			
			// Group RadioButtons together
			ToggleGroup toggleGroup = new ToggleGroup();
			toggleGroup.getToggles().addAll(rbtnColors);
			
			// Set the phone color to the user's preference
			rbtnColors[0].setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					phoneColor = "Red";
					lblPhone.setGraphic(iphoneColors[0]);
				}
			});
			
			rbtnColors[1].setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					phoneColor = "Blue";
					lblPhone.setGraphic(iphoneColors[1]);
				}
			});
			
			rbtnColors[2].setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					phoneColor = "White";
					lblPhone.setGraphic(iphoneColors[2]);
				}
			});
			
			rbtnColors[3].setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					phoneColor = "Black";
					lblPhone.setGraphic(iphoneColors[3]);
				}
			});
			
			
			// Initialize an array of buttons
			btnStorage = new Button[4];
			btnStorage[0] = new Button("64 GB");
			btnStorage[1] = new Button("128 GB");
			btnStorage[2] = new Button("256 GB");
			btnStorage[3] = new Button("512 GB");
			
			// Set button and radioButton properties
			for(int i = 0; i < 4; i++)
			{
				btnStorage[i].setPrefSize(75, 35);
				btnStorage[i].setStyle("-fx-color: white");
				rbtnColors[i].setStyle("-fx-color: white");
			}
			
			// Store the user's selected capacity and its price; change outputted the total, disable current button, enable others
			btnStorage[0].setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					storageCapacity = "64 GB";
					storagePriceTotal = 1379.99;
					txtSectionPrices[0].setText("$1,379.99");
					btnStorage[0].setDisable(true);
					btnStorage[1].setDisable(false);
					btnStorage[2].setDisable(false);
					btnStorage[3].setDisable(false);
					btnColumn[1].setDisable(false);
				}
			});
			
			btnStorage[1].setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					storageCapacity = "128 GB";
					storagePriceTotal = 1589.99;
					txtSectionPrices[0].setText("$1,589.99");
					btnStorage[0].setDisable(false);
					btnStorage[1].setDisable(true);
					btnStorage[2].setDisable(false);
					btnStorage[3].setDisable(false);
					btnColumn[1].setDisable(false);
				}
			});
			
			btnStorage[2].setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					storageCapacity = "256 GB";
					storagePriceTotal = 1799.99;
					txtSectionPrices[0].setText("$1,799.99");
					btnStorage[0].setDisable(false);
					btnStorage[1].setDisable(false);
					btnStorage[2].setDisable(true);
					btnStorage[3].setDisable(false);
					btnColumn[1].setDisable(false);
				}
			});
			
			btnStorage[3].setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					storageCapacity = "512 GB";
					storagePriceTotal = 1999.99;
					txtSectionPrices[0].setText("$1,999.99");
					btnStorage[0].setDisable(false);
					btnStorage[1].setDisable(false);
					btnStorage[2].setDisable(false);
					btnStorage[3].setDisable(true);
					btnColumn[1].setDisable(false);
				}
			});
			
			
			// Declare and initialize GridPane for the radiobuttons and buttons, set properties
			GridPane smallGrid = new GridPane();
			smallGrid.setPadding(new Insets(7, 0, 7, 0));
			smallGrid.setHgap(10);
			smallGrid.setVgap(10);
			
			smallGrid.add(rbtnColors[0], 0, 0);
			smallGrid.add(rbtnColors[1], 0, 1);
			smallGrid.add(rbtnColors[2], 0, 2);
			smallGrid.add(rbtnColors[3], 0, 3);
			smallGrid.add(btnStorage[0], 1, 0);
			smallGrid.add(btnStorage[1], 1, 1);
			smallGrid.add(btnStorage[2], 1, 2);
			smallGrid.add(btnStorage[3], 1, 3);
			
			// Declare and Initialize BorderPane object to divide the titled pane, set properties
			BorderPane bPaneDivider = new BorderPane();
			
			BorderPane.setAlignment(lblPhone, Pos.CENTER);
			bPaneDivider.setLeft(lblPhone);
			bPaneDivider.setRight(smallGrid);
			
			// Declare and initialize TitledPane for the left side, set properties
			TitledPane tPaneLeft = new TitledPane();
			tPaneLeft.setText("SELECT A PHONE");
			tPaneLeft.setCollapsible(false);
			tPaneLeft.setContent(bPaneDivider);
			tPaneLeft.setAlignment(Pos.TOP_LEFT);
			tPaneLeft.setStyle("-fx-color: red; -fx-text-fill: white; -fx-box-border: lightgray");
			
			// Add to gridpane
			grid.add(tPaneLeft, 0, 1);
			
		
			
			//----------------------------------
			// Declare and initialize TilePane object, set properties
			TilePane tiledApps = new TilePane(Orientation.HORIZONTAL, 15, 0); 
			tiledApps.setPrefColumns(4);
			tiledApps.setPrefRows(3);

			// Declare and Initialize array of CheckBoxes and StackPanes for 12 apps
			chkApps = new CheckBox[12];
			StackPane[] stackApps = new StackPane[12];

			// Declare and Initialize array of ImageView for app icons
			ImageView[] appIcons = new ImageView[] { 
					new ImageView(new Image("file:app0.png", 42, 0, true, true)),
					new ImageView(new Image("file:app1.png", 42, 0, true, true)),
					new ImageView(new Image("file:app2.png", 42, 0, true, true)),
					new ImageView(new Image("file:app3.png", 42, 0, true, true)),
					new ImageView(new Image("file:app4.png", 42, 0, true, true)),
					new ImageView(new Image("file:app5.png", 42, 0, true, true)),
					new ImageView(new Image("file:app6.png", 42, 0, true, true)),
					new ImageView(new Image("file:app7.png", 42, 0, true, true)),
					new ImageView(new Image("file:app8.png", 42, 0, true, true)),
					new ImageView(new Image("file:app9.png", 42, 0, true, true)),
					new ImageView(new Image("file:app10.png", 42, 0, true, true)),
					new ImageView(new Image("file:app11.png", 42, 0, true, true))			
			};

			// Loop through all apps
			for(int i = 0; i < chkApps.length; i++)
			{
				// Initialize apps, set size, call click method when checkbox is clicked
				chkApps[i] = new CheckBox();
				chkApps[i].setPrefSize(61, 61);
				chkApps[i].setStyle("-fx-color: white");

				int index = i;
				chkApps[i].setOnAction(e -> chkApps_Click(chkApps[index]));

				stackApps[i] = new StackPane();

				stackApps[i].getChildren().addAll(chkApps[i], appIcons[i]);
				StackPane.setAlignment(appIcons[i], Pos.CENTER_RIGHT);
			}

			// Add CheckBoxes to TiledPane
			tiledApps.getChildren().addAll(stackApps);

			// Declare and initialize TitledPane for the middle
			TitledPane tPaneMiddle = new TitledPane();
			tPaneMiddle.setText("SELECT APPLICATIONS");
			tPaneMiddle.setCollapsible(false);
			tPaneMiddle.setContent(tiledApps);

			//BACKGROUND COLOR
			tPaneMiddle.setStyle("-fx-color: red; -fx-text-fill: white; -fx-box-border: lightgray");


			// Add TitledPane to GridPane
			grid.add(tPaneMiddle, 1, 1);

			
			//----------------------------------
			//Monthly Plan
			
		
			// Declare and initialize array of labels, set size
			Label[] lblPlans = new Label[4];
			lblPlans[0] = new Label("Data Plan (GB):");
			lblPlans[1] = new Label("Local (mins):");
			lblPlans[2] = new Label("Canada (mins):");
			lblPlans[3] = new Label("U.S. (mins):");
			
			for (int i = 0; i < 4; i++)
			{
				lblPlans[i].setPrefSize(100, 35);
			}
			
			//Declare and Initialize TextField objects
			TextField[] txtPlans = new TextField[4];
			
			for (int i = 0; i < 4; i++)
			{
				txtPlans[i] = new TextField();
				txtPlans[i].setPrefSize(75, 35);
				txtPlans[i].setEditable(false);
				txtPlans[i].setText("0.00");
			}
			
			// Declare, Initialize, and add items to 4 ComboBoxes
			cboData = new ComboBox<String>();
			cboData.getItems().addAll("0", "1 GB", "2 GB", "5 GB", "10 GB", "Unlimited");
			cboData.setValue("0");
			cboData.setPrefHeight(35);
			cboData.setStyle("-fx-color: white");
			
			// Check user's choice, update price, repeat for all ComboBoxes
			cboData.setOnAction(new EventHandler<ActionEvent>() { 
				public void handle(ActionEvent e) {
					if (cboData.getValue().equals("1"))
					{
						dataPrice = 10.00;
						data = "Data: \t" + "1 GB\n";
						
					}
					else if (cboData.getValue().equals("2"))
					{
						dataPrice = 20.00;
						data = "Data: \t" + "2 GB\n";
					}
					else if (cboData.getValue().equals("5"))
					{
						dataPrice = 30.00;
						data = "Data: \t" + "5 GB\n";
					}
					else if (cboData.getValue().equals("10"))
					{
						dataPrice = 60.00;
						data = "Data: \t" + "10 GB\n";
					}
					else if (cboData.getValue().equals("Unlimited"))
					{
						dataPrice = 80.00;
						data = "Data: \t" + "Unlimited\n";
					}
					else
					{
						dataPrice = 0;
						data = " ";
					}
					
					// Update the TextField with price
					txtPlans[0].setText(String.format("%.2f", dataPrice));
					
					// Call method to update section price
					updateSectionPrice();
				} 
			});

			cboLocal = new ComboBox<String>();
			cboLocal.getItems().addAll("0", "250", "1000", "Unlimited");
			cboLocal.setValue("0");
			cboLocal.setPrefHeight(35);
			cboLocal.setStyle("-fx-color: white");
			cboLocal.setOnAction(new EventHandler<ActionEvent>() { 
				public void handle(ActionEvent e) {
					if (cboLocal.getValue().equals("250"))
					{
						localPrice = 20.00;
						local = "Local (mins): \t" + "250\n";
					}
					else if (cboLocal.getValue().equals("1000"))
					{
						localPrice = 30.00;
						local = "Local (mins): \t" + "1000\n";
					}
					else if (cboLocal.getValue().equals("Unlimited"))
					{
						localPrice = 40.00;
						local = "Local (mins): \t" + "Unlimited\n";
					}
					else
					{
						localPrice = 0;
						local = " ";
					}
					
					// Update the TextField with price
					txtPlans[1].setText(String.format("%.2f", localPrice));
					
					// Call method to update section price
					updateSectionPrice();
				} 
			});
			
			cboCan = new ComboBox<String>();
			cboCan.getItems().addAll("0", "250", "1000", "Unlimited");
			cboCan.setValue("0");
			cboCan.setPrefHeight(35);
			cboCan.setStyle("-fx-color: white");
			cboCan.setOnAction(new EventHandler<ActionEvent>() { 
				public void handle(ActionEvent e) {
					if (cboCan.getValue().equals("250"))
					{
						canPrice = 10.00;
						can = "Canada Long-Distance (mins): \t" + "250\n";
					}
					else if (cboCan.getValue().equals("1000"))
					{
						canPrice = 20.00;
						can = "Canada Long-Distance (mins): \t" + "1000\n";
					}
					else if (cboCan.getValue().equals("Unlimited"))
					{
						canPrice = 30.00;
						can = "Canada Long-Distance (mins): \t" + "Unlimited\n";
					}
					else
					{
						canPrice = 0;
						can = " ";
					}
					
					// Update the TextField with price
					txtPlans[2].setText(String.format("%.2f", canPrice));
					
					// Call method to update section price
					updateSectionPrice();
				} 
			});
			
			cboUs = new ComboBox<String>();
			cboUs.getItems().addAll("0", "250", "1000", "Unlimited");
			cboUs.setValue("0");
			cboUs.setPrefHeight(35);
			cboUs.setStyle("-fx-color: white");
			cboUs.setOnAction(new EventHandler<ActionEvent>() { 
				public void handle(ActionEvent e) {
					if (cboUs.getValue().equals("250"))
					{
						usPrice = 10.00;
						us = "U.S. Long-Distance (mins): \t" + "250\n";
					}
					else if (cboUs.getValue().equals("1000"))
					{
						usPrice = 20.00;
						us = "U.S. Long-Distance (mins): \t" + "1000\n";
					}
					else if (cboUs.getValue().equals("Unlimited"))
					{
						usPrice = 30.00;
						us = "U.S. Long-Distance (mins): \t" + "Unlimited\n";
					}
					else
					{
						usPrice = 0;
						us = " ";
					}
					
					// Update the TextField with price
					txtPlans[3].setText(String.format("%.2f", usPrice));
					
					// Call method to update section price
					updateSectionPrice();
				} 
			});			
			
			// Declare and Initialize VBox objects
			VBox vboxLblPlans = new VBox(10);
			VBox vboxCboPlans = new VBox(10);
			VBox vboxTxtPlans = new VBox(10);
			
			// Set margin around the VBoxes
			BorderPane.setMargin(vboxLblPlans, new Insets(7, 0, 7, 0));
			BorderPane.setMargin(vboxCboPlans, new Insets(7, 0, 7, 0));
			BorderPane.setMargin(vboxTxtPlans, new Insets(7, 0, 7, 0));
			
			// Add nodes to VBoxes
			vboxLblPlans.getChildren().addAll(lblPlans);
			vboxCboPlans.getChildren().addAll(cboData, cboLocal, cboCan, cboUs);
			vboxTxtPlans.getChildren().addAll(txtPlans);
			
			// Declare and initialize GridPane object
			BorderPane borderPanePlans = new BorderPane();
			borderPanePlans.setLeft(vboxLblPlans);
			borderPanePlans.setCenter(vboxCboPlans);
			borderPanePlans.setRight(vboxTxtPlans);
			
			// Declare and initialize TitledPane for the middle
			TitledPane tPaneRight = new TitledPane();
			tPaneRight.setText("SELECT A MONTHLY PLAN");
			tPaneRight.setCollapsible(false);
			tPaneRight.setContent(borderPanePlans);

			//BACKGROUND COLOR
			tPaneRight.setStyle("-fx-color: red; -fx-text-fill: white; -fx-box-border: lightgray");
			
			// Add to grid
			grid.add(tPaneRight, 2, 1);

			//----------------------------------

			// Two Apps free


			// Declare and Initialize variable for message, set style
			Label lblAppMessage = new Label("First two (2) apps are free!");
			lblAppMessage.setStyle("-fx-text-fill: red; -fx-font-family: Arial; -fx-font-weight: bold; -fx-font-size: 15;");

			// Set properties of appMessage cell
			GridPane.setColumnSpan(lblAppMessage, 3);
			GridPane.setHalignment(lblAppMessage, HPos.CENTER);
			grid.add(lblAppMessage, 0, 2);

		
			//----------------------------------

			// Payment Method
			
			// Declare and Initialize ImageView, GridPane objects for the payment method section
			ImageView paymentOptions = new ImageView(new Image("file:paymentoptions.jpg", 165, 0, true, true));
			
			GridPane.setHalignment(paymentOptions, HPos.CENTER);
			grid.add(paymentOptions, 0, 4);
			

			//----------------------------------
			//4 labels, 4 textboxes

			// Declare and initialize BorderPane, VBox, Label objects
			BorderPane borderPaneTotals = new BorderPane();
			VBox vboxLabelTotals = new VBox(10);
			Label lblSubtotal = new Label("SUBTOTAL:"); 
			Label lblDiscount = new Label("DISCOUNT:"); 
			Label lblHst = new Label("HST:"); 
			Label lblGrandTotal = new Label("GRAND TOTAL:"); 

			// Set Label Sizes
			lblSubtotal.setPrefSize(100, 35);
			lblDiscount.setPrefSize(100, 35);
			lblHst.setPrefSize(100, 35);
			lblGrandTotal.setPrefSize(100, 35);


			// Set margin around VBox, Add nodes to VBox, add it to the left side of BorderPane
			BorderPane.setMargin(vboxLabelTotals, new Insets(0,0,0,30));
			vboxLabelTotals.getChildren().addAll(lblSubtotal, lblDiscount, lblHst, lblGrandTotal);
			borderPaneTotals.setLeft(vboxLabelTotals);

			// Declare and initialize VBox object and TextField array
			VBox vboxTextfieldTotals = new VBox(10);
			txtTotals = new TextField[4];

			// Initialize TextField array, set properties
			for(int i =0; i < 4; i++)
			{
				txtTotals[i] = new TextField();
				txtTotals[i].setPrefSize(125, 35);
				txtTotals[i].setEditable(false);
				txtTotals[i].setAlignment(Pos.CENTER);
			}

			// Set margin around VBox, Add nodes to VBox, add it to the right side of BorderPane
			BorderPane.setMargin(vboxTextfieldTotals, new Insets(0,30,0,0));
			vboxTextfieldTotals.getChildren().addAll(txtTotals);
			borderPaneTotals.setRight(vboxTextfieldTotals);

			// Add BorderPane to grid
			grid.add(borderPaneTotals, 1, 4);

			// When user clicks the close button
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

				public void handle(WindowEvent e)
				{
					
					// Set Alert object properties
					alert.setTitle("Dineeco Wireless");
					alert.setHeaderText(null);
					alert.setAlertType(AlertType.CONFIRMATION);
					alert.setContentText("Are you sure you want to exit?");
					alert.getButtonTypes().clear();
					alert.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);

					// Show the Alert, wait for a response
					Optional<ButtonType> result = alert.showAndWait();

					// Check if user wants to exit, show goodbye message
					if (result.get() == ButtonType.YES)
					{
						// Set Alert Properties
						alert.setContentText("Thank you for choosing Dineeco!");
						alert.setTitle("Dineeco Wireless");
						alert.setHeaderText(null);
						alert.setAlertType(AlertType.INFORMATION);
						alert.getButtonTypes().clear();
						alert.getButtonTypes().add(ButtonType.OK);
						
						// Wait for user to click ok, close program
						alert.showAndWait();
						System.exit(0);
					}
					else
					{
						// Abort close request
						e.consume();
					}
				}
			});
			// Set Stage properties
			primaryStage.setTitle("Cell Phone Store");
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void chkApps_Click(CheckBox chk) {

		// Check if checkBox was selected, increase number of apps selected, decrease if not
		if (chk.isSelected())
		{
			numApps++;
		}
		else
		{
			numApps--;
		}

		// Add to the section total
		if (numApps > 2)
		{
			appPriceTotal = (numApps - 2) * 2.99;
		}
		else
		{
			appPriceTotal = 0;
		}
		
		// Output Section total
		txtSectionPrices[1].setText("$" + String.format("%.2f", appPriceTotal));
	}
	
	private void updateSectionPrice()
	{
		// Calculate price of monthly plan
		planPriceTotal = dataPrice + localPrice + canPrice + usPrice;
		
		// Ouput Section total
		txtSectionPrices[2].setText("$" + String.format("%.2f", planPriceTotal));
	}
	
	private void calculate()
	{
		// Calculate subtotal, discount, hst, grandTotal
		subtotal = storagePriceTotal + appPriceTotal + planPriceTotal;
		discount = planPriceTotal * 24;
		if (discount > storagePriceTotal)
		{
			discount = storagePriceTotal;
		}
		hst = 0.13 * (subtotal - discount);
		grandTotal = hst + (subtotal - discount);
		
		// Update TextFields
		txtTotals[0].setText("$" + String.format("%,.2f", subtotal));
		txtTotals[1].setText("-($" + String.format("%,.2f", discount) + ")");
		txtTotals[2].setText("$" + String.format("%,.2f", hst));
		txtTotals[3].setText("$" + String.format("%,.2f", grandTotal));
		
		// Check if there is a discount, change background to green and text to white
		if (discount > 0)
		{
			txtTotals[1].setStyle("-fx-background-color: green; -fx-text-fill: white");
		}
		
	}
	private void clear()
	{
		// Reset all variable and operations
		numApps = 0;
		phoneColor = "Red";
		storageCapacity = "";
		storagePriceTotal = 0;
		appPriceTotal = 0;
		dataPrice = 0;
		localPrice = 0;
		canPrice = 0;
		usPrice = 0;
		planPriceTotal = 0;
		subtotal = 0;
		discount = 0;
		hst = 0;
		grandTotal = 0;
		data = "";
		local = "";
		can = "";
		us = "";
		
		rbtnColors[0].setSelected(true);
		lblPhone.setGraphic(iphoneColors[0]);
		btnStorage[0].setDisable(false);
		btnStorage[1].setDisable(false);
		btnStorage[2].setDisable(false);
		btnStorage[3].setDisable(false);
		btnColumn[1].setDisable(true);
		txtTotals[1].setStyle("-fx-color: white; -fx-text-fill: black");
		for(int i = 0; i < chkApps.length; i++)
		{
			// Unselect all check boxes
			chkApps[i].setSelected(false);
		}
		cboData.setValue("O");
		cboLocal.setValue("O");
		cboCan.setValue("O");
		cboUs.setValue("O");
		txtSectionPrices[0].setText("$0.00");
		txtSectionPrices[1].setText("$0.00");
		
		txtTotals[0].setText("");
		txtTotals[1].setText("");
		txtTotals[2].setText("");
		txtTotals[3].setText("");
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
