package racingcar.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import racingcar.dao.entity.CarEntity;

public class InsertCarDao {

    private final SimpleJdbcInsert insertActor;

    public InsertCarDao(final JdbcTemplate jdbcTemplate) {
        insertActor = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("car");
    }

    public void insertAll(final List<CarEntity> carEntities) {
        @SuppressWarnings("unchecked") final HashMap<String, Object>[] parameters = carEntities.stream()
                .map(this::toMap)
                .toArray(HashMap[]::new);
        insertActor.executeBatch(parameters);
    }

    private Map<String, Object> toMap(final CarEntity carEntity) {
        final Map<String, Object> parameters = new HashMap<>(4);
        parameters.put("name", carEntity.getName());
        parameters.put("game_id", carEntity.getGameId().getValue());
        parameters.put("position", carEntity.getPosition());
        parameters.put("car_id", carEntity.getCarId().getValue());
        return parameters;
    }
}
