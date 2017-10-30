package milkman_assistant;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
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
public class BillWatcher
{	
		Connection con;
		PreparedStatement pst;
		
		TableView<BillClass> tbl;
		Text lblDF,lblT;
		ImageView btnF,h,btnE;
		RadioButton full, bal;
		HBox h1,h2;
		GridPane grd;
		DatePicker sd,ed;
		
		@SuppressWarnings("unchecked")
		BillWatcher()
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
			TableColumn<BillClass, String> colN=new TableColumn<>("Customer name");
			colN.setCellValueFactory(new PropertyValueFactory<>("cname"));
			colN.setMinWidth(100);
			
			TableColumn<BillClass, Date>colSD=new TableColumn<>("Start Date");
			colSD.setCellValueFactory(new PropertyValueFactory<>("sdate"));
			colSD.setMinWidth(100);
			
			TableColumn<BillClass, Date>colED=new TableColumn<>("End Date");
			colED.setCellValueFactory(new PropertyValueFactory<>("edate"));
			colED.setMinWidth(100);
			
			TableColumn<BillClass , Float>colCQ=new TableColumn<>("Total cow Quantity");
			colCQ.setCellValueFactory(new PropertyValueFactory<>("tcqty"));
			colCQ.setMinWidth(120);
			
			TableColumn<BillClass , Float>colBQ=new TableColumn<>("Total buffalo Quantity");
			colBQ.setCellValueFactory(new PropertyValueFactory<>("tbqty"));
			colBQ.setMinWidth(150);
			
			TableColumn<BillClass , Float>colCA=new TableColumn<>("Cow Amount");
			colCA.setCellValueFactory(new PropertyValueFactory<>("camt"));
			colCA.setMinWidth(100);
			
			TableColumn<BillClass , Float>colBA=new TableColumn<>("Buffalo Amount");
			colBA.setCellValueFactory(new PropertyValueFactory<>("bamt"));
			colBA.setMinWidth(100);
			
			TableColumn<BillClass , Float>colT=new TableColumn<>("Total Amount");
			colT.setCellValueFactory(new PropertyValueFactory<>("total"));
			colT.setMinWidth(100);
			
			TableColumn<BillClass, Date>colD=new TableColumn<>("Date of payment");
			colD.setCellValueFactory(new PropertyValueFactory<>("pdate"));
			colD.setMinWidth(100);
			
			tbl.getColumns().addAll(colN,colSD,colED,colCQ,colBQ,colCA,colBA,colT,colD);
			
			lblDF=new Text("Date from");
			lblDF.setFont(Font.font(15));
			lblT=new Text("to");
			lblT.setFont(Font.font(15));
			
			full=new RadioButton("Fully Paid");
			bal=new RadioButton("Balance left");
			
			full.setPadding(new Insets(10));
			bal.setPadding(new Insets(10));
			
			ToggleGroup t=new ToggleGroup();
			full.setToggleGroup(t);
			bal.setToggleGroup(t);
			
			h=new ImageView(new Image(BillWatcher.class.getResourceAsStream("hBW.png")));
			h.setFitHeight(80);
			h.setFitWidth(300);
			
			btnF=new ImageView(new Image(BillWatcher.class.getResourceAsStream("bfetch.png")));
			btnF.setFitHeight(50);
			btnF.setFitWidth(150);
			
			btnE=new ImageView(new Image(BillWatcher.class.getResourceAsStream("btne.png")));
			btnE.setFitHeight(50);
			btnE.setFitWidth(200);
			
			DropShadow ds=new DropShadow();
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
			
			Calendar cal=Calendar.getInstance();
			sd=new DatePicker(LocalDate.of(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH)+1,cal.get(Calendar.DATE)));
			
			ed=new DatePicker(LocalDate.of(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH)+1,cal.get(Calendar.DATE)));
			
			sd.setPromptText("Select Date");
			sd.setLayoutX(200);
			sd.setLayoutY(200);
			ed.setPromptText("Select Date");
			ed.setLayoutX(200);
			ed.setLayoutY(200);
			
			h1=new HBox();
			h1.setSpacing(10);
			h1.getChildren().addAll(lblDF,sd,lblT,ed);
			h2=new HBox();
			h2.setSpacing(10);
			h2.getChildren().addAll(full,bal,btnF);
		
			grd=new GridPane();
			grd.setVgap(5);
			grd.setHgap(5);
			grd.setGridLinesVisible(false);
			grd.setAlignment(Pos.CENTER);
			grd.setPadding(new Insets(50));
			
			GridPane.setConstraints(tbl, 0, 4, 5 , 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
			grd.getChildren().add(tbl);
			GridPane.setConstraints(h, 0, 0, 5 , 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
			grd.getChildren().add(h);
			GridPane.setConstraints(h1, 0, 1, 2, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
			grd.getChildren().add(h1);
			GridPane.setConstraints(h2, 0, 2, 2, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
			grd.getChildren().add(h2);
			GridPane.setConstraints(btnE, 0, 3, 2, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
			grd.getChildren().add(btnE);
			
			btnF.setOnMouseClicked(e->{
				if(full.isSelected())
				{
					tbl.setItems(getFull());
				}
				else if(bal.isSelected())
				{
					tbl.setItems(getBal());
				}
				else
				{
					doShow("Plzz choose fully paid or balance!");
				}
			});
			btnE.setOnMouseClicked(e->writeExcel());
			Scene scene=new Scene(grd,1200,600);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
			
		}
		ObservableList<BillClass> lst;
		ObservableList<BillClass> getFull()
		{
			lst=FXCollections.observableArrayList();
			LocalDate local1=sd.getValue();
			LocalDate local2=ed.getValue();
			java.sql.Date d1=java.sql.Date.valueOf(local1);
			java.sql.Date d2=java.sql.Date.valueOf(local2);
			try {
				pst=con.prepareStatement("select * from bill where sdate>=? and edate<=? and status=?");
				pst.setDate(1, d1);
				pst.setDate(2, d2);
				pst.setInt(3, 1);
				ResultSet rs=pst.executeQuery();
				while(rs.next())
				{
					BillClass ref=new BillClass(rs.getString("cname"), rs.getDate("sdate"), rs.getDate("edate"), rs.getFloat("tcqty"), rs.getFloat("tbqty"), rs.getFloat("camt"), rs.getFloat("bamt"), rs.getFloat("total"), rs.getDate("pdate"));
					lst.add(ref);
				}
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			return(lst);
		}
		ObservableList<BillClass> getBal()
		{
			lst=FXCollections.observableArrayList();
			LocalDate local1=sd.getValue();
			LocalDate local2=ed.getValue();
			java.sql.Date d1=java.sql.Date.valueOf(local1);
			java.sql.Date d2=java.sql.Date.valueOf(local2);
			try {
				pst=con.prepareStatement("select * from bill where sdate>=? and edate<=? and status=?");
				pst.setDate(1, d1);
				pst.setDate(2, d2);
				pst.setInt(3, 0);
				ResultSet rs=pst.executeQuery();
				while(rs.next())
				{
					BillClass ref=new BillClass(rs.getString("cname"), rs.getDate("sdate"), rs.getDate("edate"), rs.getFloat("tcqty"), rs.getFloat("tbqty"), rs.getFloat("camt"), rs.getFloat("bamt"), rs.getFloat("total"), rs.getDate("pdate"));
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
			File file = new File("D:\\Bill.csv");
			writer = new BufferedWriter(new FileWriter(file));
			String text="Customer Name,Start Date,End Date,Total Cow Quantity,Total Buffalo Quantity,Cow Amount,Buffalo Amount,Total,Payment Date\n";
			writer.write(text);
			for (BillClass b : lst) 
			{
			text = b.getCname() + "," + b.getSdate() + "," + b.getEdate()+ "," + b.getTcqty()+ "," + b.getTbqty()+ "," + b.getCamt()+ "," + b.getBamt()+ "," + b.getTotal()+ "," + b.getPdate()+"\n";
			writer.write(text);
			}
			} 
			catch (Exception ex) {
			ex.printStackTrace();
			}
			finally {
			try {
				writer.flush();
				writer.close();
				doShow("Exported!");
			} 
			catch (IOException e) 
			{
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
