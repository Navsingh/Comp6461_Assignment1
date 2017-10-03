import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Post {
    public static void main(String args[]) throws IOException {
        String method = args[0];
        String url1 = args[3];
        //String contenttype = args[1];
        String inlinedata = args[2];
        //System.out.print("ok"+args[0]);
       // URL url = new URL("http://httpbin.org/post");
        URL url = new URL(args[3]);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod(method);
        con.setDoInput(true);
        con.setRequestProperty("Content-Type",args[1]);
        con.setRequestProperty("charset", "utf-8");
        byte[] postdata = inlinedata.getBytes(StandardCharsets.UTF_8);
        int postlength = postdata.length;
        con.setRequestProperty("Content-Length",Integer.toString(postlength));
        con.setDoOutput(true);
        try
        {
            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            out.write(postdata);
            out.flush();
            out.close();
            System.out.println("ok");
            System.out.println(con.getResponseCode());
        }
         catch(IOException e)
         {
             e.printStackTrace();
         }

        InputStream inputStream = null;
        if (con != null) {
            inputStream = con.getInputStream();
        } else {
            throw new IOException("Connection is not established.");
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                inputStream));
        List<String> response = new ArrayList<String>();
          String line =null;
          while((line = reader.readLine())!=null)
        {
            response.add(line+"\n");
        }
        reader.close();
        System.out.println(response);
    }
}
