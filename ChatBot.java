package Part2;

public class ChatBot {
	public static void main(String[] args) throws Exception {
		
		bot chatBot = new bot();
		
		chatBot.setVerbose(true); //Enable debugging output
		chatBot.connect("irc.freenode.net"); //Gives the IRC server address of where the bot should connect to
		chatBot.joinChannel("#pircbot"); //Tells which channel for the bot to join (#pircbot channel)
		chatBot.sendMessage("#pircbot", "Hello! Nice to meet you, and feel free to talk to me!"); //Message to be sent initially to the user
		
	}

}
