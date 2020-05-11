/**
 * Package org.cinema for
 *
 * @author Maksim Tiunchik
 */
package org.cinema.models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

/**
 * Class Place - model data of place
 *
 * @author Maksim Tiunchik (senebh@gmail.com)
 * @version 0.1
 * @since 02.04.2020
 */
public class Place implements Comparable<Place> {
    private static final Logger LOG = LogManager.getLogger(Place.class.getName());

    private int row;

    private int place;

    private int holder;

    private String name;

    public Place(int row, int place, int holder) {
        this.row = row;
        this.place = place;
        this.holder = holder;
    }

    public Place(int row, int place, String name) {
        this.row = row;
        this.place = place;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public int getHolder() {
        return holder;
    }

    public void setHolder(int holder) {
        this.holder = holder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Place place1 = (Place) o;
        return row == place1.row
                && place == place1.place;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, place);
    }

    @Override
    public int compareTo(Place o) {
        var answer = Integer.compare(this.getRow(), o.getRow());
        if (answer == 0) {
            return Integer.compare(this.getPlace(), o.getPlace());
        }
        return answer;
    }
}
