package com.zubairriaz.lore.infrastructure;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by zubair on 12/9/2016.
 */

public class ServiceResponse {
    private static final String TAG = "ServiceResponse";

    private String operationError;
    private HashMap<String,String> propertyError;
    private boolean isCritical;

    public ServiceResponse(){
        propertyError = new HashMap<>();
    }
    public ServiceResponse(String operationError){
        this.operationError = operationError;
    }
    public ServiceResponse(String operationError,boolean isCritical){
        this.operationError = operationError;
        this.isCritical = isCritical;
    }

    public String getOperationError() {
        return operationError;
    }

    public void setOperationError(String operationError) {
        this.operationError = operationError;
    }

    public boolean isCritical() {
        return isCritical;
    }

    public void setCritical(boolean critical) {
        isCritical = critical;
    }

    public String getPropertyError(String property) {
        return propertyError.get(property);
    }

    public void setPropertyError(String property, String error) {
        propertyError.put(property,error);
    }
    public  boolean didSucceed (){
        return (operationError==null || operationError.isEmpty())&&(propertyError.size()==0);
    }
    public void showErrorToast (Context context){
        if(context == null || operationError == null || operationError.isEmpty()){
            return;
        }
        try {
            Toast.makeText(context, operationError, Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Log.e(TAG,"error to toast is ",e);
        }
    }
}
