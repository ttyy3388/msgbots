package org.beuwi.msgbots.actions;

/* public class GetWebTextAction implements Action {
    @Override
    public void init() {
    }

    public static String execute(String url) {
        try {
            URLConnection connection = new URL(url).openConnection();

            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            connection.setConnectTimeout(5000);
            connection.setUseCaches(false);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String text = reader.readLine();
            String line = "";

            while ((line = reader.readLine()) != null) {
                text += "\n" + line;
            }

            reader.close();
            // connection.time;

            return text;
        }
        catch (Exception e) {
            DisplayErrorDialogAction.execute(e);
        }

        return null;
    }

    @Override
    public String getName() {
        return "get.web.text.action";
    }
} */