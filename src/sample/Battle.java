package sample;
//Sets the modes for the classes
//Precondition: The mode that the user wants
//Postcondition:Changes stats and things as game goes on.

import javax.naming.Name;
import java.awt.*;
import java.util.ArrayList;
public class Battle {
    //CAP STATS AT A CERTAIN NUMBER
    private int LOC=-1;
    private int StrengthU = 1;
    private int SpeedU = 1;
    private int IntellU = 1;
    private int StrengthO = 1;
    private int SpeedO = 1;
    private int IntellO = 1;
    private int YouHealth;
    private int OppHealth;
    private int Uchance;
    private int Ochance;
    private ArrayList<YouStat> You = new ArrayList<>(); //Array with your character stats
    private ArrayList<YouStat> Opp = new ArrayList<>(); //Array with opponent character stats
    private ArrayList<Integer> Allmoves = new ArrayList<>(); // ArrayList with the Moves that the User makes
    private int RecentMove;
    private String name;
    private int cash = 400;
    private boolean HardCheat = false;
    private boolean MachineLearning = false;
    private boolean BlockOpp = false;
    private int temper;
    private int Usertemper;
    private Boolean Turn = false;
    private Boolean UserMove = true; //If false then kick, if True than Punch

    //Reset button
    public void reset(){

        if (HardCheat == true){ //Hard mode is on
            HardCheat = true;
        }
        if (MachineLearning == true){ // Machine Learning is on
            MachineLearning = true;
        }
        LOC=-1;
        StrengthU = 1;
        SpeedU = 1;
        IntellU = 1;
        You.clear();
        Opp.clear();
        Allmoves.clear();
        cash = 400;
        BlockOpp = false;
        Turn = false;
    }


    //Sets the mode
    public void ezMode(){
        StrengthO += 3;
        SpeedO += 3;
        IntellO += 3;
    }

    public void mlMode(){
        StrengthO += 1;
        SpeedO += 1;
        IntellO += 1;
        Allmoves.add(0);
        Allmoves.add(3);
        Allmoves.add(1);
        Allmoves.add(3);
        Allmoves.add(2);
        Allmoves.add(3);
        MachineLearning = true;

    }

    public void imMode(){
        StrengthO += 4;
        SpeedO += 5;
        IntellO += 4;
        HardCheat = true;
    }


    //Starts the fught by initizalizing vars
    public void Normfight(String name){//Precondition: The Stats of the user and CPU
        // Postcondition: The stats are weighed down into one stat that makes random percentage chances for winning and losing
        //IMPORTANT Comments!!!!!!!
        this.name = name;
        You.add(new YouStat(StrengthU,SpeedU,IntellU));
        Opp.add(new YouStat(StrengthO,SpeedO,IntellO));
        LOC++;
        Uchance = You.get(LOC).Uaverage(); //One number that holds all the stats at average to determine battle //How would you upgrade this if the stats are called after the mode is set. USE A VARIABLE FOR THE STATS
        Ochance = Opp.get(LOC).Uaverage(); //Same as Uchance but for the opponent
        YouHealth = 200;
        OppHealth = 200;
    }


    //this set of functions controlls the moves for the fights
    public String Punch(){
        UserMove = true;
        Allmoves.add(0);
        RecentMove = 0;
        String Comms = "";
        if(Turn == true){ // Check if its your turn or not
            Turn = false;
            int temp = (int)Math.floor(Math.random()*99+1); //Random number between 1-100 for
            if (HardCheat == true){
                temp = (int)Math.floor(Math.random()*(Uchance*30)+1);//The chances are changed if this is hard mode
            }
            if(temp<(Uchance*12.5)){ //random 1 - 100 if this is less than the average chance number*10 only then the attack is successful
                int temp3;
                int temp2 = (int)Math.floor(Math.random()*(IntellU-1)+1);
                if(temp2<IntellU/3){ // If a number is less than a third of the Strength U catagory then it does 2x the damage
                    temp3 = (StrengthU*2);
                    OppHealth-= temp3;
                }
                if (temp2>IntellU/2){// If the number is greater than 1/3 but less than a half of Strength U then 4 times damage
                    temp3 = (StrengthU*4);
                    OppHealth-=temp3;
                }
                else{ // If the number is greater than 1/2 if the Strength U that 3X the damage;
                    temp3 = StrengthU*3;
                    OppHealth-=temp3;
                }
                Comms = name +" punched the Opponent for " + temp3 + " Health";
                temper = (int)Math.floor(Math.random()*3);//Randomly decides if the Opponent wants to punch or kick or dodge;
            }
            else{ // If the number is not in the chances then the Opponent dodged the attack.
                Comms = "You Missed the Kick";
            }
        }
        return Comms;
    }

    public String Kick(){
        UserMove = false;
        Allmoves.add(1);
        RecentMove = 1;
        String Comms = "";
        if(Turn == true){ // Check if its your turn or not
            Turn = false;
            int temp = (int)Math.floor(Math.random()*99+1); //Random number between 1-100 for

            if (HardCheat == true){
                temp = (int)Math.floor(Math.random()*(Uchance*30)+1);//The chances are changed if this is hard mode
            }
            if(temp<(Uchance)*12.5){ //random 1 - 100 if this is less than the average chance number*9 only then the attack is successful
                int temp3;
                int temp2 = (int)Math.floor(Math.random()*(IntellU-1)+1);
                if(temp2<IntellU/3){ // If a number is less than a third of the intell U catagory then it does 2x the damage
                    temp3 = (StrengthU*3);
                    OppHealth-= temp3;
                }
                if (temp2>IntellU/2){// If the number is greater than 1/3 but less than a half of intellU then 4 times strength damage
                    temp3 = (StrengthU*5);
                    OppHealth-=temp3;
                }
                else{ // If the number is greater than 1/2 if the Strength U that 3X the damage;
                    temp3 = StrengthU*4;
                    OppHealth-=temp3;
                }
                Comms = name +" Kicked the Opponent for " + temp3 + " Health";
            }
            else{ // If the number is not in the chances then the Opponent dodged the attack.
                Comms = "You Missed the Kick";
            }
        }
        temper = (int)Math.floor(Math.random()*3);//Randomly decides if the Opponent wants to punch or kick or dodge;
        return Comms;

    }

    public String Block(int UTem){ //The block code should use the speed function to create the chances of block.
        Usertemper = UTem; // This holds the users move
        Allmoves.add(2);
        RecentMove = 2;
        String Comms = "";
        Turn=false; // Changes it to Opponents Turn
        int temp = (int)Math.floor(Math.random()*(9)+1); //Random number out of 10 to compare with the Speed;
        if (HardCheat == true){
            temp = (int)Math.floor(Math.random()*(12)+1);
        }
        if(temp<SpeedU){
            Comms = "You have dodged the Opponents Attack";
            Turn = true;
        }
        else{
            Comms = Opponent(Usertemper);
        }
        temper = (int)Math.floor(Math.random()*3);//Randomly decides if the Opponent wants to punch or kick or dodge;
        return Comms;
    }

    public String Opponent(int UTem){
        Usertemper = UTem; // Holds the users move
        String Comms = "";
        int attack = 0;
        if(MachineLearning == true){ // So When the code is run the temper or the function that chooses what the computer wants to do is run if it is run and the number us 2, then the opponent wants to block
            // which is only a part of the machine learning process so if the computer does this then the code for attack doesnt run which means that Turn = true and the code runs through a block function
            // that can predict the Users next hit from previous data.  If the computer wants to run block , then the opponents move is declared as block keeping the True as True and the code is run to see
            // what the user does if the User blocks then both the User and Opponent Blocked, if the user punches then his punch is run through the block code and if unsuccessful, the code say that the
            // Opponent Predicted and Blocked the Punch.  The code run through should be storing the users moves in a array nad comparing it to another array where the Computer Searches through for indexes
            // of the data and predicts the next step. This code should be running at all times so the user can decide to dodge attacks and when the user dodges, to attack.
            temper = MachineOn();
        }
        if(temper == 0){
            attack = 3; // A punch
        }
        else if (temper == 1){
            attack = 4; //A kick
        }
        else{
            Comms = BlockO();
        }
        if (Turn == false){
            Turn = true;
            int temp = (int)Math.floor(Math.random()*99+1); //Random number between 1-100 for
            if (HardCheat == true){
                temp = (int)Math.floor(Math.random()*(Ochance*10)+10); //The chances are changed if this is hard mode
                if(temp >100){
                    temp = 99;
                }
            }
            if(temp<((Ochance)*10)){ //random 1 - 100 if this is less than the average chance number*9 only then the attack is successful
                int temp3;
                int temp2 = (int)Math.floor(Math.random()*(IntellO-1)+1);
                if(temp2<IntellO/3){ // If a number is less than a third of the intellO catagory then it does 2x the damage
                    temp3 = ((StrengthO+1)*(attack));
                    YouHealth-= temp3;
                }
                else if (temp2>IntellO/2){// If the number is greater than 1/3 but less than a half of intellO then 4 times strength damage
                    temp3 = ((StrengthO+1)*(attack+2));
                    YouHealth-=temp3;
                }
                else{ // If the number is greater than 1/2 if the Strength O that 3X the damage;
                    temp3 = (StrengthO+1)*(attack+3);
                    YouHealth-=temp3;
                }
                Comms = "The Opponent kicked you for " + temp3 + " Health";
            }
            else{ // If the number is not in the chances then the Opponent dodged the attack.
                Comms = "The Opponent missed the Kick!";
            }
        }
        Turn = true;
        return Comms;
    }

    private String BlockO(){
        String Comms = "";
        int temp = (int)Math.floor(Math.random()*(9)+1); //Random number out of 10 to compare with the Speed;
        if(temp<=(SpeedO+1)){
            String input = "";
            if (Usertemper == 0){
                input = "Punch";
            }
            else if(Usertemper == 1){
                input = "Kick";
            }
            Comms = "BlockO The Opponent Dodged a " + input + " From " + name;
            if (Usertemper == 2){
                Comms = "BlockO The Opponent Dodged nothing from " + name;
            }
        }
        else{
            if (Usertemper !=2){
                Comms = "BlockO The Opponent missed the dodge";
            }
            else{
                Comms = "BlockO The Opponent Dodged nothing from " + name;
            }
        }
        Turn = true;
        return Comms;
    }


    private int MachineOn(){ //This Function Should be upgrading stats and guessing next attacks Changes the fight to machine learning mode
        Allmoves.add(3);
        int nextmove = 0;
        int Attacks = 0; //0
        int Dfence = 0; //1
        if(MachineLearning == true){
            for (int i = 0;i<=Allmoves.size()-1; i++){//U have all the Moves in the code and U search for the Same Move made in the array in the past, if the move is found then u compare the past move, if the past move is the same, you keep on searching until u find a diffrence, U keep this saved in the class
                if(Allmoves.get(i) == RecentMove){
                    int Predict = Allmoves.get(i+1);
                    if (Predict == 0|| Predict == 1){
                        Attacks++;// User wants to Attack;
                    }
                    else if(Predict == 2){
                        Dfence++;//User wants to Block
                    }
                }
            }
        }
        Boolean Offence;

        if (Dfence>Attacks){ //If (User Defense>User Offence)
            Offence = true; // Attack
        }
        else{//If !(User Defence>User Offence)
            Offence = false; //Blocking
        }

        if(Offence ==true){ //If the next move is to block then attack
            int temp4 = (int)Math.floor((Math.random()*1));
            nextmove = temp4;
        }
        else{// If the next move is to attack, then block
            nextmove = 2;
        }
        return nextmove;
    }


    //The health of the opponent and you, also ends the game
    public Double HealthU(){
        int temp = YouHealth;
        if(temp<=0){
            temp = 0;
            cash+=50;
        }
        return ((double)temp);
    }

    public Double HealthO(){
        int temp = OppHealth;
        if(temp<=0){
            temp = 0;
            cash+=100;
            if (MachineLearning == true){
                int temp2 = (int)Math.floor(Math.random()*2);
                if (temp2 == 0){
                    StrengthO+=2;
                }
                else if (temp2 == 1){
                    SpeedO +=2;
                }
                else{
                    IntellO+=2;
                }

                if (StrengthO >8){
                    StrengthO = 8;
                }
                if (SpeedO > 8){
                    SpeedO++;
                }
                if (IntellO>8){
                    IntellO++;
                }
            }
        }
        return ((double)temp);
    }


    //The upgrade part of the code
    public int UpgradeStr(){
        if (cash>=50 && StrengthU<8){
            StrengthU++;
            cash-=50;
            if (cash <=0){
                cash=0;
            }
        }
        Midgame();
        return StrengthU;
    }

    public int UpgradeSpd(){
        if (cash>=35 && SpeedU < 7){
            SpeedU++;
            cash-=35;
            if (cash <=0){
                cash=0;
            }
        }
        Midgame();
        return SpeedU;
    }

    public int UpgradeInt(){
        if (cash>=35 && IntellU < 7){
            IntellU++;
            cash-=35;
            if (cash <=0){
                cash=0;
            }
        }
        Midgame();
        return IntellU;
    }


    //Sends the upgrade stats to the controller
    public int StrengthUr(){
        return StrengthU;
    }

    public int SpeedUr(){
        return SpeedU;
    }

    public int IntelUr(){
        return IntellU;
    }


    //Sends the money to the controller
    public String Cashloot(){
        return Integer.toString(cash);
    }


    //Sends the Opponents stats to the controller
    public int StrengthOp(){
        return StrengthO;
    }

    public int SpeedOp(){
        return SpeedO;
    }

    public int IntelOp(){
        return IntellO;
    }


    private void Midgame(){
        You.add(new YouStat(StrengthU,SpeedU,IntellU));
        Opp.add(new YouStat(StrengthO,SpeedO,IntellO));
        LOC++;
    }


} //DOCUMENTATION MORE PLAYERS FIX BLOCKING
