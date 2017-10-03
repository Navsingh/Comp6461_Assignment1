import sun.rmi.runtime.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ParseUrl
{
public static void main(String args[]) throws IOException {

    String method = args[0];
    String response = null;
    String url = args[1];
    String verbose = args[2];
    URL uri = new URL(url);
    HttpURLConnection con = (HttpURLConnection) uri.openConnection();
    con.setRequestMethod(method);
    try {
        InputStream in = con.getInputStream();
        response = jsontoString(in);
    } catch (IOException e) {
        e.printStackTrace();
    }
    if(verbose!=null)
    {
        System.out.print("Request Method is " + con.getRequestMethod()+"\n"+con.getResponseCode()+"\n"+
        con.getPermission()+"\n"+
        con.getLastModified()+"\n"+
        con.getResponseMessage()+"\n"+con.getHeaderField("Content-Type")+"\n"+con.getHeaderField("Content-Length")+"\n");
    }
    System.out.print(response);
}
    //System.out.print(method+ url);

    private static String jsontoString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //  Log.d("json", stringBuilder.toString());
        return stringBuilder.toString();

    }

}

