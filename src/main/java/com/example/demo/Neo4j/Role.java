//package com.example.demo.Neo4j;
//
//import org.neo4j.ogm.annotation.EndNode;
//import org.neo4j.ogm.annotation.RelationshipEntity;
//import org.neo4j.ogm.annotation.StartNode;
//
//import com.fasterxml.jackson.annotation.JsonIdentityInfo;
//import com.voodoodyne.jackson.jsog.JSOGGenerator;
//
//@JsonIdentityInfo(generator=JSOGGenerator.class)
//@RelationshipEntity(type="ACTS_IN")
//public class Role {
//	private Long id;
//	
//	private String role;
//	
//	@StartNode
//	private Actor actor;
//	@EndNode
//	private Movie movie;
//	
//	public Role() {
//		
//	}
//	
//	public Role(Actor actor,Movie movie,String name) {
//		this.actor = actor;
//		this.movie = movie;
//		this.role = name;
//	}
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getRole() {
//		return role;
//	}
//
//	public void setRole(String role) {
//		this.role = role;
//	}
//
//	public Actor getActor() {
//		return actor;
//	}
//
//	public void setActor(Actor actor) {
//		this.actor = actor;
//	}
//
//	public Movie getMovie() {
//		return movie;
//	}
//
//	public void setMovie(Movie movie) {
//		this.movie = movie;
//	}
//	
//	
//}
