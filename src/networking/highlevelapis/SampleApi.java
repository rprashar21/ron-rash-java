package networking.highlevelapis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class SampleApi {

    // why hgo high level apis ??
    // If You Used Low-Level APIs Like Socket or ServerSocketChannel
    //You would need to:
    //Manually write "GET / HTTP/1.1\r\nHost: localhost\r\n\r\n" into the output stream
    //Manually read and parse the HTTP response
    //Manage socket lifecycles, buffers, and thread handling
    //Handle HTTP formatting, errors, and protocol nuances
    //⚠️ It would be verbose, error-prone, and not worth it for basic web requests

    // in hogh level apis java deos the work for u under the hood Java does all the work under the hood
    //u don’t need to care about how connections are opened, or how headers are formatted — it’s already handled


    // high level api -- uri - unifrom resource locator

    // urn url -- uniform resource name ,locator they both are subsets of uri

    // url -- it identfies and locates resource on network,,include name of the resource,protocol and address like domain name and path
    // uri strings mayconatin relative paths but urls dont
    // url class represent a url,, its pointer to a resource in www/internet -- resource can be file,directory/query/search engine

    //URI is made up of [scheme:]scheme-specific-part[#fragment]
    // [//authority][path][?query] authority component has userinfo@host[:port]

    // i java url is not a subclass of uri -- if u want to convert uri to url then use toURL on uri instance or toUri

    // what is a relative uri -- reference t a resource such as web page file or image that is relative to the current contexts
    // to convert a uri to url uri must be absolute

    public static void main(String[] args) throws IOException {
//        URI uri = URI.create("https://www.google.com/courses/complet-java-class/courses?java=threads");
//        print(uri);

        // some example of url the url class
        // uRl.openStream() - takes care of all the connection details for me and returned an input stream which retrieved the web page's html
        URL url = new URL("http://example.com");
        // printContents(uRl.openStream()); // open a connection and read contest froma website or server
        URLConnection urlConnection = url.openConnection(); // this doesnt open the connection this will just give us data
        // it has a class HTTUrlconnection to connect to webstes and sue them
        System.out.println(urlConnection.getContentType()); // text/html; charset=UTF-8 content type of the server
        System.out.println(urlConnection.getContentLength());
        Map<String, List<String>> headerFields = urlConnection.getHeaderFields();
        for (Map.Entry<String, List<String>> entry : headerFields.entrySet()) {
            // System.out.println(entry.getKey() + ": " + entry.getValue());

        }
        System.out.println(urlConnection.getURL());
        System.out.println(urlConnection.getHeaderField("Cache-Control"));

        urlConnection.connect(); // this will connect
    }

    private static void printContents(InputStream inputStream) {

        System.out.println("input stream is " + inputStream);
        //inut stream -->  input data which will come in bytes
        //InputStreamReader wil convert the bytes to charaters
        //BufferedReader will read the characters in a line
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);

            }


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void print(URI uri) throws MalformedURLException {
        System.out.println(uri.getScheme()); // https
        System.out.println(uri.getSchemeSpecificPart()); // //www.google.com/
        System.out.println(uri.getFragment());
        System.out.println(uri.getPath());
        System.out.println("query is ::" + uri.getQuery());
        System.out.println(uri.getAuthority());
        System.out.println(uri.getUserInfo());
        System.out.println(uri.getHost());
        System.out.println(uri.getPort());
        System.out.println(uri.getPath());
        System.out.println(uri.getScheme());
        System.out.println(uri.getPath());

        System.out.println("uri to url uri must be absolute " + uri.toURL());
    }
}
