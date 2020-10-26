importPackage(org.jsoup);

importPackage(java.lang);
importPackage(java.net);
importPackage(java.io);

/**
 * (String) room
 * (String) sender
 * (Boolean) isGroupChat
 * (void) replier.reply(message)
 * (Boolean) replier.reply(room, message, hideErrorToast = true) // true 고정값 반환
 * (String) imageDB.getProfileBase64()
 * (String) packageName
 */
const DB =
{
	StartWord : [],
	WordList : [],
	GameData : [],

	load : function()
	{
		DB.StartWord = JSON.parse(DB.get("StartWord"));
		DB.WordList = JSON.parse(DB.get("WordList"));
		DB.GameData = JSON.parse(DB.get("GameData"));
	},

	get : function(type, value)
	{
		let connect = new URL("https://ttyy3388.develope.dev/getData.php?type=" + type + (value ? "&value=" + value : "")).openConnection();

		connect.setConnectTimeout(5000);
		connect.setUseCaches(false);

		let reader = new BufferedReader(new InputStreamReader(connect.getInputStream())),
			text = reader.readLine(), line = "";

		while ((line = reader.readLine()) > 0)
		{
			text += "\n" + line;
		}

		reader.close();
		connect.disconnect();
			
		return text.toString();
	},

	post : function(type, value)
	{
		Jsoup.connect("https://ttyy3388.develope.dev/postData.php").data("type", type, "value", value).method(Connection.Method.POST).execute().parse();
	},
};

function response(room, message, sender, isGroupChat, replier, imageDB, packageName) 
{
if (message.startsWith(">!"))
		{
			try
			{
			 	let start = new Date().getTime();
				
				replier.reply(eval(message.replace(">! ", "")));
				replier.reply(((new Date().getTime() - start) / 1000) + ".s");
			}
			catch (e) 
			{
				replier.reply
				(
					"Error Name : " + e.name + "\n\n" + 
					"Message : " + e.message + "\n\n" +
					// "Stack : " + e.stack + "\n\n" +
					"Line : " + e.lineNumber
				); 
			}
		}
}
