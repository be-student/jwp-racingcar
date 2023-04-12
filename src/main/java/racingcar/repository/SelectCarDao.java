package racingcar.repository;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class SelectCarDao {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<CarEntity> actorRowMapper = (resultSet, rowNum) -> new CarEntity(
            resultSet.getInt("car_id"),
            resultSet.getString("name"),
            resultSet.getInt("position")
    );

    public SelectCarDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<CarEntity> findByGameId(final int gameId) {
        final String sql = "SELECT car_id, name, position FROM CAR WHERE game_id = ?";

        return jdbcTemplate.query(sql, actorRowMapper, gameId);
    }

    public CarEntity findById(final int carId) {
        final String sql = "SELECT car_id, name, position FROM CAR WHERE car_id = ?";

        return jdbcTemplate.queryForObject(sql, actorRowMapper, carId);
    }
}
