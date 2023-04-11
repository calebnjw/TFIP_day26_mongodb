package workshop.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import workshop.model.Game;
import workshop.model.Games;
import workshop.repository.BoardGamesRepository;

@RestController
public class BoardGamesController {

  BoardGamesRepository boardGamesRepository;

  BoardGamesController(BoardGamesRepository boardGamesRepository) {
    this.boardGamesRepository = boardGamesRepository;
  }

  @GetMapping(path = "/games")
  public ResponseEntity<String> getAllBoardGames(
      @RequestParam Integer limit,
      @RequestParam Integer offset) {
    List<Game> listGames = boardGamesRepository.getAllGames(limit, offset);

    Games games = new Games();
    games.setGames(listGames);
    games.setLimit(limit);
    games.setOffset(offset);
    games.setTotal(limit);
    games.setTimestamp(LocalDateTime.now());

    JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
    objectBuilder.add("boardgames", games.toJson());
    JsonObject result = objectBuilder.build();

    return ResponseEntity
        .status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(result.toString());
  }

  @GetMapping(path = "/games/rank")
  public ResponseEntity<String> getRankedBoardGames(
      @RequestParam Integer limit,
      @RequestParam Integer offset) {
    List<Game> listGames = boardGamesRepository.getSortedBoardGames(limit, offset);

    Games games = new Games();
    games.setGames(listGames);
    games.setLimit(limit);
    games.setOffset(offset);
    games.setTotal(limit);
    games.setTimestamp(LocalDateTime.now());

    JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
    objectBuilder.add("boardgames", games.toJson());
    JsonObject result = objectBuilder.build();

    return ResponseEntity
        .status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(result.toString());
  }

  @GetMapping(path = "/games/{gid}")
  public ResponseEntity<String> getBoardGameById(
      @PathVariable Integer gid) {
    Game game = boardGamesRepository.getBoardGameById(gid);

    JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
    objectBuilder.add("game", game.toJson());
    JsonObject result = objectBuilder.build();

    return ResponseEntity
        .status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(result.toString());
  }
}
