package com.zubairriaz.lore.DialougeFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.otto.Subscribe;
import com.zubairriaz.lore.R;
import com.zubairriaz.lore.Service.Account;

/**
 * Created by zubair on 12/9/2016.
 */

public class ChangePasswordDialogue extends BasicDailogueFagment implements View.OnClickListener {
    private EditText currentPassword;
    private EditText newPassword;
    private EditText confirmPassword;
    private Dialog progressDailoge;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        View dialogueView = getActivity().getLayoutInflater().inflate(R.layout.change_password_dialogue,null,false);
        currentPassword = (EditText) dialogueView.findViewById(R.id.dailogue_change_password_current_password);
        newPassword = (EditText) dialogueView.findViewById(R.id.dailogue_change_password_new_password);
        confirmPassword= (EditText) dialogueView.findViewById(R.id.dailogue_change_password_again_new_password);
        AlertDialog dialog = new AlertDialog.Builder(getActivity()).setView(dialogueView).setPositiveButton("UPDATE",null)
                .setNegativeButton("CANCEL",null).setTitle("Change Password")
                .show();
        if(!application.getAuth().getUser().getHasPassword()){
            currentPassword.setVisibility(View.GONE);
        }
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(this);
        return dialog;
    }

    @Override
    public void onClick(View view) {
        progressDailoge = new ProgressDialog.Builder(getActivity()).setTitle("Changing Password").setCancelable(false).show();
         bus.post(new Account.changePasswordRequest(currentPassword.getText().toString(),newPassword.getText().toString(),confirmPassword.getText().toString()));

    }
    @Subscribe
    public void passwordChange(Account.changePasswordResponse response){
        progressDailoge.dismiss();
        progressDailoge = null;
        if(response.didSucceed()){
            Toast.makeText(getActivity(), "PASSWORD CHANGED", Toast.LENGTH_SHORT).show();
             dismiss();
            application.getAuth().getUser().setHasPassword(true);
            return;
        }

        currentPassword.setError(response.getPropertyError("current Password"));
        newPassword.setError(response.getPropertyError("new password"));
        confirmPassword.setError(response.getPropertyError("confirm password"));
        response.showErrorToast(getActivity());
    }
}
