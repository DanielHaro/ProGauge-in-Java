import java.*;
import java.lang.*;
import java.util.*;
import com.google.gson.*;
import com.google.*;

public class StateDevice extends Device implements Comparable   //implements DeviceInterface
{

private String tagName;
private String stringData;
private int deviceType = 1;

StateDevice(String tag,String data)
{
    tagName = tag;
    Boolean result = false;
    stringData = data;
}


StateDevice(String tag,String data,int month,int day,int hour, int am_pm)
{
     tagName = tag;
     Boolean result = false;
     stringData = data;
     timeStamp.set(Calendar.MONTH,month);
     timeStamp.set(Calendar.DATE,day);
     timeStamp.set(Calendar.HOUR,hour);
     timeStamp.set(Calendar.AM_PM,am_pm);

}

public String getData()
{
  return stringData;
}

public void Accumulate(Device merge)
{
   StateDevice temp = (StateDevice) merge; 
   stringData = (stringData + "\n" + temp.getStringData());

}


public String getStringData()
{
  return stringData.toString();
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

     if(this.getTagName().compareToIgnoreCase(cTagName) !=0)
       {
        return((this.getTagName()).compareToIgnoreCase(cTagName));
       }
     if( ((StateDevice) otherDevice).getAM_PM() - this.getAM_PM()  != 0)
       {
       return( (this.getAM_PM()) -  ((StateDevice) otherDevice).getAM_PM());
       }
  return(this.getHour() - ((StateDevice) otherDevice).getHour()  );
   
}

private int getAM_PM()

{ return this.timeStamp.get(Calendar.AM_PM);}

private int getHour()

{
return timeStamp.get(Calendar.HOUR);
}

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

public Boolean equals(Device otherDevice)
{

  if (otherDevice.getType() == this.getType())
    return true;
    return false;
}

public int getType()
{return deviceType;}


public String toString()

{
 return(tagName.toString() +  "   " + stringData.toString());
}

public JsonObject toJson()
{

   Gson mySon = new Gson();

   JsonObject temp = new JsonObject();

  temp.addProperty(tagName,stringData);
  return temp;
   

}



public void SetData()
{}

}
