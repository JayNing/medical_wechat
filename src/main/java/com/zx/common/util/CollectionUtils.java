package com.zx.common.util;


import java.util.*;

/**
 * Created by ning on 2018/3/5 11:31.
 */
public class CollectionUtils {
    public static boolean isEmpty(Collection<?> collection)
    {
        return (collection == null) || (collection.isEmpty());
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return (map == null) || (map.isEmpty());
    }

    public static List arrayToList(Object source)
    {
        return Arrays.asList(ObjectUtils.toObjectArray(source));
    }

    /**
     * 合并数组到集合
     * @param array
     * @param collection
     * @param <E>
     */
    public static <E> void mergeArrayIntoCollection(Object array, Collection<Object> collection)
    {
        if (collection == null) {
            throw new IllegalArgumentException("Collection must not be null");
        }
        Object[] arr = ObjectUtils.toObjectArray(array);
        for (Object elem : arr)
            collection.add(elem);
    }

    /**
     * 合并配置内的元素到map中
     * @param props
     * @param map
     */
    public static void mergePropertiesIntoMap(Properties props, Map<String, Object> map)
    {
        if (map == null)
            throw new IllegalArgumentException("Map must not be null");
        Enumeration en;
        if (props != null)
            for (en = props.propertyNames(); en.hasMoreElements(); ) {
                String key = (String)en.nextElement();
                Object value = props.getProperty(key);
                if (value == null)
                {
                    value = props.get(key);
                }
                map.put(key, value);
            }
    }

    public static boolean contains(Iterator<?> iterator, Object element)
    {
        if (iterator != null) {
            while (iterator.hasNext()) {
                Object candidate = iterator.next();
                if (ObjectUtils.nullSafeEquals(candidate, element)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean contains(Enumeration<?> enumeration, Object element) {
        if (enumeration != null) {
            while (enumeration.hasMoreElements()) {
                Object candidate = enumeration.nextElement();
                if (ObjectUtils.nullSafeEquals(candidate, element)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean containsInstance(Collection<?> collection, Object element)
    {
        Iterator i$;
        if (collection != null) {
            for (i$ = collection.iterator(); i$.hasNext(); ) { Object candidate = i$.next();
                if (candidate == element) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean containsAny(Collection<?> source, Collection<?> candidates) {
        if ((isEmpty(source)) || (isEmpty(candidates))) {
            return false;
        }
        for (Iterator i$ = candidates.iterator(); i$.hasNext(); ) { Object candidate = i$.next();
            if (source.contains(candidate)) {
                return true;
            }
        }
        return false;
    }

    public static <T> T findFirstMatch(Collection<T> source, Collection<T> candidates)
    {
        if ((isEmpty(source)) || (isEmpty(candidates))) {
            return null;
        }
        for (Iterator i = candidates.iterator(); i.hasNext(); ) { Object candidate = i.next();
            if (source.contains(candidate)) {
                return (T) candidate;
            }
        }
        return null;
    }

    public static <T> T findValueOfType(Collection<?> collection, Class<T> type)
    {
        if (isEmpty(collection)) {
            return null;
        }
        Object value = null;
        for (Iterator i$ = collection.iterator(); i$.hasNext(); ) { Object element = i$.next();
            if ((type == null) || (type.isInstance(element))) {
                if (value != null)
                {
                    return null;
                }
                value = element;
            }
        }
        return (T)value;
    }

    public static Object findValueOfType(Collection<?> collection, Class<?>[] types)
    {
        if ((isEmpty(collection)) || (ObjectUtils.isEmpty(types))) {
            return null;
        }
        for (Class type : types) {
            Object value = findValueOfType(collection, type);
            if (value != null) {
                return value;
            }
        }
        return null;
    }

    public static boolean hasUniqueObject(Collection<?> collection)
    {
        if (isEmpty(collection)) {
            return false;
        }
        boolean hasCandidate = false;
        Object candidate = null;
        for (Iterator i$ = collection.iterator(); i$.hasNext(); ) { Object elem = i$.next();
            if (!hasCandidate) {
                hasCandidate = true;
                candidate = elem;
            } else if (candidate != elem) {
                return false;
            }
        }
        return true;
    }

    public static Class<?> findCommonElementType(Collection<?> collection)
    {
        if (isEmpty(collection)) {
            return null;
        }
        Class candidate = null;
        for (Iterator i$ = collection.iterator(); i$.hasNext(); ) { Object val = i$.next();
            if (val != null) {
                if (candidate == null)
                    candidate = val.getClass();
                else if (candidate != val.getClass()) {
                    return null;
                }
            }
        }
        return candidate;
    }

    public static <A, E extends A> A[] toArray(Enumeration<E> enumeration, A[] array)
    {
        ArrayList elements = new ArrayList();
        while (enumeration.hasMoreElements()) {
            elements.add(enumeration.nextElement());
        }
        return (A[])elements.toArray(array);
    }
}
