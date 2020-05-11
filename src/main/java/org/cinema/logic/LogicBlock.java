/**
 * Package org.cinema for
 *
 * @author Maksim Tiunchik
 */
package org.cinema.logic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.cinema.models.Place;
import org.cinema.base.Base;
import org.cinema.base.DBStore;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class LogicBlock -
 *
 * @author Maksim Tiunchik (senebh@gmail.com)
 * @version 0.1
 * @since 02.04.2020
 */
public enum LogicBlock {
    LOGIC;

    /**
     * inner logger
     */
    private static final Logger LOG = LogManager.getLogger(LogicBlock.class.getName());

    /**
     * connection to DB
     */
    private static final Base PBD = DBStore.getInstance();

    /**
     * return enum LOGIC
     *
     * @return LOGIC
     */
    public static LogicBlock getLogic() {
        return LOGIC;
    }

    /**
     * return collection to fullfilling page of cinemahall
     *
     * @return list of list of places
     */
    public List<List<Place>> getHall() {
        LinkedList<Place> base = PBD.getHall1().stream().sorted().collect(Collectors.toCollection(LinkedList::new));
        LinkedList<Place> temp = new LinkedList<>();
        List<List<Place>> out = new LinkedList<>();
        for (var e : base) {
            if (!temp.isEmpty() && temp.peekLast().getRow() != e.getRow()) {
                out.add(temp);
                temp = new LinkedList<>();
            }
            temp.add(e);
        }
        return out;
    }

    /**
     * check place before purchaisung
     *
     * @param place place for cheking
     * @return true if place is owned to cinema. false if was bought another man
     */
    public boolean checkPlace(Place place) {
        return PBD.checkStatus(place);
    }

    /**
     * buy list of places
     *
     * @param list list of places
     */
    public void purchaiseList(List<Place> list) {
        PBD.purchaiseList(list);
    }

    /**
     * checkPlace method to list of places
     *
     * @param list list of places
     * @return true if all places is owned to cinema
     */
    public boolean checkList(List<Place> list) {
        var answer = true;
        for (var e : list) {
            answer = checkPlace(e);
            if (!answer) {
                break;
            }
        }
        return answer;
    }

}
