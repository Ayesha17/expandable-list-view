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
		//if(groupList.size()>0){
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
		//}

	}



	private ArrayList<Item> fetchGroups(){
		ArrayList<Item> groupList = new ArrayList<Item>();
		
		ArrayList<String> groupTitle = new ArrayList<String>();

		//List each group 
		for(int i=0; i<5; i++){
			Item item = new Item();
			String groupName; 
			if(i%2==3) {
				groupName = "Aleph";
				item.id="Aleph";
			}
			else if(i%4==1) {
				groupName = "Bet";
				item.id="Bet";
			}
			else {
				groupName = "Gimmel";
				item.id="Gimmel";
			}


			groupTitle.add(groupName);
			System.out.println("the Group Title is: " +groupTitle.toString());
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