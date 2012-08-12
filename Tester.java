import java.lang.*;
import java.lang.reflect.*;
import java.util.*;
import com.google.gson.*;
//import com.google.gson.*;
import com.google.gson.JsonElement.*;
import com.google.gson.JsonArray.*;
import com.google.gson.Gson;



/*
 *  file: Tester.java
 *
 * This is a control file to test my code for the Junior Delvoper position at ProGauge.
 *
 * @author Daniel Haro
 * @see HashMap
 * @see Arrays
 * @see Vector
 * @see Calendar
 * @see Collection
 * @see Iterator
 * 
 ########## DO NOT EDIT ABOVE THIS LINE ########## */


public class Tester
{


/*
 * <p>Function RunProgram
 *  Run Program takes an incoming list of Devices and processes them
 *  to produce an indexed output List.
 *
 *  <p> Notes, This implementation takes in a list of devices. The devices are copied into an Array.
 *      The array is then sorted using Arrays.sort(); The logic for the sort can be found in the 
 *      Device subclasses(NumericalDevice and StateDevice) in the compareTo functions. The data is sorted by timeStamps
 *      simplifying the Accumulate process. The sorted array is then used to replace the data in Vector DeviceList.
 *
 *   <p> The devices are now sorted by their recorded TimeStamp and can now be processed.
 *       The data is processed by indexing the device names. On the first occurrence of a device
 *       The Object device is recorded into a hashmap. The hashmap allows easy tracking of first occurrence 
 *       of each device. In the event of additional occurrences of any given device, the data from the additional 
 *       occurrence is appended to the existing Device object and the additional occurrence is dropped.
 *       Any occurrence of a device that is not dated for the current date, is rejected from the list and ignored.
 *
 *       
 *
 *  <p> Finally the Devices stored in the hashmap (the orginal list has now been been condensed) are copied back into an array where they are
 *      sorted by their Tag_Name. Then the array is passed back as output.
 *
 *  @parm deviceList list of devices to be processed
 *  @return Device[] List of processed devices sorted by their Tag_Name
 */



 public Device[] RunProgram(Vector<Device> deviceList)
  {

       HashMap<String,Device> myMap = new HashMap<String,Device>();
       int inputDeviceCount = deviceList.size() -1;
       Device deviceProcessor = new Device();
       Calendar TimeStamp = new GregorianCalendar();
       String testDate = new String(TimeStamp.get(Calendar.YEAR) + "" + TimeStamp.get(Calendar.MONTH) + "" + TimeStamp.get(Calendar.DAY_OF_MONTH));
       Collection<Device> myViewer;
       Device[] outList;
       int counter = 0;

        outList = new Device[deviceList.size()];
        deviceList.toArray(outList);
        Arrays.sort(outList);
        Arrays.sort(outList);
        counter = 0;
        deviceList.clear();

        while(counter <= inputDeviceCount)
         { deviceList.add(outList[counter]);
           counter++;
         }

    counter = 0;


    while(counter <= inputDeviceCount)
     {
       
        deviceProcessor = deviceList.get(counter);//;inputDeviceCount);
        counter++;//inputDeviceCount--;
         if(deviceProcessor.isCurrent())   
          {
            if(!myMap.containsKey(deviceProcessor.getTagName()))
               {
               myMap.put(deviceProcessor.getTagName(),deviceProcessor);
               }
            else
             {
              Device temp = myMap.get(deviceProcessor.getTagName());
                if(deviceProcessor.equals(myMap.get(deviceProcessor.getTagName())))
               {  
                   temp.Accumulate(deviceProcessor);
                   myMap.remove(deviceProcessor.getTagName());
                   myMap.put(deviceProcessor.getTagName(),temp);
               }
              else
               {
                 System.out.println("type miss match error");//+ deviceProcessor.getType() +"  -->" + temp.getType());
               }
          }
       }

     }

     myViewer =  myMap.values();
     outList = new Device[myMap.size()];
     counter = 0;

     Iterator<Device> iter = myViewer.iterator();
       while (iter.hasNext())
         {
           Object reader = iter.next();
           outList[counter++] = (Device) reader;
         }

    Arrays.sort(outList);
    return outList;
 }



/*
 *  Function Main 
 *  <p> Notes, Generates and stores several Devices into a vector, the vector is then
 *       passed into the RUNPROGRAM function. The purpose of this is to set up conditons
 *       to test this programm.
 *
 *
 *
 */


public static void main (String[] args)
{
     Device test = new Device();
     Vector<Device> myList = new Vector<Device>();
     Device generator = new Device();
     Tester testme =new Tester();
     int counter;
     Device[] outList;
     String testToken;
     Gson mySon = new Gson();
     JsonObject temp = new JsonObject();
     int numOfTags;


     myList.add(generator = new NumericalDevice("Pump2","39.4362",6,9,5,0));
     myList.add(generator = new NumericalDevice("AWT3","510",6,9,5,0));
     myList.add(generator = new NumericalDevice("Exhaust2","1537.164",6,9,5,0));

     myList.add(generator = new StateDevice("Cooler2","ON"     ,6,9,5,0));
     myList.add(generator = new StateDevice("Cooler2","OFF" ,6,9,3,0));
     myList.add(generator = new StateDevice("Cooler2","OFF"   ,6,9,9,1));
     myList.add(generator = new StateDevice("Cooler2","ON",6,9,1,0));

     myList.add(generator = new StateDevice("Cooler1","on"     ,6,1,9,1));
     myList.add(generator = new StateDevice("Heater5","active",6,9,5,0));

     myList.add(generator = new StateDevice("PRS2","FAIL"          ,6,9,5,0));
     myList.add(generator = new StateDevice("PRS1","Active"       ,6,9,6,0));
     myList.add(generator = new StateDevice("PRS1","INACTIVE",6,9,7,0));
     myList.add(generator = new StateDevice("GPS","BakersField",6,9,5,1));


     outList = testme.RunProgram(myList);
     numOfTags = Array.getLength(outList);
     counter = 0;

     while(counter < numOfTags)
       {
        if(outList[counter].getType() == 0)
         temp.addProperty(outList[counter].getTagName(),outList[counter].getFloatData());
        else
         temp.addProperty(outList[counter].getTagName(),outList[counter].getStringData());
       counter++;
       }

      String outputString = mySon.toJson(temp);

      outputString = outputString.replaceAll(",",",\n" + " " );
      outputString = outputString.replaceAll("\\{","\\{\n" +" ");
      outputString = outputString.replaceAll(":",": ");
      outputString = outputString.replaceAll("}",",\n}");

      StringTokenizer output2 = new StringTokenizer(outputString,", ",true);
  
      while(output2.hasMoreTokens())
      {
        testToken = output2.nextToken();
        System.out.print(testToken);
      }


}

}
