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
import java.util.ArrayList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
public class RoutineHistory
{	
		Connection con;
		PreparedStatement pst;
		
		TableView<DailyClass> tbl;
		Text lblDF,lblT,lblN;
		ComboBox<String> txtN;
		ImageView h,btnF,btnE;
		HBox h1,h2;
		GridPane grd;
		DatePicker sd,ed;
		
		@SuppressWarnings("unchecked")
		RoutineHistory()
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
			TableColumn<DailyClass, String> colN=new TableColumn<>("Customer name");
			colN.setCellValueFactory(new PropertyValueFactory<>("cname"));
			colN.setMinWidth(120);
			
			TableColumn<DailyClass, Date> colD=new TableColumn<>("Date");
			colD.setCellValueFactory(new PropertyValueFactory<>("cdate"));
			colD.setMinWidth(120);
			
			TableColumn<DailyClass, Float> colCQ=new TableColumn<>("Cow Quantity");
			colCQ.setCellValueFactory(new PropertyValueFactory<>("cq"));
			colCQ.setMinWidth(120);
			
			TableColumn<DailyClass, Float> colBQ=new TableColumn<>("Buffalo Quantity");
			colBQ.setCellValueFactory(new PropertyValueFactory<>("bq"));
			colBQ.setMinWidth(120);
		
			tbl.getColumns().addAll(colN,colD,colCQ,colBQ);
			
			lblN=new Text("Customer name");
			lblN.setFont(Font.font(15));
			txtN=new ComboBox<>();
			txtN.setPromptText("Enter customer name");
			txtN.setMaxWidth(200);
			fillName();
			
			lblDF=new Text("Date from");
			lblDF.setFont(Font.font(15));
			lblT=new Text("to");
			lblT.setFont(Font.font(15));
			
			h=new ImageView(new Image(RoutineHistory.class.getResourceAsStream("hRH.png")));
			h.setFitHeight(80);
			h.setFitWidth(300);
			btnF=new ImageView(new Image(RoutineHistory.class.getResourceAsStream("bfetch.png")));
			btnF.setFitHeight(50);
			btnF.setFitWidth(100);
			btnE=new ImageView(new Image(RoutineHistory.class.getResourceAsStream("btne.png")));
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
			h2.getChildren().addAll(lblN,txtN);
		
			grd=new GridPane();
			grd.setVgap(5);
			grd.setHgap(5);
			grd.setGridLinesVisible(false);
			grd.setAlignment(Pos.CENTER);
			grd.setPadding(new Insets(50));
			
			GridPane.setConstraints(tbl, 0, 5, 4, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
			grd.getChildren().add(tbl);
			GridPane.setConstraints(h, 0, 0, 4, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
			grd.getChildren().add(h);
			GridPane.setConstraints(h1, 0, 2, 4, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
			grd.getChildren().add(h1);
			GridPane.setConstraints(h2, 0, 1, 4, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
			grd.getChildren().add(h2);
			GridPane.setConstraints(btnF, 0, 3, 4, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
			grd.getChildren().add(btnF);
			GridPane.setConstraints(btnE, 0, 4, 4, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
			grd.getChildren().add(btnE);
			
			btnF.setOnMouseClicked(e->{
				if(txtN.getSelectionModel()==null)
				{
					doShow("Plzz choose customer name!");
				}
				else
				{
					tbl.setItems(getRows());
				}
				
			});
			btnE.setOnMouseClicked(e->writeExcel());

			Scene scene=new Scene(grd,600,600);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
			
		}
		void fillName()
		{
			ArrayList<String> ary=new ArrayList<String>();
			try {
				pst=con.prepareStatement("select cname from customers");
				ResultSet rs=pst.executeQuery();
				while(rs.next())
				{
					String u=rs.getString("cname");
					ary.add(u);
				}
				txtN.getItems().addAll(ary);
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			
		}
		ObservableList<DailyClass> lst;
		ObservableList<DailyClass> getRows()
		{
			lst=FXCollections.observableArrayList();
			LocalDate local1=sd.getValue();
			LocalDate local2=ed.getValue();
			java.sql.Date d1=java.sql.Date.valueOf(local1);
			java.sql.Date d2=java.sql.Date.valueOf(local2);
			try {
				pst=con.prepareStatement("select * from dailyupdate where cdate>=? and cdate<=? and cname=?");
				pst.setDate(1, d1);
				pst.setDate(2, d2);
				pst.setString(3, txtN.getSelectionModel().getSelectedItem());
				ResultSet rs=pst.executeQuery();
				while(rs.next())
				{
					DailyClass ref=new DailyClass(rs.getString("cname"), rs.getDate("cdate"), rs.getFloat("cq"), rs.getFloat("bq"));
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
			File file = new File("D:\\RoutineHistory.csv");
			writer = new BufferedWriter(new FileWriter(file));
			String text="Customer Name,Date,Cow Quantity,Buffalo Quantity\n";
			writer.write(text);
			for (DailyClass b : lst) 
			{
			text = b.getCname() + "," + b.getCdate() + "," + b.getCq()+ "," + b.getBq()+ "\n";
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
