/* $Id: MibNodeInfo.java,v 1.1 2002/06/15 14:43:37 ram Exp $ */
/*
 * @(#)MibNodeInfo.java
 * Copyright (c) 2012 ZOHO Corp. All Rights Reserved.
 * Please read the associated COPYRIGHTS file for more details.
 */

/**
 * This is a tutorial example program to explain how to write an application to do
 * the get node  information from the MIB files using com.adventnet.snmp.mibs package of
 * AdventNetSNMP api.
 *
 * The user could run this application by giving the following usage.
 *  
 * java MibNodeInfo mibfile OID 
 *
 * where 
 *
 * 
 * mibfile is the name of the MIB file that is loaded. 
 * OID is the Object Identifier. 
 * The entire OID can be given or it can be given in the form of 1.1.0.
 * If the oid is not starting with a dot (.) it will be prefixed by .1.3.6.1.2.1 .
 * So the entire OID of 1.1.0 will become .1.3.6.1.2.1.1.1.0 .
 *
 * Example usage:
 *
 * java MibNodeInfo RFC1213-MIB 1.1.0 
 *
 */

import com.adventnet.snmp.snmp2.*;
import com.adventnet.snmp.mibs.*;

public class MibNodeInfo {

public static void main(String args[]) {
    
            if( args.length < 2)
	{
		System.out.println("Usage : java MibNodeInfo mibfile OID ");
		System.exit(0);
	}
        
	// Take care of getting OID and the MIB file name

	    String mibfile = args[0];
	    String OID = args[1];       

	// load the MIB file 
	MibOperations mibops = new MibOperations();
	try {
	mibops.loadMibModules(mibfile);
	} catch (Exception ex){
	     System.err.println("Error loading MIBs: "+ex);
	}


	// add OIDs

	 SnmpOID oid = mibops.getSnmpOID(OID);
	 MibNode node = mibops.getMibNode(oid);
	
	if(node == null) 
	        {
			System.out.println("Invalid OID / the node " + oid + " is not available");
		}
	else 
	    {

		System.out.println("Syntax:"+node.getSyntax()+"\n"+
                   "Access:"+node.printAccess()+"\n"+
                   "Status:"+node.getStatus()+"\n"+
                   "Reference:"+node.getReference()+"\n"+
                   "OID:"+node.getNumberedOIDString()+"\n"+
                   "Node:"+node.getOIDString()+"\n"+
                   "Description:"+node.getDescription()+"\n");
	    }
	
 }

}