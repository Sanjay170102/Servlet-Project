package com.genericSort;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Sorter
{
    public static ArrayList<?> sort1(String columnName,String TableName)
    {
    	CachingModel e = CachingModel.getInstance();
    	
        ArrayList<?> unsortedList = e.getColumn(TableName,columnName);

        if(unsortedList.get(0) instanceof Integer)
        {
            Collections.sort((ArrayList<Integer>)unsortedList, new Comparator<Integer>() {
                @Override
                public int compare(Integer t1, Integer t2) {
                    return t1>t2 ? 1 : t1<t2 ? -1 : 0;
                }
            });

            System.out.println(unsortedList);
            return unsortedList;
        }

        else if(unsortedList.get(0) instanceof Float)
        {
            Collections.sort((ArrayList<Float>)unsortedList, new Comparator<Float>() {
                @Override
                public int compare(Float t1, Float t2) {
                    return t1>t2 ? 1 : t1<t2 ? -1 : 0;
                }
            });
            System.out.println(unsortedList);
            return unsortedList;
        }

        else if(unsortedList.get(0) instanceof Long)
        {
            Collections.sort((ArrayList<Long>)unsortedList, new Comparator<Long>() {
                @Override
                public int compare(Long t1, Long t2) {
                    return t1>t2 ? 1 : t1<t2 ? -1 : 0;
                }
            });
            System.out.println(unsortedList);
            return unsortedList;
        }

        else if(unsortedList.get(0) instanceof Double)
        {
            Collections.sort((ArrayList<Double>)unsortedList, new Comparator<Double>() {
                @Override
                public int compare(Double t1, Double t2) {
                    return t1>t2 ? 1 : t1<t2 ? -1 : 0;
                }
            });
            System.out.println(unsortedList);
            return unsortedList;
        }

        else if(unsortedList.get(0) instanceof String)
        {
            Collections.sort((ArrayList<String>)unsortedList, new Comparator<String>() {
                @Override
                public int compare(String t1, String t2) {
                    return t1.length()>t2.length() ? 1 : t1.length()<t2.length() ? -1 : 0;
                }
            });
            System.out.println(unsortedList);
            return unsortedList;
        }
        return unsortedList;
    }
}

