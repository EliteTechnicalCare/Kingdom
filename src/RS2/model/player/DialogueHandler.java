package RS2.model.player;

import RS2.Settings;

@SuppressWarnings("all")
public class DialogueHandler {

	private Client c;
	
	public DialogueHandler(Client client) {
		this.c = client;
	}
	
	public void sendDialogues(String name) { //Hakki Engineering Exclusive NPC Dialog system
		switch(name) {
		//Financial Advisor
		case "Financial Advisor":
			switch(c.talkID) {
			case 0:
				NPC("Welcome to "+Settings.SERVER_NAME+" I am your", "personal Financial Advisor, here to make you rich!");
				break;
			case 1:
				player("Sounds good, what are some ways to make money?");
				break;
			case 2: 
				NPC("SIKE I AINT HELPING YOUs1");
				c.talkID = -1;
				break;
			}
			break;
		//Banker
		case "Banker":
			switch(c.talkID) {
			case 0:
				NPC("Hello, one moment while I open your bank");
				break;
			}
			break;
		//Seddu
		case "Seddu":
			switch(c.talkID) {
			case 0:
				NPC("Hello my brozer, I sell gear zat will help you stay alive", "in ze desert. The gear I sell will help you be protected from",
						"the heat of the sun and allowed you to drink less.");
				break;
			case 1:
				player("How can I tell if an item is better/worse for water?");
				break;
			case 2:
				NPC("If you click on za player controls,", "you will see za water and protection stats.", "Don't forget me delicious batatas!");
				break;
			case 3:
				c.talkID = -1;
				c.getShops().openShop(0);
				break;
			}
			break;
			//Kazemde
		case "Kazemde":
			switch(c.talkID) {
			case 0:
				NPC("By Al-Lat, Attar, and Uzer I have the best herb and magic items.",
						"We are also the Skirkat's main supplier!");
				break;
			case 1: 
				player("");
				break;
			case 3:
				c.talkID = -1;
				c.getShops().openShop(0);
				break;
			}
			break;
	}
}

	
	/*
	 * Information Box
	 */
	
	public void sendStartInfo(String text, String text1, String text2, String text3, String title) {
		c.getPA().sendFrame126(title, 6180);
		c.getPA().sendFrame126(text, 6181);
		c.getPA().sendFrame126(text1, 6182);
		c.getPA().sendFrame126(text2, 6183);
		c.getPA().sendFrame126(text3, 6184);
		c.getPA().sendFrame164(6179);
	}
	
	/*
	 * Options
	 */
	
	public void sendOption(String s) {
		c.getPA().sendFrame126("Select an Option", 2470);
	 	c.getPA().sendFrame126(s, 2471);
		c.getPA().sendFrame126("Click here to continue", 2473);
		c.getPA().sendFrame164(13758);
	}	
	
	public void sendOption(String s, String s1) {
		c.getPA().sendFrame126("Select an Option", 2460);
		c.getPA().sendFrame126(s, 2461);
		c.getPA().sendFrame126(s1, 2462);
		c.getPA().sendFrame164(2459);
	}
	
	public void sendOption(String s, String s1, String s2) {
		c.getPA().sendFrame126("Select an Option", 2470);
		c.getPA().sendFrame126(s, 2471);
		c.getPA().sendFrame126(s1, 2472);
		c.getPA().sendFrame126(s2, 2473);
		c.getPA().sendFrame164(2469);
	}
	
	public void sendOption(String s, String s1, String s2, String s3) {
		c.getPA().sendFrame126("Select an Option", 2481);
		c.getPA().sendFrame126(s, 2482);
		c.getPA().sendFrame126(s1, 2483);
		c.getPA().sendFrame126(s2, 2484);
		c.getPA().sendFrame126(s3, 2485);
		c.getPA().sendFrame164(2480);
	}
	
	public void sendOption(String s, String s1, String s2, String s3, String s4) {
		c.getPA().sendFrame126("Select an Option", 2493);
		c.getPA().sendFrame126(s, 2494);
		c.getPA().sendFrame126(s1, 2495);
		c.getPA().sendFrame126(s2, 2496);
		c.getPA().sendFrame126(s3, 2497);
		c.getPA().sendFrame126(s4, 2498);
		c.getPA().sendFrame164(2492);
	}

	/*
	 * Statements
	 */
	
	private void sendStatement(String s) { // 1 line click here to continue chat box interface
		c.getPA().sendFrame126(s, 357);
		c.getPA().sendFrame126("Click here to continue", 358);
		c.getPA().sendFrame164(356);
	}
	
	/*
	 * Npc Chatting
	 */
	
	private void NPC(String s) {
		c.getPA().sendFrame200(4883, 591);
		c.getPA().sendFrame126(c.npcTalkName, 4884);
		c.getPA().sendFrame126(s, 4885);
		c.getPA().sendFrame75(c.npcTalkID, 4883);
		c.getPA().sendFrame164(4882);
	}
	
	private void NPC(String s, String s1) {
		c.getPA().sendFrame200(4888, 591);
		c.getPA().sendFrame126(c.npcTalkName, 4889);
		c.getPA().sendFrame126(s, 4890);
		c.getPA().sendFrame126(s1, 4891);
		c.getPA().sendFrame75(c.npcTalkID, 4888);
		c.getPA().sendFrame164(4887);
	}

	private void NPC(String s, String s1, String s2) {
		c.getPA().sendFrame200(4894, 591);
		c.getPA().sendFrame126(c.npcTalkName, 4895);
		c.getPA().sendFrame126(s, 4896);
		c.getPA().sendFrame126(s1, 4897);
		c.getPA().sendFrame126(s2, 4898);
		c.getPA().sendFrame75(c.npcTalkID, 4894);
		c.getPA().sendFrame164(4893);
	}
	
	private void NPC(String s, String s1, String s2, String s3) {
		c.getPA().sendFrame200(4901, 591);
		c.getPA().sendFrame126(c.npcTalkName, 4902);
		c.getPA().sendFrame126(s, 4903);
		c.getPA().sendFrame126(s1, 4904);
		c.getPA().sendFrame126(s2, 4905);
		c.getPA().sendFrame126(s3, 4906);
		c.getPA().sendFrame75(c.npcTalkID, 4901);
		c.getPA().sendFrame164(4900);
	}
	
	/*
	 * Player Chating Back
	 */
	
	private void player(String s) {
		c.getPA().sendFrame200(969, 591);
		c.getPA().sendFrame126(c.playerName, 970);
		c.getPA().sendFrame126(s, 971);
		c.getPA().sendFrame185(969);
		c.getPA().sendFrame164(968);
	}
	
	private void player(String s, String s1) {
		c.getPA().sendFrame200(974, 591);
		c.getPA().sendFrame126(c.playerName, 975);
		c.getPA().sendFrame126(s, 976);
		c.getPA().sendFrame126(s1, 977);
		c.getPA().sendFrame185(974);
		c.getPA().sendFrame164(973);
	}
	
	private void player(String s, String s1, String s2) {
		c.getPA().sendFrame200(980, 591);
		c.getPA().sendFrame126(c.playerName, 981);
		c.getPA().sendFrame126(s, 982);
		c.getPA().sendFrame126(s1, 983);
		c.getPA().sendFrame126(s2, 984);
		c.getPA().sendFrame185(980);
		c.getPA().sendFrame164(979);
	}
	
	private void player(String s, String s1, String s2, String s3) {
		c.getPA().sendFrame200(987, 591);
		c.getPA().sendFrame126(c.playerName, 988);
		c.getPA().sendFrame126(s, 989);
		c.getPA().sendFrame126(s1, 990);
		c.getPA().sendFrame126(s2, 991);
		c.getPA().sendFrame126(s3, 992);
		c.getPA().sendFrame185(987);
		c.getPA().sendFrame164(986);
	}
}
