package by.bsuir.zavadatar.andrey.teammanagerbsuir.utils;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * This class let us get rid of singletons
 *
 */
public class Lookup {

    private static final Lookup INSTANCE = new Lookup();
    private static final String TAG = Lookup.class.getName();

    private final ArrayList<Object> instances = new ArrayList<Object>();

    private Lookup() {
    }
    
    public static Lookup getInstance() {
        return INSTANCE;
    }
    
    public <T> T lookup(Class<T> clazz) throws InstanceNotFoundException{
    	T result = unsafeLookup(clazz);
        if(result != null) return result;
        throw new InstanceNotFoundException("Lookup doesn't contain instance assignable from " + clazz);
    }

    <T> T unsafeLookup(Class<T> clazz){
        ListIterator<Object> li = instances.listIterator();
        while(li.hasNext()) {
            Object obj = li.next();
            if (clazz.isAssignableFrom(obj.getClass())) {
                return (T) obj;
            }
        }
        return null;
    }

    public void put(Object obj) {
        if (obj != null) {
// code commented below not tested
//            Object exists = unsafeLookup(obj.getClass());
//            while(exists != null) {
//                Log.w(TAG, "Removing " + exists + " from look up to put " + obj);
//                instances.remove(unsafeLookup(obj.getClass()));
//                exists = unsafeLookup(obj.getClass());
//            }
            instances.add(obj);
        } else throw new NullPointerException("Cant put null to lookup");
    }

    public void free(Object obj){
        instances.remove(obj);
    }

    public static class InstanceNotFoundException extends RuntimeException {
        public InstanceNotFoundException(String s) {
            super(s);
        }
    }


} 
