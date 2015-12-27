package uk.co.jacekk.bukkit.baseplugin.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class ListUtils
{
  private static final Random rand = new Random();
  
  public static long sumLongs(Collection<Long> numbers)
  {
    long sum = 0L;
    for (Iterator<Long> i$ = numbers.iterator(); i$.hasNext();)
    {
      long value = ((Long)i$.next()).longValue();
      sum += value;
    }
    return sum;
  }
  
  public static int sumIntegers(Collection<Integer> numbers)
  {
    int sum = 0;
    for (Iterator<Integer> i$ = numbers.iterator(); i$.hasNext();)
    {
      int value = ((Integer)i$.next()).intValue();
      sum += value;
    }
    return sum;
  }
  
  public static double stddev(Collection<Long> numbers)
  {
    double mean = sumLongs(numbers) / numbers.size();
    
    double stdDevSum = 0.0D;
    for (Long number : numbers)
    {
      double diff = number.longValue() - mean;
      stdDevSum += diff * diff;
    }
    return Math.sqrt(stdDevSum / numbers.size());
  }
  
  public static String implode(String sep, List<?> values)
  {
    if (values.size() == 0) {
      return "";
    }
    StringBuilder builder = new StringBuilder();
    
    builder.append(values.get(0).toString());
    for (int i = 1; i < values.size(); i++)
    {
      builder.append(sep);
      builder.append(values.get(i).toString());
    }
    return builder.toString();
  }
  
  public static <T> T getRandom(List<T> items)
  {
    return (T)items.get(rand.nextInt(items.size()));
  }
}
