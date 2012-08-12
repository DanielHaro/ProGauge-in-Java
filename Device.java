import java.*;
import java.lang.*;
import java.util.*;
import com.google.gson.*;
import com.google.*;


public class Device implements Comparable //implments DeviceInterface
{
  Calendar timeStamp = new GregorianCalendar();
  //private String tagName;
  private String objData;
 // private Float  floatData;
  private String data;
//Constructor stub

public Device()
{}



Device(String tag,String data,int month,int day,int hour, int am_pm)
{
    // tagName = tag;
     //Float test = new Float(data);
     //Boolean result = false;
     //floatData = new Float(data);
    // tagName = tag;
     objData = data; 

     timeStamp.set(Calendar.DATE,day);
     timeStamp.set(Calendar.HOUR,hour);
     timeStamp.set(Calendar.AM_PM,am_pm);
}



//toString fuction 
public String toString()
{
 return("INVALID DEVICE");
}

//Equals Method, Only checks for subtypes not data
public Boolean equals(Device otherDevice)
{
System.out.println("other device-->"+ otherDevice.getType() +"This Device-->"+this.getType());
  if (otherDevice.getType() == this.getType())
    return true;
    return false;
}

//Stub for compare to used in subclasses for sorting
public int compareTo(Object otherDevice)
{return 0;}

//Numerically defines the subType of the Device
public int getType()
{return -1;}

//Accumulate Mutator stub
public void Accumulate(Device merge)
{}

//Get Name of the device for this Object
public String getTagName()
{
   return "Invalid Device";
}

public Boolean isCurrent()
  {
    return(timeStamp.get(Calendar.DATE) == (Calendar.getInstance()).get(Calendar.DATE));
  }

private int getAM_PM()

{ return this.timeStamp.get(Calendar.AM_PM);}

private int getHour()

{ return timeStamp.get(Calendar.HOUR);}

public int getDay()
{
   return this.timeStamp.get(Calendar.DATE);
}

public int getMonth()
{
   return this.timeStamp.get(Calendar.MONTH); 
}


public JsonObject toJson()
{return null;}


public Float getFloatData()
{
  return(new Float(-1));

}

public String getStringData()
{
  return "-1";
}



}
