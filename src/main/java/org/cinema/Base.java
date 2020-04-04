/**
 * Package org.cinema for
 *
 * @author Maksim Tiunchik
 */

package org.cinema;

import java.util.List;

/**
 * Interface Base -
 *
 * @author Maksim Tiunchik (senebh@gmail.com)
 * @version 0.1
 * @since 02.04.2020
 */
public interface Base {

    List<Place> getHall1();

    void purchaise(Place place);

    boolean checkStatus(Place place);
}
