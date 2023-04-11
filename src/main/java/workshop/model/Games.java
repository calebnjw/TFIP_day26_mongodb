package workshop.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Games {
  private List<Game> games;
  private Integer limit;
  private Integer offset;
  private Integer total;
  private LocalDateTime timestamp;

  public Games() {
  }

  public Games(List<Game> games, Integer limit, Integer offset, Integer total, LocalDateTime timestamp) {
    this.games = games;
    this.limit = limit;
    this.offset = offset;
    this.total = total;
    this.timestamp = timestamp;
  }

  public List<Game> getGames() {
    return games;
  }

  public void setGames(List<Game> games) {
    this.games = games;
  }

  public Integer getLimit() {
    return limit;
  }

  public void setLimit(Integer limit) {
    this.limit = limit;
  }

  public Integer getOffset() {
    return offset;
  }

  public void setOffset(Integer offset) {
    this.offset = offset;
  }

  public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public JsonObject toJson() {
    return Json.createObjectBuilder()
        .add("games", getGames().toString())
        .add("limit", getLimit())
        .add("offset", getOffset())
        .add("total", getTotal())
        .add("timestamp", getTimestamp().toString())
        .build();
  }
}
