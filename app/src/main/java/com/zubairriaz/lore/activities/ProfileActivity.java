package com.zubairriaz.lore.activities;

import android.app.Dialog;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.soundcloud.android.crop.Crop;
import com.squareup.otto.Subscribe;
import com.zubairriaz.lore.DialougeFragment.ChangePasswordDialogue;
import com.zubairriaz.lore.R;
import com.zubairriaz.lore.Service.Account;
import com.zubairriaz.lore.infrastructure.User;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zubair on 12/8/2016.
 */

public class ProfileActivity extends BaseAuthenticatedActivity implements View.OnClickListener {
 private ImageView avatarView;
    private View frameProgressView;
    private Dialog progressDialog;
    private static boolean isProgressVisible;
    private File tempfile;
    private View changeAvatar;
    private EditText displayName;
    private EditText emailAdd;
    private ActionMode editProfileActionMode;
    private int REQUEST_CODE_IMAGE =200;
    private static final int STATE_VIEWING =1;
    private static final int STATE_EDITING =2;
    private int currentSTATE;
    private static final String BUNDLE_STRING = "BUNDLE_STRING";

    @Override
    protected void onLoreCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_profie);
        createNavDrawer();
        avatarView = (ImageView) findViewById(R.id.activity_profile_avatar);
        frameProgressView = (View) findViewById(R.id.activity_profile_avatar_progress_frame);
        displayName = (EditText)findViewById(R.id.activity_profile_displayname);
        emailAdd = (EditText) findViewById(R.id.activity_profile_email) ;
        changeAvatar = (View) findViewById(R.id.activity_profile_tap_change);
        tempfile = new File(getExternalCacheDir(),"temp-image.jpg");
        avatarView.setOnClickListener(this);
        findViewById(R.id.activity_profile_tap_change).setOnClickListener(this);
        frameProgressView.setVisibility(View.GONE);
        if(savedInstanceState == null){
        User user = application.getAuth().getUser();
        displayName.setText(user.getDisplayName());
        emailAdd.setText(user.getEmail());
        getSupportActionBar().setTitle(user.getDisplayName().toString());

        changeState (STATE_VIEWING);}
        else {
            changeState(savedInstanceState.getInt(BUNDLE_STRING));
        }
        if(isProgressVisible){
            setProgressVisible(true);
        }

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(BUNDLE_STRING,currentSTATE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_profile_menu,menu);
        return true;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
      int itemId =  item.getItemId();
        if(itemId == R.id.profile_menu_edit){
            changeState(STATE_EDITING);
            return true;
        }else if(itemId == R.id.profile_menu_edit_password){
            FragmentTransaction transaction = getFragmentManager().beginTransaction().addToBackStack(null);
            ChangePasswordDialogue dialogue = new ChangePasswordDialogue();
            dialogue.show(transaction,null);
            return true;
        }
        return false;
    }

    private void changeState(int state) {
        if(state == currentSTATE){
            return;
        }
        currentSTATE =state;
        if (state == STATE_VIEWING){
            displayName.setEnabled(false);
            emailAdd.setEnabled(false);
            changeAvatar.setVisibility(View.VISIBLE);
            if(editProfileActionMode != null){
                editProfileActionMode.finish();
                editProfileActionMode=null;
            }

        }else if(state == STATE_EDITING){
            displayName.setEnabled(true);
            emailAdd.setEnabled(true);
            changeAvatar.setVisibility(View.GONE);

            editProfileActionMode = toolbar.startActionMode(new EditActionModeClass());


        } else throw  new IllegalArgumentException("INVALID STATE");
    }

    @Override
    public void onClick(View view) {
      int viewId =  view.getId();
        if(viewId == R.id.activity_profile_avatar || viewId == R.id.activity_profile_tap_change){
            changeAvatar();
        }
    }

    private void changeAvatar() {
        List<Intent> otherImageCaptureIntents = new ArrayList<>();
        List<ResolveInfo> otherImageCaptureActivities = getPackageManager().queryIntentActivities(new Intent(MediaStore.ACTION_IMAGE_CAPTURE),0);
        for(ResolveInfo info : otherImageCaptureActivities){
            Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            captureIntent.setClassName(info.activityInfo.packageName,info.activityInfo.name);
            captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempfile));
            otherImageCaptureIntents.add(captureIntent);
        }
        Intent selectImage = new Intent(Intent.ACTION_PICK);
        selectImage.setType("image/*");




        Intent chooserIntent = Intent.createChooser(selectImage,"Choose Image to Change");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS,otherImageCaptureIntents.toArray(new Parcelable[otherImageCaptureIntents.size()]));

        startActivityForResult(chooserIntent,REQUEST_CODE_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri outputFile ;
        Uri tempFileUri = Uri.fromFile(tempfile);
        if(resultCode != RESULT_OK){
            tempfile.delete();
            return;
        }
        if(requestCode == REQUEST_CODE_IMAGE){

           if(data!=null && (data.getAction() == null || !data.getAction() .equals(MediaStore.ACTION_IMAGE_CAPTURE))){
               outputFile = data.getData();
           }else
               outputFile = tempFileUri;
            Crop.of(outputFile,tempFileUri).asSquare().start(this);

        }else  if(requestCode == Crop.REQUEST_CROP){
            frameProgressView.setVisibility(View.VISIBLE);
            bus.post(new Account.changeAvatarRequest(tempFileUri));


        }
    }
    @Subscribe
    public void changeAvatar(Account.changeAvatarResponse response){
        frameProgressView.setVisibility(View.GONE);
        if(response.didSucceed()){
            Toast.makeText(ProfileActivity.this, "Succed", Toast.LENGTH_SHORT).show();
        }else {
            response.showErrorToast(this);
        }
    }
    @Subscribe
    public void updateProfile (Account.updateProfileResponse response){
        if(!response.didSucceed()){
            response.showErrorToast(this);
            changeState(STATE_EDITING);
        }
        displayName.setError(response.getPropertyError("dsiplayName"));
        emailAdd.setError(response.getPropertyError("Email ADD"));
        setProgressVisible(false);

    }
   private void setProgressVisible(boolean visible){
       if(visible){
           progressDialog = new ProgressDialog.Builder(this).setTitle("UPdating Profile").setCancelable(false).show();

       }else if(progressDialog!=null){
           progressDialog.dismiss();
           progressDialog = null;
       }
       isProgressVisible = visible;
   }




    private class EditActionModeClass implements ActionMode.Callback {

        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            getMenuInflater().inflate(R.menu.activity_profile_edit,menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
          if(menuItem.getItemId() == R.id.edit_menu_done){
              //TODO send to server
              setProgressVisible(true);
               changeState(STATE_VIEWING);
              bus.post(new Account.updateProfileRequest(displayName.getText().toString(),emailAdd.getText().toString()));

              return true;
          }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            if(currentSTATE != STATE_VIEWING){
                changeState(STATE_VIEWING);
            }

        }
    }
    @Subscribe
    public void onUserdetailsupdated(Account.userDetailsUpdated event){
        getSupportActionBar().setTitle(event.user.getDisplayName());
        NAME = event.user.getDisplayName();
        EMAIL = event.user.getEmail();
        createNavDrawer();
    }

}
