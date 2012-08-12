import java.*;
import java.lang.*;
import java.util.*;
import com.google.gson.*;
import com.google.*;

/*
      <p> This is a polymorphic subclass of Device. This type of device stores data of type
          Float.  

*/
public class NumericalDevice extends Device implements Comparable//DeviceInterface
{
//Expose tagName;
private String tagName = "";
private String stringData;
private Float floatData;
private int deviceType = 0;

/*
 * Numerical Device Constructor, Set the key elements of the time stamp using the Calandar class. 
 * Constructor also sets the name of the device and it's recored data.
 */

NumericalDevice(String tag,String data,int month,int day,int hour, int am_pm)
{
    tagName = tag;
    Float test = new Float(data);
    Boolean result = false;
    floatData = new Float(data);
    //TimeStamp.set(year,month,date,hour,minute);
     timeStamp.set(Calendar.MONTH,month);
     timeStamp.set(Calendar.DATE,day);
     timeStamp.set(Calendar.HOUR,month);
     timeStamp.set(Calendar.AM_PM,am_pm);

}


// Accessor for the objects data

//public String getFloatData()
//{
//  return (String) floatData.toString();
//}

//private fucntion used to assist with compareTo function
private int getAM_PM()
{ return this.timeStamp.get(Calendar.AM_PM);}

//private fucntion used to assist with compareTo function
private int getHour()
{ return this.timeStamp.get(Calendar.HOUR);}

//private fucntion used to assist with compareTo function
public int getDay()
{
   return this.timeStamp.get(Calendar.DATE);
}


public int getMonth()
{
   return this.timeStamp.get(Calendar.MONTH); 
}


public String getTagName()
{
  return tagName.toString();
}

private Float getData()
{
  return floatData;
}
public void accumulate(Device merge)
{
   NumericalDevice temp = (NumericalDevice) merge;
   
   floatData = this.getData() + temp.getData();

}

public Float getFloatData()
{
  return floatData;

}

public String toString()

{
 return(tagName.toString() +  "   " + floatData.toString());
}


public int compareTo(Object otherDevice)
{
 /*
  If passed object is of type other than Device, throw ClassCastException.
 */
      if(!(otherDevice instanceof Device))
       {
        throw new ClassCastException("Invalid object");
       }

      String cTagName = ((Device) otherDevice).getTagName();

      if ((this.getTagName()).compareToIgnoreCase(cTagName) != 0)
           return((this.getTagName()).compareToIgnoreCase(cTagName));

      if(this.getAM_PM() - ((NumericalDevice) otherDevice).getAM_PM()   != 0)
        return(this.getAM_PM() - ((NumericalDevice) otherDevice).getAM_PM());

        return(this.getHour() - ((NumericalDevice) otherDevice).getHour());


}


public JsonObject toJson()
{

   Gson mySon = new Gson();

   JsonObject temp = new JsonObject();

  temp.addProperty(tagName,floatData);
  return temp;
   

}

public Boolean equals(Device otherDevice)
{

  if (otherDevice.getType() == this.getType())
    return true;
    return false;
}

public int getType()
{return deviceType;}

public void SetData()
{}

}
