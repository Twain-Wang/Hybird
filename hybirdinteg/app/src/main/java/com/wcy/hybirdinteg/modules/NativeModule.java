package com.wcy.hybirdinteg.modules;

import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NativeModule {
    public static void sendMessage(){
        Map<String,Object> map = new HashMap<>();
        map.put("test","info====1111");
        List<WXSDKInstance> wxsdkInstances = WXSDKManager.getInstance().getWXRenderManager().getAllInstances();
        for (int i = 0; i < wxsdkInstances.size();i++){
            wxsdkInstances.get(i).fireGlobalEventCallback("testMethod",map);
        }
    }
}
