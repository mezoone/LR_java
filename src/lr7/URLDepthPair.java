package lr7;

import java.net.MalformedURLException;
import java.net.URL;

public class URLDepthPair {

    private String host;
    private int depth;
    private URL url;

    public URLDepthPair(String host, int depth) {
        this.host = host;
        this.depth = depth;
        try {
            url = new URL(host);
        } catch (MalformedURLException e) {
            System.err.println("MalformedURLException: " + e.getMessage());
            return;
        }
    }

    public String getURL() {
        return host;
    }

    public int getDepth() {
        return depth;
    }

    public String getDocPath() {
        return url.getPath();
    }

    public String getWebHost() {
        return url.getHost();
    }

    public String getLink() {
        return url.getProtocol()+"://"+url.getHost();
    }

    @Override
    public boolean equals(Object ob) {
        if (ob instanceof URLDepthPair) {
            URLDepthPair o = (URLDepthPair) ob;
            return this.host.equals(o.getURL());
        }
        return false;
    }
}