package com.footballteam.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.footballteam.domain.FootballTeam;

@Component
@Transactional
public class FootballTeamManagerImpl implements FootballTeamManager {

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
  public void deleteFootballTeam(FootballTeam footballTeam) {
    sessionFactory.getCurrentSession().delete(footballTeam);
  }

  @Override
  public FootballTeam findById(Long id) {
    return (FootballTeam) sessionFactory.getCurrentSession().get(FootballTeam.class, id);
  }

  @Override
  public FootballTeam findByName(String name) {
    return (FootballTeam) sessionFactory.getCurrentSession().get(FootballTeam.class, name);
  }

}
