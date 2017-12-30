package com.footballteam.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.footballteam.domain.FootballTeam;
import com.footballteam.domain.League;
import com.footballteam.domain.Player;

@Component
@Transactional
public class FootballClubManagerImpl implements FootballClubManager {

  @Autowired
  private SessionFactory sessionFactory;

  public SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public void addFootballTeam(FootballTeam footballTeam) {
    sessionFactory.getCurrentSession().persist(footballTeam);
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<FootballTeam> getAllFootballTeams() {
    return sessionFactory.getCurrentSession().getNamedQuery("footballTeam.all").list();
  }

  @Override
  public void updateFootballTeam(FootballTeam footballTeam) {
    sessionFactory.getCurrentSession().saveOrUpdate(footballTeam);
  }

  @Override
  public void removeFootballTeam(FootballTeam footballTeam) {
    sessionFactory.getCurrentSession().delete(footballTeam);
  }

  @Override
  public FootballTeam findById(Long id) {
    return (FootballTeam) sessionFactory.getCurrentSession().get(FootballTeam.class, id);
  }

  @Override
  public FootballTeam findByName(String name) {
    return (FootballTeam) sessionFactory.getCurrentSession().getNamedQuery("footballTeam.byName")
        .setString("name", name).uniqueResult();
  }

  @Override
  public void addPlayer(Player player) {
    sessionFactory.getCurrentSession().persist(player);
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Player> getAllPlayers() {
    return sessionFactory.getCurrentSession().getNamedQuery("player.all").list();
  }

  @Override
  public void updatePlayer(Player player) {
    sessionFactory.getCurrentSession().saveOrUpdate(player);
  }

  @Override
  public void removePlayer(Player player) {
    sessionFactory.getCurrentSession().delete(player);
  }

  @Override
  public Player findPlayerById(Long id) {
    return (Player) sessionFactory.getCurrentSession().get(Player.class, id);
  }

  @Override
  public void addLeague(League league) {
    sessionFactory.getCurrentSession().persist(league);
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<League> getAllLeagues() {
    return sessionFactory.getCurrentSession().getNamedQuery("league.all").list();
  }

  @Override
  public void updateLeague(League league) {
    sessionFactory.getCurrentSession().saveOrUpdate(league);
  }

  @Override
  public void removeLeague(League league) {
    sessionFactory.getCurrentSession().delete(league);
  }

  @Override
  public League findLeagueById(Long id) {
    return (League) sessionFactory.getCurrentSession().get(League.class, id);
  }

}
