/*
 * Copyright (c) 2016 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.zhanghai.android.douya.broadcast.content;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import java.util.List;

import me.zhanghai.android.douya.network.api.ApiRequest;
import me.zhanghai.android.douya.network.api.ApiRequests;
import me.zhanghai.android.douya.network.api.info.apiv2.User;
import me.zhanghai.android.douya.util.FragmentUtils;

public class BroadcastLikerListResource extends BroadcastUserListResource {

    private static final String FRAGMENT_TAG_DEFAULT = BroadcastLikerListResource.class.getName();

    private static BroadcastLikerListResource newInstance(long broadcastId) {
        //noinspection deprecation
        BroadcastLikerListResource resource = new BroadcastLikerListResource();
        resource.setArguments(broadcastId);
        return resource;
    }

    public static BroadcastLikerListResource attachTo(long broadcastId, FragmentActivity activity,
                                                      String tag, int requestCode) {
        return attachTo(broadcastId, activity, tag, true, null, requestCode);
    }

    public static BroadcastLikerListResource attachTo(long broadcastId, FragmentActivity activity) {
        return attachTo(broadcastId, activity, FRAGMENT_TAG_DEFAULT, REQUEST_CODE_INVALID);
    }

    public static BroadcastLikerListResource attachTo(long broadcastId, Fragment fragment,
                                                      String tag, int requestCode) {
        return attachTo(broadcastId, fragment.getActivity(), tag, false, fragment, requestCode);
    }

    public static BroadcastLikerListResource attachTo(long broadcastId, Fragment fragment) {
        return attachTo(broadcastId, fragment, FRAGMENT_TAG_DEFAULT, REQUEST_CODE_INVALID);
    }

    private static BroadcastLikerListResource attachTo(long broadcastId, FragmentActivity activity,
                                                      String tag, boolean targetAtActivity,
                                                      Fragment targetFragment, int requestCode) {
        BroadcastLikerListResource resource = FragmentUtils.findByTag(activity, tag);
        if (resource == null) {
            resource = newInstance(broadcastId);
            if (targetAtActivity) {
                resource.targetAtActivity(requestCode);
            } else {
                resource.targetAtFragment(targetFragment, requestCode);
            }
            FragmentUtils.add(resource, activity, tag);
        }
        return resource;
    }

    /**
     * @deprecated Use {@code attachTo()} instead.
     */
    public BroadcastLikerListResource() {}

    @Override
    protected ApiRequest<List<User>> onCreateRequest(Integer start, Integer count) {
        return ApiRequests.newBroadcastLikerListRequest(getBroadcastId(), start, count,
                getActivity());
    }
}
