package com.devsuperior.dslist.projections;
//interface que atende as consultas das consultas presentes na classe GameRepositry
public interface GameMinProjection {
	Long getId();
	String getTitle();
	Integer getGameYear();
	String getImgUrl();
	String getShortDescription();
	Integer getPosition();
}
