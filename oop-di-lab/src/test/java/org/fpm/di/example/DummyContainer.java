package org.fpm.di.example;

import org.fpm.di.Container;

import javax.inject.Singleton;
import java.lang.reflect.*;

public class DummyContainer implements Container {
    DummyBinder binder;
    @Override
    public <T> T getComponent(Class<T> clazz) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        if(binder.inst.containsKey(clazz)){
            return (T) binder.inst.get(clazz);
        }
        if(binder.impl.containsKey(clazz)){
            return getComponent((Class<T>) binder.impl.get(clazz));
        }
        T constructed_clazz = null;
        if(binder.cons.containsKey(clazz)){
            Constructor<?> c = binder.cons.get(clazz);
            Parameter[] params = c.getParameters();
            Object[] args = new Object[params.length];
            for(int i = 0; i < params.length; i++){
                args[i] = getComponent(params[i].getType());
            }
            constructed_clazz = (T) c.newInstance(args);
        }
        else{
            constructed_clazz = clazz.newInstance();
        }
        if(clazz.isAnnotationPresent(Singleton.class)){
            binder.bind(clazz, constructed_clazz);
        }
        return constructed_clazz;

    }
}
