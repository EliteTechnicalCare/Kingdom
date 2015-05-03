package RS2.model.skills;

import RS2.model.player.Client;
import RS2.task.Task;
import RS2.task.TaskScheduler;
import RS2.util.Misc;

/**
* Woodcutting Pro
* @Author David Hakki
* @version 1.00
* Custom made woodcutting that isn't complete crap to make and use. 
*/

public class Woodcutting {
private Client c;
	
	public Woodcutting(Client client) {
		this.c = client;
	}
	//Constants and Variables
	public int VALID_TREE[] = {1315,1316,1307}; //All ids of valid trees
	public int VALID_AXE[] = {1349, 1351, 1353, 1355, 1357, 1359, 1361, 1363, 1365, 1367, 1369, 1371, 1373, 1375, 1377}; //All ids of valid trees
	public int VALID_ID[] = {};
	public boolean debug = true; //Enables debugging
	public void debug(String msg) {
		if(debug){
			c.sendMessage("[DEBUG]:"+msg);
		}
	}
	
	public boolean canWoodcut(){ //STEP 1: Determines if you meet the criteria to cut tree(s)
		if(!hasAxe()) {
			//c.getDH().sendStatement("You need an axe or battleaxe to begin cutting trees!");
			return false;
		}
		if(getLevelRequirements () > c.playerLevel[c.playerWoodcutting]){
		//	c.getDH().sendStatement("You must have atleast "+getLevelRequirements()+" to cut down this tree!");
			return false;
		}
		if(c.getItems().freeSlots() <= 0) {
		//	c.getDH().sendStatement("You're backpack is full! Please visit a local bank or drop some items.");
			return false;
		}
		debug("Player is ready to cut trees!");
		return true;
	}
	
	public void beginWoodcut(){ //STEP 2: Begins cutting the trees
		c.wcing = true;
		int logAmt = determineLogAmount();
		debug("Amount of logs you will recieve: "+logAmt);
		int logTime = determineLogTime();
		while(logAmt != 0 && c.wcing) {
			c.startAnimation(875);
			if(logTime == 0) {
				logAmt--;
				c.sendMessage("You've recieved a log");
				c.getItems().addItem(getLogID(), 1);
			} else {
				debug("Counting down until next log "+logTime);
				logTime--;
			}
		}
		if(logAmt == 0){
			stopWoodcut();//cuts down the tree
		}
		if(!c.wcing) {
		
		}
	}
	
	public boolean stopWoodcut(){ //STEP 3: Creates a stump, and resets wcing
	
		return false;
	}
	
	//ALL DEPENDANT METHODS BELOW ===USE CAUTION WHEN EDITING===
	private int getLogID () {
		int logID = -1;
		switch(c.logType) {
		case "regular":
			logID = 1511;
			break;
		case "oak":
			logID = 1521;
			break;
		case "maple":
			logID = 1517;
			break;
		}
		return logID;
	}
	private int determineLogTime() {
		int logTime = 999;
		switch(c.logType) {
		case "regular":
			logTime = Misc.random(1000-axeBonus()) - Misc.random((c.playerLevel[c.playerWoodcutting])/4);
			break;
		}
		return logTime;
	}
	
	private int axeBonus() {
		int axeBonus = 0;
		switch(c.axeType) {
		case "bronze":
			axeBonus = 1;
			break;
		case "iron":
			axeBonus = 5;
			break;
		case "steel":
			axeBonus = 10;
			break;
		}
		return axeBonus;
	}
	
	private int determineLogAmount() { //see how many logs you get
	int logAmount = 0;
	switch(c.logType){
	case "regular":
		logAmount = 1;
		break;
	case "oak":
		logAmount = 1 + Misc.random((c.playerLevel[c.playerWoodcutting])/4);
		break;
	case "maple":
		logAmount = Misc.random(5) + Misc.random((c.playerLevel[c.playerWoodcutting])/4);
		break;
	}
	return logAmount;
	}
	
	public String determineLogType(int ObjID){ //Determines what tree the person clicked on
		String logType = null;
		switch(ObjID) {
		case 1315:
		case 1316:
			logType = "regular";
			break;
		case 1307: 
			logType = "maple";
			break;
		}
		return logType;
	}
	
	private boolean hasAxe(){ //See if they have an axe
		for (int i = 0; i < VALID_AXE.length; i++) {
			if(c.playerEquipment[c.playerWeapon] == VALID_AXE[i]){
				if(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).startsWith("Bronze")){
					c.axeType = "bronze";
					debug("Wearing bronze axe");
				}
				return true;
			}
		}
		for (int i = 0; i < VALID_AXE.length; i++) {
			if(c.getItems().playerHasItem(VALID_AXE[i])){
				if(c.getItems().getItemName(VALID_AXE[i]).startsWith("Bronze")){
					c.axeType = "bronze";
					debug("Has a bronze axe");
				}
				return true;
			}
		}
		return false;
	}
	
	private int getLevelRequirements () {
		//Checks to see if you have the correct levels
		int level = 250;
		switch(c.logType) {
		case "regular":
			level = 0;
			break;
		case "oak":
			level = 15;
			break;
		case "maple":
			level = 35;
			break;
		}
		return level;
	}
}
