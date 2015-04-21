package com.movieproject.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.movieproject.dao.interfaces.IDaoInterfaceForActors;
import com.movieproject.entities.Actors;

@Transactional
public class ActorsDao implements IDaoInterfaceForActors {
	
	@Autowired
	HibernateTemplate hibernateTemplate;

	@Override
	public Actors save(Actors actor) {
		
		hibernateTemplate.save(actor);
		
		return null;
	}

	@Override
	public Actors update(Actors actor) {

		hibernateTemplate.update(actor);
		
		return null;
	}

	@Override
	public void delete(Actors actor) {

		hibernateTemplate.delete(actor);
		
	}

	@Override
	public Actors getActorById(Integer actorid) {
		
		String query = "from Actors a where a.actorid = ?";
		@SuppressWarnings("unchecked")
		List<Actors> actor = (List<Actors>) hibernateTemplate.find(query, actorid);

		if (actor.isEmpty()) {
			return null;
		} else {
			return actor.get(0);
		}
	}

	@Override
	public Actors getActorByName(String actorname) {
		
		String query = "from Actors a where a.actorname = ?";
		@SuppressWarnings("unchecked")
		List<Actors> actor = (List<Actors>) hibernateTemplate.find(query, actorname);

		if (actor.isEmpty()) {
			return null;
		} else {
			return actor.get(0);
		}
	}

}
