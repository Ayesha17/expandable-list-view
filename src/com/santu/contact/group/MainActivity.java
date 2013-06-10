package com.santu.contact.group;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds;
import android.provider.ContactsContract.CommonDataKinds.GroupMembership;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Data;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ExpandableListView.ExpandableListContextMenuInfo;

public class MainActivity extends Activity {

	private LinkedHashMap<Item,ArrayList<Item>> groupList;
	private ExpandableListView expandableListView;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initViews();
	}


	private void initViews(){
		initContactList();
		expandableListView = (ExpandableListView)findViewById(R.id.expandableListView);
		ExpandableAdapter adapter = new ExpandableAdapter(this, expandableListView, groupList);
		expandableListView.setAdapter(adapter);

	}
	private void initContactList(){
		groupList = new LinkedHashMap<Item,ArrayList<Item>>();

		ArrayList<Item> groupsList = fetchGroups();
		Log.i("GroupsListSize",String.valueOf(groupsList.size()));

		for(Item item:groupsList){
			Log.i("Item id",item.id);

			String[] ids = item.id.split(",");
			ArrayList<Item> groupMembers =new ArrayList<Item>();
			for(int i=0;i<ids.length;i++){
				String groupId = ids[i];
				Log.i("GroupId",groupId);
				groupMembers.addAll(fetchGroupMembers(groupId));
			}

			item.name = item.name +" ("+groupMembers.size()+")";
			groupList.put(item,groupMembers);
		}


	}



	private ArrayList<Item> fetchGroups(){
		ArrayList<Item> groupList = new ArrayList<Item>();
		


		//List each group 
		for(int i=0; i<21; i++){

		
			
			Item item = new Item();
			String groupName; 
			if(i==0) {
				item.id = groupName = "Active Life";
				
			}
			else if(i==1) {
				item.id = groupName = "Arts & Entertainment";

			}
			else if(i==2) {
				item.id = groupName = "Automotive";
			}
			else if(i==3) {
				item.id = groupName = "Beauty &s Spas";
			}
			else if(i==4) {
				item.id =groupName = "Bicycles";
			}
			else if(i==5) {
				item.id =groupName = "Education";
			} 
			else if(i==6) {
				item.id = groupName = "Event Planning & Services";
				item.id = "Event Planning & Services";
			}
			else if(i==7) {
				item.id=groupName = "Food";
			}
			else if(i==8) {
				item.id=groupName = "Health & Medical";
			}
			else if(i==9) {
				item.id=groupName = "Home Services";
			}
			else if(i==10) {
				item.id=groupName = "Hotels & Travel";
			}
			else if(i==11) {
				item.id=groupName = "Local Flavor";
			}
			else if(i==12) {
				item.id= groupName = "Local Services";
			}
			else if(i==13) {
				item.id=groupName = "Mass Media";
			}
			else if(i==14) {
				item.id=groupName = "Night Life";
			}
			else if(i==15) {
				item.id=groupName = "Pets";
			}
			else if(i==16) {
				item.id=groupName = "Professional Services";
			}
			else if(i==17) {
				item.id=groupName = "Public Services & Government";
			}
			else if(i==18) {
				item.id=groupName = "Real Estate";
			}
			else if(i==19) {
				item.id=groupName = "Religious Organizations";
			}
			else if(i==20) {
				item.id=groupName = "Restaurants";
			}
			else {
				item.id=groupName = "Shopping";
			}
			
			item.name = groupName;

			groupList.add(item);
			System.out.println("the Group List is: " + groupList.toString());
		}

		Collections.sort(groupList,new Comparator<Item>() {

			public int compare(Item item1, Item item2) {

				return item2.name.compareTo(item1.name)<0
						?0:-1;
			}
		});
		return groupList;
	}

	private ArrayList<Item> fetchGroupMembers(String groupId){
		ArrayList<Item> groupMembers = new ArrayList<Item>();

		for(int z=0; z<3; z++){
			Item item = new Item();
			item.name = "Henry";
			item.id = "Yaakov";
			groupMembers.add(item);
		}

		return groupMembers;
	}
}