package com.movieproject.dao.interfaces;

import com.movieproject.entities.Actors;

public interface IDaoInterfaceForActors {
	
	public Actors save(Actors actor);
	public Actors update(Actors actor);
	public void   delete(Actors actor);
	public Actors getActorById(Integer actorid);
	public Actors getActorByName(String actorname);
	
}
