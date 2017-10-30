package milkman_assistant;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

import milkman_assistant.SST_SMS;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Bill 
{
	Connection con;
	PreparedStatement pst,pst1;
	
	Text lblSD,lblED,lblN,lblM,lblCQ,lblBQ,lblCP,lblBP,lblCA,lblBA,lblT;
	TextField txtM,txtCQ,txtBQ,txtCP,txtBP,txtCA,txtBA,txtT;
	ComboBox<String> comboN;
	Button btnT;
	GridPane grd;
	HBox h1,h2,h3;
	float cp,bp,cq,bq; 
	DatePicker sd,ed;
	ImageView btnB,lblH,i,b;
	DropShadow ds;
	public Bill()
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
		Calendar cal=Calendar.getInstance();
		sd=new DatePicker(LocalDate.of(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH)+1,cal.get(Calendar.DATE)));
		
		ed=new DatePicker(LocalDate.of(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH)+1,cal.get(Calendar.DATE)));
		
		sd.setPromptText("Select Date");
		sd.setLayoutX(200);
		sd.setLayoutY(200);
		ed.setPromptText("Select Date");
		ed.setLayoutX(200);
		ed.setLayoutY(200);
		
		lblH=new ImageView(new Image(Bill.class.getResourceAsStream("h3.png")));
     
		lblSD=new Text("Start Date");
		lblSD.setFont(Font.font(15));
		lblSD.setFill(Color.DARKMAGENTA);
		lblED=new Text("End Date");
		lblED.setFont(Font.font(15));
		lblED.setFill(Color.DARKMAGENTA);
		lblN=new Text("Name");
		lblN.setFont(Font.font(15));
		lblN.setFill(Color.DARKMAGENTA);
		lblM=new Text("Mobile No");
		lblM.setFont(Font.font(15));
		lblM.setFill(Color.DARKMAGENTA);
		lblCQ=new Text("Cowmilk Quantity");
		lblCQ.setFont(Font.font(15));
		lblCQ.setFill(Color.DARKMAGENTA);
		lblBQ=new Text("Buffalomilk Quantity");
		lblBQ.setFont(Font.font(15));
		lblBQ.setFill(Color.DARKMAGENTA);
		lblCP=new Text("Cowmilk Price      ");
		lblCP.setFont(Font.font(15));
		lblCP.setFill(Color.DARKMAGENTA);
		lblBP=new Text("Buffalomilk Price      ");
		lblBP.setFont(Font.font(15));
		lblBP.setFill(Color.DARKMAGENTA);
		lblCA=new Text("Cowmilk Amount  ");
		lblCA.setFont(Font.font(15));
		lblCA.setFill(Color.DARKMAGENTA);
		lblBA=new Text("Buffalomilk Amount  ");
		lblBA.setFont(Font.font(15));
		lblBA.setFill(Color.DARKMAGENTA);
		lblT=new Text("Total Bill");
		lblT.setFont(Font.font(15));
		lblT.setFill(Color.DARKMAGENTA);
		
		txtM=new TextField();
		txtM.setPromptText("Mobile no");
		txtM.setEditable(false);
		txtM.setMaxWidth(200);
		txtCQ=new TextField();
		txtCQ.setPromptText("Cowmilk Quantity");
		txtBQ=new TextField();
		txtBQ.setPromptText("Buffalomilk Quantity");
		txtCP=new TextField();
		txtCP.setPromptText("Cowmilk Price");
		txtBP=new TextField();
		txtBP.setPromptText("Buffalomilk Price");
		txtCA=new TextField();
		txtCA.setPromptText("Cowmilk Amount");
		txtBA=new TextField();
		txtBA.setPromptText("Buffalomilk Amount");
		txtT=new TextField();
		txtT.setPromptText("Total Bill");
		
		comboN=new ComboBox<String>();
		comboN.setMaxSize(200, 30);
		comboN.getSelectionModel().select(0);
		fillName();
		
		i=new ImageView(new Image(Bill.class.getResourceAsStream("add-128.png")));
		i.setFitHeight(20);
		i.setFitWidth(20);
		
		btnT=new Button("Total",i);
		btnT.setMaxHeight(70);
		btnT.setMaxWidth(80);

		b=new ImageView(new Image(Bill.class.getResourceAsStream("bsms.png")));
		b.setFitHeight(50);
		b.setFitWidth(100);
		
		btnB=new ImageView(new Image(Bill.class.getResourceAsStream("Billbtn.png")));
		btnB.setFitWidth(400);
		btnB.setFitHeight(70);
		
		ds=new DropShadow();
		
		btnT.setOnMouseEntered(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				btnT.setEffect(ds);
			}
			
		});
		btnT.setOnMouseExited(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				btnT.setEffect(null);
			}
			
		});
		
		btnB.setOnMouseEntered(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				btnB.setEffect(ds);
			}
			
		});
		btnB.setOnMouseExited(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				btnB.setEffect(null);
			}
			
		});
		
		b.setOnMouseEntered(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				b.setEffect(ds);
			}
			
		});
		b.setOnMouseExited(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				b.setEffect(null);
			}
			
		});
		
		h1=new HBox();
		h1.setSpacing(20);
		h1.getChildren().addAll(lblCQ,txtCQ,lblBQ,txtBQ);
		
		h2=new HBox();
		h2.setSpacing(20);
		h2.getChildren().addAll(lblCP,txtCP,lblBP,txtBP);
		
		h3=new HBox();
		h3.setSpacing(20);
		h3.getChildren().addAll(lblCA,txtCA,lblBA,txtBA);
		
		grd=new GridPane();
		grd.setVgap(5);
		grd.setHgap(5);
		grd.setGridLinesVisible(false);
		grd.setAlignment(Pos.CENTER);
		grd.setPadding(new Insets(30));
		
		GridPane.setConstraints(sd, 1, 1, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(sd);
		GridPane.setConstraints(ed, 1, 2, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(ed);
		
		GridPane.setConstraints(lblH, 0, 0, 3, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(20));
		grd.getChildren().add(lblH);
		GridPane.setConstraints(lblN, 0, 3, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(lblN);
		GridPane.setConstraints(lblM, 0, 4, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(lblM);
		GridPane.setConstraints(lblSD, 0, 1, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(lblSD);
		GridPane.setConstraints(lblED, 0, 2, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(lblED);
		
		
		GridPane.setConstraints(txtM, 1, 4, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(txtM);
		
		GridPane.setConstraints(comboN, 1, 3, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(comboN);
		
		GridPane.setConstraints(btnT, 1, 5, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(btnT);
		
		GridPane.setConstraints(h1, 0, 6, 3, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(h1);
		GridPane.setConstraints(h2, 0, 7, 3, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(h2);
		
		GridPane.setConstraints(btnB, 1, 8, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(btnB);
		GridPane.setConstraints(h3, 0, 9, 3, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(h3);
		
		GridPane.setConstraints(lblT, 0, 10, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(lblT);
		
		GridPane.setConstraints(txtT, 1, 10, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(txtT);
		
		GridPane.setConstraints(b, 2, 10, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(b);
		
		comboN.setOnAction(e->fillMob());
		
		btnT.setOnAction(e->doTotal());
		btnB.setOnMouseClicked(e->doBill());
		b.setOnMouseClicked(e->sendSMS());
		
		Scene scene=new Scene(grd,1000,700);
		Stage stage=new Stage();
		stage.setScene(scene);
		stage.show();
		
	}
	void fillName()
	{
		ArrayList<String> ary=new ArrayList<String>();
		try {
			pst=con.prepareStatement("select distinct cname from dailyupdate");
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				String u=rs.getString("cname");
				ary.add(u);
			}
			comboN.getItems().addAll(ary);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	void fillMob()
	{
		txtM.setText(null);
		txtCQ.setText(null);
		txtBQ.setText(null);
		txtCA.setText(null);
		txtBA.setText(null);
		txtCP.setText(null);
		txtBP.setText(null);
		txtT.setText(null);
		
		try {
			pst=con.prepareStatement("select mob from customers where cname=?");
			pst.setString(1, comboN.getSelectionModel().getSelectedItem());
			
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				String mob=rs.getString("mob");
				txtM.setText(mob);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	void doTotal()
	{
		int m=0;
		LocalDate local1=sd.getValue();
		LocalDate local2=ed.getValue();
		java.sql.Date d1=java.sql.Date.valueOf(local1);
		java.sql.Date d2=java.sql.Date.valueOf(local2);
		
		if(comboN.getSelectionModel().getSelectedItem()==null)
		{
			doShow("Plzz select customer name");
		}
		else{
			try {
			pst=con.prepareStatement("select cname,edate from bill");
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				String n=rs.getString("cname");
				Date e=rs.getDate("edate");
				if(n.equals(comboN.getSelectionModel().getSelectedItem())) 
					{
						if(d1.compareTo(e)<=0)
						{
							doShow("Start date should be greater than "+e+" ");
							m=1;
						}
					}
			}
						if(m==0)
						{
							pst1=con.prepareStatement("select sum(cq) as 'cq',sum(bq) as 'bq' from dailyupdate where cname=? and cdate>=? and cdate<=?");
							pst1.setString(1, comboN.getSelectionModel().getSelectedItem());
							pst1.setDate(2, d1);
							pst1.setDate(3, d2);
							ResultSet rs1=pst1.executeQuery();
							if(rs1.next())
							{
								txtCQ.setText(String.valueOf(rs1.getFloat("cq")));
								txtBQ.setText(String.valueOf(rs1.getFloat("bq")));
								cq=rs1.getFloat("cq");
								bq=rs1.getFloat("bq");
								if(cq==0)
									txtCP.setText("0");
								if(bq==0)
									txtBP.setText("0");
								
							}
						}
			} 
			catch (SQLException e2)
			{
				e2.printStackTrace();
			}
		}
	}
	void doBill()
	{
		if(txtCP.getText().equals(""))
			doShow("Plzz enter cowmilk price!");
		else if(txtBP.getText().equals(""))
			doShow("Plzz enter buffalomilk price!");
		else
		{
			cp=Float.parseFloat(txtCP.getText());
			bp=Float.parseFloat(txtBP.getText());
			float ca=cp*cq;
			float ba=bp*bq;
			float t=ca+ba;
			txtCA.setText(String.valueOf(ca));
			txtBA.setText(String.valueOf(ba));
			txtT.setText(String.valueOf(t));
			if(t!=0)
			{
				doSave();
			}
		}
		
		
	}
	void sendSMS()
	{
		try {
			pst=con.prepareStatement("select mob from customers where cname=?");
			pst.setString(1, comboN.getSelectionModel().getSelectedItem());
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				String u=rs.getString("mob");
				System.out.println("Mob no: "+u);
				SST_SMS.bceSunSoftSend(u ,"Amount paid: "+txtT.getText());
			}
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	void doShow(String msg)
	{
		Alert alert=new Alert(AlertType.INFORMATION);
		alert.setContentText(msg);
		alert.show();
	}
	void doSave()
	{
			LocalDate local1=sd.getValue();
			LocalDate local2=ed.getValue();
			System.out.println(local1+"  "+local2);
			java.sql.Date d1=java.sql.Date.valueOf(local1);
			java.sql.Date d2=java.sql.Date.valueOf(local2);
			
			try {
				
				pst1=con.prepareStatement("insert into bill values(?,?,?,?,?,?,?,?,?,?)");
				pst1.setString(1, comboN.getSelectionModel().getSelectedItem());
				pst1.setDate(2, d1);
				pst1.setDate(3, d2);
				pst1.setString(4, txtCQ.getText());
				pst1.setString(5, txtBQ.getText());
				pst1.setString(6, txtCA.getText());
				pst1.setString(7, txtBA.getText());
				pst1.setString(8, txtT.getText());
				pst1.setString(9, "0");
				pst1.setString(10, null);
							
				pst1.executeUpdate();
				System.out.println("Record Saved");
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
	}
}

