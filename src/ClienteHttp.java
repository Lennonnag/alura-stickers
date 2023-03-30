import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class ClienteHttp {

    public String dataSeek(String url){

        try {            
            URI link = URI.create(url); // converter para uri
            var client = HttpClient.newHttpClient(); //
            var request = HttpRequest.newBuilder(link).GET().build();
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            String body = response.body();
            return body;
        } catch (IOException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }
    
}
