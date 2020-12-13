package by.epam.bikesharing.dao;

import by.epam.bikesharing.entity.BaseEntity;
import by.epam.bikesharing.entity.Card;
import by.epam.bikesharing.service.PasswordHash;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CardDao extends AbstractDao {

    private static final BigDecimal CARD_INITIAL_BALANCE = new BigDecimal("500");
    private static final String SELECT_USER_CARDS = "SELECT * FROM dbo.[Card] WHERE UserID = ?";
    private static final String SELECT_CARD_BY_ID = "SELECT * FROM dbo.[Card] WHERE CardID = ?";
    private static final String DELETE_CARD = "DELETE FROM dbo.[Card] WHERE CardID = ?";
    private static final String INSERT_CARD =
        "INSERT INTO dbo.[Card] (UserID, FirstName, LastName, Month, Year, Number, CVVHash, CVVSalt, Balance) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_CARD = "UPDATE dbo.[Card] SET Balance=? WHERE CardID=?";

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public Card findEntityById(long id) {
        List cards = find(SELECT_CARD_BY_ID, Long.toString(id));
        return (Card) cards.get(0);
    }

    public List<Card> findUserCards(long userId) {
        return find(SELECT_USER_CARDS, Long.toString(userId));
    }

    @Override
    public boolean delete(long id) {
        return delete(DELETE_CARD, id);
    }

    @Override
    PreparedStatement getCreateStatement(BaseEntity entity) throws SQLException {
        Card card = (Card) entity;
        PreparedStatement statement = getPreparedStatement(INSERT_CARD);
        statement.setLong(1, card.getUserId());
        statement.setString(2, card.getFirstName());
        statement.setString(3, card.getLastName());
        statement.setInt(4, card.getMonth());
        statement.setInt(5, card.getYear());
        statement.setString(6, card.getNumber());
        statement.setBytes(7, card.getCvv().getHash());
        statement.setBytes(8, card.getCvv().getSalt());
        statement.setBigDecimal(9, CARD_INITIAL_BALANCE);
        return statement;
    }

    @Override
    PreparedStatement getUpdateStatement(BaseEntity entity) throws SQLException {
        Card card = (Card) entity;
        PreparedStatement statement = getPreparedStatement(UPDATE_CARD);
        statement.setBigDecimal(1, card.getBalance());
        statement.setLong(2, card.getId());
        return statement;
    }

    @Override
    Card getEntityFromSet(ResultSet resultSet) throws SQLException {
        Card card = new Card();
        card.setId(resultSet.getLong("CardID"));
        card.setUserId(resultSet.getLong("UserID"));
        card.setFirstName(resultSet.getString("FirstName"));
        card.setLastName(resultSet.getString("LastName"));
        card.setMonth(resultSet.getInt("Month"));
        card.setYear(resultSet.getInt("Year"));
        card.setNumber(resultSet.getString("Number"));
        card.setCvv(new PasswordHash(
                resultSet.getBytes("CVVHash"),
                resultSet.getBytes("CVVSalt")
        ));
        card.setBalance(resultSet.getBigDecimal("Balance"));
        return card;
    }
}
