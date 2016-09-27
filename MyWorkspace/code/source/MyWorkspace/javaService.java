package MyWorkspace;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2016-09-26 15:30:52 IST
// -----( ON-HOST: MCSSIV02.eur.ad.sag

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
// --- <<IS-END-IMPORTS>> ---

public final class javaService

{
	// ---( internal utility methods )---

	final static javaService _instance = new javaService();

	static javaService _newInstance() { return new javaService(); }

	static javaService _cast(Object o) { return (javaService)o; }

	// ---( server methods )---




	public static final void listFiles (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(listFiles)>> ---
		// @sigtype java 3.5
		// [i] field:0:required fileDir
		// [o] field:1:required fileList
		IDataCursor idatacur = pipeline.getCursor();
		String fileDir = null;
		if (idatacur.first("fileDir"))
		{
			fileDir = (String) idatacur.getValue();
		}
		
		File file = new File(fileDir);
		String str[] = file.list();
		
		IDataUtil.put(idatacur, "fileList", str);
		// --- <<IS-END>> ---

                
	}



	public static final void parseString (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(parseString)>> ---
		// @sigtype java 3.5
		// [i] field:0:required ins
		// [o] field:1:required strArr
		IDataCursor cursor = pipeline.getCursor();
		String insStr = null;
		if (cursor.first("ins"))
		  {
			insStr = (String) cursor.getValue();
		  }
		
		String  thisLine = null;
		ArrayList al = new ArrayList();
		 try{
		     // open input stream test.txt for reading purpose.
		     BufferedReader br = new BufferedReader(new StringReader(insStr));
		     while ((thisLine = br.readLine()) != null) {
		        System.out.println(thisLine);
		        al.add(thisLine);		        
		     }
		     
		     int arrSize = al.size();
		     arrSize  = arrSize + 1;
		     String ssr[] = new String[arrSize];
		     System.out.println(al.size());
		     System.out.println(arrSize);
		     Iterator rr = al.iterator();
		     int count = 0;
		     while(rr.hasNext())
		     {
		    	 ssr[count] = (String) rr.next();
			        System.out.println(count);
		    	 count++;
		     }
		        System.out.println(count);
		     
		     ssr[count] = "DiffVar";
		     
			    IDataUtil.put(cursor, "strArr", ssr);
		     
		  }catch(Exception e){
		     e.printStackTrace();
		  }
		
		
			
		// --- <<IS-END>> ---

                
	}



	public static final void readFile (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(readFile)>> ---
		// @sigtype java 3.5
		// [i] field:0:required filePath
		// [i] field:0:required fileName
		// [o] field:0:required fileContent
		IDataCursor idatacur = pipeline.getCursor();
		String filePath = null;
		if (idatacur.first("filePath"))
		{
			filePath = (String) idatacur.getValue();
		}
		
		String fileName = null;
		if (idatacur.first("fileName"))
		{
			fileName = (String) idatacur.getValue();
		}
		else
		{
		  throw new ServiceException("Inputs are null!");
		}
		
		filePath = filePath + "/" + fileName;
		String thisLine = null;
		File file = new File(filePath);
		StringBuffer sb = new StringBuffer();
		try {
			BufferedReader bf = new BufferedReader(new FileReader(file));
		     while ((thisLine = bf.readLine()) != null) {
		    	 sb.append(thisLine + "\n");	        
			     }
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		IDataUtil.put(idatacur, "fileContent", sb.toString());
		
			
		// --- <<IS-END>> ---

                
	}
}

