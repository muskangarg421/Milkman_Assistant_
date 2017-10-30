package milkman_assistant;

import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PwdFrgt 
{

	Text lblM;
	ImageView btnSMS;
	TextField txtM;
	GridPane grd;
	PwdFrgt() 
	{
		lblM=new Text("Mobile No.");
		lblM.setFont(Font.font(15));
		txtM=new TextField();
		txtM.setPrefWidth(200);
		
		btnSMS=new ImageView(new Image(Bill.class.getResourceAsStream("bsms.png")));
		btnSMS.setFitHeight(50);
		btnSMS.setFitWidth(100);
		
		DropShadow ds=new DropShadow();
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
		grd.setVgap(5);
		grd.setHgap(5);
		grd.setPadding(new Insets(100));
		grd.setAlignment(Pos.CENTER);
		
		
		GridPane.setConstraints(lblM, 0 , 0, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(15));
		grd.getChildren().add(lblM);
		GridPane.setConstraints(txtM, 1 , 0, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(15));
		grd.getChildren().add(txtM);
		GridPane.setConstraints(btnSMS, 0 , 1, 2, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(15));
		grd.getChildren().add(btnSMS);
		
		btnSMS.setOnMouseClicked(e->{
			if(txtM.getText().isEmpty())
			{
				doShow("Plzz enter mobile no.");
			}
			else{
				String s=SST_SMS.bceSunSoftSend(txtM.getText(),"UserID: System,Password: jaijava");
				System.out.println(s); 
			}
		});
		Scene scene=new Scene(grd,600,400);
		Stage stage=new Stage();
		stage.setScene(scene);
		stage.show();
	}
	static void doShow(String msg)
	{
		Alert alert=new Alert(AlertType.INFORMATION);
		alert.setContentText(msg);
		alert.show();
	}

}
