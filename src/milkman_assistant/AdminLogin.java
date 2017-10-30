package milkman_assistant;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AdminLogin extends Application
{
		public static void main(String[] args) 
		{
			launch(args);
		}	
		Text lblU,lblP,lblL,h;
		TextField txtU;
		PasswordField txtP;
		GridPane grd;
		
		public void start(Stage stage) throws Exception 
		{	
			h=new Text("Admin Login");
			h.setFont(Font.font("Arial", FontWeight.BOLD, 35));
			lblU=new Text("UserID");
			lblU.setFont(Font.font(15));
			lblP=new Text("Password");
			lblP.setFont(Font.font(15));
			lblL=new Text("Forgot Password?");
			lblL.setFill(Color.BLUE);
			//underline??
			lblL.underlineProperty();
			
			txtU=new TextField();
			txtU.setFont(Font.font(15));
			txtU.setPromptText("UserID plzz");
			
			txtP=new PasswordField();
			txtP.setFont(Font.font(15));
			txtP.setPromptText("Password plzz");
			
			grd=new GridPane();
			grd.setVgap(5);
			grd.setHgap(5);
			grd.setPadding(new Insets(50));
			grd.setAlignment(Pos.CENTER);
			grd.setGridLinesVisible(false);
			
			ImageView logo=new ImageView(new Image(AdminLogin.class.getResourceAsStream("admin.png")));
			logo.setFitWidth(200);
			logo.setFitHeight(150);
			
			ImageView b=new ImageView(new Image(AdminLogin.class.getResourceAsStream("login.png")));
			b.setFitWidth(100);
			b.setFitHeight(50);
			
			DropShadow ds=new DropShadow();
			
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
			lblL.setOnMouseEntered(new EventHandler<MouseEvent>()
			{
				public void handle(MouseEvent event) 
				{
					lblL.setEffect(ds);
				}
				
			});
			lblL.setOnMouseExited(new EventHandler<MouseEvent>()
			{
				public void handle(MouseEvent event) 
				{
					lblL.setEffect(null);
				}
				
			});
			
			GridPane.setConstraints(logo, 0, 0, 1, 1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10));
			grd.getChildren().add(logo);
			
			GridPane.setConstraints(lblU, 0, 1, 1, 1,HPos.CENTER,VPos.CENTER,null,null,new Insets(15));
			grd.getChildren().add(lblU);
			
			GridPane.setConstraints(h, 1, 0, 1, 1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10));
			grd.getChildren().add(h);
			
			GridPane.setConstraints(lblP, 0, 2, 1, 1,HPos.CENTER,VPos.CENTER,null,null,new Insets(15));
			grd.getChildren().add(lblP);
			
			GridPane.setConstraints(txtU, 1, 1 , 1, 1,HPos.CENTER,VPos.CENTER,null,null,new Insets(15));
			grd.getChildren().add(txtU);
			
			GridPane.setConstraints(txtP, 1, 2, 1, 1,HPos.CENTER,VPos.CENTER,null,null,new Insets(15));
			grd.getChildren().add(txtP);
			
			GridPane.setConstraints(b, 1,3 , 1, 1,HPos.CENTER,VPos.CENTER,null,null,new Insets(15));
			grd.getChildren().add(b);
			
			GridPane.setConstraints(lblL, 1, 4, 1, 1,HPos.CENTER,VPos.CENTER,null,null,new Insets(5));
			grd.getChildren().add(lblL);
			
			b.setOnMouseClicked(e->{
				if(txtU.getText().equals(""))
				{
					doShow("Plzz enter UserID!");
				}
				else if(txtP.getText().equals(""))
				{
					doShow("Plzz enter Passwoord!");
				}
				else if(txtU.getText().equals("System") || txtU.getText().equals("system"))
				{
					if(txtP.getText().equals("jaijava"))
					{
						new DashBoard();
						txtU.setText(null);
						txtP.setText(null);
					}
					else 
					{
						doShow("Incorrect password!");
					}
				}
				else
				{
					doShow("Incorrect userID");
				}
			});
			lblL.setOnMouseClicked(e->{
				new PwdFrgt();
			});
			Scene scene=new Scene(grd,600,600);
			stage.setScene(scene);
			stage.show();
		}
		void doShow(String msg)
		{
			Alert alert=new Alert(AlertType.INFORMATION);
			alert.setContentText(msg);
			alert.show();
		}


	}


