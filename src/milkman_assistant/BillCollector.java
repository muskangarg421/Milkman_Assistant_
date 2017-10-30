
package milkman_assistant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BillCollector
{
	Connection con;
	PreparedStatement pst;
	
	ComboBox<String> comboN;
	ListView<String> sd,ed,amt;
	Text lblS,lblE,lblA,lblN,lblTA;
	TextField txtA;
	GridPane grd;
	VBox v1,v2,v3;
	HBox h1,h2;
	ArrayList<String> ary,ary1,ary2,ary3,ary4,ary5;
	DropShadow ds;
	ImageView lblH,btn,btnR,btnSMS;
	public BillCollector()
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
		lblH=new ImageView(new Image(BillCollector.class.getResourceAsStream("h4.png")));
        
		lblS=new Text("\t\tStartDate");
		lblE=new Text("\t\tEndDate");
		lblA=new Text("\t\tAmount");
		lblTA=new Text("Amount");
		lblTA.setFont(Font.font(15));
		lblTA.setFill(Color.DARKMAGENTA);
		lblN=new Text("Name");
		lblN.setFont(Font.font(15));
		lblN.setFill(Color.DARKMAGENTA);
		
		txtA=new TextField();
		txtA.setMaxSize(200, 30);
		txtA.setPromptText("Amount");
		
		btn=new ImageView(new Image(BillCollector.class.getResourceAsStream("bUpdate.png")));
		btn.setFitHeight(50);
		btn.setFitWidth(100);
		btnR=new ImageView(new Image(BillCollector.class.getResourceAsStream("bReset.png")));
		btnR.setFitHeight(50);
		btnR.setFitWidth(100);
		btnSMS=new ImageView(new Image(BillCollector.class.getResourceAsStream("bsms.png")));
		btnSMS.setFitHeight(50);
		btnSMS.setFitWidth(100);
		
		comboN=new ComboBox<String>();
		comboN.setMaxSize(200, 30);
		comboN.getSelectionModel().select(0);
		fillName();
		
		sd=new ListView<String>();
		sd.setEditable(false);
		
		ed=new ListView<String>();
		ed.setEditable(false);
		
		amt=new ListView<String>();
		amt.setEditable(false);
		
		v1=new VBox();
		v1.setSpacing(10);
		v1.getChildren().addAll(lblS,sd);
		
		v2=new VBox();
		v2.setSpacing(10);
		v2.getChildren().addAll(lblE,ed);
		
		v3=new VBox();
		v3.setSpacing(10);
		v3.getChildren().addAll(lblA,amt);
		
		h1=new HBox();
		h1.setSpacing(10);
		h1.getChildren().addAll(v1,v2,v3);
		
		h2=new HBox();
		h2.setSpacing(60);
		h2.getChildren().addAll(btn,btnR,btnSMS);
		
		ds=new DropShadow();
		
		btn.setOnMouseEntered(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				btn.setEffect(ds);
			}
			
		});
		btn.setOnMouseExited(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				btn.setEffect(null);
			}
			
		});
		btnR.setOnMouseEntered(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				btnR.setEffect(ds);
			}
			
		});
		btnR.setOnMouseExited(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				btnR.setEffect(null);
			}
			
		});
		btnSMS.setOnMouseEntered(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				btnSMS.setEffect(ds);
			}
			
		});
		btnSMS.setOnMouseExited(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				btnSMS.setEffect(null);
			}
			
		});
		
		grd=new GridPane();
		grd.setPadding(new Insets(30));
		grd.setVgap(5);
		grd.setHgap(5);
		grd.setAlignment(Pos.CENTER);
		grd.setGridLinesVisible(false);
		
		GridPane.setConstraints(lblH, 0, 0, 3, 1, HPos.CENTER, VPos.CENTER,null,null,new Insets(10));
		grd.getChildren().add(lblH);
		GridPane.setConstraints(lblTA, 0, 3, 1, 1, HPos.CENTER, VPos.CENTER,null,null,new Insets(10));
		grd.getChildren().add(lblTA);
		GridPane.setConstraints(lblN,0, 1, 1, 1, HPos.CENTER, VPos.CENTER,null,null,new Insets(10));
		grd.getChildren().add(lblN);
		
		GridPane.setConstraints(comboN, 1, 1, 1, 1, HPos.LEFT, VPos.CENTER,null,null,new Insets(10));
		grd.getChildren().add(comboN);
		GridPane.setConstraints(txtA, 1, 3, 1, 1, HPos.LEFT, VPos.CENTER,null,null,new Insets(10));
		grd.getChildren().add(txtA);
		GridPane.setConstraints(h2, 1, 4, 3, 1, HPos.CENTER, VPos.CENTER,null,null,new Insets(10));
		grd.getChildren().add(h2);
		GridPane.setConstraints(h1, 0, 2, 2, 1, HPos.CENTER, VPos.CENTER,null,null,new Insets(10));
		grd.getChildren().add(h1);
		
		ary5=new ArrayList<String>();
		
		comboN.setOnAction(e->fillSD());
		btn.setOnMouseClicked(e->doUpdate());
		btnR.setOnMouseClicked(e->{
			doReset();
		});
		btnSMS.setOnMouseClicked(e->sendSMS());
		
		Scene scene=new Scene(grd,600,600);
		Stage stage=new Stage();
		stage.setScene(scene);
		stage.show();
		doDblClick();
		
	}
	void doDblClick()
	{
		sd.setOnMouseClicked(new EventHandler<MouseEvent>() 
		{
			public void handle(MouseEvent event) {
				float sum;
				if(event.getClickCount()==2)
				{
					int i=sd.getSelectionModel().getSelectedIndex();
					ary5.add(sd.getSelectionModel().getSelectedItem());
					ed.getSelectionModel().select(i);
					amt.getSelectionModel().select(i);
					
					String a=amt.getSelectionModel().getSelectedItem();
					sum=Float.parseFloat(txtA.getText())+Float.parseFloat(a);
					txtA.setText(String.valueOf(sum));
					sd.getItems().remove(sd.getSelectionModel().getSelectedItem());
					ed.getItems().remove(ed.getSelectionModel().getSelectedItem());
					amt.getItems().remove(amt.getSelectionModel().getSelectedItem());
				}
			}
		});
		ed.setOnMouseClicked(new EventHandler<MouseEvent>() 
		{
			public void handle(MouseEvent event) {
				float sum;
				if(event.getClickCount()==2)
				{
					int i=ed.getSelectionModel().getSelectedIndex();
					sd.getSelectionModel().select(i);
					amt.getSelectionModel().select(i);
					
					String a=amt.getSelectionModel().getSelectedItem();
					sum=Float.parseFloat(txtA.getText())+Float.parseFloat(a);
					txtA.setText(String.valueOf(sum));
					sd.getItems().remove(sd.getSelectionModel().getSelectedItem());
					ed.getItems().remove(ed.getSelectionModel().getSelectedItem());
					amt.getItems().remove(amt.getSelectionModel().getSelectedItem());
				}
			}
		});
		amt.setOnMouseClicked(new EventHandler<MouseEvent>() 
		{
			public void handle(MouseEvent event) {
				float sum;
				if(event.getClickCount()==2)
				{
					int i=amt.getSelectionModel().getSelectedIndex();
					ed.getSelectionModel().select(i);
					sd.getSelectionModel().select(i);
					
					String a=amt.getSelectionModel().getSelectedItem();
					sum=Float.parseFloat(txtA.getText())+Float.parseFloat(a);
					txtA.setText(String.valueOf(sum));
					sd.getItems().remove(sd.getSelectionModel().getSelectedItem());
					ed.getItems().remove(ed.getSelectionModel().getSelectedItem());
					amt.getItems().remove(amt.getSelectionModel().getSelectedItem());
				}
			}
		});
	}
	void fillName()
	{
		ary=new ArrayList<String>();
		try {
			pst=con.prepareStatement("select distinct cname from bill where status=?");
			pst.setString(1, "0");
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
	void fillSD()
	{
		txtA.setText("0");
		ary1=new ArrayList<String>();
		ary2=new ArrayList<String>();
		ary3=new ArrayList<String>();
		sd.getItems().clear();
		ed.getItems().clear();
		amt.getItems().clear();
		try {
			pst=con.prepareStatement("select sdate,edate,total from bill where cname=? and status=?");
			pst.setString(1, comboN.getSelectionModel().getSelectedItem());
			pst.setString(2, "0");
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				java.sql.Date s=rs.getDate("sdate");
				ary1.add(String.valueOf(s));
				java.sql.Date e=rs.getDate("edate");
				ary2.add(String.valueOf(e));
				String total=rs.getString("total");
				ary3.add(total);
			}
			sd.getItems().addAll(ary1);
			ed.getItems().addAll(ary2);
			amt.getItems().addAll(ary3);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	void doUpdate()
	{
		if(comboN.getSelectionModel().getSelectedItem()==null)
		{
			doShow("Plzz select customer name!");
		}
		else{
			for(String s:ary5)
			{
			System.out.println("s: "+s);
			try {
			pst=con.prepareStatement("update bill set status=?, pdate=curdate() where sdate=? and cname=?");
			pst.setString(1, "1");
			pst.setString(2, s);
			pst.setString(3, comboN.getSelectionModel().getSelectedItem());
			pst.executeUpdate();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			}
		System.out.println("Updated");
		}
	}
	void doReset()
	{
		comboN.getSelectionModel().select(null);
		sd.getItems().removeAll();
		ed.getItems().removeAll();
		amt.getItems().removeAll();
		txtA.setText("0");
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
				SST_SMS.bceSunSoftSend(u ,"Amount paid: "+txtA.getText());
			}
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}	
	}
	static void doShow(String msg)
	{
		Alert alert=new Alert(AlertType.INFORMATION);
		alert.setContentText(msg);
		alert.show();
	}
}
