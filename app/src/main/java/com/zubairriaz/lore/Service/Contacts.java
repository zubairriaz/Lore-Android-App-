package com.zubairriaz.lore.Service;

import com.zubairriaz.lore.Service.entities.ContactRequest;
import com.zubairriaz.lore.Service.entities.UserDetails;
import com.zubairriaz.lore.infrastructure.ServiceResponse;

import java.util.List;

/**
 * Created by zubair on 12/16/2016.
 */

public final class Contacts {
    private Contacts(){

    }
    public static class getContactRequestRequest{
        public boolean fromus;

        public getContactRequestRequest(boolean fromus) {
            this.fromus = fromus;
        }
    }
    public static class getContactRequestResponse extends ServiceResponse{
        public List<ContactRequest> Requests;
    }
    public static class getContactRequest{
        public boolean includePendingContacts;

        public getContactRequest(boolean includePendingContacts) {
            this.includePendingContacts = includePendingContacts;
        }
    }
    public static class getContactsResponse extends ServiceResponse{
        public List<UserDetails> contacts;
    }
    public static class sendContactRequestRequest{
          public int userId;

          public sendContactRequestRequest(int userId) {
              this.userId = userId;
          }
      }
    public static class sendContactRequestResponse extends ServiceResponse{

    }
    public static class respondToContactRequestRequest{
        public int ContactRquestId;
        public boolean accept;

        public respondToContactRequestRequest(int contactRquestId, boolean accept) {
            ContactRquestId = contactRquestId;
            this.accept = accept;
        }
    }
    public static class respondToContactRequestResponse extends ServiceResponse{

    }


}
