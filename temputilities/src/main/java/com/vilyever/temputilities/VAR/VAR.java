package com.vilyever.temputilities.VAR;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

/**
 * VAR
 * ESB <com.vilyever.setting.Configure>
 * Created by vilyever on 2016/4/21.
 * Feature:
 */
public class VAR<T> {
    final VAR self = this;

    private static final Object X = new Object();

    private T value;
    
    /* Constructors */
    public VAR(T value) {
        this.value = value;
    }
    
    
    /* Public Methods */
    public T value() {
        return this.value;
    }

    public VAR set(T value) {
        this.value = value;
        internalNotifyValueChanged();
        return this;
    }

    public void registerObserver(Observer observer) {
        getObserverMap().put(observer, X);
    }

    public void removeObserver(Observer observer) {
        getObserverMap().remove(observer);
    }
    
    /* Properties */
    private Map<Observer, Object> observerMap;
    protected Map<Observer, Object> getObserverMap() {
        if (this.observerMap == null) {
            this.observerMap = Collections.synchronizedMap(new WeakHashMap<Observer, Object>());
        }
        return this.observerMap;
    }
    public interface Observer {
        void onValueChange(VAR property, Object value);
    }

    /* Overrides */
    
    
    /* Delegates */
    
    
    /* Private Methods */
    private void internalNotifyValueChanged() {
        Set<Observer> set = new HashSet<Observer>(getObserverMap().keySet());
        Iterator<Observer> iterator = set.iterator();

        while (iterator.hasNext()) {
            Observer observer = iterator.next();
            observer.onValueChange(this, value);
        }
    }
}