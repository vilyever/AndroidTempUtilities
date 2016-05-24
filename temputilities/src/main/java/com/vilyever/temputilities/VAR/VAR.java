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

    public synchronized VAR set(T value) {
        T oldValue = this.value;
        this.value = value;
        internalNotifyValueChanged(value, oldValue);
        return this;
    }

    public synchronized VAR onChange(Monitor<T> monitor) {
        getMonitorMap().put(monitor, X);
        return this;
    }

    public synchronized VAR onChangeWithInitial(Monitor<T> monitor) {
        forceNotify(monitor);
        getMonitorMap().put(monitor, X);
        return this;
    }

    public synchronized VAR discard(Monitor<T> monitor) {
        getMonitorMap().remove(monitor);
        return this;
    }

    public synchronized VAR forceNotify(Monitor<T> monitor) {
        monitor.onValueChange(this, value(), value());
        return this;
    }

    /* Properties */
    private Map<Monitor<T>, Object> monitorMap;
    protected Map<Monitor<T>, Object> getMonitorMap() {
        if (this.monitorMap == null) {
            this.monitorMap = Collections.synchronizedMap(new WeakHashMap<Monitor<T>, Object>());
        }
        return this.monitorMap;
    }
    public interface Monitor<T> {
        void onValueChange(VAR var, T value, T oldValue);
    }

    /* Overrides */


    /* Delegates */


    /* Private Methods */
    private void internalNotifyValueChanged(T value, T oldValue) {
        if (getMonitorMap().size() > 0) {
            Set<Monitor<T>> set = new HashSet<Monitor<T>>(getMonitorMap().keySet());
            Iterator<Monitor<T>> iterator = set.iterator();

            while (iterator.hasNext()) {
                Monitor<T> monitor = iterator.next();
                monitor.onValueChange(this, value, oldValue);
            }
        }
    }
}