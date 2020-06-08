package lr7;

import java.io.*;
import java.net.*;
import java.util.LinkedList;




public class Crawler {

    private String prefix = "http";
    private int depth;  //Глубина поиска
    private String host; // Адрес страницы

    private LinkedList<URLDepthPair> checked = new LinkedList<>(); // Список с проверенными ссылками
    private LinkedList<URLDepthPair> unchecked = new LinkedList<>(); // Список с непроверенными ссылками

    public Crawler(String host, int depth) {
        this.host = host;
        this.depth = depth;
        unchecked.add(new URLDepthPair(this.host, this.depth));
    }

    public void Search() throws IOException {
        URLDepthPair pair;
        Socket socket;
        int s=0;

        while (unchecked.size() > 0) {
            pair = unchecked.removeFirst();
            checked.add(pair);
            System.out.printf("%-6s%-70s%5s%n",++s + ": " , pair.getURL(),"["+pair.getDepth()+"]");
            if (pair.getDepth() == 0) continue;
            try {
                socket = new Socket(pair.getWebHost(), 80);
            } catch (UnknownHostException e) {
                System.err.println(e);
                continue;
            }
            socket.setSoTimeout(4000);

            PrintWriter myWriter = new PrintWriter(socket.getOutputStream(), true);
            myWriter.println("GET " + pair.getDocPath() + " HTTP/1.1");
            myWriter.println("Host: " + pair.getWebHost());
            myWriter.println("Connection: close");
            myWriter.println();

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String input;

            while ((input = reader.readLine()) != null) {
                while (input.contains("a href=\"")) {
                    String link;
                    try {
                        input = input.substring(input.indexOf("a href=\"") + 8);
                        link = input.substring(0, input.indexOf('\"'));
                        if (!link.startsWith(prefix))
                            link = link.startsWith("/") ? pair.getLink() + link : pair.getLink() + "/" + link;
                    } catch (StringIndexOutOfBoundsException e) {
                        break;
                    }
                    if (!(unchecked.contains(new URLDepthPair(link, pair.getDepth() - 1)) | checked.contains(new URLDepthPair(link, pair.getDepth() - 1))))
                        unchecked.add(new URLDepthPair(link, pair.getDepth() - 1));

                }
            }
            reader.close();
            socket.close();
        }
    }

    public static void main(String msi[]) throws IOException {
        new Crawler(msi[0], Integer.parseInt(msi[1]))
                .Search();

    }
}
