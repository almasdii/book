package SpringMVC;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .executor(executorService)
                .build();

        String string = "http://api.openweathermap.org/geo/1.0/direct?q=Almaty&limit=5&appid=d5c6861bc694bf12e0d19183e6e07195";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(string))
                .GET()
                .build();

        CompletableFuture<HttpResponse<String>> responseCompletableFuture = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());


        CompletableFuture<String> stringCompletableFuture = responseCompletableFuture.thenApply(HttpResponse::body);
        HttpResponse<String> stringHttpResponse = responseCompletableFuture.get();
        System.out.println(stringCompletableFuture.get());
        System.out.println(stringHttpResponse.statusCode());


        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(stringCompletableFuture.get());

        List<Location> locations = mapper.readValue(stringCompletableFuture.get(), new TypeReference<List<Location>>() {
        });
        System.out.println(locations);

        for (Location location : locations) {
            String format = String.format("https://api.openweathermap.org/data/2.5/weather?lat=%.6f&lon=%.6f&appid=d5c6861bc694bf12e0d19183e6e07195", location.getLat(), location.getLon());
            HttpRequest request2 = HttpRequest.newBuilder()
                    .uri(URI.create(format))
                    .GET()
                    .build();
            CompletableFuture<HttpResponse<String>> responseCompletableFuture1 = httpClient.sendAsync(request2, HttpResponse.BodyHandlers.ofString());
            String body = responseCompletableFuture1.get().body();
            JsonNode jsonNode1 = mapper.readTree(body);
            JsonNode main = jsonNode1.get("main");
            int humidity = main.get("humidity").asInt();
            BigDecimal feelsLike = main.get("feels_like").asDecimal();
            String name = jsonNode1.get("name").asString();
            BigDecimal temp = main.get("temp").asDecimal();
            System.out.println("humidity = " + humidity + " feelsLike = " + feelsLike.subtract(new BigDecimal("273.15")) + " " + " name =  " + name + " temp = " + temp.subtract(new BigDecimal("273.15")));


        }
    }
}

@Getter
@Setter
@RequiredArgsConstructor
@ToString
class Location {
    String name;
    Float lon;
    Float lat;
    String state;
}

class LocationDto{

}