package com.box.boxjavalibv2.requests.requestentities;

import java.util.LinkedHashMap;
import java.util.Map;

import com.box.boxjavalibv2.dao.BoxUser;
import com.box.boxjavalibv2.jsonentities.BoxEnterpriseEntity;
import com.box.boxjavalibv2.jsonentities.PairArrayJSONStringEntity;

public class BoxUserRequestEntity extends BoxSimpleUserRequestEntity {

    /**
     * Request entity to create an enterprise user.
     * 
     * @param login
     *            Login(email) of the user.
     * @param name
     *            name of the user
     * @return
     */
    public static BoxUserRequestEntity createEnterpriseUserRequestObject(final String login, final String name) {
        BoxUserRequestEntity entity = new BoxUserRequestEntity();
        entity.setLogin(login);
        entity.setName(name);
        return entity;
    }

    /**
     * Request entity to update an enterprise user. Please note the returned object is not supposed to be used for updating the user's primary login. For that
     * purpose, please use the method: updateUserPrimaryLoginRequestObject(final String login)
     * 
     * @param notify
     *            whether to notify user if user is rolled out of enterprise
     * @return
     */
    public static BoxUserRequestEntity updateUserInfoRequestObject(boolean notify) {
        BoxUserRequestEntity entity = new BoxUserRequestEntity();
        entity.setNotifyUser(notify);
        return entity;
    }

    public void setName(String name) {
        put(BoxUser.FIELD_NAME, name);
    }

    /**
     * Set Login(email) of the user.
     * 
     * @param login
     *            login
     * @return
     */
    public void setLogin(final String login) {
        put(BoxUser.FIELD_LOGIN, login);
    }

    /**
     * Set The the user's enterprise role. The role can be {@link BoxUser#ROLE_ADMIN} , {@link BoxUser#ROLE_COADMIN} or {@link BoxUser#ROLE_USER}.
     * 
     * @param role
     * @return
     */
    public void setRole(String role) {
        put(BoxUser.FIELD_ROLE, role);
    }

    /**
     * @param language
     *            the language to set
     * @return
     */
    public void setLanguage(String language) {
        put(BoxUser.FIELD_LANGUAGE, language);
    }

    /**
     * @param isSyncEnabled
     *            the isSyncEnabled to set
     * @return
     */
    public void setSyncEnabled(boolean isSyncEnabled) {
        put(BoxUser.FIELD_IS_SYNC_ENABLED, Boolean.toString(isSyncEnabled));
    }

    /**
     * @param jobTitle
     *            the jobTitle to set
     * @return
     */
    public void setJobTitle(String jobTitle) {
        put(BoxUser.FIELD_JOB_TITLE, jobTitle);
    }

    /**
     * @param phone
     *            the phone to set
     * @return
     */
    public void setPhone(String phone) {
        put(BoxUser.FIELD_PHONE, phone);
    }

    /**
     * @param address
     *            the address to set
     * @return
     */
    public void setAddress(String address) {
        put(BoxUser.FIELD_ADDRESS, address);
    }

    /**
     * @param spaceAmount
     *            the spaceAmount to set
     * @return
     */
    public void setSpaceAmount(double spaceAmount) {
        put(BoxUser.FIELD_SPACE_AMOUNT, Double.toString(spaceAmount));
    }

    /**
     * @param trackingCodes
     *            the trackingCodes to set
     * @return
     */
    public void setTrackingCodes(final LinkedHashMap<String, String> trackingCodes) {
        PairArrayJSONStringEntity list = new PairArrayJSONStringEntity();
        for (Map.Entry<String, String> entry : trackingCodes.entrySet()) {
            list.put(entry.getKey(), entry.getValue());
        }
        put(BoxUser.FIELD_TRACKING_CODES, list);
    }

    /**
     * @param canSeeManagedUsers
     *            the canSeeManagedUsers to set
     * @return
     */
    public void setCanSeeManagedUsers(final boolean canSeeManagedUsers) {
        put(BoxUser.FIELD_CAN_SEE_MANAGED_USERS, Boolean.toString(canSeeManagedUsers));
    }

    /**
     * @param status
     *            the status to set. Status of the user. This String can be {@link com.box.boxjavalibv2.dao.BoxUser#STATUS_ACTIVE} or
     *            {@link com.box.boxjavalibv2.dao.BoxUser#STATUS_INACTIVE}
     * @return
     */
    public void setStatus(final String status) {
        put(BoxUser.FIELD_STATUS, status);
    }

    /**
     * @param exemptFromDeviceLimits
     *            the exemptFromDeviceLimits to set
     * @return
     */
    public void setExemptFromDeviceLimits(final boolean exemptFromDeviceLimits) {
        put(BoxUser.FIELD_EXEMPT_FROM_DEVICE_LIMITS, Boolean.toString(exemptFromDeviceLimits));
    }

    /**
     * @param exemptFromLoginVerification
     *            the exemptFromLoginVerification to set
     * @return
     */
    public void setExemptFromLoginVerification(final boolean exemptFromLoginVerification) {
        put(BoxUser.FIELD_EXEMPT_FROM_LOGIN_VERIFICATION, Boolean.toString(exemptFromLoginVerification));
    }

    /**
     * @param enterprise
     *            the enterprise to set. Note when updating user information you can set this to null in order to roll the user out from enterprise.
     * @return
     */
    public void setEnterprise(final BoxEnterpriseEntity enterprise) {
        put(BoxUser.FIELD_ENTERPRISE, enterprise);
    }
}
