package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;

import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
//import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

//Use a Bar to show health and stuff.
public class Controller {
    @FXML
    private TextField Name;
    @FXML
    private ListView YouStatlist, OppStatList, PBPList;
    @FXML
    private Label WoL,Money, WoLRatio, Mode, HealthnumO, HealthnumU, IntelLabel, StrengthLabel,SpeedLabel;
    @FXML
    private ProgressBar YouH, OpponentH;
    @FXML
    private Button Punching, Kicking, Blocking;
    String name;
    private int Winner = 0;
    private int Game = 0;


    Battle b1 = new Battle();
    @FXML
    private void handleOppList(MouseEvent event){ //Not Coming In here!
        System.out.println("IN");
        //String temp = OppList.getSelectionModel().getSelectedItem().toString();
        //System.out.println(temp);
    }

    @FXML
    private void Reset(){
        b1.reset();
    }

    @FXML
    private void EzMode(){ //Sets the mode to a easy mode which has a simple Cpu Character and gives a smaller bounty to win.
        Mode.setText("Easy Mode");
        b1.ezMode();
        Punching.setVisible(false);
        Kicking.setVisible(false);
        Blocking.setVisible(false);
    }

    @FXML
    private void MlMode(){ //Sets the game to Machine Learning Mode Regular bounty.
        Mode.setText("AI Mode");
        b1.mlMode();
        Punching.setVisible(false);
        Kicking.setVisible(false);
        Blocking.setVisible(false);
    }

    @FXML
    private void ImMode(){ //Changes the CPU to a highly skilled fighter that has a advantege in everything Lots of money if you win.
        Mode.setText("Impossible Mode");
        b1.imMode();
        Punching.setVisible(false);
        Kicking.setVisible(false);
        Blocking.setVisible(false);
    }

    @FXML
    private void CheckBox(){//This Function puts a name to your character for the battle called from the Text Field Name.
        name = Name.getText();
    }

    @FXML
    private void BattleBtn(){
        WoLRatio.setText(Winner + " / " + Game);
        Game++;
        b1.Normfight(name);
        Punching.setVisible(true);
        Kicking.setVisible(true);
        Blocking.setVisible(true);
        UrStats();
        OpOStats();

    }

    @FXML
    private void IntelUp(){//This Function Puts the Intelligence level of your character up for $35
        String temp = Integer.toString(b1.UpgradeInt());
        IntelLabel.setText(temp);
        UrStats();
        OpOStats();
    }

    @FXML
    private void SpdUp(){//This Function Puts the Speed level of your character up for $35
        String temp = Integer.toString(b1.UpgradeSpd());
        SpeedLabel.setText(temp);
        UrStats();
        OpOStats();
    }


    @FXML
    private void StrUp(){//This Function Puts the Strength level of your character up for $50
        String temp = Integer.toString(b1.UpgradeStr());
        StrengthLabel.setText(temp);
        UrStats();
        OpOStats();
    }

    @FXML
    private void Punch(){
        PBPList.getItems().add(b1.Opponent(0));
        PBPList.getItems().add(b1.Punch());

        YouH.setProgress(YouHealth());
        OpponentH.setProgress(OppHealth());
    }
    @FXML
    private void Block(){
        PBPList.getItems().add(b1.Block(2));
        YouH.setProgress(YouHealth());
        OpponentH.setProgress(OppHealth());
    }
    @FXML
    private void Kick(){
        PBPList.getItems().add(b1.Opponent(1));
        PBPList.getItems().add(b1.Kick());
        YouH.setProgress(YouHealth());
        OpponentH.setProgress(OppHealth());

    }

    private Double YouHealth(){
        double temp = (b1.HealthU()/100);
        HealthnumU.setText(Double.toString(temp*100));
        if(temp == 0){
            Punching.setVisible(false);
            Kicking.setVisible(false);
            Blocking.setVisible(false);
            WoL.setText("Lose");
        }
        return temp/2;
    }

    private Double OppHealth(){
        double temp = (b1.HealthO()/100);
        HealthnumO.setText(Double.toString(temp*100));
        if(temp == 0){
            Punching.setVisible(false);
            Kicking.setVisible(false);
            Blocking.setVisible(false);
            WoL.setText("WIN");
            Winner++;
        }
        return temp/2;
    }

    private void UrStats(){
        YouStatlist.getItems().clear();
        YouStatlist.getItems().add("Strength = " + b1.StrengthUr());
        YouStatlist.getItems().add("Speed = " + b1.SpeedUr());
        YouStatlist.getItems().add("Intelligence = " + b1.IntelUr());
        Money.setText(b1.Cashloot());
    }

    private void OpOStats(){
        OppStatList.getItems().clear();
        OppStatList.getItems().add("Strength = " + b1.StrengthOp());
        OppStatList.getItems().add("Speed = " + b1.SpeedOp());
        OppStatList.getItems().add("Intelligence = " + b1.IntelOp());

    }
}

