package com.footballteam.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.footballteam.domain.FootballTeam;

public class FootballTeamServiceImpl implements FootballTeamService {
  private Connection connection;
  private String url = "jdbc:hsqldb:hsql://localhost/workdb";
  private String createTableFootballTeam = "CREATE TABLE FootballTeam(id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,"
      + " name VARCHAR(50) NOT NULL, yearOfEstablished INTEGER NOT NULL, marketValue REAL NOT NULL)";
  private PreparedStatement addFootballTeamStmt;
  private PreparedStatement getAllFootballTeamsStmt;
  private PreparedStatement updateFootballTeamStmt;
  private PreparedStatement updateFootballTeamNameStmt;
  private PreparedStatement updateFootballTeamYearOfEstablishedStmt;
  private PreparedStatement updateFootballTeamMarketValueStmt;
  private PreparedStatement removeAllFootballTeamsStmt;
  private PreparedStatement removeFootballTeamByIdStmt;
  private PreparedStatement removeFootballTeamByNameStmt;
  private PreparedStatement findByIdStmt;
  private PreparedStatement findByNameStmt;
  private PreparedStatement findByYearOfEstablishedStmt;
  private PreparedStatement findByMarketValueStmt;
  private Statement statement;

  public FootballTeamServiceImpl() {
    openConnection();
    createTable();
    createStatements();
  }

  public boolean createTable() {
    try {
      ResultSet resultSet = connection.getMetaData().getTables(null, null, null, null);
      boolean tableExists = false;
      while (resultSet.next()) {
        if ("FootballTeam".equalsIgnoreCase(resultSet.getString("TABLE_NAME"))) {
          tableExists = true;
          break;
        }
      }
      if (!tableExists) {
        statement.executeUpdate(createTableFootballTeam);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  public boolean createStatements() {
    try {
      addFootballTeamStmt = connection
          .prepareStatement("INSERT INTO FootballTeam(name, yearOfEstablished, marketValue) VALUES(?, ?, ?)");
      getAllFootballTeamsStmt = connection
          .prepareStatement("SELECT id, name, yearOfEstablished, marketValue FROM FootballTeam");
      updateFootballTeamStmt = connection.prepareStatement(
          "UPDATE FootballTeam SET id = id, name = ?, yearOfEstablished = ?, marketValue = ? WHERE id = ?");
      updateFootballTeamNameStmt = connection.prepareStatement(
          "UPDATE FootballTeam SET id = id, name = ?, yearOfEstablished = yearOfEstablished, marketValue = marketValue WHERE id = ?");
      updateFootballTeamYearOfEstablishedStmt = connection.prepareStatement(
          "UPDATE FootballTeam SET id = id, name = name, yearOfEstablished = ?, marketValue = marketValue WHERE id = ?");
      updateFootballTeamMarketValueStmt = connection.prepareStatement(
          "UPDATE FootballTeam SET id = id, name = name, yearOfEstablished = yearOfEstablished, marketValue = ? WHERE id = ?");
      removeAllFootballTeamsStmt = connection.prepareStatement("DELETE FROM FootballTeam");
      removeFootballTeamByIdStmt = connection.prepareStatement("DELETE FROM FootballTeam WHERE id = ?");
      removeFootballTeamByNameStmt = connection.prepareStatement("DELETE FROM FootballTeam WHERE name = ?");
      findByIdStmt = connection
          .prepareStatement("SELECT id, name, yearOfEstablished, marketValue FROM FootballTeam WHERE id = ?");
      findByNameStmt = connection
          .prepareStatement("SELECT id, name, yearOfEstablished, marketValue FROM FootballTeam WHERE name = ?");
      findByYearOfEstablishedStmt = connection.prepareStatement(
          "SELECT id, name, yearOfEstablished, marketValue FROM FootballTeam WHERE yearOfEstablished = ?");
      findByMarketValueStmt = connection
          .prepareStatement("SELECT id, name, yearOfEstablished, marketValue FROM FootballTeam WHERE marketValue = ?");
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  public boolean openConnection() {
    try {
      connection = DriverManager.getConnection(url);
      statement = connection.createStatement();
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  public boolean closeConnection() {
    try {
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  public Connection getConnection() {
    return connection;
  }

  @Override
  public int addFootballTeam(FootballTeam footballTeam) {
    int counter = 0;
    try {
      connection.setAutoCommit(false);
      addFootballTeamStmt.setString(1, footballTeam.getName());
      addFootballTeamStmt.setInt(2, footballTeam.getYearOfEstablished());
      addFootballTeamStmt.setDouble(3, footballTeam.getMarketValue());
      counter = addFootballTeamStmt.executeUpdate();
      connection.commit();
    } catch (SQLException e) {
      try {
        connection.rollback();
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
    } finally {
      try {
        connection.setAutoCommit(true);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return counter;
  }

  @Override
  public int addAllFootballTeamsFromList(List<FootballTeam> footballTeams) {
    int counter = 0;
    try {
      connection.setAutoCommit(false);
      for (FootballTeam footballTeam : footballTeams) {
        addFootballTeamStmt.setString(1, footballTeam.getName());
        addFootballTeamStmt.setInt(2, footballTeam.getYearOfEstablished());
        addFootballTeamStmt.setDouble(3, footballTeam.getMarketValue());
        counter += addFootballTeamStmt.executeUpdate();
        connection.commit();
      }
    } catch (SQLException e) {
      try {
        connection.rollback();
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
    } finally {
      try {
        connection.setAutoCommit(true);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return counter;
  }

  @Override
  public List<FootballTeam> getAllFootballTeams() {
    List<FootballTeam> footballTeams = new ArrayList<FootballTeam>();
    try {
      ResultSet resultSet = getAllFootballTeamsStmt.executeQuery();
      while (resultSet.next()) {
        FootballTeam footballTeam = new FootballTeam();
        footballTeam.setId(resultSet.getLong("id"));
        footballTeam.setName(resultSet.getString("name"));
        footballTeam.setYearOfEstablished(resultSet.getInt("yearOfEstablished"));
        footballTeam.setMarketValue(resultSet.getDouble("marketValue"));
        footballTeams.add(footballTeam);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return footballTeams;
  }

  @Override
  public int updateFootballTeam(FootballTeam footballTeam, String name, int yearOfEstablished, double marketValue) {
    int counter = 0;
    try {
      updateFootballTeamStmt.setString(1, name);
      updateFootballTeamStmt.setInt(2, yearOfEstablished);
      updateFootballTeamStmt.setDouble(3, marketValue);
      updateFootballTeamStmt.setLong(4, footballTeam.getId());
      counter = updateFootballTeamStmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return counter;
  }

  @Override
  public int updateFootballTeamName(FootballTeam footballTeam, String name) {
    int counter = 0;
    try {
      updateFootballTeamNameStmt.setString(1, name);
      updateFootballTeamNameStmt.setLong(2, footballTeam.getId());
      counter = updateFootballTeamNameStmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return counter;
  }

  @Override
  public int updateFootballTeamYearOfEstablished(FootballTeam footballTeam, int yearOfEstablished) {
    int counter = 0;
    try {
      updateFootballTeamYearOfEstablishedStmt.setInt(1, yearOfEstablished);
      updateFootballTeamYearOfEstablishedStmt.setLong(2, footballTeam.getId());
      counter = updateFootballTeamYearOfEstablishedStmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return counter;
  }

  @Override
  public int updateFootballTeamMarketValue(FootballTeam footballTeam, double marketValue) {
    int counter = 0;
    try {
      updateFootballTeamMarketValueStmt.setDouble(1, marketValue);
      updateFootballTeamMarketValueStmt.setLong(2, footballTeam.getId());
      counter = updateFootballTeamMarketValueStmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return counter;
  }

  @Override
  public int removeAllFootballTeams() {
    int counter = 0;
    try {
      counter = removeAllFootballTeamsStmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return counter;
  }

  @Override
  public int removeAllFootballTeamsFromList(List<FootballTeam> footballTeams) {
    int counter = 0;
    try {
      connection.setAutoCommit(false);
      for (FootballTeam footballTeam : footballTeams) {
        if (findByName(footballTeam.getName()) == null) {
          throw new SQLException("Cannot find football team to remove.");
        }
        removeFootballTeamByNameStmt.setString(1, footballTeam.getName());
        counter += removeFootballTeamByNameStmt.executeUpdate();
      }
      connection.commit();
    } catch (SQLException exception) {
      try {
        connection.rollback();
        counter = 0;
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return counter;
  }

  @Override
  public int removeFootballTeamById(long id) {
    int counter = 0;
    try {
      removeFootballTeamByIdStmt.setLong(1, id);
      counter = removeFootballTeamByIdStmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return counter;
  }

  @Override
  public int removeFootballTeamByName(String name) {
    int counter = 0;
    try {
      removeFootballTeamByNameStmt.setString(1, name);
      counter = removeFootballTeamByNameStmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return counter;
  }

  @Override
  public FootballTeam findById(long id) {
    FootballTeam footballTeam = null;
    try {
      findByIdStmt.setLong(1, id);
      ResultSet resultSet = findByIdStmt.executeQuery();
      if (resultSet.next()) {
        String name = resultSet.getString("name");
        int yearOfEstablished = resultSet.getInt("yearOfEstablished");
        double marketValue = resultSet.getDouble("marketValue");
        footballTeam = new FootballTeam(name, yearOfEstablished, marketValue);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return footballTeam;
  }

  @Override
  public FootballTeam findByName(String name) {
    FootballTeam footballTeam = null;
    try {
      findByNameStmt.setString(1, name);
      ResultSet resultSet = findByNameStmt.executeQuery();
      if (resultSet.next()) {
        int yearOfEstablished = resultSet.getInt("yearOfEstablished");
        double marketValue = resultSet.getDouble("marketValue");
        footballTeam = new FootballTeam(name, yearOfEstablished, marketValue);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return footballTeam;
  }

  @Override
  public FootballTeam findByYearOfEstablished(int yearOfEstablished) {
    FootballTeam footballTeam = null;
    try {
      findByYearOfEstablishedStmt.setInt(1, yearOfEstablished);
      ResultSet resultSet = findByYearOfEstablishedStmt.executeQuery();
      if (resultSet.next()) {
        String name = resultSet.getString("name");
        double marketValue = resultSet.getDouble("marketValue");
        footballTeam = new FootballTeam(name, yearOfEstablished, marketValue);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return footballTeam;
  }

  @Override
  public FootballTeam findByMarketValue(double marketValue) {
    FootballTeam footballTeam = null;
    try {
      findByMarketValueStmt.setDouble(1, marketValue);
      ResultSet resultSet = findByMarketValueStmt.executeQuery();
      if (resultSet.next()) {
        String name = resultSet.getString("name");
        int yearOfEstablished = resultSet.getInt("yearOfEstablished");
        footballTeam = new FootballTeam(name, yearOfEstablished, marketValue);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return footballTeam;
  }

}
