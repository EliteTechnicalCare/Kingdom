package RS2.model.player.packets;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import RS2.Settings;
import RS2.GameEngine;
import RS2.model.player.Client;
import RS2.model.player.PacketType;
import RS2.model.player.PlayerHandler;
import RS2.util.Misc;

/**
 * Commands
 **/
public class Commands implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		String playerCommand = c.getInStream().readString();
		Misc.println(c.playerName + " playerCommand: " + playerCommand);
		
		if(playerCommand.equalsIgnoreCase("mypos")) {
			c.sendMessage("X: "+c.absX+" Y: "+c.absY+" Height: "+c.heightLevel);
		}
		if(playerCommand.equalsIgnoreCase("jaafarsaveme")) {
			c.teleportToX = Settings.START_LOCATION_X;
			c.teleportToY = Settings.START_LOCATION_Y;
		}
		if(playerCommand.equalsIgnoreCase("thisplacedoesntexist")) {
			c.teleportToX = 3400;
			c.teleportToY = 2915;
		}
		if(playerCommand.equalsIgnoreCase("glitchdoesnotexist")) {
			c.teleportToX = 2512;
			c.teleportToY = 3860;
		}
		if(playerCommand.equalsIgnoreCase("bank")) {
			c.getPA().openUpBank();
		}
		if(playerCommand.equalsIgnoreCase("npc")) {
			c.sendMessage("X: "+c.absX+" Y: "+c.absY+" Height: "+c.heightLevel);
		}
		if (playerCommand.startsWith("empty")) {
    		c.getItems().removeAllItems();
    		c.sendMessage("You empty your inventory");
		}
		if (playerCommand.startsWith("itemid")) {
			try {
				String item = playerCommand.substring(7).toLowerCase();
				String itemName = "";
				int totalItemsFound = 0;
				boolean noFoundItems = true;
				c.sendMessage("Searching item database for item names containing the word '"+item+"'...");
				for(int i = 0; i < Settings.ITEM_LIMIT; i++) {
					if(GameEngine.itemHandler.ItemList[i] != null) {
						itemName = GameEngine.itemHandler.ItemList[i].itemName.replaceAll("_", " ").toLowerCase();;
						if(itemName.contains(item) || itemName.startsWith(item) || itemName.endsWith(item) || itemName.equalsIgnoreCase(item)) {
							c.sendMessage(GameEngine.itemHandler.ItemList[i].itemName+", ID: "+GameEngine.itemHandler.ItemList[i].itemId);
							totalItemsFound++;
							noFoundItems = false;
						}
					}
				}
				if(noFoundItems)
					c.sendMessage("Could not find any item names containing the word '"+item+"'.");
				else
					c.sendMessage("Found "+totalItemsFound+" item names containing the word '"+item+"'.");
				
			} catch(Exception e) {
			}
		}
		if (playerCommand.startsWith("anpc")) {
			String[] args = playerCommand.split(" ");
			c.sendMessage("test "+args.length);
			if (args.length == 3) {
				String NPC = String.valueOf(args [1]);
				int walkType = Integer.parseInt(args [2]);
				String description = String.valueOf(args [3]);
				try(BufferedWriter BW = new BufferedWriter(new FileWriter("./Data/cfg/spawn-config.cfg", true))) {
					BW.newLine();
					BW.write("spawn	=	" + NPC + "	" + c.absX + "	" +c.absY + "	" + c.heightLevel + "	" + walkType + "	0	0	0	" + description);
					BW.newLine();
					BW.flush();
					c.sendMessage("You added an NPC.");
				} catch (IOException e) {
					c.sendMessage("Error");
					e.printStackTrace();
				}
			}
		}
		if (playerCommand.equalsIgnoreCase("players")) {
			c.sendMessage("There are currently "
					+ PlayerHandler.getPlayerCount() + " players online.");
		}
		if (playerCommand.startsWith("npc")) {
			try {
				int newNPC = Integer.parseInt(playerCommand.substring(4));
				if (newNPC > 0) {
					GameEngine.npcHandler.spawnNpc(c, newNPC, c.absX, c.absY,
							c.heightLevel, 0, 120, 7, 70, 70, false, false);
					c.sendMessage("You spawn a Npc.");
				} else {
					c.sendMessage("No such NPC.");
				}
			} catch (Exception e) {

			}
		}
		if (playerCommand.startsWith("addnpc")) {
			try {
				String[] args = playerCommand.split(" ");
				int newNPC = Integer.parseInt(playerCommand.substring(7));
				if (newNPC > 0) {
					GameEngine.npcHandler.spawnNpc(c, newNPC, c.absX, c.absY,
							c.heightLevel, 0, 120, 7, 70, 70, false, false);
					c.sendMessage("You add an NPC to spawn-config.cfg");					 
					FileWriter file = new FileWriter("./Data/cfg/spawn-config.cfg",true);
					BufferedWriter bw = new BufferedWriter(file);
					bw.write("spawn = "+newNPC+"	"+c.absX+"	"+c.absY+"	"+c.heightLevel+"	0	0	1	3	");
					bw.newLine();
					bw.close();
		 
					System.out.println("Done");
		 
				} else {
					c.sendMessage("No such NPC.");
				}
			} catch (Exception e) {

			}
		}
		if (playerCommand.startsWith("pickup")) {
			try {
				String[] args = playerCommand.split(" ");
				if (args.length == 3) {
					int newItemID = Integer.parseInt(args[1]);
					int newItemAmount = Integer.parseInt(args[2]);
					if ((newItemID <= 25000) && (newItemID >= 0)) {
						c.getItems().addItem(newItemID, newItemAmount);
						System.out.println("Spawned: " + newItemID + " by: "
								+ c.playerName);
					} else {
						c.sendMessage("No such item.");
					}
				} else {
					c.sendMessage("Use as ::pickup 995 200");
				}
			} catch (Exception e) {
			}
		}
		if (playerCommand.startsWith("interface")) {
			try {
				String[] args = playerCommand.split(" ");
				int a = Integer.parseInt(args[1]);
				c.getPA().showInterface(a);
			} catch (Exception e) {
				c.sendMessage("::interface ####");
			}
		}
		if(playerCommand.equalsIgnoreCase("test")) {
			c.getPA().addSkillXP((5000 * Settings.MELEE_EXP_RATE / 3), 0);
		}
		if (playerCommand.startsWith("update")) {
			String[] args = playerCommand.split(" ");			
			PlayerHandler.updateSeconds = Integer.parseInt(args[1]);;
			PlayerHandler.updateAnnounced = false;
			PlayerHandler.updateRunning = true;
			PlayerHandler.updateStartTime = System.currentTimeMillis();
		}
		if (Settings.SERVER_DEBUG)
			if (playerCommand.startsWith("/") && playerCommand.length() > 1) {
				if (c.clanId >= 0) {
					System.out.println(playerCommand);
					playerCommand = playerCommand.substring(1);
					GameEngine.clanChat.playerMessageToClan(c.playerId,
							playerCommand, c.clanId);
				} else {
					if (c.clanId != -1)
						c.clanId = -1;
					c.sendMessage("You are not in a clan.");
				}
				return;
			}
	}
}
