package edu.oregonstate.cs361.battleship;

import spark.Request;

import com.google.gson.Gson;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

public class Main {

    public static void main(String[] args) {
        //This will allow us to server the static pages such as index.html, app.js, etc.
        staticFiles.location("/public");

        //This will listen to GET requests to /model and return a clean new model
        get("/model", (req, res) -> newModel());
        //This will listen to POST requests and expects to receive a game model, as well as location to fire to
        post("/fire/:row/:col", (req, res) -> fireAt(req));
        //This will listen to POST requests and expects to receive a game model, as well as location to place the ship
        post("/placeShip/:id/:row/:col/:orientation", (req, res) -> placeShip(req));
    }

    //This function should return a new model
    private static String newModel() {
        BattleshipModel game_state = new BattleshipModel();
        Gson gson = new Gson();
        String json = gson.toJson(game_state);
		
        System.out.println(json);	// Allows viewing of JSON in IDE (test)
        return json;
    }

    //This function should accept an HTTP request and deserialize it into an actual Java object.
    private static BattleshipModel getModelFromReq(Request req){
        Gson gson = new Gson();
        return gson.fromJson(req.body(), BattleshipModel.class);
    }
	
    // This function will convert the game object from a BattleShip model to a JSON
    private static String sendModel(BattleshipModel model) {
        Gson gson = new Gson();
        return gson.toJson(model);
    }

	//This controller should take a json object from the front end, and place the ship as requested, and then return the object.
    private static String placeShip(Request req) {
        BattleshipModel model = getModelFromReq(req);

        /*
            Ship placement logic goes here
        */

        return sendModel(model); // Previous code: return "SHIP";
    }

    //Similar to placeShip, but with firing.
    private static String fireAt(Request req) {
        BattleshipModel model = getModelFromReq(req);

        /*
            Ship placement logic goes here
        */

        return sendModel(model);
    }

}