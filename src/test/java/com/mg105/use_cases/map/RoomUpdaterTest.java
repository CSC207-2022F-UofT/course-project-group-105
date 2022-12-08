package com.mg105.use_cases.map;

import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoomUpdaterTest {
    @Test
    void singleFireSingleObserver() {
        RoomUpdater observable = new RoomUpdater();
        CountingPropertyChangeListener observer = new CountingPropertyChangeListener();
        observable.addObserver(observer);
        observable.updateRoom();
        assertEquals(1, observer.getFireCount());
    }

    @Test
    void multipleFireTwoObserver() {
        RoomUpdater observable = new RoomUpdater();
        CountingPropertyChangeListener observer1 = new CountingPropertyChangeListener();
        observable.addObserver(observer1);
        observable.updateRoom();
        CountingPropertyChangeListener observer2 = new CountingPropertyChangeListener();
        observable.addObserver(observer2);
        observable.updateRoom();
        assertEquals(2, observer1.getFireCount());
        assertEquals(1, observer2.getFireCount());
    }

    @Test
    void multipleFireTwoObserverRemove() {
        RoomUpdater observable = new RoomUpdater();
        CountingPropertyChangeListener observer1 = new CountingPropertyChangeListener();
        observable.addObserver(observer1);
        CountingPropertyChangeListener observer2 = new CountingPropertyChangeListener();
        observable.addObserver(observer2);
        observable.updateRoom();
        observable.removeObserver(observer2);
        observable.updateRoom();
        assertEquals(2, observer1.getFireCount());
        assertEquals(1, observer2.getFireCount());
    }

    private static class CountingPropertyChangeListener implements PropertyChangeListener {
        private int fireCount = 0;

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            fireCount++;
        }

        public int getFireCount() {
            return fireCount;
        }
    }
}
