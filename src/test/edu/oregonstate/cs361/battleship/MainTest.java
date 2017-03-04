package edu.oregonstate.cs361.battleship;

import com.google.gson.Gson;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import spark.Request;
import spark.Spark;
import spark.utils.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static spark.Spark.awaitInitialization;


/**
 * Created by michaelhilton on 1/26/17.
 */
class MainTest {

    @BeforeAll
    public static void beforeClass() {
        Main.main(null);
        awaitInitialization();
    }

    @AfterAll
    public static void afterClass() {
        Spark.stop();
    }

    @Test
    public void testGetModel() {
        TestResponse res = request("GET", "/model");
        assertEquals(200, res.status);
        // Long but accurate
        assertEquals("{\"aircraftCarrier\":{\"Name\":\"aircraftCarrier\",\"length\":5,\"start\":{\"Across\":0,\"Down\":0},\"end\":{\"Across\":0,\"Down\":0}},\"battleship\":{\"Name\":\"battleship\",\"length\":4,\"start\":{\"Across\":0,\"Down\":0},\"end\":{\"Across\":0,\"Down\":0}},\"cruiser\":{\"Name\":\"cruiser\",\"length\":3,\"start\":{\"Across\":0,\"Down\":0},\"end\":{\"Across\":0,\"Down\":0}},\"destroyer\":{\"Name\":\"destroyer\",\"length\":2,\"start\":{\"Across\":0,\"Down\":0},\"end\":{\"Across\":0,\"Down\":0}},\"submarine\":{\"Name\":\"submarine\",\"length\":2,\"start\":{\"Across\":0,\"Down\":0},\"end\":{\"Across\":0,\"Down\":0}},\"computer_aircraftCarrier\":{\"Name\":\"computer_aircraftCarrier\",\"length\":0,\"start\":{\"Across\":0,\"Down\":0},\"end\":{\"Across\":0,\"Down\":0}},\"computer_battleship\":{\"Name\":\"computer_battleship\",\"length\":0,\"start\":{\"Across\":0,\"Down\":0},\"end\":{\"Across\":0,\"Down\":0}},\"computer_cruiser\":{\"Name\":\"computer_cruiser\",\"length\":0,\"start\":{\"Across\":0,\"Down\":0},\"end\":{\"Across\":0,\"Down\":0}},\"computer_destroyer\":{\"Name\":\"computer_destroyer\",\"length\":0,\"start\":{\"Across\":0,\"Down\":0},\"end\":{\"Across\":0,\"Down\":0}},\"computer_submarine\":{\"Name\":\"computer_submarine\",\"length\":0,\"start\":{\"Across\":0,\"Down\":0},\"end\":{\"Across\":0,\"Down\":0}},\"playerHits\":[],\"playerMisses\":[],\"computerHits\":[],\"computerMisses\":[]}",res.body);
    }

    @Test
    public void testPlaceShip() {
        String initialBody = Main.sendModel(new BattleshipModel());
        TestResponse res = request("POST", "/placeShip/aircraftCarrier/1/1/horizontal", initialBody);
        assertEquals(200, res.status);
        assertEquals(
               res.body.startsWith("{\"aircraftCarrier\":{\"Name\":\"aircraftCarrier\",\"length\":5,\"start\":{\"Across\":1,\"Down\":1},\"end\":{\"Across\":1,\"Down\":5}},\"battleship\":{\"Name\":\"battleship\",\"length\":4,\"start\":{\"Across\":0,\"Down\":0},\"end\":{\"Across\":0,\"Down\":0}},\"cruiser\":{\"Name\":\"cruiser\",\"length\":3,\"start\":{\"Across\":0,\"Down\":0},\"end\":{\"Across\":0,\"Down\":0}},\"destroyer\":{\"Name\":\"destroyer\",\"length\":2,\"start\":{\"Across\":0,\"Down\":0},\"end\":{\"Across\":0,\"Down\":0}},\"submarine\":{\"Name\":\"submarine\",\"length\":2,\"start\":{\"Across\":0,\"Down\":0},\"end\":{\"Across\":0,\"Down\":0}}"),
               true
        );
        res = request("POST", "/placeShip/submarine/4/5/vertical", initialBody);
        assertEquals(200, res.status);
        assertEquals(
                res.body.startsWith("{\"aircraftCarrier\":{\"Name\":\"aircraftCarrier\",\"length\":5,\"start\":{\"Across\":0,\"Down\":0},\"end\":{\"Across\":0,\"Down\":0}},\"battleship\":{\"Name\":\"battleship\",\"length\":4,\"start\":{\"Across\":0,\"Down\":0},\"end\":{\"Across\":0,\"Down\":0}},\"cruiser\":{\"Name\":\"cruiser\",\"length\":3,\"start\":{\"Across\":0,\"Down\":0},\"end\":{\"Across\":0,\"Down\":0}},\"destroyer\":{\"Name\":\"destroyer\",\"length\":2,\"start\":{\"Across\":0,\"Down\":0},\"end\":{\"Across\":0,\"Down\":0}},\"submarine\":{\"Name\":\"submarine\",\"length\":2,\"start\":{\"Across\":4,\"Down\":5},\"end\":{\"Across\":5,\"Down\":5}}"),
                true
        );

        res = request("POST", "/placeShip/destroyer/3/2/vertical", initialBody);
        assertEquals(200, res.status);
        assertEquals(
                "destroyer\":{\"Name\":\"destroyer\",\"length\":2,\"start\":{\"Across\":3,\"Down\":2},\"end\":{\"Across\":4,\"Down\":2}},\"",
                res.body.substring(res.body.indexOf("destroyer"), res.body.indexOf("submarine"))
        );

        res = request("POST", "/placeShip/cruiser/8/6/vertical", initialBody);
        assertEquals(200, res.status);
        assertEquals(
                "cruiser\":{\"Name\":\"cruiser\",\"length\":3,\"start\":{\"Across\":8,\"Down\":6},\"end\":{\"Across\":10,\"Down\":6}},\"",
                res.body.substring(res.body.indexOf("cruiser"), res.body.indexOf("destroyer"))
        );

        res = request("POST", "/placeShip/battleship/5/10/vertical", initialBody);
        assertEquals(200, res.status);
        assertEquals(
                "battleship\":{\"Name\":\"battleship\",\"length\":4,\"start\":{\"Across\":5,\"Down\":10},\"end\":{\"Across\":8,\"Down\":10}},\"",
                res.body.substring(res.body.indexOf("battleship"), res.body.indexOf("cruiser"))
        );
    }
    @Test
    public void testFire() {
        String initialBody = Main.sendModel(new BattleshipModel());
        TestResponse res = request("POST", "/fire/1/1", initialBody);
        assertEquals(200, res.status);
        assertEquals(
                "computerMisses\":[{\"Across\":1,\"Down\":1}]}",
                res.body.substring(res.body.indexOf("computerMisses"))
        );
        String secondBody = res.body;
        res = request("POST", "/fire/3/4", initialBody);
        assertEquals(200, res.status);
        assertEquals(
                "computerMisses\":[{\"Across\":3,\"Down\":4}]}",
                res.body.substring(res.body.indexOf("computerMisses"))
        );
    }

    @Test
    public void testShotEquals() {
        assertTrue(new Shot(3, 4).equals(new Shot(3, 4)));
        assertTrue(new Shot(2, 5).equals(new Shot(2, 5)));
        assertFalse(new Shot(1, 1).equals(null));
        assertFalse(new Shot(1, 1).equals(new Object()));
    }

    private TestResponse request(String method, String path) {
        try {
            URL url = new URL("http://localhost:4567" + path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            connection.setDoOutput(true);
            connection.connect();
            InputStream is = connection.getInputStream();
            String body = IOUtils.toString(is);
            return new TestResponse(connection.getResponseCode(), body);
        } catch (IOException e) {
            e.printStackTrace();
            fail("Sending request failed: " + e.getMessage());
            return null;
        }
    }



    private TestResponse request(String method, String path, String bodyToSend) {
        try {
            URL url = new URL("http://localhost:4567" + path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.connect();
            OutputStream os = connection.getOutputStream();
            os.write(bodyToSend.getBytes());
            InputStream is = connection.getInputStream();
            String body = IOUtils.toString(is);
            return new TestResponse(connection.getResponseCode(), body);
        } catch (IOException e) {
            e.printStackTrace();
            fail("Sending request failed: " + e.getMessage());
            return null;
        }
    }

    private static class TestResponse {

        public final String body;
        public final int status;

        public TestResponse(int status, String body) {
            this.status = status;
            this.body = body;
        }

//        public Map<String,String> json() {
//            return new Gson().fromJson(body, HashMap.class);
//        }
    }

}