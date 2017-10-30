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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class DateHistory
{	
		Connection con;
		PreparedStatement pst;
		
		TableView<DailyClass> tbl;
		Text lblD;
		GridPane grd;
		DatePicker sd;
		ImageView h,btnF,btnE;
		
		@SuppressWarnings("unchecked")
		DateHistory()
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
			
			TableColumn<DailyClass, Float> colCQ=new TableColumn<>("Cow Quantity");
			colCQ.setCellValueFactory(new PropertyValueFactory<>("cq"));
			colCQ.setMinWidth(120);
			
			TableColumn<DailyClass, Float> colBQ=new TableColumn<>("Buffalo Quantity");
			colBQ.setCellValueFactory(new PropertyValueFactory<>("bq"));
			colBQ.setMinWidth(120);
		
			tbl.getColumns().addAll(colN,colCQ,colBQ);
			
			lblD=new Text("Date");
			lblD.setFont(Font.font(15));
			
			h=new ImageView(new Image(DateHistory.class.getResourceAsStream("hDH.png")));
			h.setFitHeight(80);
			h.setFitWidth(300);
			btnF=new ImageView(new Image(DateHistory.class.getResourceAsStream("bfetch.png")));
			btnF.setFitHeight(50);
			btnF.setFitWidth(100);
			btnE=new ImageView(new Image(DateHistory.class.getResourceAsStream("btne.png")));
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
			
			sd.setPromptText("Select Date");
			sd.setLayoutX(200);
			sd.setLayoutY(200);
				
			grd=new GridPane();
			grd.setVgap(5);
			grd.setHgap(5);
			grd.setGridLinesVisible(false);
			grd.setAlignment(Pos.CENTER);
			grd.setPadding(new Insets(50));
			
			GridPane.setConstraints(tbl, 0, 4, 3, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
			grd.getChildren().add(tbl);
			GridPane.setConstraints(h, 0, 0, 2, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
			grd.getChildren().add(h);
			GridPane.setConstraints(lblD, 0, 1, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
			grd.getChildren().add(lblD);
			GridPane.setConstraints(sd, 1, 1, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
			grd.getChildren().add(sd);
			GridPane.setConstraints(btnF, 0, 2, 2, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
			grd.getChildren().add(btnF);
			GridPane.setConstraints(btnE, 0, 3, 3, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
			grd.getChildren().add(btnE);
			
			btnF.setOnMouseClicked(e->{
				tbl.setItems(getRows());
			});
			btnE.setOnMouseClicked(e->writeExcel());

			Scene scene=new Scene(grd,600,600);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
			
		}
		ObservableList<DailyClass> lst;
		ObservableList<DailyClass> getRows()
		{
			lst=FXCollections.observableArrayList();
			LocalDate local1=sd.getValue();
			java.sql.Date d1=java.sql.Date.valueOf(local1);
			try {
				pst=con.prepareStatement("select * from dailyupdate where cdate=?");
				pst.setDate(1, d1);
				ResultSet rs=pst.executeQuery();
				while(rs.next())
				{
					DailyClass ref=new DailyClass(rs.getString("cname"), rs.getFloat("cq"), rs.getFloat("bq"));
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
			File file = new File("D:\\DateHistory.csv");
			writer = new BufferedWriter(new FileWriter(file));
			String text="Customer Name,Cow Quantity,Buffalo Quantity\n";
			writer.write(text);
			for (DailyClass b : lst) 
			{
			text = b.getCname() + "," + b.getCq()+ "," + b.getBq()+ "\n";
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
