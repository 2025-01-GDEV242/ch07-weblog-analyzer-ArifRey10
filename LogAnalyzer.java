/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author Arif Reyhan
 * @version  2025.04.04
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer(String logFileName)
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader(logFileName);
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
        
        System.out.println("hourCounts: ");
        for (int i = 0; i < hourCounts.length; i++) 
        {
        System.out.println("Hour " + i + ": " + hourCounts[i]);
        }
    }

    public int numberOfAccesses() 
    {
         int total = 0;
         for(int count : hourCounts)
        {
           total += count;
        }
        
        return total;
    } 
        
    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
    
     public int busiestHour()
     {
        int maxHour = 0;
        for (int hour = 1; hour < hourCounts.length; hour++) {
            if (hourCounts[hour] > hourCounts[maxHour]) {
                maxHour = hour;
            }
        }
        return maxHour;
    }
    
     public int quietestHour() {
        int minHour = 0;
        for (int hour = 1; hour < hourCounts.length; hour++) {
            if (hourCounts[hour] < hourCounts[minHour]) {
                minHour = hour;
            }
        }
        return minHour;
    }
    
     public int busiestTwoHour() 
     {
        int maxSum = 0;  
        int busiestHour = 0;  

        for (int hour = 0; hour < hourCounts.length - 1; hour++) 
        {
            int sum = hourCounts[hour] + hourCounts[hour + 1];   
            
             System.out.println("Sum of hours " + hour + " and " 
             + (hour + 1) + ": " + sum);
            
            if (sum > maxSum) {
                maxSum = sum;  
                busiestHour = hour;  
            }
        }
         System.out.println("The busiest two-hour period starts at hour: " 
                                                 + busiestHour + ":00");
        return busiestHour;  
    }
}
