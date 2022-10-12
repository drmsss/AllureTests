package PageObject.PageSteps.APISteps;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class RickAndMortyApiSteps {
    public static JSONObject character;
    public static JSONObject lastCharacter;
    public static int lastEpisodeId;
    public static int lastCharacterInLastEpisodeId;
    public static String firstCharacterLocation;
    public static String firstCharacterSpecies;
    public static String secondCharacterLocation;
    public static String secondCharacterSpecies;

    public static JSONObject getCharacterObject(String characterName) {
        Specification.setSpec(Specification.requestSpec(), Specification.responseSpec());
        Response response = given()
                .log().all()
                .filter(new AllureRestAssured())
                .when()
                    .get("/character/?name="+characterName)
                .then().log().all()
                    .extract().response();

        JSONObject characterObject = (JSONObject) new JSONObject(response.getBody().asString()).getJSONArray("results").get(0);
        return characterObject;
    }

    public static JSONObject getCharacterObject(int characterId) {
        Specification.setSpec(Specification.requestSpec(), Specification.responseSpec());
        Response response = given()
                .log().all()
                .filter(new AllureRestAssured())
                .when()
                    .get("/character/"+characterId)
                .then().log().all()
                    .extract().response();

        JSONObject characterObject = (JSONObject) new JSONObject(response.getBody().asString());
        return characterObject;
    }

    @Given("Отправлен запрос на получение информации по персонажу {string}")
    public static void sentRequestToGetCharacterObject(String characterName) {
        character = getCharacterObject(characterName);
    }

    @Given("Отправлен запрос на получение информации по последнему персонажу из последнего эпизода")
    public static void sentRequestToGetLastCharacterObject() {
        lastCharacter = getCharacterObject(lastCharacterInLastEpisodeId);
    }

    @Then("Получить у первого персонажа свойство {string}")
    public static void printFirstCharacterProperty (String characterProperty) {

        String characterName = character.getString("name");

        if (characterProperty.equals("location")) {
            String characterLocationName = character.getJSONObject("location").get("name").toString();
            System.out.println(characterName + " location is " + characterLocationName);
            firstCharacterLocation = characterLocationName;

        } else if (characterProperty.equals("episode")) {
            int characterEpisodesNumber = character.getJSONArray("episode").length();
            String lastEpisodeWithCharacterUrl = character.getJSONArray("episode").get(characterEpisodesNumber-1).toString();
            int lastEpisodeWithCharacterId = Integer.parseInt(lastEpisodeWithCharacterUrl.substring(lastEpisodeWithCharacterUrl.lastIndexOf("/")+1));
            System.out.println("Last episode with " + characterName + " is Episode " + lastEpisodeWithCharacterId);

        } else if (characterProperty.equals("species")){
            String characterSpecies = character.get("species").toString();
            System.out.println(characterName + " species is " + characterSpecies);
            firstCharacterSpecies = characterSpecies;
        } else {
            String characterPropertyValue = character.get(characterProperty).toString();
            System.out.println(characterName + " " + characterProperty + " is " + characterPropertyValue);
        }
    }

    @Then("Получить у последнего персонажа свойство {string}")
    public static void printSecondCharacterProperty (String characterProperty) {

        String characterName = lastCharacter.getString("name");

        if (characterProperty.equals("location")) {
            String characterLocationName = lastCharacter.getJSONObject("location").get("name").toString();
            System.out.println(characterName + " location is " + characterLocationName);
            secondCharacterLocation = characterLocationName;

        } else if (characterProperty.equals("episode")) {
            int characterEpisodesNumber = lastCharacter.getJSONArray("episode").length();
            String lastEpisodeWithCharacterUrl = lastCharacter.getJSONArray("episode").get(characterEpisodesNumber-1).toString();
            int lastEpisodeWithCharacterId = Integer.parseInt(lastEpisodeWithCharacterUrl.substring(lastEpisodeWithCharacterUrl.lastIndexOf("/")+1));
            System.out.println("Last episode with " + characterName + " is Episode " + lastEpisodeWithCharacterId);

        } else if (characterProperty.equals("species")){
            String characterSpecies = lastCharacter.get("species").toString();
            System.out.println(characterName + " species is " + characterSpecies);
            secondCharacterSpecies = characterSpecies;
        } else {
            String characterPropertyValue = lastCharacter.get(characterProperty).toString();
            System.out.println(characterName + " " + characterProperty + " is " + characterPropertyValue);
        }

    }

    @When("Получен ID последнего эпизода")
    public static void getLastEpisodeId() {
        Specification.setSpec(Specification.requestSpec(), Specification.responseSpec());
        Response response = given()
                .log().all()
                .filter(new AllureRestAssured())
                .when()
                    .get("episode")
                .then().log().all()
                    .extract().response();

        JSONObject allEpisodesBody = (JSONObject) new JSONObject(response.getBody().asString());
        lastEpisodeId = Integer.parseInt(allEpisodesBody.getJSONObject("info").get("count").toString());
        System.out.println("Last episode is "+ lastEpisodeId);
    }

    @Then("Получен ID последнего персонажа из последнего эпизода")
    public static void getLastCharacterIdFromLastEpisode() {
        Specification.setSpec(Specification.requestSpec(), Specification.responseSpec());
        Response response = given()
                .log().all()
                .filter(new AllureRestAssured())
                .when()
                    .get("episode/"+lastEpisodeId)
                .then().log().all()
                    .extract().response();

        JSONArray charactersInLastEpisode = new JSONObject(response.getBody().asString()).getJSONArray("characters");
        int charactersInLastEpisodeNumber = charactersInLastEpisode.length();
        String lastCharacterInLastEpisodeUrl = charactersInLastEpisode.get(charactersInLastEpisodeNumber-1).toString();
        lastCharacterInLastEpisodeId = Integer.parseInt(lastCharacterInLastEpisodeUrl.substring(lastCharacterInLastEpisodeUrl.lastIndexOf("/")+1));
        System.out.println("Last character in last episode is " + lastCharacterInLastEpisodeId);
    }

    @Then("Убедиться, что локации двух персонажей отличаются")
    public static void compareCharactersLocation() {
        assertNotEquals(firstCharacterLocation, secondCharacterLocation, "Локации персонажей одинаковые, а должны быть разные");
    }

    @Then("Убедиться, что расы двух персонажей совпадают")
    public static void compareCharactersSpecies() {
        assertEquals(firstCharacterSpecies, secondCharacterSpecies, "Расы персонажей отличаются, а должны быть одинаковые");
    }

}
