package workshop.repository;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import workshop.model.Game;

@Repository
public class BoardGamesRepository {

  @Autowired
  MongoTemplate mongoTemplate;

  public List<Game> getAllGames(Integer limit, Integer offset) {
    Query query = new Query();

    Pageable pageable = PageRequest.of(offset, limit);
    query.with(pageable);

    return mongoTemplate
        .find(query, Document.class, "games")
        .stream()
        .map(d -> Game.create(d))
        .toList();
  }

  public List<Game> getSortedBoardGames(Integer limit, Integer offset) {
    Query query = new Query();

    Pageable pageable = PageRequest.of(offset, limit);
    query.with(pageable);
    query.with(Sort.by(Sort.Direction.ASC, "ranking"));

    return mongoTemplate
        .find(query, Document.class, "games")
        .stream()
        .map(d -> Game.create(d))
        .toList();
  }

  public Game getBoardGameById(Integer gid) {
    Query query = new Query();
    query.addCriteria(Criteria.where("gid").is(gid));

    return mongoTemplate
        // results from the query are being automatically mapped to the Game class
        // so fields from the mongo document need to match
        // variables in the java class exactly
        .findOne(query, Game.class, "games");
  }
}
