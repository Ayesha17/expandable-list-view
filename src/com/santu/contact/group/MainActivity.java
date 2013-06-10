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
				groupName = "Active Life";
				item.id = "Active Life";
			}
			else if(i==1) {
				groupName = "Arts & Entertainment";
				item.id = "Arts & Entertainment";
			}
			else if(i==2) {
				groupName = "Automotive";
				item.id = "Automotive";
			}
			else if(i==3) {
				groupName = "Beauty &s Spas";
				item.id = "Beauty & Spas";
			}
			else if(i==4) {
				groupName = "Bicycles";
				item.id = "Bicycles";
			}
			else if(i==5) {
				groupName = "Education";
				item.id = "Education";
			}
			else if(i==6) {
				groupName = "Event Planning & Services";
				item.id = "Event Planning & Services";
			}
			else if(i==7) {
				groupName = "Food";
				item.id = "Food";
			}
			else if(i==8) {
				groupName = "Health & Medical";
				item.id = "Health & Medical";
			}
			else if(i==9) {
				groupName = "Home Services";
				item.id = "Home Services";
			}
			else if(i==10) {
				groupName = "Hotels & Travel";
				item.id = "Hotels & Travel";
			}
			else if(i==11) {
				groupName = "Local Flavor";
				item.id = "Local Flavor";
			}
			else if(i==12) {
				groupName = "Local Services";
				item.id = "Local Services";
			}
			else if(i==13) {
				groupName = "Mass Media";
				item.id = "Mass Media";
			}
			else if(i==14) {
				groupName = "Night Life";
				item.id = "Night Life";
			}
			else if(i==15) {
				groupName = "Pets";
				item.id = "Pets";
			}
			else if(i==16) {
				groupName = "Professional Services";
				item.id = "Professional Services";
			}
			else if(i==17) {
				groupName = "Public Services & Government";
				item.id = "Public Services & Government";
			}
			else if(i==18) {
				groupName = "Real Estate";
				item.id = "Real Estate";
			}
			else if(i==19) {
				groupName = "Religious Organizations";
				item.id = "Religious Organizations";
			}
			else if(i==20) {
				groupName = "Restaurants";
				item.id = "Restaurants";
			}
			else {
				groupName = "Shopping";
				item.id = "Shopping";
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