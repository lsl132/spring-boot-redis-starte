package org.plat.service;

import org.plat.entity.UserInfo;

public interface IUserInfoService {


    UserInfo cacheUserinfo(String id) throws Exception;

    void removeCach(String id) throws Exception;

    UserInfo getCache(String id) throws Exception;

}
