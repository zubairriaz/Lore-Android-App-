package com.zubairriaz.lore.Services;

import com.squareup.otto.Subscribe;
import com.zubairriaz.lore.Service.Contacts;
import com.zubairriaz.lore.Service.entities.ContactRequest;
import com.zubairriaz.lore.Service.entities.UserDetails;
import com.zubairriaz.lore.infrastructure.LoreApplication;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Created by zubair on 12/16/2016.
 */

public class inMemoryContactService extends BaseInMemeoryService {

    public inMemoryContactService(LoreApplication application) {
        super(application);
    }
    private UserDetails createFakeUser(int id,boolean isContact){
        String idString = Integer.toString(id);
        return  new UserDetails(id,isContact,"Contact "+idString,"Contact"+idString,"http://www.gravatar.com/avatar/"+idString+"?d=identicon&s=64");
    }
    @Subscribe
    private void getContactRequest(Contacts.getContactRequestRequest request){
        Contacts.getContactRequestResponse response = new Contacts.getContactRequestResponse();
        response.Requests = new ArrayList<>();
        for (int i =0 ; i<4 ;i++){
            response.Requests.add(new ContactRequest(i,request.fromus,new GregorianCalendar(),createFakeUser(i,false)));

        }
        postDelayed(response);

    }
    @Subscribe
    public void getContacts(Contacts.getContactRequest request){
        Contacts.getContactsResponse response = new Contacts.getContactsResponse();
        response.contacts = new ArrayList<>();
        for (int i =0 ; i<=10 ; i++){
            response.contacts.add(createFakeUser(i,true));
        }
        postDelayed(response);
    }
   @Subscribe
    public void sendContactRequest (Contacts.sendContactRequestRequest request){
      if(request.userId ==2){
          Contacts.sendContactRequestResponse response = new Contacts.sendContactRequestResponse();
          response.setOperationError("Something Bad Happened");
          postDelayed(response);
      }else {
          Contacts.sendContactRequestResponse response = new Contacts.sendContactRequestResponse();
          postDelayed(response);
      }
   }
    @Subscribe
    public void RespondtoContactRequest(Contacts.respondToContactRequestRequest request){
        postDelayed(new Contacts.respondToContactRequestResponse());
    }
}
