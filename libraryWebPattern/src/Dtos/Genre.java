/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dtos;

import java.util.Objects;

/**
 *
 * @author Sean
 */
public class Genre {
    private int genreID;
    private String genre;

    /**
     * This is used to create a genre object.<p>
     * Not useful on its own but is used mainly to create a titleGenre object.
     * @param genreID Used to identify spot in database. Primary key.
     * @param genre Stores what genre it is.
     */
    public Genre(int genreID, String genre) {
        this.genreID = genreID;
        this.genre = genre;
    }

    /**
     * Same usage as Main constructor.<p>
     * Only use when creating a genre object not pulled from database.
     * @param genre
     */
    public Genre(String genre) {
        this.genre = genre;
    }
    
    /**
     *
     */
    public Genre() {
        
    }

    public int getGenreID() {
        return genreID;
    }
    
    public void setGenreID(int genreID) {
        this.genreID = genreID;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.genreID;
        hash = 89 * hash + Objects.hashCode(this.genre);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Genre other = (Genre) obj;
        if (this.genreID != other.genreID) {
            return false;
        }
        if (!Objects.equals(this.genre, other.genre)) {
            return false;
        }
        return true;
    }
    
    
}
