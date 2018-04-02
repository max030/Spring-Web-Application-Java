package edu.neu.blackboard.service;

import java.util.Collection;

import edu.neu.blackboard.domain.*;

import edu.*;


	
public interface ItemService {
	public void addItems(Items item);
	public Collection<Items> listItems();
public Collection<Items> finditem(String masalaname);

 // publib sme
public Items  find(String itemname);
//public void addorder(String email,String itemname,int quantity,String address);
public void updatequantity(String itemname, int quantity);
}