package milkman_assistant;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CustDetail 
{
	Connection con;
	PreparedStatement pst;
	
	TableView<Customers> tbl;
	Text lblN,lblL;
	TextField txtN;
	ComboBox<String> comboL;
	ImageView btnG,btnS,btnF,hCD,btnE;
	RadioButton cow,buf,bth;
	HBox h;
	GridPane grd;
	
	@SuppressWarnings("unchecked")
	CustDetail()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/milkman","root","bce");
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		tbl=new TableView<>();
		TableColumn<Customers, String> colN=new TableColumn<>("Customer name");
		colN.setCellValueFactory(new PropertyValueFactory<>("cname"));
		colN.setMinWidth(100);
		
		TableColumn<Customers, String>colM=new TableColumn<Customers, String>("Mobile");
		colM.setCellValueFactory(new PropertyValueFactory<>("mob"));
		colM.setMinWidth(100);
		
		TableColumn<Customers, String>colA=new TableColumn<Customers, String>("Address");
		colA.setCellValueFactory(new PropertyValueFactory<>("adr"));
		colA.setMinWidth(100);
		
		TableColumn<Customers, String>colL=new TableColumn<Customers, String>("Location");
		colL.setCellValueFactory(new PropertyValueFactory<>("loc"));
		colL.setMinWidth(100);
		
		TableColumn<Customers, String>colC=new TableColumn<Customers, String>("CowMilk quantity");
		colC.setCellValueFactory(new PropertyValueFactory<>("cowq"));
		colC.setMinWidth(120);
		
		TableColumn<Customers, String>colB=new TableColumn<Customers, String>("BuffaloMilk quantity");
		colB.setCellValueFactory(new PropertyValueFactory<>("bufq"));
		colB.setMinWidth(150);
		
		TableColumn<Customers, String>colD=new TableColumn<Customers, String>("Date of Start");
		colD.setCellValueFactory(new PropertyValueFactory<>("dos"));
		colD.setMinWidth(100);
		
		tbl.getColumns().addAll(colN,colM,colA,colL,colC,colB,colD);
		
		lblN=new Text("Name Googler");
		lblN.setFont(Font.font(15));
		lblL=new Text("Location");
		lblL.setFont(Font.font(15));
		
		txtN=new TextField();
		txtN.setPromptText("Enter name");
		
		comboL=new ComboBox<>();
		comboL.setMaxWidth(200);
		fillLoc();
		
		cow=new RadioButton("Cow");
		buf=new RadioButton("Buffalo");
		bth=new RadioButton("Both");
		
		cow.setPadding(new Insets(20));
		buf.setPadding(new Insets(20));
		bth.setPadding(new Insets(20));
		
		ToggleGroup t=new ToggleGroup();
		cow.setToggleGroup(t);
		buf.setToggleGroup(t);
		bth.setToggleGroup(t);
		
		h=new HBox();
		h.setSpacing(10);
		h.getChildren().addAll(cow,buf,bth);
		
		btnG=new ImageView(new Image(CustDetail.class.getResourceAsStream("bGoogle.png")));
		btnG.setFitHeight(50);
		btnG.setFitWidth(100);
		btnS=new ImageView(new Image(CustDetail.class.getResourceAsStream("bSearch.png")));
		btnS.setFitHeight(50);
		btnS.setFitWidth(100);
		btnF=new ImageView(new Image(CustDetail.class.getResourceAsStream("bfetch.png")));
		btnF.setFitHeight(50);
		btnF.setFitWidth(100);
		hCD=new ImageView(new Image(CustDetail.class.getResourceAsStream("hCD.png")));
		hCD.setFitHeight(80);
		hCD.setFitWidth(300);
		btnE=new ImageView(new Image(CustDetail.class.getResourceAsStream("btne.png")));
		btnE.setFitHeight(50);
		btnE.setFitWidth(200);
		
		DropShadow ds=new DropShadow();
		btnE.setOnMouseEntered(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				btnE.setEffect(ds);
			}
			
		});
		btnE.setOnMouseExited(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				btnE.setEffect(null);
			}
			
		});
		btnF.setOnMouseEntered(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				btnF.setEffect(ds);
			}
			
		});
		btnF.setOnMouseExited(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				btnF.setEffect(null);
			}
			
		});
		btnG.setOnMouseEntered(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				btnG.setEffect(ds);
			}
			
		});
		btnG.setOnMouseExited(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				btnG.setEffect(null);
			}
			
		});
		btnS.setOnMouseEntered(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				btnS.setEffect(ds);
			}
			
		});
		btnS.setOnMouseExited(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				btnS.setEffect(null);
			}
			
		});
		
		grd=new GridPane();
		grd.setVgap(5);
		grd.setHgap(5);
		grd.setGridLinesVisible(false);
		grd.setAlignment(Pos.CENTER);
		grd.setPadding(new Insets(30));
		
		GridPane.setConstraints(hCD, 0, 0,5 , 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(hCD);
		GridPane.setConstraints(tbl, 0, 5,5 , 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(tbl);
		GridPane.setConstraints(lblN, 0, 1, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(lblN);
		GridPane.setConstraints(txtN, 1, 1, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(txtN);
		GridPane.setConstraints(lblL, 0, 3, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(lblL);
		GridPane.setConstraints(comboL, 1, 3, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(comboL);
		GridPane.setConstraints(btnE, 0, 4,5 , 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(btnE);
		GridPane.setConstraints(btnG, 2, 1, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(btnG);
		GridPane.setConstraints(btnS, 2, 2, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(btnS);
		GridPane.setConstraints(btnF, 2, 3, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(btnF);
		GridPane.setConstraints(h, 0, 2, 2, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(h);
		
		btnG.setOnMouseClicked(e->{
			tbl.setItems(getRows());
		});
		btnS.setOnMouseClicked(e->{
			if(cow.isSelected())
			{
				tbl.setItems(getCow());
			}
			else if(buf.isSelected())
			{
				tbl.setItems(getBuf());
			}
			else if(bth.isSelected())
			{
				tbl.setItems(getBoth());
			}
		});
		btnF.setOnMouseClicked(e->{
			if(cow.isSelected())
			{
				tbl.setItems(getCow1());
			}
			else if(buf.isSelected())
			{
				tbl.setItems(getBuf1());
			}
			else if(bth.isSelected())
			{
				tbl.setItems(getBoth1());
			}
			else
				tbl.setItems(getSomeRows());
		});
		btnE.setOnMouseClicked(e->writeExcel());
		
		Scene scene=new Scene(grd,1000,800);
		Stage stage=new Stage();
		stage.setScene(scene);
		stage.show();
		
	}
	void fillLoc()
	{
		ArrayList<String> ary=new ArrayList<>();
		try {
			pst=con.prepareStatement("select distinct loc from customers");
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				String lc=rs.getString("loc");
				ary.add(lc);
			}
			comboL.getItems().addAll(ary);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	ObservableList<Customers> lst;
	ObservableList<Customers> getRows()
	{
		lst=FXCollections.observableArrayList();
		try {
			pst=con.prepareStatement("select * from customers where cname like '%"+txtN.getText()+"%'");
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				Customers ref=new Customers(rs.getString("cname"), rs.getString("mob"), rs.getString("adr"), rs.getString("loc"), rs.getFloat("cowq"),rs.getFloat("bufq"),rs.getDate("dos"));
				lst.add(ref);
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return(lst);
	}
	ObservableList<Customers> getCow()
	{
		lst=FXCollections.observableArrayList();
		try {
			pst=con.prepareStatement("select * from customers where cname like '%"+txtN.getText()+"%' and bufq=?");
			pst.setString(1, "0");
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				Customers ref=new Customers(rs.getString("cname"), rs.getString("mob"), rs.getString("adr"), rs.getString("loc"), rs.getFloat("cowq"),rs.getFloat("bufq"),rs.getDate("dos"));
				lst.add(ref);
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return(lst);
	}
	ObservableList<Customers> getBuf()
	{
		lst=FXCollections.observableArrayList();
		try {
			pst=con.prepareStatement("select * from customers where cname like '%"+txtN.getText()+"%' and cowq=?");
			pst.setString(1, "0");
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				Customers ref=new Customers(rs.getString("cname"), rs.getString("mob"), rs.getString("adr"), rs.getString("loc"), rs.getFloat("cowq"),rs.getFloat("bufq"),rs.getDate("dos"));
				lst.add(ref);
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return(lst);
	}
	ObservableList<Customers> getBoth()
	{
		lst=FXCollections.observableArrayList();
		try {
			pst=con.prepareStatement("select * from customers where cname like '%"+txtN.getText()+"%' and cowq<>? and bufq<>?");
			pst.setString(1, "0");
			pst.setString(2, "0");
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				Customers ref=new Customers(rs.getString("cname"), rs.getString("mob"), rs.getString("adr"), rs.getString("loc"), rs.getFloat("cowq"),rs.getFloat("bufq"),rs.getDate("dos"));
				lst.add(ref);
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return(lst);
	}
	ObservableList<Customers> getCow1()
	{
		lst=FXCollections.observableArrayList();
		try {
			pst=con.prepareStatement("select * from customers where cname like '%"+txtN.getText()+"%' and bufq=? and cowq<>? and loc=?");
			pst.setString(1, "0");
			pst.setString(2, "0");
			pst.setString(3, comboL.getSelectionModel().getSelectedItem());
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				Customers ref=new Customers(rs.getString("cname"), rs.getString("mob"), rs.getString("adr"), rs.getString("loc"), rs.getFloat("cowq"),rs.getFloat("bufq"),rs.getDate("dos"));
				lst.add(ref);
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return(lst);
	}
	ObservableList<Customers> getBuf1()
	{
		lst=FXCollections.observableArrayList();
		try {
			pst=con.prepareStatement("select * from customers where cname like '%"+txtN.getText()+"%' and cowq=? and bufq<>? and loc=?");
			pst.setString(1, "0");
			pst.setString(2, "0");
			pst.setString(3, comboL.getSelectionModel().getSelectedItem());
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				Customers ref=new Customers(rs.getString("cname"), rs.getString("mob"), rs.getString("adr"), rs.getString("loc"), rs.getFloat("cowq"),rs.getFloat("bufq"),rs.getDate("dos"));
				lst.add(ref);
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return(lst);
	}
	ObservableList<Customers> getBoth1()
	{
		lst=FXCollections.observableArrayList();
		try {
			pst=con.prepareStatement("select * from customers where cname like '%"+txtN.getText()+"%' and cowq<>? and bufq<>? and loc=?");
			pst.setString(1, "0");
			pst.setString(2, "0");
			pst.setString(3, comboL.getSelectionModel().getSelectedItem());
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				Customers ref=new Customers(rs.getString("cname"), rs.getString("mob"), rs.getString("adr"), rs.getString("loc"), rs.getFloat("cowq"),rs.getFloat("bufq"),rs.getDate("dos"));
				lst.add(ref);
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return(lst);
	}
	ObservableList<Customers> getSomeRows()
	{
		lst=FXCollections.observableArrayList();
		try {
			pst=con.prepareStatement("select * from customers where cname like '%"+txtN.getText()+"%' and loc=?");
			pst.setString(1, comboL.getSelectionModel().getSelectedItem());
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				Customers ref=new Customers(rs.getString("cname"), rs.getString("mob"), rs.getString("adr"), rs.getString("loc"), rs.getFloat("cowq"),rs.getFloat("bufq"),rs.getDate("dos"));
				lst.add(ref);
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return(lst);
	}
	public void writeExcel()
	{
		Writer writer = null;
		try {
		File file = new File("D:\\Customers.csv");
		writer = new BufferedWriter(new FileWriter(file));
		String text="Customer Name,Mobile, Address,Location,Cow Quantity,Buffalo Quantity,StartDate\n";
		writer.write(text);
		for (Customers b : lst) 
		{
		text = b.getCname() + "," + b.getMob() + "," + b.getAdr()+ ","+ b.getLoc()+ "," + b.getCowq()+ "," + b.getBufq()+ "," + b.getDos()+"\n";
		writer.write(text);
		}
		} 
		catch (Exception ex) {
			doShow("Please close the already open excel sheet!");
		}
		finally {
		try {
			writer.flush();
			writer.close();
			doShow("Exported!");
		} 
		catch (NullPointerException e) 
		{
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		}
	}
	static void doShow(String msg)
	{
		Alert alert=new Alert(AlertType.INFORMATION);
		alert.setContentText(msg);
		alert.show();
	}
	
}
